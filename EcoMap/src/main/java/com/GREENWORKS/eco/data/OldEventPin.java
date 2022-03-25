package com.GREENWORKS.eco.data;

import javax.persistence.Entity;
import javax.persistence.Table;

/***
 * The OldEventPin is meant to logically represent events that haved aged past their date. The idea is that
 * events that have already passed can be moved to the old_events table rather than being outright deleted. 
 */
@Entity
@Table(name = "old_events")
public class OldEventPin extends Pin {
    
    /***
	 * Zero paramter constructor for the OldEventPin. 
	 */
	public OldEventPin() {
		
	}
	
	/***
	 * Single parameter constructor that assigns the provided Integer to the object id.
	 * @param id The value to be assigned to the object id. 
	 */
	public OldEventPin(Integer id) {
		this.id = id;
	}

}
