package kingdom.taxcollector;

import kingdom.commoners.Person;
import treasueroom.TreasureRoom;
import valuables.Valuable;

import java.util.*;

import static java.util.Comparator.comparing;

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
    }

    @Override
    public void run() {
        // starts riding around the country to collect tax for the king
        System.out.println("The kings tax collector is riding around the country to collect tax for the king");

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
                    System.out.println("The tax collector has collected all the necessary tax for the kings party. Heading home with the valuables...");
                    Thread.sleep(2000);
                    deliverValuablesToTheKing();

                } else {
                    collectTax();
                }
            } catch (InterruptedException e) {
                System.out.println("Something went wrong while the tax collector was working. So now he just quit his job and stopped working....");
                e.printStackTrace();
            }
        }
    }

    private void collectTax() throws InterruptedException {
            int horseSpeed = 8;
            // take a random citizen of the kingdom and collect tax
            Person p = commoners.get(new Random().nextInt(commoners.size()));
            // if that citizen has no valuables. Skip
            if (p.getValuableTotalSum() == 0) return;

            System.out.println("The tax collector rides to the next person on his list.");
            // lets ride to get their valuables (horse has avg of 14km/h speed (max 88km/h if needed))
            Thread.sleep((p.getDistanceFromCastle() / horseSpeed) * 1000);

            // take their stuff
            carriedValuables.addAll(p.takeValuables());
            System.out.println("tax collector got some of valuables and is now carrying " + calculateCarriedValuableSum() + " worth of goods.");

    }

    private void deliverValuablesToTheKing() throws InterruptedException {
        // add it into the treasure vault
        treasureRoom.acquireWrite(this.getClass().getSimpleName());
        for (Valuable val : carriedValuables) {
            System.out.println("Tax collector is adding a new item to the treasure");
            Thread.sleep(new Random().nextInt(800) + 200); // 1000 is 1 hour
            treasureRoom.addValuable(this.getClass().getSimpleName(), val);
        }
        carriedValuables.clear();
        treasureRoom.releaseWrite(this.getClass().getSimpleName());
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
