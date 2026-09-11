package com.automation.qa.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.Set;

/**
 * ============================================================================
 * INTERVIEW QUESTION: Handling Multiple Windows/Tabs & Iframes in Selenium
 * ============================================================================
 * Why it is asked in QA / SDET interviews:
 * 1. "What is the difference between getWindowHandle() and getWindowHandles()?"
 *    - `getWindowHandle()`: Returns unique alphanumeric string ID of the CURRENT focused window (`String`).
 *    - `getWindowHandles()`: Returns set of window IDs of ALL currently open windows (`Set<String>`).
 * 2. "How do you switch to a child window, close it, and return safely to the parent window?"
 * 3. "How do you switch into and out of nested iframes?"
 *    - `defaultContent()` vs `parentFrame()`.
 */
public class WindowAndFrameHandles {

    /**
     * 1. Switch to newly opened child window, do action, close it, and return to parent.
     */
    public static void switchToChildWindowAndReturn(WebDriver driver, Runnable childWindowAction) {
        String parentWindowHandle = driver.getWindowHandle();
        Set<String> allWindowHandles = driver.getWindowHandles();

        for (String handle : allWindowHandles) {
            if (!handle.equals(parentWindowHandle)) {
                // Switch focus to the child window
                driver.switchTo().window(handle);
                System.out.println("Switched to Child Window: " + driver.getTitle());

                // Execute custom test action in child window
                childWindowAction.run();

                // Close child window (NOT driver.quit() which closes entire session!)
                driver.close();
                System.out.println("Closed child window.");
                break;
            }
        }

        // Return focus back to main parent window
        driver.switchTo().window(parentWindowHandle);
        System.out.println("Safely returned to Parent Window: " + driver.getTitle());
    }

    /**
     * 2. Switch to window by matching page Title or URL substring (useful when 3+ windows exist)
     */
    public static boolean switchToWindowByTitle(WebDriver driver, String expectedTitlePart) {
        Set<String> handles = driver.getWindowHandles();
        for (String handle : handles) {
            driver.switchTo().window(handle);
            if (driver.getTitle().contains(expectedTitlePart)) {
                System.out.println("Switched to window with title containing: " + expectedTitlePart);
                return true;
            }
        }
        System.err.println("No window found matching title: " + expectedTitlePart);
        return false;
    }

    /**
     * 3. Iframe Handling Techniques:
     * - By Index: driver.switchTo().frame(0)
     * - By Name or ID: driver.switchTo().frame("loginFrame")
     * - By WebElement: driver.switchTo().frame(driver.findElement(By.cssSelector("iframe.chat-widget")))
     *
     * Returning from Frames:
     * - defaultContent(): Returns all the way to the top-level webpage document.
     * - parentFrame(): Moves up ONE frame level (essential for nested iframes).
     */
    public static void handleNestedIframes(WebDriver driver, By parentFrameLocator, By childFrameLocator) {
        // Step 1: Switch to outer parent frame
        WebElement parentFrameElement = driver.findElement(parentFrameLocator);
        driver.switchTo().frame(parentFrameElement);
        System.out.println("Inside parent iframe.");

        // Step 2: Switch to inner child frame
        WebElement childFrameElement = driver.findElement(childFrameLocator);
        driver.switchTo().frame(childFrameElement);
        System.out.println("Inside child iframe.");

        // Step 3: Move back to parent frame
        driver.switchTo().parentFrame();
        System.out.println("Moved back up to parent iframe.");

        // Step 4: Return completely to top webpage
        driver.switchTo().defaultContent();
        System.out.println("Returned to default webpage content.");
    }

    public static void main(String[] args) {
        System.out.println("=== Window & Frame Handles Interview Essentials ===");
        System.out.println("1. driver.close() closes CURRENT window; driver.quit() terminates the entire session.");
        System.out.println("2. getWindowHandles() returns Set<String> because window handles are unique.");
        System.out.println("3. switchTo().defaultContent() returns to base page.");
        System.out.println("4. switchTo().parentFrame() moves up exactly one frame in nested structures.");
    }
}
