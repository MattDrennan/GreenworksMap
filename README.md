# EcoMap Project:

# How to setup project:

This project currently relies on three API keys. The first is [ArcGIS](https://developers.arcgis.com/documentation/mapping-apis-and-services/security/api-keys/), the second is [OpenCharge](https://community.openchargemap.org/t/api-keys-are-now-required/161), and the third is [NREL](https://developer.nrel.gov/docs/transportation/alt-fuel-stations-v1/). In addition, it is necessary to create four files: cred.js, Cred.java, DatabaseConstants.java, and hibernate.cfg.xml. The steps for creating these files are below. 

1. Create a file, 'cred.js', in the root directory of 'webapp' with the following:
```
var mapKey = 'MAP_KEY_HERE';
```
2. Create a "Cred.java" file in the "com->GREENWORKS->eco->" folder with the following:
```
package com.GREENWORKS.eco;

public class Cred {
    // Put all data you want hidden here
    public static final String OPENCHARGEKEY = "OPEN_CHARGE_API_KEY_HERE";
    public static final String BASE_URL = "BASE_URL_OF_WEBSITE_HERE";
    public static final String NRELAPIKEY = "NREL_API_KEY_HERE";
}
```
3. Create a "DatabaseConstants.java" file in the "com->GREENWORKS->eco->constants->" folder with the following: 
```
package com.GREENWORKS.eco.constants;

/***
 * This class is for housing the database constants that we will use during project development. It 
 * will be necessary for this file to be modified by team members on their remote machines. Constants 
 * that will probably need to be modified are the USERNAME, PASSWORD, and DATABASE_URL. 
 * 
 * DATABASE_URL: MySQL Workbench uses port number 3306 by default. 
 * USERNAME: Change this to your database username. 
 * PASSWORD: Change this to your database password.
 */
public class DatabaseConstants {
    // Configure your connection settings. 
    public static final String DATABASE_URL = "jdbc:mysql://localhost:8889/ecomap"; 
    public static final String USERNAME = "root";
    public static final String PASSWORD = "root"; 
    
    public static final String DATABASE_DRIVER = "com.mysql.jdbc.Driver";
    public static final String MAX_POOL = "250";
}
```
4. You may need to adjust the port number in “src->main->webapp->java->com->GREENWORKS->eco->constants->DatabaseConstants.java” from 8889 to 3306. You will probably need to change the USERNAME and PASSWORD as well. DatabaseConstants.java is where the Connector recieves its properties. 

5. You will need to create a file in the “EcoMap->src->main->resources“ folder. The file must be named “hibernate.cfg.xml“. There is a readme.txt file in this same location. 
```
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE hibernate-configuration PUBLIC
		"-//Hibernate/Hibernate Configuration DTD 3.0//EN"
		"http://www.hibernate.org/dtd/hibernate-configuration-3.0.dtd">
<hibernate-configuration>
    <session-factory>
		<!-- JDBC Database Connection Settings -->
        <property name="hibernate.connection.driver_class">com.mysql.jdbc.Driver</property>
        <property name="hibernate.connection.url">jdbc:mysql://localhost:8889/ecomap?createDatabaseIfNotExist=true</property> <!-- Adjust -->
        <property name="hibernate.connection.username">root</property> <!-- Adjust -->
        <property name="hibernate.connection.password">root</property> <!-- Adjust -->
        
        <!-- JDBC Connection Pool Settings -->
        <property name="connection.pool_size">10</property>
        <property name="maxIdle">2</property>
		<property name="hibernate.enable_lazy_load_no_trans">true</property>
		
        <!-- Select SQL Dialect -->
    	<property name="hibernate.dialect">org.hibernate.dialect.MySQL57Dialect</property>
        
        <!-- Session Thread Safe -->
        <property name="current_session_context_class">thread</property>
        
    	<!-- Echo the SQL to sysout -->
		<property name="show_sql">true</property>
		
		<!-- Set Current Session Content -->
		<property name="hibernate.current_session_context_class">thread</property>
		
		<!-- Set Responsibilities -->
		<property name="hbm2ddl.auto">update</property>		   
    </session-factory>
</hibernate-configuration>
```
6. You may need to adjust the port number in “EcoMap->src->main->resources” from 8889 to 3306. You will probably need to adjust the hibernate.connection.username property, the hibernate.connection.password property, and the hibernate.connection.driver_class property. 
7. Run all the unit tests in the "EcoMap->src->test->java->" folder. **All of the unit tests must pass.** 
	1. Optional: Run datainsert.sql to populate database with entries.  
9. Run the webapp folder on TomCat 10. WARNING: Tomcat 9 will not work.

# Video

https://www.youtube.com/watch?v=V5kqlpHXOTQ
