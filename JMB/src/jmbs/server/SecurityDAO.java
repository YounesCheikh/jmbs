package jmbs.server;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;

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
	}	/**Gives the informations on the ban. It is highly advised to use isBanned(String ip) before.
	 * 
	 * @param ip - the ip adress
	 * @return BanInformation informations on ban.
	 */
	
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
			if(!rs.getBoolean("lifeban") && !isBanEffective(rs.getTimestamp("expiration", Calendar.getInstance()))){
				if(!removeBan(ip)) System.err.println("DB ERROR : Ban on " + ip + " is no more effective but it hasn't been removed.");
				res = false;
			}else res = true;
			
		} catch (SQLException e) {
			res = false; // useless but for code comprehension 
		}
		
		return res;
	}
	
	/**
	 * Given an ip adress this method checks if the ip is authorized to try to connec to to an account.
	 * 
	 *  @param ip - the ip adress
	 *  @return  true if the connection is authorized, false if the number of attempts is too high.
	 */
	public boolean isUserConnectionAttemptAuthorized(String ip){ 
		ConnectionInformation i = ServerMonitor.getInstance().getConnectionInformations(ip);
		i.userConnectionAttempt();
		
		long interval = (i.getLastTryTime() - i.getFirstTryTime());
		long zone = interval/SUSPECT_INTERVAL;
		long limit = SUSPECT_ATTEMPTS_NUMBER*(zone+1);
		
		return (i.getNumberOfAttemps() <= limit);
	}
	
	/**Says if the ip adress is authorized to try to connect to the server.
	 * 
	 * @param ip - the ip adress
	 * @return true - if the distant computer is allowed to connect.
	 */
	public boolean isConnectionToServerAuthorized(String ip){
		boolean b = true;
		if (!ServerMonitor.getInstance().isConnectionActive(ip)) { //if connection is not active
			if (!isBanned(ip)){ //and ip not banned
				b = true;//add it to active connections
			}else { //Means account is banned.
				b = false;
				throw new SecurityException(getBanInformation(ip).toString());
				}			
		} else { //Means the ip already has an active connection.
			b = false; // TODO: handle that correctly !
			throw new SecurityException("You are trying to set 2 connections from de same ip adress at the same time.\n Please log of and try again.");
		}
		return b;
	}
	
	public boolean removeBan(String ip){
		set("DELETE FROM banned WHERE ip = ?");
		setString(1,ip);
		return executeUpdate();
	}
	
	public void createSuspectProfile(ConnectionInformation i){
		
	}
	
	public void saveSuspectInformation(ConnectionInformation i){
		
	}
	
	private boolean isBanEffective (Timestamp t){
		Timestamp currentTime = new Timestamp(Calendar.getInstance().getTimeInMillis());
		return (currentTime.after(t));
	}
	
}
