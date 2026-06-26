package ArraysAndHashing;

import java.util.*;

/**
 * Problem: 259. 3Sum Smaller
 * ---------------------------
 * Given an integer array nums and an integer target,
 * return the number of index triplets (i, j, k) such that
 * i < j < k and nums[i] + nums[j] + nums[k] < target.
 *
 * Example 1:
 * Input  : nums = [-2,0,1,3], target = 2
 * Output : 2
 * Explanation: Two triplets are:
 *   (-2, 0, 1) → sum = -1 < 2
 *   (-2, 0, 3) → sum = 1 < 2
 *
 * Example 2:
 * Input  : nums = [0,0,0], target = 1
 * Output : 1
 *
 * Constraints:
 * - 3 <= nums.length <= 3000
 * - -100 <= nums[i] <= 100
 * - -10^4 <= target <= 10^4
 *
 * Approaches Implemented:
 * 1. Optimal Two-Pointer Approach (O(n^2), O(1))
 * 2. Brute Force Approach (O(n^3), O(1))
 */
public class ThreeSumSmaller {

    /**
     * Algorithm 1: Optimal Two-Pointer Approach
     * -----------------------------------------
     * Idea:
     * - Sort the array.
     * - Fix one element (i).
     * - Use two pointers (left, right) to count valid pairs.
     * - If nums[i] + nums[left] + nums[right] < target:
     *   → all pairs between left and right are valid.
     *   → add (right - left) to count.
     * - Otherwise, move right inward.
     *
     * Time Complexity: O(n^2)
     * Space Complexity: O(1)
     * Concept: Sorting + Two Pointers
     */
    public int threeSumSmallerOptimal(int[] nums, int target) {
        Arrays.sort(nums);
        int count = 0;

        for (int i = 0; i < nums.length - 2; i++) {
            int left = i + 1, right = nums.length - 1;

            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                if (sum < target) {
                    count += (right - left); // all pairs valid
                    left++;
                } else {
                    right--;
                }
            }
        }
        return count;
    }

    /**
     * Algorithm 2: Brute Force Approach
     * ---------------------------------
     * Idea:
     * - Try all triplets (i, j, k).
     * - Count if sum < target.
     *
     * Time Complexity: O(n^3)
     * Space Complexity: O(1)
     * Concept: Simple enumeration.
     */
    public int threeSumSmallerBrute(int[] nums, int target) {
        int count = 0;
        int n = nums.length;

        for (int i = 0; i < n - 2; i++) {
            for (int j = i + 1; j < n - 1; j++) {
                for (int k = j + 1; k < n; k++) {
                    if (nums[i] + nums[j] + nums[k] < target) {
                        count++;
                    }
                }
            }
        }
        return count;
    }
}
