package com.GREENWORKS.object;

import java.sql.Date;

public class EcoPillar {
    private String id;
	private String address;
	private String description;
    private int zip_code;
	private Date endDate;
	
	 public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
    public int getZip_Code() {
        return zip_code;
    }

    public void setZip_Code(int zip_code) {
        this.zip_code = zip_code;
    }

	public Date getEndDate() {
		return endDate;
	}
	
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	@Override
	public String toString() {
		return "EcoPillar [id=" + id + ", address=" + address + ", description=" + description + ", zip_code=" + zip_code + ", endDate=" + endDate + "]";
	}
	public String toPrint() {
		return address + "; " + description;
	}
}
