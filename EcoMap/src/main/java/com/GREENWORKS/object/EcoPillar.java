package com.GREENWORKS.object;

/**
 * Creates a POJO for the Orlando Greenworks Pillar locations
 */
public class EcoPillar {
    private String address, descr;
    private int loc_id, sp_id, zip_code;

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

	public void setDescr(String description) {
		this.descr = description;
	}
	
    public int getZip_Code() {
        return zip_code;
    }

    public void setZip_Code(int zip_code) {
        this.zip_code = zip_code;
    }
	
	@Override
	public String toString() {
		return "EcoPillar [loc_id=" + loc_id + ", sp_id="+ sp_id + "address=" + 
		address + ", description=" + descr + ", zip_code=" + zip_code + "]";
	}
}
