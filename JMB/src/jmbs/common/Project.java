package jmbs.common;

import java.util.ArrayList;

/**
 * Represents a Project.
 * 
 */
public class Project {

	String name;
	int idProject;
	ArrayList<User> users;

	/**
	 * Creates a project knowing the id and the name.
	 * 
	 * @param n
	 *            name of the project
	 * @param id
	 *            project id
	 */
	public Project(String n, int id) {
		name = n;
		idProject = id;
	}

	/**
	 * Creates a project knowing the name only.
	 * 
	 * @param n
	 *            name of the project
	 */
	public Project(String n) {
		name = n;
		idProject = 0;
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
	 * @return the idProject
	 */
	public int getIdProject() {
		return idProject;
	}

	/**
	 * @param idProject the idProject to set
	 */
	public void setIdProject(int idProject) {
		this.idProject = idProject;
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
		if (idProject != other.idProject || idProject == 0 || other.idProject == 0)
			return false;
		return true;
	}

}
