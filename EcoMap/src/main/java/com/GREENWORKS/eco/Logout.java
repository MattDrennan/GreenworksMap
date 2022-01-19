package com.GREENWORKS.eco;
 
import java.io.IOException;
 
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
 
@WebServlet("/logout")
public class Logout extends HttpServlet {

    public Logout() {
        super();
    }
 
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        // Get session
        HttpSession session = request.getSession(false);

        // If session exists
        if (session != null)
        {
            // Destroy
            session.removeAttribute("username");
             
            // Redirect user
            RequestDispatcher dispatcher = request.getRequestDispatcher("admin.jsp");
            dispatcher.forward(request, response);
        }
    }
}