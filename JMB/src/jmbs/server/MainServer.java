/**
 * 
 */
package jmbs.server;

import java.rmi.RemoteException;
import java.sql.SQLException;

/**
 * @author ycheikh
 * 
 */
public class MainServer {

	/**
	 * @param args
	 */

	public static void main(String[] args) throws SQLException {

		try {
			new Requests("serverjmbs");
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		/*
		 * User u; Connection con = new Connect().getConnection(); UserDAO udao
		 * = new UserDAO(con);
		 * 
		 * 
		 * u = udao.getUser("younes.cheikh@gmail.com");
		 * u.setProjects(udao.getProjects(u));
		 * 
		 * System.out.println(u); System.out.println(u.getProjects());
		 * 
		 * //ArrayList<User> user; //user = udao.findUsers("user");
		 * 
		 * System.out.println(udao.checkPassword(u,"user5pass"));
		 * //System.out.println(user);
		 * 
		 * con.close();
		 */
	}

}
