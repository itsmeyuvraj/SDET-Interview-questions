package com.automation.qa.numbers;

import java.util.ArrayList;
import java.util.List;

/**
 * ============================================================================
 * INTERVIEW QUESTION: Armstrong Number (Narcissistic Number)
 * ============================================================================
 * An Armstrong number of order n is a number equal to the sum of its own digits
 * each raised to the power of the total number of digits n.
 *
 * Examples:
 * - 153 (3 digits): 1^3 + 5^3 + 3^3 = 1 + 125 + 27 = 153 -> TRUE
 * - 370 (3 digits): 3^3 + 7^3 + 0^3 = 27 + 343 + 0 = 370 -> TRUE
 * - 9474 (4 digits): 9^4 + 4^4 + 7^4 + 4^4 = 6561 + 256 + 2401 + 256 = 9474 -> TRUE
 *
 * ALGORITHM:
 * 1. Count the number of digits (n).
 * 2. Extract each digit using % 10.
 * 3. Add (digit ^ n) to running sum.
 * 4. Compare running sum to original number.
 */
public class ArmstrongNumber {

    /**
     * Checks if a number is an Armstrong number.
     */
    public static boolean isArmstrong(int number) {
        if (number < 0) return false;

        int original = number;
        int numDigits = String.valueOf(number).length();
        long sum = 0;

        int temp = number;
        while (temp > 0) {
            int digit = temp % 10;
            sum += Math.pow(digit, numDigits);
            temp /= 10;
        }

        return sum == original;
    }

    /**
     * Finds all Armstrong numbers in a given range [start, end].
     */
    public static List<Integer> findArmstrongNumbersInRange(int start, int end) {
        List<Integer> armstrongNumbers = new ArrayList<>();
        for (int i = start; i <= end; i++) {
            if (isArmstrong(i)) {
                armstrongNumbers.add(i);
            }
        }
        return armstrongNumbers;
    }

    public static void main(String[] args) {
        int[] testCases = {153, 370, 371, 9474, 123, 500};

        System.out.println("=== Armstrong Number Checks ===");
        for (int num : testCases) {
            System.out.println(num + " is Armstrong? " + isArmstrong(num));
        }

        System.out.println("\n=== Armstrong Numbers between 1 and 1000 ===");
        System.out.println(findArmstrongNumbersInRange(1, 1000));
    }
}
