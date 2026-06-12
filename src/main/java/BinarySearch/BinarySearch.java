package BinarySearch;

/**
 * Program to search for a target element in a sorted array using Binary Search.
 * Implements:
 * 1. Optimal Binary Search Approach (O(log n))
 * 2. Brute Force Linear Search Approach (O(n))
 *
 * Problem:
 * Given an array of integers sorted in ascending order, return the index of the target element.
 * If the target is not found, return -1.
 *
 * Example:
 * Input  : nums = [-1,0,3,5,9,12], target = 9
 * Output : 4
 *
 * Input  : nums = [-1,0,3,5,9,12], target = 2
 * Output : -1
 */
public class BinarySearch {

    public static void main(String[] args) {
        int nums[] = {-1, 0, 3, 5, 9, 12};
        int target = 9;

        // ---------- Optimal Approach ----------
        int optimalResult = searchOptimal(nums, target);
        System.out.println("Target Index (Optimal): " + optimalResult);

        // ---------- Brute Force Approach ----------
        int bruteResult = searchBrute(nums, target);
        System.out.println("Target Index (Brute Force): " + bruteResult);
    }

    /**
     * Algorithm 1: Optimal Binary Search Approach
     * -------------------------------------------
     * Idea:
     * Use binary search to repeatedly divide the array in half until the target is found.
     *
     * Steps:
     * 1. Initialize left = 0, right = n-1.
     * 2. While left <= right:
     *      - mid = (left + right) / 2
     *      - If nums[mid] == target → return mid.
     *      - If nums[mid] < target → search right half.
     *      - Else → search left half.
     * 3. If not found → return -1.
     *
     * Time Complexity: O(log n)
     * Space Complexity: O(1)
     */
    public static int searchOptimal(int[] nums, int target) {
        int left = 0, right = nums.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] == target) {
                return mid;
            }

            if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return -1;
    }

    /**
     * Algorithm 2: Brute Force Linear Search
     * --------------------------------------
     * Idea:
     * Scan through the array one element at a time until target is found.
     *
     * Steps:
     * 1. Traverse array from left to right.
     * 2. If nums[i] == target → return i.
     * 3. If not found → return -1.
     *
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     */
    public static int searchBrute(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == target) return i;
        }
        return -1;
    }
}
