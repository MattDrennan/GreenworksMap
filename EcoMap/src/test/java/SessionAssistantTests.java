import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.GREENWORKS.eco.data.Admin;
import com.GREENWORKS.eco.data.GenericPin;
import com.GREENWORKS.eco.data.Pin;
import com.GREENWORKS.eco.data.PinFactory;
import com.GREENWORKS.eco.data.SessionAssistant;

public class SessionAssistantTests {
	
    // These are the test ids that are generated for the Hibernate CRUD tests. 
    private static Integer pinTestId = 0;
    private static Integer adminTestId = 0;
    
    /***
     * This will insert the test data sets into the database. 
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
    }
    
    /***
     * This will delete the test data sets from the database. 
     */
    @AfterAll
    public static void sessionAssistant_deleteTestData() {
    	SessionAssistant sessionAssistant = new SessionAssistant();
    	Admin admin = new Admin();
    	admin.setId(adminTestId);
    	Pin pin = new GenericPin();
    	pin.setId(pinTestId);
    	sessionAssistant.delete(admin);
    	//sessionAssistant.delete(pin);
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
    public void sessionAssistant_shouldReturnAdminBasedOnLoginCreds() {
    	SessionAssistant sessionAssistant = new SessionAssistant();
    	Admin admin = sessionAssistant.getByLoginCredentials("Testusername9102", "Testpassword9120");
    	assertEquals("Testusername9102", admin.getUsername());
    	assertEquals("Testpassword9120", admin.getPassword());
    	assertNotNull(admin.getId());
    }
    
    /***
     * 
     */
    @Test
    public void sessionAssistant_should() {
    	SessionAssistant sessionAssistant = new SessionAssistant(); 
    	Admin admin = sessionAssistant.getByLoginCredentials("notinthedatabase29123", "notinthedatabase29123");
    	assertNull(admin);
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
