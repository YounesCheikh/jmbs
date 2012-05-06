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

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 * @author <a href="mailto:younes.cheikh@gmail.com">Younes CHEIKH</a>
 * @author Benjamin Babic
 * @since 06-05-2012
 * @version 1.0
 */
public class SysConf {

	private static boolean macSys = false;
	private static boolean alreadyTested = false;

	/**
	 * This class setup the mac environment , it allows the JVM to use the mac
	 * menu
	 */
	public SysConf() {

	}

	/**
	 * @param appName
	 *            the application name ((JMBS-CLIENT))
	 */
	private static void macSetup(String appName) {
		String os = System.getProperty("os.name").toLowerCase();
		macSys = os.startsWith("mac os x");
		if (!macSys)
			return;

		System.setProperty("apple.laf.useScreenMenuBar", "true");
		System.setProperty("com.apple.mrj.application.apple.menu.about.name",
				appName);

	}

	/**
	 * Set the User interface manager
	 */
	public void setUIMngr() {
		if (!alreadyTested) {
			alreadyTested = true;
			macSetup("JMBS");
			try {
				UIManager.setLookAndFeel(UIManager
						.getSystemLookAndFeelClassName());
			} catch (ClassNotFoundException e) {
				// Ignore
			} catch (InstantiationException e) {
				// Ignore
			} catch (IllegalAccessException e) {
				// Ignore
			} catch (UnsupportedLookAndFeelException e) {
				// Ignore
			}
		}
	}

	/**
	 * @return true if the current OS is Mac
	 */
	public boolean isMac() {
		return macSys;
	}

}
