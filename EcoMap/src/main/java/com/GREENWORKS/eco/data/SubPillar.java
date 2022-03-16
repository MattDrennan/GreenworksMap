package com.GREENWORKS.eco.data;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/***
 * The class definition for the SubPillar object. 
 */
@Entity
@Table(name = "subpillars")
public class SubPillar {
    
    @Id
	@Column(name = "sub_pillar_id", unique = true, nullable = true)
	protected Integer subPillarId;
    @Column(name="name", unique = false, nullable = true, length = 120)
    protected String name;
    @Column(name="thumbnail", unique = false, nullable = true, length = 120)
    protected String thumbnail;
    @JoinColumn(name="pillar_id", nullable=true)
	@ManyToOne
	protected Pillar pillar;
    @OneToMany(mappedBy="subPillar")
    private Set<GenericPin> genericPins;

    /***
     * Zero parameter constructor. 
     */
    public SubPillar() {

    }

	/***
	 * Accessor method for the name subPillarId variable. 
	 * @return Returns the contents of the instance variable.
	 */
    public Integer getSubPillarId() {
        return subPillarId;
    }

	/***
	 * Mutator method for assigning to the subPillarId instance variable. 
	 * @param subPillarId The value to be assigned. 
	 */
    public void setSubPillarId(Integer subPillarId) {
        this.subPillarId = subPillarId;
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

    public Pillar getPillar() {
        return pillar;
    }

    public void setPillar(Pillar pillar) {
        this.pillar = pillar;
    }

    public Set<GenericPin> getGenericPins() {
        return genericPins;
    }

    public void setGenericPins(Set<GenericPin> genericPins) {
        this.genericPins = genericPins;
    }

}
