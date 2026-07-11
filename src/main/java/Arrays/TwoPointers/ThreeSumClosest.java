package Arrays.TwoPointers;

import java.util.*;

/**
 * Problem: 16. 3Sum Closest
 * --------------------------
 * Given an integer array nums of length n and an integer target,
 * find three integers in nums such that the sum is closest to target.
 * Return the sum of the three integers.
 *
 * Example 1:
 * Input  : nums = [-1,2,1,-4], target = 1
 * Output : 2
 * Explanation: The sum that is closest to 1 is 2 (-1 + 2 + 1).
 *
 * Example 2:
 * Input  : nums = [0,0,0], target = 1
 * Output : 0
 *
 * Constraints:
 * - 3 <= nums.length <= 500
 * - -1000 <= nums[i] <= 1000
 * - -10^4 <= target <= 10^4
 *
 * Approaches Implemented:
 * 1. Optimal Two-Pointer Approach (O(n^2), O(1))
 */
public class ThreeSumClosest {

    /**
     * Algorithm: Two-Pointer Approach
     * -------------------------------
     * Idea:
     * - Sort the array.
     * - Fix one element (i).
     * - Use two pointers (left, right) to find closest sum.
     * - Update closestSum if current sum is closer to target.
     *
     * Steps:
     * 1. Sort nums.
     * 2. Initialize closestSum = sum of first 3 elements.
     * 3. For each i:
     *    - left = i+1, right = n-1
     *    - While left < right:
     *      - Calculate currentSum
     *      - Update closestSum if closer
     *      - Move pointers based on comparison with target
     *
     * Time Complexity: O(n^2)
     * Space Complexity: O(1)
     * Concept: Sorting + Two Pointers
     */
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int closestSum = nums[0] + nums[1] + nums[2];

        for (int i = 0; i < nums.length - 2; i++) {
            int left = i + 1, right = nums.length - 1;

            while (left < right) {
                int currentSum = nums[i] + nums[left] + nums[right];

                if (Math.abs(currentSum - target) < Math.abs(closestSum - target)) {
                    closestSum = currentSum;
                }

                if (currentSum < target) {
                    left++;
                } else if (currentSum > target) {
                    right--;
                } else {
                    return currentSum; // exact match
                }
            }
        }
        return closestSum;
    }
}
