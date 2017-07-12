package main.java.classes.valueObject;

public interface IValueObject<T> {
    T getValue();
    void setValue(T value);
}
