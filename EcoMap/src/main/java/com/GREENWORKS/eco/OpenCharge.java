package com.GREENWORKS.eco;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.google.gson.Gson;

public class OpenCharge {

    // The agent we are identifying as when we send a request
    private static final String USER_AGENT = "Mozilla/5.0";

    // Where we will get data from the API
    private static final String GET_URL = "https://api.openchargemap.io/v3/poi?key=" + Cred.OPENCHARGEKEY + "&latitude=28.5384&longitude=-81.3789&distance=25";

    public static void main(String args[]) {

        // Include MYSQL
        MysqlConnect mysqlConnect = new MysqlConnect();

        // Delete all API items from DB
        String sql = "DELETE FROM locations WHERE api = 1";
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
            /*
                The code below connects to the URL and gets the data from the page,
                and puts it in a string
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

        // Convert data to JSON
        Gson gson = new Gson();

        // Load JSON data to mainData[] object
        mainData[] data = gson.fromJson(jsonString, mainData[].class);
        
        // Loop through all the data
        for(int i = 0; i <= data.length; i++)
        {
            // This needs to be here, or you can have errors
            try
            {
                /*
                    Get data from JSON like this:

                    System.out.println(data.ID);
                    System.out.println(data.DataProvider.Title);

                    Objects are matched to JSON data for readabiliy
                */

                // Set variables
                String address = data[i].AddressInfo.AddressLine1;
                String name = data[i].Connections[0].ConnectionType.FormalName;
                String coord = data[i].AddressInfo.Longitude.toString() + "," + data[i].AddressInfo.Latitude.toString();
                String content = "API";

                // Insert into database
                String sql2 = "INSERT INTO locations (iconid, address, name, coord, content, api) VALUES (1, '" + address + "', '" + name + "', '" + coord + "', '" + content + "', 1)";
                try
                {
                    PreparedStatement statement = mysqlConnect.connect().prepareStatement(sql2);
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
            }
            catch(Exception e)
            {
                // Nothing
            }
        }
    }
}

class mainData
{
  int ID;
  String UUID;
  DataProvider DataProvider;
  OperatorInfo OperatorInfo;
  UsageType UsageType;
  StatusType StatusType;
  AddressInfo AddressInfo;
  Connections[] Connections;
  SubmissionStatus SubmissionStatus;
}

class DataProvider
{
    String WebsiteURL;
    String Title;
}

class OperatorInfo
{
    String Title;
}

class UsageType
{
    Boolean isPayAtLocation;
    Boolean isMembershipRequired;
    Boolean isAccessKeyRequired;
}

class StatusType
{
    Boolean isOperational;
    Boolean isUserSelectable;
    Boolean Title;
}

class AddressInfo
{
  int ID;
  String Title;
  String AddressLine1;
  String AddressLine2;
  String Town;
  String Postcode;
  String Latitude;
  String Longitude;
  String ContactTelephone1;
  String ContactTelephone2;
  String ContactEmail;
}

class Connections
{
  int ID;
  int Amps;
  int Voltage;
  Double PowerKW;
  Level Level;
  ConnectionType ConnectionType;
}

class SubmissionStatus
{
  int isLive;
  String ID;
  Boolean Title;
}

class Level
{
    String Comments;
    Boolean isFastChargeCapable;
    int ID;
    String Title;
}

class ConnectionType
{
    String FormalName;
    String Description; 
}