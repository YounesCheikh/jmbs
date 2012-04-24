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

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class SysConf {

	private static boolean macSys = false;
	private static boolean alreadyTested = false;

	public SysConf() {

	}

	private static void macSetup(String appName) {
		String os = System.getProperty("os.name").toLowerCase();
		macSys = os.startsWith("mac os x");
		if (!macSys)
			return;

		System.setProperty("apple.laf.useScreenMenuBar", "true");
		System.setProperty("com.apple.mrj.application.apple.menu.about.name",
				appName);

	}

	public void setUIMngr() {
		if (!alreadyTested) {
			alreadyTested = true;
			macSetup("JMBS");
			try {
				UIManager.setLookAndFeel(UIManager
						.getSystemLookAndFeelClassName());
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				// e.printStackTrace();
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				// e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				// e.printStackTrace();
			} catch (UnsupportedLookAndFeelException e) {
				// TODO Auto-generated catch block
				// e.printStackTrace();
			}
		}
	}

	public boolean isMac() {
		return macSys;
	}

	public void centerThisFrame(JFrame window) {
		// Get the size of thescreen
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		// Determine the new location of the window
		int w = window.getSize().width;
		int h = window.getSize().height;
		int x = (dim.width - w) / 2;
		int y = (dim.height - h) / 2;
		// Move the window
		window.setLocation(x, y);
	}

	public void centerThisDialog(JDialog window) {
		// Get the size of thescreen
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		// Determine the new location of the window
		int w = window.getSize().width;
		int h = window.getSize().height;
		int x = (dim.width - w) / 2;
		int y = (dim.height - h) / 2;
		// Move the window
		window.setLocation(x, y);
	}
}
