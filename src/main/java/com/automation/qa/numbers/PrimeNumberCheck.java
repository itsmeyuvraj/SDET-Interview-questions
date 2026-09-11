package com.automation.qa.numbers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * ============================================================================
 * INTERVIEW QUESTION: Prime Number Check & Sieve of Eratosthenes
 * ============================================================================
 * A prime number is a natural number greater than 1 that has no positive divisors
 * other than 1 and itself (e.g. 2, 3, 5, 7, 11, 13, 17...).
 *
 * QUESTIONS ASKED:
 * 1. "Check if a given number is prime."
 * 2. "Print all prime numbers up to N."
 *
 * OPTIMIZATION (Why check only up to sqrt(n)?):
 * - If n = a * b, one factor must be <= sqrt(n) and the other >= sqrt(n).
 * - Checking up to sqrt(n) reduces time complexity from O(n) to O(sqrt(n)).
 *
 * EDGE CASES:
 * - Numbers <= 1 are NOT prime.
 * - 2 is the ONLY even prime number.
 * - Negative numbers are not prime.
 */
public class PrimeNumberCheck {

    /**
     * Checks if a single number is prime in O(sqrt(n)) time.
     */
    public static boolean isPrime(int n) {
        if (n <= 1) return false;
        if (n <= 3) return true; // 2 and 3 are prime

        // Divisible by 2 or 3 can be checked immediately
        if (n % 2 == 0 || n % 3 == 0) return false;

        // Check divisors of form 6k +/- 1 up to sqrt(n)
        for (int i = 5; (long) i * i <= n; i += 6) {
            if (n % i == 0 || n % (i + 2) == 0) {
                return false;
            }
        }

        return true;
    }

    /**
     * Sieve of Eratosthenes: Efficiently finds ALL primes up to N.
     * Time Complexity: O(N log log N) - substantially faster than checking each number.
     */
    public static List<Integer> findPrimesUpToN(int n) {
        List<Integer> primes = new ArrayList<>();
        if (n < 2) return primes;

        boolean[] isPrime = new boolean[n + 1];
        Arrays.fill(isPrime, true);
        isPrime[0] = false;
        isPrime[1] = false;

        for (int p = 2; (long) p * p <= n; p++) {
            if (isPrime[p]) {
                for (int i = p * p; i <= n; i += p) {
                    isPrime[i] = false;
                }
            }
        }

        for (int i = 2; i <= n; i++) {
            if (isPrime[i]) {
                primes.add(i);
            }
        }

        return primes;
    }

    public static void main(String[] args) {
        int[] testNumbers = {1, 2, 3, 4, 17, 25, 29, 97, 100};

        System.out.println("=== Prime Number Check (O(sqrt(n))) ===");
        for (int num : testNumbers) {
            System.out.println(num + " is prime? " + isPrime(num));
        }

        System.out.println("\n=== All Primes Up To 50 (Sieve of Eratosthenes) ===");
        System.out.println(findPrimesUpToN(50));
    }
}
