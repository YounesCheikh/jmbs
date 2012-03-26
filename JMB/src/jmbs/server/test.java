package jmbs.server;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import jmbs.common.*;

public class test {
	public static void main(String[] args) throws SQLException {
		User u;
		Connection con = new Connect().getConnection();
		UserDAO udao = new UserDAO(con);
		
		u = udao.getUser("user5@localhost");
		u.setProjects(udao.getProjects(u));
		
		System.out.println(u);
		System.out.println(u.getProjects());
		
		ArrayList<User> user;
		user = udao.findUsers("user");
		
		System.out.println(udao.checkPassword(u,"user5pass"));
		//System.out.println(user);
		con.close();
		
	}
}
