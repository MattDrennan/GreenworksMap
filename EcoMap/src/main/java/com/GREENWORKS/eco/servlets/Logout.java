package com.GREENWORKS.eco.servlets;
 
import java.io.IOException;

import org.tinylog.Logger;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
 
/***
 * This is a Servlet. This Servlet handles the process of logging out an admin by removing the credential attribute 
 * from the session. 
 */
@WebServlet("/logout")
public class Logout extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	/***
	 * Constructor that makes a call to super. This is neccessary for HttpServlet. 
	 */
	public Logout() {
        super();
    }
 
    /***
     * The admin name attribute is removed from the session and the user is redirected to the login screen. 
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        // Get session
        HttpSession session = request.getSession(false);
        // If session exists
        if (session != null)
        {
        	String adminName = (String) session.getAttribute("username"); // Admin name is saved for logging purposes. 
            // Destroy
            session.removeAttribute("username");
            Logger.warn("Admin " + adminName + " was logged out."); 
            // Redirect user
            RequestDispatcher dispatcher = request.getRequestDispatcher("admin.jsp");
            dispatcher.forward(request, response);
        }
    }
}