package jmbs.server;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import jmbs.common.Project;
import jmbs.common.User;

public class UserDAO extends DAO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1371248166710891652L;

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
			System.out.println("No users with id equal to " + userid + " !");
		}

		try {
			res.close();
		} catch (SQLException e) {
			System.err.println("Database acess error !\n Unable to close connection !\n");
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
			System.err.println("No user with " + em + " as email adress !\n");
		}

		try {
			res.close();
		} catch (SQLException e) {
			System.err.println("Database acess error !\n Unable to close connection !\n");
		}
		return u;
	}

	/**
	 * Returns all the projects a user is involved in.
	 * 
	 * @return Array of Projects
	 */
	public ArrayList<Project> getProjects(User u) {
		ArrayList<Project> p = new ArrayList<Project>();
		ResultSet res = send("SELECT partiNamecipate.idproject,name FROM participate,project WHERE participate.iduser=" + u.getId() + " AND participate.idproject=project.idproject;");

		try {
			p.add(new Project(res.getString("name"), res.getInt("idproject")));
			while (!res.isLast()) {
				res.next();
				p.add(new Project(res.getString("name"), res.getInt("idproject")));
			}

		} catch (SQLException e) {
			System.err.println("This user has no projects/n ");
			// TODO determine if this error is due to a wrong user name or a
			// lack of projects.
		}

		try {
			res.close();
		} catch (SQLException e) {
			System.err.println("Database acess error !\n Unable to close connection !\n");
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
			System.err.println("No users found with name containing " + uName);
		}

		try {
			res.close();
		} catch (SQLException e) {
			System.err.println("Database acess error !\n Unable to close connection !");
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
	 * 
	 */
	public boolean checkPassword(User u, String pass) {
		boolean ret = false;
		ResultSet res = send("SELECT pass FROM users WHERE iduser ='" + u.getId() + "';");

		try {
			ret = res.getString("pass").equals(pass);
		} catch (SQLException e) {
			System.err.println("Invalid user.\n");
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
		} catch (SQLException e) { // Unused email
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
		} catch (SQLException e) { // user does not exist
			ret = false;
		}
		return ret;
	}

	/**
	 * Adds a new user in the Database.
	 * 
	 * @param u
	 *            the new user
	 * @param pass
	 *            the hashed password
	 * @return true if editing DB succeeded
	 */
	public boolean addUser(User u, String pass) {
		boolean ret = false;
		String query = "INSERT INTO users(name, forename, email, pass) VALUES ('" + u.getName() + "', '" + u.getFname() + "', '" + u.getMail() + "', '" + pass + "');";
		ResultSet res = send(query);

		if (res != null)
			ret = true;

		return ret;
	}

	/**
	 * Set an user to follow an other user.
	 * 
	 * @param idFollower
	 * @param idFollowed
	 * @return true if DB was editing DB succeeded
	 */
	public boolean follows(int idFollower, int idFollowed) {
		boolean ret = false;
		String query = "INSERT INTO follows(follower, followed ) VALUES (" + idFollower + "," + idFollowed + ");";
		ResultSet res = send(query);

		if (res != null)
			ret = true;

		return ret;
	}

	/**
	 * Set a user to stop following an other user.
	 * 
	 * @param idFollower
	 * @param idFollowed
	 * @return true if DB was editing DB succeeded
	 */
	public boolean unFollow(int idFollower, int idFollowed) {
		boolean ret = false;
		String query = "DELETE FROM follows WHERE follower=" + idFollower + " and followed=" + idFollowed + " ;";
		ResultSet res = send(query);

		if (res != null)
			ret = true;

		return ret;
	}

	/**
	 * Returns all the users a user is following
	 * 
	 * @param user
	 * @return list of users
	 */
	public ArrayList<User> getFollowed(User user) {
		ArrayList<User> u = new ArrayList<User>();
		String query = "SELECT iduser,name,forename,email FROM users,follows WHERE follows.follower = " + user.getId() + " and follows.followed=users.iduser;";
		ResultSet res = send(query);

		try {
			u.add(new User(res.getString("name"), res.getString("forename"), res.getString("email"), res.getInt("iduser")));

			while (!res.isLast()) {
				res.next();
				u.add(new User(res.getString("name"), res.getString("forename"), res.getString("email"), res.getInt("iduser")));
			}
		} catch (SQLException e) {
			System.err.println(user.getFname() + " does not follow anyone yet! \n");
		}

		try {
			res.close();
		} catch (SQLException e) {
			System.err.println("Database acess error !\n Unable to close connection !\n");
		}
		return u;
	}

	/**
	 * Returns all the user followers.
	 * 
	 * @param user
	 * @return ArrayList<User>
	 */
	public ArrayList<User> getFollowers(User user) {
		ArrayList<User> u = new ArrayList<User>();
		String query = "SELECT iduser,name,forename,email FROM users,follows WHERE follows.followed = " + user.getId() + " and follows.follower=users.iduser;";
		ResultSet res = send(query);
		try {
			u.add(new User(res.getString("name"), res.getString("forename"), res.getString("email"), res.getInt("iduser")));

			while (!res.isLast()) {
				res.next();
				u.add(new User(res.getString("name"), res.getString("forename"), res.getString("email"), res.getInt("iduser")));
			}
		} catch (SQLException e) {
			System.err.println(user.getFname() + " is not followed by anyone yet! \n");
		}
		try {
			res.close();
		} catch (SQLException e) {
			System.err.println("Database acess error !\n Unable to close connection !\n");
		}
		return u;
	}
}
