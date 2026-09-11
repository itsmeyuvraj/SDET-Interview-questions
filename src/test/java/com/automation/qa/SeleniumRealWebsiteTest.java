package com.automation.qa;

import com.automation.qa.selenium.*;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

/**
 * ============================================================================
 * REAL-WEBSITE SELENIUM WEBDRIVER TEST SUITE
 * ============================================================================
 * Tests all core Selenium interview patterns against a live, real-world
 * automation benchmark: `https://the-internet.herokuapp.com`.
 *
 * Scenarios Covered:
 * 1. Live Page Navigation, Title & Header Verification
 * 2. Dynamic Loading & Explicit Waits (WebDriverWait)
 * 3. Dynamic Web Table parsing & cell assertions
 * 4. Dropdown Selection via Selenium Select class
 * 5. Broken Images detection using naturalWidth & HTTP status
 * 6. JavaScript Alert Dialog handling (switchTo().alert())
 * 7. Mouse Hover interactions using Actions class
 * 8. Multiple Window & Tab switching
 */
public class SeleniumRealWebsiteTest {

    private WebDriver driver;

    @BeforeEach
    void setUp() {
        driver = WebDriverFactory.createDefaultDriver();
    }

    @AfterEach
    void tearDown() {
        WebDriverFactory.quitQuietly(driver);
    }

    @Test
    @DisplayName("Selenium Real Website: Page Title, Header & Navigation")
    void testRealWebsiteNavigationAndTitle() {
        driver.get("https://the-internet.herokuapp.com");

        assertEquals("The Internet", driver.getTitle(), "Page title should match");
        assertTrue(driver.getCurrentUrl().contains("the-internet.herokuapp.com"));

        WebElement heading = driver.findElement(By.tagName("h1"));
        assertEquals("Welcome to the-internet", heading.getText().trim());
    }

    @Test
    @DisplayName("Selenium Real Website: Dynamic Loading with Explicit Wait")
    void testRealWebsiteDynamicLoadingExplicitWait() {
        driver.get("https://the-internet.herokuapp.com/dynamic_loading/1");

        // Click the 'Start' button
        WebElement startBtn = driver.findElement(By.cssSelector("#start button"));
        startBtn.click();

        // Wait for the hidden loading element to finish and #finish to become visible
        WebElement finishElement = SeleniumWaitsDeepDive.waitForElementVisible(
                driver, By.id("finish"), 15
        );

        assertNotNull(finishElement, "Finish element should be found and visible");
        assertEquals("Hello World!", finishElement.getText().trim());
    }

    @Test
    @DisplayName("Selenium Real Website: Dynamic Web Table Parsing")
    void testRealWebsiteDynamicWebTable() {
        driver.get("https://the-internet.herokuapp.com/tables");

        // Parse Table 1 data rows into List of Maps
        List<Map<String, String>> tableData = DynamicWebTableHandler.parseTableData(driver, By.id("table1"));

        assertFalse(tableData.isEmpty(), "Table 1 should contain rows");
        assertEquals(4, tableData.size(), "Table 1 should have 4 data rows");

        // Find John Smith row and assert data values
        Map<String, String> smithRow = tableData.stream()
                .filter(row -> "Smith".equals(row.get("Last Name")))
                .findFirst()
                .orElse(null);

        assertNotNull(smithRow, "Smith row should exist in table");
        assertEquals("John", smithRow.get("First Name"));
        assertEquals("jsmith@gmail.com", smithRow.get("Email"));
        assertEquals("$50.00", smithRow.get("Due"));
    }

    @Test
    @DisplayName("Selenium Real Website: Dropdown Selection")
    void testRealWebsiteDropdown() {
        driver.get("https://the-internet.herokuapp.com/dropdown");

        WebElement dropdownElem = driver.findElement(By.id("dropdown"));
        DropdownAndShadowDomHandler.selectOptionByText(dropdownElem, "Option 2");

        org.openqa.selenium.support.ui.Select select = new org.openqa.selenium.support.ui.Select(dropdownElem);
        assertEquals("Option 2", select.getFirstSelectedOption().getText().trim());
    }

    @Test
    @DisplayName("Selenium Real Website: Broken Images Detection")
    void testRealWebsiteBrokenImages() {
        driver.get("https://the-internet.herokuapp.com/broken_images");

        List<String> brokenImages = BrokenLinksAndImagesChecker.findBrokenImages(driver);
        // The-internet /broken_images intentionally contains 2 broken images
        assertTrue(brokenImages.size() >= 2, 
                "Should detect at least 2 broken images on the live test page. Found: " + brokenImages.size());
    }

    @Test
    @DisplayName("Selenium Real Website: JavaScript Alert Dialog Handling")
    void testRealWebsiteJavaScriptAlerts() {
        driver.get("https://the-internet.herokuapp.com/javascript_alerts");

        // Click JS Alert trigger button
        WebElement jsAlertBtn = driver.findElement(By.xpath("//button[text()='Click for JS Alert']"));
        jsAlertBtn.click();

        // Handle alert using helper
        String alertMsg = ScreenshotAndAlertsHandler.handleAlert(driver, 5, true, null);
        assertEquals("I am a JS Alert", alertMsg);

        // Verify result confirmation text on page
        WebElement resultElem = driver.findElement(By.id("result"));
        assertEquals("You successfully clicked an alert", resultElem.getText().trim());
    }

    @Test
    @DisplayName("Selenium Real Website: Mouse Hover using Actions Class")
    void testRealWebsiteMouseHoverActions() {
        driver.get("https://the-internet.herokuapp.com/hovers");

        WebElement firstFigure = driver.findElement(By.cssSelector(".figure:nth-of-type(1)"));
        JavaScriptExecutorAndActions.mouseHover(driver, firstFigure);

        WebElement caption = driver.findElement(By.cssSelector(".figure:nth-of-type(1) .figcaption h5"));
        assertTrue(caption.isDisplayed(), "User 1 caption should become visible on hover");
        assertEquals("name: user1", caption.getText().trim());
    }

    @Test
    @DisplayName("Selenium Real Website: Multiple Window & Tab Switching")
    void testRealWebsiteMultipleWindows() {
        driver.get("https://the-internet.herokuapp.com/windows");

        // Click the 'Click Here' link to open a child window
        driver.findElement(By.linkText("Click Here")).click();

        final String[] childHeading = new String[1];
        WindowAndFrameHandles.switchToChildWindowAndReturn(driver, () -> {
            WebElement h3 = driver.findElement(By.tagName("h3"));
            childHeading[0] = h3.getText().trim();
        });

        assertEquals("New Window", childHeading[0], "Child window should have 'New Window' heading");
        assertEquals("Opening a new window", driver.findElement(By.tagName("h3")).getText().trim(),
                "Parent window focus should be restored");
    }

    /**
     * Standalone runner for SeleniumRealWebsiteTest so it can be executed
     * directly without needing a test runner!
     */
    public static void main(String[] args) {
        System.out.println("=================================================");
        System.out.println("   Running Selenium Real Website Automation Suite ");
        System.out.println("=================================================");

        SeleniumRealWebsiteTest suite = new SeleniumRealWebsiteTest();
        int passed = 0;
        int total = 0;

        String[] testNames = {
            "Page Title & Live Navigation",
            "Dynamic Loading & Explicit Wait",
            "Dynamic Web Table Parsing",
            "Dropdown Selection (Select Class)",
            "Broken Images Detection (naturalWidth)",
            "JavaScript Alerts Handling",
            "Mouse Hover (Actions Class)",
            "Multiple Windows & Tab Switching"
        };

        Runnable[] tests = {
            suite::testRealWebsiteNavigationAndTitle,
            suite::testRealWebsiteDynamicLoadingExplicitWait,
            suite::testRealWebsiteDynamicWebTable,
            suite::testRealWebsiteDropdown,
            suite::testRealWebsiteBrokenImages,
            suite::testRealWebsiteJavaScriptAlerts,
            suite::testRealWebsiteMouseHoverActions,
            suite::testRealWebsiteMultipleWindows
        };

        for (int i = 0; i < tests.length; i++) {
            total++;
            suite.setUp();
            try {
                tests[i].run();
                System.out.printf("  [PASS] %02d. %s\n", total, testNames[i]);
                passed++;
            } catch (Throwable t) {
                System.out.printf("  [FAIL] %02d. %s -> %s\n", total, testNames[i], t.getMessage());
                t.printStackTrace();
            } finally {
                suite.tearDown();
            }
        }

        System.out.println("=================================================");
        System.out.printf("Selenium Live Results: %d of %d tests passed successfully!\n", passed, total);
        System.out.println("=================================================");
    }
}
