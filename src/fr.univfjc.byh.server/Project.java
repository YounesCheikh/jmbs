package Server;

import java.util.ArrayList;

/**
 * Represents a Project.
 * 
 */
public class Project {

	String name;
	int idProject = 0;
	boolean exists;
	ArrayList<User> users;

	/**
	 * @param n
	 *            name of the project
	 * @param id	
	 *            project id
	 */
	public Project(String n, int id) {
		name=n;
		idProject=id;
		exists = true;
	}
	
	public Project(String n) {
		name=n;
		idProject=0;
		exists = false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */

	public String toString() {
		return this.name;
	}

	/* (non-Javadoc)
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
		return true;
	}



	

}
