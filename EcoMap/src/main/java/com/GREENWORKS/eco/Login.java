package com.GREENWORKS.eco;
 
import java.io.*;
 
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import org.tinylog.Logger;

import com.GREENWORKS.eco.data.Admin;
import com.GREENWORKS.eco.data.SessionAssistant;
 
/***
 * This is a Servlet that handles admin login verification. 
 */
@WebServlet("/login")
public class Login extends HttpServlet {
    
    public Login()
    {
        super();
    }
    
    /***
     * This uses the SessionAssistant to verify if the username and password provided exist in the database. 
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        // Get username and password from user input
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        Logger.warn("Login attempt: Username: " + username + " Password: " + password);
        SessionAssistant sessionAssistant = new SessionAssistant();
        Admin admin = sessionAssistant.getByLoginCredentials(username, password);
    	try {
    	if(admin == null) {
    		String message = "Invalid username and/or password.";
            request.setAttribute("message", message);
            Logger.warn("Failed login attempt! Username: " + username + " Password: " + password);
    	} else if (admin != null) {
    		HttpSession session = request.getSession();
            session.setAttribute("username", username);
            Logger.warn("Admin " + username + " successfully logged in."); 
    	}
    	
    	String destPage = "admin.jsp";
    	RequestDispatcher dispatcher = request.getRequestDispatcher(destPage);
        dispatcher.forward(request, response);
        
        } catch (Exception ex) {
            Logger.info("An error occured while attempting to validate login credentials.");
            throw new ServletException(ex);
        }
    }
}