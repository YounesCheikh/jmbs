/*
 * JMBS: Java Micro Blogging System
 *
 * Copyright (C) 2012  
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY.
 * See the GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package jmbs.client;

import java.rmi.RemoteException;
import java.util.ArrayList;

import jmbs.client.Graphics.others.SayToUser;
import jmbs.common.Message;
import jmbs.common.Project;
import jmbs.common.User;

/**
 * @author <a href="mailto:younes.cheikh@gmail.com">Younes CHEIKH</a>
 * @author Benjamin Babic
 * @since 06-05-2012
 * @version 1.0
 */
public class ClientRequests {

	/**
	 * this constructor is invoked when the application start, than all the rest of methods are
	 * invoked statically
	 * 
	 */
	public ClientRequests() {
	}

	/**
	 * request the projects where the current user is participated
	 * @param idUser current user id
	 * @return a list of projects
	 */
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

	/**
	 * tell the server that the current user is disconnecteds
	 * @param idUser current user id
	 */
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

	/**
	 * connect user with it's login and password
	 * @param em adress mail
	 * @param psw hashed password
	 * @return a user with id different to -1 if the connection is successful 
	 */
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

	/**
	 * add someone to the current user follower list
	 * @param idfollower current user id
	 * @param idfollowed the followed user is
	 * @return true of following successful
	 */
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

	/**
	 * unfollow someone
	 * @param idfollower current user id
	 * @param idfollowed the id of the user that the current user want to unfollow
	 * @return true if the operation done successfully
	 */
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

	/**
	 * @return true if the connection is closed successfully 
	 */
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

	
	/**
	 * get latest messages from server
	 * @param iduser current user id
	 * @param idlastmessage the id of the latest message received from server
	 * @param maxMsg the maximum number of messages want to receive from server
	 * @return a list of messages
	 */
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

	/**
	 * send a new message to server
	 * @param m the message
	 * @return integer generated by the sever : the message id
	 * 			it will be used as latest message id received from server
	 */
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

	/**
	 * Send new message into project
	 * @param m message
	 * @param projectId project id where the message will be sent
	 * @return the id of the message sent to the server
	 */
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

	/**
	 * This method is used when a new user want to register 
	 * @param u the user
	 * @param hashedpassword the hashed password
	 * @return true of user is created successfully by the server
	 */
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

	/**
	 * server for user
	 * @param userName user name
	 * @param param parameter
	 * @return a list of users who have the same like name
	 */
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

	/** 
	 * get the followers of the current user
	 * @param u current user
	 * @return a list of followers
	 */
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

	/**
	 * Create new project
	 * @param name project name
	 * @param iduser current user id
	 * @param activation 1 if the project is active
	 * @param edit true if editing is allowed
	 * @param supress true if deleting messages of project is allowed
	 * @param privacy false if project is public
	 * @return project id
	 */
	public static int createProject(String name, int iduser, int activation,
			boolean edit, boolean supress, boolean privacy) {
		int b = -1;
		if (ServerConnection.server != null) {
			try {
				b = ServerConnection.server.createProject(name, iduser,
						activation, edit, supress, privacy);
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
	 * @return a list of projects
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

	/**
	 * Search for project 
	 * @param likeName project name
	 * @param userId current user id 
	 * @return a list of projects
	 */
	public static ArrayList<Project> searchForProject(String likeName,
			int userId) {
		ArrayList<Project> retList = null;
		if (ServerConnection.server != null) {
			try {
				retList = ServerConnection.server.searchForProject(likeName,
						userId);
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

	/**
	 * un-participate from project
	 * @param idUser current user id
	 * @param idProject project id
	 * @return true if all things done successfully
	 */
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

	/**
	 * participate user into project
	 * @param idUser current user id 
	 * @param idProject project id
	 * @return true if all right
	 */
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

	/**
	 * change password of current user
	 * @param userid current user id
	 * @param oldPass old password
	 * @param newPass new password
	 * @return true if password changed successfully
	 */
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

	/**
	 * change the user name
	 * @param userid current user id
	 * @param name new name
	 * @return true if all right
	 */
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

	/**
	 * change forename 
	 * @param userid user id
	 * @param fname new forname
	 * @return true if all right
	 */
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

	/**
	 * change the adress mail of current user
	 * @param userid current user id
	 * @param pass password to confirm 
	 * @param mail the new adress mail
	 * @return true if all right
	 */
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

	/**
	 * get all the participated users into a project
	 * @param idProject project id
	 * @return a list of users
	 */
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

	/**
	 * get all the projects created bye the specified user
	 * @param userid user id
	 * @return a list of projects
	 */
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

	/**
	 * set a new profile picture to current user
	 * @param userId current user id
	 * @param imageInByte profile picture
	 * @return true if all right
	 */
	public static boolean setPicture(int userId, byte[] imageInByte) {
		boolean retVal = false;
		if (ServerConnection.server != null) {
			try {
				retVal = ServerConnection.server
						.setPicture(userId, imageInByte);
			} catch (RemoteException e) {
				SayToUser.error("RemoteException", e.getMessage());
			}
		} else {
			SayToUser.error("Error connection!",
					"can't establish a reliable data connection to the server");
		}
		return retVal;
	}

	/**
	 * get the latest messages from project
	 * @param iduser user id 
	 * @param idlastmessage the id of the latest message received from server
	 * @param maxMsg the maximum of msgs
	 * @param idproject project id
	 * @return a list of messages
	 */
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

	/**
	 * close a project
	 * @param idUser user id: used to confirm that the owner want to close it's projct
	 * @param idProject project id
	 * @return true if project closed successfully
	 */
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

	/**
	 * open a closed project
	 * @param idUser user id
	 * @param idProject project id
	 * @return true if project opened successfully
	 */
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

}
