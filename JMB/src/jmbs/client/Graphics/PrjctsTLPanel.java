package jmbs.client.Graphics;

import java.awt.Component;
import java.util.ArrayList;

import javax.swing.JPanel;

import jmbs.common.Message;

import net.miginfocom.swing.MigLayout;

public class PrjctsTLPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5444522923856547520L;
	private static int idLastMessage = 0;
	/**
	 * Create the panel.
	 */
	public PrjctsTLPanel() {
		setLayout(new MigLayout("", "[grow 80,fill]", "[]"));
	}
	
	public void putMessage(Component obj) {
		// put new element and go to next row
		this.add(obj, "wrap",0);
		this.updateUI();
	}

	public void putList(ArrayList<Message> msgList) {
		if (msgList != null) {
			for (Message m : msgList) {
				putMessage(new MsgPanel(m));
				idLastMessage= m.getId();
			}
		}
	}

	public int getLastIdMsg() {
		return idLastMessage;
	}
	
	public void setLastIdMsg(int id) {
		idLastMessage = id;
	}
	
}
