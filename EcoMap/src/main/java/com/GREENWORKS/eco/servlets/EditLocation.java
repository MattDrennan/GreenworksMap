package com.GREENWORKS.eco.servlets;
 
import java.io.*;

import com.GREENWORKS.eco.data.GenericPin;
import com.GREENWORKS.eco.data.Pin;
import com.GREENWORKS.eco.data.SessionAssistant;

import org.tinylog.Logger;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

/***
 * This is a Servlet. This Servlet handles the delete operation that is implemented in the admin panel. Another function
 * that this Servlet handles is it loads a fresh data set that is displayed in the Admin portal during an edit. 
 */
@WebServlet("/editlocation")
public class EditLocation extends HttpServlet {
    
	private static final long serialVersionUID = 1L;
	
	/***
	 * Constructor that makes a call to super. This is neccessary for HttpServlet. 
	 */
	public EditLocation()
    {
        super();
    }
 
	/***
	 * How this Servlet functions is by the presence of data. If the delete parameter is populated then the Servlet 
	 * will perform a delete operation. If the edit parameter is populated then the servlet will perform a result set
	 * retrieval operation. 
	 * Note: It might be a good idea to break these two logic paths apart into their own Servlets. 
	 */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String deleteChoose = request.getParameter("deleteChoose");
        String editChoose = request.getParameter("editChoose");
        Integer location = Integer.parseInt(request.getParameter("locationID")); // The id of the pin is extracted here. 
        Logger.info("Edit location request recieved from: " + request.getRemoteAddr() + " LocationID: " + location);
		SessionAssistant sessionAssistant = new SessionAssistant();
		/*
		 * 	Note: 	The data that will be provided to this Servlet should be data that originated from the 
		 * 			database itself. Therefore, performing a load operation should be safe here. 
		 */
		Pin pin = sessionAssistant.load(new GenericPin(location)); // Generates a populated pin loaded from the db. 
		Logger.info("Pin generated: " + pin);        

		if(deleteChoose != "" && deleteChoose != null) { // If something is in deleteChoose perform delete. 
            sessionAssistant.delete(pin);
            Logger.info("Pin deleted: " + pin);
        } else if(editChoose != "" && editChoose != null) { // Else if something is in editChoose perform edit.
        	request.setAttribute("id", pin.getId());
            request.setAttribute("iconid", pin.getIconId());
            request.setAttribute("address", pin.getLocationAddress());
            request.setAttribute("name", pin.getLocationName());
            request.setAttribute("coord", pin.getCoordinates());
            request.setAttribute("dateStart", pin.getStartDate());
            request.setAttribute("dateEnd", pin.getEndDate());
            request.setAttribute("content", pin.getContent());
        }       
        RequestDispatcher dispatcher = request.getRequestDispatcher("admin.jsp"); // Redirect. 
        dispatcher.forward(request, response);
    }
}