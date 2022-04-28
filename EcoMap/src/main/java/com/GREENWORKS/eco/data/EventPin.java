package com.GREENWORKS.eco.data;

import javax.persistence.Entity;
import javax.persistence.Table;

/***
 * The Event class definition. Event is a child class of the PinData. Location is a type 
 * of PinData. 
 */
@Entity
@Table(name = "locations")
public class EventPin extends Pin {
		
	/***
	 * Constructor for Event.  
	 */
	public EventPin() {
		
	}

	/***
	 * Overridden mutator method for the content instance variable. This is so that the event date data is 
	 * displayed within the content. This way a user can see the dates on the frontend. 
	 * @param content The content of the event. 
	 */
	@Override
	public void setContent(String content) {
		this.content = content;
		if(this.startDate != null && this.endDate != null && !this.content.contains(this.startDate) && !this.content.contains(this.endDate)) {
			this.content = this.content + "<br>Event taking place from " + this.startDate + " to " + this.endDate + ".";
		}
	}
	
	/***
     * Overridden mutator method for the startDate instance variable. Conducts
	 * cleaning on the parameter. 
	 * @param startDate The begin date of the event. 
     */
	@Override
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	// this.startDate = "'" + cleanInput(startDate) + "'";

	/***
     * Overridden mutator method for the endDate instance variable. Conducts
	 * cleaning on the parameter. 
	 * @param endDate The end date of the event. 
     */
	@Override
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	// this.endDate = "'" + cleanInput(endDate) + "'";
	
    /***
     * Changes the iconId to match a location icon. 
	 * @param iconId The iconId. 
     */
	@Override
	public void setIconId(Integer iconId) {
		switch(iconId) {
		case 1:
			iconId = 9;
			break;
		case 2:
			iconId = 8;
			break;
		case 3:
			iconId = 13;
			break;
		case 4:
			iconId = 9;
			break;
		case 5:
			iconId = 11;
			break;
		case 6:
			iconId = 10;
			break;
		case 7:
			iconId = 12;
			break;
			}
		this.iconId = iconId;
	 }
	 
}