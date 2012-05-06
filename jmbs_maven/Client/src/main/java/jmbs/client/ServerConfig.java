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

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * @author <a href="mailto:younes.cheikh@gmail.com">Younes CHEIKH</a>
 * @author Benjamin Babic
 * @since 06-05-2012
 * @version 1.0
 */
public final class ServerConfig {
	private static final String CONFIGURATION_FILE_PATH = "server.config";
	private static final String DEFAULT_ADRESS_IP = "127.0.0.1";
	private static final int DEFAULT_PORT = 1099;

	private static ServerConfig instance = null;
	private String adressIP;
	private int port;

	/**
	 * Load the configuration from the file 'server.config' 
	 * generate file contains the default configuration if the file does not exist.
	 */
	public ServerConfig() {
		Properties prop = new Properties();

		FileInputStream in = null;
		try {
			in = new FileInputStream(CONFIGURATION_FILE_PATH);
			prop.load(in);
			adressIP = prop.getProperty("IP");
			prop.getProperty("NAME");
			port = Integer.parseInt(prop.getProperty("PORT"));
			in.close();
		} catch (FileNotFoundException e) { // Generating default configuration
											// file if the file was not found.
			System.out
					.println("Configuration file was not found. Default configuration file is beeing generated.");
			File config = new File(CONFIGURATION_FILE_PATH);
			try {
				FileOutputStream out = new FileOutputStream(config);
				prop.setProperty("IP", DEFAULT_ADRESS_IP);
				prop.setProperty("PORT", new String("" + DEFAULT_PORT));
				prop.store(out, "[AUTO-GENERATED CONFIGURATION FILE]");
				out.flush();
				out.close();
				adressIP = DEFAULT_ADRESS_IP;
				port = DEFAULT_PORT;
				System.out
						.println("Configuration file was sucessfully generated.");
				getInstance();
			} catch (IOException e1) {
				System.err.println("Unable to generate configuration file.");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Creates the unique instance of Configuration if it does not already
	 * exist.
	 * 
	 * @return Configuration instance using Singleton Pattern
	 */
	public final static ServerConfig getInstance() {
		if (instance == null) // we try to avoid using synchronized when not
								// Useful
		{
			synchronized (ServerConfig.class) {
				if (instance == null)
					instance = new ServerConfig();
			}
		}
		return instance;
	}

	/**
	 * @return get the server adress ip for the current connection.
	 */
	public String getAdressIP() {
		return adressIP;
	}

	/**
	 * @return the port where the application establish the connection to the
	 *         server
	 */
	public int getPort() {
		return port;
	}
}
