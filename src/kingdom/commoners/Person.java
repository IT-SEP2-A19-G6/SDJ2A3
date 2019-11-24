package kingdom.commoners;

import kingdom.catalog.Catalog;
import kingdom.valuables.Valuable;
import kingdom.valuables.ValuableFactory;
import kingdom.valuables.ValuableMaterials;

import java.util.ArrayList;
import java.util.Random;
/*
    Person class added for extra fun

 */
public class Person implements Runnable {

    private CommonerType socialStatus;
    private ArrayList<Valuable> ownedValuables;
    private int distanceFromCastle;

    public Person(CommonerType socialStatus, int maxDistance){
        this.socialStatus = socialStatus;
        ownedValuables = new ArrayList<>();
        distanceFromCastle = new Random().nextInt(maxDistance);

        Catalog.getInstance().write(this,
                "A person from the social class " + socialStatus +
                        " has settled down at " + distanceFromCastle +
                        "km from the castle where the king resides.");
    }


    public int getDistanceFromCastle() {
        return distanceFromCastle;
    }


    public int getValuableTotalSum() {
        int sum = 0;
        for (Valuable value : ownedValuables) {
            sum += value.getValue();
        }
        return sum;
    }

    @Override
    public void run() {
        try {
            // Cant work while we are moving to the owned or rented lands.
            Thread.sleep(5000);
            // work cycle
            while (true) {
                // Person goes working for 12 - 24 hours to find kingdom.valuables in his area. (1 hour is 1000 ms)
                Thread.sleep((new Random().nextInt(12)+12)*1000);
                // Did the work day result in a random valuable?
                int determinate = new Random().nextInt(10);
                if (socialStatus.ordinal() <= determinate) {
                    ValuableMaterials materials = new ValuableMaterials();
                    Valuable valuable = ValuableFactory.getValuable(materials.getRandomValuable());
                    // is this type of social status allowed to have that valuable?
                    // if the material is greater than his social status, he should not be allowed to generate it.
                    if (valuable.getMaterial().ordinal() <= socialStatus.ordinal() + 2) {
                        ownedValuables.add(valuable);
                        // add euphoric response
                        Catalog c = Catalog.getInstance();
                        if (valuable.getMaterial().ordinal() >= socialStatus.ordinal()) {
                            c.write(this, "A " + socialStatus + " " + peopleResponses(true) + " " + valuable.getType() +
                                    " with the value of " + valuable.getValue() + ". He now has a total of " + getValuableTotalSum() + " worth of valuables.");
                        }
                        // unhappy response
                        if (valuable.getMaterial().ordinal() <= socialStatus.ordinal() - 2) {
                            c.write(this, "A " + socialStatus + " " + peopleResponses(false) + " " + valuable.getType() + " with the value of " + valuable.getValue() );
                        }
                    }
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Valuable> takeValuables() {
        Catalog c = Catalog.getInstance();
        c.write(this, socialStatus + " got " + ownedValuables.size() + " owned valuables which is being taken by the tax collector");

        ArrayList<Valuable> tempValuables = new ArrayList<>(ownedValuables); // Creates a shallow copy
        ownedValuables.clear();

        return tempValuables;
    }

    private String peopleResponses(boolean happy) {
        ArrayList<String> response = new ArrayList<>();

        if (happy) {
            response.add("is walking around near his place. He sees something and decides to check it out. It turns out it was his lucky day because he found");
            response.add("is euphoric about the new acquired");
            response.add("is very excited about his new");
            response.add("were out drinking with his farmer buddies when he found a");
            response.add("has worked really hard and has received");
            response.add("has been lucky lately. Especially after he found a");
            response.add("thinks the day could not get any better, yet he just got a");
            response.add("was peeing outside his house when lightning stroke the ground near him. Totally shocked about the events he checks the ground near impact and finds a");
            response.add("has birthday today and received a gift from the kingdom in form of");
            response.add("has been beaten up by his wife. He walks slowly around his land, banished from his house, when he stumbles upon");
            response.add("was betting on a knight fight and received");
            response.add("saw to thieves running from guards with their stolen goods. He decides to stop them and receives");
        } else {
            response.add("slips in the rain, break his arms and legs on");
            response.add("went into a fight with his spouse when she throw a");
            response.add("house is on fire. Everything turns to ashes, whats left is only a");
            response.add("is furious about the new acquired");
            response.add("just sold his house, and all he got was ");
            response.add("has been working really hard but only got fucking");
            response.add("can't believe he just got a");
            response.add("went into a bar fight and got hit in the head with a");
            response.add("went out trading. Somehow he lost his two diamonds and ended out with");
            response.add("just sold his house, and all he got was");
            response.add("robbed a local merc but all he got was");
        }

        return response.get(new Random().nextInt(response.size()));

    }
}
