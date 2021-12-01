package com.GREENWORKS.controller;

import com.GREENWORKS.DAO.*;
import com.GREENWORKS.object.*;

// import java.io.File;
// import java.io.FileWriter;
// import java.io.IOException;
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
    public static String toJsonFile(LinkedList<EcoPillar> values) {
        String jsObject = "";
        int i = 1, length = values.size();

        jsObject += "[\n"; //to add the opening bracket
            for (EcoPillar jsValue : values) {
                String input = jsValue.toString();

                if (i < length) {
                    jsObject += "\t\t" + input + ",\n";
                    i++;
                    //appends a comma at the end of the json object and increases the current count
                } else {
                    jsObject += "\t\t" + input; 
                }
            }
            
            jsObject += "\n\t]"; //to add the closing bracket
            //closes open IO resources
            return jsObject;
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
