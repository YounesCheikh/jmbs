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

package jmbs.server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Class connect using the singleton Configuration to set a connection to the
 * database.
 * 
 */
public class Connect {

	private Connection connection = null;

	/**
	 * Creates a Connection object created using the parameters of the
	 * configuration file.
	 */
	public Connect() {
		Configuration conf = Configuration.getInstance();
		try {
			Class.forName(conf.getDriver());
			connection = DriverManager.getConnection(conf.getUrl(),
					conf.getLogin(), conf.getPassword());
		} catch (SQLException e) {
			System.out.println("Url: " + conf.getUrl());
			System.out.print(" Unable set the connection!");
		} catch (ClassNotFoundException e) {
			System.out.println("Driver " + conf.getDriver());
			System.out.print(" Unable to load the driver class!");
		}
	}

	/**
	 * Returns the Connection.<br>
	 * NOTE: it must be closed after using.
	 * 
	 * @return java.sql.Connection
	 */
	public Connection getConnection() {
		return connection;
	}

}
