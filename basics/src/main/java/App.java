package main.java;

import main.java.classes.ClassOne;
import main.java.classes.ClassOneSub;
import main.java.classes.ClassTwo;
import main.java.classes.InnerClasses.House;
import main.java.classes.Polymorphism.ColorPicker;
import main.java.classes.valueObject.IValueObject;
import main.java.classes.valueObject.ValueObject;
import main.java.interfaces.Circle;
import main.java.interfaces.IShape;
import main.java.interfaces.Square;

public class App {

    public static void main(String[] args) {
//        PrimitiveTypes.run();
//        Constant.run();
//        BitwiseOperators.run();
//        AssertCommand.run();
//        Flow.run();

        // CLASSES
            ClassOne c1 = new ClassOne();
            System.out.println(c1.getFoo());
            c1.logSomething();
            c1.logSomething2();
            ClassOneSub c1sub = new ClassOneSub("extraFieldVal", 0);
            System.out.println(c1sub.getExtraField());
            c1sub.logSomething();
            c1sub.logSomething2();

            ClassTwo c2 = new ClassTwo("c2Name", new ClassOne());
            c2.getClassOne().logSomething2();

            //casting classes
            ClassOneSub cc1sub = new ClassOneSub("test", 0);
            ClassOne cc1s = (ClassOne) cc1sub;
            cc1s.logSomething();  // will log ClassOneSub

            //Polymorphism
            ColorPicker.pickColor().paint();
            ColorPicker.pickColor().paint();
            ColorPicker.pickColor().paint();




        // INTERFACES
            IShape sh = new Square(4);   // can only define 1 Interface as type
            System.out.println("(I) Area of square is: "+sh.getArea());
//            System.out.println("(I) Diagonal of square is: "+sq.getDiagonal());  // sq is declared as Interface, it can no access methods that are specific only to the class.
            sh = new Circle(3);  // same variable (same Interface)
            System.out.println("(I) Area of circle is: "+sh.getArea());
//            sh.draw(); // method not accessible in IShape

            Square sq2 = new Square(4);
            System.out.println("(C) Area of square is: "+sq2.getArea());
            System.out.println("(C) Diagonal of square is: "+sq2.getDiagonal());
            sq2.draw();



        // INNER CLASSES
            // 1. private inner class (not accessible from outside class)
            House h = new House(75);
            h.addRoom(30);
            System.out.println("RoomsPrivs: "+h.getRoomPrivs());

            // 2. public inner class
            House.RoomPub roomPub = h.new RoomPub(3);   // define Public Inner CLASS
//            House.RoomPub roomPub = new House.RoomPub(3);  // not working
//            House.RoomPub roomPub = new h.RoomPub(3);  // not working

            // 3. public static inner class
            House.StaticInnerPubClass hsipc = new House.StaticInnerPubClass();
            hsipc.staticSayPub();

            // 4. private static inner class (not accessible from outside class)
//            House.StaticInnerPrivClass hsiprivc = new House.StaticInnerPrivClass();  //  private static class not accesible


            // 5. public method inner class (not accessible from outside class)
            h.methodWithClass();


            // 6. anonymous class.   Instantiates based on an interface and defines required methods.    Sends instance of anonymous class to method call.
            h.sayMethodWithAnonymousClass(new IValueObject<String>() {
                String value;
                @Override
                public String getValue() {
                    return value;
                }

                @Override
                public void setValue(String value) {
                    this.value = value;
                }
            });





        //arrays
//        ListsDemo.run();

        //console input
//        new ConsoleInput().run();
    }

}
