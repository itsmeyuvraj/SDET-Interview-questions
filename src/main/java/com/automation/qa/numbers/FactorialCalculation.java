package com.automation.qa.numbers;

import java.math.BigInteger;

/**
 * ============================================================================
 * INTERVIEW QUESTION: Factorial Calculation (Iterative, Recursive, BigInteger)
 * ============================================================================
 * Factorial of non-negative integer n is: n! = n * (n-1) * ... * 1
 * Edge case: 0! = 1.
 *
 * CRITICAL INTERVIEW DISCUSSION: Integer Overflow!
 * - 12! fits in standard 32-bit `int` (479,001,600).
 * - 13! overflows 32-bit `int`!
 * - 20! fits in 64-bit `long` (2,432,902,008,176,640,000).
 * - 21! overflows `long`!
 * - For n > 20, ALWAYS demonstrate using java.math.BigInteger!
 */
public class FactorialCalculation {

    /**
     * Approach 1: Iterative using standard long (up to n = 20)
     */
    public static long factorialIterative(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Factorial is not defined for negative numbers");
        }
        if (n > 20) {
            throw new ArithmeticException("n > 20 causes long overflow. Use calculateBigFactorial instead.");
        }

        long result = 1;
        for (int i = 2; i <= n; i++) {
            result *= i;
        }
        return result;
    }

    /**
     * Approach 2: Recursive approach
     */
    public static long factorialRecursive(int n) {
        if (n < 0) throw new IllegalArgumentException("Negative input not allowed");
        if (n <= 1) return 1;
        return n * factorialRecursive(n - 1);
    }

    /**
     * Approach 3: BigInteger for arbitrary precision (n > 20)
     * Demonstrates senior Java knowledge in interview.
     */
    public static BigInteger calculateBigFactorial(int n) {
        if (n < 0) throw new IllegalArgumentException("Negative input not allowed");

        BigInteger result = BigInteger.ONE;
        for (int i = 2; i <= n; i++) {
            result = result.multiply(BigInteger.valueOf(i));
        }
        return result;
    }

    public static void main(String[] args) {
        int n = 5;
        System.out.println(n + "! (Iterative): " + factorialIterative(n)); // 120
        System.out.println(n + "! (Recursive): " + factorialRecursive(n)); // 120

        int bigN = 30;
        System.out.println("\nHandling Overflow with BigInteger for " + bigN + "!:");
        System.out.println(bigN + "! = " + calculateBigFactorial(bigN));
    }
}
