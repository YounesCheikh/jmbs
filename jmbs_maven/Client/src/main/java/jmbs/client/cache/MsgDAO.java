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

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import jmbs.client.CurrentUser;
import jmbs.client.DataTreatment.ImageTreatment;
import jmbs.common.DAO;
import jmbs.common.Message;
import jmbs.common.User;

public class MsgDAO extends DAO {

	public MsgDAO(Connection c) {
		super(c);
		createTable();
	}

	private void createTable() {

		String query = "CREATE TABLE IF NOT EXISTS messages ";
		query += "(idmessage integer primary key, ";
		query += "content string, ";
		query += "time timestamp, ";
		query += "iduser integer, ";
		query += "username string, ";
		query += "userfname string, ";
		query += "picpath string);";
		set(query);
		executeUpdate();

		set("CREATE TABLE IF NOT EXISTS usr (iduser integer not null, idmessage integer); ");
		executeUpdate();
	}

	protected void insertMessage(Message m) {
		// Create the table messages
		set("INSERT INTO messages VALUES(?, ?, ?, ?, ?, ?, ?)");
		setInt(1, m.getId());
		setString(2, m.getMessage());
		setTimestamp(3, m.getTimestamp());
		setInt(4, m.getOwner().getId());
		setString(5, m.getOwner().getName());
		setString(6, m.getOwner().getFname());
		setString(7, "cache/upics/" + m.getOwner().getId() + ".jpg");
		executeUpdate();

		// Create the table usr
		set("INSERT INTO usr VALUES(?, ?)");
		setInt(1, CurrentUser.getId());
		setInt(2, m.getId());
		executeUpdate();
	}

	protected ArrayList<Message> getMessages() {
		ArrayList<Message> msgList = new ArrayList<Message>();

		set("SELECT messages.* FROM messages,usr WHERE usr.iduser= ? and usr.idmessage = messages.idmessage ;");
		setInt(1, CurrentUser.getId());
		ResultSet rs = executeQuery();

		try {
			do {
				Message m = new Message(rs.getInt("idmessage"), new User(
						rs.getInt("iduser"), rs.getString("username"),
						rs.getString("userfname"), ImageTreatment.pathToByte(rs
								.getString("picpath"))),
						rs.getString("content"), rs.getTimestamp("time"));
				msgList.add(m);
			} while (rs.next());
			close(rs);
		} catch (SQLException e) {
			System.out.println("There are no messages !:" + e.getMessage());
		}

		return msgList;
	}

	protected void deleteAll() {
		set("DELETE FROM messages");
		executeUpdate();
		
		set("DELETE FROM usr");
		executeUpdate();
	}
}
