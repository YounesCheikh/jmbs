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
