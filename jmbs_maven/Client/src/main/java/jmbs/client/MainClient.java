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

import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import jmbs.client.Graphics.ConnectionFrame;
import jmbs.client.Graphics.MainWindow;
import jmbs.client.Graphics.SayToUser;

public class MainClient {
	private static MainWindow window;
	/**
	 * The Connection Window
	 */
	private static ConnectionFrame cf;

	private static SysConf setMacConf = new SysConf();

	public static void main(String[] args) {
		System.setProperty("java.security.policy", ClassLoader
				.getSystemResource("security.policy").toString());
		System.setProperty("java.rmi.server.codebase", ClassLoader
				.getSystemResource("jmbs/common/").toString());
		setMacConf.setUIMngr();
		if (!setMacConf.isMac()) {
			try {
				boolean themeMacFound = false;
				boolean themeNimbusFound = false;
				for (LookAndFeelInfo info : UIManager
						.getInstalledLookAndFeels()) {
					System.out.println(info.getName());
					if ("Mac OS X".equals(info.getName())) {
						themeMacFound = true;
						break;
					} else if ("Nimbus".equals(info.getName())) {
						themeNimbusFound = true;
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

		new SayToUser();
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				cf = new ConnectionFrame(window);
				// AWTUtilities.setWindowOpacity(cf, 0.99f);
				cf.setVisible(true);
			}
		});
	}
}
