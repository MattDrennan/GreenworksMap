import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertNotNull;
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
 * PinData.java and PinDataAbstractFactory.java. If any of these tests fail then the 
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
    	Pin pinData = factory.createPinData();
    	assertNull(pinData.getIconId());
    	assertNull(pinData.getLocationAddress());
    	assertNull(pinData.getLocationName());
	}
	
    /***
     * Hibernate should declare an id for the Location upon instantiation. If this test fails then 
     * there is likely a problem with Hibernate. 
     */
	@Test
	public void createPinData_idShouldNotBeNullLocation() {
    	PinFactory factory = PinFactory.getFactory(null, null);
    	Pin pinData = factory.createPinData();
    	assertNotNull(pinData.getId());
	}
	
    /***
     * Hibernate should declare an id for the Event upon instantiation. If this test fails then 
     * there is likely a problem with Hibernate. 
     */
    @Test
    public void createPinData_idShouldNotBeNullEvent() {
    	PinFactory factory = PinFactory.getFactory("beginTest", "endTest");
    	Pin pinData = factory.createPinData();
    	assertNotNull(pinData.getId());
	}

    /***
     * Verifies that when a PinData child object does have data assignment to core instance variables 
     * when it is instantiated through the PinDataAbstractFactory.
     */
	@Test
	public void createPinData_variablesThatShouldBeNotBeNull() {
    	PinFactory factory = PinFactory.getFactory(null, null);
    	Pin pinData = factory.createPinData();
    	assertNotNull(pinData.getClass().getSimpleName());
	}

    /***
     * Verifies that when null is provided as parameters to .getFactory() and when null is provided
     * as parameters to .createPinData() that the dataType instance parameter has "Location" assigned 
     * to it. 
     */
    @Test
    public void createPinData_shouldAssignLocationToDataType() {
    	PinFactory factory = PinFactory.getFactory(null, null);
    	Pin pinData = factory.createPinData();
    	assertEquals("Location", pinData.getClass().getSimpleName());
	}

    /***
     * Verifies that when null is provided as parameters to .getFactory() and when null is provided
     * as parameters to .createPinData() that the object is of type "Location". 
     */
    @Test
    public void createPinData_shouldBeALocation() {
    	PinFactory factory = PinFactory.getFactory(null, null);
    	Pin pinData = factory.createPinData();
    	assertEquals("Location", pinData.getClass().getSimpleName());
	}

    /***
     * Verifies that when null is provided as parameters to .getFactory() and when null is provided
     * as parameters to .createPinData() that the dataType instance parameter has "Event" assigned 
     * to it. 
     */
    @Test
    public void createPinData_shouldAssignEventToDataType() {
    	PinFactory factory = PinFactory.getFactory("beginTest", "endTest");
    	Pin pinData = factory.createPinData();
    	assertEquals("Event", pinData.getClass().getSimpleName());
	}

    /***
     * Verifies that when null is provided as parameters to .getFactory() and when null is provided
     * as parameters to .createPinData() that the object is of type "Location". 
     */
    @Test
    public void createPinData_shouldBeAnEvent() {
    	PinFactory factory = PinFactory.getFactory("beginTest", "endTest");
    	Pin pinData = factory.createPinData();
    	assertEquals("Event", pinData.getClass().getSimpleName());
	}

    /***
     * Verifies that the iconId instance variable has been correctly reassigned during the .setIconId()
     * method call. 
     */
    @Test
    public void createPinData_shouldAssignIcon1() {
    	PinFactory factory = PinFactory.getFactory("beginTest", "endTest");
    	Pin pinData = factory.createPinData();
    	pinData.setIconId("1");
    	assertEquals("9", pinData.getIconId());
	}

    /***
     * Verifies that the iconId instance variable has been correctly reassigned during the .setIconId()
     * method call. 
     */
    @Test
    public void createPinData_shouldAssignIcon2() {
    	PinFactory factory = PinFactory.getFactory("beginTest", "endTest");
    	Pin pinData = factory.createPinData();
    	pinData.setIconId("2");
    	assertEquals("8", pinData.getIconId());
	}

    /***
     * Verifies that the iconId instance variable has been correctly reassigned during the .setIconId()
     * method call. 
     */
    @Test
    public void createPinData_shouldAssignIcon3() {
    	PinFactory factory = PinFactory.getFactory("beginTest", "endTest");
    	Pin pinData = factory.createPinData();
    	pinData.setIconId("3");
    	assertEquals("13", pinData.getIconId());
	}

    /***
     * Verifies that the iconId instance variable has been correctly reassigned during the .setIconId()
     * method call. 
     */
    @Test
    public void createPinData_shouldAssignIcon4() {
    	PinFactory factory = PinFactory.getFactory("beginTest", "endTest");
    	Pin pinData = factory.createPinData();
    	pinData.setIconId("4");
    	assertEquals("9", pinData.getIconId());
	}
    
    /***
     * Verifies that the iconId instance variable has been correctly reassigned during the .setIconId()
     * method call. 
     */
    @Test
    public void createPinData_shouldAssignIcon5() {
    	PinFactory factory = PinFactory.getFactory("beginTest", "endTest");
    	Pin pinData = factory.createPinData();
    	pinData.setIconId("5");
    	assertEquals("11", pinData.getIconId());
	}

    /***
     * Verifies that the iconId instance variable has been correctly reassigned during the .setIconId()
     * method call. 
     */
    @Test
    public void createPinData_shouldAssignIcon6() {
    	PinFactory factory = PinFactory.getFactory("beginTest", "endTest");
    	Pin pinData = factory.createPinData();
    	pinData.setIconId("6");
    	assertEquals("10", pinData.getIconId());
	}

    /***
     * Verifies that the iconId instance variable has been correctly reassigned during the .setIconId()
     * method call. 
     */
    @Test
    public void createPinData_shouldAssignIcon7() {
    	PinFactory factory = PinFactory.getFactory("beginTest", "endTest");
    	Pin pinData = factory.createPinData();
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
    	PinFactory factory = PinFactory.getFactory(startDate, endDate);
    	Pin pinData = factory.createPinData(); // Location Object
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
    	PinFactory factory = PinFactory.getFactory(null, null);
    	Pin pinData = factory.createPinData(); // Event Object
    	pinData.setEndDate(null);
    	pinData.setStartDate(null);
    	assertEquals("DEFAULT", pinData.getEndDate());
    	assertEquals("DEFAULT", pinData.getStartDate());
	}
    
    /***
     * Verifies that the amount of slashes are doubled in the provided string. 
     */
    @Test
    public void addSlashes_shouldAddTwoSlashes1() {
    	PinFactory factory = PinFactory.getFactory(null, null);
    	Pin pinData = factory.createPinData();
        String s = "\\\\";
        assertEquals("\\\\\\\\", pinData.addSlashes(s));
    }

    /***
     * Verifies that the amount of slashes are doubled in the provided string. 
     */
    @Test
    public void addSlashes_shouldAddTwoSlashes2() {
    	PinFactory factory = PinFactory.getFactory(null, null);
    	Pin pinData = factory.createPinData();
        String s = "\\n";
        assertEquals("\\\\n", pinData.addSlashes(s));
    }

    /***
     * Verifies that the amount of slashes are doubled in the provided string. 
     */
    @Test
    public void addSlashes_shouldAddTwoSlashes3() {
    	PinFactory factory = PinFactory.getFactory(null, null);
    	Pin pinData = factory.createPinData();
        String s = "\\r";
        assertEquals("\\\\r", pinData.addSlashes(s));
    }

    /***
     * Verifies that the amount of slashes are doubled in the provided string. 
     */
    @Test
    public void addSlashes_shouldAddTwoSlashes4() {
    	PinFactory factory = PinFactory.getFactory(null, null);
    	Pin pinData = factory.createPinData();
        String s = "\\00";
        assertEquals("\\\\00", pinData.addSlashes(s));
    }

    /***
     * Verifies that the amount of slashes are doubled in the provided string. 
     */
    @Test
    public void addSlashes_shouldAddTwoSlashes5() {
    	PinFactory factory = PinFactory.getFactory(null, null);
    	Pin pinData = factory.createPinData();
        String s = "'";
        assertEquals("\\\'", pinData.addSlashes(s));
    }

    /***
     * Verifies that if the provided string has no slashes that it will remain
     * unmodified. 
     */
    @Test
    public void addSlashes_shouldMakeNoChanges() {
    	PinFactory factory = PinFactory.getFactory(null, null);
    	Pin pinData = factory.createPinData();
        String s = "4000 Central Florida Blvd, Orlando, FL";
        assertEquals("4000 Central Florida Blvd, Orlando, FL", pinData.addSlashes(s));
    }

    /***
     * Verifies that if the provided string has no slashes that it will remain
     * unmodified. 
     */
    @Test
    public void addSlashes_shouldMakeNoCha634nges() {
    	PinFactory factory = PinFactory.getFactory(null, null);
    	Pin pinData = factory.createPinData();
        String s = "\\nOrlando', \\nFL";
        assertEquals("\\\\nOrlando\\\', \\\\nFL", pinData.addSlashes(s));
    }

    /***
     * Verifies that all <> type tags will be removed from the provided String. 
     */
    @Test
    public void remoteTags_shouldRemoveHTMLTags1(){
    	PinFactory factory = PinFactory.getFactory(null, null);
    	Pin pinData = factory.createPinData();
        String s = "<html>test<html><p>";
        assertEquals("test", pinData.removeTags(s));
    }

    /***
     * Verifies that all <> type tags will be removed from the provided String. 
     */
    @Test
    public void remoteTags_shouldRemoveHTMLTags2(){
    	PinFactory factory = PinFactory.getFactory(null, null);
    	Pin pinData = factory.createPinData();
        String s = "<test>4000 Central <test>Florida </h1>Blvd, <test>Orlando, FL";
        assertEquals("4000 Central Florida Blvd, Orlando, FL", pinData.removeTags(s));
    }

    /***
     * Verifies that all <> type tags will be removed from the provided String. 
     */
    @Test
    public void remoteTags_shouldRemoveHTMLTags3(){
    	PinFactory factory = PinFactory.getFactory(null, null);
    	Pin pinData = factory.createPinData();
        String s = "<*>";
        assertEquals("", pinData.removeTags(s));
    }

    /***
     * Verifies that all <> type tags will be removed from the provided String and
     * that the backslashes have been added. 
     */
    @Test
    public void remoteTags_shouldRemoveHTMLTagsAndAddSlashes(){
    	PinFactory factory = PinFactory.getFactory(null, null);
    	Pin pinData = factory.createPinData();
        String s = "<h1>\\n <p>Orlando</p>', \\nFL</h1>";
        assertEquals("\\n Orlando', \\nFL", pinData.removeTags(s));
    }
    
    
    @Test
    public void hibernateConfiguration_shouldAddLocationEntryToTable() {
    	PinFactory dataFactory = PinFactory.getFactory(null, null);
    	Pin pinData = dataFactory.createPinData(); // Create location
    	pinData.setIconId("4");
    	pinData.setLocationAddress("820 Balmy Beach Dr, Apopka, FL 32703");
    	pinData.setLocationName("Publix");
    	pinData.setCoordinates("35,45");
    	pinData.setContent("Shopping Center");
    	pinData.setEndDate("");
    	pinData.setStartDate("");
    	Configuration config = new Configuration().configure();
    	config.addAnnotatedClass(LocationPin.class);
    	StandardServiceRegistryBuilder builder = 
    			new StandardServiceRegistryBuilder().applySettings(config.getProperties());
    	SessionFactory factory = config.buildSessionFactory(builder.build());
    	Session session = factory.openSession();
    	Transaction transaction = session.beginTransaction();
    	session.save(pinData);
    	transaction.commit();
    	session.close();
    }
    
    @Test
    public void hibernateConfiguration_shouldAddEventEntryToTable() {
    	PinFactory dataFactory = PinFactory.getFactory("2022-01-29 06:00", "2022-01-29 05:00");
    	Pin pinData = dataFactory.createPinData(); // Create event
    	pinData.setIconId("4");
    	pinData.setStartDate("2022-01-29 06:00");
    	pinData.setEndDate("2022-01-29 05:00");
    	pinData.setLocationAddress("820 Balmy Beach Dr, Apopka, FL 32703");
    	pinData.setLocationName("Publix");
    	pinData.setCoordinates("35,45");
    	pinData.setContent("Shopping Center");
    	pinData.setEndDate("");
    	pinData.setStartDate("");
    	Configuration config = new Configuration().configure();
    	config.addAnnotatedClass(EventPin.class);
    	StandardServiceRegistryBuilder builder = 
    			new StandardServiceRegistryBuilder().applySettings(config.getProperties());
    	SessionFactory factory = config.buildSessionFactory(builder.build());
    	Session session = factory.openSession();
    	Transaction transaction = session.beginTransaction();
    	session.save(pinData);
    	transaction.commit();
    	session.close();
    }
    
    @Test
    public void hibernateConfiguration_should() {
    	Configuration config = new Configuration().configure();
    	config.addAnnotatedClass(GenericPin.class);
    	StandardServiceRegistryBuilder builder = 
    			new StandardServiceRegistryBuilder().applySettings(config.getProperties());
    	SessionFactory factory = config.buildSessionFactory(builder.build());
    	Session session = factory.openSession();
    	Transaction transaction = session.beginTransaction();
    	Pin pinData = session.get(GenericPin.class, 1444);
    	transaction.commit();
    	session.close();
    	System.out.println(pinData.toString());
    }
    

}
