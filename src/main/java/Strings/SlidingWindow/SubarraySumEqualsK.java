package Arrays.PrefixSum;

import java.util.*;

public class SubarraySumEqualsK {

    /**
     * Algorithm: Optimal Prefix Sum + HashMap
     * ---------------------------------------
     * Mnemonic: "Track prefix → Check (sum - k)."
     *
     * Idea:
     * - Maintain running prefix sum.
     * - Use HashMap to store frequency of prefix sums.
     * - For each prefix sum, check if (prefixSum - k) exists in map.
     *   → If yes, it means a subarray with sum = k exists ending here.
     * - Add count accordingly.
     *
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     */
    public static int subarraySumOptimal(int[] nums, int k) {
        Map<Integer, Integer> prefixMap = new HashMap<>();
        prefixMap.put(0, 1); // base case: prefix sum = 0 occurs once

        int prefixSum = 0;
        int count = 0;

        for (int num : nums) {
            prefixSum += num;

            // check if (prefixSum - k) exists
            if (prefixMap.containsKey(prefixSum - k)) {
                count += prefixMap.get(prefixSum - k);
            }

            // update prefix sum frequency
            prefixMap.put(prefixSum, prefixMap.getOrDefault(prefixSum, 0) + 1);
        }

        return count;
    }

    /**
     * Algorithm: Brute Force
     * ----------------------
     * Idea:
     * - Try all subarrays.
     * - Compute sum for each.
     * - If sum == k, increment count.
     *
     * Time Complexity: O(n^2)
     * Space Complexity: O(1)
     */
    public static int subarraySumBruteForce(int[] nums, int k) {
        int n = nums.length;
        int count = 0;

        for (int i = 0; i < n; i++) {
            int sum = 0;
            for (int j = i; j < n; j++) {
                sum += nums[j];
                if (sum == k) count++;
            }
        }

        return count;
    }

    /**
     * Main method for testing
     */
    public static void main(String[] args) {
        int[] nums = {1,2,3};
        int k = 3;

        System.out.println("Optimal Prefix Sum Result: " + subarraySumOptimal(nums, k)); // Expected: 2 ([1,2], [3])
        System.out.println("Brute Force Result: " + subarraySumBruteForce(nums, k));
    }
}
