package jmbs.server;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import jmbs.common.Project;
import jmbs.common.User;

public class Requests {

	/**
	 * 
	 * @param em
	 *            user's email
	 * @param psw
	 *            user's password
	 * @return the user if pass and email match null if not
	 */
	public User connectUser(String em, String psw) {
		Connection con = new Connect().getConnection();
		UserDAO udao = new UserDAO(con);
		User u = udao.getUser(em);
		boolean b = udao.checkPassword(u, psw);
		try {
			con.close();
		} catch (SQLException e) {
			System.out.println("Database access error !\n Unable to close connection !");
		}

		if (b)
			return u;
		return null;
	}

	/* DECOMENTER CI DESSOUS POUR AJOUTER L'OPTION SUIVANTE: PROGRAMMEUR COTE CLIENT FLEMMARD. */
	/*public ArrayList<User> getFollowers(User u) {
		Connection con = new Connect().getConnection();
		UserDAO udao = new UserDAO(con);
		ArrayList<User> ua = udao.getFollowers(u.getId());

		return ua;

	}

	public ArrayList<User> getFollowed(User u) {
		Connection con = new Connect().getConnection();
		UserDAO udao = new UserDAO(con);
		ArrayList<User> ua = udao.getFollowed(u.getId());

		return ua;
	}

	public ArrayList<Project> getProjects(User u) {
		Connection con = new Connect().getConnection();
		UserDAO udao = new UserDAO(con);
		ArrayList<Project> pa = udao.getProjects(u.getId());

		return pa;
	}

	public ArrayList<User> getUsers(Project p) {
		Connection con = new Connect().getConnection();
		ProjectDAO pdao = new ProjectDAO(con);
		ArrayList<User> ua = pdao.getUsers(p.getId());

		return ua;
	}
	*/

	public ArrayList<User> getFollowers(int userId) {
		Connection con = new Connect().getConnection();
		UserDAO udao = new UserDAO(con);
		ArrayList<User> ua = udao.getFollowers(userId);

		return ua;

	}

	public ArrayList<User> getFollowed(int userId) {
		Connection con = new Connect().getConnection();
		UserDAO udao = new UserDAO(con);
		ArrayList<User> ua = udao.getFollowed(userId);

		return ua;
	}

	public ArrayList<Project> getProjects(int userId) {
		Connection con = new Connect().getConnection();
		UserDAO udao = new UserDAO(con);
		ArrayList<Project> pa = udao.getProjects(userId);

		return pa;
	}

	public ArrayList<User> getUsers(int projectId) {
		Connection con = new Connect().getConnection();
		ProjectDAO pdao = new ProjectDAO(con);
		ArrayList<User> ua = pdao.getUsers(projectId);

		return ua;
	}

	
}
