package main.java.classes;

public class ClassOneSubSub extends ClassOneSub {
    String newSubSubField;

    public ClassOneSubSub(String extraField, int extraCounter, String newSubSubField) {
        super(extraField, extraCounter);
        this.newSubSubField = newSubSubField;
    }
}
