package com.tpe.day01;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * BeforeAll annotated method should be static unless the test classes are annotated with @TestInstance(Lifecycle.PER_CLASS)
 */
public class Test04_BeforeAll_AfterAll {

    @BeforeAll
    static void startUp(){
        System.out.println("BeforeAll() is executed");
    }
    @AfterAll
    static void tearDown(){
        System.out.println("AfterAll() is executed");
    }
    @Test
    void testArray(TestInfo info){ //TestInfo class gives information about running class
        String str = "Unit tests with JUnit";
        String[] actualValue = str.split(" ");
        String[] expectedValue = {"Unit", "tests", "with", "JUnit"};
        assertArrayEquals(expectedValue,actualValue,"Arrays are not equal");
        System.out.println(info.getDisplayName()+" is executed");
    }
    @Test
    void testArray2(TestInfo info){ //TestInfo class gives information about running class
        String str = "Tests should be isolated";
        String[] actualValue = str.split(" ");
        String[] expectedValue = {"Tests", "should", "be", "isolated"};
        assertArrayEquals(expectedValue,actualValue,"Arrays are not equal");
        System.out.println(info.getDisplayName()+" is executed");
    }

}
