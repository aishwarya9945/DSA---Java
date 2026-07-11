package Arrays.Basics;

/**
 * Problem: 75. Sort Colors
 * ------------------------
 * Given an array nums with n objects colored red, white, or blue,
 * sort them in-place so that objects of the same color are adjacent,
 * with the colors in the order red (0), white (1), and blue (2).
 *
 * Example 1:
 * Input  : nums = [2,0,2,1,1,0]
 * Output : [0,0,1,1,2,2]
 *
 * Example 2:
 * Input  : nums = [2,0,1]
 * Output : [0,1,2]
 *
 * Constraints:
 * - 1 <= n <= 300
 * - nums[i] ∈ {0,1,2}
 *
 * Approaches Implemented:
 * 1. Optimal Dutch National Flag Algorithm (O(n), O(1))
 * 2. Brute Force Counting Sort Approach (O(n), O(1))
 */
public class SortColors {

    /**
     * Algorithm 1: Optimal Dutch National Flag
     * ----------------------------------------
     * Idea:
     * - Maintain three pointers: low, mid, high.
     * - low: boundary for 0s, mid: current element, high: boundary for 2s.
     * - Traverse array:
     *   - If nums[mid] == 0 → swap with low, increment both.
     *   - If nums[mid] == 1 → just move mid.
     *   - If nums[mid] == 2 → swap with high, decrement high.
     *
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     * Concept: Dutch National Flag partitioning.
     */
    public void sortColorsOptimal(int[] nums) {
        int low = 0, mid = 0, high = nums.length - 1;

        while (mid <= high) {
            if (nums[mid] == 0) {
                swap(nums, low, mid);
                low++;
                mid++;
            } else if (nums[mid] == 1) {
                mid++;
            } else { // nums[mid] == 2
                swap(nums, mid, high);
                high--;
            }
        }
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    /**
     * Algorithm 2: Brute Force Counting Sort
     * --------------------------------------
     * Idea:
     * - Count number of 0s, 1s, and 2s.
     * - Overwrite nums with counts in order.
     *
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     * Concept: Counting sort.
     */
    public void sortColorsBrute(int[] nums) {
        int count0 = 0, count1 = 0, count2 = 0;
        for (int num : nums) {
            if (num == 0) count0++;
            else if (num == 1) count1++;
            else count2++;
        }

        int idx = 0;
        while (count0-- > 0) nums[idx++] = 0;
        while (count1-- > 0) nums[idx++] = 1;
        while (count2-- > 0) nums[idx++] = 2;
    }
}
