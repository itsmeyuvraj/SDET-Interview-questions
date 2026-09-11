package com.automation.qa.numbers;

/**
 * ============================================================================
 * INTERVIEW QUESTION: Palindrome Number (Without Converting to String)
 * ============================================================================
 * (LeetCode 9)
 *
 * Given an integer x, return true if x is a palindrome, and false otherwise.
 *
 * STRICT INTERVIEW CONSTRAINT:
 * "Solve this WITHOUT converting the integer to a String!"
 *
 * KEY INSIGHTS & EDGE CASES:
 * 1. Negative numbers (e.g. -121) are NEVER palindromes because of the '-' sign.
 * 2. Any number ending in 0 (except 0 itself) cannot be a palindrome (e.g. 10 -> 01 != 10).
 * 3. Extract digits mathematically using modulo (% 10) and division (/ 10).
 *
 * COMPLEXITY:
 * - Time: O(log10(n)) - Number of digits
 * - Space: O(1)
 */
public class PalindromeNumber {

    /**
     * Reverses the entire number mathematically and compares with original.
     */
    public static boolean isPalindrome(int x) {
        // Negative numbers and numbers ending in 0 (other than 0) are not palindromes
        if (x < 0 || (x % 10 == 0 && x != 0)) {
            return false;
        }

        int original = x;
        long reversed = 0;

        while (x > 0) {
            int digit = x % 10;
            reversed = reversed * 10 + digit;
            x /= 10;
        }

        return original == reversed;
    }

    /**
     * Optimal Solution: Reversing only HALF the number to avoid any chance of integer overflow.
     */
    public static boolean isPalindromeHalf(int x) {
        if (x < 0 || (x % 10 == 0 && x != 0)) {
            return false;
        }

        int revertedNumber = 0;
        // When x is smaller than revertedNumber, we have reached the middle
        while (x > revertedNumber) {
            revertedNumber = revertedNumber * 10 + x % 10;
            x /= 10;
        }

        // Even length: x == revertedNumber (e.g. 1221 -> x=12, reverted=12)
        // Odd length:  x == revertedNumber / 10 (e.g. 12321 -> x=12, reverted=123, middle digit discarded)
        return x == revertedNumber || x == revertedNumber / 10;
    }

    public static void main(String[] args) {
        int[] testCases = {121, -121, 10, 1221, 12321, 0, 1000021};

        for (int num : testCases) {
            System.out.println(num + " is palindrome? " + isPalindrome(num) +
                    " (Half-reversed check: " + isPalindromeHalf(num) + ")");
        }
    }
}
