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

import java.awt.image.BufferedImage;
import java.rmi.RemoteException;
import java.rmi.server.ServerNotActiveException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import jmbs.common.ConnectionInformation;
import jmbs.common.Message;
import jmbs.common.Project;
import jmbs.common.RemoteRequests;
import jmbs.common.User;

public class Requests extends UnicastRemoteObject implements RemoteRequests {

	private Connection con = new Connect().getConnection();
	private static final long serialVersionUID = 5885886202424414094L;
	private boolean security = true;
	private ConnectionInformation ci;

	public Requests(boolean security, String connectionNumber) throws RemoteException {
		this.security = security;
		this.ci = new ConnectionInformation(connectionNumber);
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see jmbs.common.RemoteServer#addMessage(jmbs.common.Message)
	 */
	public int addMessage(Message m) throws RemoteException {
		MessageDAO mdao = new MessageDAO(con);
		int ret = mdao.addMessage(m);
	
		return ret;
	}

	public boolean changeAvatar(int userid, BufferedImage img, String nom, boolean overwrite){
		PictureDAO pdao = new PictureDAO(con);
		return pdao.setAvatar(userid, img, nom, overwrite);
	}
	
	public boolean changeMail(int userid, String pass, String mail) throws RemoteException{
		UserDAO udao = new UserDAO(con);
		return udao.changeMail(userid, pass, mail);
	}
	
	public boolean changePassword(int userid, String oldPass, String newPass) throws RemoteException{
		UserDAO udao = new UserDAO(con);
		return udao.changePassword(userid, oldPass, newPass);
	}

	public boolean close(int userid){
		boolean b = false;
		try {
			ServerMonitor sm = ServerMonitor.getInstance();
			sm.logOut(userid);
			sm.endConnection(ci.getConnectionNumber());
			// TODO: check if rmi creates a new thread with all old objects copied in it or if it creates new ones?
			con.close(); // close the connection in the current thread
			b = true;
		} catch (SQLException e) {
			System.err.println("Database access error !\n Unable to close connection !");
		}
		return b;
	}
	

	public boolean closeProject(int idUser, int idProject) throws RemoteException{
		ProjectDAO pdao = new ProjectDAO(con);
		boolean b = false;
		if (pdao.isOwner(idUser, idProject)){
			b = pdao.closeProject(idProject);
		}
		
		return b;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see jmbs.common.RemoteServer#connectUser(java.lang.String,
	 * java.lang.String)
	 */
	public User connectUser(String em, String psw) throws RemoteException, SecurityException {
		User returnUser = new User();//TODO: explain to youyou how to handle null objects ;)
		Security s = new SecurityDAO(new Connect().getConnection());
		ServerMonitor sm = ServerMonitor.getInstance();
		
		if (security){
			if (s.isUserConnectionAttemptAuthorized(this.ci.getConnectionNumber())){ // Try to connect to an account from an ip (security check)		
				UserDAO udao = new UserDAO(con); 
				User u = udao.getUser(em);
		
				if (u != null){
					boolean b = udao.checkPassword(u, psw);
					if (b) { // if password is correct
						u.setFollows(udao.getFollowed(u)); 
						returnUser = u;
						ServerMonitor.getInstance().logIn(u.getId(),this.ci.getConnectionNumber()); // add activated account to server Monitor
						this.ci.logIn(u.getId());
					}
				}
			
			} else throw new SecurityException("You're not authorized to connect to the server because your ip has made too many unsuccessfull login attemps.\n");

		}else {
			UserDAO udao = new UserDAO(con); 
			User u = udao.getUser(em);
	
			if (u != null){
				boolean b = udao.checkPassword(u, psw);
				if (b) { // if password is correct
					u.setFollows(udao.getFollowed(u)); 
					returnUser = u;
				}
			}
		}
		return returnUser;
	}

	public Project createProject(String name, int iduser){
		ProjectDAO pdao = new ProjectDAO(con);
		UserDAO udao = new UserDAO(con);
		Project p = null;
		
		if (udao.getAccessLevel(iduser) >= ProjectDAO.CREATE_ACCESS_LEVEL){
			 p = pdao.createProject(name,iduser);
		}
				
		return p;
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
		UserDAO udao = new UserDAO(con);
		
		if (!udao.checkMail(u.getMail())) {
			ret = udao.addUser(u, hashedPassword);
		}
		
		return ret;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see jmbs.common.RemoteServer#follow(int, int)
	 */
	public boolean follows(int idfollower, int idfollowed) throws RemoteException {
		UserDAO udao = new UserDAO(con);
		boolean rb = udao.follows(idfollower, idfollowed);
		
		return rb;
	}

	public ConnectionInformation getConnectionInformations(){
		return this.ci;
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see jmbs.common.RemoteServer#getFollowers(jmbs.common.User)
	 */
	public ArrayList<User> getFollowers(User u) throws RemoteException {
		UserDAO udao = new UserDAO(con);
		ArrayList<User> ra =udao.getFollowers(u);
		
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
		
		return ra;
	}

	public ArrayList<User> getProjectUsers (int idProject) throws RemoteException{
		ProjectDAO pdao = new ProjectDAO(con);
		return pdao.getUsers(idProject);
	}

	public ArrayList<Project> getUserProjects (int idUser) throws RemoteException{
		UserDAO udao = new UserDAO(con);
		return udao.getProjects(idUser);
	}

	public void logOut(int userid) throws RemoteException {
		ServerMonitor.getInstance().logOut(userid);
	}

	public boolean participate (int iduser, int idproject, int auth){
		Connection con = new Connect().getConnection();
		UserDAO udao = new UserDAO(con);
		boolean ret = false;
		
		ret = udao.participate(iduser, idproject, auth);
		
		return ret;
	}

	public boolean participate (int iduser, int idproject){
		return this.participate(iduser, idproject, User.DEFAULT_AUTHORISATION_LEVEL);
	}

	public ArrayList<Project> searchForProject(String likeName) throws RemoteException {
		Connection con = new Connect().getConnection();
		ProjectDAO pdao = new ProjectDAO(con);
		ArrayList<Project> found = pdao.findProjects(likeName);
		
		return found;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see jmbs.common.RemoteServer#searchFor(java.lang.String)
	 */
	public ArrayList<User> searchUser(String userName, int param) throws RemoteException {
		UserDAO udao = new UserDAO(con);
		
		if (param != DAO.BY_NAME && param != DAO.BY_FORNAME && param != DAO.BY_BOTH) param = DAO.BY_BOTH; // default value
		ArrayList<User> u = udao.findUsers(userName, param);
		
		return u;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see jmbs.common.RemoteServer#unFollow(int, int)
	 */
	public boolean unFollow(int idfollower, int idfollowed) throws RemoteException {
		UserDAO udao = new UserDAO(con);
		boolean rb = udao.unFollow(idfollower, idfollowed);
		
		return rb;
	}

	public boolean unParticipate (int iduser, int idproject) throws RemoteException{
		Connection con = new Connect().getConnection();
		UserDAO udao = new UserDAO(con);
		boolean ret = udao.unParticipate(iduser, idproject);
	
		return ret;							
	}
	
	public ArrayList<Project> getOwnedProject(int userid) throws RemoteException{
		return new UserDAO(con).getOwnedProject(userid);
	}
	
}
