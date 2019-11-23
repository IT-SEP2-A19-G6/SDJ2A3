package kingdom.valuables;

import java.util.Random;

public class Property {
    private String[] shapes = {"coin", "jewelery", "bar", "statue"};
    private String shape;
    private int size;
    private boolean isPolished;
    private double valueFactor;
    private Random r = new Random();

    public Property(){
        this.shape = shapes[r.nextInt(4)];
        isPolished = r.nextBoolean();
        size = r.nextInt(9)+1;
        valueFactor = 1;
        calculateFactor();
    }

    private void calculateFactor() {
        if (shape.equals(shapes[1])){
            valueFactor *= 1.3;
        } else if (shape.equals(shapes[2])){
            valueFactor *= 1.4;
        } else if (shape.equals(shapes[3])){
            valueFactor *= 1.6;
        }

        if (size >= 5){
            valueFactor += 0.2;
        } else if (size > 8){
            valueFactor *= size * 1.4;
        }

        if (isPolished){
            valueFactor += 0.7;
        } else {
            valueFactor *= 0.5;
        }

        if (valueFactor < 1) {
            valueFactor = 1.1;
        }
    }

    public String getShape() {
        return shape;
    }

    private boolean isPolished() {
        return isPolished;
    }

    public double getValueFactor() {
        return valueFactor;
    }

    public String getSize() {
        String sizeName;
        if (size <= 3){
            sizeName = "small";
        } else if (size <= 6){
            sizeName = "medium";
        } else if (size <= 8){
            sizeName = "large";
        } else {
            sizeName = "huge";
        }
        return sizeName;
    }

    public String getSurface(){
        String surface;
        if (isPolished){
            surface = "polished";
        } else {
            surface = "unpolished";
        }
        return surface;
    }
}
