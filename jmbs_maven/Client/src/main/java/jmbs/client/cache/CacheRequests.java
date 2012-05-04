package jmbs.client.cache;

import java.sql.Connection;
import java.util.HashMap;


public class CacheRequests {
	
	private Connection con = new Connect().getConnection();
	
	public CacheRequests() {
		
	}
	
	public void insertIdentity(String mail , String pass) {
		IdentityDAO idao = new IdentityDAO(con);
		idao.add(mail, pass);
	}
	
	public HashMap<String, String> getIdentities() {
		IdentityDAO idao = new IdentityDAO(con);
		return idao.getIdentities();
	}
	
	public void updatePassword(String mail, String hashedPassword) {
		IdentityDAO idao = new IdentityDAO(con);
		idao.updatePassword(mail, hashedPassword);
	}
	
	public void removeAllIdentities() {
		IdentityDAO idao = new IdentityDAO(con);
		idao.deleteAll();
	}
}
