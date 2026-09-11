package com.automation.qa.arrays;

import java.util.Arrays;

/**
 * ============================================================================
 * INTERVIEW QUESTION: Reverse Array In-Place & Rotate Array by K Steps
 * ============================================================================
 * Two closely related problems frequently asked together in QA rounds:
 *
 * 1. Reverse Array In-Place:
 *    Swap elements from outer ends inward using two pointers.
 *    Time: O(n), Space: O(1).
 *
 * 2. Rotate Array by K Positions (LeetCode 189):
 *    Example: [1, 2, 3, 4, 5, 6, 7], k = 3 -> [5, 6, 7, 1, 2, 3, 4]
 *    Optimal Solution: Reversal Algorithm in 3 steps!
 *      Step 1: Reverse the entire array -> [7, 6, 5, 4, 3, 2, 1]
 *      Step 2: Reverse first k elements -> [5, 6, 7, 4, 3, 2, 1]
 *      Step 3: Reverse remaining (n-k) elements -> [5, 6, 7, 1, 2, 3, 4]
 *    Time: O(n), Space: O(1) in-place!
 */
public class ReverseArrayInPlace {

    /**
     * Reverses an array in-place between startIndex and endIndex.
     */
    public static void reverseRange(int[] arr, int start, int end) {
        if (arr == null) return;
        while (start < end) {
            int temp = arr[start];
            arr[start] = arr[end];
            arr[end] = temp;
            start++;
            end--;
        }
    }

    /**
     * Reverses the entire array in-place.
     */
    public static void reverseArray(int[] arr) {
        if (arr == null || arr.length <= 1) return;
        reverseRange(arr, 0, arr.length - 1);
    }

    /**
     * Rotates array to the right by k positions in O(n) time and O(1) space.
     */
    public static void rotateRightByK(int[] nums, int k) {
        if (nums == null || nums.length <= 1) return;

        int n = nums.length;
        k = k % n; // In case k is larger than array length
        if (k == 0) return;

        // Step 1: Reverse entire array
        reverseRange(nums, 0, n - 1);
        // Step 2: Reverse first k elements
        reverseRange(nums, 0, k - 1);
        // Step 3: Reverse remaining n-k elements
        reverseRange(nums, k, n - 1);
    }

    public static void main(String[] args) {
        int[] arr = {10, 20, 30, 40, 50};
        System.out.println("Original:        " + Arrays.toString(arr));
        reverseArray(arr);
        System.out.println("Reversed In-Place: " + Arrays.toString(arr));

        int[] rotateArr = {1, 2, 3, 4, 5, 6, 7};
        int k = 3;
        System.out.println("\nOriginal:        " + Arrays.toString(rotateArr));
        rotateRightByK(rotateArr, k);
        System.out.println("Rotated by k=" + k + ":   " + Arrays.toString(rotateArr)); // [5, 6, 7, 1, 2, 3, 4]
    }
}
