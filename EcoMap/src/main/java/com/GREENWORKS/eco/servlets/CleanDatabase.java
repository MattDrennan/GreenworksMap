package com.GREENWORKS.eco.servlets;

import java.io.*;

import com.GREENWORKS.eco.data.DatabaseCleaner;
import com.GREENWORKS.eco.data.OldEventPin;
import com.GREENWORKS.eco.data.Pin;
import com.GREENWORKS.eco.data.ProblemPin;
import com.GREENWORKS.eco.data.SessionAssistant;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;

import org.tinylog.Logger;

/***
 * This servlet will run the database cleaner. This will remove duplicate addresses from the current table 
 * and place them into a problem table. It will also remove old events from the locations table and move
 * them to the old_events table. 
 */
@WebServlet("/cleandatabase")
public class CleanDatabase extends HttpServlet {
    
    public CleanDatabase() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        HttpSession session = request.getSession(); // Get session
        String username = (String) session.getAttribute("username"); // Verifies that an admin is logged in. 
        
        if(username != "" && username != null) { // Check if session active
            SessionAssistant sessionAssistant = new SessionAssistant(); 
            List<Pin> pinList = sessionAssistant.getAllPinsList();
            DatabaseCleaner databaseCleaner = new DatabaseCleaner();
            HashMap<String, ArrayList<Pin>> addressPinMap = databaseCleaner.findRedundantAddress(pinList);
            ArrayList<OldEventPin> oldEvents = databaseCleaner.convertOldEvents();
            pinList.clear(); // Release memory.
            ArrayList<Pin> deleteList = databaseCleaner.solveConflicts(addressPinMap);
            ArrayList<ProblemPin> problemPinList = databaseCleaner.convertToProblemPinList(deleteList);
            ArrayList<Pin> pastDatePinList = databaseCleaner.getPastDatePinList();
            sessionAssistant.saveList(problemPinList);
            sessionAssistant.deleteList(deleteList);
            sessionAssistant.saveList(oldEvents);
            sessionAssistant.deleteList(pastDatePinList);
        	Logger.info("Admin " + username + " performed a database clean.");
            // Redirect user
            RequestDispatcher dispatcher = request.getRequestDispatcher("admin.jsp");
            dispatcher.forward(request, response);
        }
    }


}


/*
        SessionAssistant sessionAssistant = new SessionAssistant(); 
		List<Pin> pinList = sessionAssistant.getAllPinsList();
		DatabaseCleaner databaseCleaner = new DatabaseCleaner();
		HashMap<String, ArrayList<Pin>> addressPinMap = databaseCleaner.findRedundantAddress(pinList);
		pinList.clear(); // Release memory.
		ArrayList<Pin> deleteList = databaseCleaner.solveConflicts(addressPinMap);
		ArrayList<ProblemPin> problemPinList = databaseCleaner.convertToProblemPinList(deleteList);
		sessionAssistant.saveList(problemPinList);
		sessionAssistant.deleteList(deleteList);

*/