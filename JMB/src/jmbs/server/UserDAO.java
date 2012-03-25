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

			u = new User(res.getString("name"), res.getString("forename"),
					res.getString("email"), userid);
		} catch (SQLException e) {
			System.out.println("No users for id=" + userid + " !");
		}

		try {
			res.close();
		} catch (SQLException e) {
			System.out
					.println("Database acess error !\n Unable to close connection !");
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
			u = new User(res.getString("name"), res.getString("forename"),
					res.getString("email"), userid);
		} catch (SQLException e) {
			System.out.println("No usersName for with " + em
					+ " as email adress !");
		}

		try {
			res.close();
		} catch (SQLException e) {
			System.out
					.println("Database acess error !\n Unable to close connection !");
		}
		return u;
	}

	// TODO create a method which will get the users teams
	/**
	 * Returns all the projects a user is involved in.
	 * 
	 * @return Array of Projects
	 */
	public ArrayList<Project> getProjects(User u) {
		ArrayList<Project> p = new ArrayList<Project>();
		ResultSet res = send("SELECT partiNamecipate.idproject,name FROM participate,project WHERE participate.iduser="
				+ u.getId() + " AND participate.idproject=project.idproject;");

		try {
			p.add(new Project(res.getString("name"), res.getInt("idproject")));
			while (!res.isLast()) {
				res.next();
				p.add(new Project(res.getString("name"), res
						.getInt("idproject")));
			}

		} catch (SQLException e) {
			System.out.println("haha Database acess error ! ");
		}

		try {
			res.close();
		} catch (SQLException e) {
			System.out
					.println("Database acess error !\n Unable to close connection !");
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
		ResultSet res = send("SELECT * FROM users WHERE name LIKE '%" + uName
				+ "%';");
		try {
			if (res.getString("name").contains(uName)) {
				userid = res.getInt("iduser");
				u.add(new User(res.getString("name"),
						res.getString("forename"), res.getString("email"),
						userid));
			}
			while (!res.isLast()) {
				res.next();
				if (res.getString("name").contains(uName)) {
					userid = res.getInt("iduser");
					u.add(new User(res.getString("name"), res
							.getString("forename"), res.getString("email"),
							userid));
				}
			}
		} catch (SQLException e) {
			System.out.println("No users found with name containing " + uName);
		}

		try {
			res.close();
		} catch (SQLException e) {
			System.out
					.println("Database acess error !\n Unable to close connection !");
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
		ResultSet res = send("SELECT pass FROM users WHERE iduser ='"
				+ u.getId() + "';");

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
		ResultSet res = send("SELECT email FROM users WHERE email ='" + em
				+ "';");

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
		ResultSet res = send("SELECT * FROM users WHERE iduser ='" + u.getId()
				+ "';");

		try {
			ret = res.getString("email").equals(u.getMail());
		} catch (SQLException e) {
			System.out.println("Invalid user.");
			ret = false;
		}
		return ret;
	}
	
	/**
	 * add new user in the DB
	 * @param u the new user
	 * @param pass the hashed password
	 * @return true if editing DB successed
	 */
	public boolean addUser(User u, String pass) {
		boolean retVal = false;
		String query=new String();
		query+="INSERT INTO users(name, forename, email, pass ) ";
	    query+="VALUES ('"+u.getName()+"', '"+u.getFname()+"', '"+u.getMail()+"', '"+pass+"');"; 
	    try {
	    	send(query);
	    	retVal = true;
	    } catch (Exception e) {
	    	System.out.println("Error while adding User to DB!");
	    	return false;
	    }
	    
		return retVal;
	}
	
	/**
	 * user follows other user in DB
	 * @param idFollower
	 * @param idFollowed
	 * @return true if DB was editing DB successed
	 */
	public boolean follow(int idFollower, int idFollowed) {
		boolean retVal = false;
		String query=new String();
		/*
		 * INSERT INTO follows(
            follower, followed) VALUES (?, ?);
		 * 
		 */
		query+="INSERT INTO follows(follower, followed ) ";
	    query+="VALUES ("+idFollower+","+idFollowed+");"; 
	    try {
	    	send(query);
	    	retVal = true;
	    } catch (Exception e) {
	    	System.out.println("Error while adding User to DB!");
	    	return false;
	    }
	    
		return retVal;
	}
	
	/**
	 * user unfollows other user from DB
	 * @param idFollower
	 * @param idFollowed
	 * @return true if DB was editing DB successed
	 */
	public boolean unFollow(int idFollower, int idFollowed) {
		boolean retVal = false;
		String query=new String();
		/*
		 DELETE FROM follows
 			WHERE follower=1 and followed=2;
		 */
		query+="DELETE FROM follows ";
	    query+="WHERE follower="+idFollower+" and followed="+idFollowed+" ;"; 
	    try {
	    	send(query);
	    	retVal = true;
	    } catch (Exception e) {
	    	System.out.println("Error while adding User to DB!");
	    	return false;
	    }
		return retVal;
	}
	
	/**
	 * search on the DB for all users who the user follows, and set his follows list
	 * @param user
	 * @return list of users
	 */
	public ArrayList<User> setFollowingList(User user) {
		ArrayList<User> u = new ArrayList<User>();
		String query=new String();
		query+="SELECT iduser,name,forename,email FROM users,follows WHERE ";
		query+="follows.follower = "+user.getId()+" and follows.followed=users.iduser;";
		ResultSet res = send(query);
		try {
			u.add(new User(res.getString("name"),
					res.getString("forename"), res.getString("email"),
					res.getInt("iduser")));
			
			while (!res.isLast()) {
				res.next();
				u.add(new User(res.getString("name"),
						res.getString("forename"), res.getString("email"),
						res.getInt("iduser")));
				}
			user.setFollows(u);
			System.out.println("<html><b>follows "+u.size()+" persons!:</b></html>");
			for(User uu: u) {
				System.out.println(uu.getName());
			}
			
		} catch (SQLException e) {
			System.out.println("this user doesn't start following yet! ");
		}

		try {
			res.close();
		} catch (SQLException e) {
			System.out
					.println("Database acess error !\n Unable to close connection !");
		}
		return u;
	}
	
	/**
	 * this methode search on the DB for all users who follow the user entred as paramter
	 * @param user
	 * @return ArrayList<User>
	 */
	public ArrayList<User> getFollowersList(User user) {
		ArrayList<User> u = new ArrayList<User>();
		String query=new String();
		query+="SELECT iduser,name,forename,email FROM users,follows WHERE ";
		query+="follows.followed = "+user.getId()+" and follows.follower=users.iduser;";
		ResultSet res = send(query);
		try {
			u.add(new User(res.getString("name"),
					res.getString("forename"), res.getString("email"),
					res.getInt("iduser")));
			
			while (!res.isLast()) {
				res.next();
				u.add(new User(res.getString("name"),
						res.getString("forename"), res.getString("email"),
						res.getInt("iduser")));
				}
			System.out.println("has "+u.size()+" followers!");
		} catch (SQLException e) {
			System.out.println("this user doesn't start following yet! ");
		}
		try {
			res.close();
		} catch (SQLException e) {
			System.out
					.println("Database acess error !\n Unable to close connection !");
		}
		return u;
	}
}
