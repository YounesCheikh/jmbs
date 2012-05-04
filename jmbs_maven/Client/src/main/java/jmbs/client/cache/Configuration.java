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

package jmbs.client.cache;

/**
 * Singleton used to set up a connection between the database specified in the
 * configuration file.<br>
 * The configuration file path is described by the private static final String
 * CONFIGURATION_FILE_PATH <br>
 * Note: : The configuration file will be automatically generated with the
 * default values if it does not exist on the server.
 * 
 */
public final class Configuration { /* Singleton pattern for configuration file. */

	protected static final String DB_FILE_PATH = "db.connect";
	protected static final String DEFAULT_URL = "jdbc:sqlite:";
	protected static final String DEFAUlT_DRIVER = "org.postgresql.Driver";

	private static Configuration instance = null;
	/**
	 * Creates the singleton Configuration.<br>
	 * Should be launched only by the getInstance method.<br>
	 * Follows the singleton pattern. Configuration creation never fails, if it
	 * isn't loaded and if it will be logged and the default options will be
	 * forced.
	 */
	private Configuration() {

	}

	/**
	 * Creates the unique instance of Configuration if it does not already
	 * exist.
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


}
