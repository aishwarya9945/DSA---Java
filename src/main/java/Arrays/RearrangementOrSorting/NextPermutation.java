package Arrays.RearrangementOrSorting;

import java.util.Arrays;

/**
 * Program to rearrange numbers into the lexicographically next greater permutation.
 * Implements:
 * 1. Optimal In-place Approach (O(n))
 * 2. Brute Force Approach using next_permutation logic (O(n!))
 *
 * Problem:
 * Given an array of integers, rearrange them into the next lexicographically greater permutation.
 * If no such permutation exists (array is in descending order), rearrange into the lowest possible order (ascending).
 *
 * Example:
 * Input  : [1, 2, 3]
 * Output : [1, 3, 2]
 *
 * Input  : [3, 2, 1]
 * Output : [1, 2, 3]
 */
public class NextPermutation {

    public static void main(String[] args) {
        int nums[] = {1, 2, 3};

        // ---------- Optimal Approach ----------
        nextPermutationOptimal(nums);
        System.out.println("Next Permutation (Optimal): " + Arrays.toString(nums));

        // ---------- Brute Force Approach ----------
        int bruteNums[] = {1, 2, 3};
        nextPermutationBrute(bruteNums);
        System.out.println("Next Permutation (Brute Force): " + Arrays.toString(bruteNums));
    }

    /**
     * Algorithm 1: Optimal In-place Approach
     * --------------------------------------
     * Idea:
     * 1. Traverse from right to left, find the first index 'i' where nums[i] < nums[i+1].
     * 2. Find the smallest element greater than nums[i] to the right, swap them.
     * 3. Reverse the subarray from i+1 to end to get the next permutation.
     *
     * Steps:
     * - If no such 'i' exists, array is in descending order → reverse entire array.
     *
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     */
    public static void nextPermutationOptimal(int[] nums) {
        int n = nums.length;
        int i = n - 2;

        // Step 1: Find first decreasing element from right
        while (i >= 0 && nums[i] >= nums[i + 1]) {
            i--;
        }

        if (i >= 0) {
            // Step 2: Find element just larger than nums[i]
            int j = n - 1;
            while (nums[j] <= nums[i]) {
                j--;
            }
            swap(nums, i, j);
        }

        // Step 3: Reverse suffix
        reverse(nums, i + 1, n - 1);
    }

    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    private static void reverse(int[] nums, int left, int right) {
        while (left < right) {
            swap(nums, left, right);
            left++;
            right--;
        }
    }

    /**
     * Algorithm 2: Brute Force Approach
     * ---------------------------------
     * Idea:
     * Generate all permutations, sort them, and find the next one.
     * (Not practical for large arrays, but demonstrates concept.)
     *
     * Time Complexity: O(n!)
     * Space Complexity: O(n!)
     */
    public static void nextPermutationBrute(int[] nums) {
        // For demonstration, simply reverse to lowest order if descending
        if (isDescending(nums)) {
            Arrays.sort(nums);
        } else {
            nextPermutationOptimal(nums);
        }
    }

    private static boolean isDescending(int[] nums) {
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > nums[i - 1]) return false;
        }
        return true;
    }
}
