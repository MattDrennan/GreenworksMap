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
		PinDataAbstractFactory factory = PinDataAbstractFactory.getFactory(request.getParameter("dateStartEdit"), request.getParameter("dateEndEdit"));
    	PinData pinData = factory.createPinData(request.getParameter("dateStartEdit"), request.getParameter("dateEndEdit"));
    	pinData.setId(request.getParameter("id"));
		pinData.setIconId(request.getParameter("icon"));
		pinData.setLocationAddress(request.getParameter("location"));
		pinData.setLocationName(request.getParameter("locationName"));
		pinData.setZipCode(request.getParameter("zip"));
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