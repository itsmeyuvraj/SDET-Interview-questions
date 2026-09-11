package com.automation.qa.sdetpatterns;

/**
 * ============================================================================
 * INTERVIEW QUESTION: Singleton Pattern & ThreadLocal Driver for Parallel Tests
 * ============================================================================
 * Why it is asked in QA / SDET interviews:
 * 1. "How do you manage WebDriver instances in your framework?"
 * 2. "Explain the Singleton design pattern and why a basic Singleton FAILS
 *    in parallel test execution."
 * 3. "How do you ensure thread safety when running tests in parallel?"
 *
 * KEY CONCEPTS:
 * 1. Classic Double-Checked Locking Singleton:
 *    - Private constructor (prevents external `new`).
 *    - Private static volatile instance.
 *    - Public static getInstance() with synchronized block.
 *
 * 2. ThreadLocal WebDriver Pattern (Industry Standard for SDET):
 *    - A single global WebDriver instance causes race conditions when tests run in parallel.
 *    - `ThreadLocal<WebDriver>` gives each running thread its own isolated driver copy!
 */
public class SingletonWebDriverDemo {

    // ========================================================================
    // Part 1: Double-Checked Locking Singleton (Standard Design Pattern)
    // ========================================================================
    public static class FrameworkConfigManager {
        // Volatile prevents instruction reordering in JVM
        private static volatile FrameworkConfigManager instance;
        private final String environment = "QA-STAGING";

        // Private constructor prevents direct instantiation
        private FrameworkConfigManager() {
            System.out.println("[ConfigManager] Initializing framework configuration...");
        }

        public static FrameworkConfigManager getInstance() {
            if (instance == null) { // First check (no lock)
                synchronized (FrameworkConfigManager.class) {
                    if (instance == null) { // Second check (with lock)
                        instance = new FrameworkConfigManager();
                    }
                }
            }
            return instance;
        }

        public String getEnvironment() {
            return environment;
        }
    }

    // ========================================================================
    // Part 2: ThreadLocal Driver Manager (SDET Solution for Parallel Testing)
    // ========================================================================
    public static class DriverManager {
        // ThreadLocal container guarantees one isolated driver instance per thread
        private static final ThreadLocal<String> threadLocalDriver = new ThreadLocal<>();

        public static void setDriver(String browserName) {
            threadLocalDriver.set(browserName + "-Driver-" + Thread.currentThread().getName());
        }

        public static String getDriver() {
            return threadLocalDriver.get();
        }

        public static void quitDriver() {
            System.out.println("[DriverManager] Quitting: " + threadLocalDriver.get());
            threadLocalDriver.remove(); // CRITICAL: Always remove to prevent memory leaks in thread pools
        }
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println("=== 1. Singleton Verification ===");
        FrameworkConfigManager c1 = FrameworkConfigManager.getInstance();
        FrameworkConfigManager c2 = FrameworkConfigManager.getInstance();
        System.out.println("Are both instances the same? " + (c1 == c2)); // true
        System.out.println("Environment: " + c1.getEnvironment());

        System.out.println("\n=== 2. Parallel Testing Simulation with ThreadLocal ===");
        // Simulate two parallel test threads (e.g. running in TestNG or JUnit parallel)
        Thread testThread1 = new Thread(() -> {
            DriverManager.setDriver("Chrome");
            System.out.println("Test 1 using: " + DriverManager.getDriver());
            DriverManager.quitDriver();
        });

        Thread testThread2 = new Thread(() -> {
            DriverManager.setDriver("Firefox");
            System.out.println("Test 2 using: " + DriverManager.getDriver());
            DriverManager.quitDriver();
        });

        testThread1.start();
        testThread2.start();

        testThread1.join();
        testThread2.join();
        System.out.println("Parallel test simulation finished cleanly!");
    }
}
