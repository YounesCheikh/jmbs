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
import javax.swing.UIManager.LookAndFeelInfo;
import jmbs.client.dataTreatment.SetEnv;
import jmbs.client.gui.ConnectionFrame;
import jmbs.client.gui.MainWindow;
import jmbs.client.gui.others.SayToUser;

/**
 * @author <a href="mailto:younes.cheikh@gmail.com">Younes CHEIKH</a>
 * @author Benjamin Babic
 * @since 06-05-2012
 * @version 1.0
 */

public class MainClient {
	private static MainWindow window;
	private static ConnectionFrame cf;
	private static SysConf setMacConf = new SysConf();

	
	/**
	 * @param args Ignored
	 */
	public static void main(String[] args) {
		
		/*
		 * setting the properties
		 * # the security policy
		 * # the codebase for rmi
		 * # the hostname for rmi server
		 */
		System.setProperty("java.security.policy", ClassLoader
				.getSystemResource("security.policy").toString());
		System.setProperty("java.rmi.server.codebase", ClassLoader
				.getSystemResource("jmbs/common/").toString());
		System.setProperty("java.rmi.server.hostname", "localhost");
		
		/*
		 * set the user Interface Manager
		 */
		setMacConf.setUIMngr();
		/*
		 * If the current OS is not a mac, use the Nimbus lookandfeel theme as default (if exists).
		 */
		if (!setMacConf.isMac()) {
			try {
				boolean themeMacFound = false;
				boolean themeNimbusFound = false;
				for (LookAndFeelInfo info : UIManager
						.getInstalledLookAndFeels()) {
					//System.out.println(info.getName());
					if ("Mac OS X".equals(info.getName())) {
						themeMacFound = true;
						break;
					} else if ("Nimbus".equals(info.getName())) {
						themeNimbusFound = true;
						break;
					}
				}
				if (themeMacFound)
					UIManager.setLookAndFeel("Mac OS X");
				else if (themeNimbusFound)
					UIManager.setLookAndFeel("Nimbus");
			} catch (Exception e) {
				// look and feel.
			}
		}

		/*
		 * Initilize the SayToUser Class
		 */
		new SayToUser();
		/*
		 * Set the environment (Create the default directories of not exist).
		 */
		new SetEnv();
		
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() { // Run the Application
				cf = new ConnectionFrame(window);
				cf.setVisible(true);
			}
		});
	}
}
