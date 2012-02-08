package Server;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Represents a Project.
 * 
 */
public class Project {

	String name;
	int idProject = 0;
	boolean exists = true;

	/**
	 * @param n
	 *            name of the project
	 * @param id	
	 *            project id
	 */
	public Project(String n, int id) {
		name=n;
		idProject=id;
	}

	/**
	 * Create a project using his name. If the name exits in the db then it will
	 * return the project. Else it will create a new one.<br>
	 * Note: A project name must be unique.
	 * 
	 * @param n
	 *            name of the project
	 */
	public Project(String n) {
		Connection con = new Connect().getConnection();
		ResultSet res = Request.querry(con, "SELECT * FROM project WHERE name=" + n + ";");

		try {
			name = res.getString("name");
			idProject = res.getInt("idproject");

		} catch (SQLException e) {
			exists = false;
			name = n;
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

	public String toString() {
		return this.name;
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
		Project other = (Project) obj;
		if (idProject != other.idProject)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	/**
	 * Lists all the users in the project
	 * 
	 * @return Collection of User
	 */
	public ArrayList<User> listUsers() {
		ArrayList<User> u = new ArrayList<User>();
		Connection con = new Connect().getConnection();
		ResultSet res = Request.querry(con, "SELECT participate.name,user.forename FROM participate,user WHERE participate.idproject=" + this.idProject + " AND user.name=idproject.name;");

		try {
			u.add(new User(res.getString("name"), res.getString("forename")));
			while (!res.isLast()) {
				res.next();
				u.add(new User(res.getString("name"), res.getString("forename")));
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
		return u;
	}

}
