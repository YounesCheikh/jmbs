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
 * 
 */

package jmbs.client.DataTreatment;

import java.util.HashMap;
import java.util.regex.Pattern;

import jmbs.client.cache.CacheRequests;

/**
 * @author <a href="mailto:younes.cheikh@gmail.com">Younes CHEIKH</a>
 * @author Benjamin Babic
 * @since 03/05/2012
 * 
 */
public class LoginTreatment {

	public LoginTreatment() {

	}

	/**
	 * verify the email
	 * 
	 * @param mail
	 *            adress mail
	 * @return true if the user has entred a right email adress
	 */

	public boolean isMailCorrect(String mail) {
		boolean correctMail = Pattern.matches(
				"^[_a-z0-9-]+(\\.[_a-z0-9-]+)*@[a-z0-9-]+(\\.[a-z0-9-]+)+$",
				mail.toLowerCase());
		return (correctMail);
	}

	/**
	 * verify the password length
	 * 
	 * @param passwdLength
	 *            the password length
	 * @return true if password length >= 4
	 */
	public boolean hasMinLenght(int passwdLength) {
		boolean correctPasswd = (passwdLength >= 4);
		return (correctPasswd);
	}

	/**
	 * method transform an array to a string
	 * 
	 * @param array
	 *            of chars
	 * @return string
	 */
	public String arrayToString(char[] array) {
		String retStr = new String();
		for (char c : array) {
			retStr += c;
		}
		return retStr;
	}

	public void savePassword(String mail, String pass) {
		CacheRequests cr = new CacheRequests();
		HashMap<String, String> identities = cr.getIdentities();
		if (identities.containsKey(mail)) {
			if (!identities.get(mail).equals(pass)) {
				cr.updatePassword(mail, pass);
			}
		} else {
			cr.insertIdentity(mail, pass);
		}
	}

	public String isSaved(String mail) {
		String retStr = null;
		CacheRequests cr = new CacheRequests();
		HashMap<String, String> identities = cr.getIdentities();
		if (identities.containsKey(mail)) {
			retStr = identities.get(mail);
		}
		return retStr;
	}
	
}
