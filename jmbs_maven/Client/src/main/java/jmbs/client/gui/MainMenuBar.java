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

package jmbs.client.gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.*;
import jmbs.client.CurrentUser;
import jmbs.client.SysConf;
import jmbs.client.cache.CacheRequests;
import jmbs.client.dataTreatment.SetEnv;
import jmbs.client.gui.messages.TimeLinePanel;
import jmbs.client.gui.others.AboutFrame;
import jmbs.client.gui.others.Preferences;

/**
 * The main MenuBar
 * @author <a href="http://cyounes.com/">Younes CHEIKH</a>
 * @author Benjamin Babic
 */
public class MainMenuBar extends JMenuBar {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2765541073906585914L;
	private static TimeLinePanel timelinepanel;
	private static AboutFrame about;
	private static JFrame frmJmbsClient;
	private MyApplicationListener listener;
	private static Preferences prfrm;
	private boolean isMac = new SysConf().isMac() ? true : false;

	/**
         * create the menu bar
         * @param mw The main window
         */
	public MainMenuBar(final MainWindow mw) {

		if (isMac) {

			listener = new MyApplicationListener();

		}

		frmJmbsClient = mw.getFrame();
		timelinepanel = mw.getTLPanel();
		about = mw.getAbout();
		mw.getMsgListTL();
		prfrm = mw.getPreferencesFrame();

		setBackground(Color.LIGHT_GRAY);
		// frmJmbsClient.setJMenuBar(this);
		// frmJmbsClient.getContentPane().add(menuBar, BorderLayout.NORTH);

		JMenu mnFile = new JMenu("File");
		mnFile.setBackground(Color.LIGHT_GRAY);
		add(mnFile);

		JMenuItem mntmNewMessage = new JMenuItem("New Message");
		mntmNewMessage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mw.updateNewMsgPane();
			}
		});
		mnFile.add(mntmNewMessage);

		JMenuItem mntmRefresh = new JMenuItem("Refresh");
		mntmRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mw.checkNewMessages(timelinepanel.getLastIdMsg());
			}
		});
		mnFile.add(mntmRefresh);

		JSeparator separator_4 = new JSeparator();
		mnFile.add(separator_4);

		JMenuItem mntmAboutJmbs = new JMenuItem("About JMBS");
		mntmAboutJmbs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				about.setVisible(true);
			}
		});
		mnFile.add(mntmAboutJmbs);

		JSeparator separator = new JSeparator();
		mnFile.add(separator);

		JMenuItem mntmPreferences = new JMenuItem("Preferences");
		mntmPreferences.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				prfrm.setVisible(true);
			}
		});
		mnFile.add(mntmPreferences);

		JMenuItem mntmEmptyCache = new JMenuItem("Empty cache");
		mntmEmptyCache.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CacheRequests.removeAllIdentities();
				CacheRequests.removeAllMsgs();
				SetEnv.removeFile(new File(SetEnv.CACHE_PIC_PATH));
				new SetEnv();
			}
		});
		mnFile.add(mntmEmptyCache);

		JSeparator separator_2 = new JSeparator();
		mnFile.add(separator_2);

		JMenuItem mntmHide = new JMenuItem("Hide");
		mntmHide.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmJmbsClient.setState(JFrame.ICONIFIED);
			}
		});
		mnFile.add(mntmHide);

		JMenuItem mntmDisconnect = new JMenuItem("Disconnect");
		mntmDisconnect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				disconnect();
			}
		});
		mnFile.add(mntmDisconnect);

		JSeparator separator_1 = new JSeparator();
		mnFile.add(separator_1);

		JMenuItem mntmQuit = new JMenuItem("Quit");
		mntmQuit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		mnFile.add(mntmQuit);

		JMenu mnActivities = new JMenu("Activities");
		mnActivities.setBackground(Color.LIGHT_GRAY);
		add(mnActivities);

		JMenuItem mntmUsers = new JMenuItem("Users");
		mntmUsers.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mw.updateMainPanel(4);
			}
		});
		mnActivities.add(mntmUsers);

		JMenuItem mntmProjects = new JMenuItem("Projects");
		mntmProjects.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mw.updateMainPanel(2);
			}
		});
		// mntmProjects.setEnabled(false);
		mnActivities.add(mntmProjects);
	}

        /**
         * This method is unused currently
         * @return Application listener
         */
	public MyApplicationListener getApplicationListener() {
		return listener;
	}

        /**
         * Disconnect Current User with the following steps:<br />
         * 
         * <br /> * dispose all the visible frames
         * <br /> * close the cache db connection
         * <br /> * disconnect from server
         * <br /> * Show the connection frame
         * 
         */
	public static void disconnect() {
		if (about.isVisible())
			about.dispose();
		if (prfrm.isVisible())
			prfrm.dispose();
		frmJmbsClient.dispose();
		CacheRequests.closeConnection();
		CurrentUser.disconnect();
		ConnectionFrame cf = new ConnectionFrame(new MainWindow());
		cf.setVisible(true);
	}

}
