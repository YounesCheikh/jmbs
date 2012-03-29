/**
 * 
 */
package jmbs.server;

import java.awt.EventQueue;
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
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ServerWindow window = new ServerWindow();
					window.frmJmbsServerControl.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
