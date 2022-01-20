package com.GREENWORKS.eco;
 
import java.io.*;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
 
@WebServlet("/editlocationsave")
public class EditLocationSave extends HttpServlet {
    
    public EditLocationSave()
    {
        super();
    }
 
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        // Get ID of location
        String locationID = request.getParameter("id");

        // Call EcoMap
        EcoMap m = new EcoMap();

		// Set up dates
		String date1 = request.getParameter("dateStartEdit");
		String date2 = request.getParameter("dateEndEdit");

		// Set up icon
		String iconid = request.getParameter("icon");

		// Check dates
		if(request.getParameter("dateStartEdit") == null || request.getParameter("dateEndEdit") == null || request.getParameter("dateStartEdit") == "" || request.getParameter("dateEndEdit") == "")
		{
			// Not an event
			date1 = "DEFAULT";
			date2 = "DEFAULT";
		}
		else
		{
			// Clean input - is an event
			date1 =  "'" + m.cleanInput(date1) + "'";
			date2 =  "'" + m.cleanInput(date2) + "'";

			// Check icon ID values and convert to event icon
			switch(iconid)
			{
				case "1":
					iconid = "9";
					break;
				case "2":
					iconid = "8";
					break;
				case "3":
					iconid = "13";
					break;
				case "4":
					iconid = "9";
					break;
				case "5":
					iconid = "11";
					break;
				case "6":
					iconid = "10";
					break;
				case "7":
					iconid = "12";
					break;
			}
		}

        // Statement to select all location data
		String sql = "UPDATE locations SET iconid = '" + iconid + "', address = '" + m.cleanInput(request.getParameter("location")) + "', name = '" + m.cleanInput(request.getParameter("locationName")) + "', zip = '" + m.cleanInput(request.getParameter("zip")) + "', dateStart = " + date1 + ", dateEnd = " + date2 + " WHERE id = '" + locationID + "'";

        // Connect to MySQL
        MysqlConnect mysqlConnect = new MysqlConnect();

		try
		{
			// Try statement
			PreparedStatement statement = mysqlConnect.connect().prepareStatement(sql);
			
			// Execute
			statement.executeUpdate();

            // Redirect user
            RequestDispatcher dispatcher = request.getRequestDispatcher("admin.jsp");
            dispatcher.forward(request, response);
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
    }
}