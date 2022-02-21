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
	
}
