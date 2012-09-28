package jmbs.common;

import java.rmi.server.RemoteServer;
import java.rmi.server.ServerNotActiveException;
import java.sql.Timestamp;
import java.util.Calendar;

public class ConnectionInformation {

	private int userId = UNREGISTERED_USER;
	private int numberOfAttemps = 0;
	private String connectionNumber;
	private String ip;
	private Timestamp firstTry;
	private Timestamp lastTry;
	//private Timestamp lastRequest;

	private final static int UNREGISTERED_USER = 0;

	/**
	 * Created when the connection to the server is established before the user
	 * logs in.
	 * 
	 * @param ip
	 */
	public ConnectionInformation(String connectionNumber) {
		try {
			this.ip = RemoteServer.getClientHost();
			this.connectionNumber = connectionNumber;
			this.firstTry = new Timestamp(Calendar.getInstance().getTimeInMillis());
			this.lastTry = firstTry;
		} catch (ServerNotActiveException e) {
			System.err.println("[PROGRAMATION PROBLEM] Object ConnectionInformation created outside a rmi call generated thread.");
		}
	}

	public void connectionAttempt() {
		this.numberOfAttemps++;
		this.lastTry = new Timestamp(Calendar.getInstance().getTimeInMillis());
	}

	/**
	 * @param numberOfAttemps
	 *            the numberOfAttemps to set
	 */
	public void logIn(int userId) {
		this.numberOfAttemps = 0;
		this.userId = userId;
	}
	
	public void logOut(){
		this.userId = UNREGISTERED_USER;
	}

	/**
	 * Returns the connection number represented as a string.
	 * @return String - the connectionNumber
	 */
	public String getConnectionNumber() {
		return this.connectionNumber;
	}

	/**
	 * @return Timestamp - the first Try
	 */
	public Timestamp getFirstTry() {
		return firstTry;
	}

	/**
	 * @return long - the first try time in ms
	 */
	public long getFirstTryTime() {
		return firstTry.getTime();
	}

	/**
	 * @return the ip
	 */
	public String getIp() {
		return ip;
	}

	/**
	 * @return Timestamp - the lastTry
	 */
	public Timestamp getLastTry() {
		return lastTry;
	}

	/**
	 * @return long - the last try time in ms
	 */
	public long getLastTryTime() {
		return lastTry.getTime();
	}

	/**
	 * @return the number of attempts
	 */
	public int getNumberOfAttemps() {
		return numberOfAttemps;
	}

	/**
	 * @return the userId
	 */
	public int getUserId() {
		return userId;
	}


}
