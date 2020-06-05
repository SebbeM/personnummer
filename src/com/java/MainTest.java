package com.java;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class MainTest {

    String[] validPersonals = {"201701102384",
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

    String[] invalidPersonals = {"201701272394",
        "190302299813"};

    String[] meanStrings = {"",
        "hello",
        "-1",
        "0",
        "" + Integer.MAX_VALUE,
        "" + Integer.MAX_VALUE + 1,
        "1" + Integer.MAX_VALUE,
        "+",
        "-"};

    String[] validCoordinations = {"190910799824"};

    String[] validOrganizations = {"556614-3185",
        "16556601-6399",
        "262000-1111",
        "857202-7566"};

    @Test
    public void testValids() {
        for (String s: validPersonals) {
            Assertions.assertTrue(Main.validityCheck(s));
        }
    }

    @Test
    public void testInvalids() {
        for (String s:invalidPersonals) {
            System.out.println(s);
            Assertions.assertFalse(Main.validityCheck(s));
        }
    }

}