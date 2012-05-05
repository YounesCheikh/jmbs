package jmbs.client.cache;

import java.util.HashMap;

public class CacheIdentityRequests {

	private static Connect c;

	public CacheIdentityRequests() {

	}

	/******************************************************************/
	/************************* IDENTITIES *****************************/
	/******************************************************************/

	public void insertIdentity(String mail, String pass) {
		c = new Connect();
		IdentityDAO idao = new IdentityDAO(c.getConnection());
		idao.add(mail, pass);
		c.close();
	}

	public HashMap<String, String> getIdentities() {
		c = new Connect();
		IdentityDAO idao = new IdentityDAO(c.getConnection());
		HashMap<String, String> retMap = idao.getIdentities();
		c.close();
		return retMap;
	}

	public void updatePassword(String mail, String hashedPassword) {
		c = new Connect();
		IdentityDAO idao = new IdentityDAO(c.getConnection());
		idao.updatePassword(mail, hashedPassword);
		c.close();
	}

	public static void removeAllIdentities() {
		c = new Connect();
		IdentityDAO idao = new IdentityDAO(c.getConnection());
		idao.deleteAll();
		c.close();
	}

}
