package jmbs.client.Graphics;

import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.Color;
import java.awt.Font;
import java.rmi.RemoteException;
import java.util.regex.Pattern;

import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JCheckBox;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import jmbs.client.ClientRequests;
import jmbs.client.CurrentUser;
import jmbs.client.HashPassword;
import jmbs.common.RemoteServer;
import jmbs.common.User;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

import net.miginfocom.swing.MigLayout;

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
		this.cf = cf;

		JLabel lblConnectToJmbs = new JLabel("Connect to JMBS");
		lblConnectToJmbs.setFont(new Font("Dialog", Font.BOLD, 16));

		JLabel lblEmailAdress = new JLabel("Email Adress:");

		JLabel lblPassword = new JLabel("Password:");

		emailTextField = new JTextField();
		emailTextField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				checkConnection();
			}
		});
		emailTextField.setColumns(20);

		passwordField = new JPasswordField();
		passwordField.setColumns(20);
		passwordField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				checkConnection();
			}
		});

		respLabel = new JLabel(" "); /* by default */

		JCheckBox chckbxRememberMe = new JCheckBox("Remember me");

		JLabel lblRegister = new JLabel("don't have account?");

		ImagePanel logopanel = new ImagePanel("./src/jmbs/client/img/jmbslogo_small.png");

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
		btnRegister.setBorderPainted(false);
		setLayout(new MigLayout("", "[63px][12px][10px][12px][102px][6px][104px][][6px][96px]", "[20px][136px][28px][28px][23px][30px][12px][][][][36px]"));
		add(lblPassword, "cell 0 3,alignx left,aligny center");
		add(passwordField, "cell 4 3 6 1,aligny top");
		add(lblConnectToJmbs, "cell 4 0 3 1,alignx center,aligny top");
		add(lblRegister, "cell 2 5 3 1,alignx left,aligny center");
		add(btnRegister, "cell 6 5,growx,aligny top");

		JButton btnConnect = new JButton("Connect");
		btnConnect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				checkConnection();
			}
		});
		add(btnConnect, "cell 6 7,alignx left,aligny bottom");

		JSeparator separator = new JSeparator();
		add(separator, "cell 0 8 10 1,growx,aligny top");
		add(respLabel, "cell 0 10 10 1,grow");
		add(chckbxRememberMe, "cell 4 4 3 1,alignx center,aligny top");
		add(lblEmailAdress, "cell 0 2 3 1,alignx left,aligny center");
		add(emailTextField, "cell 4 2 6 1,aligny top");
		add(logopanel, "cell 4 1 3 1,grow");

	}

	/**
	 * verify the email
	 * 
	 * @param mail
	 *            adress mail
	 * @return true if the user has entred a right email adress
	 */
	private boolean verification(String mail) {
		boolean correctMail = Pattern.matches("^[_a-z0-9-]+(\\.[_a-z0-9-]+)*@[a-z0-9-]+(\\.[a-z0-9-]+)+$", mail);
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
		RemoteServer server = new ClientRequests().getConnection();
		if (!verification(emailTextField.getText())) {
			respLabel.setText("Enter a valide email please!");
			respLabel.setForeground(Color.red);
			// putElement(0, 3, respLabel);
		} else if (!verification(passwordField.getPassword().length)) {
			respLabel.setText("password less than 4 chars !");
			respLabel.setForeground(Color.red);
			// putElement(0, 3, respLabel);
		} else {
			respLabel.setText("Connection to server...");
			respLabel.setForeground(new Color(0, 100, 0));

			try {
				if (server != null) {
					User u = server.connectUser(this.emailTextField.getText(), new HashPassword(listToString(passwordField.getPassword())).getHashed());
					if (u.getId() != -1) {
						cf.dispose();
						new CurrentUser(u);
						this.initMainWindow();
						this.getMainWindow().getFrame().setVisible(true);
						// System.out.println(new
						// CurrentUser().get().toString());
					} else if (u.getId() == -1) {
						respLabel.setText("Wrong password or wrong email, Please try again!");
						respLabel.setForeground(new Color(200, 0, 0));
					}
				}
			} catch (RemoteException e) {
				//new SayToUser("coucou can't connect to server", true);
				//System.out.println("Connection to server impossible \n" + e.getMessage());
			}
		}
	}

	/**
	 * @return return the main windows of this program
	 */
	private MainWindow getMainWindow() {
		return w;
	}

	private void initMainWindow() {
		this.w = new MainWindow();
	}

	private String listToString(char[] list) {
		String retStr = new String();
		for (char c : list) {
			retStr += c;
		}
		return retStr;
	}
}
