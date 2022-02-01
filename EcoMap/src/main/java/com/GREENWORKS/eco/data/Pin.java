package com.GREENWORKS.eco.data;



import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.ColumnDefault;

/***
 * The parent abstract class for PinData. This class will not be directly instantiated
 * but rather its behavior will be instantiated through its children. This is a data 
 * class that is meant to be the representatation of the map pins that are displayed
 * on the UI. 
 */
@MappedSuperclass
public abstract class Pin {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	protected Integer id;
	@Column(name="iconid", unique = false, nullable = true)
	protected Integer iconId;
	@Column(name="name", unique = false, nullable = true, length = 120)
    protected String locationName;
	@Column(name="address", unique = false, nullable = true, length = 120)
    protected String locationAddress;
	@Column(name="coord", unique = false, nullable = true, length = 80)
    protected String coordinates;
	@Column(name="content", unique = false, nullable = true, columnDefinition="TEXT")
    protected String content; 
	@Column(name="dateStart", unique = false, nullable = true, columnDefinition="DATETIME")
    protected Date startDate;
	@Column(name="dateEnd", unique = false, nullable = true, columnDefinition="DATETIME")
    protected Date endDate;
	@Column(name="website", unique = false, nullable = true, length = 120)
	protected String websiteURL;
	@Column(name="api", unique = false, nullable = true)
	protected byte api;
    
	
    /***
     * Zero parameter constructor. 
     */
    public Pin() { 
    	
    }
    
    /***
     * This is a full parameter constructor for the Pin abstract class. 
     * @param id Value to be assigned to the id variable. 
     * @param iconId Value to be assigned to the iconId variable. 
     * @param startDate Value to be assigned to the startDate variable. 
     * @param endDate Value to be assigned to the endDate variable. 
     * @param locationName Value to be assigned to the locationName variable. 
     * @param locationAddress Value to be assigned to the locationAddress variable. 
     * @param coordinates Value to be assigned to the coordinates variable. 
     * @param content Value to be assigned to the content variable. 
     */
	public Pin(int id, Integer iconId, String locationName,
			String locationAddress, String coordinates, String content) {
		super();
		this.id = id;
		this.iconId = iconId;
		this.locationName = locationName;
		this.locationAddress = locationAddress;
		this.coordinates = coordinates;
		this.content = content;
	}

	/***
	 * This method adds slashes to a String to preserve the backslashes in textual entries. 
	 * @param s The String that will be modified. 
	 * @return A String that has had additional backslashes added. 
	 */
    public String addSlashes(String s)
	{
        s = s.replaceAll("\\\\", "\\\\\\\\");
        s = s.replaceAll("\\n", "\\\\n");
        s = s.replaceAll("\\r", "\\\\r");
        s = s.replaceAll("\\00", "\\\\0");
        s = s.replaceAll("'", "\\\\'");
        return s;
    }

	/***
	 * This method removes HTML tags from a String. It removes the tags <> and what
	 * is contained within the tags. 
	 * @param s The String that will be modified. 
	 * @return A modified String that will have no HTML tags. 
	 */

	public String removeTags(String s)
	{
		String noHTMLString = s.replaceAll("\\<.*?\\>", "");
		return noHTMLString;
	}

	/***
	 * This method executes the removeTags() and the addSlashes() methods consecutively. 
	 * @param s The String that will be modified.
	 * @return A modified String that will have no HTML tags and its backslashes preserved. 
	 */
	public String cleanInput(String s)
	{
		if(s != null) {
			String returnS = addSlashes(removeTags(s));
			return returnS;
		}
		return "";
	}

	/***
	 * Accessor method for the id instance variable. 
	 * @return Returns the contents of the instance variable. 
	 */
	public Integer getId() {
		return id;
	}

	/***
	 * Mutator method for assigning to the id instance variable. Conducts
	 * cleaning on the parameter. 
	 * @param id The value to be assigned. 
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/***
	 * Accessor method for the iconId instance variable. 
	 * @return Returns the contents of the instance variable. 
	 */
	public Integer getIconId() {
		return iconId;
	}

	/***
	 * Mutator method for assigning to the iconId instance variable. Conducts
	 * cleaning on the parameter. 
	 * @param iconId The value to be assigned. 
	 */
	public void setIconId(Integer iconId) {
		this.iconId = iconId;
	}

	/***
	 * Accessor method for the startDate instance variable. 
	 * @return Returns the contents of the instance variable. 
	 */	
	public Date getStartDate() {
		return startDate;
	}

	/***
	 * This mutator is unimplemented because it is implemented 
	 * in the child classes: Location and Event. 
	 * @param startDate The value to be assigned. 
	 */
	public void setStartDate(String startDate) {}

	/***
	 * Accessor method for the endDate instance variable. 
	 * @return Returns the contents of the instance variable. 
	 */	
	public Date getEndDate() {
		return endDate;
	}

	/***
	 * This mutator is unimplemented because it is implemented 
	 * in the child classes: Location and Event. 
	 * @param endDate The value to be assigned. 
	 */
	public void setEndDate(String endDate) {}

	/***
	 * Accessor method for the locationName instance variable. 
	 * @return Returns the contents of the instance variable. 
	 */	
	public String getLocationName() {
		return locationName;
	}

	/***
	 * Mutator method for assigning to the locationName instance variable. Conducts
	 * cleaning on the parameter. 
	 * @param locationName The value to be assigned. 
	 */
	public void setLocationName(String locationName) {
		this.locationName = cleanInput(locationName);
	}

	/***
	 * Accessor method for the locationAddress instance variable. 
	 * @return Returns the contents of the instance variable. 
	 */	
	public String getLocationAddress() {
		return locationAddress;
	}

	/***
	 * Mutator method for assigning to the locationAddress instance variable. Conducts
	 * cleaning on the parameter. 
	 * @param locationAddress The value to be assigned. 
	 */
	public void setLocationAddress(String locationAddress) {
		this.locationAddress = cleanInput(locationAddress);
	}

	/***
	 * Accessor method for the coordinates instance variable. 
	 * @return Returns the contents of the instance variable. 
	 */	
	public String getCoordinates() {
		return coordinates;
	}

	/***
	 * Mutator method for assigning to the coordinates instance variable. Conducts
	 * cleaning on the parameter. 
	 * @param coordinates The value to be assigned. 
	 */
	public void setCoordinates(String coordinates) {
		this.coordinates = cleanInput(coordinates);
	}
	
	/***
	 * Accessor method for the content instance variable. 
	 * @return Returns the contents of the instance variable. 
	 */		
	public String getContent() {
		return content;
	}
	
	/***
	 * Mutator method for assigning to the content instance variable. Conducts
	 * cleaning on the parameter. 
	 * @param coordinates The value to be assigned. 
	 */
	public void setContent(String content) {
		this.content = cleanInput(content);
	}
	
	
	/***
	 * Accessor method for the websiteURL instance variable. 
	 * @return Returns the contents of the instance variable. 
	 */		
	public String getWebsiteURL() {
		return websiteURL;
	}


	/***
	 * Mutator method for assigning to the websiteURL instance variable. Conducts
	 * cleaning on the parameter. 
	 * @param websiteURL The value to be assigned. 
	 */
	public void setWebsiteURL(String websiteURL) {
		this.websiteURL = websiteURL;
	}
	
	/***
	 * This methods generates an update SQL query that is populated with the 
	 * instance variables. 
	 * @return Returns the generated update SQL query. 
	 */	
	public String getUpdateQuery() {
		return "UPDATE locations SET iconid = '" + iconId + "', address = '" + locationAddress + 
											  "', name = '" + locationName + "', coord = '" + coordinates + 
											  "', dateStart = " + startDate + ", dateEnd = " + endDate + 
											  ", content = '" + content + "' WHERE id = '" + id + "'";
	}
	
	/***
	 * This method is meant to be overridden in each of it's child classes. 
	 * @return Returns a string. 
	 */
	public String getInsertQuery() {
		return "INSERT INTO locations (iconid, address, name, coord, dateStart, dateEnd) VALUES ('" 
										+ iconId + "', '" + locationAddress + "', '" + locationName + "', '" 
										+ coordinates + "', '" + startDate + "', '" + endDate + "')";             
	}
	
	/***
	 * This method generates a delete query based on the id of the PinData. 
	 * @return Returns the generated delete SQL query. 
	 */
	public String getDeleteQuery() {
		return "DELETE FROM locations WHERE id = '" + id + "'";
	}

	/***
	 * Overridden toString() method.
	 */
	@Override
	public String toString() {
		return "PinData [id=" + id + ", iconId=" + iconId + ", locationName=" + locationName + ", locationAddress="
				+ locationAddress + ", coordinates=" + coordinates + ", content=" + content + ", startDate=" + startDate
				+ ", endDate=" + endDate + "]";
	}
	
	@Override
	public int hashCode() {
		int result = getId() != null ? getId().hashCode() : 0;
		result = 31 * result;
		return result;
	}
	
	@Override
	public boolean equals(Object o) {
		if(this == o) 
			return true; // Are equal
		
		Pin otherPin = (GenericPin) o;
		
		if(this.getId() != null ? !this.getId().equals(otherPin.getId()) : otherPin.getId() != null)
			return false;
		
		return true;
	}
}