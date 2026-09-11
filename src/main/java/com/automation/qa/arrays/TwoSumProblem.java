package com.automation.qa.arrays;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * ============================================================================
 * INTERVIEW QUESTION: Two Sum (Find Pair with Target Sum)
 * ============================================================================
 * (LeetCode 1 - The most ubiquitous coding question across tech interviews)
 *
 * Given an array of integers nums and an integer target:
 * Return indices or values of the two numbers such that they add up to target.
 *
 * APPROACHES:
 * 1. Brute Force: Check every pair with nested loops -> O(n^2) Time.
 * 2. HashMap (Optimal for unsorted arrays):
 *    - Store (number -> index) in a HashMap.
 *    - For each element num, calculate complement = target - num.
 *    - If complement exists in map, pair found!
 *    - Time: O(n), Space: O(n).
 * 3. Two Pointers (Optimal if array is already sorted):
 *    - Left at 0, Right at len - 1.
 *    - If sum < target, left++. If sum > target, right--.
 *    - Time: O(n), Space: O(1).
 */
public class TwoSumProblem {

    /**
     * Finds indices of the two numbers using a HashMap.
     * Time: O(n), Space: O(n)
     */
    public static int[] twoSumIndices(int[] nums, int target) {
        if (nums == null || nums.length < 2) return new int[0];

        // Map stores: Key = Value from array, Value = Index of that value
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];

            if (map.containsKey(complement)) {
                return new int[]{map.get(complement), i};
            }

            map.put(nums[i], i);
        }

        return new int[0]; // No pair found
    }

    /**
     * Two Pointers approach when the input array is already sorted.
     * Time: O(n), Space: O(1)
     */
    public static int[] twoSumSorted(int[] sortedNums, int target) {
        if (sortedNums == null || sortedNums.length < 2) return new int[0];

        int left = 0;
        int right = sortedNums.length - 1;

        while (left < right) {
            int sum = sortedNums[left] + sortedNums[right];

            if (sum == target) {
                return new int[]{sortedNums[left], sortedNums[right]};
            } else if (sum < target) {
                left++; // Need a larger sum
            } else {
                right--; // Need a smaller sum
            }
        }

        return new int[0];
    }

    public static void main(String[] args) {
        int[] nums = {2, 7, 11, 15};
        int target = 9;

        int[] indices = twoSumIndices(nums, target);
        System.out.println("Array: " + Arrays.toString(nums) + ", Target: " + target);
        System.out.println("Pair Indices: " + Arrays.toString(indices)); // [0, 1]
        System.out.println("Values: " + nums[indices[0]] + " + " + nums[indices[1]] + " = " + target);

        // Sorted two-pointer demo
        int[] sorted = {1, 3, 4, 6, 8, 11, 15};
        int targetSum = 14;
        int[] pairValues = twoSumSorted(sorted, targetSum);
        System.out.println("\nSorted Array Pair for target " + targetSum + ": " + Arrays.toString(pairValues)); // [3, 11]
    }
}
