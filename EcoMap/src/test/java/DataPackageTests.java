import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.Test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import com.GREENWORKS.eco.data.EventPin;
import com.GREENWORKS.eco.data.GenericPin;
import com.GREENWORKS.eco.data.LocationPin;
import com.GREENWORKS.eco.data.Pin;
import com.GREENWORKS.eco.data.PinFactory;

/***
 * These are the unit tests for the data package. The classes that will be tested are
 * pin.java and PinDataAbstractFactory.java. If any of these tests fail then the 
 * application will not function properly. 
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
     * Hibernate should declare an id for the Location upon instantiation. If this test fails then 
     * there is likely a problem with Hibernate. 
     */
	@Test
	public void createPinData_idShouldNotBeNullLocation() {
    	PinFactory factory = PinFactory.getFactory(null, null);
    	Pin pin = factory.createPinData();
    	assertNotNull(pin.getId());
	}
	
    /***
     * Hibernate should declare an id for the Event upon instantiation. If this test fails then 
     * there is likely a problem with Hibernate. 
     */
    @Test
    public void createPinData_idShouldNotBeNullEvent() {
    	PinFactory factory = PinFactory.getFactory("beginTest", "endTest");
    	Pin pin = factory.createPinData();
    	assertNotNull(pin.getId());
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
     * as parameters to .createPinData() that the dataType instance parameter has "Location" assigned 
     * to it. 
     */
    @Test
    public void createPinData_shouldAssignLocationToDataType() {
    	PinFactory factory = PinFactory.getFactory(null, null);
    	Pin pin = factory.createPinData();
    	assertEquals("Location", pin.getClass().getSimpleName());
	}

    /***
     * Verifies that when null is provided as parameters to .getFactory() and when null is provided
     * as parameters to .createPinData() that the object is of type "Location". 
     */
    @Test
    public void createPinData_shouldBeALocation() {
    	PinFactory factory = PinFactory.getFactory(null, null);
    	Pin pin = factory.createPinData();
    	assertEquals("Location", pin.getClass().getSimpleName());
	}

    /***
     * Verifies that when null is provided as parameters to .getFactory() and when null is provided
     * as parameters to .createPinData() that the dataType instance parameter has "Event" assigned 
     * to it. 
     */
    @Test
    public void createPinData_shouldAssignEventToDataType() {
    	PinFactory factory = PinFactory.getFactory("beginTest", "endTest");
    	Pin pin = factory.createPinData();
    	assertEquals("Event", pin.getClass().getSimpleName());
	}

    /***
     * Verifies that when null is provided as parameters to .getFactory() and when null is provided
     * as parameters to .createPinData() that the object is of type "Location". 
     */
    @Test
    public void createPinData_shouldBeAnEvent() {
    	PinFactory factory = PinFactory.getFactory("beginTest", "endTest");
    	Pin pin = factory.createPinData();
    	assertEquals("Event", pin.getClass().getSimpleName());
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
    
    /***
     * Verifies that the database insertion and deletion for the LocationPin is
     * functioning correctly. 
     */
    @Test
    public void hibernateConfiguration_shouldAddLocationEntryToTable() {
    	PinFactory dataFactory = PinFactory.getFactory(null, null);
    	Pin pin = dataFactory.createPinData(); // Create LocationPin
    	pin.setIconId("4");
    	pin.setLocationAddress("820 Balmy Beach Dr, Apopka, FL 32703");
    	pin.setLocationName("Publix");
    	pin.setCoordinates("35,45");
    	pin.setContent("UNITTEST");
    	pin.setEndDate("");
    	pin.setStartDate("");
    	
    	try {
	    	Configuration config = new Configuration().configure();
	    	config.addAnnotatedClass(LocationPin.class);
	    	StandardServiceRegistryBuilder builder = 
	    			new StandardServiceRegistryBuilder().applySettings(config.getProperties());
	    	SessionFactory factory = config.buildSessionFactory(builder.build());
	    	Session session = factory.openSession();
	    	Transaction transaction = session.beginTransaction();
	    	session.save(pin);
	    	transaction.commit();
	    	session.close();
    	} catch (Exception e) {
    		fail("An exception was thrown in the hibernateConfiguration_shouldAddEventEntryToTable() unit test.");
    	}
    	
    	try {
	    	Configuration config = new Configuration().configure();
	    	config.addAnnotatedClass(LocationPin.class);
	    	StandardServiceRegistryBuilder builder = 
	    			new StandardServiceRegistryBuilder().applySettings(config.getProperties());
	    	SessionFactory factory = config.buildSessionFactory(builder.build());
	    	Session session = factory.openSession();
	    	Transaction transaction = session.beginTransaction();
	    	session.delete(pin);
	    	transaction.commit();
	    	session.close();
    	} catch (Exception e) {
    		fail("An exception was thrown in the hibernateConfiguration_shouldAddEventEntryToTable() unit test.");
    	}
    }
    
    /***
     * Verifies that the database insertion and deletion for the EventPin is
     * functioning correctly. 
     */    
    @Test
    public void hibernateConfiguration_shouldAddEventEntryToTable() {
    	PinFactory dataFactory = PinFactory.getFactory("2022-01-29 06:00", "2022-01-29 05:00");
    	Pin pin = dataFactory.createPinData(); // Create event
    	pin.setIconId("4");
    	pin.setStartDate("2022-01-29 06:00");
    	pin.setEndDate("2022-01-29 05:00");
    	pin.setLocationAddress("820 Balmy Beach Dr, Apopka, FL 32703");
    	pin.setLocationName("Publix");
    	pin.setCoordinates("35,45");
    	pin.setContent("UNITTEST");
    	pin.setEndDate("");
    	pin.setStartDate("");
    	
    	try {
	    	Configuration config = new Configuration().configure();
	    	config.addAnnotatedClass(EventPin.class);
	    	StandardServiceRegistryBuilder builder = 
	    			new StandardServiceRegistryBuilder().applySettings(config.getProperties());
	    	SessionFactory factory = config.buildSessionFactory(builder.build());
	    	Session session = factory.openSession();
	    	Transaction transaction = session.beginTransaction();
	    	session.save(pin);
	    	transaction.commit();
	    	session.close();
    	} catch (Exception e) {
    		fail("An exception was thrown in the hibernateConfiguration_shouldAddEventEntryToTable() unit test.");
    	}
    	
    	try {
	    	Configuration config = new Configuration().configure();
	    	config.addAnnotatedClass(EventPin.class);
	    	StandardServiceRegistryBuilder builder = 
	    			new StandardServiceRegistryBuilder().applySettings(config.getProperties());
	    	SessionFactory factory = config.buildSessionFactory(builder.build());
	    	Session session = factory.openSession();
	    	Transaction transaction = session.beginTransaction();
	    	session.delete(pin);
	    	transaction.commit();
	    	session.close();
    	} catch (Exception e) {
    		fail("An exception was thrown in the hibernateConfiguration_shouldAddEventEntryToTable() unit test.");
    	}
    }
    
    /***
     * Verifies that conducting select statement will not throw an exception. It is difficult
     * to make assertions on the returned Pin because to do so depends on the state of the 
     * database. 
     */
    @Test
    public void hibernateConfiguration_shouldNotThrowAnException() {
    	Configuration config = new Configuration().configure();
    	config.addAnnotatedClass(GenericPin.class);
    	StandardServiceRegistryBuilder builder = 
    			new StandardServiceRegistryBuilder().applySettings(config.getProperties());
    	SessionFactory factory = config.buildSessionFactory(builder.build());
    	Session session = factory.openSession();
    	Transaction transaction = session.beginTransaction();
    	@SuppressWarnings("unused") 
		Pin pin = session.get(GenericPin.class, 1);
    	transaction.commit();
    	session.close();
    }
    

}
