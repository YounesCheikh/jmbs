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
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import jmbs.client.ClientRequests;
import jmbs.client.CurrentUser;
import jmbs.client.SysConf;
import jmbs.client.Graphics.images.ImagePanel;
import jmbs.common.User;

public class ShowUserProfileFrm extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3038200468706212679L;
	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public ShowUserProfileFrm(final User u) {
		setResizable(false);
		setTitle(u.getFullName());
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setAlwaysOnTop(true);
		setLocationByPlatform(true);
		// setBounds(100, 100, 320, 300);
		setSize(320, 300);
		SysConf.centerThisFrame(this);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JPanel aboutPanel = new JPanel();
		contentPane.add(aboutPanel, BorderLayout.CENTER);
		aboutPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
		aboutPanel.setLayout(new BorderLayout(0, 0));

		JEditorPane dtrpnThisIsA = new JEditorPane();
		aboutPanel.add(dtrpnThisIsA);
		dtrpnThisIsA
				.setText("this is a text about this user , he is a user he do this he did that and he is...");
		dtrpnThisIsA.setEditable(false);

		JLabel lblAboutUser = new JLabel("About " + u.getFname());
		aboutPanel.add(lblAboutUser, BorderLayout.NORTH);

		JPanel topPanel = new JPanel();
		contentPane.add(topPanel, BorderLayout.NORTH);
		topPanel.setLayout(new BorderLayout(0, 0));

		JPanel leftPanel = new JPanel();
		topPanel.add(leftPanel, BorderLayout.CENTER);
		leftPanel.setLayout(new BorderLayout(0, 0));

		JLabel lblFullName = new JLabel(u.getFullName());
		leftPanel.add(lblFullName, BorderLayout.NORTH);
		lblFullName.setFont(new Font("Lucida Grande", Font.BOLD, 16));

		JPanel panel_1 = new JPanel();
		leftPanel.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new GridLayout(3, 3, 0, 0));

		JLabel lblFollowing = new JLabel("Following: 188");
		lblFollowing.setAlignmentX(0.0f);
		lblFollowing.setAlignmentY(0.5f);
		panel_1.add(lblFollowing);
		lblFollowing.setFont(new Font("Lucida Grande", Font.PLAIN, 14));

		JLabel lblFollowers = new JLabel("Followers:  222");
		lblFollowers.setAlignmentX(0.0f);
		lblFollowers.setAlignmentY(1.5f);
		panel_1.add(lblFollowers);
		lblFollowers.setFont(new Font("Lucida Grande", Font.PLAIN, 14));

		JLabel lblMessages = new JLabel("Messages:   245");
		lblMessages.setAlignmentX(0.0f);
		lblMessages.setAlignmentY(2.5f);
		panel_1.add(lblMessages);

		JPanel rightPanel = new JPanel();
		topPanel.add(rightPanel, BorderLayout.EAST);
		rightPanel.setLayout(new BorderLayout(0, 0));

		ImagePanel panel = new ImagePanel(ClientRequests.convert(u.getPic()), 70, 70);
		panel.setPreferredSize(new Dimension(70, 70));
		rightPanel.add(panel, BorderLayout.CENTER);
		panel.setBackground(Color.GRAY);

		final JLabel btnFollow = new JLabel("");
		btnFollow.setHorizontalAlignment(SwingConstants.CENTER);
		// btnFollow.setFont(new Font("Lucida Grande", Font.BOLD, 13));

		btnFollow.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				if (!CurrentUser.get().equals(u)) {
					if (CurrentUser.getFollows().contains(u)) {
						btnFollow.setIcon(new ImageIcon(getClass().getResource(
								"/img/u_unfollow.png")));
						btnFollow.setToolTipText("unfollow");
					} else {
						btnFollow.setIcon(new ImageIcon(getClass().getResource(
								"/img/u_follow.png")));
						btnFollow.setToolTipText("follow");
					}
				}
			}

			@Override
			public void mouseExited(MouseEvent e) {
				if (!CurrentUser.get().equals(u)) {
					if (!CurrentUser.getFollows().contains(u)) {
						btnFollow.setIcon(new ImageIcon(getClass().getResource(
								"/img/u_follow_off.png")));
						btnFollow.setToolTipText("following");
					} else {
						btnFollow.setIcon(new ImageIcon(getClass().getResource(
								"/img/u_following.png")));
						btnFollow.setToolTipText("following");
					}
				}
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				if (!CurrentUser.get().equals(u)) {
					if (!CurrentUser.getFollows().contains(u)) {
						ClientRequests.follows(CurrentUser.getId(), u.getId());
						CurrentUser.getFollows().add(u);
						btnFollow.setIcon(new ImageIcon(getClass().getResource(
								"/img/u_following.png")));
						btnFollow.setToolTipText("following");
					} else {
						ClientRequests.unFollow(CurrentUser.getId(), u.getId());
						CurrentUser.getFollows().remove(u);
						btnFollow.setIcon(new ImageIcon(getClass().getResource(
								"/img/u_follow_off.png")));
						btnFollow.setToolTipText("follow");
					}
					UsersMngmntPanel.updateFollowingList();
				}
			}
		});

		if (CurrentUser.getFollows().contains(u)) {
			btnFollow.setIcon(new ImageIcon(getClass().getResource(
					"/img/u_following.png")));
			// btnFollow.setText("following");
		} else {
			if (CurrentUser.get().equals(u)) {
				btnFollow.setEnabled(false);
				// btnFollow.setText("");
			} else {
				btnFollow.setIcon(new ImageIcon(getClass().getResource(
						"/img/u_follow_off" + ".png")));
				btnFollow.setToolTipText("follow");
			}
		}

		// add(tglbtnFollow, BorderLayout.EAST);
		rightPanel.add(btnFollow, BorderLayout.SOUTH);

		JPanel panel_3 = new JPanel();
		panel_3.setPreferredSize(new Dimension(29, 0));
		rightPanel.add(panel_3, BorderLayout.WEST);

		JPanel panel_4 = new JPanel();
		panel_4.setPreferredSize(new Dimension(29, 0));
		rightPanel.add(panel_4, BorderLayout.EAST);

		JPanel panel_2 = new JPanel();
		contentPane.add(panel_2, BorderLayout.PAGE_END);

		JButton btnClose = new JButton("Close");
		panel_2.add(btnClose);
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		setVisible(true);
	}
}
