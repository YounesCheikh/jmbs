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

import java.rmi.NotBoundException;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import jmbs.client.Graphics.SayToUser;
import jmbs.common.RemoteServer;

public class ClientRequests {
	private static RemoteServer server = null;
	private ServerConfig conf;
	public static int maxReceivedMsgs = 30;
	Registry registry;

	public int getMaxReceivedMsgs() {
		return maxReceivedMsgs;
	}

	public void setMaxReceivedMsgs(int maxReceivedMsgs) {
		ClientRequests.maxReceivedMsgs = maxReceivedMsgs;
	}

	public ClientRequests() {
		if (server == null) {
			conf = new ServerConfig();
			System.setSecurityManager(new RMISecurityManager());
				configure();
				try {
					server.connect();
				} catch (RemoteException e) {
					new SayToUser(e.getMessage(),true);
				}
		}
	}

	public void setNewConfiguration(String ip, int port, String name) {
		
	}
	
	public void configure() {
		try {
			registry = LocateRegistry.getRegistry(conf.getAdressIP(), conf.getPort());
			server = (RemoteServer) registry.lookup(conf.getServerName());
		} catch (RemoteException e) {
			new SayToUser(e.getMessage(), true);
		} catch (NotBoundException e) {
			new SayToUser(e.getMessage(), true);
		}
	}

	public RemoteServer getConnection() {
		return ClientRequests.server;
	}

}
