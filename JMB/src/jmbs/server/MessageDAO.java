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
		ResultSet res = send("SELECT * FROM message WHERE idmessage=" + id
				+ ";");
		UserDAO uDAO = new UserDAO(this.getConnection());

		try {

			m = new Message(res.getInt("idmessage"), uDAO.getUser(res
					.getInt("iduser")), res.getString("title"),
					res.getString("content"), res.getDate("time"));
		} catch (SQLException e) {
			System.out.println("No messages for id=" + id + " !");
		}
		try {
			res.close();
		} catch (SQLException e) {
			System.out
					.println("Database acess error !\n Unable to close connection !");
		}

		return m;
	}

	/**
	 * add message to the DB
	 * @param m the message
	 * @return true if message m is added succesful to the DB
	 */
	public boolean addMessage(Message m) {
		boolean retVal = false;
		String query = new String();
		query += "INSERT INTO message(content, \"time\", iduser) ";
		query += "VALUES ('" + m.getMessage() + "', '" + m.getDatetime()
				+ "', " + m.getOwner().getId() + ");";
		try {
			send(query);
			retVal = true;
		} catch (Exception e) {
			System.out.println("Error while adding msg to DB!");
			return false;
		}

		return retVal;
	}

	/**
	 * @param iduser
	 * @param idlastmessage
	 * @return list of messages
	 */
	public ArrayList<Message> getLatestsMessages(int iduser, int idlastmessage) {
		ArrayList<Message> msgList = new ArrayList<Message>();

		/*
		 * SELECT idmessage, content, "time", iduser FROM message,follows where
		 * (((follows.followed = iduser and follows.follower = 8) OR iduser=8)
		 * and idmessage > 9) group by idmessage order by idmessage ;
		 */
		String query = new String();
		query += "SELECT idmessage, content, \"time\", iduser ";
		query += "FROM message,follows where (((follows.followed = iduser ";
		query += "and follows.follower = " + iduser + ") OR iduser=" + iduser
				+ ") and idmessage>" + idlastmessage
				+ ") group by idmessage order by idmessage;";

		ResultSet res = send(query);
		Connection con = new Connect().getConnection();
		UserDAO udao = new UserDAO(con);
		try {
			msgList.add(new Message(res.getInt("idmessage"), udao.getUser(res
					.getInt("iduser")), "", res.getString("content"), res
					.getDate("time")));
			while (!res.isLast()) {
				res.next();
				msgList.add(new Message(res.getInt("idmessage"), udao
						.getUser(res.getInt("iduser")), "", res
						.getString("content"), res.getDate("time")));
			}
		} catch (SQLException e) {
			System.out.println("there is no new messages!");
		}
		try {
			res.close();
		} catch (SQLException e) {
			System.out
					.println("Database acess error !\n Unable to close connection !");
		}
		return msgList;
	}

}
