package com.automation.qa.numbers;

/**
 * ============================================================================
 * INTERVIEW QUESTION: Swap Two Numbers Without a Third Variable
 * ============================================================================
 * Given two integer variables:
 * int a = 15, b = 25;
 * Swap their values without declaring any third/temp variable.
 *
 * APPROACHES:
 * 1. Arithmetic Addition & Subtraction:
 *    a = a + b;  // sum
 *    b = a - b;  // (a + b) - b = a
 *    a = a - b;  // (a + b) - a = b
 *    TRAP: If a and b are very large (e.g. near Integer.MAX_VALUE), a + b causes
 *          integer overflow!
 *
 * 2. Bitwise XOR Approach (Best Approach):
 *    a = a ^ b;
 *    b = a ^ b;  // (a ^ b) ^ b = a
 *    a = a ^ b;  // (a ^ b) ^ a = b
 *    ADVANTAGE: Never suffers from integer overflow!
 */
public class SwapNumbersWithoutTemp {

    public static void swapUsingArithmetic(int a, int b) {
        System.out.println("--- Arithmetic Swap ---");
        System.out.println("Before: a = " + a + ", b = " + b);

        a = a + b;
        b = a - b;
        a = a - b;

        System.out.println("After:  a = " + a + ", b = " + b);
    }

    public static void swapUsingXOR(int a, int b) {
        System.out.println("--- Bitwise XOR Swap (Optimal) ---");
        System.out.println("Before: a = " + a + ", b = " + b);

        a = a ^ b;
        b = a ^ b;
        a = a ^ b;

        System.out.println("After:  a = " + a + ", b = " + b);
    }

    public static void main(String[] args) {
        int x = 15;
        int y = 25;

        swapUsingArithmetic(x, y);
        System.out.println();
        swapUsingXOR(x, y);
    }
}
