package com.GREENWORKS.eco.data;

import javax.persistence.Entity;
import javax.persistence.Table;

/***
 * This is the ProblemPin class. It is meant to be the representation of bad data or problematic data. This 
 * class will be used to move problematic data from the locations to the problem_locations data. 
 */
@Entity
@Table(name = "problem_locations")
public class ProblemPin extends Pin {

	/***
	 * Zero paramter constructor for the ProblemPin. 
	 */
	public ProblemPin() {
		
	}
	
	/***
	 * Single parameter constructor that assigns the provided Integer to the object id.
	 * @param id The value to be assigned to the object id. 
	 */
	public ProblemPin(Integer id) {
		this.id = id;
	}
	
	/***
	 * This is a ProblemPin constructor that takes a Pin as an argument. It will copy all of the
	 * parameters of the provided Pin to it. 
	 * @param pin The Pin object to be copied. 
	 */
	public void copyPin(Pin pin) {
		this.id = pin.getId();
		this.iconId = pin.getIconId();
		this.locationName = pin.getLocationName();
		this.startDate = pin.getStartDate();
		this.endDate = pin.getEndDate();
		this.locationAddress = pin.getLocationAddress();
		this.coordinates = pin.getCoordinates();
		this.content = pin.getContent();
		this.websiteURL = pin.getWebsiteURL();
		this.api = pin.getApi();
	}
	
}
