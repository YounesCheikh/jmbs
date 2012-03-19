package jmbs.client.Graphics;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class AvatarPanel extends JPanel {

	/**
	 * a panel to display the profile picture of users!
	 * this feature is disabled currently.
	 */
	private static final long serialVersionUID = -8055653215659767292L;
	private JLabel imgLabel ;
	/**
	 * Create the panel.
	 */
	public AvatarPanel(String imgUrl) {
		
		imgLabel = new JLabel(new ImageIcon(imgUrl)); 
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
