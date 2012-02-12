package Server;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Contains all the possible ways to set a request to the db.
 * 
 */
public abstract class DAO {
	
	protected Connection con;

	protected static ResultSet send(Connection con,String request)
	{
		/*
		 * petite subtilité: con semble etre un pointeur vers
		 * l'attribut connection de la classe Connect ce qui
		 * fait que cet attribut est libéré aussi a
		 * l'utilisation de close()
		 */
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
