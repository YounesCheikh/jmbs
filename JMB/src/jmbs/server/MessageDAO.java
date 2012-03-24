package jmbs.server;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import jmbs.common.Message;



public class MessageDAO extends DAO{

	public MessageDAO(Connection c) {
		super(c);
	}
	
	public Message getMessage(int id){
		Message m = null;
		ResultSet res = send("SELECT * FROM message WHERE idmessage=" + id + ";");
		UserDAO uDAO = new UserDAO(this.getConnection());
		
		try {

			m = new Message(res.getInt("idmessage"), uDAO.getUser(res.getInt("iduser")), res.getString("title"), res.getString("content"),res.getDate("time"));
		} catch (SQLException e) {
			System.out.println("No messages for id=" + id + " !");
		}
		try {
			res.close();
		} catch (SQLException e) {
			System.out.println("Database acess error !\n Unable to close connection !");
		}
		
		return m;
	}
	
	
	public boolean addMessage(Message m) {
		boolean retVal = false;
		String query=new String();
		query+="INSERT INTO message(content, \"time\", iduser) ";
	    query+="VALUES ('"+m.getMessage()+"', '"+m.getDatetime()+"', "+m.getOwner().getId()+");";
	    try {
	    	send(query);
	    	retVal = true;
	    } catch (Exception e) {
	    	System.out.println("Error while adding msg to DB!");
	    	return false;
	    }
	    
		return retVal;
	}

}
