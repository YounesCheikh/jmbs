package jmbs.client.Graphics.projects;

import java.awt.BorderLayout;
import java.awt.Component;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.LayoutStyle.ComponentPlacement;

import jmbs.client.CurrentUser;
import jmbs.client.Graphics.MsgPanel;
import jmbs.common.Message;
import jmbs.common.Project;
import net.miginfocom.swing.MigLayout;

public class PrjctsTimeLinePanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -477407305599641329L;
	private static int idLastMessage = 0;
	private JPanel tlPanel;

	/**
	 * Create the panel.
	 */
	public PrjctsTimeLinePanel() {
		setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		add(panel, BorderLayout.SOUTH);

		JTextArea textArea = new JTextArea();

		JButton btnSend = new JButton("Send");

		JLabel lblSendNewMessage = new JLabel("Send New Message:");
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(gl_panel
				.createParallelGroup(Alignment.TRAILING)
				.addGroup(
						gl_panel.createSequentialGroup()
								.addContainerGap()
								.addGroup(
										gl_panel.createParallelGroup(
												Alignment.LEADING)
												.addGroup(
														gl_panel.createSequentialGroup()
																.addComponent(
																		textArea,
																		GroupLayout.DEFAULT_SIZE,
																		357,
																		Short.MAX_VALUE)
																.addPreferredGap(
																		ComponentPlacement.RELATED)
																.addComponent(
																		btnSend))
												.addComponent(lblSendNewMessage))
								.addContainerGap()));
		gl_panel.setVerticalGroup(gl_panel
				.createParallelGroup(Alignment.LEADING)
				.addGroup(
						gl_panel.createSequentialGroup()
								.addContainerGap()
								.addGroup(
										gl_panel.createParallelGroup(
												Alignment.LEADING)
												.addGroup(
														Alignment.TRAILING,
														gl_panel.createSequentialGroup()
																.addComponent(
																		lblSendNewMessage)
																.addPreferredGap(
																		ComponentPlacement.RELATED)
																.addComponent(
																		textArea,
																		GroupLayout.PREFERRED_SIZE,
																		90,
																		GroupLayout.PREFERRED_SIZE)
																.addContainerGap())
												.addGroup(
														Alignment.TRAILING,
														gl_panel.createSequentialGroup()
																.addComponent(
																		btnSend)
																.addGap(36)))));
		panel.setLayout(gl_panel);

		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, BorderLayout.CENTER);

		tlPanel = new JPanel();
		scrollPane.setViewportView(tlPanel);
		tlPanel.setLayout(new MigLayout("", "[grow 80,fill]", "[]"));

		JPanel topPrjctsTLPanel = new JPanel();
		add(topPrjctsTLPanel, BorderLayout.NORTH);
		topPrjctsTLPanel.setLayout(new BorderLayout(0, 0));

		JLabel lblChooseProject = new JLabel("Choose Project:");
		topPrjctsTLPanel.add(lblChooseProject, BorderLayout.WEST);

		JComboBox comboBox = new JComboBox();

		String[] prjctsName = null;
		ArrayList<Project> prjctList;
		prjctList = CurrentUser.getProjects();
		prjctsName = new String[prjctList.size()];
		if (!prjctList.isEmpty()) {
			int i = 0;
			for (Project p : prjctList) {
				prjctsName[i] = p.getName();
				i++;
			}
		} else {
			prjctsName = new String[1];
			prjctsName[0] = "";
		}

		comboBox.setModel(new DefaultComboBoxModel(prjctsName));
		topPrjctsTLPanel.add(comboBox, BorderLayout.CENTER);

		JButton btnOk = new JButton("OK");
		topPrjctsTLPanel.add(btnOk, BorderLayout.EAST);
	}

	public void putMessage(Component obj) {
		// put new element and go to next row
		tlPanel.add(obj, "wrap", 0);
		tlPanel.updateUI();
	}

	public void putList(ArrayList<Message> msgList) {
		if (msgList != null) {
			for (Message m : msgList) {
				putMessage(new MsgPanel(m));
				idLastMessage = m.getId();
			}
		}
	}

	public int getLastIdMsg() {
		return idLastMessage;
	}

	public void setLastIdMsg(int id) {
		idLastMessage = id;
	}
}
