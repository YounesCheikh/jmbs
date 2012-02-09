
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface InterConnect extends Remote {
	public boolean trueIdentity(UserIdentity ui) throws RemoteException;
}
