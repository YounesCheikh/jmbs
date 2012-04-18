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

package jmbs.common;

import java.util.ArrayList;

// TODO: check how it displays on jdoc. Update when the method for transferring objects will be created.
/**
 * Represents a Project
 * 
 * It has a String name, an Integer id and an ArrayList of User called users.\n
 * The array is left at null by default constructor because it would generate chained object creation if not.
 * (e.g) All the projects of a user are required by the client.\n 
 * Let n, the number of projects of that user and m the average number of users in a project. Clearly n << m \n
 * We would have n Projects created (Complexity in terms of database access: n) and n*m Users created which makes
 * a complexity of n*m >> n². And in most case when a user requests the Projects he won't even take a look at the
 * users in it. \n
 * So we choose to set the ArrayList to null by default. You can fill it with external methods like @link {@link jmbs.server.ProjectDAO#getUsers(int) getUsers()} 
 * for the server part.
 * 
 * 
 */
public class Project {

	int id;
	User owner;
	String name;
	int status = STATUS_OPENED; 
	public final static int STATUS_OPENED = 1;
	public final static int STATUS_CLOSED = 0;
	// attributes below are not created by default because they are mostly unused or could trigger unwanted chained db access and object creation
	ArrayList<User> users = new ArrayList<User>();
	
	/**
	 * Creates a project knowing the id and the name.
	 * 
	 * @param n
	 *            name of the project
	 * @param id
	 *            project id
	 */
	public Project(String n, int id, User owner) {
		this.name = n;
		this.id = id;
		this.owner = owner;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the Project id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param idProject the idProject to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the users
	 */
	public ArrayList<User> getUsers() {
		return users;
	}

	/**
	 * @param users the users to set
	 */
	public void setUsers(ArrayList<User> users) {
		this.users = users;
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
	 * 2 projects are not even comparable if they have no id given by the DB. 
	 * If one of the 2 projects have a id equal to 0, result will be false
	 * anyways.
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
		// TODO: more advanced equality check
		if (id != other.id || id == 0 || other.id == 0)
			return false;
		return true;
	}

}
