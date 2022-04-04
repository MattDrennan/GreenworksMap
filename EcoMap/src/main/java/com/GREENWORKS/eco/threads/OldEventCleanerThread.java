package com.GREENWORKS.eco.threads;

import java.time.LocalDate;

import com.GREENWORKS.eco.data.DatabaseCleaner;
import org.tinylog.Logger;

public class OldEventCleanerThread implements Runnable {

    private LocalDate date;

    @Override
    public void run() {

        Logger.info("OldEventCleanerThread is starting.");
        /*
        * This thread will sleep for 30 seconds. The reason for this is because the SessionFactoryUtility needs time to configure. 30 seconds should be a generous 
        * amount of time because configuration usually takes less than a second to complete. 
        */
        try {
            Logger.info("OldEventCleanerThread is going into a 30 second standby.");
            Thread.sleep(30000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if(date != LocalDate.now()) { // This block should only run once per day. This will run when the first user of the day connects. 
            DatabaseCleaner databaseCleaner = new DatabaseCleaner();
            databaseCleaner.removeOldEvents(LocalDate.now());
            Logger.info("OldEventCleanerThread has finished.");
        }
    }

    public OldEventCleanerThread(LocalDate date) {
        this.date = date;
    }

}
