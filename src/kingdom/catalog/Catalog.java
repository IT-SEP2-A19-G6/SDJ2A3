package kingdom.catalog;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Catalog {

    public enum CatalogLevel {
        NONE,
        ALL,
        KING,
        PERSON,
        TAXCOLLECTOR,
        ACCOUNTANT,
        TREASUREROOM
    }



    // Write to a volatile field will happen before any read operation
    private static volatile Catalog instance;
    private CatalogLevel catalogLevel;
    private boolean timestamp;


    // double locking
    public static Catalog getInstance() {
        if (instance == null) {
            synchronized (Catalog .class) {
                if (instance == null) {
                    instance = new Catalog();
                }
            }
        }
        return instance;
    }

    private Catalog() {
        catalogLevel = CatalogLevel.ALL;
    }

    public void setCatalogLevel(CatalogLevel level, boolean timestamp) {
        System.out.println((level == CatalogLevel.ALL || level == CatalogLevel.NONE) ? "Cataloging has been set to " + level : "Cataloging has been set to only writing information about " + level);
        catalogLevel = level;
        this.timestamp = timestamp;
    }

    public synchronized void write(Object o, String message) {
        String stamp = "";
        if (timestamp) {
            Date date = new Date();
            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss: ");
            stamp = formatter.format(date);

        }
        if (catalogLevel == CatalogLevel.ALL) {
            System.out.println(stamp + message);
        } else if (catalogLevel != CatalogLevel.NONE) {
            if (o.getClass().getSimpleName().toLowerCase().equals(catalogLevel.toString().toLowerCase())) {
                System.out.println(stamp + message);
            }
        }
    }
}
