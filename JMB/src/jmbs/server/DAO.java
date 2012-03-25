package jmbs.server;

import java.io.Serializable;
import java.sql.Connection;
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
	private Connection con;

	protected ResultSet send(String request)
	{
		
		ResultSet result;
		try {
			Statement state = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
			result = state.executeQuery(request);
			result.absolute(1);
		} catch (SQLException e){
			if(e.getErrorCode() != 0)
				System.out.println( "Unable set the connection to the database!");
			result = null;
		}
		return result;
	}	
	
	public DAO(Connection c)
	{
		con = c;
	}
	
	public Connection getConnection()
	{
		return con;
	}
}
