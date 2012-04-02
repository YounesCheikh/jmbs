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

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.rmi.RemoteException;
import java.sql.Date;

import java.awt.Color;
import java.awt.Point;

import javax.swing.border.TitledBorder;

import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;

import jmbs.client.ClientRequests;
import jmbs.client.CurrentUser;
import jmbs.client.SysConf;
import jmbs.common.Message;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class NewMessageFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7748119817251021641L;

	private JPanel contentPane;
	private JTextArea textArea;
	private String newMsgStr;
	private static Point point = new Point();
	private JLabel lblNbchars;

	/**
	 * Create the frame.
	 */
	public NewMessageFrame(final TimeLinePanel tlpanel) {
		//setLocationRelativeTo(null);
		new SysConf().centerThisFrame(this);
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

		newMsgStr = new String();
		setTitle("Write new message");
		setAlwaysOnTop(true);
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 320, 220);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportBorder(new TitledBorder(null, "",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		scrollPane
				.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

		JButton btnSend = new JButton("Send");
		btnSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// prevent the to send the emepty and the very long messages (>600 chars) to the DB
				if (textArea.getText().length() > 0 && textArea.getText().length()<=599) {
					newMsgStr = textArea.getText();//replaceAll("'", "$'$");
					Message m = new Message(new CurrentUser().get(), newMsgStr,
							new Date(new java.util.Date().getTime()));
					Integer getIdMsg = 0;
					try {

						getIdMsg = new ClientRequests().getConnection()
								.addMessage(m);
					} catch (RemoteException e1) {
						// TODO Auto-generated catch block
						// e1.printStackTrace();
						System.out.print("Can't send to server!\n"
								+ e1.getMessage());
					}
					System.out.println(""+getIdMsg);
					if (!getIdMsg.equals(-1)) {
						tlpanel.putMessage(new MsgPanel(new Message(
								new CurrentUser().get(), textArea.getText(),
								new Date(new java.util.Date().getTime()))));
						tlpanel.setLastIdMsg(getIdMsg);
						textArea.setText("");
						setVisible(false);
					}

				}
			}
		});

		textArea = new JTextArea();
		textArea.setWrapStyleWord(true);
		textArea.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if (textArea.getText().equals(""))
					lblNbchars.setText("0");
				else {
					if(textArea.getText().length()>=599) lblNbchars.setForeground(Color.RED);
					else lblNbchars.setForeground(getForeground());
					lblNbchars.setText("" + (textArea.getText().length() + 1));
				lblNbchars.updateUI();
				}
			}
		});
		textArea.setLineWrap(true);
		textArea.setBackground(new Color(245, 245, 245));
		scrollPane.setViewportView(textArea);
		contentPane.setLayout(new MigLayout("", "[81px][148px][81px]",
				"[156px][42px]"));

		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});

		lblNbchars = new JLabel("" + (textArea.getText().length()));
		contentPane.add(lblNbchars, "cell 0 1");
		contentPane.add(btnCancel, "cell 1 1,alignx right,growy");
		contentPane.add(btnSend, "cell 2 1,grow");
		contentPane.add(scrollPane, "cell 0 0 3 1,grow");
	}

	/**
	 * we'll use this in future
	 * 
	 * @return
	 */
	public String getMsg() {
		return this.newMsgStr;
	}
}
