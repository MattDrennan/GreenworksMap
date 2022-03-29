import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.LazyInitializationException;
import org.hibernate.ObjectNotFoundException;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.GREENWORKS.eco.data.Admin;
import com.GREENWORKS.eco.data.Data;
import com.GREENWORKS.eco.data.GenericPin;
import com.GREENWORKS.eco.data.Pillar;
import com.GREENWORKS.eco.data.Pin;
import com.GREENWORKS.eco.data.PinFactory;
import com.GREENWORKS.eco.data.ProblemPin;
import com.GREENWORKS.eco.data.SessionAssistant;
import com.GREENWORKS.eco.data.SubPillar;

/***
 * These are the test cases for the SessionAssistant and for componenets that have functionality that is integrated with
 * the SessionAssitant. 
 * 
 * --- HELP: My SessionAssistantTests are failing, why? ---
 * --- Common Causes ---
 * 1. Improper database credentials. Verify that the hibernate properties file has the correct credentials to your database. 
 * 2. Relic test data. Verify that the database does not contain old generated test data. 
 * 3. Max hourly questions limit reached for user. Wait an hour and increase the max questions limit for your MySQL user. 
 */
public class SessionAssistantTests {
	
    /* These are the test values that are generated for the Hibernate CRUD tests. */
    private static Integer pinTestId = 0;
    private static Integer problemPinTestId = 0;
    private static Integer adminTestId = 0;
    private static long locationDatabaseSize = 0;
    private static Integer nullPinId = 0;
    private static Integer testSubPillarId = -1; // This will not be reassigned at runtime. 
    private static Integer testPillarId = -1; // This will not be reassigned at runtime. 
    
    /***
     * This will insert the test data sets into the database. It runs before all other tests in 
     * this class will run. 
     */
    @BeforeAll
    public static void insertTestData() {
    	SessionAssistant sessionAssistant = new SessionAssistant();

        Pillar pillar = new Pillar();
        pillar.setName("TestPillar");
        pillar.setPid(testPillarId);
        
    	SubPillar subPillar = new SubPillar();
        subPillar.setName("TestSubPillar");
        subPillar.setSubPillarId(testSubPillarId);
        subPillar.setPillar(pillar);
        
        sessionAssistant.insert(pillar); // save
        sessionAssistant.insert(subPillar); // save

    	Pin pin = null;
    	Admin admin = null;
    	ProblemPin problemPin = null;

    	/* pin of type Pin object instantiation */
	    PinFactory dataFactory = PinFactory.getFactory("2022-01-31 15:00:00", "2022-01-31 15:00:00");
	    pin = dataFactory.createPinData(); // Create event
	    pin.setIconId(4); 
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

        Pin nullPin = new GenericPin();  
    	sessionAssistant.insert(nullPin);
        nullPinId = nullPin.getId();
    	
    	pinTestId = pin.getId();
    	adminTestId = admin.getId();
    	problemPinTestId = problemPin.getId();
    	locationDatabaseSize = sessionAssistant.getLocationsTableSize();
    }

    /***
     * Verifies that a nullable SubPillar is supported by the currently design. 
     */
    @Test
    public void sessionAssistant_shouldHaveNullSubPillarId() {
    	SessionAssistant sessionAssistant = new SessionAssistant();
    	Pin pin = sessionAssistant.get(new GenericPin(nullPinId)); 
        assertEquals(null, pin.getSubPillar());
    }
    
    /***
     * Verifies that if a Pin does not exist that the returned object will be null. 
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
    	} catch (ObjectNotFoundException e) {
    		// Test pass.
    	} catch (LazyInitializationException e){
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
    	} catch (ObjectNotFoundException e) {
    		// Test pass.
    	} catch (LazyInitializationException e){
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
    	List<Pin> list = sessionAssistant.getAllPinsList(); // This can be a large resultset depending on db size. 
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
     * Verifies that the accessor for the Pillar is operating properly. 
     */
    @Test
    public void sessionAssistant_shouldEqualTestPillarName() {
    	SessionAssistant sessionAssistant = new SessionAssistant();
    	Pillar pillar = sessionAssistant.get(new Pillar(testPillarId));
        assertEquals("TestPillar", pillar.getName());
        assertEquals(testPillarId, pillar.getPid());
    }

    /***
     * Verifies that the accessor for the SubPillar is operating properly.
     */
    @Test
    public void sessionAssistant_shouldEqualTestSubPillarName() {
    	SessionAssistant sessionAssistant = new SessionAssistant();
    	SubPillar subPillar = sessionAssistant.get(new SubPillar(testSubPillarId));
        assertEquals("TestSubPillar", subPillar.getName());
        assertEquals(testSubPillarId, subPillar.getSubPillarId());
        assertEquals("TestPillar", subPillar.getPillar().getName());
        assertEquals(testSubPillarId, subPillar.getPillar().getPid());
    }

    /***
     * Verifies that the accessor for a single Pin is handling SubPillar data properly. 
     */
    @Test
    public void sessionAssistant_shouldEqualSubPillarName() {
    	SessionAssistant sessionAssistant = new SessionAssistant();
    	Pin pin = sessionAssistant.get(new GenericPin(pinTestId));
        assertEquals("TestSubPillar", pin.getSubPillar().getName());
        assertEquals(testSubPillarId, pin.getSubPillar().getSubPillarId());
    }

    /***
     * Verifies that the accessor for a single Pin is handling Pillar data properly. 
     */
    @Test
    public void sessionAssistant_shouldEqualPillarName() {
    	SessionAssistant sessionAssistant = new SessionAssistant();
    	Pin pin = sessionAssistant.get(new GenericPin(pinTestId));
        assertEquals("TestPillar", pin.getSubPillar().getPillar().getName());
        assertEquals(testPillarId, pin.getSubPillar().getPillar().getPid());
    }

    /***
     * Verifies that the 
     */
    @Test
    public void sessionAssistant_shouldEqualPilarId() {
    	SessionAssistant sessionAssistant = new SessionAssistant();
    	Pin pin = sessionAssistant.get(new GenericPin(pinTestId));
        assertEquals(testPillarId, pin.getSubPillar().getPillar().getPid());
    }
    
    /***
     * 
     */
    @Test
    public void sessionAssistant_shouldEqualSubPillarId() {
    	SessionAssistant sessionAssistant = new SessionAssistant();
    	Pin pin = sessionAssistant.get(new GenericPin(pinTestId));
        assertEquals(testSubPillarId, pin.getSubPillar().getSubPillarId());
    }

    /***
     * At the time of running this test the database should have at a minimum, the single test Pillar. However,
     * during normal operation there should be the 7+ Pillars in the database. 
     */
    @Test
    public void sessionAssistant_shouldRetrieveNonEmptyArrayListOfPillar() {
    	SessionAssistant sessionAssistant = new SessionAssistant();
    	List<Pillar> list = sessionAssistant.getAllPillars();
        assertTrue(list.size() >= 1);
    }

    /***
     * At the time of running this test the database should have at a minimum, the single test Pin in the database. 
     * Therefore, it the retrieved List<Pin> should not be empty.  
     */
    @Test
    public void sessionAssistant_shouldRetrieveNonEmptyArrayListOfPins() {
    	SessionAssistant sessionAssistant = new SessionAssistant();
    	List<Pin> list = sessionAssistant.getAllPinsList();
        assertTrue(!list.isEmpty());
    }

    /***
     * At the time of running this test the database should have at a minimum, the single test SubPillar. However,
     * during normal operation there should be the 20+ SubPillars in the database. 
     */
    @Test
    public void sessionAssistant_shouldRetrieveNonEmptyArrayListOfSubPillar() {
    	SessionAssistant sessionAssistant = new SessionAssistant();
    	List<SubPillar> list = sessionAssistant.getAllSubPillars();
        assertTrue(list.size() >= 1);
    }

    /***
     * Verifies that the test Pillar data is being extracted from the database properly. 
     */
    @Test
    public void data_shouldPillarIdShouldEqualZero() {
    	Data data = new Data();
        assertEquals(testPillarId, data.getSubPillarList().get(0).getPillar().getPid());
        assertEquals("TestPillar", data.getSubPillarList().get(0).getPillar().getName());
    }

    /***
     * Verifies that the data object can use an element of the Pillar List as a key for the HashMap. 
     */
    @Test
    public void data_shouldFindPillarIdInHashMap() {
    	Data data = new Data();
        assertEquals(testPillarId, data.getPillarHashMap().get(data.getPillarList().get(0)).get(0).getPillar().getPid());
        assertEquals("TestPillar", data.getPillarHashMap().get(data.getPillarList().get(0)).get(0).getPillar().getName());
    }

    /***
     * Verifies that the data object can be used to extract collections and that those extracted collections operate properly.
     */
    @Test
    public void data_shouldExtractArrayListFromGeneratedHashMap() {
    	Data data = new Data();
        Pillar pillar = data.getPillarList().get(0); // Get pillar at position 0.
        ArrayList<SubPillar> spList = data.getPillarHashMap().get(pillar); // Get SubPillar list that has the pillar as its key. 
        // Note: In production, it won't be necessary to instantiate additional objects, however, it helps with comprehension. 
        assertEquals(testPillarId, spList.get(0).getPillar().getPid());
        assertEquals("TestPillar", spList.get(0).getPillar().getName());
        assertEquals(testSubPillarId, spList.get(0).getSubPillarId());
        assertEquals("TestSubPillar", spList.get(0).getName());
    }
    
    /***
     * This will delete the test data sets from the database. It wil run after all
     * other tests in this test class have been run.  
     */
    @AfterAll
    public static void deleteTestData() {
        
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
        sessionAssistant.delete(new GenericPin(nullPinId));

        Pillar pillar = new Pillar();
        pillar.setPid(testPillarId);
    	SubPillar subPillar = new SubPillar();
        subPillar.setSubPillarId(testSubPillarId);
        subPillar.setPillar(pillar);

        sessionAssistant.delete(subPillar);
        sessionAssistant.delete(pillar);
        
    	sessionAssistant.shutdownSessionFactory(); // Shutdown
        
    }
}
