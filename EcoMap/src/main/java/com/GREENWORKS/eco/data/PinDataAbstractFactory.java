package com.GREENWORKS.eco.data;

/***
 * Child class definition of Location. Location is a child class of PinData. Location is 
 * a type of PinData. 
 * TODO Documentation
 */
class Location extends PinData {

	/***
	 * Constructor for Location. Assigns the class name to the superclass name variable. 
	 */
	public Location() {
		super.dataType = this.getClass().getSimpleName();
	}
	
	// TODO Documentation
	@Override
	public void setStartDate(String startDate) { 
		this.startDate = "DEFAULT";
	}
	
	// TODO Documentation
	@Override
	public void setEndDate(String endDate) {
		this.endDate = "DEFAULT";
	}
}

/***
 * The Event class definition. Event is a child class of the PinData. Location is a type 
 * of PinData. 
 */
class Event extends PinData {

	/***
	 * Constructor for Event.  
	 */
	public Event() {
		super.dataType = this.getClass().getSimpleName();
	}
	
	@Override
	public void setStartDate(String startDate) {
		this.startDate = "'" + cleanInput(startDate) + "'";
	}
	
	@Override
	public void setEndDate(String endDate) {
		this.endDate = "'" + cleanInput(endDate) + "'";
	}
	
	// TODO Documentation
	@Override
	public void setIconId(String iconId) {
		switch(iconId) {
		case "1":
			iconId = "9";
			break;
		case "2":
			iconId = "8";
			break;
		case "3":
			iconId = "13";
			break;
		case "4":
			iconId = "9";
			break;
		case "5":
			iconId = "11";
			break;
		case "6":
			iconId = "10";
			break;
		case "7":
			iconId = "12";
			break;
			}
		this.iconId = iconId;
	 }
}

/***
 * The toolkit that creates a Location PinData object.
 */
class LocationToolkit extends PinDataAbstractFactory {

    /***
     * Method that returns a newly instantiated Location with the startDate
     * and endDate variables populated.
     * 
     * @return Returns a Location.
     */
	@Override
    public PinData createPinData(String startDate, String endDate) {
    	Location location = new Location();
    	location.setStartDate(startDate);
		location.setEndDate(endDate);
        return location;
    }

}

/***
 * The toolkit that creates a Medium priority ToDoItem.
 */
class EventToolkit extends PinDataAbstractFactory {

    /***
     * Method that returns a newly instantiated Event with the startDate
     * and endDate variables populated.
     * 
     * @return Returns an Event.
     */
	@Override
    public PinData createPinData(String startDate, String endDate) {
    	Event event = new Event();
	    event.setStartDate(startDate);
	    event.setEndDate(endDate);
        return event;
    }

}

/***
 * Abstract factory that is used to instantiate and return any of the priority level ToDoItem objects.
 */
public abstract class PinDataAbstractFactory {

    private static final LocationToolkit LOCATION_TOOLKIT = new LocationToolkit();
    private static final EventToolkit EVENT_TOOLKIT = new EventToolkit();
    private static boolean notAnEvent;
    
    // TODO Documentation
    protected static boolean notAnEvent(String startDate, String endDate) {
    	return (startDate == null || endDate == null || startDate == "" || endDate == "");
    }
    
    /***
     * This method is used to select the AbstractFactory that is needed to create the PinData child.
     * @param priority The dataType of the PinData object is expressed with True(Location) and False(Event).
     * @return Returns the AbstractFactory with the toolkit to build the specified PinData child class.
     */
    public static PinDataAbstractFactory getFactory(String startDate, String endDate) {
        PinDataAbstractFactory factory = null;
        if (notAnEvent(startDate, endDate)) { 
        	factory = LOCATION_TOOLKIT;
        } else if (!notAnEvent) {
        	factory = EVENT_TOOLKIT;
        }
        return factory;
    }
    
    /**
     * Child classes must implement their own method that is used to instantiate and return a PinData.
     * @return Returns a newly instantiated PinData.
     */
    public abstract PinData createPinData(String startDate, String endDate);
}
