package jmbs.server;

import java.util.HashMap;

public final class ServerMonitor {

	private static ServerMonitor instance = null;
	HashMap<String, ConnectionInformation> activeConnections = new HashMap<String, ConnectionInformation>();
	HashMap<Integer,String> activeAccounts = new HashMap<Integer,String>();
	Security s = new SecurityDAO(new Connect().getConnection());

	private ServerMonitor() {
	}

	public final static ServerMonitor getInstance() {
		if (instance == null) {
			synchronized (Configuration.class) {
				if (instance == null)
					instance = new ServerMonitor();
			}
		}
		return instance;
	}
	
	public ConnectionInformation getConnectionInformations (int userid){
		return (getConnectionInformations(activeAccounts.get(userid)));
	}
	
	public ConnectionInformation getConnectionInformations (String ip){
		return activeConnections.get(ip);
	}

	public boolean addConnection(ConnectionInformation ci) throws SecurityException{
		String ip = ci.getIp();
		
		if (!isConnectionActive(ip)) { 
			if (!s.isBanned(ip)){
				activeConnections.put(ip, ci);
			}else {
				throw new SecurityException(s.getBanInformation(ip).toString());
				}			
		} else
			throw new SecurityException("You are trying to set 2 connections from de same ip adress at the same time.\n Please log of and try again.");
		return true;
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

	public boolean connectToAccount(String ip) {
		ConnectionInformation ci = activeConnections.get(ip);
		ci.connectionAttempt();
		
		return (s.isConnectionAuthorized(ci));	
	}

	public void closeConnection(String ip) {
		activeConnections.remove(ip);
	}
	
	public void logOff (int userid){
		activeAccounts.remove(userid);
	}
	
}
