package com.GREENWORKS.eco;
 
import java.io.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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

        // Statement to select all location data
		String sql = "UPDATE locations SET iconid = '" + m.cleanInput(request.getParameter("icon")) + "', address = '" + m.cleanInput(request.getParameter("location")) + "', name = '" + m.cleanInput(request.getParameter("locationName")) + "', zip = '" + m.cleanInput(request.getParameter("zip")) + "' WHERE id = '" + locationID + "'";

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