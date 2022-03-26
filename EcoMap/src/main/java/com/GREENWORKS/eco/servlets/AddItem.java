package com.GREENWORKS.eco.servlets;
 
import java.io.*;

import com.GREENWORKS.eco.data.Pin;
import com.GREENWORKS.eco.data.PinFactory;
import com.GREENWORKS.eco.data.SessionAssistant;
import com.GREENWORKS.eco.data.SubPillar;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import org.tinylog.Logger;

/***
 * This is a Serlvet. This Servlet handles the insertion of new database entries. The data that is provided to 
 * this Servlet comes from the admin portal. 
 */
@WebServlet("/additem")
public class AddItem extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	/***
	 * Constructor that makes a call to super. This is neccessary for HttpServlet. 
	 */    
    public AddItem()
    {
        super();
    }
    
    /***
     * This is where data insertion takes place. The pin object is instantiated and inserted into the database. 
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        HttpSession session = request.getSession(); // Get session
        String username = (String) session.getAttribute("username"); // Verifies that an admin is logged in. 
        
        if(username != "" && username != null) { // Check if session active
            String startDate = request.getParameter("dateStart");
    		String endDate = request.getParameter("dateEnd");

            String isEvent = request.getParameter("event");

        	PinFactory dataFactory = PinFactory.getFactory(startDate, endDate);
        	Pin pin = dataFactory.createPinData();
    		pin.setStartDate(startDate);
    		pin.setEndDate(endDate);
    		pin.setIconId(Integer.parseInt(request.getParameter("icon")));
    		pin.setLocationAddress(request.getParameter("location"));
    		pin.setLocationName(request.getParameter("locationName"));
    		pin.setCoordinates(request.getParameter("coord"));
    		pin.setContent(request.getParameter("content"));
            pin.setThumbnail(request.getParameter("thumbnail"));
            pin.setLink(request.getParameter("link"));

            // Set subpillar
            SubPillar subPillar = new SubPillar();
            subPillar.setSubPillarId(Integer.parseInt(request.getParameter("subpillar")));

            // Set sub pillar
            pin.setSubPillar(subPillar);

            // Server side check
            if(pin.getLocationName() == "") { return; }
            if(pin.getLocationAddress() == "") { return; }
            if(isEvent == "1" && startDate == "") { return; }
            if(isEvent == "1" && endDate == "") { return; }

    		SessionAssistant sessionAssistant = new SessionAssistant();
        	sessionAssistant.insert(pin); // Database insertion. 
        	Logger.info("Pin inserted: " + pin);
            // Redirect user
            RequestDispatcher dispatcher = request.getRequestDispatcher("admin.jsp");
            dispatcher.forward(request, response);
        }
    }
}