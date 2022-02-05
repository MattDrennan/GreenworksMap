package com.GREENWORKS.eco;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import org.tinylog.Logger;

import com.GREENWORKS.eco.constants.DatabaseConstants;

/***
 * This class houses methods that handle database connection. 
 */
public class MysqlConnect {
    // Init database variables

    // Init connection object
    private Connection connection;
    // Init properties object
    private Properties properties;

    /***
     * Database properties are assigned to the properties instance variable in this method. 
     * @return Returns the properties object with set properties. 
     */
    private Properties assignProperties()
    {
        if (properties == null)
        {
            properties = new Properties();
            properties.setProperty("user", DatabaseConstants.USERNAME);
            properties.setProperty("password", DatabaseConstants.PASSWORD);
            properties.setProperty("MaxPooledStatements", DatabaseConstants.MAX_POOL);
        }
        return properties;
    }

    /***
     * This method performs a null check on the connection object. If at the point of method
     * call the connection object is null then the connection object will have its variables
     * fulfilled. If the connection object is not null then it can be assumed that the 
     * connection object has already had its variables fulfilled. 
     * @return Returns a connection object that is not null. 
     */
    public Connection connect()
    {
        if (connection == null)
        {
            try
            {
                Class.forName(DatabaseConstants.DATABASE_DRIVER);
                connection = DriverManager.getConnection(DatabaseConstants.DATABASE_URL, assignProperties());
                Logger.info("Database connection object created.");
            }
            catch (ClassNotFoundException | SQLException e)
            {
                e.printStackTrace();
            }
        }
        return connection;
    }

    /***
     * This method handles the requirements for disconnecting from the database. Disconnecton is achieved by 
     * closing the connection and then nullifying the connection. 
     */
    public void disconnect()
    {
        if (connection != null)
        {
            try
            {
                connection.close();
                connection = null;
                Logger.info("Database connection object has been disconnected.");
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }
        }
    }

    /***
     * This is an accessor method for the connection instance variable. This should not be used 
     * outside of unit testing. 
     * @return Returns the connection object. 
     */
    public Connection getConnection() {
        return connection;
    }

    /***
     * This is an accessor method for the properties instance variable. This should not be used 
     * outside of unit testing. 
     * @return Returns the properties object. 
     */
    public Properties getProperties() {
        return properties;
    }

    /***
     * This is a mutator method for the connection instance variable. This should not be used 
     * outside of unit testing. 
     * @param connection Requires a connection to be assigned. 
     */
    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    /***
     * This is a mutator method for the properties instance variable. This should not be used 
     * outside of unit testing. 
     * @param properties Requires a properties to be assigned. 
     */
    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    
}