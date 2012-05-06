/*
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
 */

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
import javax.swing.ScrollPaneConstants;

import jmbs.client.ClientRequests;
import jmbs.client.CurrentUser;
import jmbs.client.ServerConnection;
import jmbs.client.DataTreatment.AutoRefresh;
import jmbs.client.Graphics.messages.MsgPanel;
import jmbs.client.Graphics.others.SayToUser;
import jmbs.common.Message;
import jmbs.common.Project;
import net.miginfocom.swing.MigLayout;

public class PrjctsTimeLinePanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -477407305599641329L;
	private static int idLastMessage = 0;
	private static JPanel tlPanel;
	private static JComboBox comboBox;
	private static JTextArea textArea;
	private static int SELECTED_PROJECT_ID = -1;
	private static int CURRENT_SELECTED_COMBX_INDEX = -1;
	private String newMsgStr;
	private static ArrayList<Message> currentMsgList = null;
	private static JButton btnSend;

	/**
	 * Create the panel.
	 */
	public PrjctsTimeLinePanel() {
		setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		add(panel, BorderLayout.SOUTH);

		btnSend = new JButton("Send");
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
		scrollPane
				.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
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

		updatePrjctList();

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
		
		AutoRefresh autoRefresh = new AutoRefresh();
		autoRefresh.prjctsTimeLineRefresh(60);
	}

	public static void putMessage(Component obj) {
		// put new element and go to next row
		tlPanel.add(obj, "wrap", 0);
		tlPanel.updateUI();
	}

	public static void putList(ArrayList<Message> msgList) {
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

	public static void setLastIdMsg(int id) {
		idLastMessage = id;
	}

	private static int getCurrentProjectId(String prjctName) {
		int retVal = -1;
		for (Project p : CurrentUser.getProjects()) {
			if (p.getName().equals(prjctName)) {
				retVal = p.getId();
				break;
			}
		}
		return retVal;
	}

	private static void updateTLPanel() {
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

	public static void checkNewMessages() {
		currentMsgList = ClientRequests.getLastetProjectTL(CurrentUser.getId(),
				idLastMessage, ServerConnection.maxReceivedMsgs,
				SELECTED_PROJECT_ID);
		putList(currentMsgList);
	}

	public static void updatePrjctList() {
		String[] prjctsName = null;
		ArrayList<Project> prjctList;
		prjctList = CurrentUser.getProjects();
		int nbPrjct = 0;
		for (Project p : prjctList)
			if (p.getStatus() == Project.STATUS_OPENED)
				nbPrjct++;
		prjctsName = new String[nbPrjct];
		if (!prjctList.isEmpty() && nbPrjct > 0) {
			int i = 0;
			for (Project p : prjctList) {
				if (p.getStatus() == Project.STATUS_OPENED) {
					prjctsName[i] = p.getName();
					i++;
				}
			}
			CURRENT_SELECTED_COMBX_INDEX = 0;
			SELECTED_PROJECT_ID = getCurrentProjectId(prjctsName[0]);
			btnSend.setEnabled(true);
			textArea.setEnabled(true);
		} else {
			prjctsName = new String[1];
			prjctsName[0] = "";
			SELECTED_PROJECT_ID = -1;
			btnSend.setEnabled(false);
			textArea.setEnabled(false);
		}
		updateTLPanel();
		comboBox.setModel(new DefaultComboBoxModel(prjctsName));
		comboBox.updateUI();
	}
}
