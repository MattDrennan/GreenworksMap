import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.List;

import org.hibernate.ObjectNotFoundException;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.GREENWORKS.eco.data.Admin;
import com.GREENWORKS.eco.data.GenericPin;
import com.GREENWORKS.eco.data.Pillar;
import com.GREENWORKS.eco.data.Pin;
import com.GREENWORKS.eco.data.PinFactory;
import com.GREENWORKS.eco.data.ProblemPin;
import com.GREENWORKS.eco.data.SessionAssistant;
import com.GREENWORKS.eco.data.SubPillar;

public class SessionAssistantTests {
	
    /* These are the test values that are generated for the Hibernate CRUD tests. */
    private static Integer pinTestId = 0;
    private static Integer problemPinTestId = 0;
    private static Integer adminTestId = 0;
    private static long locationDatabaseSize = 0;
    
    /***
     * This will insert the test data sets into the database. It runs before all other tests in 
     * this class will run. 
     */
    @BeforeAll
    public static void sessionAssistant_insertTestData() {
    	SessionAssistant sessionAssistant = new SessionAssistant();

        Pillar pillar = new Pillar();
        pillar.setName("TestPillar");
        pillar.setPid(0);
    	SubPillar subPillar = new SubPillar();
        subPillar.setName("TestSubPillar");
        subPillar.setSubPillarId(0);
        subPillar.setPillar(pillar);
        
        sessionAssistant.insert(pillar); // save
        sessionAssistant.insert(subPillar); // save

    	Pin pin = null;
    	Admin admin = null;
    	ProblemPin problemPin = null;

    	/* pin of type Pin object instantiation */
	    PinFactory dataFactory = PinFactory.getFactory("2022-01-31 15:00:00", "2022-01-31 15:00:00");
	    pin = dataFactory.createPinData(); // Create event
	    pin.setIconId(4); // Because this is an event the IconId will be assigned to 9. 
	    pin.setStartDate("2022-01-31 15:00:00");
	    pin.setEndDate("2022-01-31 15:00:00");
	    pin.setStreet("Test");
	    pin.setLocationName("Test");
	    pin.setCoordinates("35,45");
	    pin.setContent("Test");

        pin.setSubPillar(subPillar);

	    sessionAssistant.insert(pin); // pin inserted. 
    	
	    /* admin of type Admin object instantiation */
	    admin = new Admin("Testusername9102", "Testpassword9120");
	    sessionAssistant.insert(admin); // admin inserted. 
	    
	    /* problemPin of type ProblemPin object instantiation */
    	problemPin = new ProblemPin();
    	problemPin.copyPin(pin);
    	sessionAssistant.insert(problemPin); // problemPin inserted. 
    	
    	pinTestId = pin.getId();
    	adminTestId = admin.getId();
    	problemPinTestId = problemPin.getId();
    	locationDatabaseSize = sessionAssistant.getLocationsTableSize();
    }
    
    /***
     * Verifies that if a Pin does not exist that the returned object will be
     * null. 
     */
    @Test
    public void sessionAssistant_pinShouldBeNull() {
    	SessionAssistant sessionAssistant = new SessionAssistant();
    	Pin pin = sessionAssistant.get(new GenericPin(999999999)); // This data point should not exist. 
    	assertNull(pin);
    }
    
    /***
     * Verifies that retrieving the admin credentials by id will return the correct content 
     * for the instance variables. In a sense, this could be viewed as a database side
     * Admin constructor. 
     */
    @Test
    public void sessionAssistant_shouldEqualAdminEntries() {
    	SessionAssistant sessionAssistant = new SessionAssistant();
    	Admin admin = sessionAssistant.get(new Admin(adminTestId));
    	assertEquals("Testusername9102", admin.getUsername());
    	assertEquals("Testpassword9120", admin.getPassword());
    }
    
    /***
     * Verifies that retrieving the pin by id will return the correct content 
     * for the instance variables. In a sense, this could be viewed as a database 
     * side Pin constructor. 
     */
    @Test
    public void sessionAssistant_pinDatesShouldEqualTestDates() {
    	SessionAssistant sessionAssistant = new SessionAssistant();
    	Pin pin = sessionAssistant.get(new GenericPin(pinTestId));
    	assertEquals("2022-01-31 15:00:00", pin.getStartDate());
    	assertEquals("2022-01-31 15:00:00", pin.getEndDate());
    }
    
    /***
     * Verifies that that updating the content instance variable to "" for the pin and
     * then executing a database update statement will change the content entry
     * in the database to "". 
     */
    @Test
    public void sessionAssistant_pinDataShouldUpdateAndBeEmpty() {
    	SessionAssistant sessionAssistant = new SessionAssistant();
    	Pin pin = sessionAssistant.get(new GenericPin(pinTestId));
    	pin.setContent("");
    	sessionAssistant.update(pin);
    	pin = null; // Set pin to null. 
    	pin = sessionAssistant.get(new GenericPin(pinTestId)); // Get pin. 
    	assertEquals("", pin.getContent());
    }
    
    /***
     * The entry with the specified id should successfully load and the address instance 
     * variable should be "Test". 
     */
    @Test
    public void sessionAssistant_pinShouldLoadPin() {
    	SessionAssistant sessionAssistant = new SessionAssistant();
    	Pin pin = sessionAssistant.load(new GenericPin(pinTestId));
    	assertEquals("Test, null, null null", pin.getLocationAddress());
    	assertEquals("2022-01-31 15:00:00", pin.getEndDate()); 
    }
    
    /***
     * The returned Admin object should contain equal instance variables to the parameters 
     * provided to the getByLoginCredentials() method. 
     */
    @Test
    public void sessionAssistant_shouldReturnAdminBasedOnLoginCreds() {
    	SessionAssistant sessionAssistant = new SessionAssistant();
    	Admin admin = sessionAssistant.getByLoginCredentials("Testusername9102", "Testpassword9120");
    	assertEquals("Testusername9102", admin.getUsername());
    	assertEquals("Testpassword9120", admin.getPassword());
    	assertNotNull(admin.getId());
    }
    
    /***
     * Verifies that loading an Admin that exists will function correctly. 
     */
    @Test
    public void sessionAssistant_shouldLoadAdmin() {
    	SessionAssistant sessionAssistant = new SessionAssistant();
    	Admin admin = sessionAssistant.load(new Admin(adminTestId));
    	assertEquals("Testusername9102", admin.getUsername());
    	assertEquals("Testpassword9120", admin.getPassword());
    	assertNotNull(admin.getId());
    }
    
    /***
     * Verifies that attempting to load a piece of non-existent data will throw an exception. 
     */
    @Test
    public void sessionAssistant_loadNonexistentPinShouldThrowException() {
    	SessionAssistant sessionAssistant = new SessionAssistant();
    	try {
    		sessionAssistant.load(new GenericPin(9999999)); // This should throw an exception. 
    		fail(); // If this line is reached then the test fails. 
    	} catch (ObjectNotFoundException onfe) {
    		// Test pass.
    	}
    }
    
    /***
     * Verifies that attempting to load a piece of non-existent data will throw an exception. 
     */
    @Test
    public void sessionAssistant_loadNonexistentAdminShouldThrowException() {
    	SessionAssistant sessionAssistant = new SessionAssistant();
    	try {
    		sessionAssistant.load(new Admin(9999999)); // This should throw an exception. 
    		fail(); // If this line is reached then the test fails. 
    	} catch (ObjectNotFoundException onfe) {
    		// Test pass.
    	}
    }
    
    /***
     * Verifies that providing non-existent admin credentials returns null. 
     */
    @Test
    public void sessionAssistant_shouldBeNull() {
    	SessionAssistant sessionAssistant = new SessionAssistant(); 
    	Admin admin = sessionAssistant.getByLoginCredentials("notinthedatabase29123", "notinthedatabase29123");
    	assertNull(admin);
    }
    
    /***
     * Verifies that creating two different objects from the same data set 
     * can be compared as being equal. 
     */
    @Test
    public void sessionAssistant_pinsShouldEqualEachOther() {
    	SessionAssistant sessionAssistant = new SessionAssistant();
    	Pin pin1 = sessionAssistant.get(new GenericPin(pinTestId));
    	Pin pin2 = sessionAssistant.get(new GenericPin(pinTestId));
    	assertTrue(pin1.equals(pin2));
    }
    
    /***
     * Verifies that creating two different objects from the same data set 
     * can be compared as being equal. 
     */
    @Test
    public void sessionAssistant_adminsShouldEqualEachOther() {
    	SessionAssistant sessionAssistant = new SessionAssistant();
    	Admin admin1 = sessionAssistant.get(new Admin(adminTestId));
    	Admin admin2 = sessionAssistant.get(new Admin(adminTestId));
    	assertTrue(admin1.equals(admin2));
    }
    
    /***
     * The size of the list should be the size of the entries in the table. 
     */
    @Test
    public void sessionAssistant_shouldBeTheCorrectTableSize() {
    	SessionAssistant sessionAssistant = new SessionAssistant();
    	List<Pin> list = sessionAssistant.getAllPins(); // This can be a large resultset depending on db size. 
    	assertEquals(locationDatabaseSize, list.size());
    }
    
    /***
     * This tests the core functionality of the ProblemPin and the copyPin() method. 
     */
    @Test
    public void sessionAssistant_createProblemPinEntry() {
    	SessionAssistant sessionAssistant = new SessionAssistant();
    	ProblemPin problemPinOne = new ProblemPin();
    	ProblemPin problemPinTwo = new ProblemPin();
    	problemPinOne = (ProblemPin) sessionAssistant.get(new ProblemPin(problemPinTestId));
    	problemPinTwo.copyPin(problemPinOne);
    	assertTrue(problemPinOne.equals(problemPinTwo)); 
    }

    /***
     * 
     */
    @Test
    public void sessionAssistant_shouldEqualTestPillarName() {
    	SessionAssistant sessionAssistant = new SessionAssistant();
    	Pillar pillar = sessionAssistant.get(new Pillar(0));
        assertEquals("TestPillar", pillar.getName());
    }

    /***
     * 
     */
    @Test
    public void sessionAssistant_shouldEqualTestSubPillarName() {
    	SessionAssistant sessionAssistant = new SessionAssistant();
    	SubPillar subPillar = sessionAssistant.get(new SubPillar(0));
        assertEquals("TestSubPillar", subPillar.getName());
    }

    /***
     * 
     */
    @Test
    public void sessionAssistant_shouldReturnPinWithSubPillar() {
    	SessionAssistant sessionAssistant = new SessionAssistant();
    	Pin pin = sessionAssistant.get(new GenericPin(pinTestId));
        assertEquals("TestSubPillar", pin.getSubPillar().getName());
    }

    /***
     * 
     */
    @Test
    public void sessionAssistant_shouldReturnPinWithPillar() {
    	SessionAssistant sessionAssistant = new SessionAssistant();
    	Pin pin = sessionAssistant.get(new GenericPin(pinTestId));
        assertEquals("TestPillar", pin.getSubPillar().getPillar().getName());
    }

    
    /***
     * This will delete the test data sets from the database. It wil run after all
     * other tests in this test class have been run.  
     */
    @AfterAll
    public static void sessionAssistant_deleteTestData() {
        
    	SessionAssistant sessionAssistant = new SessionAssistant();
    	
        Admin admin = new Admin();
    	admin.setId(adminTestId);
    	sessionAssistant.delete(admin);

        // Create Pin
    	Pin pin = new GenericPin();
    	pin.setId(pinTestId);
    	
        // Create ProblemPin
    	ProblemPin problemPin = new ProblemPin();
    	problemPin.setId(problemPinTestId);

        sessionAssistant.delete(pin); // Delete Pin
    	sessionAssistant.delete(problemPin); // Delete ProblemPin

        Pillar pillar = new Pillar();
        pillar.setPid(0);
    	SubPillar subPillar = new SubPillar();
        subPillar.setSubPillarId(0);
        subPillar.setPillar(pillar);

        sessionAssistant.delete(subPillar);
        sessionAssistant.delete(pillar);
        
    	sessionAssistant.shutdownSessionFactory(); // Shutdown
        
    }
}
