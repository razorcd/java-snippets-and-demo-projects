package main.java.interfaces;

public class Square implements IShape, IDrawable {

    int width;



    public Square(int width) {
        this.width = width;
    }



    @Override
    public int getSides() {
        return 4;
    }

    @Override
    public double getArea() {
        return width^2;
    }

    // not in Interface
    public double getDiagonal() {
        return Math.sqrt(2*Math.pow(width,2));
    }

    @Override
    public void draw() {
        System.out.println("Drawing square.");
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }
}
