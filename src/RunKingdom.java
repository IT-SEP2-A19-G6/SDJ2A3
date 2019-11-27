
import kingdom.accountant.Accountant;
import kingdom.catalog.Catalog;
import kingdom.commoners.CommonerType;
import kingdom.commoners.Person;
import kingdom.king.King;
import kingdom.taxcollector.TaxCollector;
import kingdom.treasueroom.TreasureRoom;

import java.util.ArrayList;
import java.util.Random;


public class RunKingdom {
    public static void main(String[] args) {
        // create the kingdom
        TreasureRoom treasureRoom = new TreasureRoom();
        // Decide what should be known about the kingdom
        Catalog.getInstance().setCatalogLevel(Catalog.CatalogLevel.ALL, true);

        // Place a king on the throne
        King king = new King(treasureRoom);
        new Thread(king).start();
        // set kingdom size in km
        int kingdomSize = 100;
        // create commoners to work for kingdom.valuables
        ArrayList<Person> commoners = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            Person p = new Person(CommonerType.values()[new Random().nextInt(9)], kingdomSize);
            commoners.add(p);
        }
        // Create the tax collector and start collecting
        for (int i = 0; i < 10; i++) {
            TaxCollector taxCollector = new TaxCollector(treasureRoom, 100);
            taxCollector.addCommoner(commoners);
            new Thread(taxCollector).start();
        }
        // When we have an Tax collector, we better start do some accounting.
        for (int i = 0; i < 5; i++) {
            Accountant accountant = new Accountant(treasureRoom);
            new Thread(accountant).start();
        }

    }
}
