package com.automation.qa.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * ============================================================================
 * INTERVIEW QUESTION: How to Handle StaleElementReferenceException
 * ============================================================================
 * The #1 most asked Selenium troubleshooting question in SDET interviews!
 *
 * WHAT DOES "STALE" MEAN?
 * - A WebElement reference is "stale" when the element is no longer attached
 *   to the DOM of the page.
 * - Common causes:
 *   1. Page refreshed or navigated away.
 *   2. Single Page Applications (React/Angular/Vue) re-rendered the DOM component via AJAX.
 *   3. JavaScript dynamically removed and recreated the element node.
 *
 * 4 PROVEN STRATEGIES TO RESOLVE IT:
 * 1. Re-locating the element just-in-time before interacting.
 * 2. Retry Loop with try-catch block (robust helper method).
 * 3. `ExpectedConditions.refreshed(...)` with `WebDriverWait`.
 * 4. In Page Factory: Avoid `@CacheLookup` on dynamic elements.
 */
public class StaleElementHandlingStrategies {

    /**
     * Strategy 1: Retry Loop with Try-Catch
     * Automatically attempts to re-locate and click the element up to maxAttempts times.
     */
    public static boolean clickWithStaleRetry(WebDriver driver, By locator, int maxAttempts) {
        int attempts = 0;
        while (attempts < maxAttempts) {
            try {
                driver.findElement(locator).click();
                System.out.println("Clicked element on attempt #" + (attempts + 1));
                return true;
            } catch (StaleElementReferenceException ex) {
                attempts++;
                System.out.println("Caught StaleElementReferenceException. Retrying attempt #" + (attempts + 1) + "...");
            }
        }
        throw new RuntimeException("Failed to click element [" + locator + "] due to persistent StaleElementReferenceException.");
    }

    /**
     * Strategy 2: Using ExpectedConditions.refreshed(...) with WebDriverWait
     * Instructs WebDriverWait to re-query the element from the DOM if it becomes stale.
     */
    public static WebElement waitForRefreshedElementClickable(WebDriver driver, By locator, int timeoutSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));

        // ExpectedConditions.refreshed wraps any condition and re-evaluates if stale
        return wait.until(ExpectedConditions.refreshed(
                ExpectedConditions.elementToBeClickable(locator)
        ));
    }

    /**
     * Strategy 3: Safe text retrieval after DOM updates
     */
    public static String getTextWithStaleRetry(WebDriver driver, By locator, int maxAttempts) {
        for (int i = 0; i < maxAttempts; i++) {
            try {
                return driver.findElement(locator).getText();
            } catch (StaleElementReferenceException e) {
                try {
                    Thread.sleep(250); // brief pause for DOM stabilization
                } catch (InterruptedException ignored) {}
            }
        }
        return driver.findElement(locator).getText();
    }

    public static void main(String[] args) {
        System.out.println("=== StaleElementReferenceException Key Takeaways ===");
        System.out.println("1. Cause: DOM node was detached/recreated after reference was captured.");
        System.out.println("2. Fix 1: Re-fetch element using driver.findElement(locator) just before action.");
        System.out.println("3. Fix 2: Wrap action in a retry loop (2-3 attempts) catching StaleElementReferenceException.");
        System.out.println("4. Fix 3: Use ExpectedConditions.refreshed(ExpectedConditions.elementToBeClickable(locator)).");
        System.out.println("5. Page Factory warning: Never put @CacheLookup on dynamic/AJAX elements!");
    }
}
