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

package jmbs.client.Graphics.projects;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JToggleButton;
import javax.swing.LayoutStyle.ComponentPlacement;

import jmbs.client.ClientRequests;
import jmbs.client.CurrentUser;
import jmbs.common.Project;

public class ProjectPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1623471343709328488L;

	/**
	 * Create the panel.
	 */
	public ProjectPanel(final Project p) {

		JLabel lblName = new JLabel(p.getName());
		lblName.setFont(new Font("Lucida Grande", Font.BOLD, 14));

		JLabel lblCreator = new JLabel("Created by '"
				+ p.getOwner().getFullName() + "'");

		JLabel lblParticipants = new JLabel("Participants: "
				+ p.getUsers().size());

		final JToggleButton tglbtnParticipate = new JToggleButton("Participate");
		if (p.getStatus()==Project.STATUS_CLOSED) {
			tglbtnParticipate.setEnabled(false);
			tglbtnParticipate.setText("Closed");
		}
		tglbtnParticipate.setSelected(CurrentUser.getProjects().contains(p));
		tglbtnParticipate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (CurrentUser.getProjects().contains(p)) {
					ClientRequests.unParticipate(CurrentUser.getId(), p.getId());
					tglbtnParticipate.setSelected(false);
					CurrentUser.getProjects().remove(p);
				} else {
					ClientRequests.participate(CurrentUser.getId(), p.getId());
					tglbtnParticipate.setSelected(true);
					CurrentUser.getProjects().add(p);
				}
				PrjctsTimeLinePanel.updatePrjctList();
			}
		});

		JSeparator separator = new JSeparator();
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(82)
					.addComponent(separator, GroupLayout.PREFERRED_SIZE, 142, GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblName, GroupLayout.DEFAULT_SIZE, 298, Short.MAX_VALUE)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblCreator)
								.addComponent(lblParticipants, GroupLayout.PREFERRED_SIZE, 93, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED, 44, Short.MAX_VALUE)
							.addComponent(tglbtnParticipate, GroupLayout.PREFERRED_SIZE, 104, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(5)
					.addComponent(lblName)
					.addGap(7)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addComponent(lblCreator)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblParticipants))
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addComponent(tglbtnParticipate, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
							.addGap(1)))
					.addGap(0)
					.addComponent(separator, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(28, Short.MAX_VALUE))
		);
		setLayout(groupLayout);

	}
}
