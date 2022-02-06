import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import com.GREENWORKS.eco.data.GenericPin;
import com.GREENWORKS.eco.data.Pin;
import com.GREENWORKS.eco.data.PinFactory;

/***
 * These are the unit tests for the data package. The classes that will be tested are
 * Pin.java, PinFactory.java, GenericPin.java, LocationPin.java, GenericPin.java, and 
 * Admin.java. If any of these tests fail then the application will not function properly. 
 */
public class DataPackageTests {

    /***
     * The static method .getFactory() should not throw an exception if called.
     */
	@Test
	public void createPinData_getFactoryShouldNotThrowException() {
        assertDoesNotThrow(() -> PinFactory.getFactory(null, null));
	}

    /***
     * The non-static createPinData() method should thrown an exception if called. 
     */
    @Test
	public void createPinData_createPinShouldNotThrowException() {
    	PinFactory factory = PinFactory.getFactory(null, null);
        assertDoesNotThrow(() -> factory.createPinData());
	}

    /***
     * Verifies that when a PinData child object does not have data assignment to non-core instance variables 
     * when it is instantiated through the PinDataAbstractFactory.
     */
	@Test
	public void createPinData_variablesThatShouldBeNull() {
    	PinFactory factory = PinFactory.getFactory(null, null);
    	Pin pin = factory.createPinData();
    	assertNull(pin.getIconId());
    	assertNull(pin.getLocationAddress());
    	assertNull(pin.getLocationName());
	}
	
    /***
     * Null check for pin id. 
     */
	@Test
	public void createPinData_idShouldBeNullLocation() {
    	PinFactory factory = PinFactory.getFactory(null, null);
    	Pin pin = factory.createPinData();
    	assertNull(pin.getId());
	}
	
	/***
     * Null check for pin id. 
     */
    @Test
    public void createPinData_idShouldBeNullEvent() {
    	PinFactory factory = PinFactory.getFactory("beginTest", "endTest");
    	Pin pin = factory.createPinData();
    	assertNull(pin.getId());
	}

    /***
     * Verifies that when a PinData child object does have data assignment to core instance variables 
     * when it is instantiated through the PinDataAbstractFactory.
     */
	@Test
	public void createPinData_objectShouldNotBeNull() {
    	PinFactory factory = PinFactory.getFactory(null, null);
    	Pin pin = factory.createPinData();
    	assertNotNull(pin.getClass().getSimpleName());
	}

    /***
     * Verifies that when null is provided as parameters to .getFactory() and when null is provided
     * as parameters to .createPinData() that the object is of type "Location". 
     */
    @Test
    public void createPinData_shouldBeALocation() {
    	PinFactory factory = PinFactory.getFactory(null, null);
    	Pin pin = factory.createPinData();
    	assertEquals("LocationPin", pin.getClass().getSimpleName());
	}

    /***
     * Verifies that when null is provided as parameters to .getFactory() and when null is provided
     * as parameters to .createPinData() that the object is of type "Location". 
     */
    @Test
    public void createPinData_shouldBeAnEvent() {
    	PinFactory factory = PinFactory.getFactory("2022-01-31 15:00:00", "2022-01-31 17:00:00");
    	Pin pin = factory.createPinData();
    	assertEquals("EventPin", pin.getClass().getSimpleName());
	}
    
    /***
     * Verifies that overloaded setStartDate() method in EventPin is functioning properly. 
     */
    @Test
    public void createEventPin_startDateShouldBeEqual() {
    	PinFactory factory = PinFactory.getFactory("2022-01-31 15:00:00", "2022-01-31 17:00:00");
    	Pin pin = factory.createPinData();
    	pin.setStartDate("2022-01-31 15:00:00");  	
    	assertEquals("2022-01-31 15:00:00", pin.getStartDate());
	}

    /***
     * Verifies that overloaded setEndDate() method in EventPin is functioning properly. 
     */
    @Test
    public void createEventPin_endDateShouldBeEqual() {
    	PinFactory factory = PinFactory.getFactory("2022-01-31 15:00:00", "2022-01-31 17:00:00");
    	Pin pin = factory.createPinData();
    	pin.setEndDate("2022-01-31 17:00:00");  	
    	assertEquals("2022-01-31 17:00:00", pin.getEndDate());
	}

    /***
     * Verifies that the iconId instance variable has been correctly reassigned during the .setIconId()
     * method call. 
     */
    @Test
    public void createPinData_shouldAssignIcon1() {
    	PinFactory factory = PinFactory.getFactory("beginTest", "endTest");
    	Pin pin = factory.createPinData();
    	pin.setIconId(1);
    	assertEquals(9, pin.getIconId());
	}

    /***
     * Verifies that the iconId instance variable has been correctly reassigned during the .setIconId()
     * method call. 
     */
    @Test
    public void createPinData_shouldAssignIcon2() {
    	PinFactory factory = PinFactory.getFactory("beginTest", "endTest");
    	Pin pin = factory.createPinData();
    	pin.setIconId(2);
    	assertEquals(8, pin.getIconId());
	}

    /***
     * Verifies that the iconId instance variable has been correctly reassigned during the .setIconId()
     * method call. 
     */
    @Test
    public void createPinData_shouldAssignIcon3() {
    	PinFactory factory = PinFactory.getFactory("beginTest", "endTest");
    	Pin pin = factory.createPinData();
    	pin.setIconId(3);
    	assertEquals(13, pin.getIconId());
	}

    /***
     * Verifies that the iconId instance variable has been correctly reassigned during the .setIconId()
     * method call. 
     */
    @Test
    public void createPinData_shouldAssignIcon4() {
    	PinFactory factory = PinFactory.getFactory("beginTest", "endTest");
    	Pin pin = factory.createPinData();
    	pin.setIconId(4);
    	assertEquals(9, pin.getIconId());
	}
    
    /***
     * Verifies that the iconId instance variable has been correctly reassigned during the .setIconId()
     * method call. 
     */
    @Test
    public void createPinData_shouldAssignIcon5() {
    	PinFactory factory = PinFactory.getFactory("beginTest", "endTest");
    	Pin pin = factory.createPinData();
    	pin.setIconId(5);
    	assertEquals(11, pin.getIconId());
	}

    /***
     * Verifies that the iconId instance variable has been correctly reassigned during the .setIconId()
     * method call. 
     */
    @Test
    public void createPinData_shouldAssignIcon6() {
    	PinFactory factory = PinFactory.getFactory("beginTest", "endTest");
    	Pin pin = factory.createPinData();
    	pin.setIconId(6);
    	assertEquals(10, pin.getIconId());
	}

    /***
     * Verifies that the iconId instance variable has been correctly reassigned during the .setIconId()
     * method call. 
     */
    @Test
    public void createPinData_shouldAssignIcon7() {
    	PinFactory factory = PinFactory.getFactory("beginTest", "endTest");
    	Pin pin = factory.createPinData();
    	pin.setIconId(7);
    	assertEquals(12, pin.getIconId());
	}

    /***
     * Verifies that that the dates have been properly assigned and the proper modification have 
     * taken place. The instance variables need to be surrounded by punctuation marks. This is 
     * necessary for created a valid SQL String. 
     */    
    @Test
    public void createPinData_shouldAssignDateStrings() {
    	PinFactory factory = PinFactory.getFactory("2022-01-29 06:00", "2022-01-29 08:00");
    	Pin pin = factory.createPinData(); // Location Object
    	pin.setStartDate("2022-01-29 06:00");
    	pin.setEndDate("2022-01-29 08:00");
    	assertNotNull(pin.getStartDate());
    	assertNotNull(pin.getEndDate());
	}
    
    /***
     * This test enforces the logic that locations do not event dates. 
     */    
    @Test
    public void createLocationPin_shouldAssignDateStrings() {
    	PinFactory factory = PinFactory.getFactory(null, null);
    	Pin pin = factory.createPinData(); // Location Object
    	pin.setStartDate("2022-01-29 06:00");
    	pin.setEndDate("2022-01-29 08:00");
    	assertNull(pin.getStartDate());
    	assertNull(pin.getEndDate());
	}

    /***
     * Verifies that that the dates have been properly assigned and the proper modification have 
     * taken place. 
     */    
    @Test
    public void createPinData_shouldAssignNullDatesToDEFAULT() {
    	PinFactory factory = PinFactory.getFactory(null, null);
    	Pin pin = factory.createPinData(); // Location Object
    	pin.setEndDate(null);
    	pin.setStartDate(null);
    	assertEquals(null, pin.getEndDate());
    	assertEquals(null, pin.getStartDate());
	}
    
    /***
     * Verifies that the amount of slashes are doubled in the provided string. 
     */
    @Test
    public void addSlashes_shouldAddTwoSlashes1() {
    	PinFactory factory = PinFactory.getFactory(null, null);
    	Pin pin = factory.createPinData();
        String s = "\\\\";
        assertEquals("\\\\\\\\", pin.addSlashes(s));
    }

    /***
     * Verifies that the amount of slashes are doubled in the provided string. 
     */
    @Test
    public void addSlashes_shouldAddTwoSlashes2() {
    	PinFactory factory = PinFactory.getFactory(null, null);
    	Pin pin = factory.createPinData();
        String s = "\\n";
        assertEquals("\\\\n", pin.addSlashes(s));
    }

    /***
     * Verifies that the amount of slashes are doubled in the provided string. 
     */
    @Test
    public void addSlashes_shouldAddTwoSlashes3() {
    	PinFactory factory = PinFactory.getFactory(null, null);
    	Pin pin = factory.createPinData();
        String s = "\\r";
        assertEquals("\\\\r", pin.addSlashes(s));
    }

    /***
     * Verifies that the amount of slashes are doubled in the provided string. 
     */
    @Test
    public void addSlashes_shouldAddTwoSlashes4() {
    	PinFactory factory = PinFactory.getFactory(null, null);
    	Pin pin = factory.createPinData();
        String s = "\\00";
        assertEquals("\\\\00", pin.addSlashes(s));
    }

    /***
     * Verifies that the amount of slashes are doubled in the provided string. 
     */
    @Test
    public void addSlashes_shouldAddTwoSlashes5() {
    	PinFactory factory = PinFactory.getFactory(null, null);
    	Pin pin = factory.createPinData();
        String s = "'";
        assertEquals("\\\'", pin.addSlashes(s));
    }

    /***
     * Verifies that if the provided string has no slashes that it will remain
     * unmodified. 
     */
    @Test
    public void addSlashes_shouldMakeNoChanges() {
    	PinFactory factory = PinFactory.getFactory(null, null);
    	Pin pin = factory.createPinData();
        String s = "4000 Central Florida Blvd, Orlando, FL";
        assertEquals("4000 Central Florida Blvd, Orlando, FL", pin.addSlashes(s));
    }

    /***
     * Verifies that if the provided string has no slashes that it will remain
     * unmodified. 
     */
    @Test
    public void addSlashes_shouldMakeNoCha634nges() {
    	PinFactory factory = PinFactory.getFactory(null, null);
    	Pin pin = factory.createPinData();
        String s = "\\nOrlando', \\nFL";
        assertEquals("\\\\nOrlando\\\', \\\\nFL", pin.addSlashes(s));
    }

    /***
     * Verifies that all <> type tags will be removed from the provided String. 
     */
    @Test
    public void remoteTags_shouldRemoveHTMLTags1(){
    	PinFactory factory = PinFactory.getFactory(null, null);
    	Pin pin = factory.createPinData();
        String s = "<html>test<html><p>";
        assertEquals("test", pin.removeTags(s));
    }

    /***
     * Verifies that all <> type tags will be removed from the provided String. 
     */
    @Test
    public void remoteTags_shouldRemoveHTMLTags2(){
    	PinFactory factory = PinFactory.getFactory(null, null);
    	Pin pin = factory.createPinData();
        String s = "<test>4000 Central <test>Florida </h1>Blvd, <test>Orlando, FL";
        assertEquals("4000 Central Florida Blvd, Orlando, FL", pin.removeTags(s));
    }

    /***
     * Verifies that all <> type tags will be removed from the provided String. 
     */
    @Test
    public void remoteTags_shouldRemoveHTMLTags3(){
    	PinFactory factory = PinFactory.getFactory(null, null);
    	Pin pin = factory.createPinData();
        String s = "<*>";
        assertEquals("", pin.removeTags(s));
    }

    /***
     * Verifies that all <> type tags will be removed from the provided String and
     * that the backslashes have been added. 
     */
    @Test
    public void remoteTags_shouldRemoveHTMLTagsAndAddSlashes(){
    	PinFactory factory = PinFactory.getFactory(null, null);
    	Pin pin = factory.createPinData();
        String s = "<h1>\\n <p>Orlando</p>', \\nFL</h1>";
        assertEquals("\\n Orlando', \\nFL", pin.removeTags(s));
    }
    
    /***
     * Verifies the operation of a parameterized GenericPin constructor. 
     */
    @Test
    public void genericPin_idShouldEqual() {
    	GenericPin pin = new GenericPin(5);
    	assertEquals(5, pin.getId());
    }
    
    /***
     * Verifies the operation of .setCoordinates() and .getCoordinates(). 
     */
    @Test
    public void genericPin_coordinatesShouldEqual() {
    	GenericPin pin = new GenericPin();
    	pin.setCoordinates("91,81<html>");
    	assertEquals("91,81", pin.getCoordinates());
    }
    
    /***
     * Verifies the operation of .setContent() and .getContent(). 
     */
    @Test
    public void genericPin_contentShouldEqual() {
    	GenericPin pin = new GenericPin();
    	pin.setContent("This is a generic pin.");
    	assertEquals("This is a generic pin.", pin.getContent());
    }
    
    /***
     * The getIndexString() method is used to generate a string that will useful for displaying
     * pins on the map. 
     */
    @Test
    public void remoteTags_shouldProduceTheIndexPageString(){
    	PinFactory dataFactory = PinFactory.getFactory("2022-01-31 15:00:00", "2022-01-31 20:00:00");
    	Pin pin = dataFactory.createPinData(); // Create event
    	pin.setId(2);
    	pin.setIconId(11); // Because this is an event the IconId will be assigned to 9. 
    	pin.setStartDate("2022-01-31 15:00:00");
    	pin.setEndDate("2022-01-31 20:00:00");
    	pin.setLocationAddress("400 W Livingston St, Orlando, FL 32801");
    	pin.setLocationName("Marriot Edit");
    	pin.setCoordinates("-81.38423598435905,28.546908012845975");
    	pin.setContent("");
    	assertEquals("2,11,400 W Livingston St, Orlando, FL 32801,Marriot Edit,-81.38423598435905,28.546908012845975,2022-01-31 15:00:00,2022-01-31 20:00:00,", pin.getIndexString());
    }
   
}
