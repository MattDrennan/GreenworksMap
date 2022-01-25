package com.GREENWORKS.eco;
 
import java.io.*;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.tinylog.Logger;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
 
@WebServlet("/editlocationsave")
public class EditLocationSave extends HttpServlet {
    
    public EditLocationSave()
    {
        super();
    }
    
    /***
     * This method is a boolean check to identify if the input parameters are consistent 
     * with not being an Event. If the data provided is consistent with an Event then the 
     * method will return false. If the data provided is not consistent with an event then 
     * it will return true. 
     * @param startDate The String of the start date. 
     * @param endDate The String of the end date. 
     * @return Returns a boolean value. 
     */
    public boolean notAnEvent(String startDate, String endDate) 
    {
    	return (startDate == null || endDate == null || startDate == "" || endDate == "");
    }
    
    /***
     * This is method takes a String parameter and reassigns its contents. 
     * @param iconId
     * @return
     */
    public String assignEventIconId(String iconId) {
		switch(iconId)
		{
			case "1":
				iconId = "9";
				break;
			case "2":
				iconId = "8";
				break;
			case "3":
				iconId = "13";
				break;
			case "4":
				iconId = "9";
				break;
			case "5":
				iconId = "11";
				break;
			case "6":
				iconId = "10";
				break;
			case "7":
				iconId = "12";
				break;
		}
		return iconId;
    }
    
    /***
     * TODO Documentation - Will finish during unit testing
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String locationID = request.getParameter("id");
        EcoMap m = new EcoMap();
		String startDate = request.getParameter("dateStartEdit");
		String endDate = request.getParameter("dateEndEdit");
		String iconId = request.getParameter("icon");
		String locationAddress = m.cleanInput(request.getParameter("location"));
		String locationName = m.cleanInput(request.getParameter("locationName"));
		String coord = m.cleanInput(request.getParameter("coord"));
		
		if(notAnEvent(startDate, endDate)) // Returns true if it is not an event.
		{
			// Not an event
			startDate = "DEFAULT";
			endDate = "DEFAULT";
		}
		else
		{
			// Clean input - is an event
			startDate =  "'" + m.cleanInput(startDate) + "'";
			endDate =  "'" + m.cleanInput(endDate) + "'";
			// Check icon ID values and convert to event icon
			iconId = assignEventIconId(iconId);
		}

        // Statement to select all location data
		String sql = "UPDATE locations SET iconid = '" + iconId + "', address = '" + locationAddress + 
													"', name = '" + locationName + "', coord = '" + coord + 
													"', dateStart = " + startDate + ", dateEnd = " + endDate + 
													" WHERE id = '" + locationID + "'";
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
            Logger.info("Query executed: " + sql);
		}
		catch (SQLException e)
		{
			Logger.error("Error executing update! Query: " +  sql);
			e.printStackTrace();
		}
		finally
		{
			// Disconnect when done
			mysqlConnect.disconnect();
		}
    }
}