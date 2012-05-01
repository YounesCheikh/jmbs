/*
 * JMBS: Java Micro Blogging System
 *
 * Copyright (C) 2012
 *
 * This program is free software: you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later
 * version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY. See the GNU General Public License for more details. You should
 * have received a copy of the GNU General Public License along with this
 * program. If not, see <http://www.gnu.org/licenses/>.
 *
 * @author Younes CHEIKH http://cyounes.com
 * @author Benjamin Babic http://bbabic.com
 *
 */
package jmbs.common;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;

/**
 * Represents a Project
 *
 * It has a String name, an Integer id and an ArrayList of User called users.\n
 * The array is left at null by default constructor because it would generate
 * chained object creation if not.\n
 * (e.g) All the projects of a user are required by the client.\n 
 * Let n, the number of projects of that user and m the average number of users 
 * in a project: Clearly n << m \n 
 * We would have n Projects created (Complexity in terms of database access: n) 
 * and n*m Users created which makes a complexity of n*m >> n².\n
 * And in most case when a user requests the Projects he won't even take a look 
 * at the users in it.\n So we choose to set the ArrayList to null by default. 
 * You can fill it with external methods like
 * @link {@link jmbs.server.ProjectDAO#getUsers(int) getUsers()} for the server 
 * part.
 *
 */
public class Project implements Serializable {

    /**
     * Project need to be Serializable to be send via RMI.
     */
    private static final long serialVersionUID = -4499543532559102808L;
    /**
     * Defines the constant for the opend status in a project.\n
     * It is adviced to use it every time you create a new project and is set to
     * enhace code comprehension and hide technical details to the users of this
     * class.
     */
    public final static int STATUS_OPENED = 1;
    /**
     * Defines the constant for the closed status in a project.\n
     * It is set to enhace code comprehension and hide technical details to
     * the user of this class. 
     */
    public final static int STATUS_CLOSED = 0;
    /**
     * Defines the constant for enabling the user edit in a project.\n
     * It is adviced to use it every time you create a new project and is set to
     * enhace code comprehension and hide technical details to the users of this
     * class.
     */
    public final static boolean ENABLE_EDIT = true;
    /**
     * Defines the constant for disabling the user edit ability in a project.\n
     * It is set to enhace code comprehension and hide technical details to the
     * users of this class.
     */
    public final static boolean DISABLE_EDIT = false;
    /**
     * Defines the constant for enabling admin delete ability in a project.\n
     * It is adviced to use it every time you create a new project and is set to
     * enhace code comprehension and hide technical details to the users of this
     * class.
     */
    public final static boolean ENABLE_DELETE = true;
    /**
     * Defines the constant for disabling admin delete ability in a project.\n
     * It is set to enhace code comprehension and hide technical details to the
     * users of this class.
     */
    public final static boolean DISABLE_DELETE = false;
    /**
     * Defines the constant for enabling public to see a project.\n
     * It is adviced to use it every time you create a new project and is set to
     * enhace code comprehension and hide technical details to the users of this
     * class.
     */
    public final static boolean PRIVACY_PUBLIC= true;
    /**
     * Defines the constant for preventing public to see a project.\n
     * It is set to enhace code comprehension and hide technical details to the 
     * users of this class.
     */
    public final static boolean PRIVACY_PRIVATE = false;
    
    private int id;
    private User owner;
    private String name;
    private int status;
    private int numberOfUsers;
    private boolean editAllowed;
    private boolean supressionAllowed;
    private boolean aviableToPublic;
    private Timestamp creationTime;
    // attributes below are not created by default because they are mostly unused or could trigger unwanted chained db access and object creation
    private ArrayList<User> users = new ArrayList<User>();

    /**
     * Creates a project knowing the id and the name.
     *
     * @param n name of the project
     * @param id project id
     */
    public Project(String n, int id, User owner, int status, int numberOfUsers, boolean editAllowed, boolean supressionAllowed, boolean aviableToPublic,Timestamp  creationTime) {
        this.name = n;
        this.id = id;
        this.owner = owner;
        this.status = status;
        this.numberOfUsers = numberOfUsers;
        this.editAllowed = editAllowed;
        this.supressionAllowed = supressionAllowed;
        this.aviableToPublic = aviableToPublic;
        this.creationTime =  creationTime;
    }
	
    /**
     * @return the creation time
     */
    public Timestamp getTimestamp() {
    	return this.creationTime;
    }
    
    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the Project id
     */
    public int getId() {
        return id;
    }

    /**
     * @return the users
     */
    public ArrayList<User> getUsers() {
        return users;
    }
    
    /**
     * @return the owner of the project
     */
    public User getOwner() {
    	return this.owner;
    }
    
    /**
     * @return the project status
     */
    public int getStatus() {
    	return this.status;
    }

    /**
     * @return true if the project is public false if it is private
     */
    public boolean isPublic() {
        return this.aviableToPublic;
    }

    /**
     * @return true if project messages are editable by the admin false if not
     */
    public boolean isEditAllowed() {
        return this.editAllowed;
    }

    /**
     * @return true if project messages are delatable by the admin false if not
     */
    public boolean isSupprAllowed() {
        return this.supressionAllowed;
    }
    
    /**
     * @return Number of participants (does not include the owner)
     */
    public int getNumberOfParticipants(){
        return numberOfUsers;
    } 

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Project other = (Project) obj;
        // TODO: more advanced equality check
        if (id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 11 * hash + this.id;
        return hash;
    }
    
        /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return this.name;
    }
}
