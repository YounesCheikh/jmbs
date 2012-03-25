/*
 * This file is a part of the RMI Plugin for Eclipse tutorials.
 * Copyright (C) 2002-7 Genady Beryozkin
 */
package jmbs.server;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import jmbs.common.Message;
import jmbs.common.RemoteServer;
import jmbs.common.User;

public class Requests extends UnicastRemoteObject implements RemoteServer {

	private String name;

	private static final long serialVersionUID = 5885886202424414094L;

	public Requests(String name) throws RemoteException {
		this.name = name;
		try {
			Registry registry = LocateRegistry.getRegistry();
			registry.rebind(this.name, this);
		} catch (RemoteException e) {
			System.err.println("Something wrong happended on the remote end");
			e.printStackTrace();
			System.exit(-1); // can't just return, rmi threads may not exit
		}
		System.out.println("The JMBS server is ready");
	}

	/* (non-Javadoc)
	 * @see jmbs.common.RemoteServer#connectUser(java.lang.String, java.lang.String)
	 */
	public User connectUser(String em, String psw) throws RemoteException {

		Connection con = new Connect().getConnection();
		UserDAO udao = new UserDAO(con);
		User u = udao.getUser(em);
		if (u == null)
			return new User(null, null, null, -1);

		boolean b = udao.checkPassword(u, psw);
		try {
			if (b) {
				udao.setFollowingList(u);
			}
			con.close();
		} catch (SQLException e) {
			System.out
					.println("Database access error !\n Unable to close connection !");
		}

		if (b) {
			return u;
		}

		return new User(null, null, null, -2);
	}

	/* (non-Javadoc)
	 * @see jmbs.common.RemoteServer#addMessage(jmbs.common.Message)
	 */
	public boolean addMessage(Message m) throws RemoteException {
		boolean retVal = false;

		retVal = new MessageDAO(new Connect().getConnection()).addMessage(m);

		return retVal;
	}

	/* (non-Javadoc)
	 * @see jmbs.common.RemoteServer#createUser(jmbs.common.User, java.lang.String)
	 */
	public boolean createUser(User u, String hashedpassword)
			throws RemoteException {
		boolean retVal = false;
		Connection con = new Connect().getConnection();
		UserDAO udao = new UserDAO(con);
		if (!udao.checkMail(u.getMail())) {
			retVal = udao.addUser(u, hashedpassword);
		}
		return retVal;
	}

	/* (non-Javadoc)
	 * @see jmbs.common.RemoteServer#searchFor(java.lang.String)
	 */
	public ArrayList<User> searchFor(String userName) throws RemoteException {
		Connection con = new Connect().getConnection();
		UserDAO udao = new UserDAO(con);
		return udao.findUsers(userName);
	}

	/* (non-Javadoc)
	 * @see jmbs.common.RemoteServer#follow(int, int)
	 */
	public boolean follow(int idfollower, int idfollowed)
			throws RemoteException {
		Connection con = new Connect().getConnection();
		UserDAO udao = new UserDAO(con);
		return udao.follow(idfollower, idfollowed);
	}

	/* (non-Javadoc)
	 * @see jmbs.common.RemoteServer#unFollow(int, int)
	 */
	public boolean unFollow(int idfollower, int idfollowed)
			throws RemoteException {
		Connection con = new Connect().getConnection();
		UserDAO udao = new UserDAO(con);
		return udao.unFollow(idfollower, idfollowed);
	}

	/* (non-Javadoc)
	 * @see jmbs.common.RemoteServer#getFollowers(jmbs.common.User)
	 */
	public ArrayList<User> getFollowers(User u) throws RemoteException {
		Connection con = new Connect().getConnection();
		UserDAO udao = new UserDAO(con);
		return udao.getFollowersList(u);
	}

	/* (non-Javadoc)
	 * @see jmbs.common.RemoteServer#getLatestTL(int, int)
	 */
	public ArrayList<Message> getLatestTL(int iduser, int idlastmessage)
			throws RemoteException {
		Connection con = new Connect().getConnection();
		MessageDAO mdao = new MessageDAO(con);
		return mdao.getLatestsMessages(iduser, idlastmessage);
	}

}
