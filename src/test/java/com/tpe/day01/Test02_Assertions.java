package com.tpe.day01;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Test02_Assertions {
    /**
     * method to assertFalse /assertTrue
     * assertFalse(false) ==> Pass
     * assertTrue(true) ==> Pass
     */
    @Test
    void testToFindMin(){
        assertFalse(Math.min(23, 134) != 23);
    }

    /**
     * test .split
     */
    @Test
    void testArray(){
        String str = "Unit testing improves code quality";
        String[] actualValue = str.split(" ");
        String[] expectedValue = {"Unit", "testing", "improves", "code", "quality"};
        assertTrue(Arrays.equals(expectedValue,actualValue));
    }
}
