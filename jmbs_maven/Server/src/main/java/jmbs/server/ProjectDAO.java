/**
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

package jmbs.server;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import jmbs.common.Project;
import jmbs.common.User;



public class ProjectDAO extends DAO {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1449738022340494222L;

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
		int userid = 0;
		
		set("SELECT participate.name,user.* FROM participate,user WHERE participate.idproject=? AND user.name=idproject.name;");
		setInt(1, id);
		ResultSet res = executeQuery();

		try {
			do {
				
				userid = res.getInt("iduser");
				u.add(new User(res.getString("name"), res.getString("forename"), res.getString("email"), userid));
			}while (res.next());

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
		Project p = null;
		
		set("SELECT * FROM project WHERE idproject=? ;");
		setInt(1, id);
		ResultSet res = executeQuery();

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