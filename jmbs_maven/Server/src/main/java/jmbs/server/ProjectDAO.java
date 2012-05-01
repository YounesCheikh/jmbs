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
/**
 *
 */
package jmbs.server;

import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import jmbs.common.Project;
import jmbs.common.User;

/**
 * extends @see DAO \n
 * This class represents the project data access object. It will
 * access the database given by the connection parameter in his constructor to
 * make the given requests.
 *
 * @author ben
 */
public class ProjectDAO extends DAO {

    /**
     * @deprecated
     */
    private static final int DEFAULT_ACTIVATION = 1;
    /**
     * @deprecated
     */
    private static final int DEFAULT_EDIT_OPTION = 1;
    /**
     * @deprecated
     */
    private static final int DEFAULT_SUPRESS_OPTION = 1;
    /**
     * @deprecated
     */
    private static final int DEFAULT_PRIVACY = 1;

    /**
     * Creates a new ProjectDAO.
     *
     * @param c Connection object describing the connection to the database
     */
    public ProjectDAO(Connection c) {
        super(c);
    }

    /**
     * Closes a given project.
     *
     * @param idproject - the project id
     * @return true if the project was closed false if not
     */
    public boolean closeProject(int idproject) {
        boolean ret;

        if (exists(idproject)) { // can be optimized
            set("UPDATE projects SET status = ? WHERE idproject = ?;");
            setInt(1, Project.STATUS_CLOSED);
            setInt(2, idproject);
            ret = executeUpdate();
        } else {
            System.err.println("Project you are trying to close does not exists.");
            ret = false;
        }

        return ret;
    }

    /**
     * Re-Opens a closed project.
     *
     * @param idproject - the project id
     * @return true if the project was re-opened false if not
     */
    public boolean openProject(int idproject) {
        boolean ret;
        if (exists(idproject)) { // can be optimized
            set("UPDATE projects SET status = ? WHERE idproject = ?;");
            setInt(1, Project.STATUS_OPENED);
            setInt(2, idproject);
            ret = executeUpdate();
        } else {
            System.err.println("Project you are trying to open does not exists.");
            ret = false;
        }
        return ret;
    }

    /**
     * Creates a project in the database with all default options.\n Project
     * created with this method will be opened to public, it messages will be
     * editable and deletable by his owner.
     *
     * @param name - the new project name
     * @param iduser - the id of the creator
     * @deprecated 
     * @return true if project is created
     */
    public boolean createProject(String name, int iduser) {
        boolean b = false;
        Timestamp currentTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

        if (!this.exists(name)) { // If the project does not already exists
            set("INSERT INTO projects "
                    + "(name,idowner,status,nbsuscribers,iseditallowed,issupressionallowed,ispublic,creationtime) "
                    + "VALUES (?,?,?,?,?,?,?,?);");
            setString(1, name);
            setInt(2, iduser);
            setInt(3, DEFAULT_ACTIVATION);
            setInt(4, 0); // starts with 0 involved users
            setInt(5, DEFAULT_EDIT_OPTION);
            setInt(6, DEFAULT_SUPRESS_OPTION);
            setInt(7, DEFAULT_PRIVACY);
            setTimestamp(8, currentTime);
            b = executeUpdate();
        }

        return b;
    }

    /**
     * Creates a project with all the options.
     * @param name - project name
     * @param iduser - owner id
     * @param activation - 1 to activate
     * @param edit - true to enable message edditing by the owner
     * @param supress - true to enable messsage deleting by the owner
     * @param privacy - true to set it as a public project
     * @return int - the created project id or -1
     */
    public int createProject(String name, int iduser, int activation, boolean edit, boolean supress, boolean privacy) {
        int id = -1;
        Timestamp currentTime = new Timestamp(Calendar.getInstance().getTimeInMillis());
        //Conversio
        int editable = edit? 1 : 0;
        int deletable = supress? 1 : 0;
        int priv = privacy? 1 : 0;
        
        if (!this.exists(name)) {
            // If the project does not already exists
            set("INSERT INTO projects "
                    + "(name,idowner,status,nbsuscribers,iseditallowed,issupressionallowed,ispublic,creationtime) "
                    + "VALUES (?,?,?,?,?,?,?,?);", Statement.RETURN_GENERATED_KEYS);
            setString(1, name);
            setInt(2, iduser);
            setInt(3, activation);
            setInt(4, 0); // starts with 0 involved users
            setInt(5, editable);
            setInt(6, deletable);
            setInt(7, priv);
            setTimestamp(8, currentTime);           
            try {
                executeUpdate();
                ResultSet rs = getGeneratedKeys();
                id = rs.getInt("idProject");
                close(rs);
            } catch (SQLException e) {
                //Logger.getLogger(ProjectDAO.class.getName()).log(Level.SEVERE, null, e);
            }
        }

        return id;
    }

    public boolean exists(String name) {
        // xpost from /UserDAO.
        set("SELECT name FROM projects WHERE name=?;");
        setString(1, name);
        ResultSet rs = executeQuery();
        boolean ret = false;

        try {
            rs.getInt("idproject");
            ret = true;
            close(rs);
        } catch (SQLException e) { // project does not exist we can do something
            // here if we really want to waste time ...
        }

        return ret;
    }

    public boolean exists(int idprj) {
        // xpost from /UserDAO.
        set("SELECT name FROM projects WHERE idproject=?;");
        setInt(1, idprj);
        ResultSet rs = executeQuery();
        boolean ret = false;  

        try {
            rs.getString("name");
            ret = true;
            close(rs);
        } catch (SQLException e) { // project does not exist we can do something
            // here if we really want to waste time ...
        }

        return ret;
    }

    protected Project getProject(ResultSet rs) throws SQLException {
        Project p;
        UserDAO udao = new UserDAO(con);
        User u = udao.getUser(rs.getInt("idowner"));
        p = new Project(rs.getString("name"), rs.getInt("idproject"), u,
                rs.getInt("status"), rs.getInt("nbsuscribers"),
                rs.getBoolean("iseditallowed"),
                rs.getBoolean("issupressionallowed"),
                rs.getBoolean("ispublic"), rs.getTimestamp("creationtime"));
        return p;
    }

    /**
     * Get a project using his id.
     *
     * @param id id of the project
     * @return Project - the project or null if there are no
     */
    public Project getProject(int id) {
        Project p = null;

        set("SELECT * FROM projects WHERE idproject=? ;");
        setInt(1, id);
        ResultSet rs = executeQuery();

        try {
            p = getProject(rs);
            close(rs);
        } catch (SQLException e) {
            //System.out.println("Unable to find project with id=" + id + ".");
        }
        
        return p;
    }

    public Project getProject(String name) {
        Project p = null;

        set("SELECT * FROM projects WHERE name=? ;");
        setString(1, name);
        ResultSet rs = executeQuery();

        try {
            p = getProject(rs);
            close(rs);
        } catch (SQLException e) {
            System.out.println("Unable to find any project with name containg "
                    + name);
        }

        return p;
    }

    /**
     * Find all projects by name.
     * @deprecated
     * @param name project name
     * @return Array of found projects
     */
    public ArrayList<Project> findProjects(String name) {
        ArrayList<Project> found = new ArrayList<Project>();

        set("SELECT * FROM projects WHERE name LIKE ? AND ispublic=?;");
        setString(1, "%" + name + "%");
        setInt(2,1); //
        ResultSet res = executeQuery();

        try {
            do {
                found.add(getProject(res));
            } while (res.next());
        } catch (SQLException e) {
            System.out.println("Unable to find any project with name containg "
                    + name);
        }

        return found;
    }
    
    /**
     * Returns the array of visible projects to a user
     * @param name
     * @param iduser
     * @return 
     */    
    public ArrayList<Project> findProjects(String name, int iduser) {
        ArrayList<Project> found = new ArrayList<Project>();

        set("SELECT projects.* FROM projects,participate,users WHERE "
                + "(projects.name LIKE ? "
                + "AND (ispublic=1 "
                + "OR (participate.idproject=projects.idproject AND participate.iduser=?) "
                + "OR projects.idowner=? "
                + "OR (users.iduser=? AND users.authlvl<=?))) "
                + "GROUP BY projects.idproject;");
        
        setString(1, "%" + name + "%");
        setInt(2,iduser);
        setInt(3,iduser);
        setInt(4,iduser);
        setInt(5,UserDAO.ADMIN_ACCESS_LEVEL);
        ResultSet rs = executeQuery();

        try {
            do {
                found.add(getProject(rs));
            } while (rs.next());
            close(rs);
        } catch (SQLException e) {
            System.out.println("Unable to find any project with name containg "
                    + name);
        }

        return found;
    }

    //TODO change user create method with RsultSet
    /**
     * Lists all the users in the project without filling their Project array
     *
     * @return Collection of User
     */
    public ArrayList<User> getUsers(int id) {
        ArrayList<User> u = new ArrayList<User>();
        int userid;

        set("SELECT users.* FROM participate,users WHERE participate.idproject=? AND users.iduser=participate.iduser;");
        setInt(1, id);
        ResultSet res = executeQuery();

        try {
            do {
                userid = res.getInt("iduser");
                u.add(new User(res.getString("name"),
                        res.getString("forename"), res.getString("email"),
                        userid, res.getString("picture"), res.getInt("authlvl")));
            } while (res.next());

        } catch (SQLException e) {
            System.out.println("Unable to find project with id=" + id + ".");
        }

        try {
            res.close();
        } catch (SQLException e) {
            System.err.println("Database acess error !\n Unable to close connection !");
        }
        return u;
    }

    public boolean isOwner(int iduser, int idproject) {
        set("SELECT idowner FROM projects WHERE idproject=?;");
        setInt(1, idproject);
        ResultSet rs = executeQuery();
        boolean b;

        try {
            b = (iduser == rs.getInt("idowner"));
            close(rs);
        } catch (SQLException e) {
            b = false;
        }
        return b;
    }

    public boolean isActive(int projectId) {
        set("SELECT status FROM projects WHERE idproject=?;");
        setInt(1, projectId);
        ResultSet rs = executeQuery();
        boolean b;

        try {

            b = (rs.getInt("status") == 1);
            close(rs);
        } catch (SQLException e) {
            b = false;
        }
        return b;
    }

    public boolean participates(int idproject, int iduser) {
        boolean b;
        set("SELECT idproject FROM participate WHERE idproject=? AND iduser=?;");
        setInt(1, idproject);
        setInt(2, iduser);
        ResultSet rs = executeQuery();

        try {
            rs.getInt("idproject");
            b = true;
            close(rs);
        } catch (SQLException e) {
            b = false;
        }
        return b;
    }

    public boolean isInvolved(int idUser, int idProject) {
        return (participates(idProject, idUser) || isOwner(idUser, idProject));
    }
}
