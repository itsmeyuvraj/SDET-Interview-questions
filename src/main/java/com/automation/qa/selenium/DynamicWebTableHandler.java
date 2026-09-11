package com.automation.qa.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ============================================================================
 * INTERVIEW QUESTION: Handling Dynamic Web Tables in Selenium WebDriver
 * ============================================================================
 * Why it is asked in nearly 90% of Selenium QA interviews:
 * 1. Web tables (admin portals, CRM dashboards, order histories) change dynamically.
 * 2. Tests XPath axes mastery: `//td[text()='John']/following-sibling::td//button`.
 * 3. Tests data parsing: Extracting tabular UI data into Java collections
 *    (`List<Map<String, String>>`) for assertions against database queries.
 *
 * COMMON INTERVIEW QUESTIONS:
 * - "How do you locate an edit/delete button for a specific user row in a dynamic table?"
 * - "How do you extract all headers and row values dynamically?"
 * - "How do you handle tables with pagination?"
 */
public class DynamicWebTableHandler {

    /**
     * Extracts an entire HTML table into a List of Maps.
     * Each Map represents one row, with Header Name -> Cell Value.
     *
     * Example Result:
     * [
     *   {"Name": "John Doe", "Role": "Admin", "Status": "Active"},
     *   {"Name": "Jane Smith", "Role": "QA Engineer", "Status": "Pending"}
     * ]
     */
    public static List<Map<String, String>> parseTableData(WebDriver driver, By tableLocator) {
        List<Map<String, String>> tableData = new ArrayList<>();
        WebElement table = driver.findElement(tableLocator);

        // 1. Extract Column Header Names
        List<WebElement> headerElements = table.findElements(By.xpath(".//thead//th | .//tr[1]//th"));
        List<String> headers = new ArrayList<>();
        for (WebElement header : headerElements) {
            headers.add(header.getText().trim());
        }

        // 2. Extract Data Rows
        List<WebElement> rows = table.findElements(By.xpath(".//tbody//tr"));
        for (WebElement row : rows) {
            List<WebElement> cells = row.findElements(By.tagName("td"));
            if (cells.isEmpty()) continue; // Skip empty rows

            Map<String, String> rowMap = new HashMap<>();
            for (int colIndex = 0; colIndex < cells.size(); colIndex++) {
                String headerName = (colIndex < headers.size()) ? headers.get(colIndex) : "Column_" + (colIndex + 1);
                rowMap.put(headerName, cells.get(colIndex).getText().trim());
            }
            tableData.add(rowMap);
        }

        return tableData;
    }

    /**
     * Clicks an action button (e.g. "Edit" or "Delete") in the same row as a given identifier.
     * Demonstrates essential XPath axes to the interviewer.
     *
     * XPath Explained:
     * //table[@id='users']//tr[td[contains(text(),'John Doe')]]//button[text()='Delete']
     */
    public static void clickActionInRow(WebDriver driver, String rowIdentifierText, String actionButtonText) {
        // Construct dynamic relative XPath using parent/ancestor axis
        String xpath = String.format(
                "//tr[td[normalize-space()='%s']]//button[normalize-space()='%s' or contains(@class, '%s')]",
                rowIdentifierText, actionButtonText, actionButtonText.toLowerCase()
        );

        WebElement actionButton = driver.findElement(By.xpath(xpath));
        actionButton.click();
        System.out.println("Successfully clicked '" + actionButtonText + "' for row: " + rowIdentifierText);
    }

    /**
     * Handles Dynamic Table Pagination: Searches for a record across multiple pages.
     */
    public static boolean findRecordAcrossPages(WebDriver driver, String targetText, By nextButtonLocator) {
        while (true) {
            // Check if record exists on current page
            List<WebElement> matching = driver.findElements(By.xpath("//td[contains(text(), '" + targetText + "')]"));
            if (!matching.isEmpty()) {
                System.out.println("Found record '" + targetText + "' on current page!");
                return true;
            }

            // Check if 'Next' pagination button is available and enabled
            List<WebElement> nextButtons = driver.findElements(nextButtonLocator);
            if (nextButtons.isEmpty() || !nextButtons.get(0).isEnabled() ||
                    nextButtons.get(0).getAttribute("class").contains("disabled")) {
                System.out.println("Reached last page. Record '" + targetText + "' not found.");
                return false;
            }

            // Click Next page
            nextButtons.get(0).click();
        }
    }

    public static void main(String[] args) {
        System.out.println("=== Dynamic Web Table Interview Patterns ===");
        System.out.println("1. Dynamic XPath for Row Action Button:");
        System.out.println("   //tr[td[normalize-space()='Jane Smith']]//button[text()='Delete']");
        System.out.println("\n2. Finding Cell Coordinate:");
        System.out.println("   //table[@id='data']//tr[3]/td[2]");
        System.out.println("\n3. Data extraction into List<Map<String, String>> ready for DB assertions.");
    }
}
