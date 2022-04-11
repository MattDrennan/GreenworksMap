package com.GREENWORKS.eco.data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.tinylog.Logger;

/***
 * This class is intended to assist the Servlets by providing a medium for all the backend database functions. 
 */
public class SessionAssistant { 
	
    private static SessionFactory sessionFactory;

    public void checkSessionFactoryTime() {
        if(!SessionFactoryUtility.getEventsResolved() && !SessionFactoryUtility.isTestRun()) {
            Logger.info("Single-threaded OldEvent cleaning is taking place...");
            DatabaseCleaner databaseCleaner = new DatabaseCleaner();
            databaseCleaner.removeOldEvents(LocalDate.now());
            SessionFactoryUtility.setEventsResolved(true);
            Logger.info("Single-threaded OldEvent cleaning has finished.");
        }
    }

    public void setTestRun() {
        SessionFactoryUtility.setTestRun(true);
    }
    
    /***
     * In order to instantiate the SessionFactory thi method must be called. The SessionFactory will refer to the private method
     * buildSessionFactory() to recieve all of its configurations. 
     * @return returns an opened Session. 
     */
    public static Session openSession() {
        if(sessionFactory != null){
            return sessionFactory.openSession();
        }
    	sessionFactory = SessionFactoryUtility.getSessionFactory();
        Logger.info("A new SessionAssistant has acquiring the SessionFactory.");
    	return sessionFactory.openSession();
    }
    
    /***
     * This is used to shutdown the SessionFactory. It will close the connection pools and also
     * the cache. 
     */
    public void shutdownSessionFactory() {
        Logger.info("Closing session.");
        getSessionFactory().close(); // Close caches and connection pools. 
    }
	
    /***
     * This method return the SessionFactory instance variable. It is important to
     * have an instantiated sessionFactory prior to calling this method. 
     * @return The instance variable that is a SessionFactory. 
     */
    public static SessionFactory getSessionFactory() {
        Logger.info("Accessing the SessionFactory.");
        return sessionFactory;
    }
    
    /***
     * This method is used to insert a new item into the table. 
     * @param dataSet At the time of the documentation Admin and Pin are valid object types. 
     */
	public <T> void insert(T item) {
		Session session = openSession();
	 	session.beginTransaction();
	 	session.save(item);
	 	session.getTransaction().commit();
	 	Logger.info("Item saved: " + item);
	    session.close();
	}
	
	/***
     * This method is used to insert a new item into the table. 
     * @param dataSet At the time of the documentation Admin and Pin are valid object types. 
     */
	public <T> void saveList(ArrayList<T> items) {
		Session session = openSession();
	 	session.beginTransaction();
	 	for(T item : items) {
	 		session.save(item);
	 		Logger.info("Item saved: " + item);
	 	}
	 	session.getTransaction().commit();
	    session.close();
	}

    public <T> void deleteList(ArrayList<T> arrayList) {
        Session session = openSession();
        session.beginTransaction();
        for(T t : arrayList){
            session.delete(t);
        }
        session.getTransaction().commit();
        session.close();
    }
	
	/***
	 * This is the update method. This method will update existing entries in the database. 
	 * @param admin At the time of the documentation Admin and Pin are valid object types. 
	 */
	public <T> void update(T item) {
    	Session session = openSession();
   	 	session.beginTransaction();
   	 	session.update(item);
   	 	session.getTransaction().commit();
   	 	Logger.info("Item updated: " + item);
   	 	session.close();
    }
    
    /***
     * This method will delete the Pin entry that contains an identical image to its own. 
     * @param pin Requires the Pin object that will be deleted in the database. 
     */
    public <T> void delete(T item) {
    	Session session = openSession();
    	session.beginTransaction();
    	session.delete(item);
    	session.getTransaction().commit();
    	Logger.info("Item deleted: " + item);
    	session.close();
    }
   
    /***
     * This method can be viewed as an accessor method for finding objects of type Pin that
     * exist in the database or the cache. Correlation is established by id. 
     * @param pin Requires a Pin object that has a populated id instance variable. 
     * @return Returns a populated Pin. 
     */
    public Pin get(Pin pin) {
    	Session session = openSession();
    	session.beginTransaction();
    	Pin data = session.find(pin.getClass(), pin.getId());
    	session.close();
        Logger.info("Returned pin: " + data);
    	return data;
    }
   
    /***
     * This method can be viewed as an accessor method for finding objects of type Admin that
     * exist in the database or the cache. Correlation is established by id. 
     * @param admin Requires an Admin object with a populated id instance variable. 
     * @return Returns a populated Admin. 
     */
    public Admin get(Admin admin) {
    	Session session = openSession();
    	session.beginTransaction();
    	Admin adminDb = session.find(Admin.class, admin.getId());
    	session.close();
        Logger.info("Returned admin: " + adminDb);
    	return adminDb;
    }

    /***
     * This method can be viewed as an accessor method for finding objects of type Pillar that
     * exist in the database or the cache. Correlation is established by id. 
     * @param pillar Requires a Pillar object with a populated pId instance variable. 
     * @return Returns the matching data set. 
     */
    public Pillar get(Pillar pillar) {
    	Session session = openSession();
    	session.beginTransaction();
    	Pillar data = session.find(pillar.getClass(), pillar.getPid());
    	session.close();
        Logger.info("Returned pillar: " + data);
    	return data;
    }

    /***
     * This method can be viewed as an accessor method for finding objects of type SubPillar that
     * exist in the database or the cache. Correlation is established by id. 
     * @param subPillar Requires a SubPillar object with a populated spId instance variable.
     * @return Returns the matching data set. 
     */
    public SubPillar get(SubPillar subPillar) {
    	Session session = openSession();
    	session.beginTransaction();
    	SubPillar data = session.find(subPillar.getClass(), subPillar.getSubPillarId());
    	Logger.info("Returned subPillar: " + data);
    	session.close();
    	return data;
    }
   
    /***
   	 * This method uses two Strings to return an Admin object. If the parameters can not be correlated to a database
   	 * entry then the Admin object that is returned will be null. This method is used during Admin credential verification. 
   	 * @param username The username of the Admin. 
   	 * @param password The password of the Admin. 
   	 * @return Returns the results from the database as an Admin object. 
   	 */
    public Admin getByLoginCredentials(String username, String password) {
    	Session session = openSession();
    	Admin admin = (Admin) session.createQuery("FROM Admin WHERE password = :password AND username = :username").
			   					setParameter("password", password).setParameter("username", username).uniqueResult();
    	session.close();
    	return admin;
    }
   
    /***
     * This method loads a Pin object and returns it. The load is specified by the id of the Pin
     * object that is provided as a parameter. Load should only be used when it is certain that the data 
     * provided to this method can be correlated to a database entry or in the cache. 
     * @param pin A Pin object with a populated id instance variable. 
     * @return The Pin object that has had its instance variables populated from the database. 
     */
    public Pin load(Pin pin) {
    	Session session = openSession();
    	session.beginTransaction();
    	GenericPin genericPin = session.load(GenericPin.class, pin.getId());
    	session.close();
        Logger.info("Loaded pin: " + genericPin);
    	return genericPin;
    }
   
    /***
     * This method loads an Admin object and returns it. The load is specified by the id of the Admin
     * object that is provided as a parameter. Load should only be used when it is certain that the data 
     * provided to this method can be correlated to a database entry or in the cache. 
     * @param admin An Admin object with a populated id instance variable. 
     * @return The Admin object that has had its instance variables populated from the database. 
     */
    public Admin load(Admin admin) {
    	Session session = openSession();
    	session.beginTransaction();
    	Admin adminDb = session.load(Admin.class, admin.getId());
    	session.close();
        Logger.info("Loaded admin: " + adminDb);
    	return adminDb;
    }

    /***
     * This method returns all pins that are stored in the database. It stores all the pins in a List<Pin>. 
     * @return Returns a List<Pin> of all the Pins. 
     */
    public List<Pin> getAllPinsList() {
    	Session session = openSession();
    	List<Pin> pinList = session.createQuery("SELECT p FROM GenericPin p ORDER BY p.locationName", Pin.class).getResultList();
        session.close();
        Logger.info("Retrieved List<Pin> result list.");
    	return pinList;
    }

    /***
     * This method returns all pillars that are stored in the database. It stores all the pillars in a List<Pillar>. 
     * @return Returns a List<Pillar> of all the pillars. 
     */
    public List<Pillar> getAllPillars() {
    	Session session = openSession();
    	List<Pillar> pillarList = session.createQuery("SELECT p FROM Pillar p ORDER BY p.pId", Pillar.class).getResultList();
        session.close();
        Logger.info("Retrieved List<Pillar> result list.");
    	return pillarList;
    }

    /***
     * This method returns all sub-pillars that are stored in the database. It stores all the sub-pillars in a List<SubPillar>. 
     * @return Returns a List<SubPillar> of all the sub-pillars. 
     */
    public List<SubPillar> getAllSubPillars() {
    	Session session = openSession();
    	List<SubPillar> subPillarList = session.createQuery("SELECT p FROM SubPillar p ORDER BY p.spId", SubPillar.class).getResultList();
        session.close();
        Logger.info("Retrieved List<SubPillar> result list.");
    	return subPillarList;
    }
   
    /***
     * This method returns the total number of entries in the locations table. 
     * @return The total of locations. 
     */
    public long getLocationsTableSize() {
    	Session session = openSession();
    	return (long)session.createQuery("SELECT COUNT(p) FROM GenericPin p").getSingleResult();
    }

    /***
     * This method returns the total number of entries in the admin table. 
     * @return The total of admins. 
     */
    public long getAdminsTableSize() {
    	Session session = openSession();
    	return (long)session.createQuery("SELECT COUNT(a) FROM Admin a").getSingleResult();
    }

}
