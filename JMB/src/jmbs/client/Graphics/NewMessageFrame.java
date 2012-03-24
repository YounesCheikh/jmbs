package jmbs.client.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import javax.swing.LayoutStyle.ComponentPlacement;
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

	/**
	 * Create the frame.
	 */
	public NewMessageFrame(final TimeLinePanel tlpanel) {
		setLocationRelativeTo(null);
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

				if (textArea.getText().length() > 0) {
					// Replace
					// the
					// single
					// quote
					// with
					// back
					// slash
					// for
					// sql.
					newMsgStr = textArea.getText().replaceAll("'", "\\\\'");
					Message m = new Message(new CurrentUser().get(), "",
							newMsgStr, new Date(new java.util.Date().getTime()));
					boolean sendSuccessed = false;
					try {
						sendSuccessed = new ClientRequests().getConnection()
								.addMessage(m);
					} catch (RemoteException e1) {
						// TODO Auto-generated catch block
						// e1.printStackTrace();
						System.out.print("Can't send to server!\n"
								+ e1.getMessage());
					}
					if (sendSuccessed) {

						tlpanel.putMessage(new MsgPanel(new Message(
								new CurrentUser().get(), "",
								textArea.getText(), new Date(
										new java.util.Date().getTime()))));
						textArea.setText("");
						setVisible(false);
					}
				}
			}
		});

		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(gl_contentPane
				.createParallelGroup(Alignment.TRAILING)
				.addGroup(
						gl_contentPane
								.createSequentialGroup()
								.addContainerGap()
								.addComponent(btnCancel,
										GroupLayout.PREFERRED_SIZE, 75,
										GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED,
										148, Short.MAX_VALUE)
								.addComponent(btnSend).addContainerGap())
				.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 310,
						Short.MAX_VALUE));
		gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(
				Alignment.TRAILING).addGroup(
				gl_contentPane
						.createSequentialGroup()
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE,
								156, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(
								gl_contentPane
										.createParallelGroup(Alignment.LEADING)
										.addComponent(btnSend,
												GroupLayout.DEFAULT_SIZE, 42,
												Short.MAX_VALUE)
										.addComponent(btnCancel,
												GroupLayout.PREFERRED_SIZE, 42,
												GroupLayout.PREFERRED_SIZE))
						.addContainerGap()));

		textArea = new JTextArea();
		textArea.setBackground(new Color(245, 245, 245));
		scrollPane.setViewportView(textArea);
		contentPane.setLayout(gl_contentPane);
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
