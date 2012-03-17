package jmbs.server;

import java.sql.Connection;
import java.sql.SQLException;

import jmbs.common.User;

public class Requests {

	
	/**
	 * 
	 * @param em - user's email
	 * @param psw - user's password
	 * @return the user if pass and email match null if not
	 */
	public User connectUser(String em, String psw)
	{
		Connection con = new Connect().getConnection();
		UserDAO udao = new UserDAO(con);
		User u = udao.getUser(em);
		boolean b = udao.checkPassword(u, psw);
		try {
			con.close();
		} catch (SQLException e) {
			System.out.println("Database access error !\n Unable to close connection !");
		}
		
		if(b) return u;
		return null;
	}
}
