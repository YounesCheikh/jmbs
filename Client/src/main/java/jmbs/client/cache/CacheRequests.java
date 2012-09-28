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

import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import jmbs.client.dataTreatment.ImageTreatment;
import jmbs.common.Message;

/**
 * Allow the other classes to invoke requests in the cache Database <br /> <i>
 * NO PASSWORD IS SAVED UNLESS A SUCCESSFULL CONNECTION</i>
 *
 * @author <a href="http://cyounes.com/">Younes CHEIKH</a>
 *
 */
public class CacheRequests {

    private static Connection con;

    /**
     * object to execute queries on cache
     */
    public CacheRequests() {
        con = Connect.getInstance().getConnection();
    }

    /**
     * Add new identity to the cache DB
     *
     * @param mail email address used at login
     * @param pass password hashed
     */
    public void insertIdentity(String mail, String pass) {
        IdentityDAO idao = new IdentityDAO(con);
        idao.add(mail, pass);
    }

    /**
     * Load the the saved identities from the cache
     *
     * @return HashMap of identities with (key: email, value: hashed Password)
     */
    public HashMap<String, String> getIdentities() {
        IdentityDAO idao = new IdentityDAO(con);
        HashMap<String, String> retMap = idao.getIdentities();
        return retMap;
    }

    /**
     * Update password in cache This method is useful when the user change the
     * password <br /> it will replace the old hashed password by the new one.
     *
     * @param mail Email address
     * @param hashedPassword the new hashed password
     */
    public void updatePassword(String mail, String hashedPassword) {
        IdentityDAO idao = new IdentityDAO(con);
        idao.updatePassword(mail, hashedPassword);
    }

    /**
     * remove all identities from cache DB
     */
    public static void removeAllIdentities() {
        IdentityDAO idao = new IdentityDAO(con);
        idao.deleteAll();
    }

    /**
     * add new message into cahe DB
     *
     * @param m Message
     */
    public void addMessage(Message m) {
        File f = new File("upics/+" + m.getOwner().getId() + ".jpg");
        if (!f.exists()) {
            ImageTreatment.exportPicture(m.getOwner().getId(),
                    ImageTreatment.convert(m.getOwner().getPic()), "jpg");
        }
        MsgDAO mdao = new MsgDAO(con);
        mdao.insertMessage(m);
    }

    /**
     * Get all messages related to the current user from cache
     *
     * @return a list of messages
     */
    public ArrayList<Message> getMessages() {
        MsgDAO mdao = new MsgDAO(con);

        ArrayList<Message> retList = mdao.getMessages();
        return retList;
    }

    /**
     * remove all messages from cache
     */
    public static void removeAllMsgs() {
        MsgDAO mdao = new MsgDAO(con);
        mdao.deleteAll();
    }

    /**
     * Close connection to cache DB
     */
    public static void closeConnection() {
        Connect.initInstantce();
        try {
            con.close();
        } catch (SQLException e) {
            // Ignore
        }
    }
}
