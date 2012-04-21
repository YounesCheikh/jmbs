package jmbs.client.Graphics.projects;

import javax.swing.JPanel;

import jmbs.client.CurrentUser;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ParticipationsPrjcstPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5578775335517479170L;
	PrjctsListPanel prticptionPanel;
	/**
	 * Create the panel.
	 */
	public ParticipationsPrjcstPanel() {
		setLayout(new BorderLayout(0, 0));
		
		JPanel prtcptionTopPanel = new JPanel();
		add(prtcptionTopPanel, BorderLayout.NORTH);
		prtcptionTopPanel.setLayout(new BorderLayout(0, 0));
		
		final JLabel lblProjectsFound = new JLabel("Projects Found: "+CurrentUser.getProjects().size());
		prtcptionTopPanel.add(lblProjectsFound, BorderLayout.WEST);
		
		JButton btnRefresh = new JButton("Refresh");
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				prticptionPanel.putList(CurrentUser.getProjects());
				lblProjectsFound.setText(("Projects Found: "+CurrentUser.getProjects().size()));
			}
		});
		prtcptionTopPanel.add(btnRefresh, BorderLayout.EAST);
		
		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, BorderLayout.CENTER);
		
		prticptionPanel = new PrjctsListPanel();
		scrollPane.setViewportView(prticptionPanel);
		prticptionPanel.putList(CurrentUser.getProjects());
	}

}
