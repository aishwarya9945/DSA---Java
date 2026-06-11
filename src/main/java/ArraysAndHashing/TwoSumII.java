package ArraysAndHashing;

import java.util.Arrays;

/**
 * Program to solve the Two Sum II problem using:
 * 1. Optimal Two-Pointer Approach
 * 2. Brute Force Approach
 *
 * Problem:
 * Given a sorted integer array and a target value,
 * find two indices (1-based) such that their values add up to the target.
 *
 * Example:
 * Input:  nums = [2, 7, 11, 15], target = 9
 * Output: [1, 2]
 *
 * Concept:
 * - This is a variation of the classic Two Sum problem.
 * - The key difference: the input array is sorted.
 * - Because of sorting, we can use the **two-pointer technique**:
 *      → Start with one pointer at the beginning and one at the end.
 *      → If the sum is too small, move the left pointer right.
 *      → If the sum is too large, move the right pointer left.
 *      → If the sum matches the target, return the indices.
 */
public class TwoSumII {

    public static void main(String[] args) {
        int[] nums = {2, 7, 11, 15};  // Input array (sorted)
        int target = 9;               // Target value

        // ---------- Optimal Two-Pointer Approach ----------
        int[] optimalResult = twoSumOptimal(nums, target);
        System.out.println("Two Sum II (Two-Pointer): " + Arrays.toString(optimalResult));

        // ---------- Brute Force Approach ----------
        int[] bruteResult = twoSumBrute(nums, target);
        System.out.println("Two Sum II (Brute Force): " + Arrays.toString(bruteResult));
    }

    /**
     * Algorithm 1: Optimal Approach — Two Pointers
     * --------------------------------------------
     * Steps:
     * 1. Initialize two pointers: left = 0, right = nums.length - 1.
     * 2. While left < right:
     *      - Compute sum = nums[left] + nums[right].
     *      - If sum == target → return [left+1, right+1].
     *      - If sum < target → move left++.
     *      - Else → move right--.
     * 3. Return [-1, -1] if no pair found.
     *
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     * Concept: Two-pointer technique on sorted array.
     */
    public static int[] twoSumOptimal(int[] nums, int target) {
        int left = 0, right = nums.length - 1;

        while (left < right) {
            int sum = nums[left] + nums[right];

            if (sum == target) {
                return new int[]{left + 1, right + 1}; // 1-based indices
            } else if (sum < target) {
                left++;
            } else {
                right--;
            }
        }

        return new int[]{-1, -1};
    }

    /**
     * Algorithm 2: Brute Force Approach
     * ---------------------------------
     * Steps:
     * 1. Loop through each element i.
     * 2. For each element, loop through remaining elements j.
     * 3. If nums[i] + nums[j] == target → return [i+1, j+1].
     *    Else continue until all pairs checked.
     *
     * Time Complexity: O(n²)
     * Space Complexity: O(1)
     * Concept: Nested loop comparison — brute force.
     */
    public static int[] twoSumBrute(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[]{i + 1, j + 1};   // 1-based indices
                }
            }
        }
        return new int[]{-1, -1};
    }
}
