package com.automation.qa.selenium;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.time.Duration;

/**
 * ============================================================================
 * INTERVIEW QUESTION: Handling Alerts & Capturing Screenshots (Selenium 4)
 * ============================================================================
 * Why it is asked in QA / SDET interviews:
 * 1. "How do you handle JavaScript alerts, confirmation dialogs, and prompts?"
 *    - `Alert alert = driver.switchTo().alert();`
 *    - Methods: `accept()`, `dismiss()`, `getText()`, `sendKeys()`.
 * 2. "How do you capture screenshots for test failure reporting?"
 *    - Full viewport screenshot via `TakesScreenshot`.
 *    - Element-specific screenshot (Selenium 4 feature `element.getScreenshotAs()`).
 * 3. "How do you handle browser HTTP authentication popups?"
 *    - Passing credentials in URL (`http://username:password@the-site.com`).
 */
public class ScreenshotAndAlertsHandler {

    // ========================================================================
    // Part 1: JavaScript Alert Dialogs
    // ========================================================================

    /**
     * Waits for alert to be present and handles it.
     *
     * @param driver         WebDriver instance
     * @param timeoutSeconds Wait timeout
     * @param accept         true to click OK/Accept, false to click Cancel/Dismiss
     * @param promptInput    Text to send if prompt alert (nullable)
     * @return Alert message text
     */
    public static String handleAlert(WebDriver driver, int timeoutSeconds, boolean accept, String promptInput) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());

        String alertText = alert.getText();
        System.out.println("Alert text: \"" + alertText + "\"");

        if (promptInput != null) {
            alert.sendKeys(promptInput);
            System.out.println("Typed into alert prompt: " + promptInput);
        }

        if (accept) {
            alert.accept();
            System.out.println("Alert Accepted (OK).");
        } else {
            alert.dismiss();
            System.out.println("Alert Dismissed (Cancel).");
        }

        return alertText;
    }

    // ========================================================================
    // Part 2: Capturing Screenshots (Viewport & Specific Element)
    // ========================================================================

    /**
     * Captures full visible browser screenshot and saves to destination directory.
     */
    public static Path captureFullPageScreenshot(WebDriver driver, String destinationPath) throws IOException {
        TakesScreenshot ts = (TakesScreenshot) driver;
        File sourceFile = ts.getScreenshotAs(OutputType.FILE);

        Path destPath = Path.of(destinationPath);
        if (destPath.getParent() != null) {
            Files.createDirectories(destPath.getParent());
        }

        Files.copy(sourceFile.toPath(), destPath, StandardCopyOption.REPLACE_EXISTING);
        System.out.println("Screenshot saved to: " + destPath.toAbsolutePath());
        return destPath;
    }

    /**
     * Captures screenshot of a SPECIFIC WebElement (Selenium 4 Native Feature!).
     * Extremely popular SDET question (e.g. taking screenshot of only a logo or error banner).
     */
    public static Path captureElementScreenshot(WebElement element, String destinationPath) throws IOException {
        File sourceFile = element.getScreenshotAs(OutputType.FILE);
        Path destPath = Path.of(destinationPath);

        if (destPath.getParent() != null) {
            Files.createDirectories(destPath.getParent());
        }

        Files.copy(sourceFile.toPath(), destPath, StandardCopyOption.REPLACE_EXISTING);
        System.out.println("Element screenshot saved to: " + destPath.toAbsolutePath());
        return destPath;
    }

    public static void main(String[] args) {
        System.out.println("=== Alert & Screenshot Handling Summary ===");
        System.out.println("1. Simple Alert:       alert.accept()");
        System.out.println("2. Confirm Alert:      alert.accept() or alert.dismiss()");
        System.out.println("3. Prompt Alert:       alert.sendKeys(\"text\") -> alert.accept()");
        System.out.println("4. Full Screenshot:    ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE)");
        System.out.println("5. Element Screenshot: element.getScreenshotAs(OutputType.FILE) (Selenium 4 Native)");
    }
}
