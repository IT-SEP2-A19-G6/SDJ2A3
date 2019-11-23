package kingdom.valuables;

import kingdom.valuables.metals.*;
import kingdom.valuables.stones.Diamond;
import kingdom.valuables.stones.Emerald;
import kingdom.valuables.stones.Ruby;

import java.util.HashMap;

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

}
