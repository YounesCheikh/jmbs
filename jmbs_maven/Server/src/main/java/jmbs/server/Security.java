package jmbs.server;


public interface Security{
	
	public BanInformation getBanInformation(String ip);
	
	public boolean isBanned (String ip);
	
	public boolean isUserConnectionAttemptAuthorized(String key);
	
	public boolean isConnectionToServerAuthorized(String ip);
	
	public boolean removeBan(String ip);	
}
