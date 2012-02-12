package Server;

import java.sql.Connection;
import java.sql.SQLException;

public class test {
	public static void main(String[] args) throws SQLException {
		User u;
		Connection con = new Connect().getConnection();
		u = (new UserDAO(con)).createUser("user5@localhost");
		System.out.println(u);
		System.out.println(u.getProjects());
		con.close();
		
	}
}
