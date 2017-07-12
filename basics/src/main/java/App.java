package main.java;

import main.java.classes.ClassOne;
import main.java.classes.ClassOneSub;
import main.java.classes.ClassTwo;
import main.java.classes.StaticInitialization;
import main.java.classes.inner_classes.House;
import main.java.classes.polymorphism.ColorPicker;
import main.java.classes.abstract_classes.Animal;
import main.java.classes.abstract_classes.Dog;
import main.java.classes.value_object.IValueObject;
import main.java.interfaces.Circle;
import main.java.interfaces.IShape;
import main.java.interfaces.Square;

public class App {

    public static void main(String[] args) {
        PrimitiveTypes.run();
        Constant.run();
        BitwiseOperators.run();
//        AssertCommand.run();
        Flow.run();

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


            // 6. anonymous class.   Instantiates based on an interface and defines the required methods.    Sends instance of anonymous class to method call. Or can be assigned to variable, see below.
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


        // ABSTRACT CLASSES
            Animal dog = new Dog("Rita");
            dog.say();
            Dog dog1 = (Dog) dog;
            dog1.sayMore();

//            Animal animal = new Animal("custom name");  // Abstract classes can NOT be Instantiated. Unless defining Anonymous class:
            // Abstract class with Anonymous class:
            Animal customAnimal = new Animal("custom name") {
                @Override
                public void say() {
                    System.out.println("#$#$%^ @$@#");
                }
            };
            customAnimal.say();

        // Static Initialization
        System.out.println(StaticInitialization.staticVar);  // invokes static initializer if called first
        StaticInitialization.someStaticMethod();  // invokes static initializer if called first
        StaticInitialization s = new StaticInitialization(); // invokes static initializer if called first
        s.someMethod();

        //Generics
//        Generics.run();

        //arrays
//        ListsDemo.run();

        //console input
//        new ConsoleInput().run();
    }

}
