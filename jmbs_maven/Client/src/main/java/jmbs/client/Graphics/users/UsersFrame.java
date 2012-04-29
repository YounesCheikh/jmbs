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

package jmbs.client.Graphics.users;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;

import jmbs.client.ClientRequests;
import jmbs.client.CurrentUser;
import jmbs.client.SysConf;
import jmbs.common.User;
import net.miginfocom.swing.MigLayout;

public class UsersFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4074857690054435001L;
	private JPanel contentPane;
	private JTextField nameTextField;
	private UsrLstPanel resultSearchPanel;
	private UsrLstPanel flwngUsrsLstPanel;
	private UsrLstPanel flwrLstPanel;
	ArrayList<User> flwrList = new ArrayList<User>();
	private JCheckBox byNameCheckBox;
	private JCheckBox chckbxByForeName;
	private JLabel lblYourFollowers;
	private JLabel lblPeopleYouFollow;

	/**
	 * Create the frame.
	 */
	public UsersFrame() {
		setResizable(false);
		setAlwaysOnTop(true);
		// setLocationRelativeTo(new MainWindow().getFrame());
		setLocationRelativeTo(nameTextField);
		setTitle("Users management ");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		// setBounds(100, 100, 460, 420);
		setSize(460, 420);
		SysConf.centerThisFrame(this);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		contentPane.add(tabbedPane, BorderLayout.CENTER);

		JPanel searchUsersPanel = new JPanel();
		tabbedPane.addTab("Search", null, searchUsersPanel, null);
		searchUsersPanel.setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		searchUsersPanel.add(panel, BorderLayout.NORTH);

		JLabel lblSearchForUser = new JLabel("Search for user: ");

		nameTextField = new JTextField();
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
		byNameCheckBox = new JCheckBox("By name");

		chckbxByForeName = new JCheckBox("By forename");

		JButton btnSearch = new JButton("Search");
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

		panel.setLayout(new MigLayout("", "[102px][124px][6px][112px][85px]",
				"[29px][23px]"));
		panel.add(lblSearchForUser, "cell 0 0,alignx left,growy");
		panel.add(nameTextField, "cell 1 0 3 1,grow");
		panel.add(btnSearch, "cell 4 0,alignx left,aligny top");
		panel.add(byNameCheckBox, "cell 1 1,growx,aligny top");
		panel.add(chckbxByForeName, "cell 3 1,alignx left,aligny top");

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
				flwngUsrsLstPanel.removeAll();
				flwngUsrsLstPanel.putList(CurrentUser.getFollows());
				flwngUsrsLstPanel.repaint();
				flwngUsrsLstPanel.validate();
				flwngUsrsLstPanel.updateUI();
				lblPeopleYouFollow.setText("People you follow: "
						+ CurrentUser.getFollows().size());
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
				flwrLstPanel.removeAll();
				flwrList = ClientRequests.getFollowers(CurrentUser.get());
				flwrLstPanel.putList(flwrList);
				lblYourFollowers.setText("Your Followers: " + flwrList.size());
				flwrLstPanel.repaint();
				flwrLstPanel.validate();
				flwrLstPanel.updateUI();
			}
		});
		panel_1.add(btnRefresh_1, BorderLayout.EAST);
	}
}
