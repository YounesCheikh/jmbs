package jmbs.common;

import java.util.ArrayList;

// TODO: create jdoc explanations like in jmbs.common.Project 
/**
 * Represents a user.
 */
public class User {

	private String name;
	private String fname;
	private String mail;
	private int id;
	// these attributes are not created by default because they are mostly
	// unused or could trigger unwanted chained db access and object creation
	private int accesslevel = 0;
	private ArrayList<Project> projects = null;
	private ArrayList<User> follows = null;

	/**
	 * Creates a user from given informations. Some attributes are not created
	 * by default because they are mostly unused or could trigger unwanted
	 * chained database access and chained object creation. Which would make
	 * this object to heavy to be transfered in reasonable time.
	 * 
	 * @param n
	 *            user's name
	 * @param f
	 *            user's fore name
	 * @param m
	 *            user's mail
	 */
	public User(String n, String f, String m) {
		this.name = n;
		this.fname = f;
		this.mail = m;
		this.id = 0;
	}

	/**
	 * Creates a user with his id.
	 * 
	 * @param n
	 *            user's name
	 * @param f
	 *            user's fore name
	 * @param m
	 *            user's mail
	 * @param id
	 *            user's id
	 */
	public User(String n, String f, String m, int id) {
		this.name = n;
		this.fname = f;
		this.mail = m;
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the fore name
	 */
	public String getFname() {
		return fname;
	}

	/**
	 * @param fname
	 *            the fore name to set
	 */
	public void setFname(String fname) {
		this.fname = fname;
	}

	/**
	 * @return the email
	 */
	public String getMail() {
		return mail;
	}

	/**
	 * @param mail
	 *            the email to set
	 */
	public void setMail(String mail) {
		this.mail = mail;
	}

	/**
	 * @return the User id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param idUser
	 *            the User id to set
	 */
	public void setId(int idUser) {
		this.id = idUser;
	}

	/**
	 * @return the access level
	 */
	public int getAccesslevel() {
		return accesslevel;
	}

	/**
	 * @param accesslevel
	 *            the access level to set
	 */
	public void setAccesslevel(int accesslevel) {
		this.accesslevel = accesslevel;
	}

	/**
	 * @return the projects // TODO: more advanced equality check
	 */
	public ArrayList<Project> getProjects() {
		return projects;
	}

	/**
	 * @param projects
	 *            the projects to set
	 */
	public void setProjects(ArrayList<Project> projects) {
		this.projects = projects;
	}

	/**
	 * @return the followed users
	 */
	public ArrayList<User> getFollows() {
		return follows;
	}

	/**
	 * @param follows
	 *            the followed users to set
	 */
	public void setFollows(ArrayList<User> follows) {
		this.follows = follows;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "\nName: " + name + "\nForename: " + fname + "\nEmail: " + mail + "\n";
	}

	/*
	 * (non-Javadoc) 2 users are not even comparable if they have no id given by
	 * the DB. If one of the 2 users have a id equal to 0, result will be false
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
		User other = (User) obj;
		// TODO: more advanced equality check
		if (id != other.id || id == 0 || other.id == 0)
			return false;
		return true;
	}

}