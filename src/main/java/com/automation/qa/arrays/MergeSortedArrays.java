package com.automation.qa.arrays;

import java.util.Arrays;

/**
 * ============================================================================
 * INTERVIEW QUESTION: Merge Two Sorted Arrays
 * ============================================================================
 * Given two sorted arrays arr1 of size m and arr2 of size n:
 * Merge them into a single sorted array of size (m + n).
 *
 * CRITICAL INTERVIEW CONSTRAINT:
 * "Do not just combine both and call Arrays.sort()! That takes O((m+n) log (m+n)).
 * Solve it in linear time O(m + n)."
 *
 * ALGORITHM: Two Pointers (Merge step of Merge Sort)
 * - Pointer i tracks arr1, Pointer j tracks arr2, Pointer k tracks merged array.
 * - Compare arr1[i] and arr2[j], put smaller element into merged[k].
 * - Copy any remaining elements from either array.
 *
 * COMPLEXITY:
 * - Time: O(m + n)
 * - Space: O(m + n) to store the merged result
 */
public class MergeSortedArrays {

    public static int[] merge(int[] arr1, int[] arr2) {
        if (arr1 == null) return arr2 != null ? arr2.clone() : new int[0];
        if (arr2 == null) return arr1.clone();

        int m = arr1.length;
        int n = arr2.length;
        int[] merged = new int[m + n];

        int i = 0; // Pointer for arr1
        int j = 0; // Pointer for arr2
        int k = 0; // Pointer for merged array

        // Merge elements while both arrays have items
        while (i < m && j < n) {
            if (arr1[i] <= arr2[j]) {
                merged[k++] = arr1[i++];
            } else {
                merged[k++] = arr2[j++];
            }
        }

        // Copy remaining elements from arr1 (if any)
        while (i < m) {
            merged[k++] = arr1[i++];
        }

        // Copy remaining elements from arr2 (if any)
        while (j < n) {
            merged[k++] = arr2[j++];
        }

        return merged;
    }

    /**
     * LeetCode 88 Variation: Merge in-place into nums1 from the back
     * nums1 has size m + n, with the last n elements initialized as 0.
     */
    public static void mergeInPlace(int[] nums1, int m, int[] nums2, int n) {
        int p1 = m - 1;
        int p2 = n - 1;
        int p = m + n - 1;

        while (p1 >= 0 && p2 >= 0) {
            if (nums1[p1] > nums2[p2]) {
                nums1[p--] = nums1[p1--];
            } else {
                nums1[p--] = nums2[p2--];
            }
        }

        // If nums2 has remaining elements, copy them
        while (p2 >= 0) {
            nums1[p--] = nums2[p2--];
        }
    }

    public static void main(String[] args) {
        int[] arr1 = {1, 3, 5, 7, 9};
        int[] arr2 = {2, 4, 6, 8, 10, 12};

        int[] merged = merge(arr1, arr2);
        System.out.println("Array 1: " + Arrays.toString(arr1));
        System.out.println("Array 2: " + Arrays.toString(arr2));
        System.out.println("Merged:  " + Arrays.toString(merged));

        // LeetCode 88 In-place demo
        int[] nums1 = {1, 2, 3, 0, 0, 0};
        int[] nums2 = {2, 5, 6};
        mergeInPlace(nums1, 3, nums2, 3);
        System.out.println("\nLeetCode 88 In-Place Merge Result: " + Arrays.toString(nums1));
    }
}
