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
		this.add(obj, "wrap",0);
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
