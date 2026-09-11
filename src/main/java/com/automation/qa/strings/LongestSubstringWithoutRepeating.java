package com.automation.qa.strings;

import java.util.HashMap;
import java.util.Map;

/**
 * ============================================================================
 * INTERVIEW QUESTION: Longest Substring Without Repeating Characters
 * ============================================================================
 * (LeetCode 3 - Top Tier SDET / Product Company Interview Problem)
 *
 * Examples:
 * - "abcabcbb" -> 3 ("abc")
 * - "bbbbb"    -> 1 ("b")
 * - "pwwkew"   -> 3 ("wke")
 *
 * ALGORITHM: Sliding Window + HashMap
 * - Keep two pointers (left and right) defining the current window of characters.
 * - A HashMap stores the most recent index seen for each character.
 * - When a duplicate character is encountered within the current window,
 *   slide the left pointer to the right of the previous occurrence.
 *
 * COMPLEXITY:
 * - Time: O(n) - Single pass over the string with sliding window.
 * - Space: O(min(n, m)) where m is character set size (e.g. 26 or 128).
 */
public class LongestSubstringWithoutRepeating {

    public static class SubstringResult {
        public final int length;
        public final String substring;

        public SubstringResult(int length, String substring) {
            this.length = length;
            this.substring = substring;
        }

        @Override
        public String toString() {
            return "Length: " + length + ", Substring: \"" + substring + "\"";
        }
    }

    public static SubstringResult findLongestUniqueSubstring(String s) {
        if (s == null || s.isEmpty()) {
            return new SubstringResult(0, "");
        }

        Map<Character, Integer> charIndexMap = new HashMap<>();
        int maxLength = 0;
        int maxStart = 0;
        int left = 0;

        for (int right = 0; right < s.length(); right++) {
            char currentChar = s.charAt(right);

            // If the character is already in the map and within the current window
            if (charIndexMap.containsKey(currentChar)) {
                // Move left pointer right after the last occurrence
                left = Math.max(left, charIndexMap.get(currentChar) + 1);
            }

            // Update current character's latest index
            charIndexMap.put(currentChar, right);

            // Calculate current window length
            int currentLength = right - left + 1;
            if (currentLength > maxLength) {
                maxLength = currentLength;
                maxStart = left;
            }
        }

        return new SubstringResult(maxLength, s.substring(maxStart, maxStart + maxLength));
    }

    public static void main(String[] args) {
        String[] testStrings = {"abcabcbb", "bbbbb", "pwwkew", "geeksforgeeks", "automation"};

        for (String test : testStrings) {
            SubstringResult result = findLongestUniqueSubstring(test);
            System.out.println("Input: \"" + test + "\" -> " + result);
        }
    }
}
