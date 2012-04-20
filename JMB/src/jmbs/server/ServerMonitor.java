package jmbs.server;

import java.util.HashMap;

public final class ServerMonitor {

	private static ServerMonitor instance = null;
	HashMap<String, ConnectionInformation> activeConnections = new HashMap<String, ConnectionInformation>();
	HashMap<Integer,String> activeAccounts = new HashMap<Integer,String>();
	Security s = new SecurityDAO(new Connect().getConnection());

	private ServerMonitor() {
	}
	
	/**
	 * Gets the instance of the singleton ServerMonitor.
	 * @return ServerMonitor current instance
	 */
	public final static ServerMonitor getInstance() {
		if (instance == null) {
			synchronized (Configuration.class) {
				if (instance == null)
					instance = new ServerMonitor();
			}
		}
		return instance;
	}
	
	/**
	 * Adds a connection to the server monitor if the ip is not banned and hasn't set any other connections.
	 * @param ci
	 * @return
	 * @throws SecurityException
	 */
	public void addConnection(ConnectionInformation ci) throws SecurityException{
		activeConnections.put(ci.getIp(),ci);
	}

	public void closeConnection(String ip) {
		activeConnections.remove(ip);
	}
	
	public void logOff (int userid){
		activeAccounts.remove(userid);
	}
	
	public void connectUserUnderIp(int userid, String ip){
		activeAccounts.put(userid, ip);
	}

	public int getActiveConnections() {
		return activeConnections.size();
	}
	
	public boolean isAcountActive(int iduser){
		return activeAccounts.containsKey(iduser);
	}
	
	public boolean isConnectionActive(String ip){
		return activeConnections.containsKey(ip);
	}
	
	public ConnectionInformation getConnectionInformations (int userid){
		return (getConnectionInformations(activeAccounts.get(userid)));
	}
	
	public ConnectionInformation getConnectionInformations (String ip){
		return activeConnections.get(ip);
	}
}
