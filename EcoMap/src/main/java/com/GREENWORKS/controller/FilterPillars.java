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
import com.google.gson.Gson;

/**
 * Servlet implementation class FilterPillars
 */
public class FilterPillars extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String[] pillarSelect = request.getParameterValues("pillar_name");
		String sp_call = "CALL SP_show";
		ArrayList<String> dataQuery = new ArrayList<String>(); 	

		
		for (int i = 0; i < pillarSelect.length; i++) { 
			dataQuery.add(sp_call + pillarSelect[i] + "();");
			//SP call format: CALL SP_show<Pillar_table_name>();
			
		}
		//adds a new query for each value pulled from pillar_name */

		
		PillarDAO pd = new PillarDAO();
		LinkedList<EcoPillar> markers = pd.showSelectedPillar(dataQuery);
		System.out.println(new Gson().toJson(markers.get(0)));
		request.setAttribute("markers", markers);

		RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
		rd.forward(request, response);
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
