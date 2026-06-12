package BinarySearch;

/**
 * Program to search for a target element in a Rotated Sorted Array.
 * Implements:
 * 1. Optimal Binary Search Approach (O(log n))
 * 2. Brute Force Linear Search Approach (O(n))
 *
 * Problem:
 * Given a sorted array that has been rotated at an unknown pivot,
 * and a target element, return its index if found, otherwise -1.
 *
 * Example:
 * Input  : nums = [4,5,6,7,0,1,2], target = 0
 * Output : 4
 */
public class SearchInRotatedSortedArray {

    public static void main(String[] args) {
        int nums[] = {4, 5, 6, 7, 0, 1, 2};
        int target = 0;

        // ---------- Optimal Approach ----------
        int optimalIndex = searchOptimal(nums, target);
        System.out.println("Target Index (Optimal): " + optimalIndex);

        // ---------- Brute Force Approach ----------
        int bruteIndex = searchBrute(nums, target);
        System.out.println("Target Index (Brute Force): " + bruteIndex);
    }

    /**
     * Algorithm 1: Optimal Binary Search Approach
     * -------------------------------------------
     * Idea:
     * In a rotated sorted array, one half is always sorted.
     * Decide which half to search based on target value.
     *
     * Steps:
     * 1. Initialize left = 0 and right = nums.length - 1.
     * 2. While left <= right:
     *      - Find mid = (left + right) / 2.
     *      - If nums[mid] == target → return mid.
     *      - If left half is sorted (nums[left] <= nums[mid]):
     *          * If target lies in [nums[left], nums[mid]] → search left half.
     *          * Else → search right half.
     *      - Else (right half is sorted):
     *          * If target lies in [nums[mid], nums[right]] → search right half.
     *          * Else → search left half.
     * 3. If not found, return -1.
     *
     * Time Complexity: O(log n)
     * Space Complexity: O(1)
     * Concept: Binary Search with rotation awareness.
     */
    public static int searchOptimal(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] == target)
                return mid;

            // Left half is sorted
            if (nums[left] <= nums[mid]) {
                if (target >= nums[left] && target < nums[mid]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
            // Right half is sorted
            else {
                if (target > nums[mid] && target <= nums[right]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }

        return -1;
    }

    /**
     * Algorithm 2: Brute Force Linear Search
     * ---------------------------------------
     * Idea:
     * Simply scan the entire array to find the target.
     *
     * Steps:
     * 1. Iterate through the array.
     * 2. If any element == target, return its index.
     * 3. If not found, return -1.
     *
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     * Concept: Simple comparison — no optimization.
     */
    public static int searchBrute(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == target) return i;
        }
        return -1;
    }
}
