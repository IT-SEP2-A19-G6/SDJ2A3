package kingdom.valuables.stones;

import kingdom.valuables.Materials;
import kingdom.valuables.Property;
import kingdom.valuables.Valuable;

import java.util.Random;

public class Diamond implements Valuable {
    private Materials name;
    private boolean isBloodDiamond;
    private String[] cuts = {"uncut", "cut"};
    private String cut;
    private String[] colours = {"clear", "green", "blue", "pink"};
    private String colour;
    private int value;
    private Property property;


    public Diamond(){
        this.name = Materials.DIAMOND;
        this.value = 10;
        Random r = new Random();
        isBloodDiamond = r.nextBoolean();
        this.colour = colours[r.nextInt(4)];
        this.cut = cuts[r.nextInt(2)];
        this.property = new Property();
        calculateValue();
    }

    private void calculateValue() {
        if (colour.equals(colours[0])){
            value += 10;
        } else if (colour.equals(colours[1])){
            value += 18;
        } else if (colour.equals(colours[2])){
            value += 25;
        } else if (colour.equals(colours[3])){
            value += 20;
        }

        if (cut.equals(cuts[0])){
            value *= 0.8;
        } else if (cut.equals(cuts[1])){
            value *= 2.8;
        }

        if (isBloodDiamond){
            value *= 0.5;
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
        if (isBloodDiamond){
            return property.getSize() + " " + property.getSurface() + " and " + cut + " " + getName() + " blood diamond " + property.getShape() + " with a value of " + value;
        } else {
            return property.getSize() + " " + property.getSurface() + " and " + cut + " " + getName() + " " + property.getShape() + " with a value of " + value;
        }
    }

    @Override
    public Materials getMaterial() {
        return name;
    }
}
