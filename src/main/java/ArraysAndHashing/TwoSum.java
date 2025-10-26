package ArraysAndHashing;

import java.util.Arrays;
import java.util.HashMap;

/**
 * Program to solve the Two Sum problem using:
 * 1. Optimal HashMap Approach
 * 2. Brute Force Approach
 *
 * Problem:
 * Given an integer array and a target value,
 * find two indices such that their values add up to the target.
 *
 * Example:
 * Input:  nums = [2, 7, 11, 15], target = 9
 * Output: [0, 1]
 */
public class TwoSum {

    public static void main(String[] args) {
        int[] nums = {2, 7, 11, 15};  // Input array
        int target = 9;               // Target value

        // ---------- Optimal HashMap Approach ----------
        int[] optimalResult = twoSumOptimal(nums, target);
        System.out.println("Two Sum (Optimal HashMap): " + Arrays.toString(optimalResult));

        // ---------- Brute Force Approach ----------
        int[] bruteResult = twoSumBrute(nums, target);
        System.out.println("Two Sum (Brute Force): " + Arrays.toString(bruteResult));
    }

    /**
     * Algorithm 1: Optimal Approach — Using HashMap
     * ---------------------------------------------
     * Idea:
     * Use a HashMap to store each element's value and its index while traversing the array.
     * For each element, compute the difference (complement) required to reach the target.
     * If the complement already exists in the map, return the indices.
     *
     * Steps:
     * - Initialize a HashMap to store value → index.
     * - Traverse the array:
     *      - For each element, compute complement = target - nums[i].
     *      - If complement exists in the map → return indices.
     *      - Else → store current element and its index in the map.
     * - Return [-1, -1] if no pair found.
     *
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     * Concept: Hashing for constant-time lookup.
     */
    public static int[] twoSumOptimal(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap<>();   // Map to store value → index

        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];             // Difference needed to reach target

            // Check if complement already exists in map
            if (map.containsKey(complement)) {
                // Found the two indices that sum up to target
                return new int[]{map.get(complement), i};
            }

            // Otherwise store current number along with its index
            map.put(nums[i], i);
        }

        // Should never reach here per problem constraints (guarantees exactly one solution)
        return new int[]{-1, -1};
    }

    /**
     * Algorithm 2: Brute Force Approach
     * ---------------------------------
     * Idea:
     * Check all possible pairs in the array and return the first pair that sums up to the target.
     *
     * Steps:
     * - Loop through each element i.
     * - For each element, loop through remaining elements j.
     * - If nums[i] + nums[j] == target:
     *        → Return indices [i, j].
     * - Return [-1, -1] if no pair is found.
     *
     * Time Complexity: O(n²)
     * Space Complexity: O(1)
     * Concept: Nested loop comparison — brute force.
     */
    public static int[] twoSumBrute(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {       // Outer loop selects the first number
            for (int j = i + 1; j < nums.length; j++) { // Inner loop selects the second number
                if (nums[i] + nums[j] == target) {    // Condition check
                    return new int[]{i, j};           // Return matching indices
                }
            }
        }
        return new int[]{-1, -1};                    // Return default if not found
    }
}
