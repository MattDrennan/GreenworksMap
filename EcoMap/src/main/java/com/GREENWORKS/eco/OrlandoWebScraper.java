package com.GREENWORKS.eco;

import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Scanner;

import com.GREENWORKS.eco.data.GenericPin;
import com.GREENWORKS.eco.data.SessionAssistant;
import com.google.gson.Gson;

/***
 * This is a webscraper for the City of Orlando website. Rather crude but it gets the job done. 
 */
public class OrlandoWebScraper {
	
	private static final String GEOCODING_RESOURCE = "https://maps.googleapis.com/maps/api/geocode/json?address=";
    private static final String API_KEY = Cred.GOOGLEKEY;
    private static final String ANDKEY = "&key=";
    private static ArrayList<GenericPin> pinList = new ArrayList<GenericPin>();
	
	public static void main(String[] args) throws IOException {
		EcoMap ecomap = new EcoMap();
		GenericPin pin = new GenericPin();
		int pageNum = 0;
		String websiteURL = "";
		String address = "";
		String content = "";
		for(int i = 1; i <= 13; i++) {
			pageNum = i;
			websiteURL = "https://www.orlando.gov/Parks-the-Environment/Directory?dlv_OC%20CL%20Public%20Parks%20Reserves%20Listing=(pageindex=" + pageNum + ")&_ga=2.33517054.1180830333.1644162848-812513583.1643140795";
	        URL url;
			url = new URL(websiteURL);
			String scannedLine = "";
			Scanner scan = new Scanner(url.openStream());
			while(scan.hasNextLine()) {
				scannedLine = scan.nextLine();
				if(scannedLine.contains("<h2 class=\"list-item-title\">")) {
					pin = new GenericPin();
					pin.setLocationName(scan.nextLine().trim());
				}
				if(scannedLine.contains("<p class=\"list-item-address\">")) {
					address = ecomap.removeTags(scannedLine).trim();
					pin.setLocationAddress(address);
					content = ecomap.removeTags(scan.nextLine()).trim();
					pin.setContent(content);
					pin.setApi((byte) 2);
					pin.setIconId(5);
					pinList.add(pin);
				}
			}
		}
		SessionAssistant sessionAssistant = new SessionAssistant();
		for(GenericPin pins : pinList) {
			String body = "";
	        try {
				body = geocodeSync(pins.getLocationAddress());
			} catch (IOException | InterruptedException e) {
				e.printStackTrace();
			}
	        Gson gson = new Gson();
	        root data = gson.fromJson(body, root.class);
	        String coordinates = data.results.get(0).geometry.location.lng + "," + data.results.get(0).geometry.location.lat;
			pins.setCoordinates(coordinates);
		}
		sessionAssistant.saveList(pinList);
	}
	
	public static String geocodeSync(String query) throws IOException, InterruptedException {
		String address = query.replaceAll("\\s+", "+");
        HttpClient httpClient = HttpClient.newHttpClient();

        String requestUri = GEOCODING_RESOURCE + address + ANDKEY + API_KEY;

        HttpRequest geocodingRequest = HttpRequest.newBuilder().GET().uri(URI.create(requestUri)).timeout(Duration.ofMillis(2000)).build();

        HttpResponse<String> geocodingResponse = httpClient.send(geocodingRequest, HttpResponse.BodyHandlers.ofString());

        return geocodingResponse.body().toString();   
    }
	
}


class addresscomponent{
    String long_name;
    String short_name;
    ArrayList<String> types;
}

class location{
    double lat;
    double lng;
}

class northeast{
    double lat;
    double lng;
}

class southwest{
    double lat;
    double lng;
}

class viewport{
    northeast northeast;
    southwest southwest;
}

class geometry{
    location location;
    String location_type;
    viewport viewport;
}

class pluscode{
    String compound_code;
    String global_code;
}

class result{
    ArrayList<addresscomponent> address_components;
    String formatted_address;
    geometry geometry;
    String place_id;
    pluscode plus_code;
    ArrayList<String> types;
}

class root{
    ArrayList<result> results;
    String status;
}



