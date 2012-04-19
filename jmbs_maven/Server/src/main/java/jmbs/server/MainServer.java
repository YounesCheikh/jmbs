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

import java.awt.EventQueue;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @author ycheikh
 * 
 */
public class MainServer {

	/**
	 * @param args
	 * @throws SQLException
	 */
	public static void main(String[] args) {
		FileInputStream propFile;
		try {
			propFile = new FileInputStream("properties.cfg");
			Properties p = new Properties(System.getProperties());
			p.load(propFile);
			propFile.close();
			System.setProperties(p);
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			//e1.printStackTrace();
			System.err.println(e1.getMessage());
			System.exit(-1);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.err.println(e.getMessage());
			System.exit(-1);
		}
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ServerWindow window = new ServerWindow();
					window.frmJmbsServerControl.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
