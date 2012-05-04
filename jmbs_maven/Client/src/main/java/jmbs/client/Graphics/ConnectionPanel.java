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
 * @author Younes CHEIKH http://cyounes.com
 * @author Benjamin Babic http://bbabic.com
 * 
 */

package jmbs.client.Graphics;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
import jmbs.client.DataTreatment.LoginTreatment;
import jmbs.client.Graphics.images.ImagePanel;
import jmbs.common.User;
import net.miginfocom.swing.MigLayout;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class ConnectionPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8035127754370206526L;
	private JPanel panel;
	private JTextField emailTextField;
	private JPasswordField passwordField;
	private JCheckBox chckbxRememberMe;
	private boolean savedIdentity = false;
	private boolean passwordChanged = false;
	private final LoginTreatment lt = new LoginTreatment();
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
		setLayout(new BorderLayout());
		JLabel lblConnectToJmbs = new JLabel("Connect to JMBS");
		lblConnectToJmbs.setFont(new Font("Dialog", Font.BOLD, 16));

		JLabel lblEmailAdress = new JLabel("Email Adress:");

		JLabel lblPassword = new JLabel("Password:");

		emailTextField = new JTextField();
		emailTextField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				if (lt.isSaved(emailTextField.getText()) != null) {
					passwordField.setText("********");
					savedIdentity = true;
				}
			}
		});
		emailTextField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				checkConnection();
			}
		});
		emailTextField.setColumns(20);

		passwordField = new JPasswordField();
		passwordField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if (e.getKeyChar()!='\n') {
					passwordChanged = true;
				}
			}
		});
		passwordField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				
			}
		});
		passwordField.setColumns(20);
		passwordField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				checkConnection();
			}
		});

		respLabel = new JLabel(" ");

		chckbxRememberMe = new JCheckBox("Remember me");

		JLabel lblRegister = new JLabel("don't have account?");

		ImagePanel logopanel = new ImagePanel("/img/jmbslogo_small.png");

		JButton btnRegister = new JButton("Register");
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rframe = new RegisterFrame();
				rframe.setVisible(true);
			}
		});
		btnRegister.setHorizontalAlignment(SwingConstants.LEFT);
		btnRegister.setForeground(Color.BLUE);
		btnRegister.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		panel = new JPanel();
		panel.setLayout(new MigLayout(
				"",
				"[63px][12px][10px][12px][102px,grow][6px][104px][][6px][96px]",
				"[20px][136px][28px][28px][23px][30px][12px][][][][36px][grow]"));
		btnRegister.setBorderPainted(false);
		panel.add(lblPassword, "cell 0 3,alignx left,aligny center");
		panel.add(passwordField, "cell 4 3 6 1,aligny top");
		panel.add(lblConnectToJmbs, "cell 4 0 3 1,alignx center,aligny top");
		panel.add(lblRegister, "cell 2 5 3 1,alignx left,aligny center");
		panel.add(btnRegister, "cell 6 5,growx,aligny top");

		JButton btnConnect = new JButton("Connect");
		btnConnect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!lt.isMailCorrect(emailTextField.getText())) {
					respLabel.setText("Enter a valide email please!");
					respLabel.setForeground(Color.red);
					// putElement(0, 3, respLabel);
				} else if (!lt.hasMinLenght(passwordField.getPassword().length)) {
					respLabel.setText("password less than 4 chars !");
					respLabel.setForeground(Color.red);
					// putElement(0, 3, respLabel);
				} else {
					checkConnection();
				}
			}
		});
		panel.add(btnConnect, "cell 6 7,alignx left,aligny bottom");

		JSeparator separator = new JSeparator();
		panel.add(separator, "cell 0 8 10 1,growx,aligny top");
		panel.add(respLabel, "cell 0 10 10 1,grow");
		panel.add(chckbxRememberMe, "cell 4 4 3 1,alignx center,aligny top");
		panel.add(lblEmailAdress, "cell 0 2 3 1,alignx left,aligny center");
		panel.add(emailTextField, "cell 4 2 6 1,aligny top");
		panel.add(logopanel, "cell 4 1 3 1,grow");

		add(panel);

	}

	/**
	 * this methode update the response Label 'respLabel'
	 */
	private void checkConnection() {
		new ServerConnection();
		respLabel.setText("Connection to server...");
		respLabel.setForeground(new Color(0, 100, 0));
		String hashedPass = new String();
		if (savedIdentity && !passwordChanged) {
			hashedPass = lt.isSaved(emailTextField.getText());
		}
		else {
			hashedPass = new HashPassword(lt.arrayToString(passwordField.getPassword()))
			.getHashed();
		}
		User u = ClientRequests.connectUser(this.emailTextField.getText(),
				hashedPass);
		if (u == null) {
			respLabel.setText("Connection impossible...");
			respLabel.setForeground(new Color(100, 0, 0));
		} else if (u.getId() != -1) {
			if (chckbxRememberMe.isSelected() && !lt.arrayToString(passwordField.getPassword()).equals("********")) {
				lt.savePassword(
						emailTextField.getText(),
						new HashPassword(lt.arrayToString(passwordField
								.getPassword())).getHashed());
			}
			cf.dispose();
			new CurrentUser(u);
			this.initMainWindow();
		} else if (u.getId() == -1) {
			savedIdentity = false;
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
		MainWindow.checkNewMessages(0);
		// Resetting the profile panel
		this.w.resetProfilePanel();
		// setting the menubar
		this.w.setMenuBar();
		// Display the main frame
		this.w.getFrame().setVisible(true);
	}

}
