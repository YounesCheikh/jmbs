package jmbs.client;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import jmbs.client.Graphics.others.SayToUser;
import jmbs.common.Message;
import jmbs.common.Project;
import jmbs.common.User;

public class ClientRequests {

	public ClientRequests() {
	}

	public static ArrayList<Project> getUserProjects(int idUser) {
		ArrayList<Project> retList = new ArrayList<Project>();
		if (ServerConnection.server != null) {
			try {
				retList = ServerConnection.server.getUserProjects(idUser);
				if (retList != null) {
					for (Project p : retList) {
						p.setUsers(ClientRequests.getProjectUsers(p.getId()));
					}
				}
			} catch (RemoteException e) {
				SayToUser.error("RemoteException", e.getMessage());
			}
		} else {
			SayToUser.error("Error connection!",
					"can't establish a reliable data connection to the server");
		}
		return retList;
	}

	public static void logOut(int idUser) {
		if (ServerConnection.server != null) {
			try {
				ServerConnection.server.logOut(idUser);
			} catch (RemoteException e) {
				SayToUser.error("RemoteException", e.getMessage());
			}
		} else {
			SayToUser.error("Error connection!",
					"can't establish a reliable data connection to the server");
		}
	}

	public static User connectUser(String em, String psw) {
		User u = null;
		if (ServerConnection.server != null) {
			try {
				u = ServerConnection.server.connectUser(em, psw);
			} catch (SecurityException e) {
				SayToUser.error("SecurityException", e.getMessage());
			} catch (RemoteException e) {
				SayToUser.error("RemoteException", e.getMessage());

			}
		} else {
			SayToUser.error("Error connection!",
					"can't establish a reliable data connection to the server");
		}
		return u;
	}

	public static boolean follows(int idfollower, int idfollowed) {
		boolean retVal = false;
		if (ServerConnection.server != null) {
			try {
				retVal = ServerConnection.server
						.follows(idfollower, idfollowed);
			} catch (RemoteException e) {
				SayToUser.error("RemoteException", e.getMessage());
			}
		} else {
			SayToUser.error("Error connection!",
					"can't establish a reliable data connection to the server");
		}

		return retVal;
	}

	public static boolean unFollow(int idfollower, int idfollowed) {
		boolean retVal = false;
		if (ServerConnection.server != null) {
			try {
				retVal = ServerConnection.server.unFollow(idfollower,
						idfollowed);
			} catch (RemoteException e) {
				SayToUser.error("RemoteException", e.getMessage());
			}
		} else {
			SayToUser.error("Error connection!",
					"can't establish a reliable data connection to the server");
		}

		return retVal;
	}

	public static boolean close() {
		boolean retVal = false;
		if (ServerConnection.server != null) {
			try {
				retVal = ServerConnection.server.close();
			} catch (RemoteException e1) {
				SayToUser.error("RemoteException", e1.getMessage());
			}
		} else {
			SayToUser.error("Error connection!",
					"can't establish a reliable data connection to the server");
		}
		return retVal;
	}

	public static ArrayList<Message> getLatestTL(int iduser, int idlastmessage,
			int maxMsg) {
		ArrayList<Message> retList = new ArrayList<Message>();
		if (ServerConnection.server != null) {
			try {
				retList = ServerConnection.server.getLatestTL(iduser,
						idlastmessage, maxMsg);
			} catch (RemoteException e1) {
				SayToUser.error("RemoteException", e1.getMessage());
			}
		} else {
			SayToUser.error("Error connection!",
					"can't establish a reliable data connection to the server");
		}
		return retList;
	}

	public static int addMessage(Message m) {
		int retVal = 0;
		if (ServerConnection.server != null) {
			try {
				retVal = ServerConnection.server.addMessage(m);
			} catch (RemoteException e1) {
				SayToUser.error("RemoteException", e1.getMessage());
			}
		} else {
			SayToUser.error("Error connection!",
					"can't establish a reliable data connection to the server");
		}
		return retVal;
	}

	public static int addMessage(Message m, int projectId) {
		int retVal = 0;
		if (ServerConnection.server != null) {
			try {
				retVal = ServerConnection.server.addMessage(m, projectId);
			} catch (RemoteException e1) {
				SayToUser.error("RemoteException", e1.getMessage());
			}
		} else {
			SayToUser.error("Error connection!",
					"can't establish a reliable data connection to the server");
		}
		return retVal;
	}

	public static boolean createUser(User u, String hashedpassword) {
		boolean retVal = false;
		if (ServerConnection.server != null) {
			try {
				retVal = ServerConnection.server.createUser(u, hashedpassword);
			} catch (RemoteException e) {
				SayToUser.error("RemoteException", e.getMessage());
			}
		} else {
			SayToUser.error("Error connection!",
					"can't establish a reliable data connection to the server");
		}
		return retVal;
	}

	public static ArrayList<User> searchUser(String userName, int param) {
		ArrayList<User> retList = null;
		if (ServerConnection.server != null) {
			try {
				retList = ServerConnection.server.searchUser(userName, 0);
			} catch (RemoteException e) {
				SayToUser.error("RemoteException", e.getMessage());
			}
		} else {
			SayToUser.error("Error connection!",
					"can't establish a reliable data connection to the server");
		}
		return retList;
	}

	public static ArrayList<User> getFollowers(User u) {
		ArrayList<User> retList = null;
		if (ServerConnection.server != null) {
			try {
				retList = ServerConnection.server.getFollowers(u);
			} catch (RemoteException e) {
				SayToUser.error("RemoteException", e.getMessage());
			}
		} else {
			SayToUser.error("Error connection!",
					"can't establish a reliable data connection to the server");
		}
		return retList;
	}

	/**
	 * 
	 * @param name
	 * @param iduser
	 * @return true if the project created succesfully
	 * @deprecated 
	 */
	public static boolean createProject(String name, int iduser) {
		boolean b = false;
		if (ServerConnection.server != null) {
			try {
				b = ServerConnection.server.createProject(name, iduser);
			} catch (SecurityException e) {
				SayToUser.warning("SecurityException", e.getMessage());
			} catch (RemoteException e) {
				SayToUser.error("RemoteException", e.getMessage());
			}
		} else {
			SayToUser.error("Error connection!",
					"can't establish a reliable data connection to the server");
		}
		return b;
	}
	
	public static int createProject(String name, int iduser,int activation, boolean edit, boolean supress, boolean privacy) {
		int b = -1;
		if (ServerConnection.server != null) {
			try {
				b = ServerConnection.server.createProject(name, iduser,activation,edit,supress,privacy);
			} catch (SecurityException e) {
				SayToUser.warning("SecurityException", e.getMessage());
			} catch (RemoteException e) {
				SayToUser.error("RemoteException", e.getMessage());
			}
		} else {
			SayToUser.error("Error connection!",
					"can't establish a reliable data connection to the server");
		}
		return b;
	}

	/**
	 * @deprecated
	 * @param likeName
	 * @return
	 */
	public static ArrayList<Project> searchForProject(String likeName) {
		ArrayList<Project> retList = null;
		if (ServerConnection.server != null) {
			try {
				retList = ServerConnection.server.searchForProject(likeName);
				if (retList != null) {
					for (Project p : retList) {
						p.setUsers(ClientRequests.getProjectUsers(p.getId()));
					}
				}
			} catch (RemoteException e) {
				SayToUser.error("RemoteException", e.getMessage());
			}
		} else {
			SayToUser.error("Error connection!",
					"can't establish a reliable data connection to the server");
		}
		return retList;
	}
	
	 public static ArrayList<Project> searchForProject(String likeName, int userId)  {
		 ArrayList<Project> retList = null;
			if (ServerConnection.server != null) {
				try {
					retList = ServerConnection.server.searchForProject(likeName,userId);
					if (retList != null) {
						for (Project p : retList) {
							p.setUsers(ClientRequests.getProjectUsers(p.getId()));
						}
					}
				} catch (RemoteException e) {
					SayToUser.error("RemoteException", e.getMessage());
				}
			} else {
				SayToUser.error("Error connection!",
						"can't establish a reliable data connection to the server");
			}
			return retList;
	 }

	public static boolean unParticipate(int idUser, int idProject) {
		boolean retVal = false;
		if (ServerConnection.server != null) {
			try {
				retVal = ServerConnection.server.unParticipate(idUser,
						idProject);
			} catch (RemoteException e) {
				SayToUser.error("RemoteException", e.getMessage());
			}
		} else {
			SayToUser.error("Error connection!",
					"can't establish a reliable data connection to the server");
		}
		return retVal;
	}

	public static boolean participate(int idUser, int idProject) {
		boolean retVal = false;
		if (ServerConnection.server != null) {
			try {
				retVal = ServerConnection.server.participate(idUser, idProject);
			} catch (RemoteException e) {
				SayToUser.error("RemoteException", e.getMessage());
			}
		} else {
			SayToUser.error("Error connection!",
					"can't establish a reliable data connection to the server");
		}
		return retVal;
	}

	public static boolean changePassword(int userid, String oldPass,
			String newPass) {
		boolean retVal = false;
		if (ServerConnection.server != null) {
			try {
				retVal = ServerConnection.server.changePassword(userid,
						oldPass, newPass);
			} catch (RemoteException e) {
				SayToUser.error("RemoteException", e.getMessage());
			}
		} else {
			SayToUser.error("Error connection!",
					"can't establish a reliable data connection to the server");
		}
		return retVal;
	}

	public static boolean changName(int userid, String name) {
		boolean retVal = false;
		if (ServerConnection.server != null) {
			try {
				retVal = ServerConnection.server.changName(userid, name);
			} catch (RemoteException e) {
				SayToUser.error("RemoteException", e.getMessage());
			}
		} else {
			SayToUser.error("Error connection!",
					"can't establish a reliable data connection to the server");
		}
		return retVal;
	}

	public static boolean changeFname(int userid, String fname) {
		boolean retVal = false;
		if (ServerConnection.server != null) {
			try {
				retVal = ServerConnection.server.changFname(userid, fname);
			} catch (RemoteException e) {
				SayToUser.error("RemoteException", e.getMessage());
			}
		} else {
			SayToUser.error("Error connection!",
					"can't establish a reliable data connection to the server");
		}
		return retVal;
	}

	public static boolean changeMail(int userid, String pass, String mail) {
		boolean retVal = false;
		if (ServerConnection.server != null) {
			try {
				retVal = ServerConnection.server.changeMail(userid, pass, mail);
			} catch (RemoteException e) {
				SayToUser.error("RemoteException", e.getMessage());
			}
		} else {
			SayToUser.error("Error connection!",
					"can't establish a reliable data connection to the server");
		}
		return retVal;
	}

	public static ArrayList<User> getProjectUsers(int idProject) {
		ArrayList<User> retList = null;
		if (ServerConnection.server != null) {
			try {
				retList = ServerConnection.server.getProjectUsers(idProject);
			} catch (RemoteException e) {
				SayToUser.error("RemoteException", e.getMessage());
			}
		} else {
			SayToUser.error("Error connection!",
					"can't establish a reliable data connection to the server");
		}
		if (retList == null)
			return new ArrayList<User>();
		return retList;
	}

	public static ArrayList<Project> getOwnedProjects(int userid) {
		ArrayList<Project> retList = null;
		if (ServerConnection.server != null) {
			try {
				retList = ServerConnection.server.getOwnedProjects(userid);
			} catch (RemoteException e) {
				SayToUser.error("RemoteException", e.getMessage());
			}
		} else {
			SayToUser.error("Error connection!",
					"can't establish a reliable data connection to the server");
		}

		if (retList == null)
			retList = new ArrayList<Project>();
		return retList;
	}

	public static byte[] pathToByte(String path) {
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			BufferedImage bi = ImageIO.read(new File(path));
			ImageIO.write(bi, "jpg", baos);
			baos.flush();
			byte[] imageInByte = baos.toByteArray();
			baos.close();
			return imageInByte;
		} catch (IOException ex) {
			return null;
		}
	}

	public static BufferedImage byteToImage(byte[] imageInByte) {
		try {
			BufferedImage image = ImageIO.read(new ByteArrayInputStream(
					imageInByte));
			return image;
		} catch (IOException ex) {
			return null;
		}
	}

	public static boolean setPicture(int userId, byte[] imageInByte) {
		boolean retVal = false;
		if (ServerConnection.server != null) {
			try {
				retVal = ServerConnection.server.setPicture(userId,
						imageInByte);
			} catch (RemoteException e) {
				SayToUser.error("RemoteException", e.getMessage());
			}
		} else {
			SayToUser.error("Error connection!",
					"can't establish a reliable data connection to the server");
		}
		return retVal;
	}

	public static ArrayList<Message> getLastetProjectTL(int iduser,
			int idlastmessage, int maxMsg, int idproject) {
		ArrayList<Message> retList = new ArrayList<Message>();
		if (ServerConnection.server != null) {
			try {
				retList = ServerConnection.server.getLastetProjectTL(iduser,
						idlastmessage, maxMsg, idproject);
			} catch (RemoteException e1) {
				SayToUser.error("RemoteException", e1.getMessage());
			}
		} else {
			SayToUser.error("Error connection!",
					"can't establish a reliable data connection to the server");
		}
		return retList;
	}

	public static boolean closeProject(int idUser, int idProject) {
		boolean retVal = false;
		if (ServerConnection.server != null) {
			try {
				retVal = ServerConnection.server
						.closeProject(idUser, idProject);
			} catch (RemoteException e) {
				SayToUser.error("RemoteException", e.getMessage());
			} catch (SecurityException e) {
				SayToUser.error("SecurityException", e.getMessage());
			}
		} else {
			SayToUser.error("Error connection!",
					"can't establish a reliable data connection to the server");
		}
		return retVal;
	}

	public static boolean openProject(int idUser, int idProject) {
		boolean retVal = false;
		if (ServerConnection.server != null) {
			try {
				retVal = ServerConnection.server.openProject(idUser, idProject);
			} catch (RemoteException e) {
				SayToUser.error("RemoteException", e.getMessage());
			} catch (SecurityException e) {
				SayToUser.error("SecurityException", e.getMessage());
			}
		} else {
			SayToUser.error("Error connection!",
					"can't establish a reliable data connection to the server");
		}
		return retVal;
	}
	
	public static BufferedImage convert(byte[] ib) {
		BufferedImage im = null;
		try {
			im = ImageIO.read(new ByteArrayInputStream(ib));
		} catch (IOException e) {
			// Logger.getLogger(PictureDAO.class.getName()).log(Level.SEVERE,
			// null, e);
		}
		return im;
	}

}
