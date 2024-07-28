package com.tpe.day01;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * BeforeEach annotations executes annotated classes before each method
 * AfterEach annotations executes annotated classes after each method
 */
public class Test03_BeforeEach_AfterEach {

    String str;

    /**
     * assign value str before each test method
     */
    @BeforeEach
    void beforeEach(){
        str = "Test methods should be simple";
        System.out.println("*********** beforeEach() method is been executed. ***********");
    }

    /**
     * assign value str after each test method
     */
    @AfterEach
    void afterEach(){
        str = null;
        System.out.println("*********** afterEach() method is been executed. ***********");
    }

    @Test
    void testArray(TestInfo info){
        String[] actualValue = str.split(" ");
        String[] expectedValue = {"Test", "methods", "should", "be", "simple"};
        assertTrue(Arrays.equals(expectedValue,actualValue));
        System.out.println(info.getDisplayName()+" is executed");
    }

    @Test
    void testStringLength(TestInfo info){
        int actualValue = str.length();
        int expectedValue = 29;
        assertEquals(expectedValue, actualValue);
        System.out.println(info.getDisplayName()+" is executed");
    }
}
