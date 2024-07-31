package com.tpe.day02ForTest;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class RemoveAFromFirstTwoCharsTest {
    RemoveAFromFirstTwoChars obj;

    @BeforeEach
    void setUp() {
        obj = new RemoveAFromFirstTwoChars();
        System.out.println("BeforeEach is executed");
    }

    @AfterEach
    void tearDown() {
    }

    @ParameterizedTest
    @CsvSource({
            "BA, AABA",
            "BC, AABC",
            "BCC, BACC",
            "'', A",
            "DFG, DFG"
    })
    void removeAFromFirstTwoChars(String expected, String actual) {
        assertEquals(expected, obj.removeAFromFirstTwoChars(actual));
    }
}