package main.java.interfaces;

public class Circle implements IShape, IDrawable {

    int radius;

    public Circle(int radius) {
        this.radius = radius;
    }



    @Override
    public int getSides() {
        return 0;
    }

    @Override
    public double getArea() {
        return Math.PI*(Math.pow(radius,2));
    }

    @Override
    public void draw() {
        System.out.println("Drawing circle.");
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }
}
