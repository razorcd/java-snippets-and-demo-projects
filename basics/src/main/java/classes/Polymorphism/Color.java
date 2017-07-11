package main.java.classes.Polymorphism;

// Color can take different forms: Red, Blue, ...
public class Color {

    private String name;
    private double price;

    public Color() {
    }


    public void paint() {
        System.out.println("Can not paint.");
    }
}


class Red extends Color {
    @Override
    public void paint() {
        System.out.println("Painting with color red.");
    }
}

class Green extends Color {
    @Override
    public void paint() {
        System.out.println("Painting with color green.");
    }
}

class Blue extends Color {
    @Override
    public void paint() {
        System.out.println("Painting with color blue.");
    }
}

class UnknownColor extends Color {

}