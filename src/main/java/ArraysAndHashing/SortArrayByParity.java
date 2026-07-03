package ArraysAndHashing;

import java.util.*;

/**
 * Problem: 905. Sort Array By Parity
 * ----------------------------------
 * Given an integer array nums, move all even integers to the front
 * of the array followed by all odd integers.
 *
 * Example 1:
 * Input  : nums = [3,1,2,4]
 * Output : [2,4,3,1] (order of evens/odds does not matter)
 *
 * Example 2:
 * Input  : nums = [0]
 * Output : [0]
 *
 * Constraints:
 * - 1 <= nums.length <= 5000
 * - 0 <= nums[i] <= 5000
 *
 * Approaches Implemented:
 * 1. Optimal Two-Pointer In-place (O(n), O(1))
 * 2. Brute Force Extra Array (O(n), O(n))
 */
public class SortArrayByParity {

    /**
     * Algorithm 1: Optimal Two-Pointer In-place
     * -----------------------------------------
     * Idea:
     * - Maintain two pointers: left and right.
     * - If nums[left] is odd and nums[right] is even → swap.
     * - Move pointers accordingly until they meet.
     *
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     * Concept: Two-pointer partitioning.
     */
    public int[] sortArrayByParityOptimal(int[] nums) {
        int left = 0, right = nums.length - 1;
        while (left < right) {
            if (nums[left] % 2 > nums[right] % 2) {
                int temp = nums[left];
                nums[left] = nums[right];
                nums[right] = temp;
            }
            if (nums[left] % 2 == 0) left++;
            if (nums[right] % 2 == 1) right--;
        }
        return nums;
    }

    /**
     * Algorithm 2: Brute Force Extra Array
     * ------------------------------------
     * Idea:
     * - Create a new array.
     * - First add all evens, then all odds.
     *
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     * Concept: Simple separation.
     */
    public int[] sortArrayByParityBrute(int[] nums) {
        int[] result = new int[nums.length];
        int idx = 0;
        for (int num : nums) {
            if (num % 2 == 0) result[idx++] = num;
        }
        for (int num : nums) {
            if (num % 2 == 1) result[idx++] = num;
        }
        return result;
    }

    // Main method for testing
    public static void main(String[] args) {
        SortArrayByParity solver = new SortArrayByParity();
        int[] nums = {3, 1, 2, 4};

        System.out.println("Optimal: " + Arrays.toString(solver.sortArrayByParityOptimal(nums.clone())));
        System.out.println("Brute  : " + Arrays.toString(solver.sortArrayByParityBrute(nums.clone())));
    }
}
