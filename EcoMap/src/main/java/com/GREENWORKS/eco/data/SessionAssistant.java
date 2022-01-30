package com.GREENWORKS.eco.data;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.tinylog.Logger;

public class SessionAssistant {
	
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
    
    public static void shutdown() {
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
    	shutdown();
    	Logger.info("Pin inserted: " + pin);
    }
    
    public void insert(Admin admin) {
    	Session session = openSession();
   	 	session.beginTransaction();
   	 	session.save(admin);
   	 	session.getTransaction().commit();
   	 	shutdown();
   	 	Logger.info("Admin inserted: " + admin);
   }

   public void delete(Pin pin) {
	   Session session = openSession();
	   session.beginTransaction();
	   session.delete(pin);
	   session.getTransaction().commit();
	   shutdown();
	   Logger.info("Pin deleted: " + pin);
   }
   
   public void delete(Admin admin) {
	   Session session = getSessionFactory().openSession();
       session.beginTransaction();
       session.delete(admin);
       session.getTransaction().commit();
       shutdown();
       Logger.info("Admin deleted: " + admin);  	 
    }
    
}
