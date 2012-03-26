package jmbs.client.Graphics;

import java.awt.Component;
import java.util.ArrayList;
import javax.swing.JPanel;
import jmbs.client.CurrentUser;
import jmbs.common.User;
import net.miginfocom.swing.MigLayout;

public class UsrLstPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -346658870300155170L;

	/**
	 * Create the panel.
	 */
	public UsrLstPanel() {
		setLayout(new MigLayout("", "[grow 80,fill]", "[]"));
	}

	public void putUser(Component obj) {
		// put new element and go to next row
		this.add(obj, "wrap");
		this.updateUI();
	}

	public void putList(ArrayList<User> userList) {
		if (userList != null) {
			for (User u : userList) {
				// don't display the current user in the list
				if (!u.equals(new CurrentUser().get()))
					putUser(new FlwUsrPanel(u));
			}
		}
	}
}
