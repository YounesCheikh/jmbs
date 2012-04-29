package jmbs.client.Graphics.projects;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import jmbs.client.CurrentUser;

public class PrjectTabbedPane extends JTabbedPane {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5628095027207654919L;

	/**
	 * Create the panel.
	 */
	public PrjectTabbedPane(JPanel projectsPanel) {
		super(JTabbedPane.TOP);

		PrjctsTimeLinePanel prjctTLPanel = new PrjctsTimeLinePanel();
		addTab("TimeLine",
				new ImageIcon(getClass().getResource(
						"/img/timeline_project.png")), prjctTLPanel,
				"Write and read Message into/From Project");

		SearchProjectPanel searchPrjctPanel = new SearchProjectPanel();
		addTab("Search For Project",
				new ImageIcon(getClass().getResource("/img/searchproject.png")),
				searchPrjctPanel, "Search for new project");

		ParticipationsPrjcstPanel participationPrjctPanel = new ParticipationsPrjcstPanel();
		addTab("Participation",
				new ImageIcon(getClass().getResource("/img/participation.png")),
				participationPrjctPanel, "Projects where you are participated");

		MyProjectsPanel myPrjctPanel = new MyProjectsPanel();
		if (CurrentUser.getAccesslevel() == 0
				|| CurrentUser.getAccesslevel() == 1) {
			addTab("My Projects",
					new ImageIcon(getClass().getResource("/img/myprojects.png")),
					myPrjctPanel, "Projects Admin Managements");
		}
		GroupLayout gl_projectsPanel = new GroupLayout(projectsPanel);
		gl_projectsPanel.setHorizontalGroup(gl_projectsPanel
				.createParallelGroup(Alignment.LEADING).addGroup(
						gl_projectsPanel
								.createSequentialGroup()
								.addComponent(this, GroupLayout.DEFAULT_SIZE,
										440, Short.MAX_VALUE).addGap(0)));
		gl_projectsPanel.setVerticalGroup(gl_projectsPanel.createParallelGroup(
				Alignment.LEADING).addGroup(
				gl_projectsPanel
						.createSequentialGroup()
						.addComponent(this, GroupLayout.DEFAULT_SIZE, 997,
								Short.MAX_VALUE).addGap(7)));
		projectsPanel.setLayout(gl_projectsPanel);

	}

}
