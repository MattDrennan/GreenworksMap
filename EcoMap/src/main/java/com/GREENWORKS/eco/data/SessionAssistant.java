package com.GREENWORKS.eco.data;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.tinylog.Logger;

public class SessionAssistant { // TODO: After you get this class working make its methods generic. 
	
    private static SessionFactory sessionFactory = null;
    
    private static SessionFactory buildSessionFactory() {
        try {
        	Configuration config = new Configuration().configure();
        	config.addAnnotatedClass(Admin.class);
        	config.addAnnotatedClass(EventPin.class);
        	config.addAnnotatedClass(LocationPin.class);
        	config.addAnnotatedClass(GenericPin.class);
            StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(config.getProperties());
            return config.buildSessionFactory(builder.build());
        }
        catch (Throwable ex) {
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
    
    public static Session openSession() {
    	sessionFactory = buildSessionFactory();
    	return getSessionFactory().openSession();
    }
    
    public void shutdown() {
        // Close caches and connection pools
        getSessionFactory().close();
    }
    
	public boolean exist(Long id, Pin pin) {
		return false;
	}
	
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
    
    public void insert(Pin pin) {
    	Session session = openSession();
    	session.beginTransaction();
    	session.save(pin);
    	session.getTransaction().commit();
    	Logger.info("Pin inserted: " + pin);
    	session.close();
    }
    
	public void insert(Admin admin) {
		Session session = openSession();
	 	session.beginTransaction();
	 	session.save(admin);
	 	session.getTransaction().commit();
	 	Logger.info("Admin inserted: " + admin);
	    session.close();
	}
    public void update(Admin admin) {
    	Session session = openSession();
   	 	session.beginTransaction();
   	 	session.save(admin);
   	 	session.getTransaction().commit();
   	 	Logger.info("Admin update: " + admin);
    	session.close();
   }
    
    public void update(Pin pin) {
    	Session session = openSession();
    	session.beginTransaction();
    	session.save(pin);
    	session.getTransaction().commit();
    	Logger.info("Pin update: " + pin);
    	session.close();
    }    

   public void delete(Pin pin) {
	   Session session = openSession();
	   session.beginTransaction();
	   session.delete(pin);
	   session.getTransaction().commit();
	   Logger.info("Pin deleted: " + pin);
	   session.close();
   }
   
   public void delete(Admin admin) {
	   Session session = getSessionFactory().openSession();
       session.beginTransaction();
       session.delete(admin);
       session.getTransaction().commit();
       Logger.info("Admin deleted: " + admin);
       session.close();
    }
   
   public Pin get(Pin pin) {
	   Session session = getSessionFactory().openSession();
	   session.beginTransaction();
       GenericPin genericPin = session.find(GenericPin.class, pin.getId());
       Logger.info("Returned pin: " + genericPin);
       session.close();
       return genericPin;
    }
   
  public Admin get(Admin admin) {
	   Session session = getSessionFactory().openSession();
	   session.beginTransaction();
       Admin adminDb = session.find(Admin.class, admin.getId());
       Logger.info("Returned pin: " + adminDb);
       session.close();
       return adminDb;
   }
   
   public Admin getByLoginCredentials(String username, String password) {
	   Session session = openSession();
	   Admin admin = (Admin) session.createQuery("FROM Admin WHERE password = :password AND username = :username").
			   					setParameter("password", password).setParameter("username", username).uniqueResult();
	   session.close();
	   return admin;
   }
   
   public Pin load(Pin pin) {
	   Session session = getSessionFactory().openSession();
	   session.beginTransaction();
       GenericPin genericPin = session.load(GenericPin.class, pin.getId());
       Logger.info("Returned pin: " + genericPin);
       session.close();
       return genericPin;
    }
   
   public Admin load(Admin admin) {
	   Session session = getSessionFactory().openSession();
	   session.beginTransaction();
       Admin adminDb = session.load(Admin.class, admin.getId());
       Logger.info("Returned pin: " + adminDb);
       session.close();
       return adminDb;
    }
   
   public static List<GenericPin> getAllPins() {
	   Session session = getSessionFactory().openSession();
       return session.createQuery("SELECT a FROM GenericPin a", GenericPin.class).getResultList();      
   }
    
}
