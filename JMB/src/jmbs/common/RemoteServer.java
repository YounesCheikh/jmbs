/*
 * This file is a part of the RMI Plugin for Eclipse tutorials.
 * Copyright (C) 2002-7 Genady Beryozkin
 */
package jmbs.common;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * This is the generic remote printer inteface. It has methods to
 * submit print jobs, query their status. Querying the printer status
 * is also possible using the {@link #getPrinterStatus()} method.
 * 
 * @author Genady Beryozkin, rmi-info@genady.net
 */
public interface RemoteServer extends Remote {
	
	 public int somme(int x, int y) throws RemoteException;
	 
	 public User connectUser(String em, String psw) throws RemoteException;
 
	 public String getDemoMethod() throws RemoteException;
	 
	 public boolean addMessage(Message m ) throws RemoteException;
	 
	 public boolean createUser(User u, String hashedpassword) throws RemoteException;
}