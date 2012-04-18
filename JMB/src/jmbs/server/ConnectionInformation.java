package jmbs.server;

import java.sql.Timestamp;
import java.util.Calendar;

public class ConnectionInformation {
	
	private int numberOfAttemps = 0;
	private Timestamp firstTry;
	private Timestamp lastTry;
	private String ip;
	
	/**
	 * Created when the connection to the server is established before the user logs in.
	 * @param ip
	 */
	public ConnectionInformation(String ip){
		this.ip = ip;
		this.firstTry = new Timestamp(Calendar.getInstance().getTimeInMillis());
		this.lastTry = firstTry;
	}
	
	public void connectionAttempt(){
		this.numberOfAttemps ++;
		this.lastTry = new Timestamp(Calendar.getInstance().getTimeInMillis());
	}

	public long getFirstTryTime(){
		return firstTry.getTime();
	}
	
	public long getLastTryTime(){
		return lastTry.getTime();
	}
	
	/**
	 * @return the numberOfAttemps
	 */
	public int getNumberOfAttemps() {
		return numberOfAttemps;
	}

	/**
	 * @return the firstTry
	 */
	public Timestamp getFirstTry() {
		return firstTry;
	}

	/**
	 * @return the lastTry
	 */
	public Timestamp getLastTry() {
		return lastTry;
	}

	/**
	 * @return the ip
	 */
	public String getIp() {
		return ip;
	}
}
