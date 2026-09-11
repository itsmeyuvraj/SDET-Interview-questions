package com.automation.qa.sdetpatterns;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * ============================================================================
 * INTERVIEW QUESTION: Date & Timestamp Utilities for Test Automation
 * ============================================================================
 * Why it is asked in QA / SDET interviews:
 * 1. Test data creation: Creating guaranteed unique emails or usernames for signup tests
 *    (e.g., "user_20260911_162045@test.com" avoids "User already exists" error).
 * 2. Artifact naming: Naming screenshots on failure (e.g., "Failure_LoginTest_20260911_162045.png").
 * 3. Booking / Date Pickers: Selecting check-in date (today + 2 days) and check-out date (today + 5 days).
 * 4. Knowledge check: Demonstrating `java.time` package (Java 8+ modern API) over legacy `java.util.Date`.
 */
public class TimestampAndDateUtils {

    /**
     * Generates a unique timestamp string formatted as "yyyyMMdd_HHmmss".
     */
    public static String getCurrentTimestamp() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
    }

    /**
     * Generates a unique test email address for automated registration tests.
     */
    public static String generateUniqueTestEmail(String prefix) {
        String safePrefix = (prefix == null || prefix.isEmpty()) ? "testuser" : prefix;
        return safePrefix + "_" + getCurrentTimestamp() + "@automationtest.com";
    }

    /**
     * Generates dynamic screenshot filename for failed test case.
     */
    public static String getScreenshotFileName(String testMethodName) {
        return "Screenshot_" + testMethodName + "_" + getCurrentTimestamp() + ".png";
    }

    /**
     * Calculates future date for hotel/flight booking test cases.
     * Example: checkIn = getFutureDateFormatted(2, "dd/MM/yyyy") -> 2 days from now.
     */
    public static String getFutureDateFormatted(int plusDays, String formatPattern) {
        LocalDate futureDate = LocalDate.now().plusDays(plusDays);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(formatPattern);
        return futureDate.format(formatter);
    }

    public static void main(String[] args) {
        System.out.println("=== 1. Current Timestamp ===");
        System.out.println("Timestamp: " + getCurrentTimestamp());

        System.out.println("\n=== 2. Unique Test Email Generator ===");
        System.out.println("Email 1: " + generateUniqueTestEmail("qa_user"));
        System.out.println("Email 2: " + generateUniqueTestEmail("admin"));

        System.out.println("\n=== 3. Failure Screenshot File Name ===");
        System.out.println(getScreenshotFileName("verifyPaymentCheckout"));

        System.out.println("\n=== 4. Dynamic Booking Dates (Check-In & Check-Out) ===");
        String checkIn = getFutureDateFormatted(3, "dd-MMM-yyyy");
        String checkOut = getFutureDateFormatted(7, "dd-MMM-yyyy");
        System.out.println("Check-in Date (Today + 3 days):  " + checkIn);
        System.out.println("Check-out Date (Today + 7 days): " + checkOut);
    }
}
