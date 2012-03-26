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

	private static final long serialVersionUID = 5885886202424414094L;

	public Requests(String name) throws RemoteException {
		this.name = name;
		try {
			Registry registry = LocateRegistry.getRegistry();
			registry.rebind(this.name, this);
		} catch (RemoteException e) {
			System.err.println("Unexcepted remote error.");
			e.printStackTrace();
			System.exit(-1); // can't just return, rmi threads may not exit
		}
		System.out.println("The JMBS server loaded and ready to use.");
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
			udao.addUser(u, hashedPassword);
			ret = true;
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
	public ArrayList<User> searchFor(String userName) throws RemoteException {
		Connection con = new Connect().getConnection();
		UserDAO udao = new UserDAO(con);
		ArrayList<User> u = udao.findUsers(userName);
		
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
		ArrayList<User> ra =udao.getFollowed(u);
		
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
	public ArrayList<Message> getLatestTL(int iduser, int idlastmessage) throws RemoteException {
		Connection con = new Connect().getConnection();
		MessageDAO mdao = new MessageDAO(con);
		ArrayList<Message> ra = mdao.getMessages(iduser, idlastmessage);
		
		try {
			con.close();
		} catch (SQLException e) {
			System.err.println("Database access error !\n Unable to close connection !");
		}
		return ra;
		
	}

}
