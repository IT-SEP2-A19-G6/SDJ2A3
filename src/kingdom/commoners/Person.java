package kingdom.commoners;

import valuables.Materials;
import valuables.Valuable;
import valuables.ValuableFactory;
import valuables.ValuableMaterials;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Random;

public class Person implements Runnable {

    private CommonerType socialStatus;
    private ArrayList<Valuable> ownedValuables;
    private int distanceFromCastle;

    public Person(CommonerType socialStatus, int maxDistance){
        this.socialStatus = socialStatus;
        ownedValuables = new ArrayList<>();
        distanceFromCastle = new Random().nextInt(maxDistance);

        System.out.println(
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
                // Person goes working for 12 - 24 hours to find valuables in his area. (1 hour is 1000 ms)
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
                        if (valuable.getMaterial().ordinal() >= socialStatus.ordinal()) {
                            System.out.println(socialStatus + " is euphoric about the new acquired " + valuable.getType() +
                                    " he now has a total of " + getValuableTotalSum() + " worth of valuables.");
                        }
                        // unhappy response
                        if (valuable.getMaterial().ordinal() <= socialStatus.ordinal() - 2) {
                            System.out.println(socialStatus + " is NOT HAPPY about the new acquired " + valuable.getType() +
                                    ". It's way beneath a " + socialStatus + "!!!");
                        }
                    }
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Valuable> takeValuables() {
        System.out.println(socialStatus + " got " + ownedValuables.size() + " valuables which is being taken by the tax collector");

        ArrayList<Valuable> tempValuables = new ArrayList<>(ownedValuables); // Creates a shallow copy
        ownedValuables.clear();

        return tempValuables;
    }
}
