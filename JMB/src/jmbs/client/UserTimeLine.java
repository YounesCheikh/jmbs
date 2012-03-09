package jmbs.client;

import java.util.ArrayList;
import jmbs.common.Message;
import jmbs.common.User;

public class UserTimeLine {
	private ArrayList<Message> msgsList ;
	private User currentUser;
	
	public UserTimeLine(User u) {
		this.msgsList = new ArrayList<Message>();
		this.currentUser = u;
	}
	
	public void addMsg(Message m) {
		this.msgsList.add(m);
	}
	
	public boolean removeMessage(Message m) {
		if (this.currentUser.equals(m.getOwner())) {
			this.msgsList.remove(m);
			return true;
		}
		else return false;
	}
	
	public ArrayList<Message> getAll() {
		return this.msgsList;
	}
	
	public User getCurrentUser() {
		return this.currentUser;
	}
}
