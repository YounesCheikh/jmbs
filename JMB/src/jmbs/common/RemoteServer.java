/*
 * This file is a part of the RMI Plugin for Eclipse tutorials.
 * Copyright (C) 2002-7 Genady Beryozkin
 */
package jmbs.common;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * This is the generic remote printer inteface. It has methods to submit print
 * jobs, query their status. Querying the printer status is also possible using
 * the {@link #getPrinterStatus()} method.
 * 
 * @author Genady Beryozkin, rmi-info@genady.net
 */
public interface RemoteServer extends Remote {

	
	/**
	 * @param em	- user's email
	 * @param psw	- user's password
	 * @return	the user if pass and email match null if not
	 * @throws RemoteException
	 */
	public User connectUser(String em, String psw) throws RemoteException;

	/**
	 * @param m the message
	 * @return true if adding message is successed
	 * @throws RemoteException
	 */
	public boolean addMessage(Message m) throws RemoteException;

	/**
	 * Creat new user on DB
	 * @param u User
	 * @param hashedpassword
	 * @return true if creating new user on db is successed
	 * @throws RemoteException
	 */
	public boolean createUser(User u, String hashedpassword)
			throws RemoteException;

	/**
	 * Search for users on DB 
	 * @param userName string
	 * @return a list of users who have name as the entred string
	 * @throws RemoteException
	 */
	public ArrayList<User> searchFor(String userName) throws RemoteException;

	/**
	 * add relation on follows table in the DB 
	 * @param idfollower the user id
	 * @param idfollowed the folled id
	 * @return true if editing on the DB is successed
	 * @throws RemoteException
	 */
	public boolean follow(int idfollower, int idfollowed)
			throws RemoteException;

	/**
	 * remove relation from follows table in the DB 
	 * @param idfollower the user id
	 * @param idfollowed the folled id
	 * @return true if editing on the DB is successed
	 * @throws RemoteException
	 */
	public boolean unFollow(int idfollower, int idfollowed)
			throws RemoteException;

	/**
	 * @param u user
	 * @return list of users who follow the user u
	 * @throws RemoteException
	 */
	public ArrayList<User> getFollowers(User u) throws RemoteException;

	/**
	 * get the latest messages from server 
	 * @param iduser the id of user who requests the latest messages from server
	 * @param idlastmessage the id of the lasted messages requested from server by the client
	 * @return list of messages
	 * @throws RemoteException
	 */
	public ArrayList<Message> getLatestTL(int iduser, int idlastmessage) throws RemoteException;

}