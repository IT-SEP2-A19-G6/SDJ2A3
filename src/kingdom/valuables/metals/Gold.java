package kingdom.valuables.metals;

import kingdom.valuables.Materials;
import kingdom.valuables.Property;
import kingdom.valuables.Valuable;

import java.util.Random;

public class Gold implements Valuable {
    private Materials name;
    private double carat;
    private int value;
    private Property property;


    public Gold(){
        this.name = Materials.GOLD;
        this.value = 6;
        int[] carats = {8, 14, 18, 24};
        Random r = new Random();
        this.carat = carats[r.nextInt(4)];
        this.property = new Property();
        calculateValue();
    }

    private void calculateValue() {
        value *= carat/10;
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
        return property.getSize() + " " + property.getSurface() + " " + carat + " carat " + getName() + " " + property.getShape() + " with a value of " + value;
    }

    @Override
    public Materials getMaterial() {
        return name;
    }

}
