package com.GREENWORKS.eco;
 
import java.io.*;

import com.GREENWORKS.eco.data.GenericPin;
import com.GREENWORKS.eco.data.Pin;
import com.GREENWORKS.eco.data.SessionAssistant;

import org.tinylog.Logger;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
 
@WebServlet("/editlocation")
public class EditLocation extends HttpServlet {
    
    public EditLocation()
    {
        super();
    }
 
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {

        // Get ID of location
        String deleteChoose = request.getParameter("deleteChoose");
        String editChoose = request.getParameter("editChoose");
        Integer location = Integer.parseInt(request.getParameter("locationID"));
        Logger.info("Edit location request recieved from: " + request.getRemoteAddr() + " LocationID: " + location);
        
		SessionAssistant sessionAssistant = new SessionAssistant();
		Pin pin = sessionAssistant.get(new GenericPin(location)); // Generates a populated pin from the db. 
		Logger.info("Pin generated: " + pin);
		
        
        if(deleteChoose != "" && deleteChoose != null) { // If something is in deleteChoose. 
            sessionAssistant.delete(pin);
            Logger.info("Pin deleted: " + pin);
        } else if(editChoose != "" && editChoose != null) { // Else if something is in editChoose.
        	request.setAttribute("id", pin.getId());
            request.setAttribute("iconid", pin.getIconId());
            request.setAttribute("address", pin.getLocationAddress());
            request.setAttribute("name", pin.getLocationName());
            request.setAttribute("coord", pin.getCoordinates());
            request.setAttribute("dateStart", pin.getStartDate());
            request.setAttribute("dateEnd", pin.getEndDate());
            request.setAttribute("content", pin.getContent());
        }
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("admin.jsp");
        dispatcher.forward(request, response);
    }
}