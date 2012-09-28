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
package jmbs.client.cache;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import jmbs.common.DAO;

/**
 * Execute SQL queries to manage saved identities in cache DB
 *
 * @author <a href="http://cyounes.com/">Younes CHEIKH</a>
 * @author Benjamin Babic
 */
public class IdentityDAO extends DAO {

    /**
     * @param c The connection
     */
    protected IdentityDAO(Connection c) {
        super(c);
        createTable();
    }

    private void createTable() {
        set("CREATE TABLE IF NOT EXISTS identity (mail STRING PRIMARY KEY, pass STRING NOT NULL);");
        executeUpdate();
    }

    /**
     * Add new identity to the table 
     * @param mail email address
     * @param pass hashed password
     */
    protected void add(String mail, String pass) {
        String query = new String();
        query += "insert into identity values(?,?)";
        set(query);
        setString(1, mail);
        setString(2, pass);
        executeUpdate();
    }

    /**
     * Get the saved identities from cache DB
     * @return HashMap <String, String> <br />
     *          Key: Email
     *          Value: Hashed Password
     */
    protected HashMap<String, String> getIdentities() {
        HashMap<String, String> identities = new HashMap<String, String>();
        set("Select * from identity");
        ResultSet rs = executeQuery();

        try {
            do {
                identities.put(rs.getString("mail"), rs.getString("pass"));
            } while (rs.next());
            close(rs);
        } catch (SQLException e) {
            System.out.println("There are no saved identities");
        }
        return identities;
    }

    /**
     * Change the password for a saved identity
     * @param mail email address
     * @param hashedPassword hashed password
     */
    protected void updatePassword(String mail, String hashedPassword) {
        set("update identity set pass=? where mail=?");
        setString(1, hashedPassword);
        setString(2, mail);
        executeUpdate();
    }

    /**
     * remove all identities from DB
     */
    protected void deleteAll() {
        set("DELETE FROM identity");
        executeUpdate();
    }
}
