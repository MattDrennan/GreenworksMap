package com.GREENWORKS.eco.data;


/***
 * The parent abstract class for PinData. This class will not be directly instantiated
 * but rather its behavior will be instantiated through its children. This is a data 
 * class that is meant to be the representatation of the map pins that are displayed
 * on the UI. 
 * TODO Documentation
 */
public abstract class PinData {
	
	private String id;
	protected String iconId;
    private String startDate;
    private String endDate;
    private String locationName;
    private String locationAddress;
    private String zipCode;
    protected String dataType;
    
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
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = cleanInput(id);
	}
	public String getIconId() {
		return iconId;
	}
	public void setIconId(String iconId) {
		this.iconId = cleanInput(iconId);
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = cleanInput(startDate);
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = cleanInput(endDate);
	}
	public String getLocationName() {
		return locationName;
	}
	public void setLocationName(String locationName) {
		this.locationName = cleanInput(locationName);
	}
	public String getLocationAddress() {
		return locationAddress;
	}
	public void setLocationAddress(String locationAddress) {
		this.locationAddress = cleanInput(locationAddress);
	}
	public String getDataType() {
		return dataType;
	}
	public void setDataType(String dataType) {
		this.dataType = dataType;
	}	
	public String getUpdateQuery() {
		return "UPDATE locations SET iconid = '" + iconId + "', address = '" + locationAddress + 
				"', name = '" + locationName + "', zip = '" + zipCode + 
				"', dateStart = " + startDate + ", dateEnd = " + endDate + 
				" WHERE id = '" + id + "'";
	}
	
}