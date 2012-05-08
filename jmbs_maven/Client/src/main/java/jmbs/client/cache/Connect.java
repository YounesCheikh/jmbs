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
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Class connect using the singleton Configuration to set a connection to the
 * database.
 *
 */
public class Connect {

    private static final String DB_FILE_PATH = "cache.db";
    private static final String DEFAULT_URL = "jdbc:sqlite:./cache/";
    private static final String DEFAUlT_DRIVER = "org.sqlite.JDBC";
    private static Connection connection = null;
    private static volatile Connect instance = null;

    /**
     * Creates a Connection object created using the parameters of the
     * configuration file.
     */
    private Connect() {
        try { 
            Class.forName(DEFAUlT_DRIVER);
            connection = DriverManager.getConnection(DEFAULT_URL + DB_FILE_PATH);
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    

    public static Connect getInstance() {
        if (instance == null) // we try to avoid using synchronized when not
        // Useful
        {
            synchronized (Connect.class) {
                if (instance == null) {
                    instance = new Connect();
                }
            }
        }
        return instance;
    }

    /**
     * Returns the Connection.<br> NOTE: it must be closed after using.
     *
     * @return java.sql.Connection
     */
    public Connection getConnection() {
        return connection;
    }
    
    public static void initInstantce() {
    	instance  = null;
    }
}
