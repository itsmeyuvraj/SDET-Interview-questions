package com.automation.qa.strings;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * ============================================================================
 * INTERVIEW QUESTION: Character Count & Finding Duplicate Characters
 * ============================================================================
 * One of the most frequently asked questions in SDET / QA interviews.
 *
 * Typical interview prompts:
 * 1. "Write a Java program to count occurrences of each character in a given string."
 * 2. "Print only the duplicate characters and their count."
 * 3. "Can you do this using Java 8 Streams?"
 *
 * COMPLEXITY:
 * - Time: O(n) where n is length of the string
 * - Space: O(k) where k is the number of unique characters (at most 256 for ASCII)
 */
public class CharacterCountAndDuplicates {

    /**
     * Approach 1: Classic HashMap approach using getOrDefault (Traditional Java)
     */
    public static Map<Character, Integer> getCharacterCounts(String str) {
        Map<Character, Integer> charCountMap = new HashMap<>();
        if (str == null) return charCountMap;

        for (char ch : str.toCharArray()) {
            charCountMap.put(ch, charCountMap.getOrDefault(ch, 0) + 1);
        }

        return charCountMap;
    }

    /**
     * Approach 2: Print only duplicate characters (count > 1)
     */
    public static void printDuplicateCharacters(String str) {
        if (str == null || str.isEmpty()) {
            System.out.println("String is empty or null.");
            return;
        }

        Map<Character, Integer> counts = getCharacterCounts(str);
        System.out.println("Duplicate characters in \"" + str + "\":");

        boolean foundDuplicate = false;
        for (Map.Entry<Character, Integer> entry : counts.entrySet()) {
            if (entry.getValue() > 1 && !Character.isWhitespace(entry.getKey())) {
                System.out.println("'" + entry.getKey() + "' occurs " + entry.getValue() + " times");
                foundDuplicate = true;
            }
        }

        if (!foundDuplicate) {
            System.out.println("No duplicate characters found.");
        }
    }

    /**
     * Approach 3: Java 8 Streams approach
     * Shows modern Java fluency to senior interviewers.
     */
    public static Map<Character, Long> getCharacterCountsJava8(String str) {
        if (str == null) return new HashMap<>();

        return str.chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
    }

    public static void main(String[] args) {
        String testString = "automation testing in java";

        System.out.println("=== Character Count (HashMap) ===");
        Map<Character, Integer> counts = getCharacterCounts(testString);
        System.out.println(counts);

        System.out.println("\n=== Duplicate Characters Only ===");
        printDuplicateCharacters(testString);

        System.out.println("\n=== Java 8 Streams Character Count ===");
        Map<Character, Long> streamCounts = getCharacterCountsJava8(testString);
        System.out.println(streamCounts);
    }
}
