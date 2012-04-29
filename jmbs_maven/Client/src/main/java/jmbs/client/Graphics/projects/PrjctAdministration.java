package jmbs.client.Graphics.projects;

import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;

import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JRadioButton;
import javax.swing.JSeparator;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.TitledBorder;

import jmbs.client.ClientRequests;
import jmbs.client.CurrentUser;
import jmbs.client.Graphics.ImagePanel;
import jmbs.common.Project;

public class PrjctAdministration extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -397969605352614394L;
	private JPopupMenu popupMenu;
	private ImagePanel panel;

	/**
	 * Create the panel.
	 */
	public PrjctAdministration(final Project p) {
		ButtonGroup prjctStatus = new ButtonGroup();
		p.setUsers(ClientRequests.getProjectUsers(p.getId()));
		setBorder(new TitledBorder(null, "", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));

		JLabel lblProjectname = new JLabel(p.getName());
		lblProjectname.setFont(new Font("Dialog", Font.BOLD, 14));
		lblProjectname.setEnabled(true);

		JLabel lblCreatedOn = new JLabel("Created On: "
				+ new SimpleDateFormat("dd/MM/yyyy HH:mm").format(p
						.getTimestamp()));

		JLabel lblParticipants = new JLabel("participants: "
				+ p.getUsers().size());

		popupMenu = new JPopupMenu();
		addPopup(this, popupMenu);

		JMenu mnEditStatus = new JMenu("Edit Status");
		popupMenu.add(mnEditStatus);

		JRadioButton rdbtnActivate = new JRadioButton("Activate");
		rdbtnActivate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ClientRequests.openProject(CurrentUser.getId(), p.getId());
				panel.setImage("/img/projectOn.png");
				popupMenu.setVisible(false);
			}
		});
		mnEditStatus.add(rdbtnActivate);

		JRadioButton rdbtnDesactivate = new JRadioButton("Desactivate");
		rdbtnDesactivate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ClientRequests.closeProject(CurrentUser.getId(), p.getId());
				panel.setImage("/img/projectOff.png");
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
		popupMenu.add(mntmParamters);

		panel = new ImagePanel("/img/projectOn.png");
		panel.setToolTipText("Opened Project");
		if (p.getStatus() == Project.STATUS_CLOSED) {
			panel = new ImagePanel("/img/projectOff.png");
			panel.setToolTipText("Closed Project");
		}
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(lblProjectname, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblParticipants)
								.addComponent(lblCreatedOn, GroupLayout.PREFERRED_SIZE, 165, GroupLayout.PREFERRED_SIZE))
							.addGap(134)
							.addComponent(panel, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE)))
					.addGap(2))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblProjectname)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblCreatedOn)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblParticipants)
							.addContainerGap())
						.addComponent(panel, GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE)))
		);
		setLayout(groupLayout);

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
