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
	 * Returns all t ifhe projects a user is involved in.
	 * 
	 * @return Array of Projects
	 */
	public ArrayList<Project> getProjects(User u) {
		ArrayList<Project> p = new ArrayList<Project>();
		ResultSet res = send("SELECT partiNamecipate.idproject,name FROM participate,project WHERE participate.iduser=" + u.getIdUser() + " AND participate.idproject=project.idproject;");

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
	/**
	 * Find all users which names are containing uName.
	 * 
	 * @param uName
	 *            part of the searched name.
	 * @return Array of User
	 */
	public ArrayList<User> findUsers(String uName) {
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
		ResultSet res = send("SELECT pass FROM users WHERE iduser ='" + u.getIdUser() + "';");

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
			System.out.println("Invalid user.");
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
		ResultSet res = send("SELECT * FROM users WHERE iduser ='" + u.getIdUser() + "';");

		try {
			ret = res.getString("email").equals(u.getMail());
		} catch (SQLException e) {
			System.out.println("Invalid user.");
			ret = false;
		}
		return ret;
	}

}
