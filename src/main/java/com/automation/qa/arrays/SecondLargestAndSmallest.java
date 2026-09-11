package com.automation.qa.arrays;

/**
 * ============================================================================
 * INTERVIEW QUESTION: Find Second Largest and Second Smallest in Array
 * ============================================================================
 * Asked in nearly EVERY Automation QA interview.
 *
 * CRITICAL INTERVIEW CONSTRAINT:
 * "Find the second largest/smallest WITHOUT sorting the array!"
 *
 * WHY WITHOUT SORTING?
 * - Arrays.sort(arr) takes O(n log n) time.
 * - Single-pass linear scan takes O(n) time and O(1) space.
 *
 * TRAP TO AVOID:
 * Handling duplicate values!
 * Example: arr = [10, 10, 9, 8, 10]
 * Largest is 10. The second largest is 9 (NOT 10!).
 */
public class SecondLargestAndSmallest {

    /**
     * Finds the second largest element in a single pass O(n).
     * Returns Integer.MIN_VALUE if no second largest exists.
     */
    public static int findSecondLargest(int[] arr) {
        if (arr == null || arr.length < 2) {
            throw new IllegalArgumentException("Array must contain at least 2 elements");
        }

        int largest = Integer.MIN_VALUE;
        int secondLargest = Integer.MIN_VALUE;

        for (int num : arr) {
            if (num > largest) {
                secondLargest = largest;
                largest = num;
            } else if (num > secondLargest && num != largest) {
                // Must be greater than secondLargest AND not equal to the largest
                secondLargest = num;
            }
        }

        if (secondLargest == Integer.MIN_VALUE) {
            throw new RuntimeException("All elements in the array are identical. No second largest exists.");
        }

        return secondLargest;
    }

    /**
     * Finds the second smallest element in a single pass O(n).
     */
    public static int findSecondSmallest(int[] arr) {
        if (arr == null || arr.length < 2) {
            throw new IllegalArgumentException("Array must contain at least 2 elements");
        }

        int smallest = Integer.MAX_VALUE;
        int secondSmallest = Integer.MAX_VALUE;

        for (int num : arr) {
            if (num < smallest) {
                secondSmallest = smallest;
                smallest = num;
            } else if (num < secondSmallest && num != smallest) {
                secondSmallest = num;
            }
        }

        if (secondSmallest == Integer.MAX_VALUE) {
            throw new RuntimeException("All elements in the array are identical. No second smallest exists.");
        }

        return secondSmallest;
    }

    public static void main(String[] args) {
        int[] numbers = {12, 35, 1, 10, 34, 1, 35};

        System.out.print("Input Array: ");
        for (int n : numbers) System.out.print(n + " ");
        System.out.println();

        System.out.println("Second Largest:  " + findSecondLargest(numbers));   // 34
        System.out.println("Second Smallest: " + findSecondSmallest(numbers));  // 10

        // Duplicate edge case
        int[] duplicates = {10, 10, 9, 8};
        System.out.println("\nHandling duplicates [10, 10, 9, 8]:");
        System.out.println("Second Largest:  " + findSecondLargest(duplicates)); // 9
    }
}
