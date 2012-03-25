/**
 * 
 */
package jmbs.client;

import java.rmi.RMISecurityManager;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import jmbs.common.RemoteServer;

/**
 * @author ycheikh & bbabic
 *
 */
public class ClientRequests {
	private static RemoteServer server = null;
	
	public ClientRequests() {
		if (server == null) {
			System.setSecurityManager(new RMISecurityManager());
			try {
				Registry registry = LocateRegistry
						.getRegistry("localhost");
				server = (RemoteServer) registry
						.lookup("serverjmbs");

			} catch (Exception e) {
				// Something wrong here
				//e.printStackTrace();
				System.out.println("Connection to server impossible\n" + e.getMessage());
			}
		}
	}
	
	public RemoteServer getConnection() {
		return ClientRequests.server;
	}
}
