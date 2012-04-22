package jmbs.client;

import java.rmi.RemoteException;
import java.util.ArrayList;

import jmbs.client.Graphics.SayToUser;
import jmbs.common.Message;
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
			SayToUser.error("RemoteException", e.getMessage());
		}
		return retList;
	}

	public static void logOut(int idUser) {
		try {
			ClientRequests.server.logOut(idUser);
		} catch (RemoteException e) {
			SayToUser.error("RemoteException", e.getMessage());
		}
	}

	public static User connectUser(String em, String psw) {
		User u = null;
		try {
			u = ClientRequests.server.connectUser(em, psw);
		} catch (RemoteException e) {
			SayToUser.error("RemoteException", e.getMessage());
		} catch (SecurityException e) {
			SayToUser.error("SecurityException", e.getMessage());
		}
		return u;
	}

	public static boolean follows(int idfollower, int idfollowed) {
		boolean retVal = false;
		try {
			retVal = ClientRequests.server.follows(idfollower, idfollowed);
		} catch (RemoteException e) {
			SayToUser.error("RemoteException", e.getMessage());
		}

		return retVal;
	}

	public static boolean unFollow(int idfollower, int idfollowed) {
		boolean retVal = false;
		try {
			retVal = ClientRequests.server.unFollow(idfollower, idfollowed);
		} catch (RemoteException e) {
			SayToUser.error("RemoteException", e.getMessage());
		}

		return retVal;
	}

	public static boolean close(int idUser) {
		boolean retVal = false;
		if (ClientRequests.server != null)
			try {
				retVal = ClientRequests.server.close(idUser);
			} catch (RemoteException e1) {
				SayToUser.error("RemoteException", e1.getMessage());
			}
		return retVal;
	}

	public static ArrayList<Message> getLatestTL(int iduser, int idlastmessage,
			int maxMsg) {
		ArrayList<Message> retList = new ArrayList<Message>();
		try {
			retList = new ClientRequests().getConnection().getLatestTL(iduser,
					idlastmessage, maxMsg);
		} catch (RemoteException e1) {
			SayToUser.error("RemoteException", e1.getMessage());
		}
		return retList;
	}

	public static int addMessage(Message m) {
		int retVal = 0;
		try {
			retVal = ClientRequests.server.addMessage(m);
		} catch (RemoteException e1) {
			SayToUser.error("RemoteException", e1.getMessage());
		}
		return retVal;
	}

	public static boolean createUser(User u, String hashedpassword) {
		boolean retVal = false;
		try {
			if (ClientRequests.server != null)
				retVal = ClientRequests.server.createUser(u, hashedpassword);
		} catch (RemoteException e) {
			SayToUser.error("RemoteException", e.getMessage());
		}
		return retVal;
	}

	public static ArrayList<User> searchUser(String userName, int param) {
		ArrayList<User> retList = null;
		try {
			retList = ClientRequests.server.searchUser(userName, 0);
		} catch (RemoteException e) {
			SayToUser.error("RemoteException", e.getMessage());
		}
		return retList;
	}

	public static ArrayList<User> getFollowers(User u) {
		ArrayList<User> retList = null;
		try {
			retList = ClientRequests.server.getFollowers(u);
		} catch (RemoteException e) {
			SayToUser.error("RemoteException", e.getMessage());
		}
		return retList;
	}

	public static Project createProject(String name, int iduser) {
		Project retVal = null;
		try {
			retVal = ClientRequests.server.createProject(name, iduser);
		} catch (RemoteException e) {
			SayToUser.error("RemoteException", e.getMessage());
		}
		return retVal;
	}

	public static ArrayList<Project> searchForProject(String likeName) {
		ArrayList<Project> retList = null;
		try {
			retList = ClientRequests.server.searchForProject(likeName);
		} catch (RemoteException e) {
			SayToUser.error("RemoteException", e.getMessage());
		}
		return retList;
	}

	public static boolean unParticipate(int idUser, int idProject) {
		boolean retVal = false;
		try {
			retVal = ClientRequests.server.unParticipate(idUser, idProject);
		} catch (RemoteException e) {
			SayToUser.error("RemoteException", e.getMessage());
		}
		return retVal;
	}

	public static boolean participate(int idUser, int idProject) {
		boolean retVal = false;
		try {
			retVal = ClientRequests.server.participate(idUser, idProject);
		} catch (RemoteException e) {
			SayToUser.error("RemoteException", e.getMessage());
		}
		return retVal;
	}

}
