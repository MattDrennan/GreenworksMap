package com.GREENWORKS.eco;
 
import java.io.*;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
 
@WebServlet("/additem")
public class AddItem extends HttpServlet {
    
    public AddItem()
    {
        super();
    }
 
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        // Get POST variables
        String locationName = request.getParameter("locationName");
        String location = request.getParameter("location");
        String coord = request.getParameter("coord");
        String icon = request.getParameter("icon");
        String dateStart = request.getParameter("dateStart");
        String dateEnd = request.getParameter("dateEnd");
        String content = request.getParameter("content");

        // Get session
        HttpSession session = request.getSession();
        String username = (String)session.getAttribute("username");

        // Check if session active
        if(username != "" && username != null)
        {
            // Connect to MySQL
            MysqlConnect mysqlConnect = new MysqlConnect();

            // Call EcoMap
            EcoMap m = new EcoMap();

            // Set up sql
            String sql;

            // Check if an event
            if((dateStart == "" && dateEnd == "") || (dateStart == null && dateEnd == null))
            {
                // Statement to select all location data - not an event
                sql = "INSERT INTO locations (iconid, address, name, coord, content) VALUES ('" + m.cleanInput(icon) + "', '" + m.cleanInput(location) + "', '" + m.cleanInput(locationName) + "', '" + m.cleanInput(coord) + "', '" + m.cleanInput(content) + "')";
            }
            else
            {
                // Set up iconid
                String iconid = m.cleanInput(icon);

                // Check icon ID values and convert to event icon
                switch(iconid)
                {
                    case "1":
                        iconid = "9";
                        break;
                    case "2":
                        iconid = "8";
                        break;
                    case "3":
                        iconid = "13";
                        break;
                    case "4":
                        iconid = "9";
                        break;
                    case "5":
                        iconid = "11";
                        break;
                    case "6":
                        iconid = "10";
                        break;
                    case "7":
                        iconid = "12";
                        break;
                }

                // Statement to select all location data - is an event
                sql = "INSERT INTO locations (iconid, address, name, coord, dateStart, dateEnd) VALUES ('" + iconid + "', '" + m.cleanInput(location) + "', '" + m.cleanInput(locationName) + "', '" + m.cleanInput(coord) + "', '" + m.cleanInput(dateStart) + "', '" + m.cleanInput(dateEnd) + "')";             
            }

            try
            {
                // Try statement
                PreparedStatement statement = mysqlConnect.connect().prepareStatement(sql);
                statement.executeUpdate();
            }
            catch (SQLException e)
            {
                // Error
                e.printStackTrace();
            }
            finally
            {
                // Disconnect when done
                mysqlConnect.disconnect();
            }

            // Redirect user
            RequestDispatcher dispatcher = request.getRequestDispatcher("admin.jsp");
            dispatcher.forward(request, response);
        }
    }
}