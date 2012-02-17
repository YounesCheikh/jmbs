package jmbs.client;

import java.util.ArrayList;

/**
 * Represents a user.
 */
public class UserDTO {

	private String name;
	private String fname;
	private String mail;
	private String pass;
	private int idUser;
	private int accessLevel;
	private ArrayList<Integer> projects;

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
	public UserDTO(String n, String f, String m, int a, String p, ArrayList<Integer> pr) {
		name = n;
		fname = f;
		mail = m;
		accessLevel = a;
		idUser = 0;
		pass = p;
		projects = pr;
	}

	public UserDTO(String n, String f, String m, int a, String p, int id, ArrayList<Integer> pr) {
		name = n;
		fname = f;
		mail = m;
		accessLevel = a;
		idUser = id;
		pass = p;
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
		UserDTO other = (UserDTO) obj;
		if (idUser != other.idUser)
			return false;
		return true;
	}

	public ArrayList<Integer> getProjects() {
		return this.projects;
	}
	
	public boolean exists(){
		return (this.idUser!=0);
	}

}
