package com.GREENWORKS.eco.servlets;
 
import java.io.*;

import com.GREENWORKS.eco.constants.LoggerConstants;
import com.GREENWORKS.eco.data.Pin;
import com.GREENWORKS.eco.data.PinFactory;
import com.GREENWORKS.eco.data.SessionAssistant;

import org.tinylog.Logger;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

/***
 * This is a Servlet. This Servlet is used to handle updating data. The flow of updated data originates
 * from the admin panel and it ends in the database. The data is transfered from the front-end through 
 * an HttpServletRequest. The HttpServletRequest then has the parameters extracted and placed into a 
 * Pin-type object. The Pin-type object has object-relational mapping so it is used by the Hibernate
 * medium to perform a data update in the database. 
 */
@WebServlet("/editlocationsave")
public class EditLocationSave extends HttpServlet {
    
	private static final long serialVersionUID = 1L;
	
	/***
	 * Constructor that makes a call to super. This is neccessary for HttpServlet. 
	 */
	public EditLocationSave()
    {
        super();
    }
    
    /***
     * The update operation is performed here. 
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
		Logger.info("Edit-save location request recieved from: " + request.getRemoteAddr());

        String startDate = request.getParameter("dateStartEdit");
		String endDate = request.getParameter("dateEndEdit");

    	PinFactory dataFactory = PinFactory.getFactory(startDate, endDate);
    	Pin pin = dataFactory.createPinData();
    	pin.setId(Integer.parseInt(request.getParameter("id")));
		pin.setStartDate(startDate);
		pin.setEndDate(endDate);
		pin.setIconId(Integer.parseInt(request.getParameter("icon")));
		pin.setLocationAddress(request.getParameter("location"));
		pin.setLocationName(request.getParameter("locationName"));
		pin.setCoordinates(request.getParameter("coord"));
		pin.setContent(request.getParameter("content"));
		SessionAssistant sessionAssistant = new SessionAssistant();
    	sessionAssistant.update(pin); // Database updated
    	Logger.info(LoggerConstants.QUERY_UPDATE + pin);
		RequestDispatcher dispatcher = request.getRequestDispatcher("admin.jsp"); // Redirect
		dispatcher.forward(request, response); // Could throw an exception. 
    }
}