package jmbs.client.Graphics;

import javax.swing.JPanel;
import javax.swing.JLabel;
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
import jmbs.common.RemoteServer;
import jmbs.common.User;
import net.miginfocom.swing.MigLayout;

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
	private RegisterFrame rf;

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
		boolean correctMail = Pattern.matches("^[_a-z0-9-]+(\\.[_a-z0-9-]+)*@[a-z0-9-]+(\\.[a-z0-9-]+)+$", emailTextField.getText());
		return correctMail;
	}

	private boolean rightPassword(JPasswordField pf) {
		return pf.getPassword().length >= 4;
	}

	private boolean confirmedPassword() {
		String strPass = new HashPassword(listToString(passwordField.getPassword())).getHashed();
		String strConfirmedPass = new HashPassword(listToString(confirmPasswordField.getPassword())).getHashed();
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

		ImagePanel panel = new ImagePanel("./src/jmbs/client/img/jmbslogo_small.png");
		setLayout(new MigLayout("", "[127px][18px][139px][1px][163px]", "[20px][156px][18px][18px][18px][12px][18px][18px][29px][12px][16px]"));
		add(lblResp, "cell 0 10 5 1,growx,aligny top");
		add(separator, "cell 0 9 5 1,growx,aligny top");
		add(lblPassword, "cell 0 6,alignx left,aligny center");
		add(passwordField, "cell 2 6 3 1,growx,aligny top");
		add(lblConfirmPassword, "cell 0 7,alignx right,aligny center");
		add(btnCancel, "cell 2 8,alignx left,aligny top");
		add(btnRegister, "cell 4 8,alignx left,aligny top");
		add(confirmPasswordField, "cell 2 7 3 1,growx,aligny top");
		add(separator_1, "cell 0 5 5 1,growx,aligny top");
		add(lblEmailAddress, "cell 0 4,alignx left,aligny center");
		add(emailTextField, "cell 2 4 3 1,growx,aligny top");
		add(lblLastName, "cell 0 3,alignx left,aligny center");
		add(fnameTextField, "cell 2 3 3 1,growx,aligny top");
		add(lblFirstName, "cell 0 2,alignx left,aligny center");
		add(nameTextField, "cell 2 2 3 1,growx,aligny top");
		add(panel, "cell 2 1,grow");
		add(lblWelcomeToJmbs, "cell 2 0,alignx left,aligny top");

	}

	private void analysis() {
		nameTextField.setBorder(BorderFactory.createLineBorder(Color.GREEN));
		fnameTextField.setBorder(BorderFactory.createLineBorder(Color.GREEN));
		emailTextField.setBorder(BorderFactory.createLineBorder(Color.GREEN));
		passwordField.setBorder(BorderFactory.createLineBorder(Color.GREEN));
		confirmPasswordField.setBorder(BorderFactory.createLineBorder(Color.GREEN));
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
			confirmPasswordField.setBorder(BorderFactory.createLineBorder(Color.RED));
			correctFields = false;
		}
		if (!correctFields) {
			lblResp.setText("Please correct the selected fields");
			lblResp.setForeground(new Color(255, 0, 0));
		} else {
			lblResp.setText("Connection to server...");
			lblResp.setForeground(new Color(0, 100, 0));
			boolean emailAvailable = false;
			RemoteServer server = new ClientRequests().getConnection();
			try {
				if (server != null) {
					emailAvailable = server.createUser(new User(nameTextField.getText(), fnameTextField.getText(), emailTextField.getText()), new HashPassword(listToString(passwordField.getPassword())).getHashed());
					if (!emailAvailable) {
						emailTextField.setBorder(BorderFactory.createLineBorder(Color.RED));
						lblResp.setText("Email in used!");
						lblResp.setForeground(new Color(255, 0, 0));

					} else {
						RegistrationSuccessed rs = new RegistrationSuccessed(nameTextField.getText());
						lblResp.setText("Right email");
						lblResp.setForeground(new Color(0, 100, 0));
						rf.setVisible(false);
						rs.setVisible(true);
					}
				}
			} catch (RemoteException e1) {
				// emailAvailable = false;
			}
			
		}
	}
}
