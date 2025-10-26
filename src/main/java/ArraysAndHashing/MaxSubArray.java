package ArraysAndHashing;

import java.util.Arrays;

/**
 * Program to find the Maximum Subarray Sum using:
 * 1. Optimal Kadane’s Algorithm
 * 2. Brute Force Approach
 *
 * Problem:
 * Given an integer array, find the contiguous subarray (containing at least one number)
 * which has the largest sum and return that sum.
 *
 * Example:
 * Input : [-2, 2, 34, 6, 7, -2]
 * Output: 49
 */
public class MaxSubArray {

    public static void main(String[] args) {
        int[] nums = {-2, 2, 34, 6, 7, -2};

        // ---------- Optimal Kadane’s Algorithm ----------
        int optimalSum = maxSubArrayKadane(nums);
        System.out.println("Maximum Subarray Sum (Optimal): " + optimalSum);

        // ---------- Brute Force Approach ----------
        int bruteSum = maxSubArrayBrute(nums);
        System.out.println("Maximum Subarray Sum (Brute Force): " + bruteSum);
    }

    /**
     * Algorithm 1: Optimal Approach (Kadane’s Algorithm)
     * --------------------------------------------------
     * Idea:
     * Kadane’s algorithm uses dynamic programming to keep track of the maximum subarray sum
     * ending at each index. It extends previous subarrays if profitable, or starts fresh otherwise.
     *
     * Steps:
     * - Initialize currentSum = 0 and maxSum = nums[0].
     * - Traverse each element in the array:
     *    - If currentSum < 0 → reset currentSum = 0 (discard negative carryover).
     *    - Add the current element to currentSum.
     *    - Update maxSum = max(maxSum, currentSum).
     * - Return maxSum as final result.
     *
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     * Concept: Dynamic Programming — cumulative tracking of max/end subarrays.
     */
    public static int maxSubArrayKadane(int[] nums) {
        // Keeps running total of current sum of subarray
        int currentSum = 0;

        // Stores the best (maximum) sum found so far
        int maxSum = nums[0];

        // Traverse each element in the array
        for (int i = 0; i < nums.length; i++) {

            // Discard negative sums — start fresh if currentSum < 0
            if (currentSum < 0) {
                currentSum = 0;
            }

            // Add the current element to the running sum
            currentSum += nums[i];

            // Update global max if new sum is greater
            maxSum = Math.max(maxSum, currentSum);
        }

        return maxSum;  // Return the maximum subarray sum
    }

    /**
     * Algorithm 2: Brute Force Approach
     * ---------------------------------
     * Idea:
     * Consider all possible subarrays and calculate their sums.
     * Keep track of the maximum sum encountered.
     *
     * Steps:
     * - Initialize maxSum with smallest possible value (Integer.MIN_VALUE).
     * - For every starting index i:
     *     - Initialize currentSum = 0.
     *     - For every ending index j ≥ i:
     *          - Add nums[j] to currentSum.
     *          - Update maxSum = max(maxSum, currentSum).
     * - Return maxSum.
     *
     * Time Complexity: O(n²)
     * Space Complexity: O(1)
     * Concept: Brute force — evaluate all subarray combinations.
     */
    public static int maxSubArrayBrute(int[] nums) {
        // Initialize maxSum to minimum integer value
        int maxSum = Integer.MIN_VALUE;

        // Outer loop: Select starting index
        for (int i = 0; i < nums.length; i++) {
            int currentSum = 0;  // Initialize sum for this subarray

            // Inner loop: Extend subarray ending at j
            for (int j = i; j < nums.length; j++) {
                currentSum += nums[j];  // Add next element to subarray

                // Track global max if needed
                maxSum = Math.max(maxSum, currentSum);
            }
        }

        return maxSum;  // Return maximum found sum
    }
}
