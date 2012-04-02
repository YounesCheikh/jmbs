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

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * Singleton used to set up a connection between the database specified in the
 * configuration file "db.connect" <br>
 * Note: : YOU MUST A THE CONFIGURATION FILE TO USE THAT CLASS.
 * 
 */
public final class Configuration { /* Singleton pattern for configuration file. */

	private static Configuration instance = null;
	private String url;
	private String login;
	private String password;
	private String driver;

	/**
	 * Creates the singleton Configuration.
	 */
	private Configuration() {
		Properties prop = new Properties();

		FileInputStream in;
		try {
			in = new FileInputStream("db.connect");
			prop.load(in);
			in.close();
			url = prop.getProperty("Url");
			login = prop.getProperty("Login");
			driver = prop.getProperty("Driver");
			password = prop.getProperty("Password");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Creates the unique instance of Configuration if it does not exist.
	 * 
	 * @return Configuration instance using Singleton Pattern
	 */
	public final static Configuration getInstance() {
		if (instance == null) // we try to avoid using synchronized when not
								// usefull
		{
			synchronized (Configuration.class) {
				if (instance == null)
					instance = new Configuration();
			}
		}
		return instance;
	}

	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @return the login
	 */
	public String getLogin() {
		return login;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @return the driver
	 */
	public String getDriver() {
		return driver;
	}

}
