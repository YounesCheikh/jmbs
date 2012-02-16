/**
 * 
 */
package jmbs.client;

//import java.rmi.Naming;
//import java.rmi.RMISecurityManager;
//import jmbs.remote.RemoteUser;

/**
 * @author yc
 *
 */
public class Client {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		//System.out.println(new HashPassword("Younes").getHashed());
		new Window();
		/*try {
			
			System.setSecurityManager(new RMISecurityManager());
			RemoteUser ru = (RemoteUser) Naming.lookup("rmi://localhost/Serveur");
			System.out.println(ru.userExists("root", "rootpass"));
			Window w = new Window();
			//System.out.println(ru.userExists(w.getEntredUserName(), w.getEntredPassword()));
			
			//ru.userExists("root", "rootpass");
			
		} catch (Exception e) {
			System.out.println("Connexion impossible!");
			e.printStackTrace();
		}
		*/
	}
}
