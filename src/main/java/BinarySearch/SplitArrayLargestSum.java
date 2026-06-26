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
     * Algorithm 1: Optimal Binary Search + Greedy
     * -------------------------------------------
     * Idea:
     * - The answer lies between max(nums) and sum(nums).
     * - Use binary search on this range.
     * - For each mid, check if we can split into <= m subarrays
     *   with largest sum <= mid using greedy.
     *
     * Steps:
     * 1. low = max(nums), high = sum(nums).
     * 2. While low < high:
     *    - mid = (low + high)/2
     *    - If feasible(mid) → high = mid
     *    - Else → low = mid + 1
     * 3. Return low.
     *
     * Time Complexity: O(n log(sum(nums)))
     * Space Complexity: O(1)
     * Concept: Binary Search on Answer + Greedy validation.
     */
    public int splitArrayOptimal(int[] nums, int m) {
        int max = 0, sum = 0;
        for (int num : nums) {
            max = Math.max(max, num);
            sum += num;
        }

        int low = max, high = sum;
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (canSplit(nums, m, mid)) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }

    private boolean canSplit(int[] nums, int m, int maxSumAllowed) {
        int count = 1, currentSum = 0;
        for (int num : nums) {
            if (currentSum + num > maxSumAllowed) {
                count++;
                currentSum = num;
                if (count > m) return false;
            } else {
                currentSum += num;
            }
        }
        return true;
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
