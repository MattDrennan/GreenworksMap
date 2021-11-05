package com.GREENWORKS.DAO;

import java.sql.*;
//import java.sql.Date; //TODO uncomment when the Events method is created
import java.util.*;

import com.GREENWORKS.object.*;

import java.io.*;

public class PillarDAO {
    private String url = "jdbc:mysql://localhost:3306/orlando eco-map", username = "", pswd = ""; //leave 'pswd' blank if using a file reference 
	private File login = new File("");
    //TODO add the dB url, username, and pswd filepath

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
        //setPswd(); //uncomment if and only if the pswd is not explicitly given
        LinkedList<EcoPillar> pillarList = new LinkedList<EcoPillar>();

        try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(url, username, pswd);
			String tableRetrieve = "CALL SP_showPillars()"; 
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(tableRetrieve);
			
			while(rs.next()) {
				EcoPillar item = new EcoPillar();
				item.setSp_id(rs.getInt("sp_id"));
				item.setAddress(rs.getString("street_address"));
				item.setDescription(rs.getString("loc_descr"));
				item.setZip_Code(rs.getInt("zip_code"));
				item.setLat(rs.getFloat("lat"));
				item.setLng(rs.getFloat("lng"));
				pillarList.add(item);
			} 
			
			conn.close();
		} catch (Exception e) {
			System.out.println(e);
		} 

        return pillarList;
    }

//TODO confirm that showPillarSelected is able to pass the correct info to the correct table
    /**
     * The showSelectedPillar method pulls all the values from the dB that matches the filter criteria.
     * @param pillarCall This String will return the Stored Procedure call/s for the appropriate data.
     * @return pillarList The returned LinkedList stores all the information from each data
     * entry that meets the selection requirements.
     */
    public LinkedList<EcoPillar> showSelectedPillar(ArrayList<String> pillarCall) {
        //setPswd(); //uncomment if and only if the pswd is not explicitly given
        LinkedList<EcoPillar> pillarList = new LinkedList<EcoPillar>();
		String query = "";

        try {
			Scanner sc = new Scanner(login);
			pswd = sc.nextLine();
			sc.close();
			
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(url, username, pswd);
			//Query string for the MySQL dB

			for(int i = 0; i < pillarCall.size(); i++) {
				String tableRetrieve = query;
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery(tableRetrieve);
				
				while(rs.next()) {
					EcoPillar item = new EcoPillar();
					item.setSp_id(rs.getInt("sp_id"));
					item.setAddress(rs.getString("Street_address"));
					item.setDescription(rs.getString("Description"));
					item.setZip_Code(rs.getInt("Zip"));
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

	public int iconSelect(int sp_id) {
		int position = 0;
		String pillar_code = ""; 

		try {
			Scanner sc = new Scanner(login);
			pswd = sc.nextLine();
			sc.close();
			
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(url, username, pswd);

			//Query string for the MySQL dB
			String tableRetrieve = "CALL SP_pillar_code(" + sp_id + ");"; //calls SP to pull pillar code
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(tableRetrieve);
			
			pillar_code = rs.getString("pillar_code"); //stores pillar code in variable	
			conn.close();
		} catch (Exception e) {
			System.out.println(e);
		} 

		switch (pillar_code) {
			case "CE": 
			position = 0;
			break;
			case "GB": 
			position = 1;
			break;
			case "LV": 
			position = 2;
			break;
			case "LF": 
			position = 3;
			break;
			case "ZW": 
			position = 4;
			break;
			case "EA": 
			position = 5;
			break;
			case "CW": 
			position = 6;
			break;
		}
		//converts sub-pillar id to a pillar code position for the icons array in the mapEmbed.js file

		return position;
	}

    //TODO create method for the Events table/stored procedure
}
