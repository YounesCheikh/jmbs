package Server;

import java.util.ArrayList;

/**
 * Represents a user.
 */
public class User {

	private String name;
	private String fname;
	private String mail;
	private int idUser;
	private int accesslevel = 0;
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
	 * @param pr
	 *            :array of projects
	 */
	public User(String n, String f, String m, ArrayList<Project> pr) {
		name = n;
		fname = f;
		mail = m;
		idUser = 0;
		projects = pr;
	}

	public User(String n, String f, String m, int id, ArrayList<Project> pr) {
		name = n;
		fname = f;
		mail = m;
		idUser = id;
		projects = pr;
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

	protected ArrayList<Project> getProjects() {
		return this.projects;
	}
	
	public boolean exists(){
		return (this.idUser!=0);
	}

}
