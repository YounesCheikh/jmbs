package jmbs.client.Graphics;

import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.BorderFactory;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JSeparator;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;
import java.rmi.RemoteException;
import java.util.regex.Pattern;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import jmbs.client.ClientRequests;
import jmbs.client.HashPassword;
import jmbs.common.User;

public class RegisterPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -369667228660691604L;

	private JPasswordField confirmPasswordField;
	private JPasswordField passwordField;
	private JTextField emailTextField;
	private JTextField fnameTextField;
	private JTextField nameTextField;
	private JLabel lblResp;
	private RegisterFrame rf ;
	/**
	 * Create the panel.
	 */

	private boolean rightName() {
		if (nameTextField.getText().equals(""))
			return false;
		return true;
	}

	private boolean rightFName() {
		if (fnameTextField.getText().equals(""))
			return false;
		return true;
	}

	private boolean rightEmail() {
		boolean correctMail = Pattern.matches(
				"^[_a-z0-9-]+(\\.[_a-z0-9-]+)*@[a-z0-9-]+(\\.[a-z0-9-]+)+$",
				emailTextField.getText());
		return correctMail;
	}

	private boolean rightPassword(JPasswordField pf) {
		return pf.getPassword().length >= 4;
	}

	private boolean confirmedPassword() {
		String strPass = new HashPassword(
				listToString(passwordField.getPassword())).getHashed();
		String strConfirmedPass = new HashPassword(
				listToString(confirmPasswordField.getPassword())).getHashed();
		return strPass.equals(strConfirmedPass);
	}

	private String listToString(char[] list) {
		String retStr = new String();
		for (char c : list) {
			retStr += c;
		}
		return retStr;
	}
	
	

	public RegisterPanel(final RegisterFrame rf) {
		this.rf = rf; // Regsitration frame
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
				rf.setVisible(false);
			}
		});

		JSeparator separator_1 = new JSeparator();

		JLabel lblWelcomeToJmbs = new JLabel("Welcome to JMBS");
		lblWelcomeToJmbs.setFont(new Font("Lucida Grande", Font.BOLD, 16));

		ImagePanel panel = new ImagePanel(
				"./src/jmbs/client/img/jmbslogo_small.png");
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout
				.setHorizontalGroup(groupLayout
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								groupLayout
										.createSequentialGroup()
										.addGroup(
												groupLayout
														.createParallelGroup(
																Alignment.LEADING)
														.addGroup(
																groupLayout
																		.createSequentialGroup()
																		.addContainerGap()
																		.addComponent(
																				lblResp,
																				GroupLayout.DEFAULT_SIZE,
																				448,
																				Short.MAX_VALUE))
														.addGroup(
																groupLayout
																		.createSequentialGroup()
																		.addGap(7)
																		.addComponent(
																				separator,
																				GroupLayout.DEFAULT_SIZE,
																				447,
																				Short.MAX_VALUE))
														.addGroup(
																groupLayout
																		.createSequentialGroup()
																		.addGap(15)
																		.addGroup(
																				groupLayout
																						.createParallelGroup(
																								Alignment.LEADING,
																								false)
																						.addGroup(
																								groupLayout
																										.createSequentialGroup()
																										.addComponent(
																												lblPassword)
																										.addGap(73)
																										.addComponent(
																												passwordField))
																						.addGroup(
																								groupLayout
																										.createSequentialGroup()
																										.addComponent(
																												lblConfirmPassword)
																										.addGap(18)
																										.addGroup(
																												groupLayout
																														.createParallelGroup(
																																Alignment.LEADING,
																																false)
																														.addGroup(
																																Alignment.TRAILING,
																																groupLayout
																																		.createSequentialGroup()
																																		.addComponent(
																																				btnCancel)
																																		.addPreferredGap(
																																				ComponentPlacement.RELATED,
																																				GroupLayout.DEFAULT_SIZE,
																																				Short.MAX_VALUE)
																																		.addComponent(
																																				btnRegister))
																														.addComponent(
																																confirmPasswordField,
																																GroupLayout.PREFERRED_SIZE,
																																235,
																																GroupLayout.PREFERRED_SIZE)))
																						.addComponent(
																								separator_1)
																						.addGroup(
																								groupLayout
																										.createSequentialGroup()
																										.addComponent(
																												lblEmailAddress)
																										.addGap(43)
																										.addComponent(
																												emailTextField))
																						.addGroup(
																								groupLayout
																										.createSequentialGroup()
																										.addComponent(
																												lblLastName)
																										.addGap(66)
																										.addComponent(
																												fnameTextField))
																						.addGroup(
																								groupLayout
																										.createSequentialGroup()
																										.addComponent(
																												lblFirstName)
																										.addGap(64)
																										.addGroup(
																												groupLayout
																														.createParallelGroup(
																																Alignment.LEADING)
																														.addComponent(
																																nameTextField)
																														.addGroup(
																																groupLayout
																																		.createParallelGroup(
																																				Alignment.TRAILING,
																																				false)
																																		.addComponent(
																																				panel,
																																				Alignment.LEADING,
																																				GroupLayout.DEFAULT_SIZE,
																																				GroupLayout.DEFAULT_SIZE,
																																				Short.MAX_VALUE)
																																		.addComponent(
																																				lblWelcomeToJmbs,
																																				Alignment.LEADING,
																																				GroupLayout.DEFAULT_SIZE,
																																				GroupLayout.DEFAULT_SIZE,
																																				Short.MAX_VALUE)))))
																		.addPreferredGap(
																				ComponentPlacement.RELATED,
																				68,
																				Short.MAX_VALUE)))
										.addContainerGap()));
		groupLayout
				.setVerticalGroup(groupLayout
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								Alignment.TRAILING,
								groupLayout
										.createSequentialGroup()
										.addContainerGap()
										.addComponent(lblWelcomeToJmbs)
										.addPreferredGap(
												ComponentPlacement.RELATED)
										.addComponent(panel,
												GroupLayout.DEFAULT_SIZE, 106,
												Short.MAX_VALUE)
										.addPreferredGap(
												ComponentPlacement.RELATED)
										.addGroup(
												groupLayout
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(
																lblFirstName)
														.addComponent(
																nameTextField,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(
												ComponentPlacement.RELATED)
										.addGroup(
												groupLayout
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(
																lblLastName)
														.addComponent(
																fnameTextField,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE))
										.addGap(18)
										.addGroup(
												groupLayout
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(
																lblEmailAddress)
														.addComponent(
																emailTextField,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(
												ComponentPlacement.RELATED)
										.addComponent(separator_1,
												GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												ComponentPlacement.RELATED)
										.addGroup(
												groupLayout
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(
																passwordField,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(
																lblPassword))
										.addPreferredGap(
												ComponentPlacement.RELATED)
										.addGroup(
												groupLayout
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(
																confirmPasswordField,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(
																lblConfirmPassword))
										.addGap(18)
										.addGroup(
												groupLayout
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(
																btnRegister)
														.addComponent(btnCancel))
										.addPreferredGap(
												ComponentPlacement.RELATED)
										.addComponent(separator,
												GroupLayout.PREFERRED_SIZE, 12,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												ComponentPlacement.RELATED)
										.addComponent(lblResp)
										.addContainerGap()));
		setLayout(groupLayout);

	}
	
	private void analysis () {
		nameTextField.setBorder(BorderFactory
				.createLineBorder(Color.GREEN));
		fnameTextField.setBorder(BorderFactory
				.createLineBorder(Color.GREEN));
		emailTextField.setBorder(BorderFactory
				.createLineBorder(Color.GREEN));
		passwordField.setBorder(BorderFactory
				.createLineBorder(Color.GREEN));
		confirmPasswordField.setBorder(BorderFactory
				.createLineBorder(Color.GREEN));
		boolean correctFields = true;
		if (!rightName()) {
			nameTextField.setBorder(BorderFactory.createLineBorder(
					Color.RED));
			correctFields = false;
		}
		if (!rightFName()) {
			fnameTextField.setBorder(BorderFactory
					.createLineBorder(Color.RED));
			correctFields = false;
		}
		if (!rightEmail()) {
			emailTextField.setBorder(BorderFactory
					.createLineBorder(Color.RED));
			correctFields = false;
		}
		if (!rightPassword(passwordField)) {
			passwordField.setBorder(BorderFactory
					.createLineBorder(Color.RED));
			correctFields = false;
		}
		if (!rightPassword(passwordField) || !confirmedPassword()) {
			passwordField.setBorder(BorderFactory
					.createLineBorder(Color.RED));
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
			boolean emailAvailable;
			try {
				emailAvailable = new ClientRequests().getConnection()
						.createUser(
								new User(nameTextField.getText(),
										fnameTextField.getText(),
										emailTextField.getText()),
								new HashPassword(
										listToString(passwordField
												.getPassword()))
										.getHashed());
			} catch (RemoteException e1) {
				// TODO Auto-generated catch block
				// e1.printStackTrace();
				System.out
						.println("Error connection to server while creating user");
				emailAvailable = false;
			}
			if (!emailAvailable) {
				emailTextField.setBorder(BorderFactory
						.createLineBorder(Color.RED));
				lblResp.setText("Email in used!");
				lblResp.setForeground(new Color(255, 0, 0));
				
			}
			else {
				RegistrationSuccessed rs = new RegistrationSuccessed(nameTextField.getText());
				lblResp.setText("Right email");
				lblResp.setForeground(new Color(0, 100, 0));
				rf.setVisible(false);
				rs.setVisible(true);
			}
		}
	}
}
