package jmbs.client.Graphics.projects;

import java.awt.Component;
import java.util.ArrayList;

import javax.swing.JPanel;

import jmbs.common.Project;
import net.miginfocom.swing.MigLayout;

public class PrjctsListPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4588219624977229095L;

	/**
	 * Create the panel.
	 */
	public PrjctsListPanel() {
		setLayout(new MigLayout("", "[grow 80,fill]", "[]"));
	}

	public void putProject(Component obj) {
		// put new element and go to next row
		this.add(obj, "wrap", 0);
		this.updateUI();
	}

	public void putList(ArrayList<Project> projectList) {
		removeAll();
		updateUI();
		if (projectList != null) {
			for (Project p : projectList) {
				putProject(new ProjectPanel(p));
			}
		}
	}
}
