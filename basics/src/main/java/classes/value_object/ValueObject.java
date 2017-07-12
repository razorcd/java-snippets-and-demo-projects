package main.java.classes.value_object;

import java.util.Objects;

public class ValueObject<T> implements IValueObject<T>, Comparable<T> {
    private T value;

    public ValueObject() {
    }

    public ValueObject(T value) {
        this.value = value;
    }


    @Override
    public int compareTo(T o) {
        return this.toString().compareTo(o.toString());   // dummy String comparison. It should return  -1 if o<this , 0 if o==this, 1 if o>this
    }


    @Override
    public T getValue() {
        return value;
    }

    @Override
    public void setValue(T value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "ValueObject{" +
                "value=" + value +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ValueObject<?> that = (ValueObject<?>) o;
        return Objects.equals(getValue(), that.getValue());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getValue());
    }
}
