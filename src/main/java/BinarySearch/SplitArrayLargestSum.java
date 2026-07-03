package BinarySearch;

import java.util.*;

/**
 * Problem: 410. Split Array Largest Sum
 * -------------------------------------
 * Given an array nums which consists of non-negative integers and an integer m,
 * you can split the array into m non-empty continuous subarrays.
 *
 * Write an algorithm to minimize the largest sum among these m subarrays.
 *
 * Example 1:
 * Input  : nums = [7,2,5,10,8], m = 2
 * Output : 18
 * Explanation: Split into [7,2,5] and [10,8], largest sum = 18.
 *
 * Example 2:
 * Input  : nums = [1,2,3,4,5], m = 2
 * Output : 9
 *
 * Constraints:
 * - 1 <= nums.length <= 1000
 * - 0 <= nums[i] <= 10^6
 * - 1 <= m <= min(50, nums.length)
 *
 * Approaches Implemented:
 * 1. Optimal Binary Search + Greedy Check (O(n log(sum)), O(1))
 * 2. Brute Force DP Approach (O(n^2 * m), O(n*m))
 */
public class SplitArrayLargestSum {

    /**
     * Algorithm: Optimal Binary Search + Greedy
     * -----------------------------------------
     * Idea:
     * - The answer lies between:
     *      low  = max(nums)   (at least one subarray must contain the largest element)
     *      high = sum(nums)   (all elements in one subarray)
     * - Use binary search on this range.
     * - For each mid, check feasibility:
     *      Can we split into ≤ m subarrays with max sum ≤ mid?
     *      → Greedy check: keep adding elements until sum > mid, then start new subarray.
     *
     * Steps:
     * 1. low = max(nums), high = sum(nums).
     * 2. While low < high:
     *      mid = (low + high) / 2
     *      If feasible(mid) → high = mid
     *      Else → low = mid + 1
     * 3. Return low (minimum largest subarray sum).
     *
     * Mnemonic:
     * "Painter’s Partition Problem → Balance Load"
     *
     * Time Complexity: O(n log(sum(nums)))
     * Space Complexity: O(1)
     */
    public static int splitArrayOptimal(int[] nums, int k) {
        int low = 0, high = 0;
        for (int num : nums) {
            low = Math.max(low, num);  // largest element // this line finds max(nums)
            high += num;               // total sum // this line computes sum(nums)
        }

        // So the max(nums) part is mentioned implicitly:
        //low starts at 0.
        //For each element, we update low = Math.max(low, num).
        //After the loop, low holds the maximum element in the array.

        while (low < high) {
            int mid = low + (high - low) / 2;
            if (canSplit(nums, k, mid)) {
                high = mid;  // try smaller max sum
            } else {
                low = mid + 1;  // need larger max sum
            }
        }
        return low;
    }

    private static boolean canSplit(int[] nums, int k, int maxSum) {
        int count = 1;        // start with 1 subarray (1 bucket)
        int currentSum = 0;   // current bucket sum

        for (int num : nums) {
            // If adding num exceeds allowed maxSum → start new subarray
            if (currentSum + num > maxSum) {
                count++;          // new bucket
                currentSum = num; // reset sum to current element

                // If we already need more than k subarrays → not feasible
                if (count > k) return false;
            } else {
                // Otherwise keep filling current bucket
                currentSum += num;
            }
        }
        return true; // feasible within k subarrays
    }

    /**
     * Algorithm 2: Brute Force DP Approach
     * ------------------------------------
     * Idea:
     * - dp[i][k] = minimum largest sum splitting first i elements into k parts.
     * - Transition:
     *   dp[i][k] = min_{j < i}( max(dp[j][k-1], sum(j+1..i)) )
     *
     * Steps:
     * - Precompute prefix sums.
     * - Fill dp table.
     *
     * Time Complexity: O(n^2 * m)
     * Space Complexity: O(n*m)
     * Concept: Dynamic Programming.
     */
    public int splitArrayBrute(int[] nums, int m) {
        int n = nums.length;
        int[] prefix = new int[n + 1];
        for (int i = 0; i < n; i++) prefix[i + 1] = prefix[i] + nums[i];

        int[][] dp = new int[n + 1][m + 1];
        for (int[] row : dp) Arrays.fill(row, Integer.MAX_VALUE);
        dp[0][0] = 0;

        for (int i = 1; i <= n; i++) {
            for (int k = 1; k <= m; k++) {
                for (int j = 0; j < i; j++) {
                    int largest = Math.max(dp[j][k - 1], prefix[i] - prefix[j]);
                    dp[i][k] = Math.min(dp[i][k], largest);
                }
            }
        }
        return dp[n][m];
    }
}
