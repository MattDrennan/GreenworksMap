**EcoMap Project:**

**How to setup project:**

This project currently relies on three API keys. The first is [ArcGIS](https://developers.arcgis.com/documentation/mapping-apis-and-services/security/api-keys/), the second is [OpenCharge](https://community.openchargemap.org/t/api-keys-are-now-required/161), and the third is [Geocoding](https://developers.google.com/maps/documentation/geocoding/get-api-key). In addition, it is necessary to create three files: cred.js, Cred.java, DatabaseConstants.java, and hibernate.cfg.xml. The steps for creating these files are below. 

1. Create a database named “ecomap.”
1. Run “ecomap.sql” on the database.
1. Run the webapp folder on TomCat 10. WARNING: Tomcat 9 will not work. 
1. Create a file, 'cred.js', in the root directory of 'webmap' with the following:
```
var mapKey = 'MAP_KEY_HERE';
```
5. Create a "Cred.java" file in "com/GREENWORKS/eco" with the following:
```
package com.GREENWORKS.eco;

public class Cred {
    // Put all data you want hidden here
    public static final String OPENCHARGEKEY = "OPEN_CHARGE_API_KEY_HERE";
    public static final String GEOCODINGKEY = "GEOCODE_API_KEY_HERE";
}
```
6. Create a "DatabaseConstants.java" file in "com/GREENWORKS/eco/constants" with the following: 
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
7. You may need to adjust the port number in “src->main->webapp->java->com->GREENWORKS->eco->constants->DatabaseConstants.java” from 8889 to 3306. You will probably need to change the USERNAME and PASSWORD as well. DatabaseConstants.java is where the Connector recieves its properties. 

8. You will need to create a file in the “EcoMap->src->main->resources“ folder. The file must be named “hibernate.cfg.xml“. There is a readme.txt file in this same location. 
```
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE hibernate-configuration PUBLIC
		"-//Hibernate/Hibernate Configuration DTD 3.0//EN"
		"http://www.hibernate.org/dtd/hibernate-configuration-3.0.dtd">
<hibernate-configuration>
    <session-factory>
		<!-- JDBC Database Connection Settings -->
        <property name="hibernate.connection.driver_class">com.mysql.jdbc.Driver</property>
        <property name="hibernate.connection.url">jdbc:mysql://localhost:8889/ecomap</property> <!-- Adjust -->
        <property name="hibernate.connection.username">root</property> <!-- Adjust -->
        <property name="hibernate.connection.password">root</property> <!-- Adjust -->
        
        <!-- JDBC Connection Pool Settings -->
        <property name="connection.pool_size">10</property>
        
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
9. You may need to adjust the port number in “EcoMap->src->main->resources” from 8889 to 3306. You will probably need to adjust the hibernate.connection.username property, the hibernate.connection.password property, and the hibernate.connection.driver_class property. 

**ecomap.sql:**

Contains the SQL needed to run the project

**locations.csv:**

Contains address that the previous group did, possibly requested by Orlando?

**EcoMap->pom.xml**

This file has all the dependencies and project specifications for Maven.

**src->main->webapp**

This folder contains all the files for the web. This is the folder you will run to TomCat.

**src->main->webapp->index.jsp**

This file is the home page, with all the map features. 

**src->main->webapp->admin.jsp**

This file is the admin page, used by a staff member to add, edit, delete locations. 

**src->main->webapp->stylesheets**

This folder contains all the CSS needed for project.

**src->main->webapp->scripts->mapEmbed.js**

This file contains the JavaScript code for the Map on home page.

**src->main->webapp->scripts->icons**

This folder contains all the icon images for project.

**src->main->webapp->WEB-INF**

This folder contains all the class files and files needed to run project.

**src->main->webapp->java->com->GREENWORKS->eco->MysqlConnect.java**

This file handles all the MySQL connections. We are phasing out MysqlConnect in favor of Hibernate. MysqlConnect will remain in the project because it can help facilitate streamlined feature development. 

**src->main->webapp->java->com->GREENWORKS->eco->servlets**

This is the folder that houses all the Serlvets. The Servlets are how the front-end communicates with the back-end. It is highly recommended that all team members read the documentation in each of the individual Servlet. 

**src->main->webapp->java->com->GREENWORKS->eco->servlets->AddItem.java**

This Serlvet handles adding locations to the database.

**src->main->webapp->java->com->GREENWORKS->eco->servlets->EditLocation.java**

This Servlet handles the delete operation and it also loads data for the edit form. 

**src->main->webapp->java->com->GREENWORKS->eco->servlets->EditLocationSave.java**

This Servlet handles saving the edited changes. 

**src->main->webapp->java->com->GREENWORKS->eco->servlets->Logout.java**

This Servlet handles logging out a user. 

**src->main->webapp->java->com->GREENWORKS->eco->servlets->Login.java**

This Servlet handles logging in a user. 

**src->main->webapp->java->com->GREENWORKS->eco->EcoMap.java**

This file handled the main functions of the project. This was essential for project during feature development. EcoMap will remain in the project because it can help facilitate streamlined feature development. 

**src->main->webapp->java->com->GREENWORKS->eco->data**

The data package handles all database related operations. This is where Hibernate ORM is located. 
