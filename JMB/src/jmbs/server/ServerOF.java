package jmbs.server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import jmbs.common.RemoteRequests;
import jmbs.common.RemoteServer;

/**
 * This class represents the server object factory. In our application it only needs to create a single Request object every time
 * a user launches his application. The purpose of this class is to enable every RemoteRequests object to be client
 * specific. That will enable storing client specific informations such as connection information, IP address which are
 * useful to server logging and security.  
 * 
 * @author benbabic
 */
public class ServerOF extends UnicastRemoteObject implements RemoteServer{

	private static final long serialVersionUID = 1L;
	
	protected ServerOF() throws RemoteException {
		super();
	}

	/**
	 * Creates a RemoteRequests object.
	 * @return RemoteRequests - the RemoteRequests object to be used by client to make requests on the server.
	 */
	public RemoteRequests connect() throws RemoteException{
		ServerMonitor sm = ServerMonitor.getInstance();
		Requests r = new Requests(true,sm.generateKey());
		sm.addConnection(r);
		return r;
	}
}
