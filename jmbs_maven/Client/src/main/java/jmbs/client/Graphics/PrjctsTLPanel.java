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