import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.GREENWORKS.eco.data.Admin;
import com.GREENWORKS.eco.data.GenericPin;
import com.GREENWORKS.eco.data.Pin;
import com.GREENWORKS.eco.data.PinFactory;
import com.GREENWORKS.eco.data.SessionAssistant;

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
	public void createPinData_variablesThatShouldBeNotBeNull() {
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
    	PinFactory factory = PinFactory.getFactory("beginTest", "endTest");
    	Pin pin = factory.createPinData();
    	assertEquals("EventPin", pin.getClass().getSimpleName());
	}

    /***
     * Verifies that the iconId instance variable has been correctly reassigned during the .setIconId()
     * method call. 
     */
    @Test
    public void createPinData_shouldAssignIcon1() {
    	PinFactory factory = PinFactory.getFactory("beginTest", "endTest");
    	Pin pin = factory.createPinData();
    	pin.setIconId("1");
    	assertEquals("9", pin.getIconId());
	}

    /***
     * Verifies that the iconId instance variable has been correctly reassigned during the .setIconId()
     * method call. 
     */
    @Test
    public void createPinData_shouldAssignIcon2() {
    	PinFactory factory = PinFactory.getFactory("beginTest", "endTest");
    	Pin pin = factory.createPinData();
    	pin.setIconId("2");
    	assertEquals("8", pin.getIconId());
	}

    /***
     * Verifies that the iconId instance variable has been correctly reassigned during the .setIconId()
     * method call. 
     */
    @Test
    public void createPinData_shouldAssignIcon3() {
    	PinFactory factory = PinFactory.getFactory("beginTest", "endTest");
    	Pin pin = factory.createPinData();
    	pin.setIconId("3");
    	assertEquals("13", pin.getIconId());
	}

    /***
     * Verifies that the iconId instance variable has been correctly reassigned during the .setIconId()
     * method call. 
     */
    @Test
    public void createPinData_shouldAssignIcon4() {
    	PinFactory factory = PinFactory.getFactory("beginTest", "endTest");
    	Pin pin = factory.createPinData();
    	pin.setIconId("4");
    	assertEquals("9", pin.getIconId());
	}
    
    /***
     * Verifies that the iconId instance variable has been correctly reassigned during the .setIconId()
     * method call. 
     */
    @Test
    public void createPinData_shouldAssignIcon5() {
    	PinFactory factory = PinFactory.getFactory("beginTest", "endTest");
    	Pin pin = factory.createPinData();
    	pin.setIconId("5");
    	assertEquals("11", pin.getIconId());
	}

    /***
     * Verifies that the iconId instance variable has been correctly reassigned during the .setIconId()
     * method call. 
     */
    @Test
    public void createPinData_shouldAssignIcon6() {
    	PinFactory factory = PinFactory.getFactory("beginTest", "endTest");
    	Pin pin = factory.createPinData();
    	pin.setIconId("6");
    	assertEquals("10", pin.getIconId());
	}

    /***
     * Verifies that the iconId instance variable has been correctly reassigned during the .setIconId()
     * method call. 
     */
    @Test
    public void createPinData_shouldAssignIcon7() {
    	PinFactory factory = PinFactory.getFactory("beginTest", "endTest");
    	Pin pin = factory.createPinData();
    	pin.setIconId("7");
    	assertEquals("12", pin.getIconId());
	}

    /***
     * Verifies that that the dates have been properly assigned and the proper modification have 
     * taken place. The instance variables need to be surrounded by punctuation marks. This is 
     * necessary for created a valid SQL String. 
     */    
    @Test
    public void createPinData_shouldAssignDateStrings() {
    	String startDate = "startTest";
    	String endDate = "endTest";
    	PinFactory factory = PinFactory.getFactory(startDate, endDate);
    	Pin pin = factory.createPinData(); // Location Object
    	pin.setStartDate(startDate);
    	pin.setEndDate(endDate);
    	assertEquals("'endTest'", pin.getEndDate());
    	assertEquals("'startTest'", pin.getStartDate());
	}

    /***
     * Verifies that that the dates have been properly assigned and the proper modification have 
     * taken place. 
     */    
    @Test
    public void createPinData_shouldAssignNullDatesToDEFAULT() {
    	PinFactory factory = PinFactory.getFactory(null, null);
    	Pin pin = factory.createPinData(); // Event Object
    	pin.setEndDate(null);
    	pin.setStartDate(null);
    	assertEquals("DEFAULT", pin.getEndDate());
    	assertEquals("DEFAULT", pin.getStartDate());
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
    
    // These are the test ids that are generated for the Hibernate CRUD tests. 
    private static Integer pinTestId = 0;
    private static Integer adminTestId = 0;
    
    /***
     * This will insert the data sets into the database. 
     */
    @BeforeAll
    public static void sessionAssistant_insertTestData() {
    	PinFactory dataFactory = PinFactory.getFactory("2022-01-29 06:00", "2022-01-29 05:00");
    	Pin pin = dataFactory.createPinData(); // Create event
    	pin.setIconId("4"); // Because this is an event the IconId will be assigned to 9. 
    	pin.setStartDate("2022-01-29 06:00");
    	pin.setEndDate("2022-01-29 05:00");
    	pin.setLocationAddress("Test");
    	pin.setLocationName("Test");
    	pin.setCoordinates("35,45");
    	pin.setContent("Test");
    	Admin admin = new Admin("Testusername9102", "Testpassword9120");
    	SessionAssistant sessionAssistant = new SessionAssistant();
    	sessionAssistant.insert(pin);
    	sessionAssistant.insert(admin);
    	pinTestId = pin.getId();
    	adminTestId = admin.getId();
    }
    
    /***
     * This will delete the data sets from the database. 
     */
    @AfterAll
    public static void sessionAssistant_deleteTestData() {
    	SessionAssistant sessionAssistant = new SessionAssistant();
    	Admin admin = new Admin();
    	admin.setId(adminTestId);
    	Pin pin = new GenericPin();
    	pin.setId(pinTestId);
    	sessionAssistant.delete(admin);
    	sessionAssistant.delete(pin);
    	sessionAssistant.shutdown();
    }
    
    /***
     * 
     */
    @Test
    public void sessionAssistant_shouldEqualAdminEntries() {
    	SessionAssistant sessionAssistant = new SessionAssistant();
    	Admin admin = sessionAssistant.get(new Admin(adminTestId));
    	assertEquals("Testusername9102", admin.getUsername());
    	assertEquals("Testpassword9120", admin.getPassword());
    }
    
    /***
     * 
     */
    @Test
    public void sessionAssistant_pinsShouldEqualEachOther() {
    	SessionAssistant sessionAssistant = new SessionAssistant();
    	GenericPin pin1 = sessionAssistant.get(new GenericPin(pinTestId));
    	GenericPin pin2 = sessionAssistant.get(new GenericPin(pinTestId));
    	assertTrue(pin1.equals(pin2));
    }
    
    /***
     * 
     */
    @Test
    public void sessionAssistant_adminsShouldEqualEachOther() {
    	SessionAssistant sessionAssistant = new SessionAssistant();
    	Admin admin1 = sessionAssistant.get(new Admin(adminTestId));
    	Admin admin2 = sessionAssistant.get(new Admin(adminTestId));
    	assertTrue(admin1.equals(admin2));
    }
   
}
