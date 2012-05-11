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

package jmbs.client.gui.projects;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import jmbs.client.ClientRequests;
import jmbs.client.CurrentUser;
import jmbs.client.dataTreatment.FramesConf;
import jmbs.client.gui.others.SayToUser;
import jmbs.common.Project;

public class CreateProject extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4065905172379487703L;
	private JPanel contentPane;
	private JTextField textField;
	private JComboBox comboBox_status;
	private JComboBox comboBox_privacy;
	private JComboBox comboBox_delete;
	private JComboBox comboBox_edition;

	/**
	 * Create the frame.
	 */
	public CreateProject(String projectName) {
		FramesConf.centerThisFrame(this);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);

		JLabel lblProjectName = new JLabel("Project Name: ");
		panel.add(lblProjectName);

		textField = new JTextField();
		textField.setText(projectName);
		panel.add(textField);
		textField.setColumns(10);

		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(null);

		JLabel lblStatus = new JLabel("Status: ");
		lblStatus.setBounds(96, 35, 47, 16);
		panel_1.add(lblStatus);

		comboBox_status = new JComboBox();
		comboBox_status.setBounds(149, 30, 258, 27);
		comboBox_status.setModel(new DefaultComboBoxModel(new String[] {
				"Closed", "Opened" }));
		panel_1.add(comboBox_status);

		JLabel lblEdition = new JLabel("Edition: ");
		lblEdition.setBounds(91, 67, 52, 16);
		panel_1.add(lblEdition);

		comboBox_edition = new JComboBox();
		comboBox_edition.setBounds(149, 62, 258, 27);
		comboBox_edition.setModel(new DefaultComboBoxModel(new String[] {
				"Disable", "Enable" }));
		panel_1.add(comboBox_edition);

		JLabel lblDeleteMessages = new JLabel("Delete Messages: ");
		lblDeleteMessages.setBounds(30, 99, 113, 16);
		panel_1.add(lblDeleteMessages);

		comboBox_delete = new JComboBox();
		comboBox_delete.setBounds(149, 94, 258, 27);
		comboBox_delete.setModel(new DefaultComboBoxModel(new String[] {
				"Disable", "Enable" }));
		panel_1.add(comboBox_delete);

		JLabel lblPrivacy = new JLabel("Privacy: ");
		lblPrivacy.setBounds(91, 131, 52, 16);
		panel_1.add(lblPrivacy);

		comboBox_privacy = new JComboBox();
		comboBox_privacy.setBounds(149, 126, 258, 27);
		comboBox_privacy.setModel(new DefaultComboBoxModel(new String[] {
				"Private", "Public" }));
		panel_1.add(comboBox_privacy);

		JPanel panel_5 = new JPanel();
		contentPane.add(panel_5, BorderLayout.SOUTH);

		JButton btnCreate = new JButton("Create");
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				create();
			}
		});
		panel_5.add(btnCreate);
		setVisible(true);
	}

	private void create() {
		int created = -1;

		int activation = comboBox_status.getSelectedIndex() == 0 ? Project.STATUS_CLOSED
				: Project.STATUS_OPENED;
		boolean edit = comboBox_edition.getSelectedIndex() == 0 ? Project.DISABLE_EDIT
				: Project.ENABLE_EDIT;
		boolean supress = comboBox_delete.getSelectedIndex() == 0 ? Project.DISABLE_DELETE
				: Project.ENABLE_DELETE;
		boolean privacy = comboBox_privacy.getSelectedIndex() == 0 ? Project.PRIVACY_PRIVATE
				: Project.PRIVACY_PUBLIC;

		created = ClientRequests.createProject(textField.getText(),
				CurrentUser.getId(), activation, edit, supress, privacy);
		System.out.println(created);
		if (created != -1) {
			dispose();
			SayToUser.success("Successed", "The project " + textField.getText()
					+ " has been created");
		} else {
			SayToUser.warning("Warning", "The project " + textField.getText()
					+ " can't be created, please try again ");
		}
	}
}
