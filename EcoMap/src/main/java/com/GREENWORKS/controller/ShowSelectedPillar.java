package com.GREENWORKS.controller;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.GREENWORKS.DAO.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ShowSelectedPillar {
    private static final long serialVersionUID = 1L;
    

    /**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pillarSelect = request.getParameter("pillarPrefix"); 
		//TODO create corresponding filter field to pull data from
		String dataQuery = "";
	
		//TODO edit loop for filter to add appropriate calls to a string to pass to the DAO method
		while (pillarSelect != null) { //edit the conditional statement
			switch(pillarSelect) {
				case "CE": 
					dataQuery += "CALL SP_showEnergy()";
					break;
				case "GB":
					dataQuery += "CALL SP_showBuildings()";
					break;
				case "LFS":
					dataQuery += "CALL SP_showFood()";
					break;
				case "L":
					dataQuery += "CALL SP_showWaste()";
					break;
				case "ZW":
					dataQuery += "CALL SP_showWater()";
					break;
				case "CW":
					dataQuery += "CALL SP_showTransportation()";
					break;
				case "EAT":
					dataQuery += "CALL SP_showPillars()";
					break;
			}
		}

		
		PillarDAO pd = new PillarDAO();
		pd.showSelectedPillar(dataQuery);

		RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
		rd.forward(request, response);
	}
}
