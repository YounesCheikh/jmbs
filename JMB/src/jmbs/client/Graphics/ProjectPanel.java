package jmbs.client.Graphics;

import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Font;
import javax.swing.JToggleButton;
import javax.swing.JSeparator;

import jmbs.common.Project;

public class ProjectPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1623471343709328488L;

	/**
	 * Create the panel.
	 */
	public ProjectPanel(Project p) {
		
		JLabel lblName = new JLabel("The Project Name");
		lblName.setFont(new Font("Lucida Grande", Font.BOLD, 14));
		
		JLabel lblCreator = new JLabel("Created by 'The Creator'");
		
		JLabel lblParticipants = new JLabel("Participants: 100");
		
		JToggleButton tglbtnParticipate = new JToggleButton("Participate");
		
		JSeparator separator = new JSeparator();
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, groupLayout.createParallelGroup(Alignment.TRAILING)
							.addGroup(groupLayout.createSequentialGroup()
								.addContainerGap()
								.addComponent(separator, GroupLayout.DEFAULT_SIZE, 298, Short.MAX_VALUE))
							.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
								.addContainerGap()
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
									.addComponent(lblName, GroupLayout.DEFAULT_SIZE, 298, Short.MAX_VALUE)
									.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
										.addComponent(lblCreator, GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
										.addPreferredGap(ComponentPlacement.UNRELATED)
										.addComponent(lblParticipants, GroupLayout.PREFERRED_SIZE, 106, GroupLayout.PREFERRED_SIZE)))))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(91)
							.addComponent(tglbtnParticipate)))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblName)
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblCreator)
						.addComponent(lblParticipants))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(tglbtnParticipate)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(separator, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(1))
		);
		setLayout(groupLayout);

	}
}
