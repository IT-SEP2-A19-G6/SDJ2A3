package valuables;

import valuables.metals.*;
import valuables.stones.Diamond;
import valuables.stones.Emerald;
import valuables.stones.Ruby;

import java.util.HashMap;

public class ValuableFactory {
    public static HashMap<ValuableMaterials.Materials, Valuable> valuables = new java.util.HashMap<>();

    public static Valuable getValuable(ValuableMaterials.Materials valuableType){
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

}
