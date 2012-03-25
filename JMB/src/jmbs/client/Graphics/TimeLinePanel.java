package jmbs.client.Graphics;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;

import javax.swing.JPanel;

import jmbs.common.Message;

public class TimeLinePanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8369036368823849803L;
	private GridBagConstraints gbc;
	private static int linesNB = 0;
	private static int idLastMessage = 0;

	/**
	 * Create the panel.
	 */
	public TimeLinePanel() {

		gbc = new GridBagConstraints();
		gbc.anchor = GridBagConstraints.NORTH;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.ipady = 0;

		GridBagLayout gbl_timeLinePanel = new GridBagLayout();
		gbl_timeLinePanel.columnWidths = new int[] { 0, 0 };
		gbl_timeLinePanel.rowHeights = new int[] { 0, 0 };
		gbl_timeLinePanel.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gbl_timeLinePanel.rowWeights = new double[] { 1.0, Double.MAX_VALUE };
		this.setLayout(gbl_timeLinePanel);
		/*
		 * putMessage(new MsgPanel(new Message(new User("Younes", "CHEIKH",
		 * "test@test.com"), "",
		 * "can any one recommend me a good grammar checker for Latex? #latex",
		 * new Date(0)))); putMessage(new MsgPanel(new Message(new
		 * User("Benjamin", "BABIC", "test@test.com"), "",
		 * "We agree with @YourAnonNews that #Antisec was created under #FBI supervision!"
		 * , new Date(0)))); putMessage(new MsgPanel(new Message(new
		 * User("Benjamin", "BABIC", "test@test.com"), "",
		 * "FBI actually leak Stratfor e-mails just to bust Julian Assange? goo.gl/fb/ZGxiW #Security #THN #news"
		 * , new Date(0)))); putMessage(new MsgPanel(new Message(new
		 * User("Younes", "CHEIKH", "test@test.com"), "",
		 * "grep Privileged.*true modules/exploits/ -rl | wc -l  # metasploit has 274 remote roots!  =)"
		 * , new Date(0)))); putMessage(new MsgPanel(new Message(new
		 * User("Younes", "CHEIKH", "test@test.com"), "",
		 * "Greg Smith Isn't A Whistleblower, He's Just A Goldman Sachs Executive Having A Midlife Crisis onforb.es/w854GZ"
		 * , new Date(0)))); putMessage(new MsgPanel(new Message(new
		 * User("Benjamin", "BABIC", "test@test.com"), "",
		 * "well I suppose this is almost exactly what I was talking about in regards to MS12-020 bit.ly/wqv64C"
		 * , new Date(0)))); putMessage(new MsgPanel(new Message(new
		 * User("Younes", "CHEIKH", "test@test.com"), "",
		 * "It seems hypocritical to demand that the govt stay out of your life, and at the same time demand that it intrudes into the lives of others."
		 * , new Date(0)))); putMessage(new MsgPanel(new Message(new
		 * User("Younes", "CHEIKH", "test@test.com"), "",
		 * "I wonder how much the Goldman Sachs culture changed vs. how much Greg Smith realized as he rose through the ranks: nyti.ms/wunLqU"
		 * , new Date(0))));
		 */
	}

	public void putMessage(Component obj) {
		gbc.gridx = 0;
		gbc.gridy = linesNB;
		this.add(obj, gbc);
		// timeLinePanel.validate();
		linesNB++;
		this.validate();
		this.updateUI();
	}

	public void putList(ArrayList<Message> msgList) {
		int oldIdMsg = getLastIdMsg();
		if (msgList != null) {
			for (Message m : msgList) {
				putMessage(new MsgPanel(m));
				idLastMessage= m.getId();
			}
			if (getComponentCount()<5 && oldIdMsg!= getLastIdMsg())
				for (int i = 0; i < 20; i++) {
					putMessage(new JPanel());
				}
		}
	}

	public int getLastIdMsg() {
		return idLastMessage;
	}
}
