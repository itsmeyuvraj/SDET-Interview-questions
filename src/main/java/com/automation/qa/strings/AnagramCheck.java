package com.automation.qa.strings;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * ============================================================================
 * INTERVIEW QUESTION: Anagram Check
 * ============================================================================
 * Two strings are anagrams if they contain the exact same characters with the
 * exact same frequencies, but in a different order (e.g., "listen" and "silent").
 *
 * WHY ASKED IN QA INTERVIEWS:
 * - Tests understanding of character arrays, sorting vs hashing.
 * - Interviewers frequently ask: "Can you solve it in O(n) time without sorting?"
 *
 * APPROACHES:
 * 1. Sorting char arrays: O(n log n) time, O(n) space
 * 2. Character Frequency Array (Optimal for ASCII): O(n) time, O(1) auxiliary space (size 256)
 * 3. HashMap (Handles full Unicode / arbitrary characters): O(n) time, O(k) space
 *
 * EDGE CASES:
 * - Different lengths (instant false)
 * - Case sensitivity (usually asked to treat case-insensitively)
 * - Strings with spaces (e.g. "Debit Card" vs "Bad Credit")
 */
public class AnagramCheck {

    /**
     * Approach 1: Sorting char arrays
     * Time: O(n log n) due to sorting.
     */
    public static boolean isAnagramSorting(String s1, String s2) {
        if (s1 == null || s2 == null) return false;

        // Clean strings: remove spaces and convert to lower case
        String cleanS1 = s1.replaceAll("\\s", "").toLowerCase();
        String cleanS2 = s2.replaceAll("\\s", "").toLowerCase();

        if (cleanS1.length() != cleanS2.length()) {
            return false;
        }

        char[] a1 = cleanS1.toCharArray();
        char[] a2 = cleanS2.toCharArray();

        Arrays.sort(a1);
        Arrays.sort(a2);

        return Arrays.equals(a1, a2);
    }

    /**
     * Approach 2: Optimal Frequency Array (O(n) Time, O(1) Space)
     * Recommended approach to explain to the interviewer!
     */
    public static boolean isAnagramFrequencyArray(String s1, String s2) {
        if (s1 == null || s2 == null) return false;

        String cleanS1 = s1.replaceAll("\\s", "").toLowerCase();
        String cleanS2 = s2.replaceAll("\\s", "").toLowerCase();

        if (cleanS1.length() != cleanS2.length()) {
            return false;
        }

        // Standard ASCII frequency table (256 characters)
        int[] charCounts = new int[256];

        for (int i = 0; i < cleanS1.length(); i++) {
            charCounts[cleanS1.charAt(i)]++;
            charCounts[cleanS2.charAt(i)]--;
        }

        // If all frequencies are 0, strings are anagrams
        for (int count : charCounts) {
            if (count != 0) {
                return false;
            }
        }

        return true;
    }

    /**
     * Approach 3: Using HashMap (Great for Unicode or when asked to use Collections)
     */
    public static boolean isAnagramHashMap(String s1, String s2) {
        if (s1 == null || s2 == null) return false;

        String cleanS1 = s1.replaceAll("\\s", "").toLowerCase();
        String cleanS2 = s2.replaceAll("\\s", "").toLowerCase();

        if (cleanS1.length() != cleanS2.length()) return false;

        Map<Character, Integer> map = new HashMap<>();

        for (char c : cleanS1.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }

        for (char c : cleanS2.toCharArray()) {
            if (!map.containsKey(c)) return false;
            int count = map.get(c);
            if (count == 1) {
                map.remove(c);
            } else {
                map.put(c, count - 1);
            }
        }

        return map.isEmpty();
    }

    public static void main(String[] args) {
        String pair1A = "listen";
        String pair1B = "silent";

        String pair2A = "Debit Card";
        String pair2B = "Bad Credit";

        String pair3A = "Automation";
        String pair3B = "Developer";

        System.out.println("Pair 1: " + pair1A + " & " + pair1B + " -> " + isAnagramFrequencyArray(pair1A, pair1B));
        System.out.println("Pair 2: \"" + pair2A + "\" & \"" + pair2B + "\" -> " + isAnagramFrequencyArray(pair2A, pair2B));
        System.out.println("Pair 3: " + pair3A + " & " + pair3B + " -> " + isAnagramFrequencyArray(pair3A, pair3B));
    }
}
