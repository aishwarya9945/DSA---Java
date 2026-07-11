package Arrays.SlidingWindow;

import java.util.*;

public class SubarraysWithKDifferentIntegers {

    /**
     * Algorithm: Optimal Sliding Window + AtMost Helper
     * -------------------------------------------------
     * Mnemonic: "AtMost(K) - AtMost(K-1)."
     *
     * Idea:
     * - Count subarrays with at most K distinct integers.
     * - Count subarrays with at most (K-1) distinct integers.
     * - Their difference = subarrays with exactly K distinct integers.
     *
     * Time Complexity: O(n)
     * Space Complexity: O(n) (map for frequencies)
     */
    public static int subarraysWithKDistinctOptimal(int[] nums, int k) {
        return atMost(nums, k) - atMost(nums, k - 1);
    }

    // helper method: count subarrays with at most K distinct
    private static int atMost(int[] nums, int k) {
        Map<Integer, Integer> freq = new HashMap<>();
        int left = 0, count = 0;

        for (int right = 0; right < nums.length; right++) {
            freq.put(nums[right], freq.getOrDefault(nums[right], 0) + 1);

            while (freq.size() > k) {
                freq.put(nums[left], freq.get(nums[left]) - 1);
                if (freq.get(nums[left]) == 0) freq.remove(nums[left]);
                left++;
            }

            count += right - left + 1; // all subarrays ending at right
        }

        return count;
    }

    /**
     * Algorithm: Brute Force
     * ----------------------
     * Idea:
     * - Try all subarrays.
     * - Count distinct elements in each.
     * - If distinct == K, increment result.
     *
     * Time Complexity: O(n^2)
     * Space Complexity: O(n)
     */
    public static int subarraysWithKDistinctBruteForce(int[] nums, int k) {
        int n = nums.length;
        int result = 0;

        for (int i = 0; i < n; i++) {
            Set<Integer> set = new HashSet<>();
            for (int j = i; j < n; j++) {
                set.add(nums[j]);
                if (set.size() == k) result++;
                else if (set.size() > k) break; // no need to continue
            }
        }

        return result;
    }

    /**
     * Main method for testing
     */
    public static void main(String[] args) {
        int[] nums = {1,2,1,2,3};
        int k = 2;

        System.out.println("Optimal Sliding Window Result: " + subarraysWithKDistinctOptimal(nums, k)); // Expected: 7
        System.out.println("Brute Force Result: " + subarraysWithKDistinctBruteForce(nums, k));
    }
}
