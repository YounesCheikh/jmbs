package jmbs.client.cache;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import jmbs.client.CurrentUser;
import jmbs.client.DataTreatment.ImageTreatment;
import jmbs.common.Message;
import jmbs.common.User;

public class MsgDAO extends CacheDAO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5446207367173067814L;

	public MsgDAO(Connection c) {
		super(c);
		createTable();
	}

	private void createTable() {
		set("CREATE TABLE IF NOT EXISTS messages (idcurrentuser integer not null, id integer primary key,"
				+ " content string, time timestamp, iduser integer, username string , userfname string, picpath string );");
		executeUpdate();
	}

	protected void insertMessage(Message m) {
		String query = new String();
		query += "insert into messages values(?, ?, ?, ?, ?, ?, ?, ?)";
		set(query);
		setInt(1, CurrentUser.getId());
		setInt(2, m.getId());
		setString(3, m.getMessage());
		setTimestamp(4, m.getTimestamp());
		setInt(5, m.getOwner().getId());
		setString(6, m.getOwner().getName());
		setString(7, m.getOwner().getFname());
		setString(8, "upics/" + m.getOwner().getId() + ".jpg");
		executeUpdate();
	}

	protected ArrayList<Message> getMessages() {
		ArrayList<Message> msgList = new ArrayList<Message>();

		set("SELECT * FROM messages where idcurrentuser = ?;");
		setInt(1, CurrentUser.getId());
		ResultSet rs = executeQuery();

		try {
			do {
				Message m = new Message(rs.getInt("id"), new User(
						rs.getInt("iduser"), rs.getString("username"), rs.getString("userfname"),
						ImageTreatment.pathToByte(rs.getString("picpath"))),
						rs.getString("content"), rs.getTimestamp("time"));
				msgList.add(m);
			} while (rs.next());
			close(rs);
		} catch (SQLException e) {
			System.out.println("There are no messages !:"+e.getMessage());
		}

		return msgList;
	}
	
	protected void deleteAll() {
		set("DELETE FROM messages");
		executeUpdate();
	}

}
