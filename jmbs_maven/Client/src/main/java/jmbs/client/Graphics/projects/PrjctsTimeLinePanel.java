package jmbs.client.Graphics.projects;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.LayoutStyle.ComponentPlacement;

import jmbs.client.ClientRequests;
import jmbs.client.CurrentUser;
import jmbs.client.ServerConnection;
import jmbs.client.Graphics.MsgPanel;
import jmbs.client.Graphics.SayToUser;
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
	private JComboBox comboBox;
	private JTextArea textArea;
	private static int SELECTED_PROJECT_ID = -1;
	private static int CURRENT_SELECTED_COMBX_INDEX = -1;
	private String newMsgStr;
	private ArrayList<Message> currentMsgList = null;

	/**
	 * Create the panel.
	 */
	public PrjctsTimeLinePanel() {
		setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		add(panel, BorderLayout.SOUTH);

		JButton btnSend = new JButton("Send");
		btnSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (textArea.getText().length() > 0
						&& textArea.getText().length() <= 599) {
					newMsgStr = textArea.getText();

					Message m = new Message(CurrentUser.get(), newMsgStr,
							new Timestamp(Calendar.getInstance()
									.getTimeInMillis()));

					Integer getIdMsg = 0;
					getIdMsg = ClientRequests
							.addMessage(m, SELECTED_PROJECT_ID);
					System.out.println("" + getIdMsg);
					if (!getIdMsg.equals(-1)) {
						putMessage(new MsgPanel(new Message(CurrentUser.get(),
								textArea.getText(), new Timestamp(Calendar
										.getInstance().getTimeInMillis()))));
						setLastIdMsg(getIdMsg);
						textArea.setText("");
						setVisible(false);
					}

				} else {
					SayToUser
							.warning(
									"Message can't be sent",
									"Your message can't be sent with "
											+ textArea.getText().length()
											+ "characters, the maximum characters numbers is 600!");
				}
			}
		});

		JLabel lblSendNewMessage = new JLabel("Send New Message:");

		JScrollPane scrollPane_1 = new JScrollPane();
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(gl_panel
				.createParallelGroup(Alignment.LEADING)
				.addGroup(
						Alignment.TRAILING,
						gl_panel.createSequentialGroup()
								.addContainerGap()
								.addGroup(
										gl_panel.createParallelGroup(
												Alignment.TRAILING)
												.addComponent(
														scrollPane_1,
														Alignment.LEADING,
														GroupLayout.DEFAULT_SIZE,
														348, Short.MAX_VALUE)
												.addGroup(
														gl_panel.createSequentialGroup()
																.addComponent(
																		lblSendNewMessage)
																.addPreferredGap(
																		ComponentPlacement.RELATED,
																		145,
																		Short.MAX_VALUE)
																.addComponent(
																		btnSend,
																		GroupLayout.PREFERRED_SIZE,
																		80,
																		GroupLayout.PREFERRED_SIZE)))
								.addContainerGap()));
		gl_panel.setVerticalGroup(gl_panel
				.createParallelGroup(Alignment.LEADING)
				.addGroup(
						gl_panel.createSequentialGroup()
								.addContainerGap()
								.addGroup(
										gl_panel.createParallelGroup(
												Alignment.BASELINE)
												.addComponent(lblSendNewMessage)
												.addComponent(
														btnSend,
														GroupLayout.PREFERRED_SIZE,
														21,
														GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(scrollPane_1,
										GroupLayout.DEFAULT_SIZE, 90,
										Short.MAX_VALUE).addContainerGap()));

		textArea = new JTextArea();
		scrollPane_1.setViewportView(textArea);
		textArea.setLineWrap(true);
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

		comboBox = new JComboBox();
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println(comboBox.getSelectedIndex() + ":"
						+ comboBox.getSelectedItem());
				if (comboBox.getSelectedIndex() != CURRENT_SELECTED_COMBX_INDEX) {
					CURRENT_SELECTED_COMBX_INDEX = comboBox.getSelectedIndex();
					SELECTED_PROJECT_ID = getCurrentProjectId(comboBox
							.getSelectedItem().toString());
					updateTLPanel();
				}
			}
		});

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
			CURRENT_SELECTED_COMBX_INDEX = 0;
			SELECTED_PROJECT_ID = getCurrentProjectId(prjctsName[0]);
			updateTLPanel();
		} else {
			prjctsName = new String[1];
			prjctsName[0] = "";
		}

		comboBox.setModel(new DefaultComboBoxModel(prjctsName));
		topPrjctsTLPanel.add(comboBox, BorderLayout.CENTER);

		JButton btnRefresh = new JButton("");
		btnRefresh.setIcon(new ImageIcon(getClass().getResource(
				"/img/refresh_ico.png")));
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				checkNewMessages();
			}
		});
		topPrjctsTLPanel.add(btnRefresh, BorderLayout.EAST);
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

	private int getCurrentProjectId(String prjctName) {
		int retVal = -1;
		for (Project p : CurrentUser.getProjects()) {
			if (p.getName().equals(prjctName)) {
				retVal = p.getId();
				break;
			}
		}
		return retVal;
	}

	private void updateTLPanel() {
		setLastIdMsg(0);
		tlPanel.removeAll();
		currentMsgList = ClientRequests.getLastetProjectTL(CurrentUser.getId(),
				idLastMessage, ServerConnection.maxReceivedMsgs,
				SELECTED_PROJECT_ID);
		if (currentMsgList != null) {
			putList(currentMsgList);
		}
		tlPanel.updateUI();
	}

	public void checkNewMessages() {
		currentMsgList = ClientRequests.getLastetProjectTL(CurrentUser.getId(),
				idLastMessage, ServerConnection.maxReceivedMsgs,
				SELECTED_PROJECT_ID);
		putList(currentMsgList);
	}
}
