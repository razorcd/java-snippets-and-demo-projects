package main.java.classes.Polymorphism;

// Color can take different forms: Red, Blue, ...
public class ColorPicker {

    public static Color pickColor() {
        int random = (int) (Math.random() * 4 + 1); // random int between 1 and 3 (inclusive). Cast to (int) autside calculations!
        switch (random) {
            case 1:
                return new Blue();
//                break;
            case 2:
                return new Green();
//                break;
            case 3:
                return new Red();
            case 4:
                return new UnknownColor();
//                break;
        }
        return null;
    }


}

