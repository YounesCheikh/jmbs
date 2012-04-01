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
		ArrayList<Project> plist = new ArrayList<Project>();
		plist.add(new Project(""));
		plist.add(new Project(""));
		plist.add(new Project(""));
		plist.add(new Project(""));
		plist.add(new Project(""));
		plist.add(new Project(""));
		plist.add(new Project(""));
		plist.add(new Project(""));
		plist.add(new Project(""));
		plist.add(new Project(""));
		plist.add(new Project(""));
		plist.add(new Project(""));
		plist.add(new Project(""));
		plist.add(new Project(""));
		plist.add(new Project(""));
		plist.add(new Project(""));
		plist.add(new Project(""));
		plist.add(new Project(""));
		putList(plist);
		
		
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
