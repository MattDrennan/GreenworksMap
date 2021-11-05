package com.GREENWORKS.object;

import java.sql.Date;

public class EcoPillar {
    private String address, descr;
    private int loc_id, sp_id, zip_code;
	private Date startDate, endDate;
	private float lat, lng;

	public int getLoc_id() {
		return loc_id;
	}

	public void setLoc_id(int loc_id) {
		this.loc_id = loc_id;
	}

	public int getSp_id() {
		return sp_id;
	}

	public void setSp_id(int sp_id) {
		this.sp_id = sp_id;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDescr() {
		return descr;
	}

	public void setDescription(String description) {
		this.descr = description;
	}
	
    public int getZip_Code() {
        return zip_code;
    }

    public void setZip_Code(int zip_code) {
        this.zip_code = zip_code;
    }

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}
	
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public float getLat() {
		return lat;
	}

	public void setLat(float lat) {
		this.lat = lat;
	}

	public float getLng() {
		return lng;
	}

	public void setLng(float lng) {
		this.lng = lng;
	}
	
	@Override
	public String toString() {
		return "EcoPillar [loc_id=" + loc_id + ", spi_id="+ sp_id + "address=" + 
		address + ", description=" + descr + ", zip_code=" + zip_code + ", endDate=" + 
		endDate + "]";
	}
	public String toPrint(int num) {
		return "{lat:" +  lat + "lng:" + lng + "}, icons[" + num + "], <h3><b>" + 
		descr + "</b></h3><p>" + address + "&emsp;" + zip_code + "</p>";
	}
}
