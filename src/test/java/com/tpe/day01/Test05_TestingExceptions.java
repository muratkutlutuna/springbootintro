package com.tpe.day01;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * We need to test if we get expected exceptions
 */
public class Test05_TestingExceptions {

    @Test
    void testExceptions(){
        String str = "JUnit is a framework to test JAVA code";
        String str2 = null;
        assertThrows(NullPointerException.class,()->{
            System.out.println("Test Exception is executed");
            str2.length();
        });
    }
    @Test
    void testExeptions2(){
        int num1 = 4;
        int num2 = 0;
        assertThrows(ArithmeticException.class,()-> System.out.println(num1/num2));
    }

    //Instead of ArithmeticException if we use RuntimeException it also pass, because
    //RuntimeException is a parent class of ArithmeticException
    //but we should test more specific exceptions

}
