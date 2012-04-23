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
import java.sql.Timestamp;

public class Message implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7540214815650154118L;

	private int id;
	private User owner;
	private String message;
	private Timestamp t;
        private Project project;
        private User editUser = null;
        private Timestamp editTime = null;

	public Message() {

	}

	/**
	 * Creates a message knowing all informations.
	 * 
	 * @param idmessage
	 *            (int) message id given by the db
	 * @param owner
	 *            (User) Owner of the message
	 * @param content
	 *            (String) Body of the message
	 * @param dt
	 *            (Date) date of creation
	 * @param t
	 *            (String) title of the message
	 */
	public Message(int idmessage, User owner, String content, Timestamp createDate, Project p, User editUser, Timestamp editTime) {
		this.id = idmessage;
		this.t = createDate;
		this.message = content;
		this.owner = owner;
                this.project=p;
                this.editUser=editUser;
                this.editTime=editTime;
                
	}
        
        public Message(int id, User o, String m, Timestamp dt) {
		this.id = id;
		this.t = dt;
		this.message = m;
		this.owner = o;
                this.project=null;
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
	public Message(User o, String m, Timestamp dt) {
		this.id = 0;
		this.t = dt;
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
	public Timestamp getTimestamp() {
		return t;
	}

	/**
	 * @param datetime
	 *            the datetime to set
	 */
	public void setTimestamp(Timestamp t) {
		this.t = t;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return ("\nSent by " + owner.getFname() + " at " + t + ": " + message + "");
	}
	


}
