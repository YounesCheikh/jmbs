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

package jmbs.client.gui.others;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSeparator;
import javax.swing.JSlider;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import jmbs.client.ServerConfig;
import jmbs.client.ServerConnection;
import jmbs.client.dataTreatment.FramesConf;
import jmbs.client.gui.MainWindow;

import com.sun.awt.AWTUtilities;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

@SuppressWarnings("restriction")
public class Preferences extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4493110241913884584L;
	private JPanel contentPane;
	private JLabel labelprecent;
	private JSlider slider;
	private JCheckBox chckbxTransparency;
	private JLabel lblThemes;
	private JRadioButton rdbtnDark;
	private JRadioButton rdbtnGray;
	private JRadioButton rdbtnLight;
	private JFrame frame = new MainWindow().getFrame();
	private static boolean colorChanged = false;
	private JLabel lblAdresseIpserver;
	private JLabel lblPort;
	private JTextField ipTextField;
	private JTextField portTextField;
	private JButton btnOk;
	private JButton btnApply;
	private JButton btnCancel_1;
	private JButton btnRestoreAsDefault;
	private JTextField limitedMsgTextField;
	private JPanel panel;
	private JPanel panel_1;
	private JPanel msgpanel;
	private JPanel panel_3;
	private JPanel panel_4;
	private JPanel panel_5;

	/**
	 * Create the frame.
	 */
	public Preferences() {
		setResizable(false);

		ButtonGroup groupDefaultOptions = new ButtonGroup();
		ButtonGroup groupThemes = new ButtonGroup();
		setTitle("JMBS: Preferences");

		setAlwaysOnTop(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 410, 360);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		contentPane.add(tabbedPane, BorderLayout.CENTER);
		FramesConf.centerThisFrame(this);

		JPanel generalPanel = new JPanel();
		tabbedPane.addTab("General",
				new ImageIcon(getClass().getResource("/img/General-Alt.png")),
				generalPanel, null);

		ServerConfig sconf = new ServerConfig();

		btnOk = new JButton("OK");
		btnOk.setEnabled(false);
		btnOk.setBounds(288, 253, 75, 29);

		btnApply = new JButton("Apply");
		btnApply.setEnabled(false);
		btnApply.setBounds(202, 253, 80, 29);

		btnCancel_1 = new JButton("Cancel");
		btnCancel_1.setBounds(6, 253, 86, 29);
		btnCancel_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});

		btnRestoreAsDefault = new JButton("Reset To Default");
		btnRestoreAsDefault.setEnabled(false);
		btnRestoreAsDefault.setBounds(215, 218, 148, 29);
		generalPanel.setLayout(null);
		generalPanel.add(btnCancel_1);
		generalPanel.add(btnApply);
		generalPanel.add(btnOk);
		generalPanel.add(btnRestoreAsDefault);

		JPanel netwoPanel = new JPanel();
		netwoPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Server Configuration:"));
		netwoPanel.setBounds(6, 6, 367, 84);
		generalPanel.add(netwoPanel);
		netwoPanel.setLayout(new BorderLayout(0, 0));

		panel = new JPanel();
		netwoPanel.add(panel, BorderLayout.NORTH);
		panel.setLayout(new BorderLayout(0, 0));

		lblAdresseIpserver = new JLabel("Adresse IP (Server): ");
		lblAdresseIpserver.setHorizontalAlignment(SwingConstants.RIGHT);
		panel.add(lblAdresseIpserver, BorderLayout.CENTER);

		ipTextField = new JTextField();
		panel.add(ipTextField, BorderLayout.EAST);
		ipTextField.setColumns(10);

		ipTextField.setText(sconf.getAdressIP());
		
				panel_1 = new JPanel();
				netwoPanel.add(panel_1, BorderLayout.SOUTH);
				panel_1.setLayout(new BorderLayout(0, 0));
				
						portTextField = new JTextField();
						panel_1.add(portTextField, BorderLayout.EAST);
						portTextField.setColumns(10);
						portTextField.setText("" + sconf.getPort());
		
				lblPort = new JLabel("Port:");
				lblPort.setHorizontalAlignment(SwingConstants.RIGHT);
				panel_1.add(lblPort, BorderLayout.CENTER);
				
				msgpanel = new JPanel();
				msgpanel.setBounds(6, 102, 367, 104);
				generalPanel.add(msgpanel);
						msgpanel.setLayout(new BorderLayout(0, 0));
										
										panel_3 = new JPanel();
										msgpanel.add(panel_3, BorderLayout.NORTH);
										
												JLabel labelMax = new JLabel("Limite Received Messages:");
												panel_3.add(labelMax);
										
												limitedMsgTextField = new JTextField();
												panel_3.add(limitedMsgTextField);
												limitedMsgTextField.setColumns(3);
												limitedMsgTextField.setText("" + ServerConnection.maxReceivedMsgs);
												
												panel_4 = new JPanel();
												msgpanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Messages: "));
												msgpanel.add(panel_4, BorderLayout.CENTER);
												panel_4.setLayout(new BorderLayout(0, 0));
												
												panel_5 = new JPanel();
												panel_4.add(panel_5, BorderLayout.NORTH);
												
												JLabel lblAutoRefreshAfter = new JLabel("Auto refresh after: ");
												panel_5.add(lblAutoRefreshAfter);
												
												JComboBox comboBox = new JComboBox();
												comboBox.setModel(new DefaultComboBoxModel(new String[] {"30 seconds", "1 minutes", "2 minutes", "5 minutes", "10 minutes", "Disable"}));
												panel_5.add(comboBox);

		JPanel appearancePanel = new JPanel();
		tabbedPane.addTab("appearance",
				new ImageIcon(getClass().getResource("/img/appearance.png")),
				appearancePanel, "appearance");

		JRadioButton rdbtnUseDefaultOptions = new JRadioButton(
				"Use default options");
		rdbtnUseDefaultOptions.setBounds(16, 19, 155, 23);
		rdbtnUseDefaultOptions.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				chckbxTransparency.setEnabled(false);
				chckbxTransparency.setSelected(false);
				labelprecent.setEnabled(false);
				slider.setEnabled(false);
				lblThemes.setEnabled(false);
				rdbtnDark.setEnabled(false);
				rdbtnGray.setEnabled(false);
				rdbtnGray.setSelected(true);
				rdbtnLight.setEnabled(false);
				if (colorChanged)
					new MainWindow().setColors("");

				AWTUtilities.setWindowOpacity(frame, 1.0f);
			}
		});
		rdbtnUseDefaultOptions.setSelected(true);

		JRadioButton rdbtnDontUseThe = new JRadioButton(
				"Don't use the default options");
		rdbtnDontUseThe.setBounds(16, 68, 216, 23);
		rdbtnDontUseThe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				chckbxTransparency.setEnabled(true);
				lblThemes.setEnabled(true);
				rdbtnDark.setEnabled(true);
				rdbtnGray.setEnabled(true);
				rdbtnLight.setEnabled(true);
			}
		});

		groupDefaultOptions.add(rdbtnUseDefaultOptions);
		groupDefaultOptions.add(rdbtnDontUseThe);

		chckbxTransparency = new JCheckBox("Opacity:");
		chckbxTransparency.setBounds(60, 103, 84, 23);
		chckbxTransparency.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (chckbxTransparency.isSelected()) {
					labelprecent.setEnabled(true);
					slider.setEnabled(true);
					AWTUtilities.setWindowOpacity(frame,
							slider.getValue() / 100f);
				} else {
					labelprecent.setEnabled(false);
					slider.setEnabled(false);
					AWTUtilities.setWindowOpacity(frame, 1.0f);
				}
			}
		});
		chckbxTransparency.setEnabled(false);

		slider = new JSlider();
		slider.setBounds(50, 127, 260, 38);
		slider.setEnabled(false);
		slider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				labelprecent.setText(slider.getValue() + "%");
				AWTUtilities.setWindowOpacity(frame, slider.getValue() / 100f);
			}
		});
		slider.setPaintTicks(true);
		slider.setPaintLabels(true);

		labelprecent = new JLabel(slider.getValue() + "%");

		labelprecent.setBounds(155, 107, 50, 16);
		labelprecent.setEnabled(false);

		lblThemes = new JLabel("Themes:");
		lblThemes.setBounds(50, 172, 57, 16);
		lblThemes.setFont(new Font("Lucida Grande", Font.BOLD, 13));

		rdbtnDark = new JRadioButton("Dark");

		rdbtnDark.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new MainWindow().setColors("dark");
				colorChanged = true;
			}
		});

		rdbtnDark.setBounds(50, 200, 62, 23);
		rdbtnDark.setEnabled(false);

		rdbtnGray = new JRadioButton("Gray");
		rdbtnGray.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new MainWindow().setColors("");
			}
		});
		rdbtnGray.setBounds(129, 200, 60, 23);
		rdbtnGray.setEnabled(false);
		rdbtnGray.setSelected(true);

		rdbtnLight = new JRadioButton("Light");

		rdbtnLight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new MainWindow().setColors("light");
				colorChanged = true;
			}
		});

		rdbtnLight.setBounds(214, 200, 64, 23);
		rdbtnLight.setEnabled(false);

		groupThemes.add(rdbtnDark);
		groupThemes.add(rdbtnGray);
		groupThemes.add(rdbtnLight);

		JSeparator separator = new JSeparator();
		separator.setBounds(16, 43, 330, 12);

		JButton btnCancel = new JButton("Close");
		btnCancel.setBounds(145, 247, 86, 29);
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		appearancePanel.setLayout(null);
		appearancePanel.add(rdbtnUseDefaultOptions);
		appearancePanel.add(rdbtnDontUseThe);
		appearancePanel.add(chckbxTransparency);
		appearancePanel.add(slider);
		appearancePanel.add(labelprecent);
		appearancePanel.add(lblThemes);
		appearancePanel.add(rdbtnDark);
		appearancePanel.add(rdbtnGray);
		appearancePanel.add(rdbtnLight);
		appearancePanel.add(separator);
		appearancePanel.add(btnCancel);
	}
}
