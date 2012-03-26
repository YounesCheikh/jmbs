package jmbs.client.Graphics;

import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;

import jmbs.client.CurrentUser;
import jmbs.common.User;
import net.miginfocom.swing.MigLayout;

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

	/**
	 * Create the panel.
	 */
	public ProfilePanel() {
		
		User u = new CurrentUser().get();
		
		JLabel lblName = new JLabel("Name:");
		
		JLabel lblLastName = new JLabel("Last Name:");
		
		JLabel lblEmailAdress = new JLabel("Email Adress:");
		
		JSeparator separator = new JSeparator();
		
		JLabel lblPublicInformations = new JLabel("Public Informations");
		lblPublicInformations.setHorizontalAlignment(SwingConstants.CENTER);
		lblPublicInformations.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		
		JLabel lblChangePassword = new JLabel("Change Password");
		lblChangePassword.setForeground(Color.RED);
		lblChangePassword.setHorizontalAlignment(SwingConstants.CENTER);
		lblChangePassword.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		
		JLabel lblOldPassword = new JLabel("Old Password:");
		
		JLabel lblNewPassword = new JLabel("New Password:");
		
		JLabel lblConfirmPassword = new JLabel("Confirm Password:");
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.GRAY);
		
		JSeparator separator_1 = new JSeparator();
		
		JLabel lblProfilePicture = new JLabel("Profile Picture");
		lblProfilePicture.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		
		JButton btnUploadNewPhoto = new JButton("Upload New Photo");
		
		textField = new JTextField();
		textField.setColumns(10);
		setLayout(new MigLayout("", "[73px][3px][23px][12px][10px][grow,fill][15px][134px]", "[20px][28px][28px][28px][12px][20px][28px][28px][28px][12px][20px][37px][12px][52px]"));
		add(lblPublicInformations, "cell 2 0 6 1,alignx left,aligny top");
		
		nameTextField = new JTextField(u.getName());
		nameTextField.setColumns(20);
		add(nameTextField, "cell 5 1 2 1,growx,aligny top");
		
		fnameTextField = new JTextField(u.getFname());
		fnameTextField.setColumns(10);
		add(fnameTextField, "cell 5 2 2 1,growx,aligny top");
		
		emailTextField = new JTextField(u.getMail());
		emailTextField.setColumns(10);
		add(emailTextField, "cell 5 3 2 1,growx,aligny top");
		add(lblChangePassword, "cell 2 5 6 1,alignx left,aligny top");
		add(lblOldPassword, "cell 0 6 3 1,alignx left,aligny center");
		
		passwordField = new JPasswordField();
		add(passwordField, "cell 5 6 2 1,growx,aligny top");
		add(lblNewPassword, "cell 0 7 3 1,alignx left,aligny center");
		
		newpasswordField = new JPasswordField();
		add(newpasswordField, "cell 5 7 2 1,growx,aligny top");
		
		confirmpasswordField = new JPasswordField();
		add(confirmpasswordField, "cell 5 8 2 1,growx,aligny top");
		add(lblProfilePicture, "cell 4 10 4 1,alignx left,aligny top");
		add(separator, "cell 0 4 8 1,growx,aligny top");
		add(lblName, "cell 0 1,alignx left,aligny center");
		add(lblLastName, "cell 0 2,alignx right,aligny center");
		add(lblEmailAdress, "cell 0 3 3 1,alignx left,aligny center");
		add(panel, "cell 0 11 3 3,grow");
		add(textField, "cell 4 13 3 1,growx,aligny top");
		add(btnUploadNewPhoto, "cell 4 11 4 1,alignx left,aligny bottom");
		add(separator_1, "cell 0 9 8 1,growx,aligny top");
		add(lblConfirmPassword, "cell 0 8 5 1,alignx left,aligny center");

	}
}
