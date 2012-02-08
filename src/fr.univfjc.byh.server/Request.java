package Server;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Contains all the possible ways to set a request to the db.
 * 
 */
public abstract class Request {

	
	protected static ResultSet querry(Connection con,String request)
	{
		ResultSet result;
		try {
			Statement state = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
			result = state.executeQuery(request);
			result.absolute(1);
		} catch (SQLException e){
			System.out.println( "Unable set the connection to the database!" );
			result = null;
		}
		return result;
	}	
}
