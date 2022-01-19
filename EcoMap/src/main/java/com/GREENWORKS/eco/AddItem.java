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
        String zip = request.getParameter("zip");
        String icon = request.getParameter("icon");

        // Get session
        HttpSession session = request.getSession();
        String username = (String)session.getAttribute("username");

        // Check if session active
        if(username != "" && username != null)
        {
            // Connect to MySQL
            MysqlConnect mysqlConnect = new MysqlConnect();

            // Statement to select all location data
            String sql = "INSERT INTO locations (iconid, address, name, zip) VALUES ('" + icon + "', '" + location + "', '" + locationName + "', '" + zip + "')";

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