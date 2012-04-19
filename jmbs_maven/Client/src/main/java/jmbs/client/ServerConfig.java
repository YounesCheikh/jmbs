/**
 * 
 */
package jmbs.client;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * @author ycheikh
 *
 */
public final class ServerConfig {
	private static final String CONFIGURATION_FILE_PATH = "server.config";
	private static final String DEFAULT_ADRESS_IP = "127.0.0.1";
	private static final String DEFAULT_SERVER_NAME = "serverjmbs";
	private static final int DEFAULT_PORT = 1099;
	
	private static ServerConfig instance = null;
	private String adressIP;
	private String serverName;
	private int port;
	
	
	public  ServerConfig() {
		Properties prop = new Properties();

		FileInputStream in = null;
		try {
			in = new FileInputStream(CONFIGURATION_FILE_PATH);
			prop.load(in);
			adressIP = prop.getProperty("IP");
			serverName = prop.getProperty("NAME");
			port =  Integer.parseInt(prop.getProperty("PORT"));
			in.close();
		} catch (FileNotFoundException e) { // Generating default configuration file if the file was not found.
			System.out.println("Configuration file was not found. Default configuration file is beeing generated.");
			File config = new File(CONFIGURATION_FILE_PATH);
			try {
				FileOutputStream out = new FileOutputStream(config);
				prop.setProperty("IP", DEFAULT_ADRESS_IP);
				prop.setProperty("NAME",DEFAULT_SERVER_NAME);
				prop.setProperty("PORT", new String(""+DEFAULT_PORT));
				prop.store(out, "[AUTO-GENERATED CONFIGURATION FILE]");
				out.flush();
				out.close();
				adressIP = DEFAULT_ADRESS_IP;
				serverName = DEFAULT_SERVER_NAME;
				port = DEFAULT_PORT;
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
	
	public String getAdressIP() {
		return adressIP;
	}


	public String getServerName() {
		return serverName;
	}


	public int getPort() {
		return port;
	}
}
