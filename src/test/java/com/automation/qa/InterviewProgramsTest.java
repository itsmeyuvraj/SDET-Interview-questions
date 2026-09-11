package com.automation.qa;

import com.automation.qa.arrays.*;
import com.automation.qa.collections.MapIterationAndSorting;
import com.automation.qa.numbers.*;
import com.automation.qa.sdetpatterns.RegexForQAValidation;
import com.automation.qa.selenium.*;
import com.automation.qa.strings.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

/**
 * ============================================================================
 * Automated Test Suite for Interview Programs
 * ============================================================================
 * Runs under JUnit 5 to verify the mathematical and algorithmic correctness of
 * all interview solution classes in this Maven repository.
 */
public class InterviewProgramsTest {

    @Test
    @DisplayName("String: Reverse String with Preserving Spaces")
    void testReverseStringPreservingSpaces() {
        String input = "I Am Not String";
        String expected = "g ni rtS toNmAI";
        assertEquals(expected, ReverseStringVariants.reversePreservingSpaces(input));
    }

    @Test
    @DisplayName("String: Reverse Words in a Sentence")
    void testReverseWordsInSentence() {
        assertEquals("Automation Test", ReverseWordsInSentence.reverseWordOrder("Test Automation"));
        assertEquals("tseT noitamotuA", ReverseWordsInSentence.reverseEachWord("Test Automation"));
    }

    @ParameterizedTest
    @ValueSource(strings = {"A man, a plan, a canal: Panama", "racecar", "Was it a car or a cat I saw?"})
    @DisplayName("String: Alphanumeric Palindromes")
    void testAlphanumericPalindrome(String candidate) {
        assertTrue(PalindromeCheck.isAlphanumericPalindrome(candidate));
    }

    @Test
    @DisplayName("String: Anagrams Detection")
    void testAnagrams() {
        assertTrue(AnagramCheck.isAnagramFrequencyArray("Debit Card", "Bad Credit"));
        assertFalse(AnagramCheck.isAnagramFrequencyArray("hello", "world"));
    }

    @Test
    @DisplayName("String: First Non-Repeated Character")
    void testFirstNonRepeatedChar() {
        assertEquals('w', FirstNonRepeatedChar.findFirstNonRepeatedUsingMap("swiss"));
        assertEquals('t', FirstNonRepeatedChar.findFirstNonRepeatedUsingArray("stress"));
        assertNull(FirstNonRepeatedChar.findFirstNonRepeatedUsingMap("aabbcc"));
    }

    @Test
    @DisplayName("Array: Find Second Largest and Smallest Without Sorting")
    void testSecondLargestAndSmallest() {
        int[] numbers = {12, 35, 1, 10, 34, 1, 35};
        assertEquals(34, SecondLargestAndSmallest.findSecondLargest(numbers));
        assertEquals(10, SecondLargestAndSmallest.findSecondSmallest(numbers));
    }

    @Test
    @DisplayName("Array: Find Duplicates in Array")
    void testFindDuplicates() {
        int[] numbers = {1, 5, 2, 1, 4, 3, 1, 7, 2};
        Set<Integer> duplicates = FindDuplicatesInArray.findDuplicatesUsingSet(numbers);
        assertTrue(duplicates.contains(1));
        assertTrue(duplicates.contains(2));
        assertEquals(2, duplicates.size());
    }

    @Test
    @DisplayName("Array: Missing Number in 1 to N Array")
    void testMissingNumber() {
        int[] numbers = {1, 2, 4, 6, 3, 7, 8};
        assertEquals(5, MissingNumberInArray.findMissingUsingXOR(numbers, 8));
        assertEquals(5, MissingNumberInArray.findMissingUsingSum(numbers, 8));
    }

    @Test
    @DisplayName("Array: Move Zeroes to End")
    void testMoveZeroes() {
        int[] arr = {0, 1, 0, 3, 12};
        MoveZeroesToEnd.moveZeroes(arr);
        assertArrayEquals(new int[]{1, 3, 12, 0, 0}, arr);
    }

    @Test
    @DisplayName("Array: Two Sum Problem")
    void testTwoSum() {
        int[] nums = {2, 7, 11, 15};
        int[] result = TwoSumProblem.twoSumIndices(nums, 9);
        assertArrayEquals(new int[]{0, 1}, result);
    }

    @Test
    @DisplayName("Number: Prime Number Check")
    void testPrimeNumber() {
        assertTrue(PrimeNumberCheck.isPrime(17));
        assertTrue(PrimeNumberCheck.isPrime(29));
        assertFalse(PrimeNumberCheck.isPrime(4));
        assertFalse(PrimeNumberCheck.isPrime(1));
    }

    @Test
    @DisplayName("Number: Armstrong Number Check")
    void testArmstrongNumber() {
        assertTrue(ArmstrongNumber.isArmstrong(153));
        assertTrue(ArmstrongNumber.isArmstrong(370));
        assertTrue(ArmstrongNumber.isArmstrong(9474));
        assertFalse(ArmstrongNumber.isArmstrong(123));
    }

    @Test
    @DisplayName("Number: Palindrome Number Without String Conversion")
    void testPalindromeNumber() {
        assertTrue(PalindromeNumber.isPalindrome(121));
        assertTrue(PalindromeNumber.isPalindromeHalf(1221));
        assertFalse(PalindromeNumber.isPalindrome(-121));
        assertFalse(PalindromeNumber.isPalindrome(10));
    }

    @Test
    @DisplayName("SDET Pattern: Regex Order ID and OTP Extraction")
    void testRegexExtractors() {
        String msg = "Your order #773194 has been confirmed.";
        assertEquals("773194", RegexForQAValidation.extractOrderId(msg));

        String otpMsg = "OTP is 904321 for login.";
        assertEquals("904321", RegexForQAValidation.extractOtp(otpMsg));
    }

    @Test
    @DisplayName("Collections: Sort Map by Values")
    void testSortMapByValues() {
        Map<String, Integer> map = Map.of("Firefox", 120, "Chrome", 450, "Safari", 230);
        Map<String, Integer> sortedDesc = MapIterationAndSorting.sortByValue(map, false);
        assertEquals("Chrome", sortedDesc.keySet().iterator().next());
    }

    @Test
    @DisplayName("Selenium: HTTP Link Status Checker")
    void testBrokenLinkChecker() {
        BrokenLinksAndImagesChecker.LinkValidationResult result = 
                BrokenLinksAndImagesChecker.checkUrlStatus("https://www.google.com");
        assertEquals(200, result.statusCode);
        assertFalse(result.isBroken);
    }

    @Test
    @DisplayName("Selenium Real Website: Navigation & Title Verification")
    void testSeleniumRealWebsiteNavigation() {
        org.openqa.selenium.WebDriver driver = WebDriverFactory.createDefaultDriver();
        try {
            driver.get("https://the-internet.herokuapp.com");
            assertEquals("The Internet", driver.getTitle());
            assertTrue(driver.getCurrentUrl().contains("the-internet.herokuapp.com"));
        } finally {
            WebDriverFactory.quitQuietly(driver);
        }
    }

    @Test
    @DisplayName("Selenium Real Website: Dynamic Loading with Explicit Wait")
    void testSeleniumRealWebsiteDynamicLoading() {
        org.openqa.selenium.WebDriver driver = WebDriverFactory.createDefaultDriver();
        try {
            driver.get("https://the-internet.herokuapp.com/dynamic_loading/1");
            driver.findElement(org.openqa.selenium.By.cssSelector("#start button")).click();

            org.openqa.selenium.WebElement finish = SeleniumWaitsDeepDive.waitForElementVisible(
                    driver, org.openqa.selenium.By.id("finish"), 15
            );
            assertNotNull(finish);
            assertEquals("Hello World!", finish.getText().trim());
        } finally {
            WebDriverFactory.quitQuietly(driver);
        }
    }

    @Test
    @DisplayName("Selenium Real Website: Dynamic Web Table Parsing")
    void testSeleniumRealWebsiteDynamicTable() {
        org.openqa.selenium.WebDriver driver = WebDriverFactory.createDefaultDriver();
        try {
            driver.get("https://the-internet.herokuapp.com/tables");
            List<Map<String, String>> rows = DynamicWebTableHandler.parseTableData(
                    driver, org.openqa.selenium.By.id("table1")
            );
            assertFalse(rows.isEmpty());
            assertEquals(4, rows.size());
        } finally {
            WebDriverFactory.quitQuietly(driver);
        }
    }

    @Test
    @DisplayName("Selenium Real Website: Broken Images Detection")
    void testSeleniumRealWebsiteBrokenImages() {
        org.openqa.selenium.WebDriver driver = WebDriverFactory.createDefaultDriver();
        try {
            driver.get("https://the-internet.herokuapp.com/broken_images");
            List<String> brokenImages = BrokenLinksAndImagesChecker.findBrokenImages(driver);
            assertTrue(brokenImages.size() >= 2);
        } finally {
            WebDriverFactory.quitQuietly(driver);
        }
    }

    /**
     * Standalone main method: Allows running all tests as a standard Java Application
     * in ANY IDE (IntelliJ, Eclipse, VS Code) without requiring test runner configuration!
     */
    public static void main(String[] args) {
        System.out.println("=================================================");
        System.out.println("   Running Automation QA Interview Test Suite    ");
        System.out.println("=================================================");

        InterviewProgramsTest suite = new InterviewProgramsTest();
        int passed = 0;
        int total = 0;

        String[] testNames = {
            "Reverse String Preserving Spaces",
            "Reverse Words in Sentence",
            "Alphanumeric Palindromes",
            "Anagram Detection",
            "First Non-Repeated Character",
            "Second Largest & Smallest Without Sorting",
            "Find Duplicates in Array",
            "Missing Number in Array",
            "Move Zeroes to End",
            "Two Sum Problem",
            "Prime Number Check",
            "Armstrong Number Check",
            "Palindrome Number Without String Conversion",
            "Regex Order ID & OTP Extraction",
            "Sort Map by Values",
            "Selenium HTTP Link Status Checker",
            "Selenium Real Website: Live Navigation & Title",
            "Selenium Real Website: Dynamic Loading & Explicit Wait",
            "Selenium Real Website: Dynamic Web Table Parsing",
            "Selenium Real Website: Broken Images Detection"
        };

        Runnable[] tests = {
            suite::testReverseStringPreservingSpaces,
            suite::testReverseWordsInSentence,
            () -> {
                suite.testAlphanumericPalindrome("A man, a plan, a canal: Panama");
                suite.testAlphanumericPalindrome("racecar");
            },
            suite::testAnagrams,
            suite::testFirstNonRepeatedChar,
            suite::testSecondLargestAndSmallest,
            suite::testFindDuplicates,
            suite::testMissingNumber,
            suite::testMoveZeroes,
            suite::testTwoSum,
            suite::testPrimeNumber,
            suite::testArmstrongNumber,
            suite::testPalindromeNumber,
            suite::testRegexExtractors,
            suite::testSortMapByValues,
            suite::testBrokenLinkChecker,
            suite::testSeleniumRealWebsiteNavigation,
            suite::testSeleniumRealWebsiteDynamicLoading,
            suite::testSeleniumRealWebsiteDynamicTable,
            suite::testSeleniumRealWebsiteBrokenImages
        };

        for (int i = 0; i < tests.length; i++) {
            total++;
            try {
                tests[i].run();
                System.out.printf("  [PASS] %02d. %s\n", total, testNames[i]);
                passed++;
            } catch (Throwable t) {
                System.out.printf("  [FAIL] %02d. %s -> %s\n", total, testNames[i], t.getMessage());
                t.printStackTrace();
            }
        }

        System.out.println("=================================================");
        System.out.printf("Results: %d of %d tests passed successfully!\n", passed, total);
        System.out.println("=================================================");
    }
}

