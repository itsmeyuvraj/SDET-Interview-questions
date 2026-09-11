package com.automation.qa.selenium;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;

/**
 * ============================================================================
 * INTERVIEW QUESTION: Actions Class vs JavaScriptExecutor in Selenium
 * ============================================================================
 * Why it is asked in QA / SDET interviews:
 * 1. "When does standard element.click() fail and how do you solve it?"
 *    - Fails with `ElementClickInterceptedException` (overlapping banner/loader)
 *    - Solution: Use `JavascriptExecutor.executeScript("arguments[0].click();", element)`.
 * 2. "How do you perform mouse hover, drag and drop, and context click (right-click)?"
 *    - Using the `Actions` class (`moveToElement`, `dragAndDrop`, `contextClick`).
 *    - Always remember to end with `.perform()` or `.build().perform()`.
 * 3. "How do you scroll in Selenium?"
 *    - Scroll into view via `JavascriptExecutor` or `Actions.scrollToElement()`.
 */
public class JavaScriptExecutorAndActions {

    // ========================================================================
    // Part 1: Actions Class (User Gestures)
    // ========================================================================

    /**
     * Mouse Hover over menu item.
     */
    public static void mouseHover(WebDriver driver, WebElement menuElement) {
        Actions actions = new Actions(driver);
        actions.moveToElement(menuElement).perform();
        System.out.println("Hovered over element successfully.");
    }

    /**
     * Right-click (Context Click) on an element.
     */
    public static void rightClick(WebDriver driver, WebElement element) {
        Actions actions = new Actions(driver);
        actions.contextClick(element).perform();
        System.out.println("Performed right-click (context click).");
    }

    /**
     * Double Click on an element.
     */
    public static void doubleClick(WebDriver driver, WebElement element) {
        Actions actions = new Actions(driver);
        actions.doubleClick(element).perform();
        System.out.println("Performed double click.");
    }

    /**
     * Drag and Drop an element to a target element.
     */
    public static void dragAndDrop(WebDriver driver, WebElement source, WebElement target) {
        Actions actions = new Actions(driver);
        actions.dragAndDrop(source, target).perform();
        System.out.println("Dragged source element to target element.");
    }

    // ========================================================================
    // Part 2: JavaScriptExecutor (Bypassing WebDriver DOM Limitations)
    // ========================================================================

    /**
     * Clicks an element via JavaScript when standard element.click() throws
     * ElementClickInterceptedException (e.g. sticky header or modal overlay).
     */
    public static void clickViaJs(WebDriver driver, WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", element);
        System.out.println("Clicked element using JavaScriptExecutor.");
    }

    /**
     * Smoothly scrolls the element into the center of the viewport.
     */
    public static void scrollIntoView(WebDriver driver, WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", element);
        System.out.println("Scrolled element into center view.");
    }

    /**
     * Scrolls all the way to the bottom of the page.
     */
    public static void scrollToPageBottom(WebDriver driver) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
        System.out.println("Scrolled to page bottom.");
    }

    /**
     * Highlights an element with yellow background and red border for screenshots/debugging.
     * SDET framework best practice!
     */
    public static void highlightElement(WebDriver driver, WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        String originalStyle = element.getAttribute("style");
        js.executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;');", element);

        try {
            Thread.sleep(300); // Brief pause so highlight is captured or visible
        } catch (InterruptedException ignored) {}

        // Restore original styling
        js.executeScript("arguments[0].setAttribute('style', arguments[1]);", element, originalStyle);
    }

    public static void main(String[] args) {
        System.out.println("=== Actions vs JavascriptExecutor Summary ===");
        System.out.println("1. Actions class simulates real OS-level mouse/keyboard events.");
        System.out.println("2. Always remember .perform() at the end of Actions chains.");
        System.out.println("3. JavascriptExecutor bypasses CSS overlays: arguments[0].click().");
        System.out.println("4. Element highlighting is useful for failure screenshot evidence.");
    }
}
