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

import net.miginfocom.swing.MigLayout;

import jmbs.common.Project;

public class SearchProjectPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3188199974551155568L;

	/**
	 * Create the panel.
	 */
	public SearchProjectPanel() {
		setLayout(new MigLayout("", "[grow 80,fill]", "[]"));
		
		
	}

	public void putProject(Component obj) {
		// put new element and go to next row
		this.add(obj, "wrap", 0);
		this.updateUI();
	}

	public void putList(ArrayList<Project> projectList) {
		if (projectList != null) {
			for (Project p : projectList) {
				putProject(new ProjectPanel(p));
			}
		}
	}

}
