package com.GREENWORKS.eco.data;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class SessionFactoryUtility {
    
    private static SessionFactory sessionFactory;

    /***
     * This method returns the configured builder for the SessionFactory. 
     * @return returns the configured SessionFactory. 
     */
    public static SessionFactory getSessionFactory() {
        if(sessionFactory == null) {
            try {
                Configuration config = new Configuration().configure();
                config.addAnnotatedClass(Admin.class);
                config.addAnnotatedClass(EventPin.class);
                config.addAnnotatedClass(LocationPin.class);
                config.addAnnotatedClass(GenericPin.class);
                config.addAnnotatedClass(ProblemPin.class);
                config.addAnnotatedClass(OldEventPin.class);
                StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(config.getProperties());
                return config.buildSessionFactory(builder.build());
            }
            catch (Throwable ex) {
                System.err.println("Initial SessionFactory creation failed." + ex);
                throw new ExceptionInInitializerError(ex);
            }
        } else {
            return sessionFactory;
        }
    }
}
