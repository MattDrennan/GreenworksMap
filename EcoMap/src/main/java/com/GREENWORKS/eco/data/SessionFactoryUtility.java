package com.GREENWORKS.eco.data;

import java.time.LocalDate;

import com.GREENWORKS.eco.threads.OldEventCleanerThread;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.tinylog.Logger;

/***
 * The definition of the SessionFactoryUtility class. This class is used to return the configured SessionFactory. 
 */
public class SessionFactoryUtility {

    private static SessionFactory sessionFactory;
    private static Boolean firstRun = true; // This is 
    private static LocalDate date = null;
    private static Boolean eventsResolved = false;
    private static Runtime runtime = Runtime.getRuntime();
    private static int numberOfProcessors = runtime.availableProcessors();

    /***
     * This method returns the configured builder for the SessionFactory. 
     * @return returns the configured SessionFactory. 
     */
    public static SessionFactory getSessionFactory() {
        
        // The server we are testing on is single virtual, but if this software is deployed to a server with more resources this multi-threading block can be used. 
        if(date != LocalDate.now() || firstRun == true) { // This block should only run once per day. This will run when the first user of the day connects. 
            if(numberOfProcessors > 30) {
                OldEventCleanerThread oect = new OldEventCleanerThread(date);
                Thread thread = new Thread(oect);
                thread.start();
                date = LocalDate.now();
                eventsResolved = true;
            } else { // If the else block is reached it means this will be handled in a singled-threaded manner later.
                Logger.info("Host system does not have enough available cores. OldEvent pins will be handled through a single-threaded solution.");
                date = LocalDate.now(); 
                eventsResolved = false;
            }
        }

        if(sessionFactory == null) {
            try {
                Configuration config = new Configuration().configure();
                config.addAnnotatedClass(Admin.class);
                config.addAnnotatedClass(EventPin.class);
                config.addAnnotatedClass(LocationPin.class);
                config.addAnnotatedClass(GenericPin.class);
                config.addAnnotatedClass(ProblemPin.class);
                config.addAnnotatedClass(OldEventPin.class);
                config.addAnnotatedClass(SubPillar.class);
                config.addAnnotatedClass(Pillar.class);
                StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(config.getProperties());
                firstRun = false; 
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

    public static Boolean getFirstRun() {
        return firstRun;
    }

    public static void setFirstRun(Boolean firstRun) {
        SessionFactoryUtility.firstRun = firstRun;
    }

    public static LocalDate getDate() {
        return date;
    }

    public static void setDate(LocalDate date) {
        SessionFactoryUtility.date = date;
    }

    public static Boolean getEventsResolved() {
        return eventsResolved;
    }

    public static void setEventsResolved(Boolean eventsResolved) {
        SessionFactoryUtility.eventsResolved = eventsResolved;
    }

    public static int getNumberOfProcessors() {
        return numberOfProcessors;
    }

    public static void setNumberOfProcessors(int numberOfProcessors) {
        SessionFactoryUtility.numberOfProcessors = numberOfProcessors;
    }
    
}
