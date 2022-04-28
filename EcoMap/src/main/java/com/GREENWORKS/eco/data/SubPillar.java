package com.GREENWORKS.eco.data;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/*
What is a SubPillar? A SubPillar is a sub-category of a Pillar. Pillars can be logically broken down into more specific 
categories of data. Those specific categories of data are what we call SubPillars. 

pillar_id 	sub_pillar_id 	name
0           00              Unnamed Sub-Pillar
1		    11		        Solar Arrays
1		    12		        Resilience Hubs
1		    13		        Solar Tables of Connection
2		    21		        Hydration Stations
2		    22		        Orlando Wetlands Park
2		    23		        Wastewater Treatment Plants
2		    24		        Potable Water Plants
3		    31		        EV Charging Stations
3		    32		        Bike Trails
3		    33		        Bus Stops
4		    41		        LEED/Energy Star/Green Globe certified buildings
5		    51		        Historic Trees of Significance
5		    52		        Iconic Parks and Neighborhood Centers
6		    61		        Farmer's Markets
6		    62		        Community Gardens
6		    63		        Urban Farms
6		    64		        Apiaries
7		    71		        Recycling and Food Waste Drop-offs
7		    72		        Landfill
7		    73		        Hazardous Waste Facility
7		    74		        Recycling Transfer Stations
*/

/***
 * The class definition for the SubPillar object. SubPillars are more specific sub-categories
 * of the Pillars. 
 */
@Entity
@Table(name = "subpillars")
public class SubPillar {
    
    @Id
	@Column(name = "sub_pillar_id", unique = true, nullable = false)
	protected Integer spId;
    @Column(name="name", unique = false, nullable = true, length = 120)
    protected String name;
    @Column(name="thumbnail", unique = false, nullable = true, length = 120)
    protected String thumbnail;
    @JoinColumn(name="pillar_id", nullable=true) 
    @ManyToOne(cascade = CascadeType.ALL) // MANY SubPillar may have ONE Pillar.
	protected Pillar pillar;
    @OneToMany(mappedBy="subPillar")
    private Set<GenericPin> genericPinSet; 

    /***
     * Zero parameter constructor. 
     */
    public SubPillar() {

    }
    
    /***
     * Single parameter constructor that is used for creating a bare SubPillar with a populated Id. 
     * @param spId The value that will be assigned to the spId instance variable. 
     */
    public SubPillar(Integer spId) {
        this.spId = spId;
    }

	/***
	 * Accessor method for the subPillarId variable. 
	 * @return Returns the contents of the instance variable.
	 */
    public Integer getSubPillarId() {
        return spId;
    }

	/***
	 * Mutator method for assigning to the subPillarId instance variable. 
	 * @param subPillarId The value to be assigned. 
	 */
    public void setSubPillarId(Integer subPillarId) {
        this.spId = subPillarId;
    }

	/***
	 * Accessor method for the name instance variable. 
	 * @return Returns the contents of the instance variable.
	 */
    public String getName() {
        return name;
    }

    /***
	 * Mutator method for assigning to the name instance variable. 
	 * @param name The value to be assigned. 
	 */
    public void setName(String name) {
        this.name = name;
    }

	/***
	 * Accessor method for the thumbnail instance variable. 
	 * @return Returns the contents of the instance variable.
	 */
    public String getThumbnail() {
        return thumbnail;
    }

	/***
	 * Mutator method for assigning to the thumbnail instance variable. 
	 * @param thumbnail The value to be assigned. 
	 */
    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

	/***
	 * Accessor method for the pillar object. 
	 * @return Returns the pillar.
	 */
    public Pillar getPillar() {
        return pillar;
    }

	/***
	 * Mutator method for assigning to the pillar object. 
	 * @param pillar The Pillar to be assigned. 
	 */
    public void setPillar(Pillar pillar) {
        this.pillar = pillar;
    }

	/***
	 * toString() method for printing the SubPillar contents in a human readable way. 
	 */
    @Override
    public String toString() {
        return "SubPillar [ name=" + name + ", pillar=" + pillar + ", subPillarId="
                + spId + ", thumbnail=" + thumbnail + "]";
    }
    
}
