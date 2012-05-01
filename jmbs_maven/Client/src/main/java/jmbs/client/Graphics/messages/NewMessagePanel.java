package jmbs.client.Graphics.messages;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Timestamp;
import java.util.Calendar;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import jmbs.client.ClientRequests;
import jmbs.client.CurrentUser;
import jmbs.common.Message;

public class NewMessagePanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3372643911095768895L;

	private JPanel contentPane;
	private JTextArea textArea;
	private String newMsgStr;
	private JLabel lblNbchars;
	private JPanel panel;
	private JPanel panel_1;

	/**
	 * Create the panel.
	 */
	public NewMessagePanel(final TimeLinePanel tlpanel) {
		setLayout(new BorderLayout(0, 0));

		newMsgStr = new String();
		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

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

		panel_1 = new JPanel();
		panel.add(panel_1, BorderLayout.EAST);
		panel_1.setLayout(new BorderLayout(0, 0));

		JButton btnSend = new JButton("Send");
		btnSend.setIcon(new ImageIcon(getClass().getResource("/img/btn_send.png")));
		btnSend.setBorder(new EmptyBorder(10, 15, 10, 15));
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
		contentPane.setPreferredSize(new Dimension(100,150));
		//setVisible(false);
		add(contentPane);

	}

}
