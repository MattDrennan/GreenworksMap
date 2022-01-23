package com.GREENWORKS.eco;
 
import java.io.*;
 
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import org.tinylog.Logger;
 
@WebServlet("/login")
public class Login extends HttpServlet {
    
    public Login()
    {
        super();
    }
 
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        // Get username and password from user input
        String username = request.getParameter("username");
        String password = request.getParameter("password");
         
        // Load EcoMap to get checkLogin
        EcoMap e = new EcoMap();
         
        try
        {
            // Check if login is valid
            Boolean valid = e.checkLogin(username, password);

            // Set up destination page
            String destPage = "admin.jsp";
             
            // If valid set username
            if (valid)
            {
                HttpSession session = request.getSession();
                session.setAttribute("username", username);
                Logger.warn("Admin " + username + " successfully logged in."); 
            }
            else
            {
                // Not valid
                String message = "Invalid username and/or password.";
                request.setAttribute("message", message);
                Logger.warn("Failed login attempt! Username: " + username + " Password: " + password);
            }
             
            // Redirect user
            RequestDispatcher dispatcher = request.getRequestDispatcher(destPage);
            dispatcher.forward(request, response);
             
        }
        catch (Exception ex)
        {
            // Error message
            throw new ServletException(ex);
        }
    }
}