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

import java.rmi.NoSuchObjectException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;

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

		// ClassLoader.getSystemClassLoader().getSystemResource("jmbs/common/");
		System.getProperties().put("java.rmi.server.codebase",
				ClassLoader.getSystemResource("jmbs/common/").toString());
		try {
			UnicastRemoteObject.unexportObject(
					LocateRegistry.createRegistry(1099), true);
		} catch (NoSuchObjectException e1) {
			// TODO Auto-generated catch block
			// e1.printStackTrace();
			System.out.println(e1.getMessage());
		} catch (RemoteException e1) {
			// TODO Auto-generated catch block
			// e1.printStackTrace();
			System.out.println(e1.getMessage());
		}
		try {
			LocateRegistry.createRegistry(1099);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			System.err
					.println("Failed to create a rigistry on port 1099.\nRMIREGISTRY already in used:\n"
							+ e.getMessage());
			System.exit(-1);
		}
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				ServerWindow window = new ServerWindow();
				window.frmJmbsServerControl.setVisible(true);
			}
		});
	}
}