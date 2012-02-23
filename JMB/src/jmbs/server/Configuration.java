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
