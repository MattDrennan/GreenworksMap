import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.GREENWORKS.eco.data.Admin;
import com.GREENWORKS.eco.data.GenericPin;
import com.GREENWORKS.eco.data.Pin;
import com.GREENWORKS.eco.data.PinFactory;
import com.GREENWORKS.eco.data.SessionAssistant;

public class SessionAssistantTests {
	
    // These are the test values that are generated for the Hibernate CRUD tests. 
    private static Integer pinTestId = 0;
    private static Integer adminTestId = 0;
    private static long databaseSize = 0;
    
    /***
     * This will insert the test data sets into the database. It runs before all other tests in 
     * this class will run. 
     */
    @BeforeAll
    public static void sessionAssistant_insertTestData() {
    	PinFactory dataFactory = PinFactory.getFactory("2022-01-31 15:00:00", "2022-01-31 15:00:00");
    	Pin pin = dataFactory.createPinData(); // Create event
    	pin.setIconId(4); // Because this is an event the IconId will be assigned to 9. 
    	pin.setStartDate("2022-01-31 15:00:00");
    	pin.setEndDate("2022-01-31 15:00:00");
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
    	databaseSize = sessionAssistant.getLocationsTableSize();
    }
    
    /***
     * Verifies that if a Pin does not exist that the returned object will be
     * null. 
     */
    @Test
    public void sessionAssistant_dateShouldBeNull() {
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
    public void sessionAssistant_dateShouldBeEqual() {
    	SessionAssistant sessionAssistant = new SessionAssistant();
    	Pin pin = sessionAssistant.get(new GenericPin(pinTestId));
    	assertEquals("2022-01-31 15:00:00", pin.getStartDate());
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
    public void sessionAssistant_shouldBeTheTableSize() {
    	SessionAssistant sessionAssistant = new SessionAssistant();
    	List<Pin> list = sessionAssistant.getAllPins();
    	assertEquals(databaseSize, list.size());
    }
    
    /***
     * This will delete the test data sets from the database. It wil run after all
     * other tests in this class have been run.  
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
    
}
