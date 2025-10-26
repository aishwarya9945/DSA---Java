package ArraysAndHashing;

import java.util.HashSet;
import java.util.Set;

/**
 * Program to check if an array contains any duplicate elements
 * using both Optimal (HashSet) and Brute Force approaches.
 *
 * Problem:
 * Given an integer array, return true if any value appears
 * at least twice in the array, and return false if every
 * element is distinct.
 *
 * Example:
 * Input  : [1, 2, 3, 4, 4, 5, 6, 6, 5, 9]
 * Output : true
 */
public class ContainDuplicates {

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4, 4, 5, 6, 6, 5, 9};

        // ---------- Optimal HashSet Approach ----------
        boolean hasDuplicateOptimal = hasDuplicateOptimal(nums);
        System.out.println("Contains Duplicate (Optimal): " + hasDuplicateOptimal);

        // ---------- Brute Force Approach ----------
        boolean hasDuplicateBrute = hasDuplicateBrute(nums);
        System.out.println("Contains Duplicate (Brute Force): " + hasDuplicateBrute);
    }

    /**
     * Algorithm 1: Optimal HashSet Approach
     * -------------------------------------
     * Idea:
     * Traverse the array and add each element to a HashSet.
     * If any element is already in the set, return true.
     * If traversal completes without duplicates, return false.
     *
     * Steps:
     * - Initialize empty HashSet.
     * - For each element in the array:
     *     - If found in set, return true.
     *     - Else, add to set.
     * - Return false if completed with no duplicates.
     *
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     * Concept: Hashing for constant-time membership test.
     */
    public static boolean hasDuplicateOptimal(int[] nums) {
        Set<Integer> seen = new HashSet<>();         // Holds already seen numbers

        for (int num : nums) {                       // Traverse all numbers
            if (seen.contains(num)) {                // If num already present → duplicate
                return true;
            }
            seen.add(num);                           // Otherwise add to set for future checks
        }
        return false;                                // No duplicate found
    }

    /**
     * Algorithm 2: Brute Force Approach
     * ---------------------------------
     * Idea:
     * For every pair of indices, check if the elements are equal.
     * If any match is found, return true, else return false.
     *
     * Steps:
     * - Loop through each element by index i.
     * - For each i, loop from j = i+1 to end.
     *     - If nums[i] == nums[j], return true.
     * - If finished, return false.
     *
     * Time Complexity: O(n²)
     * Space Complexity: O(1)
     * Concept: Exhaustive pairwise comparison.
     */
    public static boolean hasDuplicateBrute(int[] nums) {
        for (int i = 0; i < nums.length; i++) {      // Outer loop: first element
            for (int j = i + 1; j < nums.length; j++) { // Inner loop: check all other elements
                if (nums[i] == nums[j]) {                // If match found, return true
                    return true;
                }
            }
        }
        return false;                                // No duplicate found
    }
}
