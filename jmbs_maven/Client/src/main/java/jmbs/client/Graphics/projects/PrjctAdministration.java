package jmbs.client.Graphics.projects;

import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.Font;

import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;

import jmbs.common.Project;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JRadioButton;

public class PrjctAdministration extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -397969605352614394L;
	private  JPopupMenu popupMenu;
	/**
	 * Create the panel.
	 */
	public PrjctAdministration(Project p) {
		
		JLabel lblProjectname = new JLabel("ProjectName");
		lblProjectname.setFont(new Font("Dialog", Font.BOLD, 14));
		lblProjectname.setEnabled(true);
		
		JLabel lblCreatedOn = new JLabel("Created On: 12/12/2012");
		
		JLabel lblParticipants = new JLabel("participants: 1000");
		
		JButton btnClose = new JButton("Admin");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 //popupMenu.show(true);
			}
		});
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblProjectname, GroupLayout.DEFAULT_SIZE, 438, Short.MAX_VALUE)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblParticipants)
								.addComponent(lblCreatedOn, GroupLayout.PREFERRED_SIZE, 165, GroupLayout.PREFERRED_SIZE))
							.addGap(87)
							.addComponent(btnClose, GroupLayout.PREFERRED_SIZE, 107, GroupLayout.PREFERRED_SIZE)
							.addGap(79)))
					.addContainerGap())
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
							.addComponent(lblParticipants))
						.addComponent(btnClose, GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE))
					.addContainerGap())
		);
		
		popupMenu = new JPopupMenu();
		addPopup(btnClose, popupMenu);
		
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
