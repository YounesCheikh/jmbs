/**
 * 
 */
package jmbs.client;

import java.sql.Date;

import jmbs.common.User;
import jmbs.common.Message;
//import java.rmi.Naming;
//import java.rmi.RMISecurityManager;
//import jmbs.remote.RemoteUser;

/**
 * @author yc
 *
 */

public class Client {
	public static void main(String[] args) {
		
		// Test ConnectionPanel
		Window w= new Window();
		User u = new User("Younes","CH","younes@gmail.com",10);
		UserTimeLine testTL = new UserTimeLine(u);
		
		
		
		Message m1 = new Message(1, new User("user1","fuser1","user1@gmail.com",3), "Hi", "Hi every one, how are you?", new Date(1,1,2012));
		Message m2 = new Message(2, u, "Hi", "this is message n¡ 2", new Date(2,1,2012));
		Message m3 = new Message(3, u, "Hi", "this is message n¡ 2", new Date(3,1,2012));
		Message m4 = new Message(4, new User("user3","fuser3","user3@gmail.com",4), "lalalala", "cou couco ucou couc ocu ocuoc u", new Date(1,1,2012));
		testTL.addMsg(m1);
		testTL.addMsg(m2);
		testTL.addMsg(m3);
		testTL.addMsg(m4);
		TLPanel tl = new TLPanel(testTL);
		w.setContentPane(tl);
		w.setVisible(true);
		
		
		
	}
}
