package com.automation.qa.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;

/**
 * ============================================================================
 * INTERVIEW QUESTION: Dropdowns (Standard & Custom) and Shadow DOM in Selenium 4
 * ============================================================================
 * Why it is asked in QA / SDET interviews:
 * 1. "How do you handle dropdowns with a <select> tag versus without a <select> tag?"
 * 2. "What methods does the Select class provide?"
 *    - `selectByVisibleText()`, `selectByValue()`, `selectByIndex()`, `getOptions()`.
 * 3. "How do you interact with elements inside a Shadow DOM in Selenium 4?"
 *    - Selenium 4 introduced native support with `element.getShadowRoot()`!
 */
public class DropdownAndShadowDomHandler {

    // ========================================================================
    // Part 1: Standard <select> Dropdown Handling
    // ========================================================================

    /**
     * Selects an option by visible text and verifies it was selected.
     */
    public static void selectOptionByText(WebElement selectElement, String visibleText) {
        Select select = new Select(selectElement);
        select.selectByVisibleText(visibleText);

        String selectedText = select.getFirstSelectedOption().getText();
        System.out.println("Selected dropdown option: " + selectedText);
    }

    /**
     * Gets all available options in a standard <select> dropdown.
     */
    public static List<String> getAllDropdownOptions(WebElement selectElement) {
        Select select = new Select(selectElement);
        List<WebElement> options = select.getOptions();
        List<String> optionTexts = new ArrayList<>();

        for (WebElement option : options) {
            optionTexts.add(option.getText().trim());
        }

        return optionTexts;
    }

    // ========================================================================
    // Part 2: Custom / Bootstrap / Dynamic Dropdowns (No <select> tag)
    // ========================================================================

    /**
     * Handles modern UI dropdowns (Div / Button + UL / LI options).
     *
     * @param driver            WebDriver instance
     * @param dropdownTrigger   Locator for the button/input that opens the dropdown
     * @param optionsLocator    Locator for all rendered option items (e.g. By.cssSelector("ul.menu li"))
     * @param optionToSelect    The text of the option to click
     */
    public static void selectCustomDropdownOption(WebDriver driver, By dropdownTrigger, By optionsLocator, String optionToSelect) {
        // Step 1: Click the dropdown toggle to open options
        driver.findElement(dropdownTrigger).click();

        // Step 2: Locate all rendered option elements
        List<WebElement> options = driver.findElements(optionsLocator);

        boolean found = false;
        for (WebElement opt : options) {
            if (opt.getText().trim().equalsIgnoreCase(optionToSelect)) {
                opt.click();
                found = true;
                System.out.println("Selected custom dropdown option: " + optionToSelect);
                break;
            }
        }

        if (!found) {
            throw new RuntimeException("Option '" + optionToSelect + "' not found in custom dropdown.");
        }
    }

    // ========================================================================
    // Part 3: Shadow DOM in Selenium 4 (Native getShadowRoot)
    // ========================================================================

    /**
     * Interacts with an input element located inside a Shadow Root (Selenium 4 feature).
     * Example: Chrome download page or Web Components (<custom-input> shadow root).
     */
    public static void enterTextInShadowDom(WebDriver driver, By hostLocator, By shadowChildLocator, String textToEnter) {
        // 1. Locate the Shadow Host element
        WebElement shadowHost = driver.findElement(hostLocator);

        // 2. Extract SearchContext using native Selenium 4 getShadowRoot()
        SearchContext shadowRoot = shadowHost.getShadowRoot();

        // 3. Find the element inside the shadow root and interact with it
        WebElement shadowElement = shadowRoot.findElement(shadowChildLocator);
        shadowElement.clear();
        shadowElement.sendKeys(textToEnter);
        System.out.println("Entered text into Shadow DOM element: " + textToEnter);
    }

    public static void main(String[] args) {
        System.out.println("=== Dropdown & Shadow DOM Summary ===");
        System.out.println("1. Standard <select>: Use org.openqa.selenium.support.ui.Select");
        System.out.println("2. Custom Dropdown: Click trigger button -> loop through list elements -> click matching text");
        System.out.println("3. Selenium 4 Shadow DOM: shadowHost.getShadowRoot().findElement(...)");
    }
}
