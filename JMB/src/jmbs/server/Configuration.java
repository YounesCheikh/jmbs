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

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Singleton used to set up a connection between the database specified in the
 * configuration file.<br>
 * The configuration file path is described by the private static final String
 * CONFIGURATION_FILE_PATH 
 * <br>
 * Note: : The configuration file will be automatically generated with the default values if it does not exist on the server.
 * 
 */
public final class Configuration { /* Singleton pattern for configuration file. */

	private static final String CONFIGURATION_FILE_PATH = "db.connect";
	private static final String DEFAULT_URL = "jdbc:postgresql://localhost:5432/jmbs";
	private static final String DEFAUlT_DRIVER = "org.postgresql.Driver";
	private static final String DEFAULT_LOGIN = "postgres";
	private static final String DEFAULT_PASSWORD = "postgres";
	private static Configuration instance = null;
	private String url;
	private String login;
	private String password;
	private String driver;

	/**
	 * Creates the singleton Configuration.<br>
	 * Should be launched only by the getInstance method.<br>
	 * Follows the singleton pattern. 
	 */
	private Configuration() {
		Properties prop = new Properties();

		FileInputStream in = null;
		try {
			in = new FileInputStream(CONFIGURATION_FILE_PATH);
			prop.load(in);
			in.close();
			url = prop.getProperty("Url");
			login = prop.getProperty("Login");
			driver = prop.getProperty("Driver");
			password = prop.getProperty("Password");
		} catch (FileNotFoundException e) { // Generating default configuration file if the file was not found.
			System.out.println("Configuration file was not found. Default configuration file is beeing generated.");
			File config = new File(CONFIGURATION_FILE_PATH);
			try {
				FileOutputStream out = new FileOutputStream(config);
				prop.setProperty("url", DEFAULT_URL);
				prop.setProperty("driver",DEFAUlT_DRIVER);
				prop.setProperty("login", DEFAULT_LOGIN);
				prop.setProperty("password", DEFAULT_PASSWORD);
				prop.store(out, "[AUTO-GENERATED CONFIGURATION FILE]");
				out.close();
				System.out.println("Configuration file was sucessfully generated.");
				getInstance();
			} catch (IOException e1) {
				System.err.println("Unable to generate configuration file.");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Creates the unique instance of Configuration if it does not already exist.
	 * 
	 * @return Configuration instance using Singleton Pattern
	 */
	public final static Configuration getInstance() {
		if (instance == null) // we try to avoid using synchronized when not
								// Useful
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
