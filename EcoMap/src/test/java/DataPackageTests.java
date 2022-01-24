import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;

import com.GREENWORKS.eco.data.PinData;
import com.GREENWORKS.eco.data.PinDataAbstractFactory;

/***
 * These are the unit tests for the data package. The classes that will be tested are
 * PinData.java and PinDataAbstractFactory.java. 
 * TODO Documentation
 */
public class DataPackageTests {
	@Test
	public void createPinData_variablesThatShouldBeNull() {
    	PinDataAbstractFactory factory = PinDataAbstractFactory.getFactory(null, null);
    	PinData pinData = factory.createPinData(null, null);
    	assertNull(pinData.getId());
    	assertNull(pinData.getIconId());
    	assertNull(pinData.getLocationAddress());
    	assertNull(pinData.getLocationName());
	}
	@Test
	public void createPinData_variablesThatShouldBeNotBeNull() {
    	PinDataAbstractFactory factory = PinDataAbstractFactory.getFactory(null, null);
    	PinData pinData = factory.createPinData(null, null);
    	assertNotNull(pinData.getDataType());
    	assertNotNull(pinData.getStartDate());
    	assertNotNull(pinData.getEndDate());
	}
    @Test
    public void createPinData_shouldAssignLocationToDataType() {
    	PinDataAbstractFactory factory = PinDataAbstractFactory.getFactory(null, null);
    	PinData pinData = factory.createPinData(null, null);
    	assertEquals("Location", pinData.getDataType());
	}
    @Test
    public void createPinData_shouldAssignEventToDataType() {
    	PinDataAbstractFactory factory = PinDataAbstractFactory.getFactory("beginTest", "endTest");
    	PinData pinData = factory.createPinData("beginTest", "endTest");
    	assertEquals("Event", pinData.getDataType());
	}
    @Test
    public void createPinData_shouldAssignIcon1() {
    	PinDataAbstractFactory factory = PinDataAbstractFactory.getFactory("beginTest", "endTest");
    	PinData pinData = factory.createPinData("beginTest", "endTest");
    	pinData.setIconId("1");
    	assertEquals("9", pinData.getIconId());
	}
    @Test
    public void createPinData_shouldAssignIcon2() {
    	PinDataAbstractFactory factory = PinDataAbstractFactory.getFactory("beginTest", "endTest");
    	PinData pinData = factory.createPinData("beginTest", "endTest");
    	pinData.setIconId("2");
    	assertEquals("8", pinData.getIconId());
	}
    @Test
    public void createPinData_shouldAssignIcon3() {
    	PinDataAbstractFactory factory = PinDataAbstractFactory.getFactory("beginTest", "endTest");
    	PinData pinData = factory.createPinData("beginTest", "endTest");
    	pinData.setIconId("3");
    	assertEquals("13", pinData.getIconId());
	}
    @Test
    public void createPinData_shouldAssignIcon4() {
    	PinDataAbstractFactory factory = PinDataAbstractFactory.getFactory("beginTest", "endTest");
    	PinData pinData = factory.createPinData("beginTest", "endTest");
    	pinData.setIconId("4");
    	assertEquals("9", pinData.getIconId());
	}
    @Test
    public void createPinData_shouldAssignIcon5() {
    	PinDataAbstractFactory factory = PinDataAbstractFactory.getFactory("beginTest", "endTest");
    	PinData pinData = factory.createPinData("beginTest", "endTest");
    	pinData.setIconId("5");
    	assertEquals("11", pinData.getIconId());
	}
    @Test
    public void createPinData_shouldAssignIcon6() {
    	PinDataAbstractFactory factory = PinDataAbstractFactory.getFactory("beginTest", "endTest");
    	PinData pinData = factory.createPinData("beginTest", "endTest");
    	pinData.setIconId("6");
    	assertEquals("10", pinData.getIconId());
	}
    @Test
    public void createPinData_shouldAssignIcon7() {
    	PinDataAbstractFactory factory = PinDataAbstractFactory.getFactory("beginTest", "endTest");
    	PinData pinData = factory.createPinData("beginTest", "endTest");
    	pinData.setIconId("7");
    	assertEquals("12", pinData.getIconId());
	}
    @Test
    public void createPinData_shouldAssignDateStrings() {
    	PinDataAbstractFactory factory = PinDataAbstractFactory.getFactory("beginTest", "endTest");
    	PinData pinData = factory.createPinData("beginTest", "endTest");
    	assertEquals("endTest", pinData.getEndDate());
    	assertEquals("beginTest", pinData.getStartDate());
	}
    @Test
    public void createPinData_shouldAssignNullDatesToBlank() {
    	PinDataAbstractFactory factory = PinDataAbstractFactory.getFactory(null, null);
    	PinData pinData = factory.createPinData(null, null);
    	assertEquals("", pinData.getEndDate());
    	assertEquals("", pinData.getStartDate());
	}
    @Test
    public void createPinData_shouldAssignDateStrings8() {
    	PinDataAbstractFactory factory = PinDataAbstractFactory.getFactory("beginTest", "endTest");
    	PinData pinData = factory.createPinData("beginTest", "endTest");
    	assertNull(pinData.getId());
	}
    /***
     * Verifies that the amount of slashes are doubled in the provided string. 
     */
    @Test
    public void addSlashes_shouldAddTwoSlashes1() {
    	PinDataAbstractFactory factory = PinDataAbstractFactory.getFactory(null, null);
    	PinData pinData = factory.createPinData(null, null);
        String s = "\\\\";
        assertEquals("\\\\\\\\", pinData.addSlashes(s));
    }

    /***
     * Verifies that the amount of slashes are doubled in the provided string. 
     */
    @Test
    public void addSlashes_shouldAddTwoSlashes2() {
    	PinDataAbstractFactory factory = PinDataAbstractFactory.getFactory(null, null);
    	PinData pinData = factory.createPinData(null, null);
        String s = "\\n";
        assertEquals("\\\\n", pinData.addSlashes(s));
    }

    /***
     * Verifies that the amount of slashes are doubled in the provided string. 
     */
    @Test
    public void addSlashes_shouldAddTwoSlashes3() {
    	PinDataAbstractFactory factory = PinDataAbstractFactory.getFactory(null, null);
    	PinData pinData = factory.createPinData(null, null);
        String s = "\\r";
        assertEquals("\\\\r", pinData.addSlashes(s));
    }

    /***
     * Verifies that the amount of slashes are doubled in the provided string. 
     */
    @Test
    public void addSlashes_shouldAddTwoSlashes4() {
    	PinDataAbstractFactory factory = PinDataAbstractFactory.getFactory(null, null);
    	PinData pinData = factory.createPinData(null, null);
        String s = "\\00";
        assertEquals("\\\\00", pinData.addSlashes(s));
    }

    /***
     * Verifies that the amount of slashes are doubled in the provided string. 
     */
    @Test
    public void addSlashes_shouldAddTwoSlashes5() {
    	PinDataAbstractFactory factory = PinDataAbstractFactory.getFactory(null, null);
    	PinData pinData = factory.createPinData(null, null);
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
    	PinData pinData = factory.createPinData(null, null);
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
    	PinData pinData = factory.createPinData(null, null);
        String s = "\\nOrlando', \\nFL";
        assertEquals("\\\\nOrlando\\\', \\\\nFL", pinData.addSlashes(s));
    }

    /***
     * Verifies that all <> type tags will be removed from the provided String. 
     */
    @Test
    public void remoteTags_shouldRemoveHTMLTags1(){
    	PinDataAbstractFactory factory = PinDataAbstractFactory.getFactory(null, null);
    	PinData pinData = factory.createPinData(null, null);
        String s = "<html>test<html><p>";
        assertEquals("test", pinData.removeTags(s));
    }

    /***
     * Verifies that all <> type tags will be removed from the provided String. 
     */
    @Test
    public void remoteTags_shouldRemoveHTMLTags2(){
    	PinDataAbstractFactory factory = PinDataAbstractFactory.getFactory(null, null);
    	PinData pinData = factory.createPinData(null, null);
        String s = "<test>4000 Central <test>Florida </h1>Blvd, <test>Orlando, FL";
        assertEquals("4000 Central Florida Blvd, Orlando, FL", pinData.removeTags(s));
    }

    /***
     * Verifies that all <> type tags will be removed from the provided String. 
     */
    @Test
    public void remoteTags_shouldRemoveHTMLTags3(){
    	PinDataAbstractFactory factory = PinDataAbstractFactory.getFactory(null, null);
    	PinData pinData = factory.createPinData(null, null);
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
    	PinData pinData = factory.createPinData(null, null);
        String s = "<h1>\\n <p>Orlando</p>', \\nFL</h1>";
        assertEquals("\\n Orlando', \\nFL", pinData.removeTags(s));
    }
}
