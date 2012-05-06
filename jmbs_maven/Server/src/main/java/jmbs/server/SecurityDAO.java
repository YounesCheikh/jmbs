package jmbs.server;

import jmbs.common.DAO;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;

import jmbs.common.ConnectionInformation;

/**
 * This class is used to access Security information stored in the db. Since this class is separated from the others
 * by the Security interface, it is possible to store security information in files or in any other support by only changing 
 * the object access method.
 * 
 * @author benbabic
 *
 */
public class SecurityDAO extends DAO implements Security{

	private static final long serialVersionUID = 7440865625633853755L;
	final static long SUSPECT_INTERVAL = 600000; //ms = 10 min
	final static int SUSPECT_ATTEMPTS_NUMBER = 5;
	

	public SecurityDAO(Connection con){
		super(con);
	}
	
	/**Gives the informations on the ban. It is highly advised to use isBanned(String ip) before.
	 * 
	 * @param ip - the ip adress
	 * @return BanInformation informations on ban.
	 */
	public BanInformation getBanInformation(String ip){
		set("SELECT * FROM banned WHERE ip = ?");
		setString(1,ip);
		ResultSet rs = executeQuery();
		BanInformation bi = null;
		
			try {
				Timestamp exp = rs.getTimestamp("expiration", Calendar.getInstance());
				String reason = rs.getString("Reason");
				boolean lb = rs.getBoolean("lifeban");
				
				if (!lb) bi = new BanInformation(ip, reason, exp);
				else bi = new BanInformation(ip, reason);				
				//User is banned.
			} catch (SQLException e) {
				System.out.println("Ip " + ip + " is not banned.");
			}
			
		return bi;			
	}
        
                /**
         * Tells if the user has at least the access level passed in parameter.
         * \n\n
         * It returns (getAccessLevel(userId) <= accessLevel)
         * 
         * 
         * @param userId - the user id to check
         * @param accessLevel - the requiered access level
         * @return true if the access level is sufficiant false if not
         */
        public boolean isAccessLevelSufficiant(int userId,int accessLevel){
            return (getAccessLevel(userId) <= accessLevel);
        }    
	
	/**This method checks in the db if the ip address occurs in the ban table. If so, it checks
	 * if the ban expiration is over and removes the ban from the db if it is and returns false.
	 * <br> Else it returns true. For more informations you can call getBanInformaton(String ip)
	 * 
	 * @param ip - the ip adress
	 * @return true if user is banned else if not.
	 */
	public boolean isBanned (String ip){
		
		set("SELECT lifeban,expiration FROM banned WHERE ip = ?");
		setString(1,ip);
		ResultSet rs = executeQuery();
		boolean res = false;
		
		try {
			if(!rs.getBoolean("lifeban") && !isPast(rs.getTimestamp("expiration", Calendar.getInstance()))){ // if ban has expired
				if(!removeBan(ip)) System.err.println("DB ERROR : Ban on " + ip + " is no more effective but it hasn't been removed."); // try to remove if print error if not removed
				res = false;
			}else res = true; // else return true;
			
		} catch (SQLException e) { // if the exception is caught it means the query was empty: ip is not banned !
			res = false; // useless but for code comprehension 
		}
		
		return res;
	}
	
	/**Says if the ip address is authorized to try to connect to the server.
	 * 
	 * @param ip - the ip address
	 * @return true - if the distant computer is allowed to connect.
	 */
	public boolean isConnectionToServerAuthorized(String ip){
		boolean b = true;
		
		if (!isBanned(ip))b = true;
		else { 
			b = false;
			throw new SecurityException(getBanInformation(ip).toString());
		}			
		return b;
	}

	/**
	 * checks if the date has past or not
	 * @param t - timestamp to check
	 * @return true if t is before current time.
	 */
	private boolean isPast (Timestamp t){
		Timestamp currentTime = new Timestamp(Calendar.getInstance().getTimeInMillis());
		return (currentTime.after(t));
	}

	/**
	 * Given a key this method checks if the key is authorized to try to connect to to an account.
	 * 
	 *  @param key - the key
	 *  @return  true if the connection is authorized, false if the number of attempts is too high.
	 */
	public boolean isUserConnectionAttemptAuthorized(String key){ 
		ConnectionInformation i = ServerMonitor.getInstance().getConnectionInformations(key);
		i.connectionAttempt();
		
		long interval = (i.getLastTryTime() - i.getFirstTryTime());
		long zone = interval/SUSPECT_INTERVAL;
		long limit = SUSPECT_ATTEMPTS_NUMBER*(zone+1);
		
		return (i.getNumberOfAttemps() <= limit);
	}

        /**
         * Gives the user authorisation level
         * @param iduser - the user
         * @return the authorisation level
         * @throws SecurityException when user does not exists
         */
        public int getAccessLevel (int iduser) throws SecurityException{
		int ret = 100;
		if (new UserDAO(con).exists(iduser)) {
			set("SELECT authlvl FROM users WHERE iduser=?");
			setInt(1,iduser);
			ResultSet res = executeQuery();
		
			try {
				ret = res.getInt("authlvl");
			} catch (SQLException e) {
				System.err.println("Unexcepted error !");
			}
		}else throw new SecurityException("User "+iduser+" not found.");
		
		return ret;
	}
                
	/**
	 * Removes a ban from the db
	 * @param ip - the ip to un-ban
	 * @return true if the removal was done correctly, false otherwise
	 */
	public boolean removeBan(String ip){
		set("DELETE FROM banned WHERE ip = ?");
		setString(1,ip);
		return executeUpdate();
	}
	
	/**
	 * Bans a ip until an ordered time for the specified reason if the user is already banned
	 * @param ip - ip to ban
	 * @param reason - the reason
	 * @param until - ban end timestamp
	 */
	public void setBan(String ip, String reason, Timestamp until){
		set("INSERT INTO banned (ip,expiration,reason,lifeban) VALUES (?,?,?,0)");
		setString(1,ip);
		setTimestamp(2, until);
		setString(3,reason);
		if (!executeUpdate()){
			set("UPDATE banned SET expiration = ? WHERE ip = ?");
			setTimestamp(1, until);
			setString(2,ip);
			executeUpdate();
			
			set("UPDATE banned SET reason = ? WHERE ip = ?");
			setString(1,reason);
			setString(2,ip);
			executeUpdate();
		}
	}	
}
