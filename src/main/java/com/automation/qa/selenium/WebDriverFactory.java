package com.automation.qa.selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;

/**
 * ============================================================================
 * UTILITY: WebDriver Factory for Real-Browser Automation
 * ============================================================================
 * Provides standardized, clean, production-grade WebDriver instantiation.
 *
 * Highlights:
 * 1. Supports headless mode (default for CI/CD pipelines & fast test runs)
 *    and headed mode (for visual test observation).
 * 2. Pre-configures essential flags: `--headless=new`, `--no-sandbox`,
 *    `--disable-dev-shm-usage`, and 1920x1080 window size.
 * 3. Automatically detects local Chrome / Chrome for Testing installations
 *    and integrates seamlessly with Selenium 4's built-in Selenium Manager.
 */
public class WebDriverFactory {

    private static final String CHROME_FOR_TESTING_REL_PATH = 
            ".cache/selenium/chrome/mac-arm64/153.0.8010.36/Google Chrome for Testing.app/Contents/MacOS/Google Chrome for Testing";

    /**
     * Creates and configures a ChromeDriver instance.
     *
     * @param headless true to run without opening GUI window, false to run headed (visible browser)
     * @return ready-to-use WebDriver instance
     */
    public static WebDriver createChromeDriver(boolean headless) {
        ChromeOptions options = new ChromeOptions();

        if (headless) {
            options.addArguments("--headless=new");
        } else {
            // Headed mode: give window clean focus and size for visual observation
            options.addArguments("--start-maximized");
        }

        // Essential arguments for stability in modern automated environments
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-gpu");
        options.addArguments("--window-size=1280,900");
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--disable-blink-features=AutomationControlled");

        // Suppress verbose CDP warnings
        System.setProperty("webdriver.chrome.silentOutput", "true");

        // If local Chrome for Testing binary is present, direct options to it
        String userHome = System.getProperty("user.home");
        Path cftPath = Path.of(userHome, CHROME_FOR_TESTING_REL_PATH);
        if (Files.exists(cftPath)) {
            options.setBinary(cftPath.toFile());
        }

        // Also check if local chromedriver is downloaded in cache
        Path driverPath = Path.of(userHome, ".cache/selenium/chromedriver/mac-arm64/153.0.8010.36/chromedriver");
        if (Files.exists(driverPath)) {
            System.setProperty("webdriver.chrome.driver", driverPath.toString());
        }

        WebDriver driver = new ChromeDriver(options);
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        return driver;
    }

    /**
     * Creates a visible (headed) browser driver so the real Chrome window opens
     * visually on the screen for test observation.
     */
    public static WebDriver createHeadedDriver() {
        return createChromeDriver(false);
    }

    /**
     * Creates a headless browser driver (useful for background CI/CD runs).
     */
    public static WebDriver createHeadlessDriver() {
        return createChromeDriver(true);
    }

    /**
     * Convenience helper to create the default driver.
     * Defaults to HEADED mode (visible window). To override to headless in CI,
     * pass `-Dheadless=true`.
     */
    public static WebDriver createDefaultDriver() {
        boolean headless = Boolean.parseBoolean(System.getProperty("headless", "false"));
        return createChromeDriver(headless);
    }

    /**
     * Safely quits a WebDriver instance without throwing uncaught exceptions.
     */
    public static void quitQuietly(WebDriver driver) {
        if (driver != null) {
            try {
                driver.quit();
            } catch (Exception ignored) {
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("Testing WebDriverFactory initialization...");
        WebDriver driver = createDefaultDriver();
        try {
            driver.get("https://the-internet.herokuapp.com");
            System.out.println("Page Title: " + driver.getTitle());
            System.out.println("Current URL: " + driver.getCurrentUrl());
            System.out.println("WebDriverFactory verified successfully!");
        } finally {
            quitQuietly(driver);
        }
    }
}
