package jmbs.server;


public interface Security{
	
	public BanInformation getBanInformation(String ip);
	
	public boolean isBanned (String ip);
	
	public boolean isConnectionAuthorized(ConnectionInformation i);
	
	public boolean removeBan(String ip);	
}
