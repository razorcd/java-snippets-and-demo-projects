package main.java.classes;

public class ClassOneSub extends ClassOne {

    private String extraField;
    private int extraCounter;



    public ClassOneSub(String extraField, int extraCounter) {
        this.extraField = extraField;
        this.extraCounter = extraCounter;

//        this.privateVar = "";   // not accessible here
        this.protectedVar = "";  //  visible in Subclasses from any Package   and   Inside Same Package
        this.publicVar = "";  // visible from everywhere

//        ClassOne.privateStaticVar = "";   // not accessible here
        ClassOne.protectedStaticVar = ""; //  visible in Subclasses from any Package   and   Inside Same Package
        ClassOne.publicStaticVar = "";  // visible from everywhere

    }

    public ClassOneSub(String foo, String extraField, int extraCounter) {
        super(foo);
        this.extraField = extraField;
        this.extraCounter = extraCounter;
    }

    public ClassOneSub(String foo, int counter, double amount, String extraField, int extraCounter) {
        super(foo, counter, amount);
        this.extraField = extraField;
        this.extraCounter = extraCounter;
    }


    @Override   //= if super class does not have this method it will throw a compile error
    public void logSomething2() {
        System.out.println("Logging something2 (overrode) from " + this.getClass());
    }

    public void subMethod() {
        System.out.println("ClassOneSub#subMethod calles");
    }

    public String getExtraField() {
        return extraField;
    }

    public void setExtraField(String extraField) {
        this.extraField = extraField;
    }

    public int getExtraCounter() {
        return extraCounter;
    }

    public void setExtraCounter(int extraCounter) {
        this.extraCounter = extraCounter;
    }
}
