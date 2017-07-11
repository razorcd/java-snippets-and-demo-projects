package main.java.classes;

import java.util.Objects;

public class ValueObject<T> {
    private T value;

    public ValueObject() {
    }

    public ValueObject(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }

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
