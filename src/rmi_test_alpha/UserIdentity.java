
import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserIdentity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String uname;
	private String upass;

	public UserIdentity(String username, String password) {
		uname = username;
		upass = password;
	}

	public boolean exists() {

		Connection con = new Connect().getConnection();
		// String u;
		String p;

		ResultSet res = Request.querry(con,
				"SELECT name,pass FROM users WHERE name='" + uname + "';");
		try {
			// u = res.getString("name");
			p = res.getString("pass");
		} catch (SQLException e) {
			return false;
		}

		if (upass.equals(p)) {
			// si le mot de passe est correct renvoi vrai
			return true;
		}
		return false;

	}
}
