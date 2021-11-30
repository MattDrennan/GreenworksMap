package com.GREENWORKS.DAO;

import java.sql.*;
//import java.sql.Date; //uncomment when the Events method is created
import java.util.*;

import com.GREENWORKS.object.*;

import java.io.*;

public class PillarDAO {
    private String url = "jdbc:mysql://localhost:3306/eco_map", username = "root", pswd = ""; //leave 'pswd' blank if using a file reference 

    /** 
     * The setPswd method accesses a file which has stored the dB password in an attempt to obfuscate.
     * If the password is explicitly declared, then this method is obsolete.
     */
    public void setPswd() { //use if and only if the pswd is not explicitly given but is referencing a file
		try {
			File login = new File("C:\\Users\\K. Alecia\\Desktop\\pswd.txt"); 
			Scanner sc = new Scanner(login);
			pswd = sc.nextLine();
			sc.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

    /**
     * The showPillar method will pull all values from the dB from the `locations` table.
     * @return pillarList The returned value is a LinkedList storing all the information for each 
     * data entry.
     */
    public LinkedList<EcoPillar> showPillars() {
        setPswd(); //uncomment if and only if the pswd is not explicitly given
        LinkedList<EcoPillar> pillarList = new LinkedList<EcoPillar>();

        try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(url, username, pswd);
			//Creates connection to MySQL dB

			String tableRetrieve = "CALL SP_showGWLocations()"; 
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(tableRetrieve);
			//creates query for dB

			while(rs.next()) {
				EcoPillar item = new EcoPillar();
				item.setSp_id(rs.getInt("Sub_PillarID"));
				item.setLoc_id(rs.getInt("Location_ID"));
				item.setAddress(rs.getString("Street_address"));
				item.setDescr(rs.getString("Descr"));
				item.setZip_Code(rs.getInt("Zip_code"));
				pillarList.add(item);
			} 
			//stores records to a LinkedList<EcoPillar>

			conn.close();
		} catch (Exception e) {
			System.out.println(e);
		} 

        return pillarList;
    }

    /**
     * The showSelectedPillar method pulls all the values from the dB that matches the filter criteria.
     * @param pillarCall This String will return the Stored Procedure call/s for the appropriate data.
     * @return pillarList The returned LinkedList stores all the information from each data
     * entry that meets the selection requirements.
     */
    public LinkedList<EcoPillar> showSelectedPillar(ArrayList<String> pillarCall) {
    	setPswd(); //uncomment if and only if the pswd is not explicitly given
        LinkedList<EcoPillar> pillarList = new LinkedList<EcoPillar>();
		String query = "";

        try {		
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(url, username, pswd);
			//Creates connection to the MySQL dB

			for(int i = 0; i < pillarCall.size(); i++) {
				query = pillarCall.get(i);
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery(query);
				
				while(rs.next()) {
					EcoPillar item = new EcoPillar();
					item.setSp_id(rs.getInt("Sub_PillarID"));
					item.setLoc_id(rs.getInt("Location_ID"));
					item.setAddress(rs.getString("Street_address"));
					item.setDescr(rs.getString("Descr"));
					item.setZip_Code(rs.getInt("Zip_code"));
					pillarList.add(item);
				} 	
			}
			// loops through the array of pillar calls and stores all the return values
			
			conn.close();
		} catch (Exception e) {
			System.out.println(e);
		} 

        return pillarList;
    }

    //TODO create method for the Events table/stored procedure
}
