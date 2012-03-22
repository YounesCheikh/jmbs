package jmbs.client;

import java.util.ArrayList;

import jmbs.common.Message;
import jmbs.common.User;


public class UserTimeLine {
	/**
	 * a list mixed of limited number of message from all the users (users who the current user follows)
	 */
	private ArrayList<Message> msgsList ;
	private User currentUser;
	
	/**
	 * Create a new TimeLine for the specified user
	 * @param u: the current connected user on the application
	 */
	public UserTimeLine(User u) {
		this.msgsList = new ArrayList<Message>();
		this.currentUser = u;
	}
	
	
	/**
	 * add a message to the current user's timeline
	 * @param m the message
	 */
	public void addMsg(Message m) {
		this.msgsList.add(m);
	}
	
	
	/**
	 * remove a message from timeline
	 * each user can remove a message which he had shared
	 * this method need to confirm that the current user is the owner of the message before remove it 
	 * @param m the message
	 * @return true if the message removed correctly
	 * @return false if were a problem to remove the message
	 */
	public boolean removeMessage(Message m) {
		if (this.currentUser.equals(m.getOwner())) {
			this.msgsList.remove(m);
			return true;
		}
		else return false;
	}
	
	
	/**
	 * @return the message list
	 */
	public ArrayList<Message> getAll() {
		return this.msgsList;
	}
	
	/**
	 * @return the current user
	 */
	public User getCurrentUser() {
		return this.currentUser;
	}
}
