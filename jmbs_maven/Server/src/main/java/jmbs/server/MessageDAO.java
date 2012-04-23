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
import java.sql.Statement;
import java.util.ArrayList;
import jmbs.common.Message;
import jmbs.common.Project;
import jmbs.common.User;

public class MessageDAO extends DAO {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7014521799755756037L;

	public MessageDAO(Connection c) {
		super(c);
	}
        
        private Message createMessage(ResultSet rs) throws SQLException{
            UserDAO uDAO = new UserDAO(con);
            ProjectDAO pDAO = new ProjectDAO(con);
            Message m;
            User editUser = null;
            Project p = null;
            User owner = uDAO.getUser(rs.getInt("iduser"));
             
            
            int projectId = rs.getInt("idproject");
            int editUserId = rs.getInt("idedituser");
            if (editUserId != 0) editUser = uDAO.getUser(editUserId);
            if (projectId != 0) p = pDAO.findProject(projectId);
            
            m = new Message(rs.getInt("idmessage"), 
                            owner,
                            rs.getString("content"),
                            rs.getTimestamp("time"),
                            p,
                            editUser,
                            rs.getTimestamp("edittime"));
                            
            return m;                   
        }

	/**
	 * @param id
	 * @return
	 */
	public Message getMessage(int id) {
		set("SELECT * FROM message WHERE idmessage=?;");
		setInt(1, id);
		ResultSet res = executeQuery();
                Message m = null;
		try {
                    m = createMessage(res);
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
			m = new Message(res.getInt("idmessage"), 
                                uDAO.getUser(res.getInt("iduser")), 
                                res.getString("content"), 
                                res.getTimestamp("time"));
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
		set("INSERT INTO message(content, \"time\", iduser) VALUES (?,?,?);",Statement.RETURN_GENERATED_KEYS );
		setString(1, m.getMessage());
		setTimestamp(2, m.getTimestamp());
		setInt(3, m.getOwner().getId());
		executeUpdate();
                try {
                    ResultSet rs = getGeneratedKeys();
                    messageId = rs.getInt("idmessage");
                } catch (SQLException ex) {
                    System.err.println("Error returning generated key.");
                } 
		return messageId;
	}
        
        public int addMessage(Message m, int projectId) {
		int messageId = -1;
		set("INSERT INTO message(content, \"time\", iduser,idproject) VALUES (?,?,?,?);",Statement.RETURN_GENERATED_KEYS);
		setString(1, m.getMessage());
		setTimestamp(2, m.getTimestamp());
		setInt(3, m.getOwner().getId());
                setInt(4,projectId);
                executeUpdate();
                try {
                    ResultSet rs = getGeneratedKeys();
                    messageId = rs.getInt("idmessage");
                } catch (SQLException ex) {
                    System.err.println("Error while creating message.");
                } 
		return messageId;
	}
        
	/**
	 * @param iduser
	 * @param idlastmessage
	 * @return list of messages
	 */
	public ArrayList<Message> getMessages(int iduser, int idlastmessage, int maxMsg) {
		ArrayList<Message> msgList = new ArrayList<Message>();

		set("SELECT idmessage, content, \"time\", iduser "
                        + "FROM message,follows "
                        + "WHERE (((follows.followed = message.iduser "
                        + "AND follows.follower=? "
                        + "AND message.idproject IS NULL) "
                        + "OR message.iduser=?) AND idmessage>?) " 
                        + "GROUP BY idmessage ORDER BY idmessage;");
		setInt(1, iduser);
		setInt(2, iduser);
		setInt(3, idlastmessage);
		ResultSet res = executeQuery();

		UserDAO udao = new UserDAO(con);

		try {
			if (maxMsg > 0) {
				do {
					msgList.add(createMessage(res));
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
        
        public ArrayList<Message> getMessages(int idUser, int idlastmessage, int maxMsg, int idProject) {
		ArrayList<Message> msgList = new ArrayList<Message>();

		set("SELECT message.* "
                        + "FROM message,follows "
                        + "WHERE (((follows.followed = message.iduser "
                        + "AND follows.follower=? "
                        + "AND message.idproject=?) "
                        + "OR message.iduser=?) AND idmessage>?) " 
                        + "GROUP BY idmessage ORDER BY idmessage;");
		setInt(1, idUser);
                setInt(2,idProject);
		setInt(3, idUser);
		setInt(4, idlastmessage);
		ResultSet res = executeQuery();

		UserDAO udao = new UserDAO(con);

		try {
			if (maxMsg > 0) {
				do {
					msgList.add(createMessage(res));
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
