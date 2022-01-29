package com.GREENWORKS.eco.data;

import javax.persistence.Entity;
import javax.persistence.Table;

/***
 * This is meant to logically represent a generic point of data. The reason why this is 
 * useful is because there will be times when it is desirable to work with data of 
 * unidenfied specifications. Or when working with the data itself is more important 
 * than identifying the data. 
 */
@Entity
@Table(name = "tbl_locations")
public class GenericPin extends Pin {

	private static final long serialVersionUID = 1L;
	
	/***
	 * Constructor for GenericPin. Assigns the class name to the superclass name variable. 
	 */
	public GenericPin() {
		
	}
}
