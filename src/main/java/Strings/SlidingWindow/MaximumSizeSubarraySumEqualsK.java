package Arrays.PrefixSum;

import java.util.*;

public class MaximumSizeSubarraySumEqualsK {

    /**
     * Algorithm: Optimal Prefix Sum + HashMap
     * ---------------------------------------
     * Mnemonic: "Track prefix → Check (sum - k) → Max length."
     *
     * Idea:
     * - Maintain running prefix sum.
     * - Use HashMap to store earliest index of each prefix sum.
     * - For each prefix sum, check if (prefixSum - k) exists in map.
     *   → If yes, subarray between those indices has sum = k.
     *   → Update max length accordingly.
     *
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     */
    public static int maxSubArrayLenOptimal(int[] nums, int k) {
        Map<Integer, Integer> prefixMap = new HashMap<>();
        int prefixSum = 0;
        int maxLen = 0;

        for (int i = 0; i < nums.length; i++) {
            prefixSum += nums[i];

            // case 1: whole prefix sum equals k
            if (prefixSum == k) {
                maxLen = i + 1;
            }

            // case 2: check if (prefixSum - k) exists
            if (prefixMap.containsKey(prefixSum - k)) {
                maxLen = Math.max(maxLen, i - prefixMap.get(prefixSum - k));
            }

            // store earliest index of prefixSum
            if (!prefixMap.containsKey(prefixSum)) {
                prefixMap.put(prefixSum, i);
            }
        }

        return maxLen;
    }

    /**
     * Algorithm: Brute Force
     * ----------------------
     * Idea:
     * - Try all subarrays.
     * - Compute sum for each.
     * - If sum == k, update max length.
     *
     * Time Complexity: O(n^2)
     * Space Complexity: O(1)
     */
    public static int maxSubArrayLenBruteForce(int[] nums, int k) {
        int n = nums.length;
        int maxLen = 0;

        for (int i = 0; i < n; i++) {
            int sum = 0;
            for (int j = i; j < n; j++) {
                sum += nums[j];
                if (sum == k) {
                    maxLen = Math.max(maxLen, j - i + 1);
                }
            }
        }

        return maxLen;
    }

    /**
     * Main method for testing
     */
    public static void main(String[] args) {
        int[] nums = {1, -1, 5, -2, 3};
        int k = 3;

        System.out.println("Optimal Prefix Sum Result: " + maxSubArrayLenOptimal(nums, k)); // Expected: 4 ([1,-1,5,-2])
        System.out.println("Brute Force Result: " + maxSubArrayLenBruteForce(nums, k));
    }
}
