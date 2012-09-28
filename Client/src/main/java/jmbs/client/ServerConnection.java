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

import java.net.MalformedURLException;
import java.rmi.AccessException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import jmbs.client.gui.others.SayToUser;
import jmbs.common.RemoteRequests;
import jmbs.common.RemoteServer;

/**
 * @author <a href="mailto:younes.cheikh@gmail.com">Younes CHEIKH</a>
 * @author Benjamin Babic
 * @since 06-05-2012
 * @version 1.0
 */
public class ServerConnection {
	private static RemoteServer main = null;
        
        /**
         * the interface
         */
	public static RemoteRequests server = null;
	private static String key = null;
	private static String addressIP;

	private static int port;
        
        /**
         * the maximum number of messages requested from server
         */
	public static int maxReceivedMsgs = 30;

	/**
	 * @return the maximum of messages want to receive from server
	 */
	public int getMaxReceivedMsgs() {
		return maxReceivedMsgs;
	}

	/**
	 * @param maxReceivedMsgs
	 *            set the maxium of messages want to receive from server
	 */
	public void setMaxReceivedMsgs(int maxReceivedMsgs) {
		ServerConnection.maxReceivedMsgs = maxReceivedMsgs;
	}

	/**
	 * create a connection between the client and the server
	 */
	public ServerConnection() {
		if (main == null) {

			if (System.getSecurityManager() == null) {
				System.setSecurityManager(new RMISecurityManager());
			}
			try {
				ServerConnection.addressIP = "localhost";
				ServerConnection.port = 1099;
				Registry registry = LocateRegistry.getRegistry(addressIP, port);
				main = (RemoteServer) registry.lookup(RemoteServer.REMOTE_NAME);
				key = main.connect();
				server = (RemoteRequests) Naming.lookup(key);

			} catch (SecurityException se) {
				SayToUser.error("SecurityException", se.getMessage());
				server = null;
				main = null;
			} catch (AccessException e) {
				SayToUser.error("AccessException", e.getMessage());
				server = null;
				main = null;
			} catch (RemoteException e) {
				SayToUser.error("RemoteException", e.getMessage());
				server = null;
				main = null;
			} catch (NotBoundException e) {
				SayToUser.error("NotBoundException", e.getMessage());
				server = null;
				main = null;
			} catch (MalformedURLException e) {
				System.out.println(e.getMessage());
			}

		}
	}

	/**
	 * Setting a new configuration to connect to the server
	 * 
	 * @param ip
	 *            ip adress of the server
	 * @param port
	 *            port to establish connection
	 */
	public void setNewConfiguration(String ip, int port) {
		ServerConnection.addressIP = ip;
		ServerConnection.port = port;
	}

	/**
	 * @return the adress ip of the current connection's configuration
	 */
	public String getAddressIP() {
		return addressIP;
	}

	/**
	 * @param addressIP
	 *            : set the adress ip of the current connection's configuration
	 */
	public void setAddressIP(String addressIP) {
		ServerConnection.addressIP = addressIP;
	}

	/**
	 * @return the port where the current configuration establish the
	 *         connecition
	 */
	public int getPort() {
		return port;
	}

	/**
	 * @param port
	 *            set the port where the current configuration establish the
	 *            connecition
	 */
	public void setPort(int port) {
		ServerConnection.port = port;
	}
}
