package Server;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProjectDAO extends DAO {

	public ProjectDAO(Connection c) {
		super(c);
	}

	/**
	 * Lists all the users in the project without filling their Project array
	 * 
	 * @return Collection of User
	 */
	public ArrayList<User> getUsers(int id) {
		ArrayList<User> u = new ArrayList<User>();
		ResultSet res = send("SELECT participate.name,user.* FROM participate,user WHERE participate.idproject=" + id + " AND user.name=idproject.name;");
		int userid = 0;
		try {
			userid = res.getInt("iduser");
			u.add(new User(res.getString("name"), res.getString("forename"), res.getString("email"), userid));
			while (!res.isLast()) {
				res.next();
				userid = res.getInt("iduser");
				u.add(new User(res.getString("name"), res.getString("forename"), res.getString("email"), userid));
			}

		} catch (SQLException e) {
			System.out.println("Unable to find project with id=" + id + ".");
		}

		try {
			res.close();
		} catch (SQLException e) {
			System.out.println("Database acess error !\n Unable to close connection !");
		}
		return u;
	}

	/**
	 * find a project using his name. If the name exits in the db then it will
	 * return the project. Else it will create a new one.<br>
	 * Note: A project name must be unique.
	 * 
	 * @param id
	 *            id of the project
	 */
	public Project findProject(int id) {
		ResultSet res = send("SELECT * FROM project WHERE idproject=" + id + ";");
		Project p = null;

		try {
			p = new Project(res.getString("name"), res.getInt("idproject"));
		} catch (SQLException e) {
			System.out.println("Unable to find project with id=" + id + ".");
		}

		try {
			res.close();
		} catch (SQLException e) {
			System.out.println("Database acess error !\n Unable to close connection !");
		}

		return p;
	}

}
