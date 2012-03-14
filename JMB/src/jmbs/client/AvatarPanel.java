package jmbs.client;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class AvatarPanel extends JPanel {

	/**
	 * a panel to display the profile picture of users!
	 */
	private static final long serialVersionUID = -8055653215659767292L;

	/**
	 * Create the panel.
	 */
	public AvatarPanel() {
		
		JLabel imgLabel = new JLabel(new ImageIcon("avatar.jpg")); 
		imgLabel.setBounds(0, 0, 96, 96);
		
		GroupLayout gl_imgPanel = new GroupLayout(this);
		gl_imgPanel.setHorizontalGroup(
			gl_imgPanel.createParallelGroup(Alignment.TRAILING)
				.addComponent(imgLabel, GroupLayout.PREFERRED_SIZE, 69, Short.MAX_VALUE)
		);
		gl_imgPanel.setVerticalGroup(
			gl_imgPanel.createParallelGroup(Alignment.LEADING)
				.addComponent(imgLabel, GroupLayout.PREFERRED_SIZE, 67, Short.MAX_VALUE)
		);
		this.setLayout(gl_imgPanel);
	}

}
