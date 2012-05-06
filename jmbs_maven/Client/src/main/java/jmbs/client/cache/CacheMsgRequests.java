/*
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
 */

package jmbs.client.cache;

import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import jmbs.client.DataTreatment.ImageTreatment;
import jmbs.common.Message;

public class CacheMsgRequests {

	private static Connection con = Connect.getInstance().getConnection();

	public CacheMsgRequests() {

	}

	public void addMessage(Message m) {
		File f = new File("upics/+" + m.getOwner().getId() + ".jpg");
		if (!f.exists()) {
			ImageTreatment.exportPicture(m.getOwner().getId(),
					ImageTreatment.convert(m.getOwner().getPic()), "jpg");
		}
		MsgDAO mdao = new MsgDAO(con);
		mdao.insertMessage(m);
	}

	public ArrayList<Message> getMessages() {
		MsgDAO mdao = new MsgDAO(con);

		ArrayList<Message> retList = mdao.getMessages();
		return retList;
	}

	public static void removeAllMsgs() {
		MsgDAO mdao = new MsgDAO(con);
		mdao.deleteAll();
	}
	
	public static void closeConnection() {
		try {
			con.close();
		} catch (SQLException e) {
			// Ignore
		}
	}
}
