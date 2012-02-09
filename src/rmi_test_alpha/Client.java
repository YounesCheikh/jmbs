
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.rmi.Naming;
import java.rmi.RMISecurityManager;

public class Client {
	public static void main(String args[]) {
		// verification des arguments
		if (args.length != 1) {
			System.err.println("Syntaxe: pg url_service_rmi");
			System.exit(1);
		}
		System.setSecurityManager(new RMISecurityManager());
		// dialogue client-serveur
		String urlService = args[0];
		BufferedReader in = null;
		String uname = null;
		String passwd = null;
		InterConnect serveur = null;

		try {
			// ouverture du flux clavier
			in = new BufferedReader(new InputStreamReader(System.in));
			// localisation du service
			serveur = (InterConnect) Naming.lookup(urlService);
			// boucle de lecture des msg à envoyer au serveur d'écho
			// System.out.print("Message: ");
			// msg = in.readLine().toLowerCase().trim();

			boolean goodpassword = false;
			UserIdentity ui = null;
			do {
				System.out.print("Login: ");
				uname = in.readLine().toLowerCase().trim();

				System.out.print("Login: ");
				passwd = in.readLine().toLowerCase().trim();

				ui = new UserIdentity(uname, passwd);
				// envoi du msg au serveur et réception de la réponse
				goodpassword = serveur.trueIdentity(ui);
				// suivi
				System.out.println("Reponse serveur: " + goodpassword);
				// msg suivant

			} while (!goodpassword); // while
			// c'est fini
			System.exit(0);
			// gestion des erreurs
		} catch (Exception e) {
			System.err.println("erreur : " + e);
			System.exit(2);
		} // try
	}
}
