package com.GREENWORKS.eco.servlets;

import com.GREENWORKS.eco.data.DatabaseCleaner;

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
 * This servlet will run the database cleaner. This will remove duplicate addresses from the current table 
 * and place them into a problem table. It will also remove old events from the locations table and move
 * them to the old_events table. 
 */
@WebServlet("/cleandatabase")
public class CleanDatabase extends HttpServlet {
    
    public CleanDatabase() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { // This needs to be implemented on the front-end
        HttpSession session = request.getSession(); // Get session
        String username = (String) session.getAttribute("username"); // Verifies that an admin is logged in. 
        if(username != "" && username != null) { // Check if session active
            DatabaseCleaner databaseCleaner = new DatabaseCleaner();
            databaseCleaner.runDatabaseCleaner();
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