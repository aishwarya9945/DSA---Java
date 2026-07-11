package Arrays.PrefixSum;

import java.util.*;

public class SubarraySumsDivisibleByK {

    /**
     * Algorithm: Optimal Prefix Sum + Modulo HashMap
     * ----------------------------------------------
     * Mnemonic: "Track prefix mod → Count matches."
     *
     * Idea:
     * - Maintain running prefix sum.
     * - Compute prefixSum % k (mod value).
     * - If the same mod value has appeared before, it means the subarray
     *   between those indices has sum divisible by k.
     * - Use HashMap to store frequency of mod values.
     * - Add counts accordingly.
     *
     * Time Complexity: O(n)
     * Space Complexity: O(k)
     */
    public static int subarraysDivByKOptimal(int[] nums, int k) {
        Map<Integer, Integer> modMap = new HashMap<>();
        modMap.put(0, 1); // base case: mod = 0 occurs once

        int prefixSum = 0;
        int count = 0;

        for (int num : nums) {
            prefixSum += num;
            int mod = ((prefixSum % k) + k) % k; // handle negative mods

            if (modMap.containsKey(mod)) {
                count += modMap.get(mod);
            }

            modMap.put(mod, modMap.getOrDefault(mod, 0) + 1);
        }

        return count;
    }

    /**
     * Algorithm: Brute Force
     * ----------------------
     * Idea:
     * - Try all subarrays.
     * - Compute sum for each.
     * - If sum % k == 0, increment count.
     *
     * Time Complexity: O(n^2)
     * Space Complexity: O(1)
     */
    public static int subarraysDivByKBruteForce(int[] nums, int k) {
        int n = nums.length;
        int count = 0;

        for (int i = 0; i < n; i++) {
            int sum = 0;
            for (int j = i; j < n; j++) {
                sum += nums[j];
                if (sum % k == 0) count++;
            }
        }

        return count;
    }

    /**
     * Main method for testing
     */
    public static void main(String[] args) {
        int[] nums = {4,5,0,-2,-3,1};
        int k = 5;

        System.out.println("Optimal Prefix Sum Result: " + subarraysDivByKOptimal(nums, k)); // Expected: 7
        System.out.println("Brute Force Result: " + subarraysDivByKBruteForce(nums, k));
    }
}
