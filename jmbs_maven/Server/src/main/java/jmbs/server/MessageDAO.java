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

package jmbs.server;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import jmbs.common.Message;

public class MessageDAO extends DAO {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7014521799755756037L;

	public MessageDAO(Connection c) {
		super(c);
	}

	/**
	 * @param id
	 * @return
	 */
	public Message getMessage(int id) {
		Message m = null;

		set("SELECT * FROM message WHERE idmessage=?;");
		setInt(1, id);
		ResultSet res = executeQuery();

		UserDAO uDAO = new UserDAO(this.getConnection());

		try {
			m = new Message(res.getInt("idmessage"), uDAO.getUser(res.getInt("iduser")), res.getString("content"), res.getDate("time"));
		} catch (SQLException e) {
			System.out.println("No messages for id " + id + " !");
		}
		try {
			res.close();
		} catch (SQLException e) {
			System.err.println("Database acess error !\n Unable to close connection !");
		}

		return m;
	}

	/*
	 * @return null if there a no message by the user u, else return the
	 * message.
	 */
	public Message getLastMessage(int iduser) // TODO javadoc.
	{
		Message m = null;
		set("SELECT * FROM message WHERE iduser=? ORDER BY idmessage DESC;");
		setInt(1, iduser);
		ResultSet res = executeQuery();

		// TODO: test it !
		UserDAO uDAO = new UserDAO(this.getConnection());

		try {
			m = new Message(res.getInt("idmessage"), uDAO.getUser(res.getInt("iduser")), res.getString("content"), res.getDate("time"));
		} catch (SQLException e) {
			System.err.println("No messages by " + uDAO.getUser(iduser).getFname() + " !"); // :)
		}

		try {
			res.close();
		} catch (SQLException e) {
			System.err.println("Database acess error !\n Unable to close connection !");
		}

		return m;

	}

	/**
	 * add message to the DB
	 * 
	 * @param m
	 *            the message
	 * @return true if message m is added successful to the DB
	 */
	public int addMessage(Message m) {
		int messageId = -1;
		set("INSERT INTO message(content, \"time\", iduser) VALUES (?,?,?);");
		setString(1, m.getMessage());
		setDate(2, m.getDatetime());
		setInt(3, m.getOwner().getId());
		boolean res = executeUpdate();

		if (res)
			messageId = getLastMessage(m.getOwner().getId()).getId();
		// id of the last message sent to database by the user.

		return messageId;
	}

	/**
	 * @param iduser
	 * @param idlastmessage
	 * @return list of messages
	 */
	public ArrayList<Message> getMessages(int iduser, int idlastmessage, int maxMsg) {
		Connection con = new Connect().getConnection();
		ArrayList<Message> msgList = new ArrayList<Message>();

		set("SELECT idmessage, content, \"time\", iduser FROM message,follows " +
				"WHERE (((follows.followed = message.iduser AND follows.follower=?) " +
				"OR message.iduser=?) AND idmessage>?) " +
				"GROUP BY idmessage ORDER BY idmessage;");
		setInt(1, iduser);
		setInt(2, iduser);
		setInt(3, idlastmessage);
		ResultSet res = executeQuery();

		UserDAO udao = new UserDAO(con);

		try {
			if (maxMsg > 0) {
				do {
					msgList.add(new Message(res.getInt("idmessage"), udao.getUser(res.getInt("iduser")), res.getString("content"), res.getDate("time")));
				} while (res.next());
				//int i = 0;
				while (msgList.size()>maxMsg) {
					msgList.remove(0);
				}
			}

		} catch (SQLException e) {
			System.out.println("There are no new messages !");
		}
		try {
			res.close();
		} catch (SQLException e) {
			System.err.println("Database acess error !\n Unable to close connection !");
		}

		return msgList;
	}

}
