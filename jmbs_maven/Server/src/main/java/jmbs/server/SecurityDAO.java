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
	final static long SUSPECT_INTERVAL = 60000; //ms = 10 min
	final static int SUSPECT_ATTEMPTS_NUMBER = 5;
	

	public SecurityDAO(Connection con){
		super(con);
	}
	
	public BanInformation getBanInformation(String ip){
		set("SELECT * FROM banned WHERE ip = ?");
		setString(1,ip);
		ResultSet rs = executeQuery();
		BanInformation bi = null;
		
			try {
				Timestamp exp = rs.getTimestamp("expiration", Calendar.getInstance());
				String reason = rs.getString("Reason");
				boolean lb = rs.getBoolean("lifeban");
				
				if (!lb) {
					bi = new BanInformation(ip, reason, exp);
					if (!isBanEffective(bi)) {
						bi = null;
						if (!removeBan(ip)) {
							System.err.println("DB ERROR : Ban " + bi + " is no more effective but it hasn't been removed.");
						}
					}
				}
				else {
					bi = new BanInformation(ip, reason);
				}
				
				//User is banned.
			} catch (SQLException e) {
				System.out.println("Ip " + ip + " is not banned.");
			}
			
		return bi;			
	}
	
	public boolean isBanned (String ip){
		set("SELECT lifeban FROM banned WHERE ip = ?");
		setString(1,ip);
		ResultSet rs = executeQuery();
		boolean res = false;
		
		try {
			rs.getBoolean("lifeban");
			res = true;
		} catch (SQLException e) {
			res = false; // useless but for code comprehension 
		}
		
		return res;
	}
	
	public boolean isConnectionAuthorized(ConnectionInformation i){ 
		long interval = (i.getLastTryTime() - i.getFirstTryTime());
		long zone = interval%SUSPECT_INTERVAL;
		long limit = SUSPECT_ATTEMPTS_NUMBER*(zone+1);
		
		boolean b = (i.getNumberOfAttemps() <= limit);
		
		return b;
	}
	
	public boolean removeBan(String ip){
		set("DELETE FROM banned WHERE ip = ?");
		setString(1,ip);
		return executeUpdate();
	}
	
	private boolean isBanEffective(BanInformation bi){
		Timestamp currentTime = new Timestamp(Calendar.getInstance().getTimeInMillis());
		return (!bi.isLifebaned() && currentTime.after(bi.getExpiration()));
	}
	
}
