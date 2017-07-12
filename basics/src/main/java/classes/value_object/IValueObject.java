package main.java.classes.value_object;

// maybe not use I in interfaces names, it's confusing.
public interface IValueObject<T extends Object> {  // or extends a Class with Interfaces: <T extends Class1 & Interface1 & Interface2>

    int MY_CONSTANT = 100;  // constant defined in an interface. By default is public.
    T getValue();                    // by default it's public.
    public void setValue(T value);   // by default it's public.    ! Only public allowed in interfaces.
}
