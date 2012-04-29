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

package jmbs.client.Graphics.messages;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.sql.Timestamp;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import jmbs.client.ClientRequests;
import jmbs.client.CurrentUser;
import jmbs.client.SysConf;
import jmbs.common.Message;

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
	private JPanel panel;
	private JPanel panel_1;

	/**
	 * Create the frame.
	 */
	public NewMessageFrame(final TimeLinePanel tlpanel) {
		// setLocationRelativeTo(null);
		SysConf.centerThisFrame(this);
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
		setSize(320, 209);
		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportBorder(new TitledBorder(null, "",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		scrollPane
				.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

		textArea = new JTextArea();
		textArea.setWrapStyleWord(true);
		textArea.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if (textArea.getText().equals(""))
					lblNbchars.setText("0");
				else {
					if (textArea.getText().length() >= 599)
						lblNbchars.setForeground(Color.RED);
					else
						lblNbchars.setForeground(getForeground());
					lblNbchars.setText("" + (textArea.getText().length() + 1));
					lblNbchars.updateUI();
				}
			}
		});
		textArea.setLineWrap(true);
		textArea.setBackground(new Color(245, 245, 245));
		scrollPane.setViewportView(textArea);
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.add(scrollPane);

		panel = new JPanel();
		contentPane.add(panel, BorderLayout.SOUTH);
		panel.setLayout(new BorderLayout(0, 0));

		JButton btnCancel = new JButton("Cancel");
		panel.add(btnCancel, BorderLayout.WEST);
		btnCancel.setForeground(Color.BLACK);

		panel_1 = new JPanel();
		panel.add(panel_1, BorderLayout.EAST);
		panel_1.setLayout(new BorderLayout(0, 0));

		JButton btnSend = new JButton("Send");
		panel_1.add(btnSend, BorderLayout.EAST);
		btnSend.setForeground(Color.BLACK);

		lblNbchars = new JLabel("" + (textArea.getText().length()));
		panel_1.add(lblNbchars, BorderLayout.CENTER);
		lblNbchars.setForeground(Color.BLACK);
		btnSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// prevent the to send the emepty and the very long messages
				// (>600 chars) to the DB
				if (textArea.getText().length() > 0
						&& textArea.getText().length() <= 599) {
					newMsgStr = textArea.getText();// replaceAll("'", "$'$");
					Message m = new Message(CurrentUser.get(), newMsgStr,
							new Timestamp(Calendar.getInstance()
									.getTimeInMillis()));
					Integer getIdMsg = 0;
					getIdMsg = ClientRequests.addMessage(m);
					System.out.println("" + getIdMsg);
					if (!getIdMsg.equals(-1)) {
						tlpanel.putMessage(new MsgPanel(new Message(CurrentUser
								.get(), textArea.getText(), new Timestamp(
								Calendar.getInstance().getTimeInMillis()))));
						tlpanel.setLastIdMsg(getIdMsg);
						textArea.setText("");
						setVisible(false);
					}

				}
			}
		});
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textArea.setText("");
				setVisible(false);
			}
		});
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
