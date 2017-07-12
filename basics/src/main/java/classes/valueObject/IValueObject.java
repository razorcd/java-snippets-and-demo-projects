package main.java.classes.valueObject;

// maybe not use I in interfaces names, it's confusing.
public interface IValueObject<T> {
    T getValue();
    void setValue(T value);
}
