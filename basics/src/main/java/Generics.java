package main.java;

import main.java.classes.ClassOne;
import main.java.classes.ClassOneSub;
import main.java.classes.ClassOneSubSub;

import java.util.ArrayList;

public class Generics {

    public static void run() {
        // Wildcard GENERICS in Lists
        //  ClassOneSubSub  inherits  ClassOneSub  inherits  ClassOne  inherits  Object

//        ? - too general, can't guarantee anything that you read or write to the list
        ArrayList<?> z = new ArrayList<>();
        z.add(null);
//        z.add(new ClassOneSubSub("s", 1, "d"));  // error
//        z.add(new ClassOneSub("s", 1));  // error
//        z.add(new ClassOne());           // error
//        z.add(new Object());             // error
//        ClassOneSubSub z0 = z.get(0);    // error
//        ClassOneSub z1 = z.get(0);       // error
//        ClassOne z2 = z.get(0);          // error
        Object z3 = z.get(0);

//        ? super ClassOneSub  -  guarantees that you can ADD `ClassOneSubSub` and `subclass of ClassOneSub` objects to the list (anything below||= ClassOneSub)
        ArrayList<? super ClassOneSub> x = new ArrayList<>();
        x.add(null);
        x.add(new ClassOneSubSub("s", 1, "d"));
        x.add(new ClassOneSub("s", 1));
//        x.add(new ClassOne()); // error
//        x.add(new Object());  // error
//        ClassOneSubSub x0 = x.get(0); // error
//        ClassOneSub x1 = x.get(0);  // error
//        ClassOne x2 = x.get(0);  // error
        Object x3 = x.get(0);

//        ? extends ClassOne  -  guarantees that you can GET `ClassOneSub` and `ClassOne` objects from the list (anything above||= ClassOneSub)
        ArrayList<? extends ClassOneSub> y = new ArrayList<>();
          y.add(null);
//        y.add(new ClassOneSubSub("s", 1, "d")); // error
//        y.add(new ClassOneSub("s", 1));  // error
//        y.add(new ClassOne()); // error
//        y.add(new Object()); // error
//        ClassOneSubSub y0 = y.get(0); // error
        ClassOneSub y1 = y.get(0);
        ClassOne y2 = y.get(0);
        Object y3 = y.get(0);
    }
}
