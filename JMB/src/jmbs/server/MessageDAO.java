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
		setInt(1,id);
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
		setInt(1,iduser);
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
		setString(1,m.getMessage());
		setDate(2,m.getDatetime());
		setInt(3,m.getOwner().getId());
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
	public ArrayList<Message> getMessages(int iduser, int idlastmessage) {
		Connection con = new Connect().getConnection();
		ArrayList<Message> msgList = new ArrayList<Message>();
		
		set("SELECT idmessage, content, \"time\", iduser FROM message,follows WHERE (((follows.followed = message.iduser AND follows.follower=?) OR message.iduser=?) AND idmessage>?) GROUP BY idmessage ORDER BY idmessage;");
		setInt(1,iduser);
		setInt(2,iduser);
		setInt(3,idlastmessage);
		ResultSet res = executeQuery();
		
		UserDAO udao = new UserDAO(con);

		try {
			msgList.add(new Message(res.getInt("idmessage"), udao.getUser(res.getInt("iduser")), res.getString("content"), res.getDate("time")));
			while (!res.isLast()) {
				res.next();
				msgList.add(new Message(res.getInt("idmessage"), udao.getUser(res.getInt("iduser")), res.getString("content"), res.getDate("time")));
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
