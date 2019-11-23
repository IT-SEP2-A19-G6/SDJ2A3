package kingdom.valuables;

import java.util.Random;

public class ValuableMaterials {

    public Materials getRandomValuable(){
        Random r = new Random();
        Materials[] valuableTypes = {Materials.BRASS, Materials.BRONZE, Materials.COPPER, Materials.GOLD, Materials.SILVER, Materials.DIAMOND, Materials.EMERALD, Materials.RUBY};
        return valuableTypes[r.nextInt(8)];
    }

}
