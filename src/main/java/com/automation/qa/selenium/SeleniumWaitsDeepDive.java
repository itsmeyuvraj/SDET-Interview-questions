package com.automation.qa.selenium;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * ============================================================================
 * INTERVIEW QUESTION: Implicit Wait vs Explicit Wait vs Fluent Wait in Selenium
 * ============================================================================
 * Asked in 100% of Selenium QA / SDET interviews!
 *
 * CRITICAL INTERVIEW POINTS TO EMPHASIZE:
 * 1. Implicit Wait:
 *    - Global setting applied to every `driver.findElement()` call.
 *    - Defaults to 0 seconds.
 *    - Only checks DOM presence, NOT visibility or clickability!
 *
 * 2. Explicit Wait (WebDriverWait):
 *    - Targeted wait applied to specific elements and specific conditions
 *      (e.g., `elementToBeClickable`, `visibilityOfElementLocated`).
 *    - Configurable timeout and polling interval (default 500ms).
 *
 * 3. Fluent Wait:
 *    - Most customizable wait. Lets you configure:
 *      a) Total timeout duration
 *      b) Polling frequency (e.g., check every 250ms instead of 500ms)
 *      c) Exceptions to ignore (e.g. `NoSuchElementException`, `StaleElementReferenceException`)
 *      d) Custom timeout message
 *
 * 4. GOLDEN INTERVIEW RULE (DO NOT MIX!):
 *    - "Never mix Implicit and Explicit waits in the same framework."
 *    - Selenium documentation warns that mixing them causes undefined and
 *      unpredictable wait times (e.g., 10s implicit + 10s explicit might wait 20s or 0s).
 */
public class SeleniumWaitsDeepDive {

    /**
     * 1. Setting Implicit Wait (Global)
     */
    public static void configureImplicitWait(WebDriver driver, int timeoutSeconds) {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(timeoutSeconds));
    }

    /**
     * 2. Explicit Wait for Element Clickability
     */
    public static WebElement waitForElementClickable(WebDriver driver, By locator, int timeoutSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    /**
     * 3. Explicit Wait for Element Visibility
     */
    public static WebElement waitForElementVisible(WebDriver driver, By locator, int timeoutSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    /**
     * 4. Fluent Wait Implementation with Custom Polling and Ignored Exceptions
     */
    public static WebElement waitForElementWithFluentWait(WebDriver driver, By locator, int timeoutSeconds, int pollingMillis) {
        Wait<WebDriver> fluentWait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(timeoutSeconds))
                .pollingEvery(Duration.ofMillis(pollingMillis))
                .ignoring(NoSuchElementException.class)
                .ignoring(StaleElementReferenceException.class)
                .withMessage("Timed out waiting for element [" + locator + "] via FluentWait");

        return fluentWait.until(d -> {
            WebElement element = d.findElement(locator);
            return element.isDisplayed() ? element : null;
        });
    }

    /**
     * 5. Wait for Complete Page Load (DOM readyState == 'complete')
     * Top SDET interview question for single-page applications (React / Angular).
     */
    public static void waitForPageLoadComplete(WebDriver driver, int timeoutSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));
        wait.until(d -> ((JavascriptExecutor) d)
                .executeScript("return document.readyState")
                .toString().equals("complete"));
    }

    public static void main(String[] args) {
        System.out.println("=== Selenium Waits Summary for Interviews ===");
        System.out.println("1. Implicit Wait: Global, DOM presence only, driver.manage().timeouts().implicitlyWait(...)");
        System.out.println("2. Explicit Wait: Element-specific, checks state (clickable, visible, invisible), WebDriverWait");
        System.out.println("3. Fluent Wait:   Custom polling, custom ignored exceptions, custom timeout message");
        System.out.println("4. Golden Rule:   Never mix Implicit and Explicit waits together!");
    }
}
