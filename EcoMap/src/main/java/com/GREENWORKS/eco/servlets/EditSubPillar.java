package com.GREENWORKS.eco.servlets;

import com.GREENWORKS.eco.data.Pillar;
import com.GREENWORKS.eco.data.SessionAssistant;
import com.GREENWORKS.eco.data.SubPillar;

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
 * This is a Servlet. This Servlet is used to handle updating SubPillars. The
 * flow of updated data originates
 * from the admin panel and it ends in the database.
 */
@WebServlet("/editsubpillar")
public class EditSubPillar extends HttpServlet {

    /***
     * Constructor that makes a call to super. This is neccessary for HttpServlet.
     */
    public EditSubPillar() {
        super();
    }

    /***
     * The update operation is performed here.
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException { // This needs to be implemented on the front-end
        HttpSession session = request.getSession(); // Get session
        String username = (String) session.getAttribute("username"); // Verifies that an admin is logged in.

        if(username != "" && username != null) { // Check if session active
            // Set Pillar
            Pillar pillar = new Pillar();
            pillar.setPid(Integer.parseInt(request.getParameter("pillarId")));
            pillar.setName(request.getParameter("pillarName"));

            // Set sub pillar
            SubPillar subPillar = new SubPillar();
            subPillar.setName(request.getParameter("subPillarName"));
            subPillar.setSubPillarId(Integer.parseInt(request.getParameter("subPillarId")));
            subPillar.setThumbnail(request.getParameter("subPillarThumbnail"));
            subPillar.setPillar(pillar); // Set Pillar to Sub Pillar

            SessionAssistant sessionAssistant = new SessionAssistant();
            sessionAssistant.update(subPillar);
            Logger.info("Admin " + username + " edited SubPillar " + subPillar + ".");

            // Redirect user
            RequestDispatcher dispatcher = request.getRequestDispatcher("admin.jsp");
            dispatcher.forward(request, response);
        }
    }
}