package com.GREENWORKS.eco;
 
import java.io.*;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.tinylog.Logger;

import com.GREENWORKS.eco.data.PinData;
import com.GREENWORKS.eco.data.PinDataAbstractFactory;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

/***
 * This is the Servlet class that handles the update operations between the admin interface and the database. 
 */
@WebServlet("/editlocationsave")
public class EditLocationSave extends HttpServlet {
    
	/***
	 * Constructor that makes a call to its parent constuctor. 
	 */
    public EditLocationSave()
    {
        super();
    }
    
	/***
	 * This handles the post request from the Jakarta Server Page. Within the method the HTTPServletRequest has its
	 * parameters extracted and placed in a PinData. The contents of the PinData are then inserted into the database
	 * through an update statement. 
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
		String content = m.cleanInput(request.getParameter("content"));
		
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
													", content = '" + content + "' WHERE id = '" + locationID + "'";
        // Connect to MySQL
        MysqlConnect mysqlConnect = new MysqlConnect();
		try
		{
			PreparedStatement statement = mysqlConnect.connect().prepareStatement(pinData.getUpdateQuery());
			statement.executeUpdate();
            RequestDispatcher dispatcher = request.getRequestDispatcher("admin.jsp");
            dispatcher.forward(request, response);
            Logger.info("Executed Query: " + pinData.getUpdateQuery());
            
		}
		catch (SQLException e)
		{
			Logger.error("An exception was thrown: ", e);
			Logger.error("Error executing update! Query: " +  pinData.getUpdateQuery());
			e.printStackTrace();
		}
		finally
		{
			mysqlConnect.disconnect();
		}
    }
}