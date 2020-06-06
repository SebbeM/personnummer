package com.java;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class MainTest {

    String[] validPersonals = {
            "201701102384",
            "141206-2380",
            "20080903-2386",
            "7101169295",
            "198107249289",
            "19021214-9819",
            "190910199827",
            "191006089807",
            "192109099180",
            "4607137454",
            "194510168885",
            "900118+9811",
            "189102279800",
            "189912299816"};

    String[] invalidPersonals = {"201701272394", "190302299813"};

    String[] meanStrings = {
            "",
            "hello",
            "-1",
            "0",
            "" + Integer.MAX_VALUE,
            "" + Integer.MAX_VALUE + 1,
            "1" + Integer.MAX_VALUE,
            "+",
            "-"};

    String[] validCoordinations = {"190910799824"};

    String[] validOrganizations = {
            "556614-3185",
            "16556601-6399",
            "262000-1111",
            "857202-7566"};

    @Test
    public void testValids() {
        testArrays(validPersonals, true);
    }

    @Test
    public void testInvalids() {
        testArrays(invalidPersonals, false);
    }

    @Test
    public void testCoordinations() {
        testArrays(validCoordinations, true);
    }

    @Test
    public void testOrganizations() {
        testArrays(validOrganizations, true);
    }

    private void testArrays(String[] array, boolean assertion) {
        for (String s : array) {
            boolean result = Main.validityCheck(s);
            if (assertion == true) {
                Assertions.assertTrue(result);
            } else {
                Assertions.assertFalse(result);
            }
        }
    }

}