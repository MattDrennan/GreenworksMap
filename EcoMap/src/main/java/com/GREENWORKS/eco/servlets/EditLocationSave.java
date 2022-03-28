package com.GREENWORKS.eco.servlets;
 
import java.io.*;

import com.GREENWORKS.eco.constants.LoggerConstants;
import com.GREENWORKS.eco.data.Pillar;
import com.GREENWORKS.eco.data.Pin;
import com.GREENWORKS.eco.data.PinFactory;
import com.GREENWORKS.eco.data.SessionAssistant;
import com.GREENWORKS.eco.data.SubPillar;

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

		String isEvent = request.getParameter("eventEdit");

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
		pin.setThumbnail(request.getParameter("thumbnail"));
		pin.setLink(request.getParameter("link"));
		// sessionAssistant.get(new SubPillar(Integer.parseInt(request.getParameter("subpillar"))));
		Pillar pillar = new Pillar();
        SubPillar subPillar = new SubPillar();

		try { 
			// Set Pillar
			pillar = new Pillar();
			pillar.setPid(Integer.parseInt(request.getParameter("pillarId"))); // This needs to be implemented on the front-end
			pillar.setName(request.getParameter("pillarName")); // This needs to be implemented on the front-end
			// Set sub pillar
			subPillar = new SubPillar();
			subPillar.setName(request.getParameter("subPillarName")); // This needs to be implemented on the front-end
			subPillar.setSubPillarId(Integer.parseInt(request.getParameter("subPillarId"))); // This needs to be implemented on the front-end
			subPillar.setThumbnail(request.getParameter("subPillarThumbnail")); // This needs to be implemented on the front-end
			subPillar.setPillar(pillar); // Set Pillar to Sub Pillar
			pin.setSubPillar(subPillar); // Set Sub Pillar to Pin
			Logger.info("SubPillar created: " + subPillar);
		} catch(NumberFormatException nfe){
			System.out.println("A number format exception occured. This is likely due to attempting to parse a null value to an integer.");
			SessionAssistant sessionAssistant = new SessionAssistant();
			subPillar = sessionAssistant.get(new SubPillar(Integer.parseInt(request.getParameter("subpillar"))));
			pin.setSubPillar(subPillar);
		} catch(Exception e){
			System.out.println("An exception occured.");
			SessionAssistant sessionAssistant = new SessionAssistant();
			subPillar = sessionAssistant.get(new SubPillar(Integer.parseInt(request.getParameter("subpillar"))));
			pin.setSubPillar(subPillar);
		}

		// Server side check
		if(pin.getLocationName() == "") { return; }
		if(pin.getLocationAddress() == "") { return; }
		if(isEvent == "1" && startDate == "") { return; }
		if(isEvent == "1" && endDate == "") { return; }

		SessionAssistant sessionAssistant = new SessionAssistant();
    	sessionAssistant.update(pin); // Database updated
    	Logger.info(LoggerConstants.QUERY_UPDATE + pin);
		RequestDispatcher dispatcher = request.getRequestDispatcher("admin.jsp"); // Redirect
		dispatcher.forward(request, response); // Could throw an exception. 
    }
}