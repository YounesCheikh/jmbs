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

package jmbs.client.Graphics;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;

import jmbs.client.SysConf;

public class ConnectionFrame extends JFrame {

	/**
	 * Display a frame contain the connection panel
	 */
	private static final long serialVersionUID = 6941716821811760066L;

	/**
	 * Create the frame.
	 * 
	 * @param MainWindow
	 *            , need this to display it after a successed connection
	 */
	public ConnectionFrame(MainWindow w) {
		setResizable(false);
		setLocationRelativeTo(null);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				dispose();
				System.exit(0);
			}
		});
		this.setTitle("Connect to JMBS!");
		this.setSize(430, 430);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// this.setLocationRelativeTo(null);
		new SysConf().centerThisFrame(this);

		ConnectionPanel cp = new ConnectionPanel(w, this);
		this.getContentPane().add(cp);
		// this.setVisible(true);
	}

}
