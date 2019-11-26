package kingdom.catalog;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Catalog {
    private Queue<Logger> logger;
    private static Catalog instance;
    private static Lock lock = new ReentrantLock();

    public Catalog() {
        logger = new LinkedList<>();
    }

    public static Catalog getInstance() {
        if (instance == null) {
            synchronized (lock) {
                if (instance == null) {
                    instance = new Catalog();
                }
            }
        }
        return instance;
    }

    public synchronized void write(String logEntry){

        if(logger==null || "".equals(logEntry)){
            return;
        }
        logger.add(new Logger(logEntry));
        System.out.println(logEntry);
    }
}
