
import java.rmi.Naming;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Main extends UnicastRemoteObject implements InterConnect {
	/**
	 * Starts the rmi Server Responds with true to client if recived the true
	 * identity, else respond with false
	 */
	private static final long serialVersionUID = -3957989931769816627L;

	protected Main() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	public boolean trueIdentity(UserIdentity ui)
			throws java.rmi.RemoteException {
		// renvoi vrai si l'utilisateur existe dans la bdd avec le bon mot de
		// passe
		return (ui.exists());
	}

	public static void main(String args[]) {
		System.setSecurityManager(new RMISecurityManager());
		try {
			Main serveurMain = new Main();
			Naming.rebind("Main", serveurMain);
			System.out.println("Serveur main pret!");
		} catch (Exception e) {
			System.err.println(" Erreur " + e
					+ " lors du lancement du servuer Main");
		}

	}
}
