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

package jmbs.client;

import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import jmbs.client.Graphics.others.SayToUser;
import jmbs.common.RemoteRequests;
import jmbs.common.RemoteServer;

public class ServerConnection {
	private static RemoteServer main = null;
	public static RemoteRequests server = null;
	private static String key = null;
	private static String addressIP;

	private static int port;
	private static String name;

	public static int maxReceivedMsgs = 30;

	public int getMaxReceivedMsgs() {
		return maxReceivedMsgs;
	}

	public void setMaxReceivedMsgs(int maxReceivedMsgs) {
		ServerConnection.maxReceivedMsgs = maxReceivedMsgs;
	}

	public ServerConnection() {
		if (main == null) {

			System.setSecurityManager(new RMISecurityManager());
			try {
				ServerConnection.addressIP = "120.0.0.1";
				ServerConnection.name = RemoteServer.REMOTE_NAME;
				ServerConnection.port = 1099;
				Registry registry = LocateRegistry.getRegistry(addressIP, port);
				main = (RemoteServer) registry.lookup(RemoteServer.REMOTE_NAME);
				key = main.connect();
				server = (RemoteRequests) registry.lookup(key);

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
			}

		}
	}

	public void setNewConfiguration(String ip, int port, String name) {
		ServerConnection.addressIP = ip;
		ServerConnection.port = port;
		ServerConnection.name = name;
	}

	public String getAddressIP() {
		return addressIP;
	}

	public void setAddressIP(String addressIP) {
		ServerConnection.addressIP = addressIP;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		ServerConnection.port = port;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		ServerConnection.name = name;
	}
}
