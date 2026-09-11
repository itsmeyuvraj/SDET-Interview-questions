package com.automation.qa.strings;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * ============================================================================
 * INTERVIEW QUESTION: First Non-Repeated (Unique) Character in a String
 * ============================================================================
 * Examples:
 * - "swiss"  -> 'w'
 * - "stress" -> 't'
 * - "aabbcc" -> No unique character
 *
 * WHY ASKED IN QA INTERVIEWS:
 * - Evaluates difference between HashMap (no order guarantee) and LinkedHashMap (preserves insertion order).
 * - Tests ability to write O(n) solutions with two passes.
 *
 * COMPLEXITY:
 * - Time: O(n) - 2 linear passes over the string
 * - Space: O(k) - At most 256 entries in the frequency map / array
 */
public class FirstNonRepeatedChar {

    /**
     * Approach 1: Using LinkedHashMap (Preserves character order)
     */
    public static Character findFirstNonRepeatedUsingMap(String str) {
        if (str == null || str.isEmpty()) return null;

        Map<Character, Integer> charCountMap = new LinkedHashMap<>();

        // First pass: populate counts while preserving order of appearance
        for (char ch : str.toCharArray()) {
            charCountMap.put(ch, charCountMap.getOrDefault(ch, 0) + 1);
        }

        // Second pass: find the first key with count == 1
        for (Map.Entry<Character, Integer> entry : charCountMap.entrySet()) {
            if (entry.getValue() == 1) {
                return entry.getKey();
            }
        }

        return null; // No unique character found
    }

    /**
     * Approach 2: Using a frequency array (No Collections overhead)
     * Extremely fast and memory efficient.
     */
    public static Character findFirstNonRepeatedUsingArray(String str) {
        if (str == null || str.isEmpty()) return null;

        int[] freq = new int[256];

        // Pass 1: count occurrences
        for (int i = 0; i < str.length(); i++) {
            freq[str.charAt(i)]++;
        }

        // Pass 2: find first character with frequency 1
        for (int i = 0; i < str.length(); i++) {
            if (freq[str.charAt(i)] == 1) {
                return str.charAt(i);
            }
        }

        return null;
    }

    public static void main(String[] args) {
        String[] testCases = {"swiss", "stress", "automation", "aabbcc", "z"};

        for (String test : testCases) {
            Character result = findFirstNonRepeatedUsingMap(test);
            Character resultArr = findFirstNonRepeatedUsingArray(test);
            System.out.println("Input: \"" + test + "\" -> First Unique: " +
                    (result != null ? "'" + result + "'" : "None") +
                    " (Array approach: " + (resultArr != null ? "'" + resultArr + "'" : "None") + ")");
        }
    }
}
