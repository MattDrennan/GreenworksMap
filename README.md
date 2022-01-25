**EcoMap Project:**

**How to setup project:**

1. Create a database named “ecomap.”
1. Run “ecomap.sql” on the database.
1. Run the webapp folder on TomCat 10. WARNING: Tomcat 9 will not work. 
1. You may need to adjust the port number in “src->main->webapp->java->com->GREENWORKS->eco->MysqlConnect.java” from 8889 to 3306. The database user and password can be changed from that file as well.
1. Create a file, 'cred.js', in the root directory of 'webmap' with the following:

```
var mapKey = 'MAP_KEY_HERE';
```



**ecomap.sql:**

Contains the SQL needed to run the project

**locations.csv:**

Contains address that the previous group did, possibly requested by Orlando?

**EcoMap->pom.xml**

This file has all the dependencies and project specifications for maven

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

This file handles all the MySQL connections.

**src->main->webapp->java->com->GREENWORKS->eco->Logout.java**

This file handles logging out a user.

**src->main->webapp->java->com->GREENWORKS->eco->Login.java**

This file handles logging in a user.

**src->main->webapp->java->com->GREENWORKS->eco->EcoMap.java**

This file handles the main functions of the project.

**src->main->webapp->java->com->GREENWORKS->eco->AddItem.java**

This file handles adding locations to the database.




