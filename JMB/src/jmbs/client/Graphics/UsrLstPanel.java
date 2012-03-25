package jmbs.client.Graphics;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;

import javax.swing.JPanel;

import jmbs.client.CurrentUser;
import jmbs.common.User;

public class UsrLstPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -346658870300155170L;
	private GridBagConstraints gbc;
	private int linesNB = 0;

	/**
	 * Create the panel.
	 */
	public UsrLstPanel() {
		gbc = new GridBagConstraints();
		gbc.anchor = GridBagConstraints.NORTH;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.ipady = 0;

		GridBagLayout gbl_timeLinePanel = new GridBagLayout();
		gbl_timeLinePanel.columnWidths = new int[] { 0, 0 };
		gbl_timeLinePanel.rowHeights = new int[] { 0, 0 };
		gbl_timeLinePanel.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gbl_timeLinePanel.rowWeights = new double[] { 1.0, Double.MAX_VALUE };
		this.setLayout(gbl_timeLinePanel);

	}

	public void putUser(Component obj) {
		gbc.gridx = 0;
		gbc.gridy = linesNB;
		this.add(obj, gbc);
		// timeLinePanel.validate();
		linesNB++;
		// this.validate();
		this.updateUI();
	}

	public void putList(ArrayList<User> userList) {
		linesNB = 0;
		if (userList != null) {
			for (User u : userList) {
				// don't display the current user in the list
				if (!u.equals(new CurrentUser().get()))
					putUser(new FlwUsrPanel(u));
			}
			if (userList.size() < 4)
				for (int i = 0; i < 20; i++) {
					putUser(new JPanel());
				}
		}
	}
}
