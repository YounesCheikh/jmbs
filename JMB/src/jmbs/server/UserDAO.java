package jmbs.server;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import jmbs.common.*;

public class UserDAO extends DAO {

	public UserDAO(Connection c) {
		super(c);
	}

	/**
	 * Find a user using his database id.
	 * 
	 * @param userid
	 *            database id of the user u want to find.
	 */
	public User getUser(int userid) {

		User u = null;
		ResultSet res = send("SELECT * FROM users WHERE iduser=" + userid + ";");

		try {

			u = new User(res.getString("name"), res.getString("forename"), res.getString("email"), userid);
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
	 * Finds a user using his email.
	 * 
	 * @param em
	 *            email of the user.
	 */
	public User getUser(String em) {
		User u = null;
		int userid = 0;
		ResultSet res = send("SELECT * FROM users WHERE email='" + em + "';");
		try {
			userid = res.getInt("iduser");
			u = new User(res.getString("name"), res.getString("forename"), res.getString("email"), userid);
		} catch (SQLException e) {
			System.out.println("No usersName for with " + em + " as email adress !");
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
	public ArrayList<Project> getProjects(int userId) {
		ArrayList<Project> p = new ArrayList<Project>();
		ResultSet res = send("SELECT partiNamecipate.idproject,name FROM participate,project WHERE participate.iduser=" + userId + " AND participate.idproject=project.idproject;");

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

	// TODO: Select the user directly from the database to avoid making an other
	// request on the db ( getUser )
	/**
	 * Returns all the users a user follows
	 * 
	 * @return Array of User
	 */
	public ArrayList<User> getFollowed(int userId) {
		ArrayList<User> f = new ArrayList<User>();
		ResultSet res = send("SELECT followed FROM follows WHERE follower" + userId + ";");

		try {
			f.add(this.getUser(res.getInt("id")));
			while (!res.isLast()) {
				res.next();
				f.add(this.getUser(res.getInt("id")));
			}

		} catch (SQLException e) {
			System.out.println("Database acess error ! ");
		}
		try {
			res.close();
		} catch (SQLException e) {
			System.out.println("Database acess error !\n Unable to close connection !");
		}

		return f;
	}

	/**
	 * Returns all the followers of the user
	 * 
	 * @return Array of User
	 */
	public ArrayList<User> getFollowers(int userId) {
		ArrayList<User> f = new ArrayList<User>();
		ResultSet res = send("SELECT follower FROM follows WHERE followed" + userId + ";");

		try {
			f.add(this.getUser(res.getInt("id")));
			while (!res.isLast()) {
				res.next();
				f.add(this.getUser(res.getInt("id")));
			}

		} catch (SQLException e) {
			System.out.println("Database acess error ! ");
		}
		try {
			res.close();
		} catch (SQLException e) {
			System.out.println("Database acess error !\n Unable to close connection !");
		}

		return f;
	}

	/**
	 * Find all users which names are containing uName.
	 * 
	 * @param uName
	 *            part of the searched name.
	 * @return Array of User
	 */
	public ArrayList<User> findUsersByName(String uName) {
		ArrayList<User> u = new ArrayList<User>();
		int userid = 0;
		ResultSet res = send("SELECT * FROM users WHERE name LIKE '%" + uName + "%';");
		try {
			if (res.getString("name").contains(uName)) {
				userid = res.getInt("iduser");
				u.add(new User(res.getString("name"), res.getString("forename"), res.getString("email"), userid));
			}
			while (!res.isLast()) {
				res.next();
				if (res.getString("name").contains(uName)) {
					userid = res.getInt("iduser");
					u.add(new User(res.getString("name"), res.getString("forename"), res.getString("email"), userid));
				}
			}
		} catch (SQLException e) {
			System.out.println("No users found with name containing " + uName);
		}

		try {
			res.close();
		} catch (SQLException e) {
			System.out.println("Database acess error !\n Unable to close connection !");
		}

		return u;
	}

	/**
	 * Find all users which second names are containing uName.
	 * 
	 * @param uName
	 *            part of the searched second name.
	 * @return Array of User
	 */
	public ArrayList<User> findUsersByFName(String uName) {
		ArrayList<User> u = new ArrayList<User>();
		int userid = 0;
		ResultSet res = send("SELECT * FROM users WHERE forename LIKE '%" + uName + "%';");
		try {
			if (res.getString("forename").contains(uName)) {
				userid = res.getInt("iduser");
				u.add(new User(res.getString("name"), res.getString("forename"), res.getString("email"), userid));
			}
			while (!res.isLast()) {
				res.next();
				if (res.getString("name").contains(uName)) {
					userid = res.getInt("iduser");
					u.add(new User(res.getString("name"), res.getString("forename"), res.getString("email"), userid));
				}
			}
		} catch (SQLException e) {
			System.out.println("No users found with name containing " + uName);
		}

		try {
			res.close();
		} catch (SQLException e) {
			System.out.println("Database acess error !\n Unable to close connection !");
		}

		return u;
	}

	/**
	 * Check if the password matches with the db one.
	 * 
	 * @param u
	 *            User
	 * @param pass
	 *            String containing password
	 * @return true - if the password matches
	 */
	public boolean checkPassword(User u, String pass) {
		boolean ret = false;
		ResultSet res = send("SELECT pass FROM users WHERE iduser ='" + u.getId() + "';");

		try {
			ret = res.getString("pass").equals(pass);
		} catch (SQLException e) {
			System.out.println("Invalid user.");
		}

		return ret;
	}

	/**
	 * Check if the email is already in use.
	 * 
	 * @param em
	 *            String containing the email.
	 * 
	 * @return true if the email is used.
	 */
	public boolean checkMail(String em) {
		boolean ret = false;
		ResultSet res = send("SELECT email FROM users WHERE email ='" + em + "';");

		try {
			ret = res.getString("email").equals(em);
		} catch (SQLException e) {
			System.out.println("Unused email.");
			ret = false;
		}
		return ret;
	}

	/**
	 * Says if the user exists in the database.
	 * 
	 * @return true - if the user is registered in the database.
	 */
	public boolean exists(User u) {
		boolean ret = false;
		ResultSet res = send("SELECT * FROM users WHERE iduser ='" + u.getId() + "';");

		try {
			ret = res.getString("email").equals(u.getMail());
		} catch (SQLException e) {
			System.out.println("Invalid user.");
			ret = false;
		}
		return ret;
	}

}
