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

package jmbs.client.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.regex.Pattern;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import jmbs.client.ClientRequests;
import jmbs.client.ServerConnection;
import jmbs.client.dataTreatment.FramesConf;
import jmbs.client.dataTreatment.HashPassword;
import jmbs.client.gui.images.ImagePanel;
import jmbs.client.gui.others.SayToUser;
import jmbs.common.User;
import net.miginfocom.swing.MigLayout;

/**
 * @author <a href="mailto:younes.cheikh@gmail.com">Younes CHEIKH</a>
 * @author Benjamin Babic
 * @since 06-05-2012
 * @version 1.0
 */
public class RegisterFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7428710889628300587L;
	private JPanel contentPane;
	private static Point point = new Point();
	private JPasswordField confirmPasswordField;
	private JPasswordField passwordField;
	private JTextField emailTextField;
	private JTextField fnameTextField;
	private JTextField nameTextField;
	private JLabel lblResp;

	/**
	 * Create the frame.
	 */
	public RegisterFrame() {

		// setLocationRelativeTo(null);
		FramesConf.centerThisFrame(this);

		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				point.x = e.getX();
				point.y = e.getY();
			}
		});

		addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				Point p = getLocation();
				setLocation(p.x + e.getX() - point.x, p.y + e.getY() - point.y);
			}
		});
		setTitle("Register with JMBS");
		setUndecorated(true);

		// setDefaultCloseOperation(JFrame.);
		// setBounds(100, 100, 450, 484);
		setSize(450, 484);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JPanel panel = new JPanel();

		JLabel lblFirstName = new JLabel("First Name:");

		JLabel lblLastName = new JLabel("Last Name:");

		JLabel lblEmailAddress = new JLabel("Email Address:");

		JLabel lblPassword = new JLabel("Password:");

		JLabel lblConfirmPassword = new JLabel("Confirm Password:");

		confirmPasswordField = new JPasswordField();
		confirmPasswordField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				analysis();
			}
		});

		passwordField = new JPasswordField();
		passwordField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				analysis();
			}
		});

		emailTextField = new JTextField();
		emailTextField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				analysis();
			}
		});
		emailTextField.setColumns(10);

		fnameTextField = new JTextField();
		fnameTextField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				analysis();
			}
		});
		fnameTextField.setColumns(10);

		nameTextField = new JTextField();
		nameTextField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				analysis();
			}
		});
		nameTextField.setColumns(10);

		nameTextField.setBorder(BorderFactory.createLineBorder(null));
		fnameTextField.setBorder(BorderFactory.createLineBorder(null));
		emailTextField.setBorder(BorderFactory.createLineBorder(null));
		passwordField.setBorder(BorderFactory.createLineBorder(null));
		confirmPasswordField.setBorder(BorderFactory.createLineBorder(null));

		lblResp = new JLabel("  ");

		JSeparator separator = new JSeparator();

		JButton btnRegister = new JButton("Register");
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				analysis();
			}
		});

		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});

		JSeparator separator_1 = new JSeparator();

		JLabel lblWelcomeToJmbs = new JLabel("Welcome to JMBS");
		lblWelcomeToJmbs.setFont(new Font("Lucida Grande", Font.BOLD, 16));

		ImagePanel impanel = new ImagePanel("/img/jmbslogo_small.png");
		panel.setLayout(new MigLayout("", "[127px][18px][139px][1px][163px]",
				"[20px][156px][18px][18px][18px][12px][18px][18px][29px][12px][16px]"));
		panel.add(lblResp, "cell 0 10 5 1,growx,aligny top");
		panel.add(separator, "cell 0 9 5 1,growx,aligny top");
		panel.add(lblPassword, "cell 0 6,alignx left,aligny center");
		panel.add(passwordField, "cell 2 6 3 1,growx,aligny top");
		panel.add(lblConfirmPassword, "cell 0 7,alignx right,aligny center");
		panel.add(btnCancel, "cell 2 8,alignx left,aligny top");
		panel.add(btnRegister, "cell 4 8,alignx left,aligny top");
		panel.add(confirmPasswordField, "cell 2 7 3 1,growx,aligny top");
		panel.add(separator_1, "cell 0 5 5 1,growx,aligny top");
		panel.add(lblEmailAddress, "cell 0 4,alignx left,aligny center");
		panel.add(emailTextField, "cell 2 4 3 1,growx,aligny top");
		panel.add(lblLastName, "cell 0 3,alignx left,aligny center");
		panel.add(fnameTextField, "cell 2 3 3 1,growx,aligny top");
		panel.add(lblFirstName, "cell 0 2,alignx left,aligny center");
		panel.add(nameTextField, "cell 2 2 3 1,growx,aligny top");
		panel.add(impanel, "cell 2 1,grow");
		panel.add(lblWelcomeToJmbs, "cell 2 0,alignx left,aligny top");

		contentPane.add(panel, BorderLayout.CENTER);
	}

	/**
	 * @return true if the nameTextfiled is not empty
	 */
	private boolean rightName() {
		if (nameTextField.getText().equals(""))
			return false;
		return true;
	}

	/**
	 * @return true if fnameTextfield is not empty
	 */
	private boolean rightFName() {
		if (fnameTextField.getText().equals(""))
			return false;
		return true;
	}

	/**
	 * @return true if the emailTextfield contains a valid adress mail
	 */
	private boolean rightEmail() {
		boolean correctMail = Pattern.matches(
				"^[_a-z0-9-]+(\\.[_a-z0-9-]+)*@[a-z0-9-]+(\\.[a-z0-9-]+)+$",
				emailTextField.getText());
		return correctMail;
	}

	/**
	 * @param pf
	 *            passwordfiled
	 * @return true if password length > 4 charcters
	 */
	private boolean rightPassword(JPasswordField pf) {
		return pf.getPassword().length >= 4;
	}

	/**
	 * @return true if the passwordFiled and confirmedPasswordfiled contain the
	 *         same password
	 */
	private boolean confirmedPassword() {
		String strPass = new HashPassword(
				listToString(passwordField.getPassword())).getHashed();
		String strConfirmedPass = new HashPassword(
				listToString(confirmPasswordField.getPassword())).getHashed();
		return strPass.equals(strConfirmedPass);
	}

	/**
	 * @param list
	 *            array of chars
	 * @return string converted from array
	 */
	private String listToString(char[] list) {
		String retStr = new String();
		for (char c : list) {
			retStr += c;
		}
		return retStr;
	}

	/**
	 * confirm the entred data by the user in the text fields
	 */
	private void analysis() {
		new ServerConnection();
		nameTextField.setBorder(BorderFactory.createLineBorder(Color.GREEN));
		fnameTextField.setBorder(BorderFactory.createLineBorder(Color.GREEN));
		emailTextField.setBorder(BorderFactory.createLineBorder(Color.GREEN));
		passwordField.setBorder(BorderFactory.createLineBorder(Color.GREEN));
		confirmPasswordField.setBorder(BorderFactory
				.createLineBorder(Color.GREEN));
		boolean correctFields = true;
		if (!rightName()) {
			nameTextField.setBorder(BorderFactory.createLineBorder(Color.RED));
			correctFields = false;
		}
		if (!rightFName()) {
			fnameTextField.setBorder(BorderFactory.createLineBorder(Color.RED));
			correctFields = false;
		}
		if (!rightEmail()) {
			emailTextField.setBorder(BorderFactory.createLineBorder(Color.RED));
			correctFields = false;
		}
		if (!rightPassword(passwordField)) {
			passwordField.setBorder(BorderFactory.createLineBorder(Color.RED));
			correctFields = false;
		}
		if (!rightPassword(passwordField) || !confirmedPassword()) {
			passwordField.setBorder(BorderFactory.createLineBorder(Color.RED));
			confirmPasswordField.setBorder(BorderFactory
					.createLineBorder(Color.RED));
			correctFields = false;
		}
		if (!correctFields) {
			lblResp.setText("Please correct the selected fields");
			lblResp.setForeground(new Color(255, 0, 0));
		} else {
			lblResp.setText("Connection to server...");
			lblResp.setForeground(new Color(0, 100, 0));
			boolean emailAvailable = false;

			emailAvailable = ClientRequests.createUser(
					new User(nameTextField.getText(), fnameTextField.getText(),
							emailTextField.getText()), new HashPassword(
							listToString(passwordField.getPassword()))
							.getHashed());
			if (!emailAvailable) {
				emailTextField.setBorder(BorderFactory
						.createLineBorder(Color.RED));
				lblResp.setText("Email in used!");
				lblResp.setForeground(new Color(255, 0, 0));

			} else {
				SayToUser
						.success(
								"Congratulations!",
								"Thanks "
										+ nameTextField.getText()
										+ "! your registration has been successful. Now you can login using your email and password.");
				dispose();
			}

		}
	}

}
