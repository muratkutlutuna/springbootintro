package com.tpe.day01;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * if we want to repeat test for several time
 */
public class Test07_RepeatMultipleTimes {

    /**
     * to repeat test 4 times
     */
    @RepeatedTest(4)
    void testSubstring(){
        assertEquals("Java", "Java is must".substring(0, 4));
    }

    Random random = new Random();

    @RepeatedTest(5)
    void testMathExactWithRandom(){
        int num1 = random.nextInt(10);
        int num2 = random.nextInt(10);

        assertEquals(num1 + num2, Math.addExact(num1, num2));
        System.out.println("num1: "+num1+" ; num2: "+num2);
    }

}
