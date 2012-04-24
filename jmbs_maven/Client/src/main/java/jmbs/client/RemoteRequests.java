package jmbs.client;

import java.rmi.RemoteException;
import java.sql.SQLException;
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
		if (ClientRequests.server != null)
			try {
				retList = ClientRequests.server.getUserProjects(idUser);
			} catch (RemoteException e) {
				SayToUser.error("RemoteException", e.getMessage());
			}
		else {
			SayToUser.error("Error connection!",
					"can't establish a reliable data connection to the server");
		}
		return retList;
	}

	public static void logOut(int idUser) {
		if (ClientRequests.server != null)
			try {
				ClientRequests.server.logOut(idUser);
			} catch (RemoteException e) {
				SayToUser.error("RemoteException", e.getMessage());
			}
		else {
			SayToUser.error("Error connection!",
					"can't establish a reliable data connection to the server");
		}
	}

	public static User connectUser(String em, String psw) {
		User u = null;
		if (ClientRequests.server != null)
			try {
				u = ClientRequests.server.connectUser(em, psw);
			} catch (RemoteException e) {
				SayToUser.error("RemoteException", e.getMessage());
			} catch (SecurityException e) {
				SayToUser.error("SecurityException", e.getMessage());
			}
		else {
			SayToUser.error("Error connection!",
					"can't establish a reliable data connection to the server");
		}
		return u;
	}

	public static boolean follows(int idfollower, int idfollowed) {
		boolean retVal = false;
		if (ClientRequests.server != null)
			try {
				retVal = ClientRequests.server.follows(idfollower, idfollowed);
			} catch (RemoteException e) {
				SayToUser.error("RemoteException", e.getMessage());
			}
		else {
			SayToUser.error("Error connection!",
					"can't establish a reliable data connection to the server");
		}

		return retVal;
	}

	public static boolean unFollow(int idfollower, int idfollowed) {
		boolean retVal = false;
		if (ClientRequests.server != null)
			try {
				retVal = ClientRequests.server.unFollow(idfollower, idfollowed);
			} catch (RemoteException e) {
				SayToUser.error("RemoteException", e.getMessage());
			}
		else {
			SayToUser.error("Error connection!",
					"can't establish a reliable data connection to the server");
		}

		return retVal;
	}

	public static boolean close() {
		boolean retVal = false;
		if (ClientRequests.server != null)
			try {
				retVal = ClientRequests.server.close();
			} catch (RemoteException e1) {
				SayToUser.error("RemoteException", e1.getMessage());
			}
		else {
			SayToUser.error("Error connection!",
					"can't establish a reliable data connection to the server");
		}
		return retVal;
	}

	public static ArrayList<Message> getLatestTL(int iduser, int idlastmessage,
			int maxMsg) {
		ArrayList<Message> retList = new ArrayList<Message>();
		if (ClientRequests.server != null)
			try {
				retList = ClientRequests.server.getLatestTL(
						iduser, idlastmessage, maxMsg);
			} catch (RemoteException e1) {
				SayToUser.error("RemoteException", e1.getMessage());
			}
		else {
			SayToUser.error("Error connection!",
					"can't establish a reliable data connection to the server");
		}
		return retList;
	}

	public static int addMessage(Message m) {
		int retVal = 0;
		if (ClientRequests.server != null)
			try {
				retVal = ClientRequests.server.addMessage(m);
			} catch (RemoteException e1) {
				SayToUser.error("RemoteException", e1.getMessage());
			}
		else {
			SayToUser.error("Error connection!",
					"can't establish a reliable data connection to the server");
		}
		return retVal;
	}

	public static boolean createUser(User u, String hashedpassword) {
		boolean retVal = false;
		if (ClientRequests.server != null)
			try {
				retVal = ClientRequests.server.createUser(u, hashedpassword);
			} catch (RemoteException e) {
				SayToUser.error("RemoteException", e.getMessage());
			}
		else {
			SayToUser.error("Error connection!",
					"can't establish a reliable data connection to the server");
		}
		return retVal;
	}

	public static ArrayList<User> searchUser(String userName, int param) {
		ArrayList<User> retList = null;
		if (ClientRequests.server != null)
			try {
				retList = ClientRequests.server.searchUser(userName, 0);
			} catch (RemoteException e) {
				SayToUser.error("RemoteException", e.getMessage());
			}
		else {
			SayToUser.error("Error connection!",
					"can't establish a reliable data connection to the server");
		}
		return retList;
	}

	public static ArrayList<User> getFollowers(User u) {
		ArrayList<User> retList = null;
		if (ClientRequests.server != null)
			try {
				retList = ClientRequests.server.getFollowers(u);
			} catch (RemoteException e) {
				SayToUser.error("RemoteException", e.getMessage());
			}
		else {
			SayToUser.error("Error connection!",
					"can't establish a reliable data connection to the server");
		}
		return retList;
	}

	public static Project createProject(String name, int iduser) {
		Project retVal = null;
		if (ClientRequests.server != null)
			try {
				retVal = ClientRequests.server.createProject(name, iduser);
			} catch (RemoteException e) {
				SayToUser.error("RemoteException", e.getMessage());
			}
		else {
			SayToUser.error("Error connection!",
					"can't establish a reliable data connection to the server");
		}
		return retVal;
	}

	public static ArrayList<Project> searchForProject(String likeName) {
		ArrayList<Project> retList = null;
		if (ClientRequests.server != null)
			try {
				retList = ClientRequests.server.searchForProject(likeName);
			} catch (RemoteException e) {
				SayToUser.error("RemoteException", e.getMessage());
			}
		else {
			SayToUser.error("Error connection!",
					"can't establish a reliable data connection to the server");
		}
		return retList;
	}

	public static boolean unParticipate(int idUser, int idProject) {
		boolean retVal = false;
		if (ClientRequests.server != null)
			try {
				retVal = ClientRequests.server.unParticipate(idUser, idProject);
			} catch (RemoteException e) {
				SayToUser.error("RemoteException", e.getMessage());
			}
		else {
			SayToUser.error("Error connection!",
					"can't establish a reliable data connection to the server");
		}
		return retVal;
	}

	public static boolean participate(int idUser, int idProject) {
		boolean retVal = false;
		if (ClientRequests.server != null)
			try {
				retVal = ClientRequests.server.participate(idUser, idProject);
			} catch (RemoteException e) {
				SayToUser.error("RemoteException", e.getMessage());
			}
		else {
			SayToUser.error("Error connection!",
					"can't establish a reliable data connection to the server");
		}
		return retVal;
	}

	public static boolean changePassword(int userid, String oldPass,
			String newPass) {
		boolean retVal = false;
		if (ClientRequests.server != null)
			try {
				retVal = ClientRequests.server.changePassword(userid, oldPass,
						newPass);
			} catch (RemoteException e) {
				SayToUser.error("RemoteException", e.getMessage());
                        }
		else {
			SayToUser.error("Error connection!",
					"can't establish a reliable data connection to the server");
		}
		return retVal;
	}

	public static boolean changName(int userid, String name) {
		boolean retVal = false;
		if (ClientRequests.server != null)
			try {
				retVal = ClientRequests.server.changName(userid, name);
			} catch (RemoteException e) {
				SayToUser.error("RemoteException", e.getMessage());
			}
		else {
			SayToUser.error("Error connection!",
					"can't establish a reliable data connection to the server");
		}
		return retVal;
	}

	public static boolean changeFname(int userid, String fname) {
		boolean retVal = false;
		if (ClientRequests.server != null)
			try {
				retVal = ClientRequests.server.changFname(userid, fname);
			} catch (RemoteException e) {
				SayToUser.error("RemoteException", e.getMessage());
			}
		else {
			SayToUser.error("Error connection!",
					"can't establish a reliable data connection to the server");
		}
		return retVal;
	}

	public static boolean changeMail(int userid, String pass, String mail) {
		boolean retVal = false;
		if (ClientRequests.server != null)
			try {
				retVal = ClientRequests.server.changeMail(userid, pass, mail);
			} catch (RemoteException e) {
				SayToUser.error("RemoteException", e.getMessage());
			}
		else {
			SayToUser.error("Error connection!",
					"can't establish a reliable data connection to the server");
		}
		return retVal;
	}

}
