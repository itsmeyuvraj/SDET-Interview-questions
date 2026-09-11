package com.automation.qa.sdetpatterns;

/**
 * ============================================================================
 * INTERVIEW QUESTION: Custom Exceptions in Automation Frameworks
 * ============================================================================
 * Why it is asked in QA / SDET interviews:
 * 1. "How do you handle exceptions in your test framework?"
 * 2. "Checked vs Unchecked Exceptions: which do you use for custom test exceptions?"
 *    Answer: Unchecked (`RuntimeException`) because test framework code shouldn't
 *    force callers to declare `throws` on every step method in page objects.
 * 3. Exception chaining: Passing the root cause (`Throwable cause`) to retain
 *    the original stack trace.
 */
public class CustomExceptionDemo {

    /**
     * Custom Unchecked Exception for Test Framework errors.
     */
    public static class AutomationFrameworkException extends RuntimeException {
        public AutomationFrameworkException(String message) {
            super(message);
        }

        public AutomationFrameworkException(String message, Throwable cause) {
            super(message, cause);
        }
    }

    /**
     * Specific Custom Exception for Element Wait Timeouts.
     */
    public static class ElementWaitTimeoutException extends AutomationFrameworkException {
        private final String locator;
        private final int timeoutSeconds;

        public ElementWaitTimeoutException(String locator, int timeoutSeconds) {
            super("Element with locator [" + locator + "] was not visible within " + timeoutSeconds + " seconds.");
            this.locator = locator;
            this.timeoutSeconds = timeoutSeconds;
        }

        public String getLocator() { return locator; }
        public int getTimeoutSeconds() { return timeoutSeconds; }
    }

    /**
     * Simulated test action that triggers custom exception
     */
    public static void waitForElementVisible(String locator, int timeoutSeconds) {
        System.out.println("[WebDriverWait] Waiting for element: " + locator + " for " + timeoutSeconds + "s...");
        // Simulate timeout occurrence
        boolean elementFound = false;

        if (!elementFound) {
            throw new ElementWaitTimeoutException(locator, timeoutSeconds);
        }
    }

    public static void main(String[] args) {
        System.out.println("=== Testing Custom Framework Exception Handling ===");

        try {
            waitForElementVisible("//button[@id='submit-order']", 15);
        } catch (ElementWaitTimeoutException ex) {
            System.err.println("\n[CAUGHT CUSTOM EXCEPTION]");
            System.err.println("Message:         " + ex.getMessage());
            System.err.println("Failed Locator:  " + ex.getLocator());
            System.err.println("Timeout Given:   " + ex.getTimeoutSeconds() + " seconds");
            System.out.println("\nCustom exception handled gracefully and ready for failure reporting!");
        }
    }
}
