/**
 * 
 */
package jmbs.client;

import java.io.Serializable;

import jmbs.common.User;

/**
 * @author ycheikh
 *
 */
public class CurrentUser implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 44433007358974855L;
	private static User u = null;
	
	public CurrentUser(User receivedUser) {
		if (get()==null) {
			CurrentUser.set(receivedUser);
		}
	}
	
	public CurrentUser() {
		
	}

	public User get() {
		return u;
	}

	private static void set(User u) {
		CurrentUser.u = u;
	}
	
	public void disconnect() {
		set(null);
	}
	
	
}
