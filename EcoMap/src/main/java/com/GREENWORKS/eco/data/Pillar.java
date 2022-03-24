package com.GREENWORKS.eco.data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/*
Pillars are general categories of green-related data.  

pillar_id       name
0               Unnamed Pillar
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
	@Column(name = "pillar_id", unique = true, nullable = false)
    private Integer pId;
    @Column(name="name", unique = false, nullable = true, length = 120)
    protected String name;
    
    /***
     * Zero parameter constructor. 
     */
    public Pillar() {

    }

    /***
     * Single parameter constructor for assigning pid upon instatiation. Good for testing. 
     */
    public Pillar(Integer id) {
        this.pId = id;
    }

	/***
	 * Accessor method for the pid instance variable. 
	 * @return Returns the contents of the instance variable.
	 */
    public Integer getPid() {
        return pId;
    }

	/***
	 * Mutator method for assignment of the pid instance variable. 
	 * @param genericPins The integer to be assigned. 
	 */
    public void setPid(Integer pid) {
        this.pId = pid;
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
	 * toString() method for printing the Pillar contents in a human readable way. 
	 */
    @Override
    public String toString() {
        return "Pillar [name=" + name + ", pid=" + pId + "]";
    }

    /***
     * hashCode() method for providing a basis of comparison between Pillars. 
     */
    @Override
    public int hashCode() {
      final int prime = 31;
      int result = 1;
      result = prime * result + ((pId == null) ? 0 : pId.hashCode());
      return result;
    }
    /***
     * equals() method for comparing Pillars. 
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
          return true;
        if (obj == null)
          return false;
        if (getClass() != obj.getClass())
          return false;
        Pillar other = (Pillar) obj;
        if (pId == null) {
          if (other.pId != null)
            return false;
        } else if (!pId.equals(other.pId))
          return false;
        return true;
      }
    
}
