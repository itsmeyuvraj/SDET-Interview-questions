package com.automation.qa.arrays;

import java.util.*;
import java.util.stream.Collectors;

/**
 * ============================================================================
 * INTERVIEW QUESTION: Find Duplicate Elements in an Array
 * ============================================================================
 * Why it is asked in QA / SDET interviews:
 * - Tests data structure selection (HashSet vs sorting vs frequency map).
 * - Common automated QA task: verifying unique test record IDs, checking for
 *   duplicate items in a dropdown or search result table.
 *
 * APPROACHES:
 * 1. Using HashSet.add(): Returns false if element already exists! O(n) Time, O(n) Space.
 * 2. Using Sorting: Compare adjacent elements arr[i] == arr[i-1]. O(n log n) Time, O(1) Space.
 * 3. Java 8 Streams: Grouping and filtering duplicates.
 */
public class FindDuplicatesInArray {

    /**
     * Approach 1: Using HashSet.add() - Most Recommended in Interviews
     * Time: O(n), Space: O(n)
     */
    public static Set<Integer> findDuplicatesUsingSet(int[] arr) {
        Set<Integer> seen = new HashSet<>();
        Set<Integer> duplicates = new LinkedHashSet<>();

        for (int num : arr) {
            // set.add(num) returns false if the item was already in the set
            if (!seen.add(num)) {
                duplicates.add(num);
            }
        }

        return duplicates;
    }

    /**
     * Approach 2: Using Sorting (when extra memory is restricted)
     * Time: O(n log n), Space: O(1) auxiliary
     */
    public static Set<Integer> findDuplicatesUsingSorting(int[] arr) {
        Set<Integer> duplicates = new LinkedHashSet<>();
        if (arr == null || arr.length < 2) return duplicates;

        int[] copy = Arrays.copyOf(arr, arr.length);
        Arrays.sort(copy);

        for (int i = 1; i < copy.length; i++) {
            if (copy[i] == copy[i - 1]) {
                duplicates.add(copy[i]);
            }
        }

        return duplicates;
    }

    /**
     * Approach 3: Practical QA Scenario with String IDs (e.g., verifying order IDs or product SKUs)
     */
    public static List<String> findDuplicateProductIds(List<String> productIds) {
        Set<String> uniqueIds = new HashSet<>();
        return productIds.stream()
                .filter(id -> !uniqueIds.add(id))
                .distinct()
                .collect(Collectors.toList());
    }

    public static void main(String[] args) {
        int[] numbers = {1, 5, 2, 1, 4, 3, 1, 7, 2, 8, 9, 5};

        System.out.println("Array: " + Arrays.toString(numbers));
        System.out.println("Duplicates (Using Set):     " + findDuplicatesUsingSet(numbers));
        System.out.println("Duplicates (Using Sorting): " + findDuplicatesUsingSorting(numbers));

        // Practical SDET test case: Web table row IDs with duplicate detection
        List<String> tableRowIds = List.of("ORD-101", "ORD-102", "ORD-103", "ORD-101", "ORD-104", "ORD-102");
        System.out.println("\nQA Test Verification - Duplicate Order IDs:");
        System.out.println("All Orders:       " + tableRowIds);
        System.out.println("Duplicate Orders: " + findDuplicateProductIds(tableRowIds));
    }
}
