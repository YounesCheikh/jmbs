/**
 * JMBS: Java Micro Blogging System
 *
 * Copyright (C) 2012  
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY.
 * See the GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 * @author Younes CHEIKH http://cyounes.com
 * @author Benjamin Babic http://bbabic.com
 * 
 */

package jmbs.common;

import java.io.Serializable;
import java.sql.Date;

public class Message implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7540214815650154118L;

	private int id;
	private User owner;
	private String message;
	private Date datetime;

	public Message() {

	}

	/**
	 * Creates a message knowing all informations.
	 * 
	 * @param id
	 *            (int) message id given by the db
	 * @param o
	 *            (User) Owner of the message
	 * @param m
	 *            (String) Body of the message
	 * @param dt
	 *            (Date) date of creation
	 * @param t
	 *            (String) title of the message
	 */
	public Message(int id, User o, String m, Date dt) {
		this.id = id;
		this.datetime = dt;
		this.message = m;
		this.owner = o;
	}

	/**
	 * Creates a message knowing all informations.
	 * 
	 * @param o
	 *            (User) Owner of the message
	 * @param m
	 *            (String) Body of the message
	 * @param dt
	 *            (Date) date of creation
	 * @param t
	 *            (String) title of the message
	 */
	public Message(User o, String m, Date dt) {
		this.id = 0;
		this.datetime = dt;
		this.message = m;
		this.owner = o;
	}

	/**
	 * @return the idMessage
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param idMessage
	 *            the idMessage to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the owner
	 */
	public User getOwner() {
		return owner;
	}

	/**
	 * @param owner
	 *            the owner to set
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
	 * @param message
	 *            the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * @return the date & time
	 */
	public Date getDatetime() {
		return datetime;
	}

	/**
	 * @param datetime
	 *            the datetime to set
	 */
	public void setDatetime(Date datetime) {
		this.datetime = datetime;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return ("\nSent by " + owner.getFname() + " at " + datetime + ": " + message + "");
	}
	


}