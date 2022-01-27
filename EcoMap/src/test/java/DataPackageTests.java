import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;

import com.GREENWORKS.eco.data.PinData;
import com.GREENWORKS.eco.data.PinDataAbstractFactory;

/***
 * These are the unit tests for the data package. The classes that will be tested are
 * PinData.java and PinDataAbstractFactory.java. If any of these tests fail then the 
 * application will not function properly. 
 */
public class DataPackageTests {

    /***
     * The static method .getFactory() should not throw an exception if called.
     */
	@Test
	public void createPinData_getFactoryShouldNotThrowException() {
        assertDoesNotThrow(() -> PinDataAbstractFactory.getFactory(null, null));
	}

    /***
     * The non-static createPinData() method should thrown an exception if called. 
     */
    @Test
	public void createPinData_createPinShouldNotThrowException() {
    	PinDataAbstractFactory factory = PinDataAbstractFactory.getFactory(null, null);
        assertDoesNotThrow(() -> factory.createPinData());
	}

    /***
     * Verifies that when a PinData child object does not have data assignment to non-core instance variables 
     * when it is instantiated through the PinDataAbstractFactory.
     */
	@Test
	public void createPinData_variablesThatShouldBeNull() {
    	PinDataAbstractFactory factory = PinDataAbstractFactory.getFactory(null, null);
    	PinData pinData = factory.createPinData();
    	assertNull(pinData.getId());
    	assertNull(pinData.getIconId());
    	assertNull(pinData.getLocationAddress());
    	assertNull(pinData.getLocationName());
	}

    /***
     * Verifies that when a PinData child object does have data assignment to core instance variables 
     * when it is instantiated through the PinDataAbstractFactory.
     */
	@Test
	public void createPinData_variablesThatShouldBeNotBeNull() {
    	PinDataAbstractFactory factory = PinDataAbstractFactory.getFactory(null, null);
    	PinData pinData = factory.createPinData();
    	assertNotNull(pinData.getDataType());
	}

    /***
     * Verifies that when null is provided as parameters to .getFactory() and when null is provided
     * as parameters to .createPinData() that the dataType instance parameter has "Location" assigned 
     * to it. 
     */
    @Test
    public void createPinData_shouldAssignLocationToDataType() {
    	PinDataAbstractFactory factory = PinDataAbstractFactory.getFactory(null, null);
    	PinData pinData = factory.createPinData();
    	assertEquals("Location", pinData.getDataType());
	}

    /***
     * Verifies that when null is provided as parameters to .getFactory() and when null is provided
     * as parameters to .createPinData() that the object is of type "Location". 
     */
    @Test
    public void createPinData_shouldBeALocation() {
    	PinDataAbstractFactory factory = PinDataAbstractFactory.getFactory(null, null);
    	PinData pinData = factory.createPinData();
    	assertEquals("Location", pinData.getClass().getSimpleName());
	}

    /***
     * Verifies that when null is provided as parameters to .getFactory() and when null is provided
     * as parameters to .createPinData() that the dataType instance parameter has "Event" assigned 
     * to it. 
     */
    @Test
    public void createPinData_shouldAssignEventToDataType() {
    	PinDataAbstractFactory factory = PinDataAbstractFactory.getFactory("beginTest", "endTest");
    	PinData pinData = factory.createPinData();
    	assertEquals("Event", pinData.getDataType());
	}

    /***
     * Verifies that when null is provided as parameters to .getFactory() and when null is provided
     * as parameters to .createPinData() that the object is of type "Location". 
     */
    @Test
    public void createPinData_shouldBeAnEvent() {
    	PinDataAbstractFactory factory = PinDataAbstractFactory.getFactory("beginTest", "endTest");
    	PinData pinData = factory.createPinData();
    	assertEquals("Event", pinData.getClass().getSimpleName());
	}

    /***
     * Verifies that the iconId instance variable has been correctly reassigned during the .setIconId()
     * method call. 
     */
    @Test
    public void createPinData_shouldAssignIcon1() {
    	PinDataAbstractFactory factory = PinDataAbstractFactory.getFactory("beginTest", "endTest");
    	PinData pinData = factory.createPinData();
    	pinData.setIconId("1");
    	assertEquals("9", pinData.getIconId());
	}

    /***
     * Verifies that the iconId instance variable has been correctly reassigned during the .setIconId()
     * method call. 
     */
    @Test
    public void createPinData_shouldAssignIcon2() {
    	PinDataAbstractFactory factory = PinDataAbstractFactory.getFactory("beginTest", "endTest");
    	PinData pinData = factory.createPinData();
    	pinData.setIconId("2");
    	assertEquals("8", pinData.getIconId());
	}

    /***
     * Verifies that the iconId instance variable has been correctly reassigned during the .setIconId()
     * method call. 
     */
    @Test
    public void createPinData_shouldAssignIcon3() {
    	PinDataAbstractFactory factory = PinDataAbstractFactory.getFactory("beginTest", "endTest");
    	PinData pinData = factory.createPinData();
    	pinData.setIconId("3");
    	assertEquals("13", pinData.getIconId());
	}

    /***
     * Verifies that the iconId instance variable has been correctly reassigned during the .setIconId()
     * method call. 
     */
    @Test
    public void createPinData_shouldAssignIcon4() {
    	PinDataAbstractFactory factory = PinDataAbstractFactory.getFactory("beginTest", "endTest");
    	PinData pinData = factory.createPinData();
    	pinData.setIconId("4");
    	assertEquals("9", pinData.getIconId());
	}
    
    /***
     * Verifies that the iconId instance variable has been correctly reassigned during the .setIconId()
     * method call. 
     */
    @Test
    public void createPinData_shouldAssignIcon5() {
    	PinDataAbstractFactory factory = PinDataAbstractFactory.getFactory("beginTest", "endTest");
    	PinData pinData = factory.createPinData();
    	pinData.setIconId("5");
    	assertEquals("11", pinData.getIconId());
	}

    /***
     * Verifies that the iconId instance variable has been correctly reassigned during the .setIconId()
     * method call. 
     */
    @Test
    public void createPinData_shouldAssignIcon6() {
    	PinDataAbstractFactory factory = PinDataAbstractFactory.getFactory("beginTest", "endTest");
    	PinData pinData = factory.createPinData();
    	pinData.setIconId("6");
    	assertEquals("10", pinData.getIconId());
	}

    /***
     * Verifies that the iconId instance variable has been correctly reassigned during the .setIconId()
     * method call. 
     */
    @Test
    public void createPinData_shouldAssignIcon7() {
    	PinDataAbstractFactory factory = PinDataAbstractFactory.getFactory("beginTest", "endTest");
    	PinData pinData = factory.createPinData();
    	pinData.setIconId("7");
    	assertEquals("12", pinData.getIconId());
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
    	PinDataAbstractFactory factory = PinDataAbstractFactory.getFactory(startDate, endDate);
    	PinData pinData = factory.createPinData(); // Location Object
    	pinData.setStartDate(startDate);
    	pinData.setEndDate(endDate);
    	assertEquals("'endTest'", pinData.getEndDate());
    	assertEquals("'startTest'", pinData.getStartDate());
	}

    /***
     * Verifies that that the dates have been properly assigned and the proper modification have 
     * taken place. 
     */    
    @Test
    public void createPinData_shouldAssignNullDatesToDEFAULT() {
    	PinDataAbstractFactory factory = PinDataAbstractFactory.getFactory(null, null);
    	PinData pinData = factory.createPinData(); // Event Object
    	pinData.setEndDate(null);
    	pinData.setStartDate(null);
    	assertEquals("DEFAULT", pinData.getEndDate());
    	assertEquals("DEFAULT", pinData.getStartDate());
	}

    /***
     * Verifies that when the PinDataAbstractFactory generateds a PinData that the Id is not 
     * assgined upon object instantiation. 
     */
    @Test
    public void createPinData_shouldAssignDateStrings8() {
    	PinDataAbstractFactory factory = PinDataAbstractFactory.getFactory("beginTest", "endTest");
    	PinData pinData = factory.createPinData();
    	assertNull(pinData.getId());
	}
    /***
     * Verifies that the amount of slashes are doubled in the provided string. 
     */
    @Test
    public void addSlashes_shouldAddTwoSlashes1() {
    	PinDataAbstractFactory factory = PinDataAbstractFactory.getFactory(null, null);
    	PinData pinData = factory.createPinData();
        String s = "\\\\";
        assertEquals("\\\\\\\\", pinData.addSlashes(s));
    }

    /***
     * Verifies that the amount of slashes are doubled in the provided string. 
     */
    @Test
    public void addSlashes_shouldAddTwoSlashes2() {
    	PinDataAbstractFactory factory = PinDataAbstractFactory.getFactory(null, null);
    	PinData pinData = factory.createPinData();
        String s = "\\n";
        assertEquals("\\\\n", pinData.addSlashes(s));
    }

    /***
     * Verifies that the amount of slashes are doubled in the provided string. 
     */
    @Test
    public void addSlashes_shouldAddTwoSlashes3() {
    	PinDataAbstractFactory factory = PinDataAbstractFactory.getFactory(null, null);
    	PinData pinData = factory.createPinData();
        String s = "\\r";
        assertEquals("\\\\r", pinData.addSlashes(s));
    }

    /***
     * Verifies that the amount of slashes are doubled in the provided string. 
     */
    @Test
    public void addSlashes_shouldAddTwoSlashes4() {
    	PinDataAbstractFactory factory = PinDataAbstractFactory.getFactory(null, null);
    	PinData pinData = factory.createPinData();
        String s = "\\00";
        assertEquals("\\\\00", pinData.addSlashes(s));
    }

    /***
     * Verifies that the amount of slashes are doubled in the provided string. 
     */
    @Test
    public void addSlashes_shouldAddTwoSlashes5() {
    	PinDataAbstractFactory factory = PinDataAbstractFactory.getFactory(null, null);
    	PinData pinData = factory.createPinData();
        String s = "'";
        assertEquals("\\\'", pinData.addSlashes(s));
    }

    /***
     * Verifies that if the provided string has no slashes that it will remain
     * unmodified. 
     */
    @Test
    public void addSlashes_shouldMakeNoChanges() {
    	PinDataAbstractFactory factory = PinDataAbstractFactory.getFactory(null, null);
    	PinData pinData = factory.createPinData();
        String s = "4000 Central Florida Blvd, Orlando, FL";
        assertEquals("4000 Central Florida Blvd, Orlando, FL", pinData.addSlashes(s));
    }

    /***
     * Verifies that if the provided string has no slashes that it will remain
     * unmodified. 
     */
    @Test
    public void addSlashes_shouldMakeNoCha634nges() {
    	PinDataAbstractFactory factory = PinDataAbstractFactory.getFactory(null, null);
    	PinData pinData = factory.createPinData();
        String s = "\\nOrlando', \\nFL";
        assertEquals("\\\\nOrlando\\\', \\\\nFL", pinData.addSlashes(s));
    }

    /***
     * Verifies that all <> type tags will be removed from the provided String. 
     */
    @Test
    public void remoteTags_shouldRemoveHTMLTags1(){
    	PinDataAbstractFactory factory = PinDataAbstractFactory.getFactory(null, null);
    	PinData pinData = factory.createPinData();
        String s = "<html>test<html><p>";
        assertEquals("test", pinData.removeTags(s));
    }

    /***
     * Verifies that all <> type tags will be removed from the provided String. 
     */
    @Test
    public void remoteTags_shouldRemoveHTMLTags2(){
    	PinDataAbstractFactory factory = PinDataAbstractFactory.getFactory(null, null);
    	PinData pinData = factory.createPinData();
        String s = "<test>4000 Central <test>Florida </h1>Blvd, <test>Orlando, FL";
        assertEquals("4000 Central Florida Blvd, Orlando, FL", pinData.removeTags(s));
    }

    /***
     * Verifies that all <> type tags will be removed from the provided String. 
     */
    @Test
    public void remoteTags_shouldRemoveHTMLTags3(){
    	PinDataAbstractFactory factory = PinDataAbstractFactory.getFactory(null, null);
    	PinData pinData = factory.createPinData();
        String s = "<*>";
        assertEquals("", pinData.removeTags(s));
    }

    /***
     * Verifies that all <> type tags will be removed from the provided String and
     * that the backslashes have been added. 
     */
    @Test
    public void remoteTags_shouldRemoveHTMLTagsAndAddSlashes(){
    	PinDataAbstractFactory factory = PinDataAbstractFactory.getFactory(null, null);
    	PinData pinData = factory.createPinData();
        String s = "<h1>\\n <p>Orlando</p>', \\nFL</h1>";
        assertEquals("\\n Orlando', \\nFL", pinData.removeTags(s));
    }

}
