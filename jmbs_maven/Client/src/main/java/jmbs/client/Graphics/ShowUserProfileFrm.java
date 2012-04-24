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

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

import jmbs.client.SysConf;
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
	public ShowUserProfileFrm(User u) {
		setResizable(false);
		setTitle(u.getFullName());
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setAlwaysOnTop(true);
		setLocationByPlatform(true);
		// setBounds(100, 100, 320, 300);
		setSize(320, 300);
		new SysConf().centerThisFrame(this);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JLabel lblFullName = new JLabel(u.getFullName());
		lblFullName.setFont(new Font("Lucida Grande", Font.BOLD, 16));

		JLabel lblFollowing = new JLabel("Following: 188");
		lblFollowing.setFont(new Font("Lucida Grande", Font.PLAIN, 14));

		JLabel lblFollowers = new JLabel("Followers:  222");
		lblFollowers.setFont(new Font("Lucida Grande", Font.PLAIN, 14));

		JLabel lblMessages = new JLabel("Messages:   245");

		JLabel lblAboutUser = new JLabel("About " + u.getFname());

		JPanel panel = new JPanel();
		panel.setBackground(Color.GRAY);

		JToggleButton tglbtnFollow = new JToggleButton("Follow");

		JEditorPane dtrpnThisIsA = new JEditorPane();
		dtrpnThisIsA
				.setText("this is a text about this user , he is a user he do this he did that and he is...");
		dtrpnThisIsA.setEditable(false);

		JButton btnClose = new JButton("Close");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane
				.setHorizontalGroup(gl_contentPane
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								gl_contentPane
										.createSequentialGroup()
										.addGroup(
												gl_contentPane
														.createParallelGroup(
																Alignment.LEADING)
														.addGroup(
																gl_contentPane
																		.createSequentialGroup()
																		.addContainerGap()
																		.addGroup(
																				gl_contentPane
																						.createParallelGroup(
																								Alignment.TRAILING)
																						.addComponent(
																								dtrpnThisIsA,
																								Alignment.LEADING,
																								GroupLayout.DEFAULT_SIZE,
																								298,
																								Short.MAX_VALUE)
																						.addComponent(
																								lblMessages,
																								Alignment.LEADING)
																						.addComponent(
																								lblAboutUser,
																								Alignment.LEADING)
																						.addGroup(
																								Alignment.LEADING,
																								gl_contentPane
																										.createSequentialGroup()
																										.addGroup(
																												gl_contentPane
																														.createParallelGroup(
																																Alignment.LEADING)
																														.addComponent(
																																lblFollowing)
																														.addComponent(
																																lblFollowers)
																														.addGroup(
																																gl_contentPane
																																		.createSequentialGroup()
																																		.addComponent(
																																				lblFullName)
																																		.addPreferredGap(
																																				ComponentPlacement.RELATED)
																																		.addComponent(
																																				tglbtnFollow)))
																										.addPreferredGap(
																												ComponentPlacement.RELATED,
																												50,
																												Short.MAX_VALUE)
																										.addComponent(
																												panel,
																												GroupLayout.PREFERRED_SIZE,
																												76,
																												GroupLayout.PREFERRED_SIZE))))
														.addGroup(
																gl_contentPane
																		.createSequentialGroup()
																		.addGap(116)
																		.addComponent(
																				btnClose)))
										.addContainerGap()));
		gl_contentPane
				.setVerticalGroup(gl_contentPane
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								gl_contentPane
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												gl_contentPane
														.createParallelGroup(
																Alignment.LEADING,
																false)
														.addGroup(
																gl_contentPane
																		.createSequentialGroup()
																		.addGroup(
																				gl_contentPane
																						.createParallelGroup(
																								Alignment.BASELINE)
																						.addComponent(
																								lblFullName)
																						.addComponent(
																								tglbtnFollow))
																		.addPreferredGap(
																				ComponentPlacement.UNRELATED)
																		.addComponent(
																				lblFollowing)
																		.addPreferredGap(
																				ComponentPlacement.UNRELATED)
																		.addComponent(
																				lblFollowers))
														.addComponent(
																panel,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.DEFAULT_SIZE,
																Short.MAX_VALUE))
										.addPreferredGap(
												ComponentPlacement.UNRELATED)
										.addComponent(lblMessages)
										.addPreferredGap(
												ComponentPlacement.UNRELATED)
										.addComponent(lblAboutUser)
										.addPreferredGap(
												ComponentPlacement.RELATED)
										.addComponent(dtrpnThisIsA,
												GroupLayout.PREFERRED_SIZE, 73,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												ComponentPlacement.RELATED, 11,
												Short.MAX_VALUE)
										.addComponent(btnClose)));
		contentPane.setLayout(gl_contentPane);
		setVisible(true);
	}
}
