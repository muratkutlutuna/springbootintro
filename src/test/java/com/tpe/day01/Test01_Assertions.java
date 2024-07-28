package com.tpe.day01;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Test01_Assertions {
    @Test//makes method runnable
    public void test(){

    }
    /*
    //starting from JUnit 5  public access modifier is optional
    It is suggested that test name should start with name
     */

    /**
     * Test length method
     */
    @Test
    public void testLength(){
        String str = "Hello World";
        int actualValue = str.length();
        int expectedValue = 11;
        assertEquals(expectedValue, actualValue);
    }

    /**
     * Test uppercase from core java
     */
    @Test
    public void testUpperCase(){
        String actualValue = "hello".toUpperCase();
        String expectedValue = "HELLO";
        assertEquals(expectedValue, actualValue,"UpperCase method field");
    }

    /**
     * Test Math.addExact from core java
     */
    @Test
    public void testAddExact(){
        int actualValue = Math.addExact(2, 4);
        int expectedValue = 6;
        assertEquals(expectedValue,actualValue,"MATH.addExact method from core java");
    }

    /**
     * Test String.contains method from core java
     */
    @Test
    public void testContains(){
        assertEquals(false,"JUnit".contains("z"));
    }

}
