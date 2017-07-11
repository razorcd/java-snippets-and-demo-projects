package main.java.classes;

public class ClassTwo {

    private String name;
    private ClassOne classOne;

    public ClassTwo() {
    }

    public ClassTwo(String name, ClassOne classOne) {
        this.name = name;
        this.classOne = classOne;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ClassOne getClassOne() {
        return classOne;
    }

    public void setClassOne(ClassOne classOne) {
        this.classOne = classOne;
    }
}
