package com.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.core.CombinableMatcher.either;
import static org.junit.Assert.assertThat;

@RunWith(Parameterized.class)
public class ParameterizedBankTest {

//    private Bank subject;


    private String paramString;
    private int paramInt;
    private boolean paramBool;


    public ParameterizedBankTest(String paramString, int paramInt, boolean paramBool) {
        this.paramString = paramString;
        this.paramInt = paramInt;
        this.paramBool = paramBool;
    }




    @Parameterized.Parameters
    public static Collection<Object> testCollections() {
        return Arrays.asList(new Object[][] {
                {"A", 100, true},
                {"B", 200, false},
                {"C", 300, true}
        });
    }



    @Test
    public void someTest() {
        assertThat(paramString, either(equalTo("A")).or(equalTo("B")).or(equalTo("C")));
        assertThat(paramInt, either(equalTo(100)).or(equalTo(200)).or(equalTo(300)));
        assertThat(paramBool, either(equalTo(false)).or(equalTo(true)));
    }


}