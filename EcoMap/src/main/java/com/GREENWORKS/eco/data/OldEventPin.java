package com.GREENWORKS.eco.data;

import javax.persistence.Entity;
import javax.persistence.Table;

/***
 * 
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
