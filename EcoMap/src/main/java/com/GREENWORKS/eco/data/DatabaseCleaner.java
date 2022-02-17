package com.GREENWORKS.eco.data;

import java.util.List;
import java.util.Set;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;


/***
 * This class will remove redundant points from the locations table and put them in the problem_locations 
 * table. The class has the ability to determine which redundant points are good, so the good points will
 * remain in the locations table.
 */
public class DatabaseCleaner {
	
	private Set<Integer> zipMap = new HashSet<Integer>(); 
	
	/***
	 * For now, this is how this tool will be run, however, it would be good to integrate it into the
	 * work-flow later on. 
	 */
	public static void main(String[] args) {
		SessionAssistant sessionAssistant = new SessionAssistant(); 
		List<Pin> pinList = sessionAssistant.getAllPinsList();
		DatabaseCleaner databaseCleaner = new DatabaseCleaner();
		HashMap<String, ArrayList<Pin>> addressPinMap = databaseCleaner.findRedundantAddress(pinList);
		pinList.clear(); // Release memory.
		ArrayList<Pin> deleteList = databaseCleaner.solveConflicts(addressPinMap);
		ArrayList<ProblemPin> problemPinList = databaseCleaner.convertToProblemPinList(deleteList);
		sessionAssistant.saveList(problemPinList);
		sessionAssistant.deleteList(deleteList);
	}
	
	/***
	 * This method is useful for understanding how this class functions. It will print
	 * all the contents of an ArrayList<Pin>. 
	 * @param list The ArrayList<Pin> that will be printed to the console. 
	 */
	public void printPinList(ArrayList<Pin> list){
		System.out.println(list.size());
		for(Pin pin : list){
			System.out.println(pin);
		}
	}

	/***
	 * This method is used to print the contents of the reduMap. The reduMap contains 
	 * a key that is the address and the contents is an ArrayList<Pin> of Pin objects
	 * that have the same address. 
	 */
	public void printReduMap(HashMap<String, ArrayList<Pin>> addressPinMap) {
		Set<String> keySet = addressPinMap.keySet();
		for(String key : keySet) {
			System.out.println("Key: " + key);
			ArrayList<Pin> pinList = addressPinMap.get(key);
			for(Pin pin : pinList){
				System.out.println(pin);
			}
		}
	}

	/***
	 * This method converts an ArrayList<Pin> to an ArrayList<ProblemPin>. 
	 * @param deleteList The ArrayList<Pin>.
	 * @return The converted ArrayList. 
	 */
	public ArrayList<ProblemPin> convertToProblemPinList(ArrayList<Pin> deleteList){
		ArrayList<ProblemPin> problemPinList = new ArrayList<>();
		for(Pin pin : deleteList){
			ProblemPin problemPin = new ProblemPin();
			problemPin.copyPin(pin);
			problemPinList.add(problemPin);
		}
		return problemPinList;
	}

	/***
	 * I used this method to print out the entire contents of the database by iconId. It is
	 * useful for data extraction. The String printed String is formatted to be compatible 
	 * with a SQL insertion statement. 
	 * 		Note: This might be better placed in the SessionAssistant.  
	 * @param pinList Requires the list of Pins to be printed. 
	 */
	public static void printPinDataByIconId(List<Pin> pinList, int iconId) {
		for(Pin pin: pinList) {
			if(pin.getIconId() == iconId) { 
				System.out.println(
						"(" + pin.getIconId() + " ,\"" + pin.getLocationName() + "\", \""
							+ pin.getLocationAddress() + "\", \"" + pin.getCoordinates() + "\", \""
							+ pin.getContent() + "\"),"
				);
			}
		}
	}
	
	/***
	 * This method goes through the pinList that is provided to it and isolates Pins that
	 * have the same address. 
	 * @param pinList The List<Pin> that will be analyzed. 
	 */
	public HashMap<String, ArrayList<Pin>> findRedundantAddress(List<Pin> pinList) {
		HashMap<String, ArrayList<Pin>> pinMap = new HashMap<String, ArrayList<Pin>>();
		HashMap<String, ArrayList<Pin>> addressPinMap = new HashMap<String, ArrayList<Pin>>();
		for(Pin pin : pinList) {
			String address = pin.getLocationAddress();
			Integer zip = getZip(address);
			if(!zipMap.contains(zip)) { 
				if(address.substring(address.length() - 5).trim().length() != 5) {
					System.out.println("Zip code error: " + address);
				} else
					zipMap.add(zip);
			}
			if(pinMap.containsKey(address)) {
				ArrayList<Pin> list = pinMap.get(address);
				list.add(pin);
				addressPinMap.put(address, list);
			}
			ArrayList<Pin> list = new ArrayList<Pin>();
			list.add(pin);
			pinMap.put(address, list); 
		}
		return addressPinMap;
	}
	
	/***
	 * This method is used to extract zipcodes from addresses. The format of the address must
	 * have the zip code trailing on the end.  
	 * @param address The address as a String. 
	 * @return Returns the zip code as an Integer. 
	 */
	public Integer getZip(String address) {
		Integer zip = null;
		try {
			zip = Integer.parseInt(address.substring(address.length() - 5).trim()); // Assuming the last 5 are the zip code. 
		} catch (NumberFormatException nfe) {
			// System.out.println("The provided String did not have content that matched a zipcode.");
		}
		return zip;
	}
	
	/***
	 * This method solves the conflicted pins by removing the 
	 * @return 
	 */
	public ArrayList<Pin> solveConflicts(HashMap<String, ArrayList<Pin>> addressPinMap) {
		ArrayList<Pin> deleteList = new ArrayList<Pin>();
		Set<String> keySet = addressPinMap.keySet();
		for(String key : keySet) {
			ArrayList<Pin> conflictedPins = addressPinMap.get(key);
			Pin firstPin = conflictedPins.get(0);
			for(int i = 1; i < conflictedPins.size(); i++) {
				if(zipMap.contains(getZip(conflictedPins.get(i).getContent()))) {
					deleteList.add(conflictedPins.get(i));
				} else if (firstPin.getContent().equals(conflictedPins.get(i).getContent())) {
					deleteList.add(conflictedPins.get(i));
				}
			}
		}
		return deleteList;
	}

}