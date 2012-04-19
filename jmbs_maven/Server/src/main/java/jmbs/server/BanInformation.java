package jmbs.server;

import java.sql.Timestamp;

public class BanInformation {

	private String ip;
	private Timestamp expiration;
	private String reason;
	private boolean lifeban = false;
	private static final String END_MESSAGE= "\nPlease contact your server administrator for further informations.";
	

	public BanInformation(String ip, String reason, Timestamp expiration){
		this.ip=ip;
		this.expiration=expiration;
		this.reason=reason;
	}
	
	public BanInformation(String ip, String reason){
		this.ip=ip;
		this.reason=reason;
		this.lifeban=true;
	}

	/**
	 * @return the lifeban
	 */
	public boolean isLifebaned() {
		return lifeban;
	}

	/**
	 * @param lifeban the lifeban to set
	 */
	public void setLifeban(boolean lifeban) {
		this.lifeban = lifeban;
	}
	
	/**
	 * @return the ip
	 */
	public String getIp() {
		return ip;
	}

	/**
	 * @param ip the ip to set
	 */
	public void setIp(String ip) {
		this.ip = ip;
	}

	/**
	 * @return the expiration
	 */
	public Timestamp getExpiration() {
		return expiration;
	}

	/**
	 * @param expiration the expiration to set
	 */
	public void setExpiration(Timestamp expiration) {
		this.expiration = expiration;
	}

	/**
	 * @return the reason
	 */
	public String getReason() {
		return reason;
	}

	/**
	 * @param reason the reason to set
	 */
	public void setReason(String reason) {
		this.reason = reason;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		if (this.lifeban){
			return ("Ip address " + ip + " is banned for life under the following reason: " + reason +  END_MESSAGE);
		} else return ("Ip address " + ip + " is banned until " + expiration + " for reason " + reason +  END_MESSAGE);
	}
}
