package jmbs.client.Graphics.projects;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import jmbs.client.ClientRequests;
import jmbs.client.CurrentUser;
import jmbs.client.DataTreatment.AutoRefresh;
import jmbs.common.Project;

public class ParticipationsPrjcstPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5578775335517479170L;
	private static PrjctsListPanel prticptionPanel;
	private static JLabel lblProjectsFound;

	/**
	 * Create the panel.
	 */
	public ParticipationsPrjcstPanel() {
		setLayout(new BorderLayout(0, 0));

		JPanel prtcptionTopPanel = new JPanel();
		add(prtcptionTopPanel, BorderLayout.NORTH);
		prtcptionTopPanel.setLayout(new BorderLayout(0, 0));

		lblProjectsFound = new JLabel("Projects Found: "
				+ CurrentUser.getProjects().size());
		prtcptionTopPanel.add(lblProjectsFound, BorderLayout.WEST);

		JButton btnRefresh = new JButton("Refresh");
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateList();
			}
		});
		prtcptionTopPanel.add(btnRefresh, BorderLayout.EAST);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane
				.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		add(scrollPane, BorderLayout.CENTER);

		prticptionPanel = new PrjctsListPanel();
		scrollPane.setViewportView(prticptionPanel);
		prticptionPanel.putList(CurrentUser.getProjects());
		
		AutoRefresh autoRefresh = new AutoRefresh();
		autoRefresh.participationsRefresh(600); // 10 minutes
	}

	public static void updateList() {
		ArrayList<Project> pList = new ArrayList<Project>();
		pList = ClientRequests.getUserProjects(CurrentUser.getId());
		if (pList != null) {
			if (!pList.equals(CurrentUser.getProjects()))
				CurrentUser.get().setProjects(pList);
		}
		prticptionPanel.putList(CurrentUser.getProjects());
		lblProjectsFound.setText(("Projects Found: " + CurrentUser
				.getProjects().size()));
	}

}
