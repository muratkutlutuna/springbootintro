package com.tpe.day02;

import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertTimeout;

public class Test01_PerformanceTest {

    //Test method performance which will print some integer on screen
    @Test
    void testPrintPerformance(){
        assertTimeout(Duration.ofSeconds(1),
                ()-> IntStream.rangeClosed(1,100000).forEach(
                        System.out::println
                )
                );
    }

}
