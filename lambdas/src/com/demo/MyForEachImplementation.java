package com.demo;

public class MyForEachImplementation {

    public static void run() {
        SuperArrayList<String> superArrayList = new SuperArrayList<>(new String[]{"a", "b", "c"});
        superArrayList.forEvery(s -> System.out.println(s));
        superArrayList.forEveryWithIndex((s,i) -> System.out.println(i + " - " + s));
    }
}


class SuperArrayList<T> {
    private T[] elements;

    public SuperArrayList(T[] elements) {
        this.elements = elements;
    }

    public void forEvery(MyCallable<T> myCallable) {
        for (int i = 0; i < elements.length; i++) {
            myCallable.call(elements[i]);
        }
    }

    public void forEveryWithIndex(MyCallableWithIndex<T> myCallableWithIndex) {
        for (int i = 0; i < elements.length; i++) {
            myCallableWithIndex.callWithIndex(elements[i], i);
        }
    }
}

@FunctionalInterface
interface MyCallable<T> {
    void call(T t);
}

@FunctionalInterface
interface MyCallableWithIndex<T> {
    void callWithIndex(T t, int i);
}