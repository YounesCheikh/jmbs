/*
 * JMBS: Java Micro Blogging System
 *
 * Copyright (C) 2012  
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY.
 * See the GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
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
import java.util.ArrayList;
import jmbs.common.Project;
import jmbs.common.User;

/**
 * 
 * @author
 */
public class UserDAO extends DAO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1371248166710891652L;
        
        public static final int ROOT_ACCESS_LEVEL = 0; 
        public static final int ADMIN_ACCESS_LEVEL = 1; // Sees all projects, can supress, give authorisations, suppress others messages in projects
        public static final int MANAGER_ACCESS_LEVEL = 2; // Create projects, supress owned projects, invite users in project, supress messages in his project
        public static final int CREATE_ACCESS_LEVEL = 2; // for code clarity in functions
        public static final int DEV_ACCESS_LEVEL = 3; // Post in a project he is involved in, Post on timeline,
        public static final int DEFAULT_ACCESS_LEVEL = 10; // See timelines & projects
	
	public UserDAO(Connection c) {
		super(c);
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
		if (!checkMail(u.getMail()))
		{
			set("INSERT INTO users(name, forename, email, pass, picture, authlvl) VALUES (?,?,?,?,?,?);");
			setString(1,u.getName());
			setString(2,u.getFname());
			setString(3,u.getMail());
			setString(4,pass);
			setString(5,u.getPic());
                        setInt(6,DEFAULT_ACCESS_LEVEL);
			return executeUpdate();
		}
		System.err.println("Email already used.");
	
		return false;
	}
	
	public boolean changeMail(int userid, String pass, String mail){
		boolean b = false;
		if (checkPassword(userid, pass)){
			set("UPDATE users SET mail=? WERE iduser = ?");
			setString(1,mail);
			setInt(2,userid);
			b = executeUpdate();
		}
		return b;
	}

	public boolean changePassword(int userid, String oldPass, String newPass){
		boolean b = false;
		if (checkPassword(userid, oldPass)){
			set("UPDATE users SET pass=? WERE iduser = ?");
			setString(1,newPass);
			setInt(2,userid);
			b = executeUpdate();

		}
		
		return b;
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
		return checkPassword(u.getId(), pass);
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
	public boolean checkPassword(int iduser, String pass) {
		boolean ret = false;
		
		set("SELECT pass FROM users WHERE iduser =?;");
		setInt(1,iduser);
		ResultSet res = executeQuery();
	
		try {
			ret = res.getString("pass").equals(pass);
			// TODO add connection log.
		} catch (SQLException e) {
			System.err.println("Invalid User.\n");
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
		boolean ret = true;
		
		set("SELECT email FROM users WHERE email =?;");
		setString(1,em);
		ResultSet res = executeQuery();
	
		try {
			res.getString("email");
		} catch (SQLException e) { // Unused email
			ret = false;
		}
	
		return ret;
	}

	//TODO: check if not useless.
	/**
	 * Says if the user exists in the database.
	 * 
	 * @return true - if the user is registered in the database.
	 */
	public boolean exists(User u) {
		boolean ret;
		
		set("SELECT * FROM users WHERE iduser=?;");
		setInt(1,u.getId());
		ResultSet res = executeQuery();
	
		try {
			ret = res.getString("email").equals(u.getMail());
		} catch (SQLException e) { // user does not exist
                    ret = false;
		}
	
		return ret;
	}

	public boolean exists(int iduser){
		set("SELECT email FROM users WHERE iduser=?");
		setInt(1,iduser);
		ResultSet res = executeQuery();
		boolean ret = false;
		
		try {
			res.getString("email");
			ret = true;
		} catch (SQLException e) { // user does not exist we can do something here if we really want to waste time ...
			
		}
		
		return ret;
	}

	// TODO add an option to list Users by second names...
	// TODO don't consider upper case...
	/**
	 * Find all users which names are containing uName.
	 * 
	 * @param uName
	 *            part of the searched name.
	 * @return Array of User
	 */
	public ArrayList<User> findUsers(String uName, int param) {
		ArrayList<User> u = new ArrayList<User>();
		int userid;
		String errorMsg = new String();
		
		if (param == BY_NAME) 
		{
			set("SELECT * FROM users WHERE name LIKE ?;");
			setString(1,"%"+uName+"%");
			errorMsg="No users found with name containing  \"" + uName +"\"";
		}
		if (param == BY_FORNAME)
		{
			set("SELECT * FROM users WHERE forename LIKE ?;");
			setString(1,"%"+uName+"%");
			errorMsg="No users found with second name containing  \"" + uName +"\"";
		}
		if (param == BY_BOTH)
		{
			set("SELECT * FROM users WHERE name LIKE ? OR forename LIKE ?;");
			setString(1,"%"+uName+"%");
			setString(2,"%"+uName+"%");
			errorMsg="No users found with name or second name containing  \"" + uName +"\"";
		}
		ResultSet res = executeQuery();
	
		try {
			 do {
					userid = res.getInt("iduser");
					u.add(new User(res.getString("name"), res.getString("forename"), res.getString("email"), userid, res.getString("picture")));
			} while (res.next());
		} catch (SQLException e) {
			System.err.println(errorMsg);
		}
	
		try {
			res.close();
		} catch (SQLException e) {
			System.err.println("Database acess error !\n Unable to close connection !");
		}
	
		return u;
	}

	/**
	 * Set an user to follow an other user.
	 * 
	 * @param idFollower
	 * @param idFollowed
	 * @return true if DB was editing DB succeeded
	 */
	public boolean follows(int idFollower, int idFollowed) {
		if (this.exists(idFollower) && this.exists(idFollowed)){
			set("INSERT INTO follows(follower, followed) VALUES (?,?);");
			setInt(1,idFollower);
			setInt(2,idFollowed);
			boolean res = executeUpdate();
			
			return (res);
		} else return false	;
	}

	public int getAccessLevel (int iduser, int idproject){
		int ret = -1;
		if (this.exists(iduser) && (new ProjectDAO(super.con)).exists(idproject)) {
			set("SELECT authlvl FROM participate WHERE iduser=? AND idproject=?");
			setInt(1,iduser);
			setInt(2,idproject);
			ResultSet res = executeQuery();
		
			try {
				ret = res.getInt("authlvl");
			} catch (SQLException e) {
				System.err.println("Unexcepted error !");
			}
		}
		
		return ret;
	}

	/**
	 * Returns all the users a user is following
	 * 
	 * @param user - the user 
	 * @return list of users
	 */
	public ArrayList<User> getFollowed(User user) {
		ArrayList<User> u = new ArrayList<User>();
		
		set("SELECT * FROM users,follows WHERE follows.follower =? and follows.followed=users.iduser;");
		setInt(1,user.getId());
		ResultSet res = executeQuery();
	
		try {
			do {
				u.add(new User(res.getString("name"), res.getString("forename"), res.getString("email"), res.getInt("iduser"), res.getString("picture")));
			} while (res.next());
		} catch (SQLException e) {
			System.err.println(user.getFname() + " does not follow anyone yet!");
		}
	
		try {
			res.close();
		} catch (SQLException e) {
			System.err.println("Database acess error !\n Unable to close connection !");
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
		
		set("SELECT iduser,name,forename,email,picture FROM users,follows WHERE follows.followed =? and follows.follower=users.iduser;");
		setInt(1,user.getId());
		ResultSet res = executeQuery();
		
		try {
			do {
				u.add(new User(res.getString("name"), res.getString("forename"), res.getString("email"), res.getInt("iduser"), res.getString("picture")));
			}while (res.next());
		} catch (SQLException e) {
			System.err.println(user.getFname() + " is not followed by anyone yet!");
		}
		try {
			res.close();
		} catch (SQLException e) {
			System.err.println("Database acess error !\n Unable to close connection !");
		}
		
		return u;
	}

	/**
	 * Returns all the projects a user is involved in.
	 * 
	 * @return Array of Projects
	 */
	public ArrayList<Project> getProjects(int userid) {
		ArrayList<Project> p = new ArrayList<Project>();
		
		set("SELECT projects.idowner,projects.status,participate.idproject,name FROM participate,projects WHERE participate.iduser=? AND participate.idproject=projects.idproject;");
		setInt(1,userid);
		ResultSet res = executeQuery();
	
		try {
			do {	
				p.add(new Project(res.getString("name"), res.getInt("idproject"), this.getUser(res.getInt("idowner")),res.getInt("status")));
			} while (res.next());
	
		} catch (SQLException e) {
			System.err.println("This user has no projects/n ");
			// TODO determine if this error is due to a wrong user name or a
			// lack of projects.
		}
	
		try {
			res.close();
		} catch (SQLException e) {
			System.err.println("Database acess error !\n Unable to close connection !");
		}
		
		
		return p;
	}

	/**
	 * Returns all the projects a user is involved in.
	 * 
	 * @return Array of Projects
	 */
	public ArrayList<Project> getProjects(User u) {
		return getProjects(u.getId());
	}
	
	public ArrayList<Project> getOwnedProject(User u){
		return getOwnedProject(u.getId());
	}
	
	public ArrayList<Project> getOwnedProject(int userid){
		set("SELECT * FROM projects WHERE idowner=?;");
		setInt(1,userid);
		ResultSet rs = executeQuery();
		
		ArrayList<Project> pj = new ArrayList<Project>();
		
		try{
			do{
				pj.add(new Project(rs.getString("name"), rs.getInt("idproject"),getUser(userid),rs.getInt("status")));
			}while(rs.next());
			
		}catch(SQLException e){
			
		}
		return pj;
	}

	/**
	 * Find a user using his database id.
	 * 
	 * @param userid
	 *            database id of the user u want to find.
	 */
	public User getUser(int userid) {

		User u = null;
		set("SELECT * FROM users WHERE iduser=?;");
		setInt(1, userid);
		ResultSet res = executeQuery();

		try {
			u = new User(res.getString("name"), res.getString("forename"), res.getString("email"), userid, res.getString("picture"));
		} catch (SQLException e) {
			System.out.println("No users with id equal to " + userid + " !");
		}

		try {
			res.close();
		} catch (SQLException e) {
			System.err.println("Database acess error !\n Unable to close connection !");
		}
		return u;
	}

	/**
	 * Finds a user using his email.
	 * 
	 * @param em
	 *            email of the user.
	 * @throws SQLException 
	 */
	public User getUser(String em) {
		User u = null;
		int userid;
		ResultSet res;
		
		set("SELECT * FROM users WHERE email=?;");
		setString(1,em);
		res = executeQuery();
		
		try {
			userid = res.getInt("iduser");
			u = new User(res.getString("name"), res.getString("forename"), res.getString("email"), userid, res.getString("picture"));
		} catch (SQLException e) {
			System.err.println("No user with " + em + " as email adress !\n");
		}

		try {
			res.close();
		} catch (SQLException e) {
			System.err.println("Database acess error !\n Unable to close connection !");
		}
		
		return u;
	}

        /**
         * Grans the developper status to a user
         * @param adminId - the user who grants the developper status
         * @param userId - the user who will be granted the status
         * @return
         * @throws SecurityException if the user who try to grant status has a too low authorisation level
         */
        public boolean grantDevStatus(int adminId, int userId) throws SecurityException{
            boolean res;
            if (new SecurityDAO(con).isAccessLevelSufficiant(adminId,ADMIN_ACCESS_LEVEL)){
                set("UPDATE users SET authlvl=? WHERE iduser=?;");
                setInt(1,DEV_ACCESS_LEVEL);
                setInt(2,userId);
                res = executeUpdate();
            } else {
                throw new SecurityException("Your access level is not high enough to do that operation.");
            }
            return res;
        }       
        
	public boolean participate (int iduser, int idproject, int auth){
		if (this.exists(iduser) && (new ProjectDAO(super.con)).exists(idproject)){ //if the project and the user exists.
			set("INSERT INTO participate (iduser,idproject,authlvl) VALUES (?,?,?);");
			setInt(1,iduser);
			setInt(2,idproject);
			setInt(3,auth);
			boolean res = executeUpdate();
		
			return res;
		} else return false;
	}

	public boolean participate (int iduser, int idproject){
		return this.participate(iduser, idproject,User.DEFAULT_AUTHORISATION_LEVEL);
	}

	/**
	 * Set a user to stop following an other user.
	 * 
	 * @param idFollower
	 * @param idFollowed
	 * @return true if DB was editing DB succeeded
	 */
	public boolean unFollow(int idFollower, int idFollowed) {
			set("DELETE FROM follows WHERE follower=? and followed=?;");
			setInt(1,idFollower);
			setInt(2,idFollowed);
			boolean res = executeUpdate();
			
			return (res);
	}

	public boolean unParticipate (int iduser, int idproject){
		set("DELETE FROM participate WHERE iduser=? and idproject=?;");
		setInt(1,iduser);
		setInt(2,idproject);
		boolean res = executeUpdate();
		
		return (res);
	}
}
