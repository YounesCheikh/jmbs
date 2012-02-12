package Server;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProjectDAO extends DAO{
	
	public ProjectDAO(Connection c) {
		con = c;
	}

	/**
	 * Lists all the users in the project
	 * 
	 * @return Collection of User
	 */
	public ArrayList<User> findUsers(int id) {
		ArrayList<User> u = new ArrayList<User>();
		ResultSet res = send(con,"SELECT participate.name,user.* FROM participate,user WHERE participate.idproject=" + id + " AND user.name=idproject.name;");
		UserDAO uDAO= new UserDAO(con);
		int userid=0;
		try {
			userid=res.getInt("iduser");
			u.add(new User(res.getString("name"), res.getString("forename"),
					res.getString("email"), res.getInt("accesslevel"),
					res.getString("pass"), userid,
					uDAO.findProjects(userid)));
			while (!res.isLast()) {
				res.next();
				userid=res.getInt("iduser");
				u.add(new User(res.getString("name"), res.getString("forename"),
						res.getString("email"), res.getInt("accesslevel"),
						res.getString("pass"), userid,
						uDAO.findProjects(userid)));
			}

		} catch (SQLException e) {
			System.out.println("Unable to find project with id="+id+".");
		}

		try {
			res.close();
		} catch (SQLException e) {
			System.out.println("Database acess error !\n Unable to close connection !");
		}
		return u;
	}
	
	/**
	 * Create a project using his name. If the name exits in the db then it will
	 * return the project. Else it will create a new one.<br>
	 * Note: A project name must be unique.
	 * 
	 * @param n
	 *            name of the project
	 */
	public Project createProject(int id) {
		ResultSet res = send(con,"SELECT * FROM project WHERE idproject=" + id + ";");
		Project p = null;
		
		try {
			p=new Project(res.getString("name"),res.getInt("idproject"));
		} catch (SQLException e) {
			System.out.println("Unable to find project with id="+id+".");
		}

		try {
			res.close();
		} catch (SQLException e) {
			System.out.println("Database acess error !\n Unable to close connection !");
		}
		
		return p;
	}

}
