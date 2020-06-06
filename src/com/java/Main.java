package com.java;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        while (true) {
            System.out.println("Please enter a number to check for validity.");
            String s = scan.next();
            boolean valid = false;
            if (s.equalsIgnoreCase("q")) {
                return;
            }
            if (validityCheck(s)) {
                System.out.println("The provided number IS a valid personal identification number.");
            } else {
                System.out.println("The provided number IS NOT a valid personal identification number.");
            }
        }
    }

    static boolean validityCheck(String s) {
        if (isControlValid(s) && isDateValid(s)) {
            return true;
        } else {
            System.out.println(s);
            return false;
        }
    }

    /**
     * Turns Strings of different formats into YYMMDDXXXX
     * @param s
     * @return
     */
    static String trim(String s) {
        s = s.replaceAll("[+,-]", ""); // Remove delimiters
        if (s.length() == 12) {
            s = s.substring(2); // Ignore century digits
        } else if (s.length() < 10) {
            throw new IllegalArgumentException("Provided String too short");
        } else if (s.length() > 10) {
            throw new IllegalArgumentException("Provided String too long");
        }
        return s;
    }

    /**
     * Checks whether the control digit is correct
     * @param s
     * @return true if control digit is correct, otherwise false
     */
    static boolean isControlValid (String s) {
        s = trim(s);
        ArrayList<Integer> numbers =  new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            int value = Character.getNumericValue(s.charAt(i));
            int doubled = value * (i % 2 == 0 ? 2 : 1);
            char[] digits = Integer.toString(doubled).toCharArray();
            for (char c : digits) {
                numbers.add(Character.getNumericValue(c));
            }
        }
        int sum = 0;
        for (int n : numbers) {
            sum += n;
        }
        int control = (10 - (sum % 10)) % 10;
        return control == Character.getNumericValue(s.charAt(9));
    }

    /**
     * Checks whether the date is valid
     * @param s
     * @return true if date is valid, otherwise false
     */
    static boolean isDateValid(String s) {
        String birthYear, birthMonth, birthDay;
        int currentYear = LocalDate.now().getYear();
        int currentMonth = LocalDate.now().getMonth().getValue();
        int currentDay = LocalDate.now().getDayOfMonth();

        if (s.length() > 11) { // Century is specified
            birthYear = s.substring(0, 4);
            birthMonth = s.substring(4, 6);
            birthDay = s.substring(6, 8);
        } else { // Century is not specified
            birthYear = s.substring(0, 2);
            birthMonth = s.substring(2, 4);
            birthDay = s.substring(4, 6);
            int currentCentury = currentYear / 100;
            if (Integer.parseInt(birthYear) > currentYear
                    && Integer.parseInt(birthMonth) > currentMonth
                    && Integer.parseInt(birthDay) > currentDay) {
                birthYear = currentCentury - (s.contains("+") ? 2 : 1) + birthYear;
            } else {
                birthYear = currentCentury - (s.contains("+") ? 1 : 0) + birthYear;
            }
        }

        if (Integer.parseInt(birthDay) > 60) { // String is a coordination number
            birthDay = "" + (Integer.parseInt(birthDay) - 60);
        }

        try {
            LocalDate.parse(birthYear + "-" + birthMonth + "-" + birthDay);
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }
}
