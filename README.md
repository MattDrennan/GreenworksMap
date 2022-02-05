**EcoMap Project:**

**How to setup project:**

1. Create a database named “ecomap.”
1. Run “ecomap.sql” on the database.
1. Run the webapp folder on TomCat 10. WARNING: Tomcat 9 will not work. 
1. You may need to adjust the port number in “src->main->webapp->java->com->GREENWORKS->eco->constants->DatabaseConstants.java” from 8889 to 3306. The MySQL Connector user and password can be changed from that file as well.
1. Create a file, 'cred.js', in the root directory of 'webmap' with the following:

```
var mapKey = 'MAP_KEY_HERE';
```

1. Create a "Cred.java" file in "com/GREENWORKS/eco" with the following:

```
package com.GREENWORKS.eco;

public class Cred {
    // Put all data you want hidden here
    public static final String OPENCHARGEKEY = "OPEN_CHARGE_API_KEY_HERE";
}
```

1. Create a "hibernate.properties" file in "src/main/java" with the following:

```
hibernate.connection.driver_class = com.mysql.jdbc.Driver
hibernate.connection.url = jdbc:mysql://localhost:YOUR-PORT-NUMBER/ecomap
hibernate.connection.username = YOUR-MYSQL-USERNAME
hibernate.connection.password = YOUR-MYSQL-PASSWORD
```

Note: Alternatively, you can modify the hibernate.cfg.xml. Uncomment the JDBC block and add your specifications. 

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

This file handles all the MySQL connections. We are phasing out MysqlConnect in favor of Hibernate. MysqlConnect will remain in the project because it can help facilitate quick feature development. 

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

This file handled the main functions of the project. This was essential for project during feature development. EcoMap will remain in the project because it can help facilitate quick feature development. 

**src->main->webapp->java->com->GREENWORKS->eco->data**

The data package handles all data related operations. This is where Hibernate ORM is located. 