import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import com.GREENWORKS.eco.MysqlConnect;
import com.GREENWORKS.eco.constants.DatabaseConstants;

import org.junit.jupiter.api.Test;

/***
 * These are the unit tests for MysqlConnect.java. This test file requires valid 
 * database properties. If these tests are failing it will likely be necessary to
 * modify the DatabaseConstants.java. 
 */
public class MysqlConnectTests {

    /***
     * Upon instantiation of the MysqlConnect object the instance Connection variable 
     * should be null. 
     */
    @Test
    public void connection_shouldBeNull() {
        MysqlConnect mysqlConnect = new MysqlConnect();
        assertNull(mysqlConnect.getConnection());
    }

    /***
     * Upon instantiation of the MysqlConnect object the instance Properties variable 
     * should be null. 
     */
    @Test
    public void properties_shouldBeNull() {
        MysqlConnect mysqlConnect = new MysqlConnect();
        assertNull(mysqlConnect.getProperties());
    }

    /***
     * Verifies that calling the disconnect() method when the connection object is null 
     * does not throw an exception. 
     */
    @Test
    public void disconnect_shouldNotThrow() {
        MysqlConnect mysqlConnect = new MysqlConnect();
        assertDoesNotThrow(() -> mysqlConnect.disconnect());
    }

    /***
     * This verifies that the the DatabaseConstants.USERNAME is being assigned correctly to 
     * the mysqlConnect instance variable when the connect() method is called. 
     */
    @Test
    public void username_property_shouldEqual() {
        MysqlConnect mysqlConnect = new MysqlConnect();
        mysqlConnect.connect();
        assertEquals(DatabaseConstants.USERNAME, mysqlConnect.getProperties().getProperty("user"));
    }

    /***
     * This verifies that the the DatabaseConstants.PASSWORD is being assigned correctly to 
     * the mysqlConnect instance variable when the connect() method is called. 
     */
    @Test
    public void password_property_shouldEqual() {
        MysqlConnect mysqlConnect = new MysqlConnect();
        mysqlConnect.connect();
        assertEquals(DatabaseConstants.PASSWORD, mysqlConnect.getProperties().getProperty("password"));
    }

    /***
     * This verifies that the the DatabaseConstants.MAX_POOL is being assigned correctly to 
     * the mysqlConnect instance variable when the connect() method is called. 
     */
    @Test
    public void maxPS_property_shouldEqual() {
        MysqlConnect mysqlConnect = new MysqlConnect();
        mysqlConnect.connect();
        assertEquals(DatabaseConstants.MAX_POOL, mysqlConnect.getProperties().getProperty("MaxPooledStatements"));
    }

}
