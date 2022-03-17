package com.GREENWORKS.eco.data;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.tinylog.Logger;

/***
 * This class is intended to assist the Servlets by performing all the backend database functions. 
 */
public class SessionAssistant { 
	
    private static SessionFactory sessionFactory;
    
    /***
     * In order to instantiate the SessionFactory thi method must be called. The SessionFactory will refer to the private method
     * buildSessionFactory() to recieve all of its configurations. 
     * @return returns an opened Session. 
     */
    public static Session openSession() {
    	sessionFactory = SessionFactoryUtility.getSessionFactory();
    	return getSessionFactory().openSession();
    }
    
    /***
     * This is used to shutdown the SessionFactory. It will close the connection pools and also
     * the cache. 
     */
    public void shutdownSessionFactory() {
        getSessionFactory().close(); // Close caches and connection pools. 
    }
	
    /***
     * This method return the SessionFactory instance variable. It is important to
     * have an instantiated sessionFactory prior to calling this method. 
     * @return The instance variable that is a SessionFactory. 
     */
    public static SessionFactory getSessionFactory() {
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
    	Logger.info("Returned pin: " + data);
    	session.close();
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
    	Logger.info("Returned admin: " + adminDb);
    	session.close();
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
    	Logger.info("Returned pillar: " + data);
    	session.close();
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
    	Logger.info("Loaded pin: " + genericPin);
    	session.close();
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
    	Logger.info("Loaded admin: " + adminDb);
    	session.close();
    	return adminDb;
    }
   
    /***
     * This method returns all pins that are stored in the database. It stores all the Pins in an ArrayList. This
     * method is used in the front-end of the application. 
     * @return Returns an ArrayList of all the Pins. 
     */
    public ArrayList<Pin> getAllPins() {
    	Session session = openSession();
    	List<GenericPin> genericPinList = session.createQuery("SELECT p FROM GenericPin p ORDER BY p.locationName", GenericPin.class).getResultList();
    	ArrayList<Pin> pinList = new ArrayList<Pin>();
    	for(Pin pin : genericPinList) {
    		pinList.add(pin);
    	}
        session.close();
    	return pinList;
    }
    
    /***
     * This method returns all pins that are stored in the database. It stores all the Pins in a List. 
     * @return Returns a List of all the Pins. 
     */
    public List<Pin> getAllPinsList() {
    	Session session = openSession();
    	List<Pin> pinList = session.createQuery("SELECT p FROM GenericPin p", Pin.class).getResultList();
        session.close();
    	return pinList;
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
