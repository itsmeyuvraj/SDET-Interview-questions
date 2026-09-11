package com.automation.qa.strings;

/**
 * ============================================================================
 * INTERVIEW QUESTION: Palindrome Check (Basic & Alphanumeric)
 * ============================================================================
 * Why it is asked in QA / SDET interviews:
 * 1. Checks boundary conditions, loop conditions, and character evaluation.
 * 2. Real-world SDET variation: Checking if dynamic test data or page titles are palindromic.
 * 3. Most common interview trap: Not handling case sensitivity, punctuation, or spaces.
 *
 * VARIATIONS:
 * 1. Simple Palindrome (e.g. "racecar", "madam")
 * 2. Alphanumeric Palindrome ignoring non-alphanumeric chars and case:
 *    "A man, a plan, a canal: Panama" -> TRUE!
 *
 * COMPLEXITY:
 * - Time: O(n) - Single pass with two pointers
 * - Space: O(1) - In-place without extra strings created
 */
public class PalindromeCheck {

    /**
     * Approach 1: Simple two-pointer palindrome check (Case-sensitive)
     */
    public static boolean isSimplePalindrome(String str) {
        if (str == null) return false;
        int left = 0;
        int right = str.length() - 1;

        while (left < right) {
            if (str.charAt(left) != str.charAt(right)) {
                return false; // Mismatch found, definitely not a palindrome
            }
            left++;
            right--;
        }
        return true;
    }

    /**
     * Approach 2: Alphanumeric Palindrome (LeetCode 125 - SDET Favorite)
     * Ignores case and skips all spaces/punctuations without creating new strings.
     * Space Complexity: O(1)
     */
    public static boolean isAlphanumericPalindrome(String s) {
        if (s == null) return false;

        int left = 0;
        int right = s.length() - 1;

        while (left < right) {
            // Move left pointer if not letter or digit
            while (left < right && !Character.isLetterOrDigit(s.charAt(left))) {
                left++;
            }
            // Move right pointer if not letter or digit
            while (left < right && !Character.isLetterOrDigit(s.charAt(right))) {
                right--;
            }

            // Compare lowercase characters
            char charLeft = Character.toLowerCase(s.charAt(left));
            char charRight = Character.toLowerCase(s.charAt(right));

            if (charLeft != charRight) {
                return false;
            }

            left++;
            right--;
        }

        return true;
    }

    public static void main(String[] args) {
        System.out.println("=== Simple Palindrome ===");
        String word1 = "racecar";
        String word2 = "selenium";
        System.out.println("\"" + word1 + "\" is palindrome? " + isSimplePalindrome(word1));
        System.out.println("\"" + word2 + "\" is palindrome? " + isSimplePalindrome(word2));

        System.out.println("\n=== Alphanumeric Palindrome (SDET Favorite) ===");
        String phrase1 = "A man, a plan, a canal: Panama";
        String phrase2 = "Was it a car or a cat I saw?";
        String phrase3 = "Automation Testing";

        System.out.println("\"" + phrase1 + "\" -> " + isAlphanumericPalindrome(phrase1)); // true
        System.out.println("\"" + phrase2 + "\" -> " + isAlphanumericPalindrome(phrase2)); // true
        System.out.println("\"" + phrase3 + "\" -> " + isAlphanumericPalindrome(phrase3)); // false
    }
}
