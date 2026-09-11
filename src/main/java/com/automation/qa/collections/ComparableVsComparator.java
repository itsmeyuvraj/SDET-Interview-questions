package com.automation.qa.collections;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * ============================================================================
 * INTERVIEW QUESTION: Comparable vs Comparator in Java
 * ============================================================================
 * One of the absolute top core Java questions in SDET / Automation interviews!
 *
 * KEY DIFFERENCES TO STATE IN THE INTERVIEW:
 * 1. Package:
 *    - Comparable belongs to `java.lang`.
 *    - Comparator belongs to `java.util`.
 * 2. Method:
 *    - Comparable requires overriding `compareTo(T o)`. (Single parameter)
 *    - Comparator requires overriding `compare(T o1, T o2)`. (Two parameters)
 * 3. Purpose / Modification:
 *    - Comparable provides natural ordering and modifies the original class.
 *    - Comparator provides custom, multiple sorting strategies without modifying
 *      the original class.
 *
 * SDET REAL-WORLD EXAMPLE:
 * Managing Automated Test Cases:
 * - Natural ordering: By Test Priority (P1 > P2 > P3).
 * - Custom ordering: By Execution Time (to identify slow tests for CI/CD optimization),
 *   or by Test Name.
 */
public class ComparableVsComparator {

    /**
     * TestCase class implementing Comparable for natural priority-based sorting.
     */
    public static class TestCase implements Comparable<TestCase> {
        private final String testId;
        private final String name;
        private final int priority; // 1 = highest, 3 = lowest
        private final double executionTimeSeconds;

        public TestCase(String testId, String name, int priority, double executionTimeSeconds) {
            this.testId = testId;
            this.name = name;
            this.priority = priority;
            this.executionTimeSeconds = executionTimeSeconds;
        }

        public String getTestId() { return testId; }
        public String getName() { return name; }
        public int getPriority() { return priority; }
        public double getExecutionTimeSeconds() { return executionTimeSeconds; }

        /**
         * Natural ordering: Higher priority tests run first (lower priority number).
         */
        @Override
        public int compareTo(TestCase other) {
            return Integer.compare(this.priority, other.priority);
        }

        @Override
        public String toString() {
            return String.format("[%s] %s | Priority: P%d | Duration: %.2fs",
                    testId, name, priority, executionTimeSeconds);
        }
    }

    public static void main(String[] args) {
        List<TestCase> testCases = new ArrayList<>();
        testCases.add(new TestCase("TC-101", "Verify Login With Valid Credentials", 1, 3.45));
        testCases.add(new TestCase("TC-102", "Verify Forgot Password Email Trigger", 3, 1.20));
        testCases.add(new TestCase("TC-103", "Verify Checkout Payment Gateway", 1, 8.75));
        testCases.add(new TestCase("TC-104", "Verify User Profile Avatar Upload", 2, 4.10));
        testCases.add(new TestCase("TC-105", "Verify Footer Navigation Links", 3, 0.85));

        System.out.println("=== 1. Natural Ordering (Comparable - Sorted by Priority) ===");
        Collections.sort(testCases);
        testCases.forEach(System.out::println);

        System.out.println("\n=== 2. Custom Ordering (Comparator - Slowest Tests First for CI Optimization) ===");
        Comparator<TestCase> bySlowestDuration = Comparator.comparingDouble(TestCase::getExecutionTimeSeconds).reversed();
        testCases.sort(bySlowestDuration);
        testCases.forEach(System.out::println);

        System.out.println("\n=== 3. Chained Comparator (Priority First, then Execution Time Ascending) ===");
        Comparator<TestCase> chainedComparator = Comparator
                .comparingInt(TestCase::getPriority)
                .thenComparing(TestCase::getExecutionTimeSeconds);
        testCases.sort(chainedComparator);
        testCases.forEach(System.out::println);
    }
}
