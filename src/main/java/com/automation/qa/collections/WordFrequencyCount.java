package com.automation.qa.collections;

import java.util.*;

/**
 * ============================================================================
 * INTERVIEW QUESTION: Word Frequency Count & Duplicate Words in String
 * ============================================================================
 * Why it is asked in QA / SDET interviews:
 * 1. Log analysis simulation: Parsing log files, error messages, or stack traces
 *    to count occurrences of specific exception keywords (e.g., "TimeoutException").
 * 2. Tests regex tokenization, punctuation removal, and Map grouping.
 *
 * REQUIREMENTS:
 * - Tokenize words regardless of punctuation (commas, periods, exclamation marks).
 * - Treat words case-insensitively ("Test" and "test" are the same word).
 * - Identify duplicate words (count > 1).
 */
public class WordFrequencyCount {

    /**
     * Counts occurrences of each word in a paragraph.
     */
    public static Map<String, Integer> countWordFrequencies(String text) {
        Map<String, Integer> wordCountMap = new LinkedHashMap<>();
        if (text == null || text.trim().isEmpty()) return wordCountMap;

        // Clean punctuation and split by whitespace
        String cleaned = text.toLowerCase().replaceAll("[^a-zA-Z0-9\\s]", "");
        String[] words = cleaned.split("\\s+");

        for (String word : words) {
            if (!word.isEmpty()) {
                wordCountMap.put(word, wordCountMap.getOrDefault(word, 0) + 1);
            }
        }

        return wordCountMap;
    }

    /**
     * Prints only duplicate words and their counts.
     */
    public static void printDuplicateWords(String text) {
        Map<String, Integer> frequencies = countWordFrequencies(text);
        System.out.println("Duplicate words found:");

        boolean hasDuplicates = false;
        for (Map.Entry<String, Integer> entry : frequencies.entrySet()) {
            if (entry.getValue() > 1) {
                System.out.println("Word: \"" + entry.getKey() + "\" -> " + entry.getValue() + " times");
                hasDuplicates = true;
            }
        }

        if (!hasDuplicates) {
            System.out.println("No duplicate words found.");
        }
    }

    public static void main(String[] args) {
        String logSnippet = "Test passed. Test failed due to TimeoutException. " +
                "Please check the test environment, as test execution timed out.";

        System.out.println("Input Text:\n" + logSnippet);

        System.out.println("\n=== Word Frequencies ===");
        Map<String, Integer> counts = countWordFrequencies(logSnippet);
        counts.forEach((k, v) -> System.out.println(k + ": " + v));

        System.out.println("\n=== Duplicates Only ===");
        printDuplicateWords(logSnippet);
    }
}
