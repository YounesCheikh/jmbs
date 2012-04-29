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

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;

import jmbs.client.ClientRequests;
import jmbs.common.Message;
import jmbs.common.Project;
import javax.swing.ScrollPaneConstants;

public class SearchProjectPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3188199974551155568L;
	private JTextField textField;
	private static PrjctsListPanel foundedPrjctsPanel;

	/**
	 * Create the panel.
	 */
	public SearchProjectPanel() {
		setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		add(panel, BorderLayout.NORTH);
		panel.setLayout(new BorderLayout(0, 0));

		JLabel lblFind = new JLabel("Find Projects:");
		panel.add(lblFind, BorderLayout.WEST);

		textField = new JTextField();
		panel.add(textField, BorderLayout.CENTER);
		textField.setColumns(10);

		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArrayList<Project> plist = new ArrayList<Project>();
				plist = ClientRequests.searchForProject(textField.getText());
				foundedPrjctsPanel.removeAll();
				foundedPrjctsPanel.updateUI();
				if (plist != null)
				{
					ArrayList<Project> pListTmp = new ArrayList<Project>();
					for(Project p: plist) {
						if(p.getStatus()==Project.STATUS_OPENED)
							pListTmp.add(p);
					}
					foundedPrjctsPanel.putList(pListTmp);
				}
			}
		});
		panel.add(btnSearch, BorderLayout.EAST);
		foundedPrjctsPanel = new PrjctsListPanel();
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setViewportBorder(new SoftBevelBorder(BevelBorder.LOWERED,
				null, null, null, null));
		scrollPane.updateUI();
		scrollPane.setAutoscrolls(true);
		add(scrollPane, BorderLayout.CENTER);
		scrollPane.setViewportView(foundedPrjctsPanel);
	}

}
