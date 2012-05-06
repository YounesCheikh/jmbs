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

package jmbs.client.Graphics.projects;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;

import jmbs.common.Project;

public class PrjctParameters extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4080965748209117430L;
	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public PrjctParameters(Project p) {
		setTitle("Project Parameters: " + p.getName());
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 550, 380);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.PAGE_END);
		panel.setLayout(new BorderLayout(0, 0));

		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		panel.add(btnCancel, BorderLayout.WEST);

		JPanel panel_1 = new JPanel();
		panel.add(panel_1, BorderLayout.EAST);
		panel_1.setLayout(new BorderLayout(0, 0));

		JButton btnOk = new JButton("OK");
		panel_1.add(btnOk, BorderLayout.EAST);

		JButton btnApply = new JButton("Apply");
		panel_1.add(btnApply, BorderLayout.WEST);
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		contentPane.add(tabbedPane, BorderLayout.CENTER);

		JScrollPane usersScrollPane = new JScrollPane();
		usersScrollPane
				.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		tabbedPane.addTab("Users Managements", null, usersScrollPane, null);
		JScrollPane managersScrollPane = new JScrollPane();
		tabbedPane.addTab("Project Managers", null, managersScrollPane, null);

		JPanel configPanel = new JPanel();
		tabbedPane.addTab("Configurations", null, configPanel, null);
	}
}
