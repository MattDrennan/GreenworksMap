package com.GREENWORKS.eco.data;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "pillars")
public class Pillar {

    @Id
	@Column(name = "pillar_id", unique = true, nullable = true)
    private Integer pid;
    @Column(name="name", unique = false, nullable = true, length = 120)
    protected String name;

    @OneToMany(mappedBy="pillar")
    private Set<SubPillar> subPillar;

    public Pillar() {

    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<SubPillar> getSubPillar() {
        return subPillar;
    }

    public void setSubPillar(Set<SubPillar> subPillar) {
        this.subPillar = subPillar;
    }
    
    
    
}
