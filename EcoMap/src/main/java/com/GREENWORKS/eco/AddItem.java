package com.GREENWORKS.eco;
 
import java.io.*;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.GREENWORKS.eco.constants.LoggerConstants;
import com.GREENWORKS.eco.data.Pin;
import com.GREENWORKS.eco.data.PinFactory;
import com.GREENWORKS.eco.data.SessionAssistant;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import org.tinylog.Logger;
 
@WebServlet("/additem")
public class AddItem extends HttpServlet {
    
    public AddItem()
    {
        super();
    }
 
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        // Get session
        HttpSession session = request.getSession();
        String username = (String)session.getAttribute("username");
        
        // Check if session active
        if(username != "" && username != null) {
            String startDate = request.getParameter("dateStartEdit");
    		String endDate = request.getParameter("dateEndEdit");

        	PinFactory dataFactory = PinFactory.getFactory(startDate, endDate);
        	Pin pin = dataFactory.createPinData();
    		pin.setStartDate(startDate);
    		pin.setEndDate(endDate);
    		pin.setIconId(Integer.parseInt(request.getParameter("icon")));
    		pin.setLocationAddress(request.getParameter("location"));
    		pin.setLocationName(request.getParameter("locationName"));
    		pin.setCoordinates(request.getParameter("coord"));
    		pin.setContent(request.getParameter("content"));
    		Logger.info(LoggerConstants.QUERY_UPDATE + pin);

    		SessionAssistant sessionAssistant = new SessionAssistant();
        	sessionAssistant.insert(pin); // Database updated
        	
            // Redirect user
            RequestDispatcher dispatcher = request.getRequestDispatcher("admin.jsp");
            dispatcher.forward(request, response);
        }
    }
}