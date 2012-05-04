package jmbs.client.cache;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class IdentityDAO extends CacheDAO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5185528505889859689L;

	protected IdentityDAO(Connection c) {
		super(c);
		createTable();
	}

	private void createTable() {
		set("CREATE TABLE IF NOT EXISTS identity (mail string primary key, pass string);");
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
