package com.GREENWORKS.eco;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/***
 * This class handles the transference of locations data points from the database to the front end. 
 * 
 * Note: 	This class also checks admin credentials. It is probably the case that admin credentials 
 * 			should be moved to another class. However, this will done in a refactoring stage. 
 */
public class EcoMap {

	/***
	 * This method returns the full list of locations stored in the database. The location data 
	 * is used to plot points on the map. 
	 * @return A complete list of locations. 
	 */
	public ArrayList<String> getLocations() 
	{
		MysqlConnect mysqlConnect = new MysqlConnect();
		ArrayList<String> returnArray = new ArrayList<String>();
		
		try
		{
			returnArray = retrieveLocations(mysqlConnect);
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			mysqlConnect.disconnect();
		}
		return returnArray;
	}

	/***
	 * This method was pulled from the getLocations() method. It has been pulled to make unit testing
	 * more approachable. 
	 * @param mysqlConnect Requires a MysqlConnect object for database connection. 
	 * @return A complete list of locations. 
	 * @throws SQLException If an exception is thrown it is likely due to a misconfiguration in the DatabaseConstants.java.
	 */
	public ArrayList<String> retrieveLocations(MysqlConnect mysqlConnect) throws SQLException
	{
		ArrayList<String> returnArray = new ArrayList<String>();
		String sql = "SELECT * FROM locations";
		PreparedStatement statement = mysqlConnect.connect().prepareStatement(sql);
		ResultSet rs = statement.executeQuery();
		while(rs.next())
		{
			returnArray.add(rs.getString(1));
			returnArray.add(rs.getString(2));
			returnArray.add(rs.getString(3));
			returnArray.add(rs.getString(4));
			returnArray.add(rs.getString(5));
			returnArray.add(rs.getString(6));
			returnArray.add(rs.getString(7));
			returnArray.add(rs.getString(8));
		}
		mysqlConnect.disconnect();
		return returnArray;		
	}

	/***
	 * This method adds slashes to a String to preserve the backslashes in textual entries. 
	 * @param s The String that will be modified. 
	 * @return A String that has had additional backslashes added. 
	 */
    public String addSlashes(String s)
	{
        s = s.replaceAll("\\\\", "\\\\\\\\");
        s = s.replaceAll("\\n", "\\\\n");
        s = s.replaceAll("\\r", "\\\\r");
        s = s.replaceAll("\\00", "\\\\0");
        s = s.replaceAll("'", "\\\\'");
        return s;
    }

	/***
	 * This method removes HTML tags from a String. It removes the tags <> and what
	 * is contained within the tags. 
	 * @param s The String that will be modified. 
	 * @return A modified String that will have no HTML tags. 
	 */
	public String removeTags(String s)
	{
		String noHTMLString = s.replaceAll("\\<.*?\\>", "");
		return noHTMLString;
	}

	/***
	 * This method executes the removeTags() and the addSlashes() methods consecutively. 
	 * @param s The String that will be modified.
	 * @return A modified String that will have no HTML tags and its backslashes preserved. 
	 */
	public String cleanInput(String s)
	{
		String returnS = addSlashes(removeTags(s));
		return returnS;
	}

	/***
	 * This is a credentials checker. It uses the credentials that the user provides to check if they 
	 * exist in the database. If the credentials exist then the method returns true. If the credentials 
	 * do not exist then the method returns false. 
	 * @param username The username provided by the user. 
	 * @param password The password provided by the user. 
	 * @return Boolean check if the credentials exists or not. 
	 */
	public Boolean checkLogin(String username, String password)
	{
		MysqlConnect mysqlConnect = new MysqlConnect();
		Boolean valid = false;
		String sql = "SELECT * FROM users WHERE username = '" + username + "' AND password = '" + password + "' LIMIT 1";
		try
		{
			PreparedStatement statement = mysqlConnect.connect().prepareStatement(sql);
			ResultSet rs = statement.executeQuery();
			/**
			 * Note: 	the SQL query has a LIMIT 1 specified. Looping through a result 
			 * 			set with a single entry can be considered a lite operation. 
			 */
			while(rs.next())
			{
				valid = true;
			}
			
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			mysqlConnect.disconnect();
		}
		return valid;
	}
}