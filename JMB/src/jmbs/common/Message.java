package jmbs.common;

import java.sql.Date;

public class Message {

	private int idMessage;
	private User owner;
	private String message;
	private Date datetime;
	/**
	 * @return the idMessage
	 */
	public int getIdMessage() {
		return idMessage;
	}
	/**
	 * @param idMessage the idMessage to set
	 */
	public void setIdMessage(int idMessage) {
		this.idMessage = idMessage;
	}
	/**
	 * @return the owner
	 */
	public User getOwner() {
		return owner;
	}
	/**
	 * @param owner the owner to set
	 */
	public void setOwner(User owner) {
		this.owner = owner;
	}
	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}
	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	/**
	 * @return the datetime
	 */
	public Date getDatetime() {
		return datetime;
	}
	/**
	 * @param datetime the datetime to set
	 */
	public void setDatetime(Date datetime) {
		this.datetime = datetime;
	}
	
	

}
