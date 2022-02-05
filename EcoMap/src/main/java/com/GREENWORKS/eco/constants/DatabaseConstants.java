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
    public static final String HIBERNATE_URL = "hibernate.connection.url";
    public static final String HIBERNATE_USERNAME = "hibernate.connection.username";
    public static final String HIBERNATE_PASSWORD = "hibernate.connection.password";
    public static final String HIBERNATE_DIALECT = "dialect";
    public static final String DIALECT = "org.hibernate.dialect.MySQL57Dialect";
    public static final String HIBERNATE_POOL = "connection.pool_size";
    public static final String MAX_POOL = "250";
    public static final String HIBERNATE_THREAD = "current_session_context_class"; 
    public static final String THREAD = "thread";
    public static final String HIBERNATE_SHOW = "show_sql";
    public static final String TRUE = "true";
    public static final String HBM = "hbm2ddl.auto";
    public static final String UPDATE = "update";
	// test
}