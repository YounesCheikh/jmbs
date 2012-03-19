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
		
		nameTextField = new JTextField();
		nameTextField.setColumns(10);
		
		fnameTextField = new JTextField();
		fnameTextField.setColumns(10);
		
		emailTextField = new JTextField();
		emailTextField.setColumns(10);
		
		passwordField = new JPasswordField();
		
		newpasswordField = new JPasswordField();
		
		confirmpasswordField = new JPasswordField();
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.GRAY);
		
		JSeparator separator_1 = new JSeparator();
		
		JLabel lblProfilePicture = new JLabel("Profile Picture");
		lblProfilePicture.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		
		JButton btnUploadNewPhoto = new JButton("Upload New Photo");
		
		textField = new JTextField();
		textField.setColumns(10);
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(84)
					.addComponent(lblPublicInformations)
					.addContainerGap(50, Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(82)
					.addComponent(lblChangePassword, GroupLayout.PREFERRED_SIZE, 163, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(75, Short.MAX_VALUE))
				.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblOldPassword)
					.addGap(48)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addComponent(newpasswordField, GroupLayout.PREFERRED_SIZE, 127, GroupLayout.PREFERRED_SIZE)
						.addComponent(passwordField, GroupLayout.PREFERRED_SIZE, 127, GroupLayout.PREFERRED_SIZE))
					.addGap(50))
				.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewPassword)
					.addContainerGap(220, Short.MAX_VALUE))
				.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
					.addGap(122)
					.addComponent(lblProfilePicture)
					.addContainerGap(83, Short.MAX_VALUE))
				.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
					.addGap(9)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
							.addComponent(separator, Alignment.LEADING)
							.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
								.addPreferredGap(ComponentPlacement.RELATED)
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
									.addComponent(lblName)
									.addComponent(lblLastName)
									.addComponent(lblEmailAdress))
								.addGap(48)
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
									.addComponent(emailTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addComponent(fnameTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addComponent(nameTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
						.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
							.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
								.addComponent(panel, GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
									.addComponent(textField)
									.addComponent(btnUploadNewPhoto, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
							.addGroup(Alignment.LEADING, groupLayout.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(separator_1, Alignment.LEADING)
								.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
									.addComponent(lblConfirmPassword)
									.addGap(18)
									.addComponent(confirmpasswordField, 128, 128, 128)))))
					.addContainerGap(20, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(8)
					.addComponent(lblPublicInformations)
					.addGap(12)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblName)
						.addComponent(nameTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblLastName)
						.addComponent(fnameTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(12)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblEmailAdress)
						.addComponent(emailTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(separator, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblChangePassword)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblOldPassword)
						.addComponent(passwordField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewPassword)
						.addComponent(newpasswordField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblConfirmPassword)
						.addComponent(confirmpasswordField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(separator_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblProfilePicture)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(panel, GroupLayout.PREFERRED_SIZE, 101, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(14)
							.addComponent(btnUploadNewPhoto)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(9, Short.MAX_VALUE))
		);
		setLayout(groupLayout);

	}
}
