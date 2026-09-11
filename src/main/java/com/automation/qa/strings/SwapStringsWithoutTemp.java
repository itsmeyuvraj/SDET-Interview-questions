package com.automation.qa.strings;

/**
 * ============================================================================
 * INTERVIEW QUESTION: Swap Two Strings Without a 3rd Variable
 * ============================================================================
 * Given:
 *   String a = "Selenium";
 *   String b = "Playwright";
 * Output:
 *   a should be "Playwright", b should be "Selenium"
 * Constraint: You cannot declare any third temporary variable!
 *
 * HOW IT WORKS:
 * 1. Concatenate both strings: a = a + b ("SeleniumPlaywright")
 * 2. Extract original 'a' into 'b':
 *    b = a.substring(0, a.length() - b.length())
 *    -> substring from 0 to (18 - 10) = 8 -> "Selenium"
 * 3. Extract original 'b' into 'a':
 *    a = a.substring(b.length())
 *    -> substring starting from index 8 -> "Playwright"
 *
 * EDGE CASES:
 * - Empty string ("")
 * - null inputs (throws NullPointerException if not checked)
 */
public class SwapStringsWithoutTemp {

    public static void main(String[] args) {
        String a = "Selenium";
        String b = "Playwright";

        System.out.println("Before Swapping:");
        System.out.println("a = " + a);
        System.out.println("b = " + b);

        // Step 1: Concatenate both strings and store in 'a'
        a = a + b; // "SeleniumPlaywright"

        // Step 2: Extract initial 'a' from concatenated string and assign to 'b'
        b = a.substring(0, a.length() - b.length()); // "Selenium"

        // Step 3: Extract initial 'b' from concatenated string and assign to 'a'
        a = a.substring(b.length()); // "Playwright"

        System.out.println("\nAfter Swapping:");
        System.out.println("a = " + a);
        System.out.println("b = " + b);
    }
}
