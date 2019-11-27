package kingdom.taxcollector;

import kingdom.catalog.Catalog;
import kingdom.commoners.Person;
import kingdom.treasueroom.TreasureRoom;
import kingdom.valuables.Valuable;

import java.util.*;


public class TaxCollector implements Runnable {

    private ArrayList<Person> commoners;
    private ArrayList<Valuable> carriedValuables;
    private TreasureRoom treasureRoom;
    private int valuableTargetSum;

    public TaxCollector(TreasureRoom treasureRoom, int valuableTargetSum) {
        this.treasureRoom = treasureRoom;
        this.valuableTargetSum = valuableTargetSum;
        commoners = new ArrayList<>();
        carriedValuables = new ArrayList<>();
        Catalog.getInstance().write(this, "A tax collector has been hired to collect tax ("+ valuableTargetSum +" at the time) for the king.");
    }

    @Override
    public void run() {
        Catalog c = Catalog.getInstance();
        // starts riding around the country to collect tax for the king
        c.write(this, "The kings tax collector is riding around the country to collect tax for the king");

        while(true) {
            try {
                // decide actions
                if (commoners.size() == 0) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else if (calculateCarriedValuableSum() >= valuableTargetSum) {
                    c.write(this, "The tax collector has collected all the necessary tax for the kings party. Heading home with the valuables...");
                    Thread.sleep(2000);
                    deliverValuablesToTheKing();

                } else {
                    collectTax();
                }
            } catch (InterruptedException e) {
                c.write(this, "Something went wrong while the tax collector was working. So now he just quit his job and stopped working....");
                e.printStackTrace();
            }
        }
    }

    private void collectTax() throws InterruptedException {
            int horseSpeed = 8;
            // take a random citizen of the kingdom and collect tax
            Person p = commoners.get(new Random().nextInt(commoners.size()));
            // if that citizen has no kingdom.valuables. Skip
            if (p.getValuableTotalSum() == 0) return;
            Catalog c = Catalog.getInstance();
            c.write(this, "The tax collector rides to the next person on his list.");
            // lets ride to get their kingdom.valuables (horse has avg of 14km/h speed (max 88km/h if needed))
            Thread.sleep((p.getDistanceFromCastle() / horseSpeed) * 1000);

            // take their stuff
            carriedValuables.addAll(p.takeValuables());
            c.write(this, "tax collector got some of kingdom.valuables and is now carrying " + calculateCarriedValuableSum() + " worth of goods.");

    }

    private void deliverValuablesToTheKing() throws InterruptedException {
        // add it into the treasure vault
        treasureRoom.acquireWrite(this);
        for (Valuable val : carriedValuables) {
            Catalog.getInstance().write(this, "Tax collector is adding a new item to the treasure");
            Thread.sleep(new Random().nextInt(800) + 200); // 1000 is 1 hour
            treasureRoom.addValuable(this, val);
        }
        carriedValuables.clear();
        treasureRoom.releaseWrite(this);
    }

    private int calculateCarriedValuableSum() {
        int sum = 0;
        for (Valuable carriedValuable : carriedValuables) {
            sum += carriedValuable.getValue();
        }
        return sum;
    }


    public void addCommoner(Person p) {
        commoners.add(p);
    }
}
