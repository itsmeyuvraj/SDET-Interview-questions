package com.automation.qa.sdetpatterns;

import java.util.function.Supplier;

/**
 * ============================================================================
 * INTERVIEW QUESTION: Implementing a Custom Retry Mechanism in Java
 * ============================================================================
 * Why it is asked in QA / SDET interviews:
 * 1. Flaky tests are the #1 challenge in test automation (stale elements, network lag,
 *    eventual consistency in microservices).
 * 2. Interviewers frequently ask: "How would you design a retry runner or custom wait
 *    mechanism in pure Java without using external libraries?"
 *
 * KEY CONCEPTS DEMONSTRATED:
 * - Functional interfaces (`Supplier<T>`, `Runnable`).
 * - Configurable maximum attempts and backoff delay.
 * - Catching transient exceptions while letting non-recoverable ones propagate.
 */
public class RetryMechanism {

    /**
     * Executes a given supplier action with retry logic.
     *
     * @param action       The test action to execute (e.g. clicking a button, querying an API)
     * @param maxAttempts  Maximum number of retry attempts
     * @param delayMillis  Wait time in milliseconds between retries
     * @param <T>          Return type of the action
     * @return Result of the action if successful
     * @throws RuntimeException If action fails on all retry attempts
     */
    public static <T> T executeWithRetry(Supplier<T> action, int maxAttempts, long delayMillis) {
        int attempt = 0;
        Exception lastException = null;

        while (attempt < maxAttempts) {
            attempt++;
            try {
                System.out.println("[RetryRunner] Executing action (Attempt " + attempt + " of " + maxAttempts + ")...");
                return action.get();
            } catch (Exception ex) {
                lastException = ex;
                System.out.println("[RetryRunner] Attempt " + attempt + " failed with: " + ex.getMessage());

                if (attempt < maxAttempts) {
                    try {
                        System.out.println("[RetryRunner] Waiting " + delayMillis + "ms before next retry...");
                        Thread.sleep(delayMillis);
                    } catch (InterruptedException ie) {
                        Thread.currentThread().interrupt();
                        throw new RuntimeException("Thread interrupted during retry backoff", ie);
                    }
                }
            }
        }

        throw new RuntimeException("Action failed after " + maxAttempts + " attempts.", lastException);
    }

    public static void main(String[] args) {
        System.out.println("=== Simulating a Flaky API / UI Call That Succeeds on Attempt 3 ===");

        // Flaky service simulator
        class FlakyService {
            int calls = 0;
            String fetchData() {
                calls++;
                if (calls < 3) {
                    throw new RuntimeException("503 Service Unavailable: Network blip on call #" + calls);
                }
                return "200 OK: Data retrieved successfully!";
            }
        }

        FlakyService service = new FlakyService();

        String result = executeWithRetry(
                service::fetchData,
                4,      // maxAttempts
                500     // delayMillis (0.5 second)
        );

        System.out.println("\nFinal Result: " + result);
    }
}
