package com.GREENWORKS.eco.data;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/***
 * The class definition for the SubPillar object. The SubPillar object is a 
 */
@Entity
@Table(name = "subpillar")
public class SubPillar {
    
    @Id
	@Column(name = "sub_pillar_id", unique = true, nullable = true)
	protected Integer subPillarId;
    @Column(name="name", unique = false, nullable = true, length = 120)
    protected String name;
    @Column(name="thumbnail", unique = false, nullable = true, length = 120)
    protected String thumbnail;
    @OneToMany(mappedBy="subPillar")
    private Set<GenericPin> genericPins;

    /***
     * Zero parameter constructor. 
     */
    public SubPillar() {

    }

    public Integer getSubPillarId() {
        return subPillarId;
    }

    public void setSubPillarId(Integer subPillarId) {
        this.subPillarId = subPillarId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

}
