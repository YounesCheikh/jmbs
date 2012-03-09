package jmbs.client;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class TLPanel extends JPanel implements KeyListener {

	/**
	 * Un panneau qui affiche le timeline (Les diffirent messages)
	 */

	private static final long serialVersionUID = 7120390944031252968L;

	private JEditorPane myMessageList = new JEditorPane();
	private JScrollPane scroll;
	private UserTimeLine timeline;
	private final JTextField text;

	public TLPanel(UserTimeLine utl) {
		this.timeline = utl;
		text = new JTextField();
		scroll = new JScrollPane();
		JButton send = new JButton("Send");
		this.setLayout(new BorderLayout());

		myMessageList.setBackground(Color.decode("#FBFBFB"));
		myMessageList.setForeground(Color.LIGHT_GRAY);
		myMessageList.setEditable(false);
		myMessageList.setContentType("text/html");
		myMessageList.setText("<h3>Welcome to JMBS</h3><hr>");
		scroll.getViewport().add(myMessageList);
		updateView();
		JPanel jp = new JPanel();
		jp.setLayout(new BorderLayout());
		jp.add(send, java.awt.BorderLayout.EAST);
		jp.add(text, java.awt.BorderLayout.CENTER);
		this.add(scroll, BorderLayout.CENTER);
		this.add(jp, BorderLayout.SOUTH);

		send.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sendButtonIsClicked(text.getText());
				text.setText("");
			}
		});
		text.addKeyListener(this);
	}

	/**
	 * Adds a message to the list of received messages
	 * 
	 * @param from
	 *            the name of the message's sender
	 * @param message
	 *            the message to display
	 */
	public void displayMessage(String from, String message) {
		String content = myMessageList.getText();
		int end = content.indexOf("</body>");
		content = content.substring(0, end);
		content += "<img style=\"float:right;\" align=\"left\" height=\"80\" width=\"80\" src=\"http://atmac.org/wp-content/uploads/gravatar-blank.jpg\" />";
		content += "<br><font face=\"Comic Sans MS\" color=\"#111111\"><strong>" + from + " :</strong></font><br /> ";
		content += "<font face=\"Comic Sans MS\" >" + message + "</font><HR \" WIDTH=\"100%\"> </body></html>";
		myMessageList.setText(content);
	}

	public JScrollPane getScroll() {
		return this.scroll;
	}

	/**
	 * Send a message <br>
	 * Currently, only displays the message in the console
	 * 
	 * @param message
	 *            the message to send
	 */
	public void sendButtonIsClicked(String message) {
		if (!message.equals("")) {
			displayMessage(this.timeline.getCurrentUser().getFullName(), message);
		}
	}

	private void updateView() {
		for (int i = 0; i < this.timeline.getAll().size(); i++) {
			displayMessage(this.timeline.getAll().get(i).getOwner().getFullName(), this.timeline.getAll().get(i).getMessage());
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyChar() == 10)
			if (!text.getText().equals("")) {
				sendButtonIsClicked(text.getText());
				text.setText("");
			}

	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}
}
