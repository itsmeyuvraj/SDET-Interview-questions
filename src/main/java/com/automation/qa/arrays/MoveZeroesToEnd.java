package com.automation.qa.arrays;

import java.util.Arrays;

/**
 * ============================================================================
 * INTERVIEW QUESTION: Move All Zeroes to End of Array
 * ============================================================================
 * (LeetCode 283 - Classic Automation QA Coding Round Question)
 *
 * Given an integer array nums, move all 0's to the end of it while maintaining
 * the relative order of the non-zero elements.
 *
 * CONSTRAINTS:
 * 1. Must do this in-place without making a copy of the array.
 * 2. Time Complexity: O(n)
 * 3. Space Complexity: O(1)
 *
 * ALGORITHM: Two Pointers
 * - Pointer `nonZeroIndex` keeps track of where the next non-zero number belongs.
 * - Pointer `i` scans through every element.
 * - When arr[i] != 0, assign arr[nonZeroIndex] = arr[i] and increment nonZeroIndex.
 * - After scanning, fill remaining slots from nonZeroIndex to arr.length with 0.
 */
public class MoveZeroesToEnd {

    public static void moveZeroes(int[] nums) {
        if (nums == null || nums.length <= 1) return;

        int nonZeroIndex = 0;

        // Pass 1: Move all non-zero elements forward
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                nums[nonZeroIndex++] = nums[i];
            }
        }

        // Pass 2: Fill the remaining indices with 0
        while (nonZeroIndex < nums.length) {
            nums[nonZeroIndex++] = 0;
        }
    }

    /**
     * Single-pass variation using in-place swap
     */
    public static void moveZeroesSinglePass(int[] nums) {
        if (nums == null || nums.length <= 1) return;

        int insertPos = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                if (i != insertPos) {
                    // Swap nums[i] and nums[insertPos]
                    int temp = nums[i];
                    nums[i] = nums[insertPos];
                    nums[insertPos] = temp;
                }
                insertPos++;
            }
        }
    }

    public static void main(String[] args) {
        int[] arr1 = {0, 1, 0, 3, 12};
        System.out.println("Original:    " + Arrays.toString(arr1));
        moveZeroes(arr1);
        System.out.println("After Move:  " + Arrays.toString(arr1)); // [1, 3, 12, 0, 0]

        int[] arr2 = {0, 0, 0, 5, 0, 7, 0, 9};
        System.out.println("\nOriginal:    " + Arrays.toString(arr2));
        moveZeroesSinglePass(arr2);
        System.out.println("Single-Pass: " + Arrays.toString(arr2)); // [5, 7, 9, 0, 0, 0, 0, 0]
    }
}
