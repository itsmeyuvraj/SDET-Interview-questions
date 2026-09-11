package com.automation.qa.numbers;

import java.util.ArrayList;
import java.util.List;

/**
 * ============================================================================
 * INTERVIEW QUESTION: Fibonacci Series
 * ============================================================================
 * The Fibonacci sequence is: 0, 1, 1, 2, 3, 5, 8, 13, 21, 34, ...
 * Formula: F(n) = F(n-1) + F(n-2) with F(0) = 0, F(1) = 1.
 *
 * WHY ASKED IN QA INTERVIEWS:
 * - Checks recursion vs iteration tradeoff.
 * - Naive recursion has an exponential time complexity of O(2^n) and causes StackOverflowError.
 * - Iterative approach runs in O(n) time and O(1) space!
 */
public class FibonacciSeries {

    /**
     * Approach 1: Iterative generation of first N Fibonacci numbers.
     * Time: O(n), Space: O(1) auxiliary.
     */
    public static List<Long> generateFibonacci(int count) {
        List<Long> series = new ArrayList<>();
        if (count <= 0) return series;

        long first = 0;
        long second = 1;

        series.add(first);
        if (count == 1) return series;

        series.add(second);

        for (int i = 2; i < count; i++) {
            long next = first + second;
            series.add(next);
            first = second;
            second = next;
        }

        return series;
    }

    /**
     * Approach 2: Find Nth Fibonacci number iteratively (0-indexed).
     * Time: O(n), Space: O(1).
     */
    public static long getNthFibonacci(int n) {
        if (n <= 0) return 0;
        if (n == 1) return 1;

        long prev2 = 0;
        long prev1 = 1;
        long current = 0;

        for (int i = 2; i <= n; i++) {
            current = prev1 + prev2;
            prev2 = prev1;
            prev1 = current;
        }

        return current;
    }

    /**
     * Approach 3: Recursive with Memoization.
     * Prevents exponential O(2^n) runtime by caching calculated values.
     */
    public static long getNthFibonacciMemo(int n, Long[] memo) {
        if (n <= 0) return 0;
        if (n == 1) return 1;

        if (memo[n] != null) {
            return memo[n];
        }

        memo[n] = getNthFibonacciMemo(n - 1, memo) + getNthFibonacciMemo(n - 2, memo);
        return memo[n];
    }

    public static void main(String[] args) {
        int n = 10;
        System.out.println("First " + n + " Fibonacci numbers:");
        System.out.println(generateFibonacci(n));

        int targetIndex = 15;
        System.out.println("\n15th Fibonacci number (Iterative): " + getNthFibonacci(targetIndex));

        Long[] memo = new Long[targetIndex + 1];
        System.out.println("15th Fibonacci number (Memoized):  " + getNthFibonacciMemo(targetIndex, memo));
    }
}
