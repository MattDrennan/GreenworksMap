package com.GREENWORKS.eco;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import com.GREENWORKS.eco.data.GenericPin;
import com.GREENWORKS.eco.data.Pin;
import com.GREENWORKS.eco.data.SessionAssistant;
import com.google.gson.Gson;

/*** This class handles all the NREL API calls to request data related to fuel stations.
 *  It converts the data into a Json string and calls a session to save the new locations in the database.
 * 
 */
public class NREL {
        // The agent we are identifying as when we send a request
        private static final String USER_AGENT = "Mozilla/5.0";

        // Where we will get data from the API
        private static final String GET_URL = "https://developer.nrel.gov/api/alt-fuel-stations/v1.json?fuel_type=ELEC&state=FL&zip=32789,32801,32803,32804,32805,32806,32807,32808,32809,32810,32811,32812,32814,32819,32822,32824,32827,32829,32832,32835,32839&ev_network=Greenlots&limit=100&api_key=" + Cred.NRELAPIKEY;
    
       /***
        *  This is where the data request and conversion happens.
        */
        public static void main(String args[]) {
        // Include MYSQL
        MysqlConnect mysqlConnect = new MysqlConnect();

        // Delete all API items from DB
        String sql = "DELETE FROM locations WHERE api = 3"; // API is 3 for NREL
        try
        {
            PreparedStatement statement = mysqlConnect.connect().prepareStatement(sql);
            statement.executeUpdate();
            
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        finally
        {
            mysqlConnect.disconnect();
        }


            // Set up the JSON String
            String jsonString = "";
            
            try
            {
                /***
                *** Explanation ***
                The code below connects to the URL and gets the data from the page,
                and puts it in a string. 
                */
                URL obj = new URL(GET_URL);
                HttpURLConnection con = (HttpURLConnection) obj.openConnection();
                con.setRequestMethod("GET");
                con.setRequestProperty("User-Agent", USER_AGENT);
                int responseCode = con.getResponseCode();
                System.out.println("GET Response Code :: " + responseCode);
                if (responseCode == HttpURLConnection.HTTP_OK)
                {
                    BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                    String inputLine;
                    StringBuffer response = new StringBuffer();
    
                    // Append line by line
                    while ((inputLine = in.readLine()) != null)
                    {
                        response.append(inputLine);
                    }
                    in.close();
    
                    // Data to string
                    jsonString = response.toString();
                } else {
                    System.out.println("GET request not working.");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("Here");
            // Convert data to JSON
            Gson gson = new Gson();
    
            // Load JSON data to mainData[] object
            Root root = gson.fromJson(jsonString, Root.class);
            System.out.println("Result length: " + root.total_results);
    
            ArrayList<Pin> pinList = new ArrayList<Pin>();

            // Loop through all the data
            for(FuelStation fuelstation : root.fuel_stations)
            {
                // This needs to be here, or you can have errors
                try
                {
                    /*
                    *** For Quick String Viewing ***
                    Get data from JSON like this:
                    
                    System.out.println(data.ID);
                    System.out.println(data.DataProvider.Title);
    
                    Objects are matched to JSON data for readabiliy. Review the POJOs on the bottom of this class
                    to determine what data to use. 
                    */

                    // Set variables
                    String street = fuelstation.street_address;
                    String town = fuelstation.city;
                    String state = fuelstation.state;
                    String zipCode = fuelstation.zip;
                    String content = "This is an electronic vehicle charging station.";
                    String name = fuelstation.ev_network; // ev_network!=Non-Networked
                    String thumbnail = "<img src='https://github.com/EthanNValencia/EcoMapImageRepo/blob/master/GreenLots.png?raw=true' /><br /><br />";
                    
                    for(String connection: fuelstation.ev_connector_types) {
                        content += "<br>Connection Info: " + connection;
                    }
                    
                    String longitude = String.valueOf(fuelstation.longitude);
                    String latitude = String.valueOf(fuelstation.latitude);
                    
                    String website = fuelstation.ev_network_web;
    
                    Pin pin = new GenericPin();
                    pin.setApi((byte) 3);
                    pin.setIconId(3);
                    pin.setLocationName(name);
                    pin.setStreet(street);
                    pin.setState(state);
                    pin.setZipCode(zipCode);
                    pin.setTown(town);
                    pin.setThumbnail(thumbnail);
                    pin.setLongitude(longitude);
                    pin.setLatitude(latitude);
                    pin.setContentNoClean(content);
                    pin.setLink(website);
                    pinList.add(pin);
                }
                catch(Exception e)
                {
                    // Nothing
                }
            }

            SessionAssistant SessionAssistant = new SessionAssistant();
            SessionAssistant.saveList(pinList);
            
        }
}
    
    /*
    The classes below are POJO classes for JSON to POJO conversion. 
    */




class BD{
    int total;
}

class E85{
    int total;
}

class Stations{
    int total;
}

class ELEC{
    int total;
    Stations stations;
}

class HY{
    int total;
}

class LNG{
    int total;
}

class CNG{
    int total;
}

class LPG{
    int total;
}

class StationCounts{
    int total;
}

class EvNetworkIds{
    ArrayList<String> station;
    ArrayList<String> posts;
}

class FuelStation {
    String access_code;
    String access_days_time;
    String access_detail_code;
    String cards_accepted;
    String date_last_confirmed;
    String expected_date;
    String fuel_type_code;
    String groups_with_access_code;
    int id;
    String open_date;
    String owner_type_code;
    String status_code;
    String station_name;
    String station_phone;
    Date updated_at;
    String facility_type;
    String geocode_status;
    double latitude;
    double longitude;
    String city;
    String intersection_directions;
    String plus4;
    String state;
    String street_address;
    String zip;
    String country;
    String bd_blends;
    String cng_dispenser_num;
    String cng_fill_type_code;
    String cng_psi;
    String cng_renewable_source;
    String cng_total_compression;
    String cng_total_storage;
    String cng_vehicle_class;
    String e85_blender_pump;
    String e85_other_ethanol_blends;
    ArrayList<String> ev_connector_types;
    String ev_dc_fast_num;
    String ev_level1_evse_num;
    int ev_level2_evse_num;
    String ev_network;
    String ev_network_web;
    String ev_other_evse;
    String ev_pricing;
    String ev_renewable_source;
    String hy_is_retail;
    String hy_pressures;
    String hy_standards;
    String hy_status_link;
    String lng_renewable_source;
    String lng_vehicle_class;
    String lpg_primary;
    String lpg_nozzle_types;
    String ng_fill_type_code;
    String ng_psi;
    String ng_vehicle_class;
    String access_days_time_fr;
    String intersection_directions_fr;
    String bd_blends_fr;
    String groups_with_access_code_fr;
    String ev_pricing_fr;
    EvNetworkIds ev_network_ids;
}

class Root {
    String station_locator_url;
    int total_results;
    StationCounts station_counts;
    ArrayList<FuelStation> fuel_stations;
}

