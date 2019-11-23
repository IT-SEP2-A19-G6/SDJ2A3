package valuables;

import java.util.ArrayList;
import java.util.Random;

public class ValuableMaterials {

    public enum Materials {
        BRASS,
        BRONZE,
        COPPER,
        GOLD,
        SILVER,
        DIAMOND,
        EMERALD,
        RUBY;
    }

    public Materials getRandomValuable(){
        Random r = new Random();
        Materials[] valuableTypes = {Materials.BRASS, Materials.BRONZE, Materials.COPPER, Materials.GOLD, Materials.SILVER, Materials.DIAMOND, Materials.EMERALD, Materials.RUBY};
        return valuableTypes[r.nextInt(8)];
    }

}
