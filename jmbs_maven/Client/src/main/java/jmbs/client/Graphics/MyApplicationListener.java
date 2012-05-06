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

package jmbs.client.Graphics;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.simplericity.macify.eawt.ApplicationEvent;
import org.simplericity.macify.eawt.ApplicationListener;

public class MyApplicationListener implements ApplicationListener {
	private JFrame f = new MainWindow().getFrame();

	private void handle(ApplicationEvent event, String message) {
		JOptionPane.showMessageDialog(f, message);
		event.setHandled(true);
	}

	public void handleAbout(ApplicationEvent event) {
		handle(event, "aboutAction");
	}

	public void handleOpenApplication(ApplicationEvent event) {
		// Ok, we know our application started
		// Not much to do about that..
	}

	public void handleOpenFile(ApplicationEvent event) {
		handle(event, "openFileInEditor: " + event.getFilename());
	}

	public void handlePreferences(ApplicationEvent event) {

		JFrame fr = new JFrame();
		fr.setTitle("Preferences");
		fr.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		fr.setSize(300, 300);
		fr.setLocationRelativeTo(null);
		fr.setVisible(true);
		handle(event, "preferencesAction");
	}

	public void handlePrintFile(ApplicationEvent event) {
		handle(event, "Sorry, printing not implemented");
	}

	public void handleQuit(ApplicationEvent event) {
		handle(event, "exitAction");
		System.exit(0);
	}

	public void handleReOpenApplication(ApplicationEvent event) {
		event.setHandled(true);
		f.setVisible(true);
	}
}
