package jmbs.client.Graphics.projects;

import java.awt.Component;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JRadioButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.UIManager;

import jmbs.common.Project;

public class PrjctAdministration extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -397969605352614394L;
	private JPopupMenu popupMenu;

	/**
	 * Create the panel.
	 */
	public PrjctAdministration(Project p) {
		setBorder(UIManager
				.getBorder("List.sourceListSelectionBackgroundPainter"));

		JLabel lblProjectname = new JLabel(p.getName());
		lblProjectname.setFont(new Font("Dialog", Font.BOLD, 14));
		lblProjectname.setEnabled(true);

		JLabel lblCreatedOn = new JLabel("Created On: "
				+ new SimpleDateFormat("dd/MM/yyyy HH:mm").format(p
						.getTimestamp()));

		JLabel lblParticipants = new JLabel("participants: "
				+ p.getUsers().size());

		JButton btnClose = new JButton("Admin");
		btnClose.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				popupMenu.show(e.getComponent(), e.getX(), e.getY());
			}
		});

		popupMenu = new JPopupMenu();
		addPopup(this, popupMenu);

		JMenu mnEditStatus = new JMenu("Edit Status");
		popupMenu.add(mnEditStatus);

		JRadioButton rdbtnActivate = new JRadioButton("Activate");
		mnEditStatus.add(rdbtnActivate);

		JMenuItem mntmDesactivate = new JMenuItem("Desactivate");
		mnEditStatus.add(mntmDesactivate);

		JMenu mnUsers = new JMenu("Users");
		popupMenu.add(mnUsers);

		JMenuItem mntmShowParticipants = new JMenuItem("Show Participants");
		mnUsers.add(mntmShowParticipants);

		JMenuItem mntmInviteUser = new JMenuItem("Invite User");
		mnUsers.add(mntmInviteUser);

		JMenuItem mntmRemoveUser = new JMenuItem("Remove User");
		mnUsers.add(mntmRemoveUser);
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout
				.setHorizontalGroup(groupLayout
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								groupLayout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												groupLayout
														.createParallelGroup(
																Alignment.TRAILING)
														.addComponent(
																lblProjectname,
																GroupLayout.DEFAULT_SIZE,
																438,
																Short.MAX_VALUE)
														.addGroup(
																groupLayout
																		.createSequentialGroup()
																		.addGroup(
																				groupLayout
																						.createParallelGroup(
																								Alignment.LEADING)
																						.addComponent(
																								lblParticipants)
																						.addComponent(
																								lblCreatedOn,
																								GroupLayout.PREFERRED_SIZE,
																								165,
																								GroupLayout.PREFERRED_SIZE))
																		.addGap(87)
																		.addComponent(
																				btnClose,
																				GroupLayout.PREFERRED_SIZE,
																				107,
																				GroupLayout.PREFERRED_SIZE)
																		.addGap(79)))
										.addContainerGap()));
		groupLayout
				.setVerticalGroup(groupLayout
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								groupLayout
										.createSequentialGroup()
										.addContainerGap()
										.addComponent(lblProjectname)
										.addPreferredGap(
												ComponentPlacement.RELATED)
										.addGroup(
												groupLayout
														.createParallelGroup(
																Alignment.LEADING)
														.addGroup(
																groupLayout
																		.createSequentialGroup()
																		.addComponent(
																				lblCreatedOn)
																		.addPreferredGap(
																				ComponentPlacement.RELATED)
																		.addComponent(
																				lblParticipants))
														.addComponent(
																btnClose,
																GroupLayout.DEFAULT_SIZE,
																40,
																Short.MAX_VALUE))
										.addContainerGap()));
		setLayout(groupLayout);

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
