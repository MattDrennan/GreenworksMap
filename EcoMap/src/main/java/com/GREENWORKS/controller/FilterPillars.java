package com.GREENWORKS.controller;

import com.GREENWORKS.DAO.*;
import com.GREENWORKS.object.*;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.LinkedList;

public class FilterPillars extends HttpServlet {
    private static final long serialVersionUID = 1L;
    

    /**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pillarSelect[] = request.getParameterValues("pillar_name"), dataQuery = ""; 	
	
		for (int i = 0; i < pillarSelect.length; i++) { 
			switch(pillarSelect[i]) {
				case "CE": 
					dataQuery += "CALL SP_showEnergy();";
					break;
				case "GB":
					dataQuery += "CALL SP_showBuildings();";
					break;
				case "LF":
					dataQuery += "CALL SP_showFood();";
					break;
				case "LV":
					dataQuery += "CALL SP_showWaste();";
					break;
				case "ZW":
					dataQuery += "CALL SP_showWater();";
					break;
				case "CW":
					dataQuery += "CALL SP_showTransportation();";
					break;
				case "EA":
					dataQuery += "CALL SP_showPillars();";
					break;
			}
		}

		
		PillarDAO pd = new PillarDAO();
		LinkedList<EcoPillar> markers = pd.showSelectedPillar(dataQuery);
		request.setAttribute("markers", markers);

		RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
		rd.forward(request, response);
	}
}
