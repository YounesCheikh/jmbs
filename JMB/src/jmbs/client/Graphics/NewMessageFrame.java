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
import javax.swing.JProgressBar;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Date;

import jmbs.common.Message;
import jmbs.common.User;
import java.awt.Color;
import javax.swing.border.TitledBorder;
public class NewMessageFrame extends JFrame {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7748119817251021641L;

	private JPanel contentPane;
	private JTextArea textArea;
	private String newMsgStr;

	/**
	 * Create the frame.
	 */
	public NewMessageFrame(final TimeLinePanel tlpanel) {
		newMsgStr = new String();
		setTitle("Write new message");
		setAlwaysOnTop(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 320, 220);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
		JButton btnSend = new JButton("Send");
		btnSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				newMsgStr = new String(textArea.getText());
				textArea.setText("");
				tlpanel.putMessage(new MsgPanel(new Message(new User("Younes", "CHEIKH", "test@test.com"), "", newMsgStr, new Date(0))));
				setVisible(false);
			}
		});
		
		JProgressBar progressBar = new JProgressBar();
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(progressBar, GroupLayout.DEFAULT_SIZE, 223, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnSend)
					.addContainerGap())
				.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 310, Short.MAX_VALUE)
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 156, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(progressBar, GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE)
						.addComponent(btnSend, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addContainerGap())
		);
		
		textArea = new JTextArea();
		textArea.setBackground(new Color(245, 245, 245));
		scrollPane.setViewportView(textArea);
		contentPane.setLayout(gl_contentPane);
	}
	
	/**
	 * we'll use this in future 
	 * @return
	 */
	public String getMsg() {
		return this.newMsgStr;
	}
}
