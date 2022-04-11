import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import com.GREENWORKS.eco.data.EventPin;
import com.GREENWORKS.eco.data.GenericPin;
import com.GREENWORKS.eco.data.LocationPin;
import com.GREENWORKS.eco.data.OldEventPin;
import com.GREENWORKS.eco.data.Pillar;
import com.GREENWORKS.eco.data.Pin;
import com.GREENWORKS.eco.data.PinFactory;
import com.GREENWORKS.eco.data.ProblemPin;
import com.GREENWORKS.eco.data.SubPillar;

/***
 * These are the unit tests for the data package. The classes that will be tested are
 * Pin.java, PinFactory.java, GenericPin.java, LocationPin.java, GenericPin.java, 
 * ProblemPin.java, OldEventPin.java, Admin.java, Pillar.java, SubPilar.java. If any 
 * of these tests fail then the application will not function properly. 
 * 
 * TODO: Unit tests for EventPin
 */
public class DataPackageTests {

     /***
     * Verifies that the program can detect a valid number of processors of the host system. 
     */
    @Test
	public void checkNumberofProcessors() {
        Runtime runtime = Runtime.getRuntime();
        int numberOfProcessors = runtime.availableProcessors();
        assertTrue(numberOfProcessors > 0);
	}

    /***
     * Verifying that parsing the dates, as they exist in the database does not throw an exception. 
     */
    @Test
	public void dateParse_test() {
        try {
            String date = "2022-04-07 03:00:00";
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDate test = LocalDate.parse(date, formatter);
        } catch (Exception e) {
            fail();
        }
	}

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
    	assertNull(pin.getStreet());
        assertNull(pin.getState());
        assertNull(pin.getTown());
        assertNull(pin.getZipCode());
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
     * Verifies that the Pin method getAddressAsArrayList() is functioning properly.
     */
    @Test
    public void pin_parseAddress() {
        String test = "400 W Livingston St, Orlando, FL 32801";
        Pin pin = new GenericPin();
        ArrayList<String> addressList = pin.getAddressAsArrayList(test);
        ArrayList<String> testList = new ArrayList<String>();
        testList.add("400 W Livingston St");
		testList.add("Orlando");
		testList.add("FL");
		testList.add("32801");
        for(int i = 0; i < testList.size(); i++){
            assertEquals(testList.get(i), addressList.get(i));
        }
    }

    /***
     * Verifies that the set coordinates method is functioning properly. It should split the coordinates string
     * into latitude and longitude. 
     */
    @Test
    public void pin_parseCoordinates() {
        Pin pin = new GenericPin();
        String test = "81.38656929999999,28.5485126";
        pin.setCoordinates(test);
        assertEquals("81.38656929999999", pin.getLongitude());
        assertEquals("28.5485126", pin.getLatitude());
    }

    /***
     * Verifies that upon Pillar instantiation by a default constructor results in null instance variables. 
     */
    @Test
    public void pillar_shouldBeNull() {
        Pillar pillar = new Pillar();
        assertEquals(null, pillar.getPid());
        assertEquals(null, pillar.getName());
    }

    /***
     * Verifies that the single parameter constructor is assigning the proper value to the pId instance variable. 
     */
    @Test
    public void pillar_singleParamConstructor_shouldBeEqual() {
        Pillar pillar = new Pillar(5);
        assertEquals(5, pillar.getPid());
    }

    /***
     * Verifies that the mutator and accessor methods for the pId instance variable are functioning as intended. 
     */
    @Test
    public void pillar_setGetId_shouldBeEqual() {
        Pillar pillar = new Pillar();
        pillar.setPid(5);
        assertEquals(5, pillar.getPid());
    }

    /***
     * Verfieis that the the accessors and mutators for the name instance variable are functioning as intended. 
     */
    @Test
    public void pillar_setGetName_shouldBeEqual() {
        Pillar pillar = new Pillar();
        pillar.setName("Clean Water");;
        assertEquals("Clean Water", pillar.getName());
    }

    /***
     * Verifies that instantiation of a SubPillar through the default constructor results in null instance variables. 
     */
    @Test
    public void subpillar_shouldBeNull() {
        SubPillar subPillar = new SubPillar();
        assertEquals(null, subPillar.getSubPillarId());
        assertEquals(null, subPillar.getName());
        assertEquals(null, subPillar.getThumbnail());
        assertEquals(null, subPillar.getPillar());
    }

    /***
     * Verifies that the accessor and mutator for the SubPillars spId instance variable are being assigned and accessed correctly. 
     */
    @Test
    public void subpillar_setGetspId_shouldBeEqual() {
        SubPillar subPillar = new SubPillar();
        subPillar.setSubPillarId(5);
        assertEquals(5, subPillar.getSubPillarId());
    }

    /***
     * Verifies that the accessor and mutator for the name instance variable are functioning as intended. 
     */
    @Test
    public void subpillar_setGetName_shouldBeEqual() {
        SubPillar subPillar = new SubPillar();
        subPillar.setName("Hazardous Waste Facility");
        assertEquals("Hazardous Waste Facility", subPillar.getName());
    }

    /***
     * Verifies that the accessor and mutator for the thumbnail instance variable are functioning as intended. 
     */
    @Test
    public void subpillar_setGetThumbnail_shouldBeEqual() {
        SubPillar subPillar = new SubPillar();
        subPillar.setThumbnail("thumbnail.com/test/test.png");
        assertEquals("thumbnail.com/test/test.png", subPillar.getThumbnail());
    }

    /***
     * Verifies that accessor and mutator for the Pillar instance variable of the SubPillar class are functioning as intended. 
     */
    @Test
    public void subpillar_setGetPillar_shouldBeEqual() {
        SubPillar subPillar = new SubPillar();
        subPillar.setPillar(new Pillar(5));
        assertEquals(5, subPillar.getPillar().getPid());
    }

    /***
     * Verifies that the copy method is functioning properly for a GenericPin to a ProblemPin. 
     */
    @Test
    public void copyGenericPinToProblemPin_shouldEqual() {
        Pin pin = new GenericPin();
        pin.setContent("Test content");
        pin.setId(701);
        pin.setLatitude("80");
        pin.setLongitude("80");
        Pin problemPin = new ProblemPin();
        problemPin.copyPin(pin);
        assertEquals("Test content", problemPin.getContent());
        assertEquals(701, problemPin.getId());
        assertEquals("80", problemPin.getLatitude());
        assertEquals("80", problemPin.getLongitude());
    }

    /***
     * Verifies that the copy method is functioning properly for an EventPin to an OldEventPin.
     */
    @Test
    public void copyGenericPinToEventPin_shouldEqual() {
        Pin pin = new EventPin();
        pin.setContent("Test content");
        pin.setId(701);
        pin.setLatitude("80");
        pin.setLongitude("80");
        Pin oldEventPin = new OldEventPin();
        oldEventPin.copyPin(pin);
        assertEquals("Test content", oldEventPin.getContent());
        assertEquals(701, oldEventPin.getId());
        assertEquals("80", oldEventPin.getLatitude());
        assertEquals("80", oldEventPin.getLongitude());
    }

    /***
     * Verifies that the single parameter constructor for the ProblemPin class is functioning correctly. 
     */
    @Test
    public void problemPin_zeroParamConstructor_shouldEqual() {
        ProblemPin problemPin = new ProblemPin(5);
        assertEquals(5, problemPin.getId());
    }

    /***
     * Verifies that the accessor and mutator for the EventPin are assinging the StartDate correctly. 
     */
    @Test
    public void eventPin_setGetStartDate_shouldEqual() {
        Pin eventPin = new EventPin();
        eventPin.setStartDate("2020-04-22");
        assertEquals("2020-04-22", eventPin.getStartDate());
    }

    /***
     * Verifies that the accessor and mutator for the EventPin are assinging the endDate correctly. 
     */
    @Test
    public void eventPin_setGetEndDate_shouldEqual() {
        Pin eventPin = new EventPin();
        eventPin.setEndDate("2020-04-22");
        assertEquals("2020-04-22", eventPin.getEndDate());
    }

    /***
     * Verifies that the EventPin is having the content modified if the endDate and StartDate instance
     * variables are not null. This is used in the front-end to display the event dates to users. 
     */
    @Test
    public void eventPin_setGetContent_shouldModifyAndEqual() {
        Pin eventPin = new EventPin();
        eventPin.setStartDate("2020-04-22");
        eventPin.setEndDate("2020-04-22");
        eventPin.setContent("This is the content.");
        assertEquals("This is the content.<br>Event taking place from 2020-04-22 to 2020-04-22.", eventPin.getContent());
    }

    /***
     * Verifies that assigning the content more than once will overwrite the entire content string, not 
     * just the beginning of the content string. 
     */
    @Test
    public void eventPin_setGetContentTwice_shouldModifyAndEqual() {
        Pin eventPin = new EventPin();
        eventPin.setStartDate("2020-04-22");
        eventPin.setEndDate("2020-04-22");
        eventPin.setContent("This is the content.");
        eventPin.setContent("Now this is the content.");
        assertEquals("Now this is the content.<br>Event taking place from 2020-04-22 to 2020-04-22.", eventPin.getContent());
    }

    /***
     * Verifies that the EventPin will not modify the content string if the startDate and endDate instance variables
     * are null. 
     */
    @Test
    public void eventPin_setGetContent_shouldEqual() {
        Pin eventPin = new EventPin();
        eventPin.setContent("This is the content.");
        assertEquals("This is the content.", eventPin.getContent());
    }

    /***
     * Verifies that the accessor and mutator for the LocationPin are overridden and that 
     * they prevent date assignment of a non-EventPin data object. 
     */
    @Test
    public void locationPin_setGetEndDate_shouldBeNull() {
        Pin locationPin = new LocationPin();
        locationPin.setEndDate("2020-04-22");
        assertEquals(null, locationPin.getEndDate());
    }

    /***
     * Verifies that the accessor and mutator for the LocationPin are overridden and that 
     * they prevent date assignment of a non-EventPin data object. 
     */
    @Test
    public void locationPin_setGetStartDate_shouldBeNull() {
        Pin locationPin = new LocationPin();
        locationPin.setStartDate("2020-04-22");
        assertEquals(null, locationPin.getStartDate());
    }

}
