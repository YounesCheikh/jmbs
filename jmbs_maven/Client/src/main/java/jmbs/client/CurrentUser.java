
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
import java.util.ArrayList;
import jmbs.common.Project;
import jmbs.common.User;

public class CurrentUser implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 44433007358974855L;
	private static User u = null;
	
	public CurrentUser(User receivedUser) {
		if (get()==null) {
			CurrentUser.set(receivedUser);
			u.setProjects(RemoteRequests.getUserProjects(u.getId()));
		}
	}
	
	public CurrentUser() {

	}

	public static User get() {
		return u;
	}

	private static void set(User u) {
		CurrentUser.u = u;
	}
	
	public static void disconnect() {
		RemoteRequests.logOut(u.getId());
		set(null);
	}

	/**
	 * @return the name
	 */
	public static String getName() {
		return u.getName();
	}

	public static String getFullName() {
		return u.getFullName();
	}


	/**
	 * @return the fore name
	 */
	public static String getFname() {
		return u.getFname();
	}


	/**
	 * @return the email
	 */
	public static String getMail() {
		return u.getMail();
	}


	/**
	 * @return the User id
	 */
	public static int getId() {
		return u.getId();
	}


	/**
	 * @return the access level
	 */
	public static int getAccesslevel() {
		return u.getAccesslevel();
	}

	/**
	 * @return the projects // TODO: more advanced equality check
	 */
	public static ArrayList<Project> getProjects() {
		return u.getProjects();
	}


	/**
	 * @return the followed users
	 */
	public static ArrayList<User> getFollows() {
		return u.getFollows();
	}



	public static String getPic() {
		return u.getPic();
	}
	
	
}
