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
import java.util.HashMap;

import jmbs.client.DataTreatment.ImageTreatment;
import jmbs.common.Message;

public class CacheRequests {

	private static Connection con ;

	public CacheRequests() {
		con = Connect.getInstance().getConnection();
	}
	public void insertIdentity(String mail, String pass) {
		IdentityDAO idao = new IdentityDAO(con);
		idao.add(mail, pass);
	}

	public HashMap<String, String> getIdentities() {
		IdentityDAO idao = new IdentityDAO(con);
		HashMap<String, String> retMap = idao.getIdentities();
		return retMap;
	}

	public void updatePassword(String mail, String hashedPassword) {
		IdentityDAO idao = new IdentityDAO(con);
		idao.updatePassword(mail, hashedPassword);
	}

	public static void removeAllIdentities() {
		IdentityDAO idao = new IdentityDAO(con);
		idao.deleteAll();
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
		Connect.initInstantce();
		try {
			con.close();
		} catch (SQLException e) {
			// Ignore
		}
	}

}
