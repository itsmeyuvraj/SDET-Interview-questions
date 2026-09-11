package com.automation.qa.collections;

import java.util.*;
import java.util.stream.Collectors;

/**
 * ============================================================================
 * INTERVIEW QUESTION: HashMap Iteration, Sorting by Keys & Sorting by Values
 * ============================================================================
 * Why it is asked in QA / SDET interviews:
 * 1. Test results summaries, API response JSON maps, and test environment configurations
 *    are heavily handled as Maps in test frameworks.
 * 2. "How to sort a Map by its VALUES?" is one of the top 3 Java collection interview questions.
 *
 * TOPICS COVERED:
 * 1. 4 standard ways to iterate a Map.
 * 2. Sorting Map by Keys using TreeMap.
 * 3. Sorting Map by Values using Java 8 Streams and Map.Entry.comparingByValue().
 */
public class MapIterationAndSorting {

    /**
     * Demonstrates all standard ways to iterate over a Map.
     */
    public static void demonstrateIteration(Map<String, Integer> map) {
        System.out.println("--- 1. Using entrySet() with enhanced for-loop (Most Efficient) ---");
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }

        System.out.println("\n--- 2. Using keySet() and values() ---");
        for (String key : map.keySet()) {
            System.out.println("Key: " + key + ", Value: " + map.get(key));
        }

        System.out.println("\n--- 3. Using Java 8 forEach Lambda ---");
        map.forEach((key, val) -> System.out.println(key + " : " + val));

        System.out.println("\n--- 4. Using Iterator on entrySet ---");
        Iterator<Map.Entry<String, Integer>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Integer> entry = iterator.next();
            System.out.println(entry.getKey() + " = " + entry.getValue());
        }
    }

    /**
     * Sort Map by Keys (Ascending).
     * Solution: Pass into a TreeMap!
     */
    public static Map<String, Integer> sortByKey(Map<String, Integer> unsortedMap) {
        return new TreeMap<>(unsortedMap);
    }

    /**
     * Sort Map by Values in Ascending or Descending order.
     * Top SDET interview question!
     */
    public static Map<String, Integer> sortByValue(Map<String, Integer> unsortedMap, boolean ascending) {
        Comparator<Map.Entry<String, Integer>> comparator = Map.Entry.comparingByValue();
        if (!ascending) {
            comparator = comparator.reversed();
        }

        return unsortedMap.entrySet()
                .stream()
                .sorted(comparator)
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (oldVal, newVal) -> oldVal,
                        LinkedHashMap::new // LinkedHashMap preserves sorted order!
                ));
    }

    public static void main(String[] args) {
        // Sample SDET data: Browser execution counts
        Map<String, Integer> testRunCounts = new HashMap<>();
        testRunCounts.put("Chrome", 450);
        testRunCounts.put("Firefox", 120);
        testRunCounts.put("Safari", 230);
        testRunCounts.put("Edge", 80);

        System.out.println("=== Iteration Techniques ===");
        demonstrateIteration(testRunCounts);

        System.out.println("\n=== Sort by Keys (TreeMap) ===");
        Map<String, Integer> sortedByKey = sortByKey(testRunCounts);
        System.out.println(sortedByKey);

        System.out.println("\n=== Sort by Values Descending (Most Executions First) ===");
        Map<String, Integer> sortedByValDesc = sortByValue(testRunCounts, false);
        System.out.println(sortedByValDesc);
    }
}
