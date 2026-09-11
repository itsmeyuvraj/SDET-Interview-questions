package com.automation.qa.sdetpatterns;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * ============================================================================
 * INTERVIEW QUESTION: Regular Expressions (Regex) in QA Automation
 * ============================================================================
 * Why it is asked in QA / SDET interviews:
 * 1. UI validation: Extracting dynamic order IDs, verification OTP codes, or tracking tokens
 *    from confirmation banners or notification emails.
 * 2. Form field validation: Validating emails, phone numbers, and dates.
 * 3. Pattern & Matcher usage: Understanding capturing groups `matcher.group(1)`.
 */
public class RegexForQAValidation {

    /**
     * Extracts dynamic Order ID from UI confirmation message.
     * Example text: "Thank you! Your order #987214 has been placed successfully."
     */
    public static String extractOrderId(String confirmationMessage) {
        if (confirmationMessage == null) return null;

        // Matches 'order #' or 'order id:' followed by one or more digits
        Pattern pattern = Pattern.compile("(?i)order\\s*#?\\s*(\\d+)");
        Matcher matcher = pattern.matcher(confirmationMessage);

        if (matcher.find()) {
            return matcher.group(1); // Group 1 contains the digits
        }

        return null;
    }

    /**
     * Extracts 6-digit OTP code from an SMS/Email body.
     * Example: "Your security code is 481920. Do not share it with anyone."
     */
    public static String extractOtp(String messageBody) {
        if (messageBody == null) return null;

        // \\b ensures exact 6-digit word boundary (not 7 digits or part of a larger number)
        Pattern pattern = Pattern.compile("\\b(\\d{6})\\b");
        Matcher matcher = pattern.matcher(messageBody);

        if (matcher.find()) {
            return matcher.group(1);
        }

        return null;
    }

    /**
     * Validates Email format.
     */
    public static boolean isValidEmail(String email) {
        if (email == null) return false;
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        return email.matches(emailRegex);
    }

    /**
     * Validates standard IPv4 address format (e.g. for test server or proxy validation).
     */
    public static boolean isValidIPv4(String ip) {
        if (ip == null) return false;
        String ipv4Regex = "^((25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$";
        return ip.matches(ipv4Regex);
    }

    public static void main(String[] args) {
        System.out.println("=== 1. Extracting Dynamic Order ID ===");
        String banner = "Thank you! Your order #849301 has been confirmed.";
        System.out.println("Message: \"" + banner + "\"");
        System.out.println("Extracted Order ID: " + extractOrderId(banner));

        System.out.println("\n=== 2. Extracting 6-Digit OTP / 2FA Token ===");
        String emailBody = "Your one-time authentication code is: 582194. Expires in 5 minutes.";
        System.out.println("Extracted OTP: " + extractOtp(emailBody));

        System.out.println("\n=== 3. Email Format Validation ===");
        String[] testEmails = {"qa.engineer@company.com", "invalid-email@", "test@domain.co.in", "@missinguser.com"};
        for (String email : testEmails) {
            System.out.println(email + " -> Valid? " + isValidEmail(email));
        }

        System.out.println("\n=== 4. IPv4 Address Validation ===");
        String[] ips = {"192.168.1.1", "256.100.0.1", "10.0.0.255"};
        for (String ip : ips) {
            System.out.println(ip + " -> Valid? " + isValidIPv4(ip));
        }
    }
}
