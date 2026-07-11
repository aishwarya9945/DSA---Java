package Arrays.TwoPointers;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

/**
 * Program to solve the 3Sum problem using:
 * 1. Optimal Two-Pointer Approach
 * 2. Brute Force Approach
 *
 * Problem:
 * Given an integer array nums, return all unique triplets [nums[i], nums[j], nums[k]]
 * such that i != j, i != k, j != k and nums[i] + nums[j] + nums[k] == 0.
 *
 * Example:
 * Input:  nums = [-1, 0, 1, 2, -1, -4]
 * Output: [[-1, -1, 2], [-1, 0, 1]]
 *
 * Concept:
 * - This is an extension of the Two Sum problem.
 * - Instead of finding two numbers that sum to target, we find three numbers.
 * - Optimal solution uses sorting + two-pointer technique:
 *      → Fix one element, then solve Two Sum II on the remaining subarray.
 *      → Skip duplicates to avoid repeated triplets.
 */
public class ThreeSum {

    public static void main(String[] args) {
        int[] nums = {-1, 0, 1, 2, -1, -4};  // Input array

        // ---------- Optimal Two-Pointer Approach ----------
        List<List<Integer>> optimalResult = threeSumOptimal(nums);
        System.out.println("3Sum (Optimal Two-Pointer): " + optimalResult);

        // ---------- Brute Force Approach ----------
        List<List<Integer>> bruteResult = threeSumBrute(nums);
        System.out.println("3Sum (Brute Force): " + bruteResult);
    }

    /**
     * Algorithm 1: Optimal Approach — Sorting + Two Pointers
     * ------------------------------------------------------
     * Steps:
     * 1. Sort the array.
     * 2. Loop i from 0 to n‑2 (so last valid index is n‑3):
     *      - Skip duplicates for i.
     *      - Set left = i+1, right = n-1.
     *      - While left < right:
     *          → Compute sum = nums[i] + nums[left] + nums[right].
     *          → If sum == 0 → add triplet, move left/right skipping duplicates.
     *          → If sum < 0 → move left++.
     *          → If sum > 0 → move right--.
     * 3. Return list of unique triplets.
     *
     * Time Complexity: O(n²)
     * Space Complexity: O(1) (excluding output list)
     * Concept: Fix one element, then apply Two Sum II.
     */
    public static List<List<Integer>> threeSumOptimal(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();

        for (int i = 0; i < nums.length - 2; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) continue; // Skip duplicate i

            int left = i + 1, right = nums.length - 1;

            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];

                if (sum == 0) {
                    result.add(Arrays.asList(nums[i], nums[left], nums[right]));

                    // Skip duplicates for left and right
                    while (left < right && nums[left] == nums[left - 1]) left++;
                    while (left < right && nums[right] == nums[right + 1]) right--;

                    left++;
                    right--;

                } else if (sum < 0) {
                    left++;
                } else {
                    right--;
                }
            }
        }

        return result;
    }

    /**
     * Algorithm 2: Brute Force Approach
     * ---------------------------------
     * Steps:
     * 1. Loop i from 0 to n-3.
     * 2. Loop j from i+1 to n-2.
     * 3. Loop k from j+1 to n-1.
     *      - If nums[i] + nums[j] + nums[k] == 0 → add triplet.
     * 4. Return list of triplets (may contain duplicates).
     *
     * Time Complexity: O(n³)
     * Space Complexity: O(1) (excluding output list)
     * Concept: Check all possible triplets — brute force.
     */
    public static List<List<Integer>> threeSumBrute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();

        for (int i = 0; i < nums.length - 2; i++) {
            for (int j = i + 1; j < nums.length - 1; j++) {
                for (int k = j + 1; k < nums.length; k++) {
                    if (nums[i] + nums[j] + nums[k] == 0) {
                        result.add(Arrays.asList(nums[i], nums[j], nums[k]));
                    }
                }
            }
        }

        return result;
    }
}
