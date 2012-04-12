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

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import jmbs.common.Message;
import jmbs.common.RemoteServer;
import jmbs.common.User;

public class Requests extends UnicastRemoteObject implements RemoteServer {

	private String name;
	private Registry registry;
	private static final long serialVersionUID = 5885886202424414094L;

	public Requests(String name) throws RemoteException {
		this.name = name;
		try {
			registry = LocateRegistry.getRegistry();
			//registry.rebind(this.name, this);
		} catch (RemoteException e) {
			System.err.println("Unexcepted remote error.");
			e.printStackTrace();
			System.exit(-1); // can't just return, rmi threads may not exit
		}
		//System.out.println("The JMBS server loaded and ready to use.");
	}
	
	public String getName() {
		return name;
	}
	
	public Registry getRegistry() {
		return registry;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see jmbs.common.RemoteServer#connectUser(java.lang.String,
	 * java.lang.String)
	 */
	public User connectUser(String em, String psw) throws RemoteException {

		Connection con = new Connect().getConnection();
		UserDAO udao = new UserDAO(con);
		User returnUser = new User(); 
		User u = udao.getUser(em);
		
		if (u != null){
			boolean b = udao.checkPassword(u, psw);
			if (b) { // if password is correct
			u.setFollows(udao.getFollowed(u)); 
			returnUser = u;
			}
		}
		
		try {
			con.close();
		} catch (SQLException e) {
			System.err.println("Database access error !\n Unable to close connection !");
		}

		return returnUser;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see jmbs.common.RemoteServer#addMessage(jmbs.common.Message)
	 */
	public int addMessage(Message m) throws RemoteException {
		Connection con = new Connect().getConnection();
		MessageDAO mdao = new MessageDAO(con);
		int ret = mdao.addMessage(m);

		try {
			con.close();
		} catch (SQLException e) {
			System.err.println("Database access error !\n Unable to close connection !");
		}
		return ret;
	}

	//TODO send a mail and check if used.
	/*
	 * (non-Javadoc)
	 * 
	 * @see jmbs.common.RemoteServer#createUser(jmbs.common.User,
	 * java.lang.String)
	 */
	public boolean createUser(User u, String hashedPassword) throws RemoteException {
		boolean ret = false;
		Connection con = new Connect().getConnection();
		UserDAO udao = new UserDAO(con);
		
		if (!udao.checkMail(u.getMail())) {
			ret = udao.addUser(u, hashedPassword);
		}
		
		try {
			con.close();
		} catch (SQLException e) {
			System.err.println("Database access error !\n Unable to close connection !");
		}
		return ret;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see jmbs.common.RemoteServer#searchFor(java.lang.String)
	 */
	public ArrayList<User> searchUser(String userName, int param) throws RemoteException {
		Connection con = new Connect().getConnection();
		UserDAO udao = new UserDAO(con);
		
		if (param != DAO.BY_NAME && param != DAO.BY_FORNAME && param != DAO.BY_BOTH) param = DAO.BY_BOTH; // default value
		ArrayList<User> u = udao.findUsers(userName, param);
		
		try {
			con.close();
		} catch (SQLException e) {
			System.err.println("Database access error !\n Unable to close connection !");
		}
		return u;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see jmbs.common.RemoteServer#follow(int, int)
	 */
	public boolean follows(int idfollower, int idfollowed) throws RemoteException {
		Connection con = new Connect().getConnection();
		UserDAO udao = new UserDAO(con);
		boolean rb = udao.follows(idfollower, idfollowed);
		
		try {
			con.close();
		} catch (SQLException e) {
			System.err.println("Database access error !\n Unable to close connection !");
		}
		return rb;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see jmbs.common.RemoteServer#unFollow(int, int)
	 */
	public boolean unFollow(int idfollower, int idfollowed) throws RemoteException {
		Connection con = new Connect().getConnection();
		UserDAO udao = new UserDAO(con);
		boolean rb = udao.unFollow(idfollower, idfollowed);
		
		try {
			con.close();
		} catch (SQLException e) {
			System.err.println("Database access error !\n Unable to close connection !");
		}
		return rb;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see jmbs.common.RemoteServer#getFollowers(jmbs.common.User)
	 */
	public ArrayList<User> getFollowers(User u) throws RemoteException {
		Connection con = new Connect().getConnection();
		UserDAO udao = new UserDAO(con);
		ArrayList<User> ra =udao.getFollowers(u);
		
		try {
			con.close();
		} catch (SQLException e) {
			System.err.println("Database access error !\n Unable to close connection !");
		}
		return ra;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see jmbs.common.RemoteServer#getLatestTL(int, int)
	 */
	public ArrayList<Message> getLatestTL(int iduser, int idlastmessage, int maxMsg) throws RemoteException {
		Connection con = new Connect().getConnection();
		MessageDAO mdao = new MessageDAO(con);
		ArrayList<Message> ra = mdao.getMessages(iduser, idlastmessage, maxMsg);
		
		try {
			con.close();
		} catch (SQLException e) {
			System.err.println("Database access error !\n Unable to close connection !");
		}
		return ra;
		
	}

}
