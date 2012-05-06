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

import java.io.*;
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
	
	private static volatile Configuration instance = null;
	private String url;
	private String login;
	private String password;
	private String driver;

	/**
	 * Creates the singleton Configuration.<br>
	 * Should be launched only by the getInstance method.<br>
	 * Follows the singleton pattern. 
         * Configuration creation never fails, if it isn't loaded and if it will be
         * logged and the default options will be forced.
	 */
	private Configuration() {
            if (!loadCfg()) {
                System.err.println("db.connect file not found: using default cfg");
                forceDefaultCfg();
            }
	}
        
        /**
         * Loads the configuration file in the single Configuration instance.
         * If the file was not found it generates the file using a private
         * method.
         * 
         * @return true if confing was loaded false if not
         */
        private boolean loadCfg() {
            Properties prop = new Properties();
            boolean b;
            try{
                FileInputStream in = new FileInputStream(CONFIGURATION_FILE_PATH);
                prop.load(in);  
                this.url = prop.getProperty("Url");
                this.login = prop.getProperty("Login");
                this.driver = prop.getProperty("Driver");
                this.password = prop.getProperty("Password");
                in.close();
                b = true;
            } catch (FileNotFoundException e) { // if the file was not found
                generateDefaultCfg(); // we try to generate it
                b = false;
            } catch (IOException e) {
                System.err.println("Unable to load the file check permission on "+CONFIGURATION_FILE_PATH);
                b = false;
            } 
            return b;
        }
        
        private boolean generateDefaultCfg(){
            Properties prop = new Properties();
            File config = new File(CONFIGURATION_FILE_PATH);
            boolean b;
            
            try {
                FileOutputStream out = new FileOutputStream(config);
                prop.setProperty("Url", DEFAULT_URL);
                prop.setProperty("Driver",DEFAUlT_DRIVER);
                prop.setProperty("Login", DEFAULT_LOGIN);
                prop.setProperty("Password", DEFAULT_PASSWORD);
                prop.store(out, "[AUTO-GENERATED CONFIGURATION FILE]");
                out.flush();
                out.close();
                System.out.println("Configuration file was sucessfully generated.");
                b = true;
            } catch (IOException e1) {
                System.err.println("Unable to generate configuration file.");
                b = false;
            }
            return b;
        }
        
        private void forceDefaultCfg(){
            this.url = DEFAULT_URL;
            this.login = DEFAUlT_DRIVER;
            this.driver = DEFAULT_LOGIN;
            this.password = DEFAULT_PASSWORD;
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
