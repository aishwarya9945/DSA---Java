package ArraysAndHashing;

import java.util.HashSet;
import java.util.Set;

/**
 * Program to check if an array contains duplicate elements within k distance.
 * Demonstrates:
 * 1. Optimal Approach (HashSet + Sliding Window)
 * 2. Brute Force Approach
 *
 * Problem:
 * Given an integer array `nums` and an integer `k`, return true if there are two distinct indices
 * i and j in the array such that nums[i] == nums[j] and |i - j| <= k.
 *
 * Example:
 * Input  : nums = [1, 2, 5, 8, 9], k = 9
 * Output : false
 *
 * Input  : nums = [1, 2, 3, 1], k = 3
 * Output : true
 */
public class ContainDuplicatesII {

    public static void main(String[] args) {
        int[] nums = {1, 2, 5, 8, 9};
        int k = 9;

        // ---------- Optimal Sliding Window Approach ----------
        boolean resultOptimal = containsDuplicatesOptimal(nums, k);
        System.out.println("Contains Nearby Duplicate (Optimal): " + resultOptimal);

        // ---------- Brute Force Approach ----------
        boolean resultBrute = containsDuplicatesBrute(nums, k);
        System.out.println("Contains Nearby Duplicate (Brute Force): " + resultBrute);
    }

    /**
     * Algorithm 1: Optimal Approach (Sliding Window + HashSet)
     * ---------------------------------------------------------
     * Idea:
     * Use a sliding window of size `k` to track recent elements using a HashSet.
     * If a duplicate appears within the current window, return true.
     *
     * Steps:
     * - Initialize a HashSet to represent the sliding window.
     * - Iterate over elements:
     *     - If the current element exists in set → duplicate found → return true.
     *     - Add element to set.
     *     - If set size > k → remove element that slid out of window (i - k).
     * - Return false if no duplicates were found.
     *
     * Time Complexity: O(n)
     * Space Complexity: O(k)
     * Concept: Use HashSet for O(1) lookup while maintaining a window of last k elements.
     */
    public static boolean containsDuplicatesOptimal(int[] nums, int k) {
        // HashSet acts as window to hold at most 'k' previous elements
        Set<Integer> window = new HashSet<>();

        // Iterate over each array element
        for (int i = 0; i < nums.length; i++) {

            // Check if current number already seen in window
            if (window.contains(nums[i])) {
                return true; // Duplicate found within distance k
            }

            // Add current element to window
            window.add(nums[i]);

            // Maintain window size constraint (max 'k' elements)
            if (window.size() > k) {
                window.remove(nums[i - k]); // Remove element that goes out of range k
            }
        }

        // No duplicates found within k distance
        return false;
    }

    /**
     * Algorithm 2: Brute Force Approach
     * ---------------------------------
     * Idea:
     * For each element, check next k elements to see if there's a duplicate.
     * Return true immediately when one is found.
     *
     * Steps:
     * - Loop through each index i in array.
     * - For each i, check up to next k elements:
     *     - If nums[i] == nums[j] → return true.
     * - Return false after complete traversal.
     *
     * Time Complexity: O(n × k)
     * Space Complexity: O(1)
     * Concept: Exhaustive window checking via nested loops.
     */
    public static boolean containsDuplicatesBrute(int[] nums, int k) {
        // Outer loop for each element in array
        for (int i = 0; i < nums.length; i++) {
            // Inner loop: compare with next k elements
            for (int j = i + 1; j <= i + k && j < nums.length; j++) {
                if (nums[i] == nums[j]) {
                    return true;  // Found duplicate within distance k
                }
            }
        }

        // No such duplicate pairs found
        return false;
    }
}
