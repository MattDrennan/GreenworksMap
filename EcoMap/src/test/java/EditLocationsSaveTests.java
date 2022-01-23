import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.GREENWORKS.eco.EditLocationSave;

/***
 * Unit tests for EditLocationsSave.java. 
 */
public class EditLocationsSaveTests {
	
	/***
	 * This should return True because if the data does not contain assigned
	 * variables for the dates provided then it is not an event. An event 
	 * must have dates. notAnEvent is True. 
	 */
    @Test
    public void notAnEvent_shouldReturnTrue1() {
        EditLocationSave editLocationSave = new EditLocationSave();
        assertTrue(editLocationSave.notAnEvent(null, null));
    }
    
	/***
	 * This should return True because if the data does not contain assigned
	 * variables for the dates provided then it is not an event. An event 
	 * must have dates. notAnEvent is True. 
	 */
    @Test
    public void notAnEvent_shouldReturnTrue2() {
    	EditLocationSave editLocationSave = new EditLocationSave();
        assertTrue(editLocationSave.notAnEvent("", "test"));
    }
 
	/***
	 * This should return False because if the data does contain assigned
	 * variables then it could be a date. This test is valuable for understanding
	 * how this data check operates. It doesn't actually check if the dates
	 * are dates. It merely checks that data exists. If the input parameters contain
	 * data then the method returns false. 
	 */
    @Test
    public void notAnEvent_shouldReturnFalse() {
        EditLocationSave editLocationSave = new EditLocationSave();
        assertFalse(editLocationSave.notAnEvent("test", "test"));
    }
    
    /***
     * The method assignEventIconId() should return "9" when "1" or
     * "4" are provided as a parameter. 
     */
    @Test
    public void assignEventIconId_shouldReturn9() {
        EditLocationSave editLocationSave = new EditLocationSave();
        assertEquals("9" ,editLocationSave.assignEventIconId("1"));
        assertEquals("9" ,editLocationSave.assignEventIconId("4"));
    }
    
    /***
     * The method assignEventIconId() should return "8" when "2" is 
     * provided as a parameter. 
     */
    @Test
    public void assignEventIconId_shouldReturn8() {
        EditLocationSave editLocationSave = new EditLocationSave();
        assertEquals("8" ,editLocationSave.assignEventIconId("2"));
    }
    
    /***
     * The method assignEventIconId() should return "13" when "3" is 
     * provided as a parameter. 
     */
    @Test
    public void assignEventIconId_shouldReturn13() {
        EditLocationSave editLocationSave = new EditLocationSave();
        assertEquals("13" ,editLocationSave.assignEventIconId("3"));
    }
    
    /***
     * The method assignEventIconId() should return "11" when "5" is 
     * provided as a parameter. 
     */
    @Test
    public void assignEventIconId_shouldReturn11() {
        EditLocationSave editLocationSave = new EditLocationSave();
        assertEquals("11" ,editLocationSave.assignEventIconId("5"));
    }
    
    /***
     * The method assignEventIconId() should return "10" when "6" is 
     * provided as a parameter. 
     */
    @Test
    public void assignEventIconId_shouldReturn10() {
        EditLocationSave editLocationSave = new EditLocationSave();
        assertEquals("10" ,editLocationSave.assignEventIconId("6"));
    }
    
    /***
     * The method assignEventIconId() should return "12" when "7" is 
     * provided as a parameter. 
     */
    @Test
    public void assignEventIconId_shouldReturn12() {
        EditLocationSave editLocationSave = new EditLocationSave();
        assertEquals("12" ,editLocationSave.assignEventIconId("7"));
    }    
    
}
