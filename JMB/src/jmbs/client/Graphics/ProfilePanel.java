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

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;

import jmbs.client.CurrentUser;
import jmbs.common.User;

public class ProfilePanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -398362580268810333L;
	private JTextField nameTextField;
	private JTextField fnameTextField;
	private JTextField emailTextField;
	private JPasswordField passwordField;
	private JPasswordField newpasswordField;
	private JPasswordField confirmpasswordField;
	private JTextField textField;
	private User currentUser = new CurrentUser().get();

	/**
	 * Create the panel.
	 */
	public ProfilePanel() {
		
		JLabel lblName = new JLabel("Name:");
		lblName.setBounds(9, 46, 40, 16);
		
		JLabel lblLastName = new JLabel("Last Name:");
		lblLastName.setBounds(9, 86, 70, 16);
		
		JLabel lblEmailAdress = new JLabel("Email Adress:");
		lblEmailAdress.setBounds(9, 126, 85, 16);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(9, 154, 267, 12);
		
		JLabel lblPublicInformations = new JLabel("Public Informations");
		lblPublicInformations.setBounds(84, 8, 162, 20);
		lblPublicInformations.setHorizontalAlignment(SwingConstants.CENTER);
		lblPublicInformations.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		
		JLabel lblChangePassword = new JLabel("Change Password");
		lblChangePassword.setBounds(82, 172, 163, 20);
		lblChangePassword.setForeground(Color.RED);
		lblChangePassword.setHorizontalAlignment(SwingConstants.CENTER);
		lblChangePassword.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		
		JLabel lblOldPassword = new JLabel("Old Password:");
		lblOldPassword.setBounds(6, 204, 89, 16);
		
		JLabel lblNewPassword = new JLabel("New Password:");
		lblNewPassword.setBounds(6, 238, 94, 16);
		
		JLabel lblConfirmPassword = new JLabel("Confirm Password:");
		lblConfirmPassword.setBounds(9, 272, 118, 16);
		
		nameTextField = new JTextField(currentUser.getName());
		nameTextField.setBounds(142, 40, 180, 28);
		nameTextField.setColumns(10);
		
		fnameTextField = new JTextField(currentUser.getFname());
		fnameTextField.setBounds(142, 80, 180, 28);
		fnameTextField.setColumns(10);
		
		emailTextField = new JTextField(currentUser.getMail());
		emailTextField.setBounds(142, 120, 180, 28);
		emailTextField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(142, 198, 180, 28);
		
		newpasswordField = new JPasswordField();
		newpasswordField.setBounds(142, 232, 180, 28);
		
		confirmpasswordField = new JPasswordField();
		confirmpasswordField.setBounds(142, 266, 180, 28);
		
		JPanel panel = new JPanel();
		panel.setBounds(9, 350, 96, 101);
		panel.setBackground(Color.GRAY);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(9, 306, 264, 12);
		
		JLabel lblProfilePicture = new JLabel("Profile Picture");
		lblProfilePicture.setBounds(122, 324, 115, 20);
		lblProfilePicture.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		
		JButton btnUploadNewPhoto = new JButton("Upload New Photo");
		btnUploadNewPhoto.setBounds(117, 358, 159, 29);
		
		textField = new JTextField();
		textField.setBounds(117, 399, 159, 28);
		textField.setColumns(10);
		setLayout(null);
		add(lblPublicInformations);
		add(lblChangePassword);
		add(lblOldPassword);
		add(newpasswordField);
		add(passwordField);
		add(lblNewPassword);
		add(lblProfilePicture);
		add(separator);
		add(lblName);
		add(lblLastName);
		add(lblEmailAdress);
		add(emailTextField);
		add(fnameTextField);
		add(nameTextField);
		add(panel);
		add(textField);
		add(btnUploadNewPhoto);
		add(separator_1);
		add(lblConfirmPassword);
		add(confirmpasswordField);

	}
}
