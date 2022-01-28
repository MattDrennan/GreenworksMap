package com.GREENWORKS.eco;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.google.gson.Gson;

public class OpenCharge {

    // The agent we are identifying as when we send a request
    private static final String USER_AGENT = "Mozilla/5.0";

    // Where we will get data from the API
    private static final String GET_URL = "https://api.openchargemap.io/v3/poi?key=" + Cred.OPENCHARGEKEY + "&latitude=28.5384&longitude=-81.3789&distance=0.5";

    public static void main(String args[]) {

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
                System.out.println("GET request not worked");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Convert data to JSON
        Gson gson = new Gson();

        // Load JSON data to mainData[] object
        mainData[] myTypes = gson.fromJson(jsonString, mainData[].class);
        
        // Loop through all the data
        for(int i = 0; i <= myTypes.length; i++)
        {
            // This needs to be here, or you can have errors
            try
            {
                /*
                    Get data from JSON like this:

                    System.out.println(mainData.ID);
                    System.out.println(mainData.DataProvider.Title);

                    Objects are matched to JSON data for readabiliy
                */
                System.out.println(myTypes[i].SubmissionStatus.ID);
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
    String Description; 
}