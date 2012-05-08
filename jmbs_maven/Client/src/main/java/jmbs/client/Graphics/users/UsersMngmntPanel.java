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

package jmbs.client.Graphics.users;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;

import jmbs.client.ClientRequests;
import jmbs.client.CurrentUser;
import jmbs.client.DataTreatment.AutoRefresh;
import jmbs.client.Graphics.MainWindow;
import jmbs.common.User;

public class UsersMngmntPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4560886266133006166L;
	private JPanel contentPane;
	private JTextField nameTextField;
	private UsrLstPanel resultSearchPanel;
	private static UsrLstPanel flwngUsrsLstPanel;
	private static UsrLstPanel flwrLstPanel;
	static ArrayList<User> flwrList = new ArrayList<User>();
	private JCheckBox byNameCheckBox;
	private JCheckBox chckbxByForeName;
	private static JLabel lblYourFollowers;
	private static JLabel lblPeopleYouFollow;

	/**
	 * Create the panel.
	 */
	public UsersMngmntPanel(final MainWindow mw) {
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);

		JPanel searchUsersPanel = new JPanel();
		tabbedPane.addTab("Search", null, searchUsersPanel, null);
		searchUsersPanel.setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		searchUsersPanel.add(panel, BorderLayout.NORTH);
		panel.setLayout(new BorderLayout(0, 0));

		JPanel panel_2 = new JPanel();
		panel.add(panel_2, BorderLayout.NORTH);
		panel_2.setLayout(new BorderLayout(0, 0));

		nameTextField = new JTextField();
		panel_2.add(nameTextField, BorderLayout.CENTER);
		nameTextField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resultSearchPanel.removeAll();
				ArrayList<User> usersList = null;
				usersList = ClientRequests.searchUser(nameTextField.getText(),
						0);
				if (usersList != null) {
					resultSearchPanel.putList(usersList);
				}
				resultSearchPanel.repaint();
				resultSearchPanel.validate();
				resultSearchPanel.updateUI();
			}
		});
		nameTextField.setColumns(10);

		JButton btnSearch = new JButton("Search");
		panel_2.add(btnSearch, BorderLayout.EAST);
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resultSearchPanel.removeAll();
				ArrayList<User> usersList = null;
				int searchBy = 0;
				// Comparaison:
				// if checkBox by name selected search by = 1
				searchBy = byNameCheckBox.isSelected() ? searchBy + 1
						: searchBy;
				// if checkBox by forename selected searchby = seachby +2
				searchBy = chckbxByForeName.isSelected() ? searchBy + 2
						: searchBy;
				usersList = ClientRequests.searchUser(nameTextField.getText(),
						searchBy);
				if (usersList != null) {
					resultSearchPanel.putList(usersList);
				}
				resultSearchPanel.repaint();
				resultSearchPanel.validate();
				resultSearchPanel.updateUI();
			}
		});

		JPanel panel_3 = new JPanel();
		panel.add(panel_3, BorderLayout.CENTER);
		byNameCheckBox = new JCheckBox("By name");
		panel_3.add(byNameCheckBox);

		chckbxByForeName = new JCheckBox("By forename");
		panel_3.add(chckbxByForeName);

		JPanel searchContainerPanel = new JPanel();
		searchUsersPanel.add(searchContainerPanel, BorderLayout.CENTER);
		searchContainerPanel.setLayout(new BorderLayout(0, 0));

		JScrollPane resultScrollPane = new JScrollPane();
		resultScrollPane.updateUI();
		searchContainerPanel.add(resultScrollPane);

		resultSearchPanel = new UsrLstPanel();
		resultScrollPane.setViewportView(resultSearchPanel);

		JPanel followingPanel = new JPanel();
		tabbedPane.addTab("Following", null, followingPanel, null);
		followingPanel.setLayout(new BorderLayout(0, 0));

		JScrollPane flwngScrollPane = new JScrollPane();
		flwngScrollPane
				.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		followingPanel.add(flwngScrollPane, BorderLayout.CENTER);

		flwngUsrsLstPanel = new UsrLstPanel();
		flwngUsrsLstPanel.putList(CurrentUser.getFollows());
		flwngScrollPane.setViewportView(flwngUsrsLstPanel);

		JPanel topFlwngPanel = new JPanel();
		followingPanel.add(topFlwngPanel, BorderLayout.NORTH);
		topFlwngPanel.setLayout(new BorderLayout(0, 0));

		lblPeopleYouFollow = new JLabel("People you follow: "
				+ CurrentUser.getFollows().size());
		lblPeopleYouFollow.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		topFlwngPanel.add(lblPeopleYouFollow);

		JButton btnRefresh = new JButton("Refresh");
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateFollowingList();
			}
		});
		topFlwngPanel.add(btnRefresh, BorderLayout.EAST);

		JPanel followersPanel = new JPanel();
		tabbedPane.addTab("Followers", null, followersPanel, null);
		followersPanel.setLayout(new BorderLayout(0, 0));

		JScrollPane flwrScrollPane = new JScrollPane();
		followersPanel.add(flwrScrollPane);

		flwrLstPanel = new UsrLstPanel();
		flwrList = ClientRequests.getFollowers(CurrentUser.get());
		if (flwrList == null) {
			flwrList = new ArrayList<User>();
		}
		flwrLstPanel.putList(flwrList);

		flwrScrollPane.setViewportView(flwrLstPanel);

		JPanel panel_1 = new JPanel();
		followersPanel.add(panel_1, BorderLayout.NORTH);
		panel_1.setLayout(new BorderLayout(0, 0));

		lblYourFollowers = new JLabel("Your Followers: " + flwrList.size());
		panel_1.add(lblYourFollowers);

		JButton btnRefresh_1 = new JButton("Refresh");
		btnRefresh_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateFollowersList();
			}
		});
		setLayout(new BorderLayout(0, 0));
		panel_1.add(btnRefresh_1, BorderLayout.EAST);
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.add(tabbedPane);
		add(contentPane);
		
		AutoRefresh autoRefresh = new AutoRefresh(mw);
		autoRefresh.followingRefresh(120);
		autoRefresh.followersRefresh(120);
	}
	
	public static void updateFollowingList() {
		flwngUsrsLstPanel.removeAll();
		flwngUsrsLstPanel.putList(CurrentUser.getFollows());
		flwngUsrsLstPanel.repaint();
		flwngUsrsLstPanel.validate();
		flwngUsrsLstPanel.updateUI();
		lblPeopleYouFollow.setText("People you follow: "
				+ CurrentUser.getFollows().size());
	}
	
	public static void updateFollowersList() {
		flwrLstPanel.removeAll();
		flwrList = ClientRequests.getFollowers(CurrentUser.get());
		flwrLstPanel.putList(flwrList);
		lblYourFollowers.setText("Your Followers: " + flwrList.size());
		flwrLstPanel.repaint();
		flwrLstPanel.validate();
		flwrLstPanel.updateUI();
	}

}
