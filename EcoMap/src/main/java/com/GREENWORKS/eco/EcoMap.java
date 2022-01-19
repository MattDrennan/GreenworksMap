package com.GREENWORKS.eco;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EcoMap {

	public ArrayList<String> test()
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
				//returnArray.add(rs.getString(0));
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

		System.out.println(returnArray);

		return returnArray;
	}
}