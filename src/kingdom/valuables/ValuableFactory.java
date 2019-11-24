package kingdom.valuables;

import kingdom.valuables.metals.*;
import kingdom.valuables.stones.Diamond;
import kingdom.valuables.stones.Emerald;
import kingdom.valuables.stones.Ruby;

import java.util.HashMap;
import java.util.Random;

public class ValuableFactory {
    public static HashMap<Materials, Valuable> valuables = new java.util.HashMap<>();

    public static Valuable getValuable(Materials valuableType){
        Valuable valuable = valuables.get(valuableType);
        if (valuable == null) {
            switch (valuableType) {
                case DIAMOND: {
                    valuable = new Diamond();
                    break;
                }
                case EMERALD: {
                    valuable = new Emerald();
                    break;
                }
                case RUBY: {
                    valuable = new Ruby();
                    break;
                }
                case GOLD: {
                    valuable = new Gold();
                    break;
                }
                case SILVER: {
                    valuable = new Silver();
                    break;
                }
                case BRONZE: {
                    valuable = new Bronze();
                    break;
                }
                case COPPER: {
                    valuable = new Copper();
                    break;
                }
                case BRASS: {
                    valuable = new Brass();
                    break;
                }
            }
            valuables.put(valuableType, valuable);
        }
        return valuable;
    }

    public static Materials getRandomMaterial(){
        Random r = new Random();
        Materials[] valuableTypes = {Materials.BRASS, Materials.BRONZE, Materials.COPPER, Materials.GOLD, Materials.SILVER, Materials.DIAMOND, Materials.EMERALD, Materials.RUBY};
        return valuableTypes[r.nextInt(8)];
    }

}
