package com.GREENWORKS.eco;
 
import java.io.*;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.GREENWORKS.eco.constants.LoggerConstants;
import com.GREENWORKS.eco.data.Pin;
import com.GREENWORKS.eco.data.PinFactory;
import com.GREENWORKS.eco.data.SessionAssistant;

import org.tinylog.Logger;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import jakarta.servlet.RequestDispatcher;
 
@WebServlet("/editlocationsave")
public class EditLocationSave extends HttpServlet {
    
    public EditLocationSave()
    {
        super();
    }
    
    /***
     * TODO Documentation - Will finish during unit testing
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
		Logger.info(LoggerConstants.QUERY_UPDATE + pin);

		SessionAssistant sessionAssistant = new SessionAssistant();
    	sessionAssistant.update(pin); // Database updated
		RequestDispatcher dispatcher = request.getRequestDispatcher("admin.jsp");
		dispatcher.forward(request, response);
    }
}