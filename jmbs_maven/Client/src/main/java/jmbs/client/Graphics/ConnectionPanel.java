/**
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
 * @author Younes CHEIKH http://cyounes.com
 * @author Benjamin Babic http://bbabic.com
 * 
 */

package jmbs.client.Graphics;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import jmbs.client.ClientRequests;
import jmbs.client.CurrentUser;
import jmbs.client.HashPassword;
import jmbs.client.ServerConnection;
import jmbs.client.Graphics.images.ImagePanel;
import jmbs.common.User;

public class ConnectionPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8035127754370206526L;
	private JTextField emailTextField;
	private JPasswordField passwordField;
	private JLabel respLabel; /*
							 * this will be used to say to the user if there is
							 * a wrong password or email
							 */
	private RegisterFrame rframe;
	/**
	 * the main window
	 */
	private MainWindow w;
	/**
	 * the connection frame, used to stop displaying it after a connection
	 * successed
	 */
	private ConnectionFrame cf;

	/**
	 * @wbp.nonvisual location=198,111
	 */
	/**
	 * Create the panel.
	 * 
	 * @param w
	 *            the main window
	 * @param cf
	 *            the connection frame
	 */
	public ConnectionPanel(MainWindow w, ConnectionFrame cf) {
		this.w = w;
		this.cf = cf;/* by default */

		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout(0, 0));
		setLayout(new BorderLayout(0, 0));
		add(panel, BorderLayout.CENTER);

		JPanel southPanel = new JPanel();
		panel.add(southPanel, BorderLayout.SOUTH);
		southPanel.setLayout(new BorderLayout(0, 0));

		JButton btnConnect = new JButton("Connect");
		southPanel.add(btnConnect, BorderLayout.EAST);
		btnConnect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!verification(emailTextField.getText())) {
					respLabel.setText("Enter a valide email please!");
					respLabel.setForeground(Color.red);
					// putElement(0, 3, respLabel);
				} else if (!verification(passwordField.getPassword().length)) {
					respLabel.setText("password less than 4 chars !");
					respLabel.setForeground(Color.red);
					// putElement(0, 3, respLabel);
				} else {
					checkConnection();
				}
			}
		});

		JCheckBox chckbxRememberMe = new JCheckBox("Remember me");
		chckbxRememberMe.setHorizontalAlignment(SwingConstants.RIGHT);
		southPanel.add(chckbxRememberMe, BorderLayout.CENTER);

		JPanel panel_4 = new JPanel();
		southPanel.add(panel_4, BorderLayout.SOUTH);
		panel_4.setLayout(new BorderLayout(0, 0));

		respLabel = new JLabel(" ");
		panel_4.add(respLabel, BorderLayout.CENTER);

		JSeparator separator = new JSeparator();
		panel_4.add(separator, BorderLayout.NORTH);

		JPanel topPanel = new JPanel();
		topPanel.setPreferredSize(new Dimension(100, 170));
		panel.add(topPanel, BorderLayout.NORTH);
		topPanel.setLayout(new BorderLayout(0, 0));

		ImagePanel logopanel = new ImagePanel("/img/jmbslogo_small.png");
		logopanel.setPreferredSize(new Dimension(128, 128));
		topPanel.add(logopanel, BorderLayout.CENTER);
		JLabel lblConnectToJmbs = new JLabel("Connect to JMBS");
		lblConnectToJmbs.setHorizontalAlignment(SwingConstants.CENTER);
		topPanel.add(lblConnectToJmbs, BorderLayout.SOUTH);
		lblConnectToJmbs.setFont(new Font("Dialog", Font.BOLD, 16));

		JPanel panel_6 = new JPanel();
		topPanel.add(panel_6, BorderLayout.NORTH);

		JPanel panel_7 = new JPanel();
		panel_7.setPreferredSize(new Dimension(145, 0));
		topPanel.add(panel_7, BorderLayout.WEST);

		JPanel panel_8 = new JPanel();
		panel_8.setPreferredSize(new Dimension(145, 0));
		topPanel.add(panel_8, BorderLayout.EAST);

		JPanel centerPanel = new JPanel();
		// centerPanel.setPreferredSize(new Dimension(100,60));
		panel.add(centerPanel, BorderLayout.CENTER);
		centerPanel.setLayout(new BorderLayout(0, 0));

		JPanel panel_3 = new JPanel();
		centerPanel.add(panel_3, BorderLayout.SOUTH);
		panel_3.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JLabel lblRegister = new JLabel("don't have account?");
		panel_3.add(lblRegister);

		JButton btnRegister = new JButton("Register");
		panel_3.add(btnRegister);
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rframe = new RegisterFrame();
				rframe.setVisible(true);
			}
		});
		btnRegister.setHorizontalAlignment(SwingConstants.LEFT);
		btnRegister.setForeground(Color.BLUE);
		btnRegister.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		btnRegister.setBorderPainted(false);

		JPanel panel_5 = new JPanel();
		centerPanel.add(panel_5, BorderLayout.CENTER);
		panel_5.setLayout(new BorderLayout(0, 0));

		JPanel panel_1 = new JPanel();
		panel_5.add(panel_1, BorderLayout.NORTH);
		panel_1.setLayout(new BorderLayout(0, 0));

		JLabel lblEmailAdress = new JLabel("Email Adress:");
		lblEmailAdress.setHorizontalAlignment(SwingConstants.RIGHT);
		panel_1.add(lblEmailAdress);

		emailTextField = new JTextField();
		panel_1.add(emailTextField, BorderLayout.EAST);
		emailTextField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				checkConnection();
			}
		});
		emailTextField.setColumns(20);

		JPanel panel_2 = new JPanel();
		panel_5.add(panel_2, BorderLayout.SOUTH);
		panel_2.setLayout(new BorderLayout(0, 0));

		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setHorizontalAlignment(SwingConstants.RIGHT);
		panel_2.add(lblPassword);

		passwordField = new JPasswordField();
		panel_2.add(passwordField, BorderLayout.EAST);
		passwordField.setColumns(20);
		passwordField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				checkConnection();
			}
		});

	}

	/**
	 * verify the email
	 * 
	 * @param mail
	 *            adress mail
	 * @return true if the user has entred a right email adress
	 */
	private boolean verification(String mail) {
		boolean correctMail = Pattern.matches(
				"^[_a-z0-9-]+(\\.[_a-z0-9-]+)*@[a-z0-9-]+(\\.[a-z0-9-]+)+$",
				mail);
		return (correctMail);
	}

	/**
	 * verify the password length
	 * 
	 * @param passwdLength
	 *            the password length
	 * @return true if password length >= 4
	 */
	private boolean verification(int passwdLength) {
		boolean correctPasswd = (passwdLength >= 4);
		return (correctPasswd);
	}

	/**
	 * this methode update the response Label 'respLabel'
	 */
	private void checkConnection() {
		new ServerConnection();
		respLabel.setText("Connection to server...");
		respLabel.setForeground(new Color(0, 100, 0));

		User u = ClientRequests.connectUser(this.emailTextField.getText(),
				new HashPassword(listToString(passwordField.getPassword()))
						.getHashed());
		if (u == null) {
			respLabel.setText("Connection impossible...");
			respLabel.setForeground(new Color(100, 0, 0));
		} else if (u.getId() != -1) {
			cf.dispose();
			new CurrentUser(u);
			this.initMainWindow();
		} else if (u.getId() == -1) {
			respLabel
					.setText("Wrong password or wrong email, Please try again!");
			respLabel.setForeground(new Color(200, 0, 0));
		}
	}

	private void initMainWindow() {
		MainWindow.initFrame();
		this.w = new MainWindow(); // Initialize new Main Window

		// Setting the frame Title
		this.w.getFrame()
				.setTitle("JMBS Client : " + CurrentUser.getFullName());
		// Empty the List of messages if not
		this.w.initMsgListTL();
		// set the id of the last received message at 0
		this.w.getTLPanel().setLastIdMsg(0);
		// remove all the comptent from the timeline panel
		this.w.getTLPanel().removeAll();
		this.w.getTLPanel().updateUI();
		// Repante and revalidate the timeline panel
		this.w.getTLPanel().repaint();
		this.w.getTLPanel().revalidate();
		// check for new messages from the server and display them on the
		// timeline panel
		this.w.checkNewMessages(0);
		// Resetting the profile panel
		this.w.resetProfilePanel();
		// setting the menubar
		this.w.setMenuBar();
		// Display the main frame
		this.w.getFrame().setVisible(true);
	}

	private String listToString(char[] list) {
		String retStr = new String();
		for (char c : list) {
			retStr += c;
		}
		return retStr;
	}
}
