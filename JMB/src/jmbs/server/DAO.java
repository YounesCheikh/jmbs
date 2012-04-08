/**
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
 * @author Younes CHEIKH http://cyounes.com
 * @author Benjamin Babic http://bbabic.com
 * 
 */

package jmbs.server;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Contains all the possible ways to set a request to the db.
 * 
 */
public abstract class DAO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1746724303428703866L;
	protected Connection con = null;
	private PreparedStatement stmt = null;
	public static final int BY_NAME = 1;
	public static final int BY_FORNAME = 2;	
	public static final int BY_BOTH = 3;
	
	public DAO(Connection c){
		con = c;
	}

	protected ResultSet send(String request){
		
		ResultSet result;
		try {
			Statement state = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
			result = state.executeQuery(request);
			result.absolute(1);
		} catch (SQLException e){
			if(e.getErrorCode() != 0)
			System.err.println("Unable set the connection to the database!/n");
			result = null;
		}
		return result;
	}	
	
	protected void set(String request){
		try {
			stmt = con.prepareStatement(request,ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
		} catch (SQLException e) {
			System.err.println("Unable to execute querry: "+ request);
		
		}
	}
	
	protected void setString (int index,String s){
		try {
			stmt.setString(index,s);
		} catch (SQLException e) {
			System.err.println("Unable to set string: \""+ s +"\"");
		}
	}
	
	protected void setInt (int index,int i){
		try {
			stmt.setInt(index, i);
		} catch (SQLException e) {
			System.err.println("Unable to set int: "+ i);
		}
	}
	
	protected void setDate(int index,Date dt){
		try {
			stmt.setDate(index,dt);
		} catch (SQLException e) {
			System.err.println("Unable to set date: "+ dt);
		}
	}
	
	protected ResultSet executeQuery() {
		ResultSet res = null;
		try {
			res = stmt.executeQuery();
			res.absolute(1);
		} catch (SQLException e) {
			System.err.println("Unable to execute query.");
			e.printStackTrace();
		}

		return res;
	}
	
	protected boolean executeUpdate() {
		boolean b;

		try {
			stmt.executeUpdate();
			b = true;
		} catch (SQLException e) {
			System.err.println("Unable to execute query.");
			e.printStackTrace();
			b = false;
		}
		
		return b;
	}
	
	protected void closeStatement(){
		try {
			if (stmt != null) {
					stmt.close();
					stmt = null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("Unable to close Statement !");
		}
		
	}
	
	public Connection getConnection(){
		return con;
	}
}
