package kingdom.valuables.stones;

import kingdom.valuables.Materials;
import kingdom.valuables.Property;
import kingdom.valuables.Valuable;

import java.util.Random;

public class Ruby implements Valuable {
    private Materials name;
    private String[] cuts = {"uncut", "cut"};
    private String cut;
    private int value;
    private Property property;


    public Ruby(){
        this.name = Materials.RUBY;
        this.value = 8;
        Random r = new Random();
        this.cut = cuts[r.nextInt(2)];
        this.property = new Property();
        calculateValue();
    }

    private void calculateValue() {
        if (cut.equals(cuts[0])){
            value *= 0.7;
        } else if (cut.equals(cuts[1])){
            value *= 1.9;
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

    @Override
    public Materials getMaterial() {
        return name;
    }
}
