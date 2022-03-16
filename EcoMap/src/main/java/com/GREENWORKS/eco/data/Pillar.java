package com.GREENWORKS.eco.data;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/*
Pillars are general categories of green-related data.  

pillar_id       name
1	            Clean Energy
2	            Clean Water
3	            Electric & Alt. Transportation
4	            Green Buildings
5	            Livability
6	            Local Food Systems
7	            Zero Waste
*/

/***
 * The class definition for the Pillar object. The Pillar is meant to be the data representation of the
 * 7 Green Works pillars. 
 */
@Entity
@Table(name = "pillars")
public class Pillar {

    @Id
	@Column(name = "pillar_id", unique = true, nullable = true)
    private Integer pid;
    @Column(name="name", unique = false, nullable = true, length = 120)
    protected String name;
    @OneToMany(mappedBy="pillar") // ONE Pillar may have MANY SubPillars.
    private Set<SubPillar> subPillar;
    
    /***
     * Zero parameter constructor. 
     */
    public Pillar() {

    }

	/***
	 * Accessor method for the pid instance variable. 
	 * @return Returns the contents of the instance variable.
	 */
    public Integer getPid() {
        return pid;
    }

	/***
	 * Mutator method for assignment of the pid instance variable. 
	 * @param genericPins The integer to be assigned. 
	 */
    public void setPid(Integer pid) {
        this.pid = pid;
    }

	/***
	 * Accessor method for the name instance variable. 
	 * @return Returns the contents of the instance variable.
	 */
    public String getName() {
        return name;
    }

	/***
	 * Mutator method for assignment of the name instance variable. 
	 * @param genericPins The string to be assigned. 
	 */
    public void setName(String name) {
        this.name = name;
    }

	/***
	 * Accessor method for the getSubPillar Set. 
	 * @return Returns the Set.
	 */
    public Set<SubPillar> getSubPillar() {
        return subPillar;
    }

	/***
	 * Mutator method for assigning the set of SubPillars. 
	 * @param genericPins The Set to be assigned. 
	 */
    public void setSubPillar(Set<SubPillar> subPillar) {
        this.subPillar = subPillar;
    }
    
}
