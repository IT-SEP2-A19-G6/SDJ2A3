package valuables.metals;

import valuables.Materials;
import valuables.Property;
import valuables.Valuable;
import valuables.ValuableMaterials;

import java.util.Random;

public class Brass implements Valuable {
    private Materials name;
    private int value;
    private Property property;


    public Brass(){
        this.name = Materials.BRASS;
        this.value = 1;
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

    @Override
    public Materials getMaterial() {
        return name;
    }

}
