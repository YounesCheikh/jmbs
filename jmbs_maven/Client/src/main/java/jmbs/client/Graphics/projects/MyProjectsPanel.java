package jmbs.client.Graphics.projects;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

import jmbs.client.ClientRequests;
import jmbs.client.CurrentUser;
import jmbs.client.Graphics.others.SayToUser;
import jmbs.common.Project;
import net.miginfocom.swing.MigLayout;

public class MyProjectsPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8278854644898217652L;
	private JTextField txtProjectName;
	JPanel prjctListPanel;
	private JCheckBox chckbxClosed;
	private JCheckBox chckbxOpened;

	/**
	 * Create the panel.
	 */
	public MyProjectsPanel() {
		setLayout(new BorderLayout(0, 0));
		JPanel topMyPrjctPanel = new JPanel();
		add(topMyPrjctPanel, BorderLayout.NORTH);
		topMyPrjctPanel.setLayout(new BorderLayout(0, 0));

		txtProjectName = new JTextField();
		txtProjectName.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {

			}

			@Override
			public void focusGained(FocusEvent e) {
				if (txtProjectName.getText().equals("Project Name...")) {
					txtProjectName.setText("");
					txtProjectName.setForeground(Color.black);
				}
			}
		});
		txtProjectName.setText("Project Name...");
		txtProjectName.setForeground(Color.LIGHT_GRAY);
		topMyPrjctPanel.add(txtProjectName, BorderLayout.CENTER);
		txtProjectName.setColumns(10);

		JLabel lblCreateNew = new JLabel("Create New:");
		topMyPrjctPanel.add(lblCreateNew, BorderLayout.WEST);

		JButton btnCreate = new JButton("Create");
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean created = false;
				created = (ClientRequests.createProject(
						txtProjectName.getText(), CurrentUser.getId()) != null);
				if (created)
					SayToUser.success("Successed", "The project "
							+ txtProjectName.getText() + " has been created");
				else
					SayToUser.error("Permission denied",
							"You don't have access to do this!");
			}
		});
		topMyPrjctPanel.add(btnCreate, BorderLayout.EAST);

		JPanel panel = new JPanel();
		topMyPrjctPanel.add(panel, BorderLayout.SOUTH);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JLabel lblShowProjects = new JLabel("Show Projects:");
		panel.add(lblShowProjects);

		chckbxClosed = new JCheckBox("Closed");
		panel.add(chckbxClosed);

		chckbxOpened = new JCheckBox("Active");
		panel.add(chckbxOpened);

		JButton btnRefresh = new JButton("Refresh");
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				putList(ClientRequests.getOwnedProjects(CurrentUser.getId()));
			}
		});
		panel.add(btnRefresh);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane
				.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		add(scrollPane, BorderLayout.CENTER);

		prjctListPanel = new JPanel();
		prjctListPanel.setLayout(new MigLayout("", "[]", "[]"));
		// ArrayList<Project> prjctList =
		// RemoteRequests.getUserProjects(CurrentUser.)
		putList(ClientRequests.getOwnedProjects(CurrentUser.getId()));
		scrollPane.setViewportView(prjctListPanel);

	}

	public void putProject(Component obj) {
		// put new element and go to next row
		prjctListPanel.add(obj, "wrap", 0);
		prjctListPanel.updateUI();
	}

	public void putList(ArrayList<Project> projectList) {
		if (projectList != null) {
			prjctListPanel.removeAll();
			prjctListPanel.updateUI();

			if (chckbxClosed.isSelected()) {
				for (Project p : projectList) {
					if (p.getStatus() == Project.STATUS_CLOSED)
						putProject(new PrjctAdministration(p));
				}
			}
			
			if (chckbxOpened.isSelected()) {
				for (Project p : projectList) {
					if (p.getStatus() == Project.STATUS_OPENED)
						putProject(new PrjctAdministration(p));
				}
			}

		}
	}

}
