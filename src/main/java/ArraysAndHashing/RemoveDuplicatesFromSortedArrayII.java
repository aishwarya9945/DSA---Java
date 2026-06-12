package ArraysAndHashing;

import java.util.Arrays;

/**
 * Program to remove duplicates from a sorted array in-place,
 * allowing each unique element to appear at most twice.
 * Implements:
 * 1. Optimal Two-Pointer Approach (O(n))
 * 2. Brute Force Approach using extra space (O(n))
 *
 * Problem:
 * Given an integer array nums sorted in non-decreasing order,
 * remove duplicates in-place such that each unique element appears at most twice.
 * Return the new length of the array after removal.
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
     * Use a write pointer (i) to track the position where the next valid element should go.
     * For each element, check if it can be placed:
     *  - If i < 2 → always place (first two elements are always valid).
     *  - Else → place only if nums[j] > nums[i-2] (ensures at most two duplicates).
     *
     * Steps:
     * 1. Initialize i = 0.
     * 2. Traverse j from 0 to end:
     *      - If i < 2 or nums[j] > nums[i-2], set nums[i] = nums[j], increment i.
     * 3. Return i as the new length.
     *
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     */
    public static int removeDuplicatesOptimal(int[] nums) {
        int i = 0;
        for (int num : nums) {
            if (i < 2 || num > nums[i - 2]) {
                nums[i] = num;
                i++;
            }
        }
        return i;
    }

    /**
     * Algorithm 2: Brute Force Approach
     * ---------------------------------
     * Idea:
     * Use a frequency map or counter to allow at most two occurrences,
     * then build a new array.
     *
     * Steps:
     * 1. Traverse array and count occurrences.
     * 2. Add element to result if count <= 2.
     *
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     */
    public static int[] removeDuplicatesBrute(int[] nums) {
        java.util.LinkedHashMap<Integer, Integer> freqMap = new java.util.LinkedHashMap<>();
        for (int num : nums) {
            freqMap.put(num, freqMap.getOrDefault(num, 0) + 1);
        }

        java.util.List<Integer> resultList = new java.util.ArrayList<>();
        for (int key : freqMap.keySet()) {
            int count = Math.min(freqMap.get(key), 2);
            for (int c = 0; c < count; c++) {
                resultList.add(key);
            }
        }

        int[] result = new int[resultList.size()];
        for (int i = 0; i < resultList.size(); i++) {
            result[i] = resultList.get(i);
        }
        return result;
    }
}
