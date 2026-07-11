package Arrays.SlidingWindow;

import java.util.TreeMap;

/**
 * Problem: 2762. Continuous Subarrays
 * -----------------------------------
 * You are given an integer array nums.
 * A continuous subarray is valid if the absolute difference between
 * any two elements in the subarray is <= 2.
 * Return the total number of valid continuous subarrays.
 *
 * Example:
 * Input  : nums = [5,4,2,4]
 * Output : 8
 * Explanation: Valid subarrays are [5], [4], [2], [4], [5,4], [4,2], [2,4], [4,2,4].
 *
 * Constraints:
 * 1 <= nums.length <= 10^5
 * 1 <= nums[i] <= 10^9
 */
public class ContinuousSubarrays {

    /**
     * Algorithm: Optimal Sliding Window + TreeMap
     * --------------------------------------------
     * Mnemonic: "Expand → Track min/max → Shrink if diff > 2 → Count."
     *
     * Idea:
     * - Use TreeMap to maintain counts of elements in the window.
     * - Expand window by adding nums[right].
     * - If max - min > 2, shrink window from left until valid.
     * - For each right, all subarrays ending at right are valid: (right - left + 1).
     *
     * Time Complexity: O(n log n) (TreeMap operations)
     * Space Complexity: O(n) (store window elements)
     */
    public static long continuousSubarraysOptimal(int[] nums) {
        TreeMap<Integer, Integer> map = new TreeMap<>(); // store frequency of elements
        int left = 0;                                    // window start
        long count = 0;                                  // total valid subarrays

        for (int right = 0; right < nums.length; right++) {
            // expand window: add nums[right]
            map.put(nums[right], map.getOrDefault(nums[right], 0) + 1);

            // shrink window if condition violated
            while (map.lastKey() - map.firstKey() > 2) {
                map.put(nums[left], map.get(nums[left]) - 1); // remove nums[left]
                if (map.get(nums[left]) == 0) map.remove(nums[left]); // cleanup
                left++; // move window start forward
            }

            // all subarrays ending at right are valid
            count += right - left + 1;
        }

        return count;
    }

    /**
     * Algorithm: Brute Force
     * ----------------------
     * Idea:
     * - For each starting index, expand subarray until max - min > 2.
     * - Count valid subarrays.
     *
     * Time Complexity: O(n^2)
     * Space Complexity: O(1)
     */
    public static long continuousSubarraysBruteForce(int[] nums) {
        int n = nums.length;
        long count = 0;

        for (int i = 0; i < n; i++) {
            int min = nums[i]; // track min in subarray
            int max = nums[i]; // track max in subarray
            for (int j = i; j < n; j++) {
                min = Math.min(min, nums[j]); // update min
                max = Math.max(max, nums[j]); // update max

                if (max - min <= 2) {
                    count++; // valid subarray
                } else {
                    break;   // stop expanding further
                }
            }
        }

        return count;
    }

    /**
     * Main method for testing
     */
    public static void main(String[] args) {
        int[] nums = {5, 4, 2, 4};

        long optimalResult = continuousSubarraysOptimal(nums);
        long bruteResult = continuousSubarraysBruteForce(nums);

        System.out.println("Optimal Sliding Window Result: " + optimalResult);
        System.out.println("Brute Force Result: " + bruteResult);
    }
}
