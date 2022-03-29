package com.GREENWORKS.eco.servlets;

import com.GREENWORKS.eco.data.Pillar;
import com.GREENWORKS.eco.data.SessionAssistant;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import org.tinylog.Logger;

/***
 * 
 */
@WebServlet("/editpillar")
public class EditPillar extends HttpServlet {
    
    public EditPillar() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        HttpSession session = request.getSession(); // Get session
        String username = (String) session.getAttribute("username"); // Verifies that an admin is logged in. 
        
        if(username != "" && username != null) { // Check if session active
            // Set Pillar
            Pillar pillar = new Pillar();
            pillar.setPid(Integer.parseInt(request.getParameter("pillarId")));
            pillar.setName(request.getParameter("pillarName"));

            SessionAssistant sessionAssistant = new SessionAssistant();
            sessionAssistant.update(pillar);
        	Logger.info("Admin " + username + " edited SubPillar " + pillar + ".");
            
            // Redirect user
            RequestDispatcher dispatcher = request.getRequestDispatcher("admin.jsp");
            dispatcher.forward(request, response);
        }
    }
}