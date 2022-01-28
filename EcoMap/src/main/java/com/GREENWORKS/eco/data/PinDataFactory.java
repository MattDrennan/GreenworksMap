package com.GREENWORKS.eco.data;

/***
 * The toolkit that creates a Location PinData object.
 */
class LocationToolkit extends PinDataFactory {

    /***
     * Method that returns a newly instantiated Location with the startDate
     * and endDate variables populated.
	 * @param startDate The begin date. 
	 * @param endDate The end date. 
     * @return Returns a Location.
     */
	@Override
    public PinData createPinData() {
    	Location location = new Location();
        return location;
    }

}

/***
 * The toolkit that creates a Medium priority ToDoItem.
 */
class EventToolkit extends PinDataFactory {

    /***
     * Method that returns a newly instantiated Event with the startDate
     * and endDate variables populated.
	 * @param startDate The begin date. 
	 * @param endDate The end date. 
     * @return Returns an Event.
     */
	@Override
    public PinData createPinData() {
    	Event event = new Event();
        return event;
    }

}

/***
 * Abstract factory that is used to instantiate and return any of the priority level ToDoItem objects.
 */
public abstract class PinDataFactory {

    private static final LocationToolkit LOCATION_TOOLKIT = new LocationToolkit();
    private static final EventToolkit EVENT_TOOLKIT = new EventToolkit();
    private static boolean notAnEvent;
    
    /***
	 * 
	 * @param startDate
	 * @param endDate
	 * @return
	 */
    protected static boolean notAnEvent(String startDate, String endDate) {
    	return (startDate == null || endDate == null || startDate == "" || endDate == "");
    }
    
    /***
     * This method is used to select the AbstractFactory that is needed to create the PinData child.
     * @param priority The dataType of the PinData object is expressed with True(Location) and False(Event).
     * @return Returns the AbstractFactory with the toolkit to build the specified PinData child class.
     */
    public static PinDataFactory getFactory(String startDate, String endDate) {
        PinDataFactory factory = null;
        if (notAnEvent(startDate, endDate)) { 
        	factory = LOCATION_TOOLKIT;
        } else if (!notAnEvent) {
        	factory = EVENT_TOOLKIT;
        }
        return factory;
    }
    
	/***
     * Child classes must implement their own method that is used to instantiate and return a PinData.
	 * @param startDate The begin date. 
	 * @param endDate The end date. 
	 * @return Returns a newly instantiated PinData.
	 */
    public abstract PinData createPinData();

}