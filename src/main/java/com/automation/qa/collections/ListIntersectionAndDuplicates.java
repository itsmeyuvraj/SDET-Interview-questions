package com.automation.qa.collections;

import java.util.*;

/**
 * ============================================================================
 * INTERVIEW QUESTION: List Operations (Intersection, Difference, Conversions)
 * ============================================================================
 * Why it is asked in QA / SDET interviews:
 * 1. Test Verification / Assertions: Comparing Expected vs Actual lists
 *    (e.g., UI table rows vs Database query results).
 * 2. Finding missing elements (in expected but not in actual).
 * 3. Finding extra unexpected elements (in actual but not in expected).
 * 4. Converting between Arrays and Lists (`Arrays.asList()`, `toArray()`, `List.of()`).
 */
public class ListIntersectionAndDuplicates {

    /**
     * Find common elements (Intersection) between two lists.
     */
    public static <T> List<T> findIntersection(List<T> list1, List<T> list2) {
        if (list1 == null || list2 == null) return Collections.emptyList();

        Set<T> set1 = new HashSet<>(list1);
        Set<T> intersection = new LinkedHashSet<>();

        for (T item : list2) {
            if (set1.contains(item)) {
                intersection.add(item);
            }
        }

        return new ArrayList<>(intersection);
    }

    /**
     * Real-World SDET Problem: Compare Expected vs Actual test datasets.
     * Reports missing items and unexpected extra items.
     */
    public static void compareExpectedVsActual(List<String> expected, List<String> actual) {
        System.out.println("Expected Items: " + expected);
        System.out.println("Actual Items:   " + actual);

        // Missing items: in expected, but NOT in actual
        List<String> missing = new ArrayList<>(expected);
        missing.removeAll(actual);

        // Extra items: in actual, but NOT in expected
        List<String> extra = new ArrayList<>(actual);
        extra.removeAll(expected);

        System.out.println("Missing Items (Test Failure Risk): " + missing);
        System.out.println("Extra Items (Unexpected Elements): " + extra);

        if (missing.isEmpty() && extra.isEmpty()) {
            System.out.println("PASS: Expected and Actual datasets match perfectly!");
        } else {
            System.out.println("FAIL: Discrepancy detected between Expected and Actual data.");
        }
    }

    /**
     * Demonstrates Array to List and List to Array conversions.
     */
    public static void demonstrateConversions() {
        // 1. Array to List
        String[] stringArray = {"Chrome", "Firefox", "Edge"};
        List<String> listFromArr = new ArrayList<>(Arrays.asList(stringArray));
        listFromArr.add("Safari"); // Modifiable list

        // 2. List to Array
        String[] backToArray = listFromArr.toArray(new String[0]);
        System.out.println("Array length: " + backToArray.length + ", Content: " + Arrays.toString(backToArray));
    }

    public static void main(String[] args) {
        System.out.println("=== Set / List Intersection ===");
        List<Integer> l1 = List.of(1, 2, 3, 4, 5);
        List<Integer> l2 = List.of(3, 4, 5, 6, 7);
        System.out.println("Common Elements: " + findIntersection(l1, l2));

        System.out.println("\n=== QA Test Comparison: UI vs DB Test Data ===");
        List<String> expectedProducts = List.of("Laptop", "Mouse", "Keyboard", "Monitor", "Headphones");
        List<String> actualProductsOnUI = List.of("Laptop", "Mouse", "Keyboard", "Webcam");
        compareExpectedVsActual(expectedProducts, actualProductsOnUI);

        System.out.println("\n=== Array <-> List Conversions ===");
        demonstrateConversions();
    }
}
