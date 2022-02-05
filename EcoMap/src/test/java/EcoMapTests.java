import static org.junit.jupiter.api.Assertions.assertEquals;

import com.GREENWORKS.eco.EcoMap;

import org.junit.jupiter.api.Test;

/***
 * The unit tests for the EcoMap.java class.
 */
public class EcoMapTests {

    /***
     * Verifies that the amount of slashes are doubled in the provided string. 
     */
    @Test
    public void addSlashes_shouldAddTwoSlashes1() {
        EcoMap ecoMap = new EcoMap();
        String s = "\\\\";
        assertEquals("\\\\\\\\", ecoMap.addSlashes(s));
    }

    /***
     * Verifies that the amount of slashes are doubled in the provided string. 
     */
    @Test
    public void addSlashes_shouldAddTwoSlashes2() {
        EcoMap ecoMap = new EcoMap();
        String s = "\\n";
        assertEquals("\\\\n", ecoMap.addSlashes(s));
    }

    /***
     * Verifies that the amount of slashes are doubled in the provided string. 
     */
    @Test
    public void addSlashes_shouldAddTwoSlashes3() {
        EcoMap ecoMap = new EcoMap();
        String s = "\\r";
        assertEquals("\\\\r", ecoMap.addSlashes(s));
    }

    /***
     * Verifies that the amount of slashes are doubled in the provided string. 
     */
    @Test
    public void addSlashes_shouldAddTwoSlashes4() {
        EcoMap ecoMap = new EcoMap();
        String s = "\\00";
        assertEquals("\\\\00", ecoMap.addSlashes(s));
    }

    /***
     * Verifies that the amount of slashes are doubled in the provided string. 
     */
    @Test
    public void addSlashes_shouldAddTwoSlashes5() {
        EcoMap ecoMap = new EcoMap();
        String s = "'";
        assertEquals("\\\'", ecoMap.addSlashes(s));
    }

    /***
     * Verifies that if the provided string has no slashes that it will remain
     * unmodified. 
     */
    @Test
    public void addSlashes_shouldMakeNoChanges() {
        EcoMap ecoMap = new EcoMap();
        String s = "4000 Central Florida Blvd, Orlando, FL";
        assertEquals("4000 Central Florida Blvd, Orlando, FL", ecoMap.addSlashes(s));
    }

    /***
     * Verifies that if the provided string has no slashes that it will remain
     * unmodified. 
     */
    @Test
    public void addSlashes_shouldMakeNoCha634nges() {
        EcoMap ecoMap = new EcoMap();
        String s = "\\nOrlando', \\nFL";
        assertEquals("\\\\nOrlando\\\', \\\\nFL", ecoMap.addSlashes(s));
    }

    /***
     * Verifies that all <> type tags will be removed from the provided String. 
     */
    @Test
    public void remoteTags_shouldRemoveHTMLTags1(){
        EcoMap ecoMap = new EcoMap();
        String s = "<html>test<html><p>";
        assertEquals("test", ecoMap.removeTags(s));
    }

    /***
     * Verifies that all <> type tags will be removed from the provided String. 
     */
    @Test
    public void remoteTags_shouldRemoveHTMLTags2(){
        EcoMap ecoMap = new EcoMap();
        String s = "<test>4000 Central <test>Florida </h1>Blvd, <test>Orlando, FL";
        assertEquals("4000 Central Florida Blvd, Orlando, FL", ecoMap.removeTags(s));
    }

    /***
     * Verifies that all <> type tags will be removed from the provided String. 
     */
    @Test
    public void remoteTags_shouldRemoveHTMLTags3(){
        EcoMap ecoMap = new EcoMap();
        String s = "<*>";
        assertEquals("", ecoMap.removeTags(s));
    }

    /***
     * Verifies that all <> type tags will be removed from the provided String and
     * that the backslashes have been added. 
     */
    @Test
    public void remoteTags_shouldRemoveHTMLTagsAndAddSlashes(){
        EcoMap ecoMap = new EcoMap();
        String s = "<h1>\\n <p>Orlando</p>', \\nFL</h1>";
        assertEquals("\\n Orlando', \\nFL", ecoMap.removeTags(s));
    }

}
