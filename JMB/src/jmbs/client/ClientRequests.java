/**
 * 
 */
package jmbs.client;

import java.rmi.RMISecurityManager;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import jmbs.client.Graphics.SayToUser;
import jmbs.common.RemoteServer;

/**
 * @author ycheikh & bbabic
 *
 */
public class ClientRequests {
	private static RemoteServer server = null;
	private static String addressIP ;

	private static int port ;
	private static String name ;
	
	public static int maxReceivedMsgs = 30;
	public int getMaxReceivedMsgs() {
		return maxReceivedMsgs;
	}

	public void setMaxReceivedMsgs(int maxReceivedMsgs) {
		ClientRequests.maxReceivedMsgs = maxReceivedMsgs;
	}
	
	public ClientRequests() {
		if (server == null) {
			
			System.setSecurityManager(new RMISecurityManager());
			try {
				ClientRequests.addressIP = "127.0.0.1";
				ClientRequests.name = "serverjmbs";
				ClientRequests.port = 1099;
				Registry registry = LocateRegistry
						.getRegistry(addressIP,port);
				server = (RemoteServer) registry
						.lookup(ClientRequests.name);

			} catch (Exception e) {
				// Something wrong here
				new SayToUser("coucou can't connect to server", true);
			}
		}
	}
	
	public void setNewConfiguration(String ip, int port, String name) {
		ClientRequests.addressIP = ip;
		ClientRequests.port = port;
		ClientRequests.name = name;
	}
	
	public RemoteServer getConnection() {
		return ClientRequests.server;
	}
	
	public String getAddressIP() {
		return addressIP;
	}

	public void setAddressIP(String addressIP) {
		ClientRequests.addressIP = addressIP;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		ClientRequests.port = port;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		ClientRequests.name = name;
	}
}
