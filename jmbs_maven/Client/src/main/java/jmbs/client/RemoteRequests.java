package jmbs.client;

import java.rmi.RemoteException;
import java.util.ArrayList;

import jmbs.client.Graphics.SayToUser;
import jmbs.common.Project;
import jmbs.common.User;

public class RemoteRequests {

	public RemoteRequests() {

	}

	public static ArrayList<Project> getUserProjects(int idUser) {
		ArrayList<Project> retList = new ArrayList<Project>();
		try {
			retList = ClientRequests.server.getUserProjects(idUser);
		} catch (RemoteException e) {
			new SayToUser(e.getMessage(), true);
		}
		return retList;
	}

	public static void logOut(int idUser) {
		try {
			ClientRequests.server.logOut(idUser);
		} catch (RemoteException e) {
			new SayToUser(e.getMessage(), true);
		}
	}

	public static User connectUser(String em, String psw) {
		User u = null;
		try {
			u = ClientRequests.server.connectUser(em, psw);
		} catch (RemoteException e) {
			new SayToUser(e.getMessage(), true);
		} catch (SecurityException e) {
			new SayToUser(e.getMessage(), true);
		}
		return u;
	}
	
	public static boolean follows(int idfollower, int idfollowed) {
		boolean retVal = false;
		try {
			retVal = ClientRequests.server.follows(idfollower, idfollowed);
		} catch (RemoteException e) {
			new SayToUser(e.getMessage(), true);
		}
		
		return retVal;
	}
	
	public static boolean unFollow(int idfollower, int idfollowed) {
		boolean retVal = false;
		try {
			retVal = ClientRequests.server.unFollow(idfollower, idfollowed);
		} catch (RemoteException e) {
			new SayToUser(e.getMessage(), true);
		}
		
		return retVal;
	}
	
	public static boolean close(int idUser) {
		boolean retVal = false;
		if (ClientRequests.server != null)
			try {
				ClientRequests.server.close(idUser);
			} catch (RemoteException e1) {
				// TODO Auto-generated catch block
				// e1.printStackTrace();
				System.out.println(e1.getMessage());
			}
		return retVal;
	}
}
