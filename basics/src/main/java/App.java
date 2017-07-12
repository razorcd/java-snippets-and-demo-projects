package main.java;

import main.java.classes.ClassOne;
import main.java.classes.ClassOneSub;
import main.java.classes.ClassTwo;
import main.java.classes.Polymorphism.ColorPicker;

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

        //arrays
        ListsDemo.run();

        //console input
//        new ConsoleInput().run();
    }

}
