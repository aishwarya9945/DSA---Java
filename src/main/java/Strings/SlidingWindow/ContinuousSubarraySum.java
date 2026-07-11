package Arrays.PrefixSum;

import java.util.*;

public class ContinuousSubarraySum {

    /**
     * Algorithm: Optimal Prefix Sum + Modulo HashMap
     * ----------------------------------------------
     * Mnemonic: "Track prefix mod → Check earlier index."
     *
     * Idea:
     * - Maintain running prefix sum.
     * - Compute prefixSum % k (mod value).
     * - If the same mod value has appeared before, it means the subarray
     *   between those indices has sum divisible by k.
     * - Ensure subarray length ≥ 2.
     * - Use HashMap to store earliest index of each mod value.
     *
     * Time Complexity: O(n)
     * Space Complexity: O(k)
     */
    public static boolean checkSubarraySumOptimal(int[] nums, int k) {
        Map<Integer, Integer> modMap = new HashMap<>();
        modMap.put(0, -1); // base case: mod = 0 at index -1

        int prefixSum = 0;

        for (int i = 0; i < nums.length; i++) {
            prefixSum += nums[i];
            int mod = k == 0 ? prefixSum : prefixSum % k;

            if (modMap.containsKey(mod)) {
                // ensure subarray length ≥ 2
                if (i - modMap.get(mod) > 1) {
                    return true;
                }
            } else {
                modMap.put(mod, i); // store earliest index
            }
        }

        return false;
    }

    /**
     * Algorithm: Brute Force
     * ----------------------
     * Idea:
     * - Try all subarrays of length ≥ 2.
     * - Compute sum for each.
     * - If sum % k == 0, return true.
     *
     * Time Complexity: O(n^2)
     * Space Complexity: O(1)
     */
    public static boolean checkSubarraySumBruteForce(int[] nums, int k) {
        int n = nums.length;

        for (int i = 0; i < n; i++) {
            int sum = 0;
            for (int j = i; j < n; j++) {
                sum += nums[j];
                if (j - i + 1 >= 2) {
                    if (k == 0) {
                        if (sum == 0) return true;
                    } else {
                        if (sum % k == 0) return true;
                    }
                }
            }
        }

        return false;
    }

    /**
     * Main method for testing
     */
    public static void main(String[] args) {
        int[] nums = {23, 2, 4, 6, 7};
        int k = 6;

        System.out.println("Optimal Prefix Sum Result: " + checkSubarraySumOptimal(nums, k)); // Expected: true ([2,4], [4,6], etc.)
        System.out.println("Brute Force Result: " + checkSubarraySumBruteForce(nums, k));
    }
}
