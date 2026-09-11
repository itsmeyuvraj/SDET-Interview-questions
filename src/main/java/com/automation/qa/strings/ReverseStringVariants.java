package com.automation.qa.strings;

/**
 * ============================================================================
 * INTERVIEW QUESTION: Reverse a String (Multiple Variants)
 * ============================================================================
 * Why it is asked in QA / SDET interviews:
 * 1. Tests fundamental understanding of String immutability in Java.
 * 2. Evaluates ability to manipulate arrays, two-pointer techniques, and StringBuilder.
 * 3. A common twist is: "Reverse a string while preserving space positions"
 *    (e.g., "I Am Not String" -> "g ni rtS toNmAI").
 *
 * COMPLEXITY:
 * - Approach 1 (StringBuilder): Time: O(n), Space: O(n)
 * - Approach 2 (Two Pointers / In-place char[]): Time: O(n), Space: O(n) (Java strings are immutable)
 * - Approach 3 (Preserving Spaces): Time: O(n), Space: O(n)
 *
 * COMMON INTERVIEW TRAPS / EDGE CASES:
 * - null input: Always check for null before calling .length() or .toCharArray().
 * - Empty string ("") or single character string ("a").
 * - Strings with leading/trailing or multiple consecutive spaces.
 */
public class ReverseStringVariants {

    /**
     * Approach 1: Using StringBuilder.reverse()
     * When to use: When the interviewer allows built-in library methods.
     */
    public static String reverseUsingBuiltIn(String input) {
        if (input == null) return null;
        return new StringBuilder(input).reverse().toString();
    }

    /**
     * Approach 2: Using Two Pointers on a char array (No built-in reverse)
     * When to use: When the interviewer says "Do NOT use StringBuilder.reverse()".
     * Explains in-place swapping logic.
     */
    public static String reverseUsingTwoPointers(String input) {
        if (input == null || input.length() <= 1) {
            return input;
        }

        char[] chars = input.toCharArray();
        int left = 0;
        int right = chars.length - 1;

        while (left < right) {
            // Swap characters at left and right indices
            char temp = chars[left];
            chars[left] = chars[right];
            chars[right] = temp;

            left++;
            right--;
        }

        return new String(chars);
    }

    /**
     * Approach 3: Advanced QA Twist - Reverse string while preserving space positions!
     * Example: "I Am Not String" -> "g ni rtS toNmAI"
     * Notice: Space at index 1 and 4 stay at index 1 and 4.
     */
    public static String reversePreservingSpaces(String input) {
        if (input == null || input.length() <= 1) {
            return input;
        }

        char[] chars = input.toCharArray();
        int left = 0;
        int right = chars.length - 1;

        while (left < right) {
            // If character at left pointer is space, skip it
            if (chars[left] == ' ') {
                left++;
            }
            // If character at right pointer is space, skip it
            else if (chars[right] == ' ') {
                right--;
            }
            // If neither is space, swap them
            else {
                char temp = chars[left];
                chars[left] = chars[right];
                chars[right] = temp;
                left++;
                right--;
            }
        }

        return new String(chars);
    }

    public static void main(String[] args) {
        System.out.println("=== Reverse String Demo ===");

        String original = "Selenium WebDriver";
        System.out.println("Original:              " + original);
        System.out.println("Using StringBuilder:   " + reverseUsingBuiltIn(original));
        System.out.println("Using Two Pointers:    " + reverseUsingTwoPointers(original));

        String withSpaces = "I Am Not String";
        System.out.println("\n=== Advanced Twist: Preserving Space Positions ===");
        System.out.println("Original:              \"" + withSpaces + "\"");
        System.out.println("Preserving Spaces:     \"" + reversePreservingSpaces(withSpaces) + "\"");
        // Expected: "g ni rtS toNmAI"
    }
}
