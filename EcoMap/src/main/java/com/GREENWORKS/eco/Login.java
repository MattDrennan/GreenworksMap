package com.GREENWORKS.eco;
 
import java.io.*;
 
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import org.tinylog.Logger;

import com.GREENWORKS.eco.data.Admin;
import com.GREENWORKS.eco.data.SessionAssistant;
 
/***
 * This is a Servlet that handles admin login verification. It verifies login credentials by checking if they exist
 * in the database. If the credentials exist then the login will be successful. If the credentials do not exist
 * then the login will fail.
 */
@WebServlet("/login")
public class Login extends HttpServlet {
    
	private static final long serialVersionUID = 1L;

	/***
	 * Constructor that makes a call to super. This is neccessary for HttpServlet. 
	 */
    public Login()
    {
        super();
    }
    
    /***
     * This is where admin credential verification takes place. If the admin credentials that were provided to this
     * servlet can be matched in the database then a successful login will take place. 
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
        // Get username and password from user input
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        Logger.warn("Login attempt recieved from " + request.getRemoteAddr() + " | Username: " + username + " Password: " + password);
        SessionAssistant sessionAssistant = new SessionAssistant();
        Admin admin = sessionAssistant.getByLoginCredentials(username, password);
    	try {
	    	if(admin == null) { // If the data cannot be matched database side the Admin object will be null. 
	    		String message = "Invalid username and/or password.";
	            request.setAttribute("message", message);
	            Logger.warn("Failed login attempt from" + request.getRemoteAddr() + "! Username: " + username + " Password: " + password);
	    	} else if (admin != null) { // This is the condition for a successful login. 
	    		HttpSession session = request.getSession();
	            session.setAttribute("username", username);
	            Logger.warn("Admin " + username + " successfully logged in."); 
	    	}
	    	String destPage = "admin.jsp";
	    	RequestDispatcher dispatcher = request.getRequestDispatcher(destPage);
	        dispatcher.forward(request, response);
        } catch (Exception ex) {
            Logger.info("An error occured while attempting to validate login credentials from" + request.getRemoteAddr() + "| Username: " + username + " Password: " + password);
            throw new ServletException(ex);
        }
    }
}