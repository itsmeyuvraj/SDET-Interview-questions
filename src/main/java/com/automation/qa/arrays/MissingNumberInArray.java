package com.automation.qa.arrays;

import java.util.Arrays;

/**
 * ============================================================================
 * INTERVIEW QUESTION: Find the Missing Number in an Array (1 to N)
 * ============================================================================
 * You are given an array containing (n - 1) distinct integers in the range [1, n].
 * One integer is missing. Find the missing integer.
 *
 * Example:
 *   Input:  arr = [1, 2, 4, 6, 3, 7, 8], n = 8
 *   Output: 5
 *
 * APPROACHES:
 * 1. Mathematical Sum Formula:
 *    Expected Sum = n * (n + 1) / 2
 *    Missing Number = Expected Sum - Actual Sum
 *    Time: O(n), Space: O(1)
 *    Caution: For large n, sum can exceed Integer.MAX_VALUE! Use `long`.
 *
 * 2. XOR Bitwise Approach (Interviewer Favorite - No Overflow!):
 *    XOR of any number with itself is 0 (a ^ a = 0)
 *    XOR of any number with 0 is the number itself (a ^ 0 = a)
 *    XOR all numbers from 1 to n, then XOR with every array element.
 *    All matching numbers cancel out to 0, leaving ONLY the missing number!
 *    Time: O(n), Space: O(1), Zero risk of integer overflow.
 */
public class MissingNumberInArray {

    /**
     * Approach 1: Mathematical formula (uses long to prevent arithmetic overflow)
     */
    public static int findMissingUsingSum(int[] arr, int n) {
        long expectedSum = (long) n * (n + 1) / 2;
        long actualSum = 0;

        for (int num : arr) {
            actualSum += num;
        }

        return (int) (expectedSum - actualSum);
    }

    /**
     * Approach 2: XOR Bitwise Approach (Optimal, guarantees NO integer overflow)
     */
    public static int findMissingUsingXOR(int[] arr, int n) {
        int xor1 = 0;
        int xor2 = 0;

        // XOR all numbers from 1 to n
        for (int i = 1; i <= n; i++) {
            xor1 ^= i;
        }

        // XOR all elements in the array
        for (int num : arr) {
            xor2 ^= num;
        }

        // The remaining value is the missing number
        return xor1 ^ xor2;
    }

    public static void main(String[] args) {
        int[] numbers = {1, 2, 4, 6, 3, 7, 8};
        int n = 8; // range from 1 to 8

        System.out.println("Array: " + Arrays.toString(numbers) + " (Range 1 to " + n + ")");
        System.out.println("Missing (Formula): " + findMissingUsingSum(numbers, n)); // 5
        System.out.println("Missing (XOR):     " + findMissingUsingXOR(numbers, n)); // 5

        int[] test2 = {2, 3, 1, 5};
        int n2 = 5;
        System.out.println("\nArray: " + Arrays.toString(test2) + " (Range 1 to " + n2 + ")");
        System.out.println("Missing (XOR):     " + findMissingUsingXOR(test2, n2)); // 4
    }
}
