package com.demo;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

// Non modifiable class. myMaps is encapsulated.
public final class MyEncapsulatedMaps {
    private final Map<String, Account> myMaps;

    public MyEncapsulatedMaps(Map<String, Account> myMaps) {
        this.myMaps = new HashMap<>(myMaps);   // clone the Map
        this.myMaps.put("Default", new Account("Default", new BigDecimal(0)));
    }

    public Map<String, Account> getMyMaps() {
//        return Collections.unmodifiableMap(myMaps);  // returns a Map that is readonly. Any attempt to modify it will throw `UnsupportedOperationException`
        return new HashMap<>(myMaps);  // returns a copy of the map. The copy of the map still holds references to same Account objects!
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MyEncapsulatedMaps that = (MyEncapsulatedMaps) o;
        return Objects.equals(myMaps, that.myMaps);
    }

    @Override
    public int hashCode() {

        return Objects.hash(myMaps);
    }
}
