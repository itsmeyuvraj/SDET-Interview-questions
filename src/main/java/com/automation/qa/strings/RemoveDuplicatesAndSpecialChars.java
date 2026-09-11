package com.automation.qa.strings;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * ============================================================================
 * INTERVIEW QUESTION: Remove Duplicates & Clean Special Characters (Regex)
 * ============================================================================
 * Why it is asked in QA / SDET interviews:
 * 1. Test data sanitization: In UI automation (Selenium/Cypress), prices often come
 *    as "$1,299.99", dates come as "09/11/2026", and dynamic IDs need regex cleaning.
 * 2. Tests knowledge of LinkedHashSet (preserves order) vs HashSet.
 * 3. Tests mastery of Java regular expressions (replaceAll).
 */
public class RemoveDuplicatesAndSpecialChars {

    /**
     * Remove duplicate characters from a string preserving the original order.
     * Example: "programming" -> "progamin"
     */
    public static String removeDuplicateCharacters(String input) {
        if (input == null || input.length() <= 1) return input;

        Set<Character> seen = new LinkedHashSet<>();
        for (char c : input.toCharArray()) {
            seen.add(c);
        }

        StringBuilder sb = new StringBuilder();
        for (Character ch : seen) {
            sb.append(ch);
        }

        return sb.toString();
    }

    /**
     * Clean special characters from string using Regular Expressions (Regex).
     * Retains only alphanumeric characters and spaces.
     * Example: "User#123@Admin! -> "User123Admin"
     */
    public static String removeSpecialCharacters(String input) {
        if (input == null) return null;
        // [^a-zA-Z0-9 ] matches any character that is NOT a letter, digit, or space
        return input.replaceAll("[^a-zA-Z0-9 ]", "");
    }

    /**
     * Practical QA Scenario: Extract numeric/decimal price from UI text
     * Example: "$1,499.50 USD (incl. VAT)" -> 1499.50
     */
    public static double extractPriceFromUiString(String uiText) {
        if (uiText == null || uiText.isEmpty()) return 0.0;

        // Remove everything except digits and decimal point
        String cleaned = uiText.replaceAll("[^0-9.]", "");
        return Double.parseDouble(cleaned);
    }

    /**
     * Count counts of Vowels, Consonants, Digits, and Special Characters
     */
    public static void countCharacterTypes(String input) {
        if (input == null) return;

        int vowels = 0, consonants = 0, digits = 0, special = 0;
        String lower = input.toLowerCase();

        for (int i = 0; i < lower.length(); i++) {
            char ch = lower.charAt(i);
            if (ch >= 'a' && ch <= 'z') {
                if (ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u') {
                    vowels++;
                } else {
                    consonants++;
                }
            } else if (ch >= '0' && ch <= '9') {
                digits++;
            } else if (!Character.isWhitespace(ch)) {
                special++;
            }
        }

        System.out.println("Vowels: " + vowels + ", Consonants: " + consonants +
                ", Digits: " + digits + ", Special Chars: " + special);
    }

    public static void main(String[] args) {
        System.out.println("=== Remove Duplicate Characters ===");
        String dupTest = "programming";
        System.out.println("\"" + dupTest + "\" without duplicates: " + removeDuplicateCharacters(dupTest));

        System.out.println("\n=== Regex Special Character Cleaning ===");
        String messyString = "Test_Account#2026! @Special&Chars";
        System.out.println("Original:  " + messyString);
        System.out.println("Cleaned:   " + removeSpecialCharacters(messyString));

        System.out.println("\n=== QA Practical: Extract Price from UI Element ===");
        String priceText = "$ 1,249.95 Total Due";
        System.out.println("Raw UI Text: " + priceText);
        System.out.println("Parsed Double Price: " + extractPriceFromUiString(priceText));

        System.out.println("\n=== Character Categorization Counts ===");
        countCharacterTypes("Hello World 2026! #Selenium");
    }
}
