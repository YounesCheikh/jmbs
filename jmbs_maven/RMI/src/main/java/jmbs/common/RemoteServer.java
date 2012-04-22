package jmbs.common;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * This is the common interface for a server factory object. It is created and bind in rmi registry when the server is started.
 * 
 * @author benbabic
 */
public interface RemoteServer extends Remote{

	public static final String REMOTE_NAME = "Server";
	
	/**
	 * Creates a RemoteRequests object.
	 * @return RemoteRequests - the RemoteRequests object to be used by client to make requests on the server.
	 */
	public String connect() throws RemoteException;
}
