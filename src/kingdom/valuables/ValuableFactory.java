package kingdom.valuables;

import kingdom.valuables.metals.*;
import kingdom.valuables.stones.Diamond;
import kingdom.valuables.stones.Emerald;
import kingdom.valuables.stones.Ruby;

import java.util.HashMap;
import java.util.Random;

public class ValuableFactory {
    public static HashMap<Materials, Valuable> valuables = new java.util.HashMap<>();

    private static Valuable getValuable(Materials valuableType){
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

    public static Valuable getRandomValuable(){
        Random r = new Random();
        Materials[] valuableTypes = {Materials.BRASS, Materials.COPPER, Materials.BRONZE, Materials.SILVER, Materials.GOLD, Materials.EMERALD, Materials.RUBY, Materials.DIAMOND};
        Materials valuableOfType = valuableTypes[r.nextInt(8)];
        return getValuable(valuableOfType);
    }


    public static Valuable getRandomBrassValuable(){
        return getValuable(Materials.BRASS);
    }

    public static Valuable getRandomCopperValuable(){
        return getValuable(Materials.COPPER);
    }

    public static Valuable getRandomBronzeValuable(){
        return getValuable(Materials.BRONZE);
    }

    public static Valuable getRandomSilverValuable(){
        return getValuable(Materials.SILVER);
    }

    public static Valuable getRandomGoldValuable(){
        return getValuable(Materials.GOLD);
    }

    public static Valuable getRandomEmeraldValuable(){
        return getValuable(Materials.EMERALD);
    }

    public static Valuable getRandomRubyValuable(){
        return getValuable(Materials.RUBY);
    }

    public static Valuable getRandomDiamondValuable(){
        return getValuable(Materials.DIAMOND);
    }





}
