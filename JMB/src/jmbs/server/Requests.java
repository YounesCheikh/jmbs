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
		System.out.println("The print server is ready");
	}

	public int somme(int x, int y) throws RemoteException {
		return x + y;
	}

	/**
	 * 
	 * @param em
	 *            - user's email
	 * @param psw
	 *            - user's password
	 * @return the user if pass and email match null if not
	 */
	public User connectUser(String em, String psw) throws RemoteException {

		Connection con = new Connect().getConnection();
		UserDAO udao = new UserDAO(con);
		User u = udao.getUser(em);
		if (u == null)
			return new User(null, null, null, -1);

		boolean b = udao.checkPassword(u, psw);
		try {
			con.close();
		} catch (SQLException e) {
			System.out
					.println("Database access error !\n Unable to close connection !");
		}

		if (b)
			return u;

		return new User(null, null, null, -2);
	}

	@Override
	public String getDemoMethod() throws RemoteException {
		return new Date().toString();
	}

	public boolean addMessage(Message m) throws RemoteException {
		boolean retVal = false;

		retVal = new MessageDAO(new Connect().getConnection()).addMessage(m);

		return retVal;
	}

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

}
