package com.GREENWORKS.eco.data;

/***
 * The toolkit that creates a Location PinData object.
 */
class LocationToolkit extends PinFactory {

    /***
     * Method that returns a newly instantiated Location with the startDate
     * and endDate variables populated.
	 * @param startDate The begin date. 
	 * @param endDate The end date. 
     * @return Returns a Location.
     */
	@Override
    public Pin createPinData() {
    	LocationPin location = new LocationPin();
        return location;
    }

}

/***
 * The toolkit that creates a Medium priority ToDoItem.
 */
class EventToolkit extends PinFactory {

    /***
     * Method that returns a newly instantiated Event with the startDate
     * and endDate variables populated.
	 * @param startDate The begin date. 
	 * @param endDate The end date. 
     * @return Returns an Event.
     */
	@Override
    public Pin createPinData() {
    	EventPin event = new EventPin();
        return event;
    }

}

/***
 * Abstract factory that is used to instantiate and return any of the priority level ToDoItem objects.
 */
public abstract class PinFactory {

    private static final LocationToolkit LOCATION_TOOLKIT = new LocationToolkit();
    private static final EventToolkit EVENT_TOOLKIT = new EventToolkit();
    private static boolean notAnEvent;
    
    /***
	 * This method is used to determine if the provided dataset conforms to the nature of
	 * an EventPin or to that of a LocationPin. The simple distinction between an EventPin and a
	 * LocationPin is that an EventPin will have a begin data and an end date. If the dataset does 
	 * not have a begin date or an end date then the method concludes that it is not an EventPin. 
	 * @param startDate The begin date. 
	 * @param endDate The end date. 
	 * @return Returns true if the data is not an EventPin. Return false if the data is an EventPin.  
	 */
    protected static boolean notAnEvent(String startDate, String endDate) {
    	return (startDate == null || endDate == null || startDate == "" || endDate == "");
    }
    
    /***
     * This method is used to select the AbstractFactory that is needed to create the PinData child.
     * @param priority The dataType of the PinData object is expressed with True(Location) and False(Event).
     * @return Returns the AbstractFactory with the toolkit to build the specified PinData child class.
     */
    public static PinFactory getFactory(String startDate, String endDate) {
        PinFactory factory = null;
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
    public abstract Pin createPinData();

}