
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

package jmbs.client;

import java.io.Serializable;

import jmbs.common.User;

	// TODO: This class will extends from User Class
public class CurrentUser implements Serializable {
	
	private static final long serialVersionUID = 44433007358974855L;
	private static User u = null;
	
	public CurrentUser(User receivedUser) {
			CurrentUser.set(receivedUser);
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
