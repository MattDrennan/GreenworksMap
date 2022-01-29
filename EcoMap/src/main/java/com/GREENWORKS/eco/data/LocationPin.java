package com.GREENWORKS.eco.data;

import javax.persistence.Entity;
import javax.persistence.Table;

/***
 * Child class definition of Location. Location is a child class of PinData. Location is 
 * a type of PinData. 
 */
@Entity
@Table(name = "tbl_locations")
public class LocationPin extends Pin {

	private static final long serialVersionUID = 1L;

	/***
	 * Constructor for Location. Assigns the class name to the superclass name variable. 
	 */
	public LocationPin() {
		
	}
	
	/***
     * Overridden mutator method for the startDate instance variable. Conducts
	 * cleaning on the parameter. 
	 * @param startDate The begin date of the event. 
     */
	@Override
	public void setStartDate(String startDate) { 
		this.startDate = "DEFAULT";
	}
	
	/***
     * Overridden mutator method for the endDate instance variable. Conducts
	 * cleaning on the parameter. 
	 * @param endDate The begin date of the event. 
     */
	@Override
	public void setEndDate(String endDate) {
		this.endDate = "DEFAULT";
	}
	
	@Override
	public String getInsertQuery() {
		return "INSERT INTO locations (iconid, address, name, coord, content) VALUES ('" + iconId + "', '" + locationAddress + "', '" + locationName + "', '" + coordinates + "', '" + content + "')";
	}

}