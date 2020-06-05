package com.java;

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
        s = s.replaceAll("[+,-]", ""); // Remove delimiters
        if (s.length() == 12) {
            s = s.substring(2); // Ignore century digits
        } else if (s.length() != 10) {
            return false;
        }
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
}
