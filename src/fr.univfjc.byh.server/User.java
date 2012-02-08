package Server;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

//TODO: add a ArrayList of Projects to the user ... 
/**
 * Represents a user.
 */
public class User {

	private String name;
	private String fname;
	private String mail;
	@SuppressWarnings("unused")
	private String pass;
	private int idUser;
	@SuppressWarnings("unused")
	private int accessLevel;
	private boolean exists = true;
	private ArrayList<Project> projects;

	/**
	 * 
	 * Creates a user from given informations. Used to create a non existing
	 * user: A User which does not exists in the db.<br>
	 * This method should be used to create a user before registering him in the
	 * database.
	 * 
	 * @param n
	 *            : user's name
	 * @param f
	 *            : user's fore name
	 * @param m
	 *            : user's mail
	 * @param a
	 *            : user's access level
	 * @param p
	 *            : user's password
	 * @param pr
	 *            :array of projects
	 */
	public User(String n, String f, String m, int a, String p, ArrayList<Project> pr) {
		name = n;
		fname = f;
		mail = m;
		accessLevel = a;
		idUser = 0;
		pass = p;
		exists = false;
		projects=pr;
	}

	public User(String n, String f, String m, int a, String p, int id) {
		name = n;
		fname = f;
		mail = m;
		accessLevel = a;
		idUser = id;
		pass = p;
		exists = true;
		projects=setProjects(idUser);
	}

	/**
	 * Creates a user using his database id userid.
	 * 
	 * @param userid
	 *            database id of the user u want to get.
	 */
	protected User(int userid) {
		Connection con = new Connect().getConnection();
		ResultSet res = Request.querry(con, "SELECT * FROM users WHERE iduser=" + userid + ";");
		try {
			name = res.getString("name");
			fname = res.getString("forename");
			mail = res.getString("email");
			accessLevel = res.getInt("accesslevel");
			idUser = res.getInt("userid");
			pass = res.getString("pass");
			projects=setProjects(idUser);
		} catch (SQLException e) {
			System.out.println("No users for id=" + userid + " !");
		}

		try {
			con.close(); /*
						 * petite subtilité: con semble etre un pointeur vers
						 * l'attribut connection de la classe Connect ce qui
						 * fait que cet attribut est libéré aussi a
						 * l'utilisation de close()
						 */
			res.close();
		} catch (SQLException e) {
			System.out.println("Database acess error !\n Unable to close connection !");
		}
	}

	/**
	 * Creates a user using his name + forename.
	 * 
	 * @param n
	 *            Name of the user
	 * @param fn
	 *            Forename
	 */
	protected User(String n, String fn) {
		Connection con = new Connect().getConnection();
		ResultSet res = Request.querry(con, "SELECT * FROM users WHERE name='" + n + "' AND forename='" + fn + "';");
		try {
			name = res.getString("name");
			fname = res.getString("forename");
			mail = res.getString("email");
			accessLevel = res.getInt("accesslevel");
			idUser = res.getInt("userid");
			pass = res.getString("pass");
			projects=setProjects(idUser);
		} catch (SQLException e) {
			System.out.println("No users for name=" + n + " and forename=" + fn + " !");
		}

		try {
			con.close();
			res.close();
		} catch (SQLException e) {
			System.out.println("Database acess error !\n Unable to close connection !");
		}
	}

	/**
	 * Creates a user using his email.
	 * 
	 * @param em
	 *            email of the user.
	 */
	protected User(String em) {
		Connection con = new Connect().getConnection();
		ResultSet res = Request.querry(con, "SELECT * FROM users WHERE email='" + em + "';");
		try {
			name = res.getString("name");
			fname = res.getString("forename");
			mail = res.getString("email");
			accessLevel = res.getInt("accesslevel");
			idUser = res.getInt("userid");
			pass = res.getString("pass");
			projects=setProjects(idUser);
		} catch (SQLException e) {
			System.out.println("No users for with " + em + " as email adress !");
		}

		try {
			con.close();
			res.close();
		} catch (SQLException e) {
			System.out.println("Database acess error !\n Unable to close connection !");
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Name: " + name + "\nForename: " + fname + "\nEmail: " + mail + "\n";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (idUser != other.idUser)
			return false;
		return true;
	}
	
	// TODO create a method which will get the users teams
	/**
	 * Returns all the projects a user is involved in.
	 * 
	 * @return Array of Projects
	 */
	private static ArrayList<Project> setProjects(int idUser) {
		ArrayList<Project> p = new ArrayList<Project>();
		Connection con = new Connect().getConnection();
		ResultSet res = Request.querry(con, "SELECT participate.idproject,name FROM participate,project WHERE participate.iduser=" + idUser + " AND participate.idproject=project.idproject;");

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
			con.close();
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
	protected static ArrayList<User> listUsers(String uName) {
		ArrayList<User> u = new ArrayList<User>();
		Connection con = new Connect().getConnection();
		ResultSet res = Request.querry(con, "SELECT * FROM users WHERE name='" + uName + "';");
		try {
			if (uName.equals(res.getString("name"))) {
				u.add(new User(res.getString("name"), res.getString("forename"), res.getString("email"), res.getInt("accesslevel"), res.getString("pass"), res.getInt("userid")));
			}
			while (!res.isLast()) {
				res.next();
				if (uName.equals(res.getString("name"))) {
					u.add(new User(res.getString("name"), res.getString("forename"), res.getString("email"), res.getInt("accesslevel"), res.getString("pass"), res.getInt("userid")));
				}
			}
		} catch (SQLException e) {
			System.out.println("Database acess error ! ");
		}

		try {
			con.close();
			res.close();
		} catch (SQLException e) {
			System.out.println("Database acess error !\n Unable to close connection !");
		}

		return u;
	}
	
	protected ArrayList<Project> getProjects()
	{
		return this.projects;
	}
	
	//TODO Check also email address an so on
	/**
	 * Check if the user exists in the database
	 * 
	 * @return boolean true if it exists in db false if not
	 */
	protected boolean exits()
	{
		if(!exists)
		{
			User u=new User(this.name,this.fname);
			this.exists=this.equals(u);
		}
		return this.exists;
	}
}
