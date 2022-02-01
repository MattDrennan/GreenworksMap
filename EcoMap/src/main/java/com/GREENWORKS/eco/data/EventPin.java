package com.GREENWORKS.eco.data;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

/***
 * The Event class definition. Event is a child class of the PinData. Location is a type 
 * of PinData. 
 */
@Entity
@Table(name = "tbl_locations")
public class EventPin extends Pin {
	// "2022-01-31 15:00"
	private static final long serialVersionUID = 1L;
	
	/***
	 * Constructor for Event.  
	 */
	public EventPin() {
	
	}
	
	/***
     * Overridden mutator method for the startDate instance variable. Conducts
	 * cleaning on the parameter. 
	 * @param startDate The begin date of the event. 
     */
	@Override
	public void setStartDate(String startDate) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Date date;
		try {
			date = (Date) formatter.parse(startDate);
			this.startDate = date;
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	// this.startDate = "'" + cleanInput(startDate) + "'";

	/***
     * Overridden mutator method for the endDate instance variable. Conducts
	 * cleaning on the parameter. 
	 * @param endDate The end date of the event. 
     */
	@Override
	public void setEndDate(String endDate) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Date date;
		try {
			date = (Date) formatter.parse(endDate);
			this.endDate = date;
		} catch (ParseException e) {
			e.printStackTrace();
		}
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
	
	@Override
	public String getInsertQuery() {
		return "INSERT INTO locations (iconid, address, name, coord, dateStart, dateEnd) VALUES ('" + iconId + "', '" + locationAddress + "', '" + locationName + "', '" + coordinates + "', '" + startDate + "', '" + endDate + "')";             

	}
	 
}