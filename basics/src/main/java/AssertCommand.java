package main.java;

public class AssertCommand {

    public static void run() {

        // Must start application with   `java -ea main.java.App` to enable assertions

        int i = 1;
        assert(true);   // passes
        assert(i==2);   // Throws if FALSE.
    }
}
