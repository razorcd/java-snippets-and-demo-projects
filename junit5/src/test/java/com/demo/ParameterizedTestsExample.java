package com.demo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.TestReporter;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Parameterized tests")
@ExtendWith(MyLifecycleExtension.class)
public class ParameterizedTestsExample {

    public ParameterizedTestsExample(TestInfo testInfo) {
        System.out.println("\nConstructor");
        assertEquals("Parameterized tests", testInfo.getDisplayName());
    }


    private static Stream<Arguments> getParameters() {
        return Stream.of(
                Arguments.of(1, 10, true),
                Arguments.of(2, 20, true),
                Arguments.of(3, 0, false)
        );
    }


    @BeforeEach
    void init(TestInfo testInfo) {
        String displayName = testInfo.getDisplayName();
        System.out.println("BeforeEach: Running test " + displayName);
//        assertTrue(displayName.matches("^Test #.*"));
    }


    @ParameterizedTest(name = "Test #{index}: value={0}")
    @ValueSource(longs ={11,22,33,44,55})
    void parameterizedTest1(long value) {
//    void parameterizedTest1(long value, TestInfo testInfo, TestReporter testReporter) {
//        System.out.println("Display name: " + testInfo.getDisplayName());
//        testReporter.publishEntry("Value", String.valueOf(value));

        assertTrue(value < 100);

    }

    /**
     * SOURCES can be Values, Enums, Methods, Class, CSV String and CSV File, custom ArgumentsProvider
     */

    @ParameterizedTest
    @MethodSource("getParameters")
    void parameterizedTest2(int num, int result, boolean state) {
        System.out.println(num + " * 10 = " + result + " is " + state);
        assertEquals(num * 10 == result, state );
    }

    @ParameterizedTest
    @CsvSource({ "foo, FOO", "bar, BAR", "'baz, qux', 'BAZ, QUX'" })
    void parameterizedTest3(String s1, String s2) {
        assertEquals(s1.toUpperCase(), s2);
    }


    @ParameterizedTest
    @EnumSource(MyEnums.class)
    void parameterizedtest4(MyEnums e) {
        System.out.println("Enum: " + e);
        assertEquals(e.getId() > 0, true);
    }


    public enum MyEnums {
        ONE(1),
        TWO(2),
        THREE(3);

        private final int id;

        MyEnums(int id) {
            this.id = id;
        }

        public int getId() {
            return id;
        }
    }

}


