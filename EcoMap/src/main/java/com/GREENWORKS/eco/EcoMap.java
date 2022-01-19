package com.GREENWORKS.eco;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EcoMap {
	// getLocations: Returns an array list of locations from the database
	public ArrayList<String> getLocations()
	{
		// Connect to MySQL
		MysqlConnect mysqlConnect = new MysqlConnect();

		// Set up return variable
		ArrayList<String> returnArray = new ArrayList<String>();

		// Statement to select all location data
		String sql = "SELECT * FROM locations";

		try
		{
			// Try statement
			PreparedStatement statement = mysqlConnect.connect().prepareStatement(sql);
			
			// Loop through statement
			ResultSet rs = statement.executeQuery();

			while(rs.next())
			{
				returnArray.add(rs.getString(1));
				returnArray.add(rs.getString(2));
				returnArray.add(rs.getString(3));
				returnArray.add(rs.getString(4));
			}
		}
		catch (SQLException e)
		{
			// Error
			e.printStackTrace();
		}
		finally
		{
			// Disconnect when done
			mysqlConnect.disconnect();
		}

		return returnArray;
	}

	// checkLogin: Checks the login credentials, and returns the username
	public Boolean checkLogin(String username, String password)
	{
		// Connect to MySQL
		MysqlConnect mysqlConnect = new MysqlConnect();

		// Set up return string
		Boolean valid = false;

		// Statement to select all location data
		String sql = "SELECT * FROM users WHERE username = '" + username + "' AND password = '" + password + "' LIMIT 1";

		try
		{
			// Try statement
			PreparedStatement statement = mysqlConnect.connect().prepareStatement(sql);
			
			// Loop through statement
			ResultSet rs = statement.executeQuery();

			while(rs.next())
			{
				valid = true;
				System.out.print("test");
			}
		}
		catch (SQLException e)
		{
			// Error
			e.printStackTrace();
		}
		finally
		{
			// Disconnect when done
			mysqlConnect.disconnect();
		}

		// Return value
		return valid;
	}
}