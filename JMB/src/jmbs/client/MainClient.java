/*
 * This file is a part of the RMI Plugin for Eclipse tutorials.
 * Copyright (C) 2002-7 Genady Beryozkin
 */
package jmbs.client;

import java.awt.EventQueue;
import jmbs.client.Graphics.ConnectionFrame;
import jmbs.client.Graphics.MainWindow;

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

	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {

					cf = new ConnectionFrame(window);
					// Dispaly the connection frame
					cf.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		});

	}
}
