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

package jmbs.client.dataTreatment;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Hash a password to MD5
 * @author ycheikh
 */
public class HashPassword {

	String word = null;

	/**
	 * String to Md5 password
	 * 
	 * @param password
	 */
	public HashPassword(String password) {
		word = password;
	}

        /**
         * Char List to MD5
         * @param password a list of chars 
         */
	public HashPassword(char[] password) {
		word = listToString(password);
	}

	/**
	 * @return Hashed Password
	 */
	public String getHashed() {
		String hashword = null;
		try {
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			md5.update(this.word.getBytes());
			BigInteger hash = new BigInteger(1, md5.digest());
			hashword = hash.toString(16);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return hashword;
	}

	private String listToString(char[] list) {
		String retStr = new String();
		for (char c : list) {
			retStr += c;
		}
		return retStr;
	}

}
