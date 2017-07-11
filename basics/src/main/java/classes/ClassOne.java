package main.java.classes;

public class ClassOne {

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


    public void logSomething() {
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
