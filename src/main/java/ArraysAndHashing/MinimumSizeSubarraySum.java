package ArraysAndHashing;

import java.util.*;

/**
 * Problem: 209. Minimum Size Subarray Sum
 * ---------------------------------------
 * Given an array of positive integers nums and a positive integer target,
 * return the minimal length of a subarray whose sum is greater than or equal to target.
 * If there is no such subarray, return 0 instead.
 *
 * Example 1:
 * Input  : target = 7, nums = [2,3,1,2,4,3]
 * Output : 2
 * Explanation: The subarray [4,3] has sum 7 and length 2.
 *
 * Example 2:
 * Input  : target = 4, nums = [1,4,4]
 * Output : 1
 *
 * Example 3:
 * Input  : target = 11, nums = [1,1,1,1,1,1,1,1]
 * Output : 0
 *
 * Constraints:
 * - 1 <= target <= 10^9
 * - 1 <= nums.length <= 10^5
 * - 1 <= nums[i] <= 10^4
 *
 * Approaches Implemented:
 * 1. Optimal Sliding Window Approach (O(n), O(1))
 * 2. Brute Force Approach (O(n^2), O(1))
 */
public class MinimumSizeSubarraySum {

    /**
     * Algorithm 1: Optimal Sliding Window
     * -----------------------------------
     * Idea:
     * - Use two pointers (start, end) to maintain a window.
     * - Expand end to increase sum.
     * - Shrink start while sum >= target, update minLength.
     *
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     * Concept: Sliding Window.
     */
    public int minSubArrayLenOptimal(int target, int[] nums) {
        int n = nums.length;
        int left = 0, sum = 0;
        int minLength = Integer.MAX_VALUE;

        for (int right = 0; right < n; right++) {
            sum += nums[right];
            while (sum >= target) {
                minLength = Math.min(minLength, right - left + 1);
                sum -= nums[left];
                left++;
            }
        }
        return minLength == Integer.MAX_VALUE ? 0 : minLength;
    }

    /**
     * Algorithm 2: Brute Force Approach
     * ---------------------------------
     * Idea:
     * - Try all subarrays.
     * - For each start index, expand end index until sum >= target.
     * - Track minimal length.
     *
     * Time Complexity: O(n^2)
     * Space Complexity: O(1)
     * Concept: Simple enumeration.
     */
    public int minSubArrayLenBrute(int target, int[] nums) {
        int n = nums.length;
        int minLength = Integer.MAX_VALUE;

        for (int i = 0; i < n; i++) {
            int sum = 0;
            for (int j = i; j < n; j++) {
                sum += nums[j];
                if (sum >= target) {
                    minLength = Math.min(minLength, j - i + 1);
                    break; // no need to expand further
                }
            }
        }
        return minLength == Integer.MAX_VALUE ? 0 : minLength;
    }
}
