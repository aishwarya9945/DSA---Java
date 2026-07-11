package Arrays.RearrangementOrSorting;

import java.util.Arrays;

/**
 * Program to remove duplicates from a sorted array in-place.
 * Implements:
 * 1. Optimal Two-Pointer Approach (O(n))
 * 2. Brute Force Approach using extra space (O(n))
 *
 * Problem:
 * Given an integer array nums sorted in non-decreasing order,
 * remove the duplicates in-place such that each unique element appears only once.
 * Return the new length of the array after removing duplicates.
 *
 * Example:
 * Input  : nums = [0,0,1,1,1,2,2,3,3,4]
 * Output : 5, nums = [0,1,2,3,4,...]
 */
public class RemoveDuplicatesFromSortedArray {

    public static void main(String[] args) {
        int nums[] = {0,0,1,1,1,2,2,3,3,4};

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
     * Use two pointers:
     *  - One pointer (i) tracks the position of the last unique element.
     *  - Another pointer (j) scans through the array.
     * When nums[j] != nums[i], increment i and copy nums[j] to nums[i+1].
     *
     * Steps:
     * 1. Initialize i = 0.
     * 2. Traverse j from 1 to end:
     *      - If nums[j] != nums[i], increment i and set nums[i] = nums[j].
     * 3. Return i+1 as the new length.
     *
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     */
    public static int removeDuplicatesOptimal(int[] nums) {
        if (nums.length == 0) return 0;

        int i = 0; // pointer for unique elements
        for (int j = 1; j < nums.length; j++) {
            if (nums[j] != nums[i]) {
                i++;
                nums[i] = nums[j];
            }
        }
        return i + 1;
    }

    /**
     * Algorithm 2: Brute Force Approach
     * ---------------------------------
     * Idea:
     * Use a temporary array or HashSet to store unique elements,
     * then copy them back.
     *
     * Steps:
     * 1. Traverse array and insert unique elements into a set or list.
     * 2. Copy back into nums.
     *
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     */
    public static int[] removeDuplicatesBrute(int[] nums) {
        java.util.LinkedHashSet<Integer> set = new java.util.LinkedHashSet<>();
        for (int num : nums) {
            set.add(num);
        }
        int[] result = new int[set.size()];
        int idx = 0;
        for (int num : set) {
            result[idx++] = num;
        }
        return result;
    }
}
