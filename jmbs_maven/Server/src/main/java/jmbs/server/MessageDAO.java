/*
 * JMBS: Java Micro Blogging System
 *
 * Copyright (C) 2012
 *
 * This program is free software: you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later
 * version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY. See the GNU General Public License for more details. You should
 * have received a copy of the GNU General Public License along with this
 * program. If not, see <http://www.gnu.org/licenses/>.
 *
 * @author Younes CHEIKH http://cyounes.com
 * @author Benjamin Babic http://bbabic.com
 *
 */

/**
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

/**
 * extends @see DAO \n
 * This class represents the messages data access object. It will access the 
 * database given by the connection parameter in his constructor to make the
 * given requests.
 * 
 * @author ben
 */
public class MessageDAO extends DAO {

    /**
     * Creates a new MessageDAO.
     * @param c Connection object describing the connection to the database
     */
    public MessageDAO(Connection c) {
        super(c);
    }
    
    /**
     * Prvivate method to create a Message with a given ResultSet object.\n
     * NOTE: the ResultSet must contain all the fields of the message table, 
     * it is strongly adviced to use "SELECT message.* ..." to set the request
     * which will generate the ResultSet.
     * 
     * @param rs - the ResultSet
     * @return Message - the created message 
     * @throws SQLException - if the ResultSet does not contains all needed fields
     */
    private Message getMessage(ResultSet rs) throws SQLException {
        UserDAO uDAO = new UserDAO(con);
        ProjectDAO pDAO = new ProjectDAO(con);
        Message m;
        // Default settings for project and edit options
        User editUser = null;
        Project p = null;
        User owner = uDAO.getUser(rs.getInt("iduser"));

        int projectId = rs.getInt("idproject");
        int editUserId = rs.getInt("idedituser");
        if (editUserId != 0) {
            editUser = uDAO.getUser(editUserId);
        }
        if (projectId != 0) {
            p = pDAO.getProject(projectId);
        }

        m = new Message(rs.getInt("idmessage"), owner, rs.getString("content"),
                rs.getTimestamp("time"), p, editUser,
                rs.getTimestamp("edittime"));

        return m;
    }

    /**
     * Adds a messages in the database.\n The message given to the method must
     * contain the user which created the message.
     *
     * @param m the message
     * @return the new message's id, -1 if an error occured during creation
     */
    public int addMessage(Message m) {
        int messageId = -1;
        set("INSERT INTO "
                + "message(content, \"time\", iduser) "
                + "VALUES (?,?,?);",
                Statement.RETURN_GENERATED_KEYS);
        setString(1, m.getMessage());
        setTimestamp(2, m.getTimestamp());
        setInt(3, m.getOwner().getId());
        executeUpdate();
        
        try {
            ResultSet rs = getGeneratedKeys();
            messageId = rs.getInt("idmessage");
            close(rs);
        } catch (SQLException e) {
            System.err.println("Error while returning generated key.");
        }
        
        return messageId;
    }

    /**
     * Adds a messages in the database.\n The message given to the method must
     * contain the user wich created the message.\n The message will be set for
     * the given project id.
     *
     * @param m - the message
     * @param projectId - the project id
     * @return the new message's id, -1 if an error occured during creation
     */
    public int addMessage(Message m, int projectId) {
        int messageId = -1;
        //TODO secure it !! 
        set("INSERT INTO "
                + "message(content, \"time\", iduser,idproject) "
                + "VALUES (?,?,?,?);",
                Statement.RETURN_GENERATED_KEYS);
        setString(1, m.getMessage());
        setTimestamp(2, m.getTimestamp());
        setInt(3, m.getOwner().getId());
        setInt(4, projectId);
        executeUpdate();
        
        try {
            ResultSet rs = getGeneratedKeys();
            messageId = rs.getInt("idmessage");
            close(rs);
        } catch (SQLException e) {
            System.err.println("Error while returning generated key.");
        }
        return messageId;
    }

    /**
     * Gets all the messages for a user's timeline.\n
     * It will only get the "numberOfMessages" messages sent after the last message
     * given by the parameter "idLastMessage".
     * 
     * @param idUser - the user who want to get his messages
     * @param idLastMessage - the last message he got
     * @param numberOfMessages - the number of messages to return
     * @return ArrayList of messages
     */
    public ArrayList<Message> getMessages(int idUser, int idLastMessage, int numberOfMessages) {
        ArrayList<Message> msgList = new ArrayList<Message>();
        UserDAO udao = new UserDAO(con);
        
        set("SELECT message.* FROM message,follows "
                + "WHERE (("
                + "(follows.followed = message.iduser AND follows.follower=?) "
                + "OR message.iduser=?) "
                + "AND idmessage>? AND message.idproject IS NULL) "
                + "GROUP BY idmessage ORDER BY idmessage;");
        setInt(1, idUser);
        setInt(2, idUser);
        setInt(3, idLastMessage);
        ResultSet rs = executeQuery();

        try {
            if (numberOfMessages>0) {
                do {
                    msgList.add(getMessage(rs));
                    numberOfMessages--;
                } while (rs.next() && numberOfMessages>0);
            }
            close(rs);
        } catch (SQLException e) {
            System.out.println("There are no new messages !");
        }
        
        return msgList;
    }

     /**
     * Gets all the messages for a user's project timeline.\n
     * It will only get the "numberOfMessages" messages sent after the last 
     * message given by the parameter "idLastMessage".\n 
     * If the given user is not involved in the project it will return an
     * empty ArrayList.     * 
     * 
     * @param idUser - the user who want to get his messages
     * @param idLastMessage - the last message he got
     * @param numberOfMessages - the number of messages to return
     * @param idProject - the project id
     * @return ArrayList of messages
     */
    public ArrayList<Message> getMessages(int idUser, int idLastMessage, int numberOfMessages, int idProject) {
        ArrayList<Message> msgList = new ArrayList<Message>();
        ProjectDAO pdao = new ProjectDAO(con);
        UserDAO udao = new UserDAO(con);
        
        if (pdao.isInvolved(idUser, idProject)){
            set("SELECT message.* "
                    + "FROM message "
                    + "WHERE (idmessage>? AND message.idproject=?) "
                    + "GROUP BY idmessage ORDER BY idmessage;");

            setInt(1, idLastMessage);
            setInt(2, idProject);
            ResultSet rs = executeQuery();

            try {
                if (numberOfMessages>0) {
                    do {
                        msgList.add(getMessage(rs));
                        numberOfMessages--;
                    } while (rs.next() && numberOfMessages>0);
                }
                close(rs);
            } catch (SQLException e) {
                System.out.println("There are no new messages !");
            }
            
        } else {
            System.out.println("User not involved.");
        }
        
        return msgList;
    }
}
