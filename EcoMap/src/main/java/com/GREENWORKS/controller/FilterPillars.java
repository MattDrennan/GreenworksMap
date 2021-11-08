package com.GREENWORKS.controller;

import com.GREENWORKS.DAO.*;
import com.GREENWORKS.object.*;
import javax.servlet.RequestDispatcher;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.LinkedList;
import java.util.ArrayList;

/**
 * Servlet implementation class FilterPillars
 */
public class FilterPillars extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pillarSelect[] = request.getParameterValues("pillar_name");
		ArrayList<String> dataQuery = new ArrayList<String>(); 	
	
		for (int i = 0; i < pillarSelect.length; i++) { 
			switch(pillarSelect[i]) {
				case "CE": 
					dataQuery.add("CALL SP_showEnergy();");
					break;
				case "GB":
					dataQuery.add("CALL SP_showBuildings();");
					break;
				case "LF":
					dataQuery.add("CALL SP_showFood();");
					break;
				case "LV":
					dataQuery.add("CALL SP_showWaste();");
					break;
				case "ZW":
					dataQuery.add("CALL SP_showWater();");
					break;
				case "CW":
					dataQuery.add("CALL SP_showTransportation();");
					break;
				case "EA":
					dataQuery.add("CALL SP_showPillars();");
					break;
			}
		}
		//adds a new query for each value pulled from pillar_name
		
		PillarDAO pd = new PillarDAO();
		LinkedList<EcoPillar> markers = pd.showSelectedPillar(dataQuery);
		request.setAttribute("markers", markers);

		RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
		rd.forward(request, response);
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}
