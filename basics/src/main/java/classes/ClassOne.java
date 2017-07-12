package main.java.classes;

// Top Class must be:
// public class ClassOne
// class ClassOne  =  package-private.  Can only be accessible from classes within `main.java.classes.**`
public class ClassOne {

    // ! Static variables Share the Class Scope between all instances
    private static String privateStaticVar;
    protected static String protectedStaticVar;
    public static String publicStaticVar;

    private String privateVar;  // visible only withing  This Class  and  Inner Classes
    protected String protectedVar;  //  visible in    Subclasses from any Package    and   Classes Inside Same Package
    public String publicVar;  // visible from everywhere

    private String foo;
    private int counter;
    private double amount;


    public ClassOne() {
        this("ClassOne,defaultFoo", 0, 0);   // call constructor with default values
    }

    public ClassOne(String foo) {
        this(foo, 0, 0);    // call constructor with default values
    }

    public ClassOne(String foo, int counter, double amount) {
        this.foo = foo;
        this.counter = counter;
        this.amount = amount;
    }

    // final methods can not be overridden
    public final void logSomething() {
        System.out.println("Logging something from " + this.getClass());
    }

    public void logSomething2() {
        System.out.println("Logging something2 from " + this.getClass());
    }

    public String getFoo() {
        return foo;
    }

    public void setFoo(String foo) {
        this.foo = foo;
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
