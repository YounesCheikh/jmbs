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
	 * @throws SQLException
	 */
	public static void main(String[] args) throws SQLException {

		try {
			new Requests("serverjmbs");
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
