package com.automation.qa.streams;

import java.util.*;
import java.util.stream.Collectors;

/**
 * ============================================================================
 * INTERVIEW TOPIC: Java 8+ Streams for Automation QA / SDET
 * ============================================================================
 * Modern QA interviews evaluate functional programming with Java 8 Streams:
 * - filter(): Selecting tests by status or environment
 * - map(): Transforming test objects to names, IDs, or durations
 * - collect(groupingBy()): Grouping results by Suite, Browser, or Status
 * - summaryStatistics(): Calculating total, min, max, and average execution times
 * - anyMatch() / allMatch(): Verifying test suite assertions
 */
public class Java8StreamsForQA {

    public enum Status { PASSED, FAILED, SKIPPED }

    public static class TestExecution {
        private final String testId;
        private final String testName;
        private final String module;
        private final Status status;
        private final double durationSeconds;

        public TestExecution(String testId, String testName, String module, Status status, double durationSeconds) {
            this.testId = testId;
            this.testName = testName;
            this.module = module;
            this.status = status;
            this.durationSeconds = durationSeconds;
        }

        public String getTestId() { return testId; }
        public String getTestName() { return testName; }
        public String getModule() { return module; }
        public Status getStatus() { return status; }
        public double getDurationSeconds() { return durationSeconds; }

        @Override
        public String toString() {
            return String.format("[%s] %-30s | %-12s | %-7s | %.2fs",
                    testId, testName, module, status, durationSeconds);
        }
    }

    public static void main(String[] args) {
        List<TestExecution> runResults = List.of(
                new TestExecution("TC-01", "Login With Valid User", "Auth", Status.PASSED, 2.3),
                new TestExecution("TC-02", "Login With Locked User", "Auth", Status.PASSED, 1.8),
                new TestExecution("TC-03", "Add Product To Cart", "Checkout", Status.PASSED, 3.5),
                new TestExecution("TC-04", "Apply Discount Coupon", "Checkout", Status.FAILED, 2.1),
                new TestExecution("TC-05", "Credit Card Payment", "Payment", Status.FAILED, 5.7),
                new TestExecution("TC-06", "Paypal Checkout", "Payment", Status.SKIPPED, 0.0),
                new TestExecution("TC-07", "Order Confirmation Email", "Notification", Status.PASSED, 4.2),
                new TestExecution("TC-08", "Search By Keyword", "Search", Status.PASSED, 1.2)
        );

        System.out.println("=== 1. Filter: Get Only FAILED Tests ===");
        List<TestExecution> failedTests = runResults.stream()
                .filter(t -> t.getStatus() == Status.FAILED)
                .collect(Collectors.toList());
        failedTests.forEach(System.out::println);

        System.out.println("\n=== 2. Map: Extract Names of All Passed Tests ===");
        List<String> passedTestNames = runResults.stream()
                .filter(t -> t.getStatus() == Status.PASSED)
                .map(TestExecution::getTestName)
                .collect(Collectors.toList());
        passedTestNames.forEach(name -> System.out.println("- " + name));

        System.out.println("\n=== 3. GroupingBy: Count Tests By Status ===");
        Map<Status, Long> statusCounts = runResults.stream()
                .collect(Collectors.groupingBy(TestExecution::getStatus, Collectors.counting()));
        statusCounts.forEach((status, count) -> System.out.println(status + ": " + count));

        System.out.println("\n=== 4. GroupingBy: Group Tests By Module ===");
        Map<String, List<TestExecution>> testsByModule = runResults.stream()
                .collect(Collectors.groupingBy(TestExecution::getModule));
        testsByModule.forEach((mod, tests) -> {
            System.out.println("[" + mod + "]: " + tests.size() + " tests");
        });

        System.out.println("\n=== 5. Max: Find the Slowest Executing Test ===");
        runResults.stream()
                .max(Comparator.comparingDouble(TestExecution::getDurationSeconds))
                .ifPresent(slowest -> System.out.println("Slowest Test: " + slowest));

        System.out.println("\n=== 6. SummaryStatistics: Total & Average Suite Duration ===");
        DoubleSummaryStatistics stats = runResults.stream()
                .mapToDouble(TestExecution::getDurationSeconds)
                .summaryStatistics();
        System.out.printf("Total Duration: %.2fs | Avg Duration: %.2fs | Max: %.2fs\n",
                stats.getSum(), stats.getAverage(), stats.getMax());

        System.out.println("\n=== 7. anyMatch / allMatch Assertions ===");
        boolean anyFailures = runResults.stream().anyMatch(t -> t.getStatus() == Status.FAILED);
        boolean allPassed = runResults.stream().allMatch(t -> t.getStatus() == Status.PASSED);
        System.out.println("Were there any test failures? " + anyFailures); // true
        System.out.println("Did every test pass?          " + allPassed);   // false
    }
}
