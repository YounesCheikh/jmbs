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
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import jmbs.client.ClientRequests;
import jmbs.client.CurrentUser;
import jmbs.client.DataTreatment.AutoRefresh;
import jmbs.client.Graphics.MainWindow;
import jmbs.common.Project;

public class ParticipationsPrjcstPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5578775335517479170L;
	private static PrjctsListPanel prticptionPanel;
	private static JLabel lblProjectsFound;

	/**
	 * Create the panel.
	 */
	public ParticipationsPrjcstPanel(final MainWindow mw) {
		setLayout(new BorderLayout(0, 0));

		JPanel prtcptionTopPanel = new JPanel();
		add(prtcptionTopPanel, BorderLayout.NORTH);
		prtcptionTopPanel.setLayout(new BorderLayout(0, 0));

		lblProjectsFound = new JLabel("Projects Found: "
				+ CurrentUser.getProjects().size());
		prtcptionTopPanel.add(lblProjectsFound, BorderLayout.WEST);

		JButton btnRefresh = new JButton("Refresh");
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateList();
			}
		});
		prtcptionTopPanel.add(btnRefresh, BorderLayout.EAST);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane
				.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		add(scrollPane, BorderLayout.CENTER);

		prticptionPanel = new PrjctsListPanel();
		scrollPane.setViewportView(prticptionPanel);
		prticptionPanel.putList(CurrentUser.getProjects());
		
		AutoRefresh autoRefresh = new AutoRefresh(mw);
		autoRefresh.participationsRefresh(600); // 10 minutes
	}

	public static void updateList() {
		ArrayList<Project> pList = new ArrayList<Project>();
		pList = ClientRequests.getUserProjects(CurrentUser.getId());
		if (pList != null) {
			if (!pList.equals(CurrentUser.getProjects()))
				CurrentUser.get().setProjects(pList);
		}
		prticptionPanel.putList(CurrentUser.getProjects());
		lblProjectsFound.setText(("Projects Found: " + CurrentUser
				.getProjects().size()));
	}

}
