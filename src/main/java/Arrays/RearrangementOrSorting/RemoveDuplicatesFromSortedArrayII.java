package Arrays.RearrangementOrSorting;

import java.util.Arrays;

/**
 * Problem: 80. Remove Duplicates from Sorted Array II
 * ---------------------------------------------------
 * Given an integer array nums sorted in non-decreasing order,
 * remove duplicates in-place such that each unique element appears at most twice.
 * Return the new length of the array after removal.
 *
 * Approaches:
 * 1. Optimal Two-Pointer (O(n), O(1))
 * 2. Brute Force Extra Array (O(n), O(n))
 *
 * Example:
 * Input  : nums = [0,0,1,1,1,1,2,3,3]
 * Output : 7, nums = [0,0,1,1,2,3,3,...]
 */
public class RemoveDuplicatesFromSortedArrayII {

    public static void main(String[] args) {
        int nums[] = {0,0,1,1,1,1,2,3,3};

        // ---------- Optimal Approach ----------
        int lengthOptimal = removeDuplicatesOptimal(nums);
        System.out.println("New Length (Optimal): " + lengthOptimal);
        System.out.println("Array after removal (Optimal): " + Arrays.toString(Arrays.copyOf(nums, lengthOptimal)));

        // ---------- Brute Force Approach ----------
        int[] bruteResult = removeDuplicatesBrute(nums);
        System.out.println("Array after removal (Brute Force): " + Arrays.toString(bruteResult));
    }

    /**
     * Algorithm 1: Optimal Two-Pointer Approach
     * -----------------------------------------
     * Idea:
     * - Keep a write pointer (left) that marks the position to overwrite.
     * - Start scanning from index 2 (since first two elements are always valid).
     * - For each nums[right]:
     *      - If nums[right] != nums[left - 2], copy it to nums[left] and move left.
     *
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     */
    public static int removeDuplicatesOptimal(int[] nums) {
        int n = nums.length;
        if (n <= 2) return n;

        int left = 2; // position to write next valid element
        for (int right = 2; right < n; right++) {
            if (nums[right] != nums[left - 2]) {
                nums[left] = nums[right];
                left++;
            }
        }
        return left;
    }

    /**
     * Algorithm 2: Brute Force Approach
     * ---------------------------------
     * Idea:
     * - Traverse array and allow at most two occurrences of each element.
     * - Build a new array with valid elements.
     *
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     */
    public static int[] removeDuplicatesBrute(int[] nums) {
        java.util.List<Integer> resultList = new java.util.ArrayList<>();
        int count = 1;
        resultList.add(nums[0]);

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[i - 1]) {
                count++;
            } else {
                count = 1;
            }
            if (count <= 2) {
                resultList.add(nums[i]);
            }
        }

        int[] result = new int[resultList.size()];
        for (int i = 0; i < resultList.size(); i++) {
            result[i] = resultList.get(i);
        }
        return result;
    }
}
