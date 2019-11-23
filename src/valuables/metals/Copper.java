package valuables.metals;

import valuables.Property;
import valuables.Valuable;
import valuables.ValuableMaterials;

import java.util.Random;

public class Copper implements Valuable {
    private ValuableMaterials.Materials name;
    private int value;
    private Property property;


    public Copper(){
        this.name = ValuableMaterials.Materials.COPPER;
        this.value = 2;
        Random r = new Random();
        this.property = new Property();
        calculateValue();
    }

    private void calculateValue() {
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
        return property.getSize() + " " + property.getSurface() + " " + getName() + " " + property.getShape() + " with a value of " + value;
    }

}
