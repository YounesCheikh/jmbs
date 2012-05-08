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
import java.util.HashMap;
import jmbs.common.DAO;

public class IdentityDAO extends DAO {


	protected IdentityDAO(Connection c) {
		super(c);
		createTable();
	}

	private void createTable() {
		set("CREATE TABLE IF NOT EXISTS identity (mail STRING PRIMARY KEY, pass STRING NOT NULL);");
		executeUpdate();
	}

	protected void add(String mail, String pass) {
		String query = new String();
		query += "insert into identity values(?,?)";
		set(query);
		setString(1, mail);
		setString(2, pass);
		executeUpdate();
	}

	protected HashMap<String, String> getIdentities() {
		HashMap<String, String> identities = new HashMap<String, String>();
		set("Select * from identity");
		ResultSet rs = executeQuery();

		try {
			do {
				identities.put(rs.getString("mail"), rs.getString("pass"));
			} while (rs.next());
			close(rs);
		} catch (SQLException e) {
			System.out.println("There are no saved identities");
		}
		return identities;
	}

	protected void updatePassword(String mail, String hashedPassword) {
		set("update identity set pass=? where mail=?");
		setString(1, hashedPassword);
		setString(2, mail);
		executeUpdate();
	}
	
	protected void deleteAll() {
		set("DELETE FROM identity");
		executeUpdate();
	}
}
