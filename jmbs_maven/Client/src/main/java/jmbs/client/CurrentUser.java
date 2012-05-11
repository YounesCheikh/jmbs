/*
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
 */
package jmbs.client;

import java.io.Serializable;
import java.util.ArrayList;
import jmbs.client.dataTreatment.AutoRefresh;
import jmbs.common.Project;
import jmbs.common.User;

/**
 * @author <a href="mailto:younes.cheikh@gmail.com">Younes CHEIKH</a>
 * @author Benjamin Babic
 * @since 06-05-2012
 * @version 1.0
 */
public class CurrentUser implements Serializable {

    private static final long serialVersionUID = 44433007358974855L;
    private static User u = null;
    /**
     * the default path for default profile picture
     */
    public static String DEFAULT_IMAGE = "/img/avatar.jpg";

    /**
     * @param receivedUser : the user sent by the server
     */
    public CurrentUser(User receivedUser) {
        if (get() == null) {
            CurrentUser.set(receivedUser);
            ArrayList<Project> userPrjcsList = ClientRequests.getUserProjects(u.
                    getId());
            if (userPrjcsList != null) {
                u.setProjects(userPrjcsList);
                System.out.println(u.getProjects().size());
            } else {
                // Set the user projects with empty arraylist
                u.setProjects(new ArrayList<Project>());
            }
        }
    }

    /**
     * @return the current user
     */
    public static User get() {
        return u;
    }

    /**
     * this method is used usually to set the current user to null
     *
     * @param u: set the current user as u
     */
    private static void set(User u) {
        CurrentUser.u = u;
    }

    /**
     * disconnect the current user from server
     */
    public static void disconnect() {
        AutoRefresh.stopAll();
        ClientRequests.logOut(u.getId());
        set(null);
    }

    /**
     * @return current user name
     */
    public static String getName() {
        return u.getName();
    }

    /**
     * @return current user full name
     */
    public static String getFullName() {
        return u.getFullName();
    }

    /**
     * @return current user forname
     */
    public static String getFname() {
        return u.getFname();
    }

    /**
     * @return current user adress mail
     */
    public static String getMail() {
        return u.getMail();
    }

    /**
     * @return current user id
     */
    public static int getId() {
        return u.getId();
    }

    /**
     * @return current user access level
     */
    public static int getAccesslevel() {
        return u.getAccesslevel();
    }

    /**
     * @return current user projects
     */
    public static ArrayList<Project> getProjects() {
        return u.getProjects();
    }

    /**
     * @return a list of users who are followed by the current users
     */
    public static ArrayList<User> getFollows() {
        return u.getFollows();
    }

    /**
     * @return current user picture as array of bytes
     */
    public static byte[] getPic() {
        return u.getPic();
    }
}
