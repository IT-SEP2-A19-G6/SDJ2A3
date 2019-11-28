package kingdom.valuables.metals;

import kingdom.valuables.Materials;
import kingdom.valuables.Property;
import kingdom.valuables.Valuable;

import java.util.Random;

public class Silver implements Valuable {
    private Materials name;
    private int value;
    private Property property;


    public Silver(){
        this.name = Materials.SILVER;
        this.value = 4;
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
