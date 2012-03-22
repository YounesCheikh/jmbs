/*
 * This file is a part of the RMI Plugin for Eclipse tutorials.
 * Copyright (C) 2002-7 Genady Beryozkin
 */
package jmbs.client;

import java.awt.EventQueue;
import java.rmi.RMISecurityManager;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import jmbs.client.Graphics.ConnectionFrame;
import jmbs.client.Graphics.MainWindow;
import jmbs.common.RemoteServer;
import jmbs.common.User;

import jmbs.common.*;

public class MainClient {

	/**
	 * Tha main window
	 */
	private static MainWindow window;
	/**
	 * The Connection Window
	 */
	private static ConnectionFrame cf;
	/**
	 * the current application user
	 */
	private static User currentUser;

	private static String serverHost;

	public static void main(String[] args) {
		if (args.length != 1) {
			System.out.println("Usage: java " + MainClient.class
					+ " <registry name>");
			return;
		}
		serverHost = args[0];
		//currentUser = new User("Younes", "cheikh", "younes.cheikh@gmail.com");

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					System.setSecurityManager(new RMISecurityManager());
					try {
						Registry registry = LocateRegistry
								.getRegistry(serverHost);
						RemoteServer server = (RemoteServer) registry
								.lookup("serverjmbs");
						
						/**
						 * Just a test of RMI
						 * we ask the server for 4+4
						 * the server returns the somme of 4+4
						 */
						int x = server.somme(4, 4);
						System.out.println("Received result for 4+4 from server: " + x);
						
						// TODO we'll uncomment the following lines after enabling the db connection
						/*
						currentUser = server.connectUser("", "");
						
						if (currentUser==null) {
							System.out.println("user null");
						}
						else System.out.println(currentUser.toString());
						*/
						
					} catch (Exception e) {
						// Something wrong here
						e.printStackTrace();
					}
					
					
					window = new MainWindow();
					/*
					 * We start by displaying the connection Frame when user
					 * enter a right identity, the main window will be shown
					 * automaticly
					 */
					cf = new ConnectionFrame(window);
					cf.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}
}
