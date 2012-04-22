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

package jmbs.server;

import com.sun.awt.AWTUtilities;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.rmi.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import javax.swing.*;
import jmbs.common.RemoteServer;
import net.miginfocom.swing.MigLayout;

@SuppressWarnings("restriction")
public class ServerWindow {

	protected JFrame frmJmbsServerControl;
	private JTextField txtStatus;
	private ServerOF ser;
	private boolean serverIsRunning = false;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the application.
	 */
	public ServerWindow() {
		try {
			ser = new ServerOF();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			System.err.println("Error occurred during creating the new Request!\n" + e.getMessage());
		}
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmJmbsServerControl = new JFrame();
		frmJmbsServerControl.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				frmJmbsServerControl.dispose();
				try {
					UnicastRemoteObject.unexportObject(LocateRegistry.createRegistry(1099),true);
				} catch (NoSuchObjectException e1) {
					// TODO Auto-generated catch block
					//e1.printStackTrace();
					System.out.println(e1.getMessage());
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					//e1.printStackTrace();
					System.out.println(e1.getMessage());
				} 
				System.exit(0);
			}
		});
		frmJmbsServerControl.setResizable(false);
		frmJmbsServerControl.setBackground(new Color(105, 105, 105));
		frmJmbsServerControl.setAlwaysOnTop(true);
		frmJmbsServerControl.setTitle("JMBS Server Control");
		frmJmbsServerControl.getContentPane().setBackground(Color.DARK_GRAY);
		frmJmbsServerControl.setBounds(100, 100, 280, 169);
		frmJmbsServerControl.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// Setting Opacity
		AWTUtilities.setWindowOpacity(frmJmbsServerControl, 0.88f);
		txtStatus = new JTextField();
		txtStatus.setForeground(new Color(255, 69, 0));
		txtStatus.setBackground(Color.BLACK);
		txtStatus.setHorizontalAlignment(SwingConstants.CENTER);
		txtStatus.setEditable(false);
		txtStatus.setText("Stopped");
		txtStatus.setColumns(10);

		JLabel lblStatusServer = new JLabel("Status Server:");
		lblStatusServer.setForeground(Color.LIGHT_GRAY);

		final JButton btnStartstop = new JButton("Start");
		btnStartstop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (serverIsRunning) {
					serverIsRunning = !stopServer();
					if (!serverIsRunning) {
						btnStartstop.setText("Start");
						txtStatus.setText("Stopped");
						txtStatus.setForeground(Color.RED);
					}
				} else {
					serverIsRunning = startServer();
					if (serverIsRunning) {
						btnStartstop.setText("Stop");
						txtStatus.setText("Running");
						txtStatus.setForeground(Color.GREEN);
					}
				}
			}
		});
		//btnStartstop.setSelectedIcon(new ImageIcon(ServerWindow.class.getResource("/com/sun/java/swing/plaf/gtk/resources/gtk-yes-4.png")));
		//btnStartstop.setIcon(new ImageIcon("/Users/ycheikh/Dev/JMB/src/jmbs/client/img/quit_ico.png"));
		btnStartstop.setToolTipText("Start/Stop JMBS Server");
		btnStartstop.setBackground(Color.BLACK);

		JLabel lblJmbsServerControl = new JLabel("JMBS Server Control");
		lblJmbsServerControl.setFont(new Font("Noteworthy", Font.BOLD, 22));
		lblJmbsServerControl.setForeground(Color.GREEN);
		frmJmbsServerControl.getContentPane().setLayout(new MigLayout("", "[85px][12px][134px]", "[37px][28px][36px]"));
		frmJmbsServerControl.getContentPane().add(txtStatus, "cell 2 1,alignx left,aligny top");
		frmJmbsServerControl.getContentPane().add(lblStatusServer, "cell 0 1,alignx left,aligny center");
		frmJmbsServerControl.getContentPane().add(btnStartstop, "cell 0 2 3 1,alignx center,aligny top");
		frmJmbsServerControl.getContentPane().add(lblJmbsServerControl, "cell 0 0 3 1,alignx center,aligny top");
	}

	private boolean startServer() {
		boolean retVal = false;
		try {
			LocateRegistry.getRegistry().bind(RemoteServer.REMOTE_NAME, ser);
			System.out.println("The JMBS server loaded and ready to use.");
			retVal = true;
		} catch (AccessException e) {
			System.err.println("Error while running Server: \n" + e.getMessage());
		} catch (RemoteException e) {
			System.err.println("Error while running Server: \n" + e.getMessage());
		} catch (AlreadyBoundException e) {
			System.err.println("Error while running Server: \n" + e.getMessage());
		}
		return retVal;
	}

	private boolean stopServer() {
		boolean retVal = false;

		try {
			LocateRegistry.getRegistry().unbind(RemoteServer.REMOTE_NAME);
			System.out.println("The JMBS server stopped correctly.");
			retVal = true;
		} catch (AccessException e) {
			System.err.println("Error while Stopping Server: \n" + e.getMessage());
		} catch (RemoteException e) {
			System.err.println("Error while Stopping Server: \n" + e.getMessage());
		} catch (NotBoundException e) {
			System.err.println("Error while Stopping Server: \n" + e.getMessage());
		}

		return retVal;
	}
}
