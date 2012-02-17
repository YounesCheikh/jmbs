package Server;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserDAO extends DAO {

	public UserDAO(Connection c) {
		super(c);
	}

	/**
	 * Find a user using his database id userid.
	 * 
	 * @param userid
	 *            database id of the user u want to get.
	 */
	protected User findUser(int userid) {

		User u = null;
		ResultSet res = send("SELECT * FROM users WHERE iduser=" + userid + ";");

		try {

			u = new User(res.getString("name"), res.getString("forename"), res.getString("email"), res.getInt("accesslevel"), res.getString("pass"), userid, getProjects(userid));
		} catch (SQLException e) {
			System.out.println("No users for id=" + userid + " !");
		}

		try {
			res.close();
		} catch (SQLException e) {
			System.out.println("Database acess error !\n Unable to close connection !");
		}
		return u;
	}

	/**
	 * Find a user using his name + forename.
	 * 
	 * @param n
	 *            Name of the user
	 * @param fn
	 *            Forename
	 */
	protected User findUser(String n, String fn) {
		User u = null;
		int userid = 0;
		ResultSet res = send("SELECT * FROM users WHERE name='" + n + "' AND forename='" + fn + "';");

		try {
			userid = res.getInt("iduser");
			u = new User(res.getString("name"), res.getString("forename"), res.getString("email"), res.getInt("accesslevel"), res.getString("pass"), userid, getProjects(userid));
		} catch (SQLException e) {
			System.out.println("No users for name=" + n + " and forename=" + fn + " !");
		}

		try {
			res.close();
		} catch (SQLException e) {
			System.out.println("Database acess error !\n Unable to close connection !");
		}
		return u;
	}

	/**
	 * Finds a user using his email.
	 * 
	 * @param em
	 *            email of the user.
	 */
	protected User findUser(String em) {
		User u = null;
		int userid = 0;
		ResultSet res = send("SELECT * FROM users WHERE email='" + em + "';");
		try {
			userid = res.getInt("iduser");
			u = new User(res.getString("name"), res.getString("forename"), res.getString("email"), res.getInt("accesslevel"), res.getString("pass"), userid, getProjects(userid));
		} catch (SQLException e) {
			System.out.println("No users for with " + em + " as email adress !");
		}

		try {
			res.close();
		} catch (SQLException e) {
			System.out.println("Database acess error !\n Unable to close connection !");
		}
		return u;
	}

	// TODO create a method which will get the users teams
	/**
	 * Returns all the projects a user is involved in.
	 * 
	 * @return Array of Projects
	 */
	public ArrayList<Project> getProjects(int idUser) {
		ArrayList<Project> p = new ArrayList<Project>();
		ResultSet res = send("SELECT participate.idproject,name FROM participate,project WHERE participate.iduser=" + idUser + " AND participate.idproject=project.idproject;");

		try {
			p.add(new Project(res.getString("name"), res.getInt("idproject")));
			while (!res.isLast()) {
				res.next();
				p.add(new Project(res.getString("name"), res.getInt("idproject")));
			}

		} catch (SQLException e) {
			System.out.println("haha Database acess error ! ");
		}

		try {
			res.close();
		} catch (SQLException e) {
			System.out.println("Database acess error !\n Unable to close connection !");
		}

		return p;
	}

	// TODO add an option to list Users by second names...
	// TODO change the method to be string research compatible/..
	/**
	 * Find all users having a specified name.
	 * 
	 * @param uName
	 *            name
	 * @return Array of User
	 */
	protected ArrayList<User> findUsers(String uName) {
		ArrayList<User> u = new ArrayList<User>();
		int userid = 0;
		ResultSet res = send("SELECT * FROM users WHERE name='" + uName + "';");
		try {
			if (uName.equals(res.getString("name"))) {
				userid = res.getInt("iduser");
				u.add(new User(res.getString("name"), res.getString("forename"), res.getString("email"), res.getInt("accesslevel"), res.getString("pass"), userid, getProjects(userid)));
			}
			while (!res.isLast()) {
				res.next();
				if (uName.equals(res.getString("name"))) {
					userid = res.getInt("iduser");
					u.add(new User(res.getString("name"), res.getString("forename"), res.getString("email"), res.getInt("accesslevel"), res.getString("pass"), userid, getProjects(userid)));
				}
			}
		} catch (SQLException e) {
			System.out.println("Database acess error ! ");
		}

		try {
			res.close();
		} catch (SQLException e) {
			System.out.println("Database acess error !\n Unable to close connection !");
		}

		return u;
	}

}
