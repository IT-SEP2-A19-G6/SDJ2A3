package valuables.stones;

import valuables.Property;
import valuables.Valuable;
import valuables.ValuableMaterials;

import java.util.Random;

public class Emerald implements Valuable {
    private ValuableMaterials.Materials name;
    private String[] cuts = {"uncut", "cut"};
    private String cut;
    private int value;
    private Property property;


    public Emerald(){
        this.name = ValuableMaterials.Materials.EMERALD;
        this.value = 7;
        Random r = new Random();
        this.cut = cuts[r.nextInt(2)];
        this.property = new Property();
        calculateValue();
    }

    private void calculateValue() {
        if (cut.equals(cuts[0])){
            value *= 0.5;
        } else if (cut.equals(cuts[1])){
            value *= 1.7;
        }

        value *= property.getValueFactor();

        if (value < 1){
            value = 1;
        }
    }

    @Override
    public String getName() {
        return name.name().toLowerCase();
    }

    @Override
    public int getValue() {
        return value;
    }

    @Override
    public String getType() {
        return property.getSize() + " " + property.getSurface() + " " + cut + " " + getName() + " " + property.getShape() + " with a value of " + value;
    }
}
