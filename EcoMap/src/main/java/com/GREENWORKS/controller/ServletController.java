package com.GREENWORKS.controller;

import com.GREENWORKS.DAO.*;
import com.GREENWORKS.object.*;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import com.google.gson.Gson;

/**
 * This class handles the business logic of the servlets.
 */
public class ServletController {
    /**
     * This method provides a list of records to the calling function.
     * @return a list of all pillar records
     */
    public static LinkedList<EcoPillar> returnAll() {
        PillarDAO pd = new PillarDAO();
		return pd.showPillars();
    }

    /**
     * This method filters the results from a table of records.
     * @param dataQuery - list of data queries as specified by the calling function. 
     * @return a list of records based on the calling criteria.
     */
    public static LinkedList<EcoPillar> returnFiltered(ArrayList<String> dataQuery) {
        PillarDAO pd = new PillarDAO();
		return pd.showSelectedPillar(dataQuery);
    }

    /**
     * This method writes the parameter value to a JSON file
     * @param jsonText - takes an EcoPillar value to convert to JSON
     */
    public static void toJsonFile(LinkedList<EcoPillar> jsonText) {
        try {
            String filepath = "EcoMap\\src\\main\\resources";
            FileWriter fileWrite = new FileWriter(filepath); // to create a means to write text to the newly create file

            while (jsonText != null) {
                String input = new Gson().toJson(jsonText), jsonValue = input + "\n";
                if (!input.isEmpty() && !input.matches(" ")) {
                    fileWrite.write(jsonValue); 
                    System.out.println(); //test only - delete when done
                    //writes the JSON value to the file when the line is not empty
                }
            }

            fileWrite.close();
            //closes open IO resources
        } catch (IOException ioe) {
            ioe.getStackTrace();
        }
    }

    /**
     * This method is strictly for testing purposes only. 
     * It takes in a value and then outputs it in JSON format
     * to the calling function.
     * @param marker - an EcoPillar object to be converted
     * @return the JSON form of the input value
     */
    public static String toJsonTest(EcoPillar marker) {
        return new Gson().toJson(marker);
    }
}
