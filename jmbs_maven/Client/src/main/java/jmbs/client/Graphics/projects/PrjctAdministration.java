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
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;

import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JRadioButton;
import javax.swing.JSeparator;
import javax.swing.border.TitledBorder;

import jmbs.client.ClientRequests;
import jmbs.client.CurrentUser;
import jmbs.client.Graphics.images.ImagePanel;
import jmbs.common.Project;
import net.miginfocom.swing.MigLayout;

public class PrjctAdministration extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -397969605352614394L;
	private JPopupMenu popupMenu;
	private ImagePanel panel;
	private PrjctParameters pParamters;

	/**
	 * Create the panel.
	 */
	public PrjctAdministration(final Project p) {
		pParamters = new PrjctParameters(p);
		ButtonGroup prjctStatus = new ButtonGroup();
		p.setUsers(ClientRequests.getProjectUsers(p.getId()));
		setBorder(new TitledBorder(null, "", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));

		JLabel lblProjectname = new JLabel(p.getName());
		lblProjectname.setFont(new Font("Dialog", Font.BOLD, 14));
		lblProjectname.setEnabled(true);
		setLayout(new BorderLayout(0, 0));

		popupMenu = new JPopupMenu();
		addPopup(this, popupMenu);

		JMenu mnEditStatus = new JMenu("Edit Status");
		popupMenu.add(mnEditStatus);

		JRadioButton rdbtnActivate = new JRadioButton("Open");
		rdbtnActivate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ClientRequests.openProject(CurrentUser.getId(), p.getId());
				panel.setImage("/img/project_opened.png");
				popupMenu.setVisible(false);
			}
		});
		mnEditStatus.add(rdbtnActivate);

		JRadioButton rdbtnDesactivate = new JRadioButton("Close");
		rdbtnDesactivate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ClientRequests.closeProject(CurrentUser.getId(), p.getId());
				panel.setImage("/img/project_closed.png");
				popupMenu.setVisible(false);
			}
		});
		mnEditStatus.add(rdbtnDesactivate);
		prjctStatus.add(rdbtnActivate);
		prjctStatus.add(rdbtnDesactivate);
		JMenu mnUsers = new JMenu("Users");
		popupMenu.add(mnUsers);

		JMenuItem mntmShowParticipants = new JMenuItem("Show Participants");
		mnUsers.add(mntmShowParticipants);

		JMenuItem mntmInviteUser = new JMenuItem("Invite User");
		mnUsers.add(mntmInviteUser);

		JMenuItem mntmRemoveUser = new JMenuItem("Remove User");
		mnUsers.add(mntmRemoveUser);

		JSeparator separator = new JSeparator();
		popupMenu.add(separator);

		JMenuItem mntmParamters = new JMenuItem("Paramters");
		mntmParamters.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pParamters.setVisible(true);
				popupMenu.setVisible(false);
			}
		});
		popupMenu.add(mntmParamters);

		panel = new ImagePanel("/img/project_opened.png");

		panel.setToolTipText("Opened Project");
		if (p.getStatus() == Project.STATUS_CLOSED) {
			panel = new ImagePanel("/img/project_closed.png");
			panel.setToolTipText("Closed Project");
		}
		panel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				popupMenu.show(panel, e.getX(), e.getY());
			}
		});

		panel.setPreferredSize(new Dimension(48, 68));
		add(lblProjectname, BorderLayout.NORTH);
		add(panel, BorderLayout.EAST);

		JPanel panel_1 = new JPanel();
		add(panel_1, BorderLayout.CENTER);

		JLabel lblCreatedOn = new JLabel("Created On: "
				+ new SimpleDateFormat("dd/MM/yyyy HH:mm").format(p
						.getTimestamp()));

		JLabel lblParticipants = new JLabel("participants: "
				+ p.getUsers().size());
		panel_1.setLayout(new MigLayout("", "[298px]", "[16px][16px]"));
		panel_1.add(lblCreatedOn, "cell 0 0,growx,aligny top");
		panel_1.add(lblParticipants, "cell 0 1,growx,aligny top");

		if (p.getStatus() == Project.STATUS_OPENED) {
			rdbtnActivate.setSelected(true);
			rdbtnDesactivate.setSelected(false);
		} else {
			rdbtnActivate.setSelected(false);
			rdbtnDesactivate.setSelected(true);
		}

	}

	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}

			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}

			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
}
