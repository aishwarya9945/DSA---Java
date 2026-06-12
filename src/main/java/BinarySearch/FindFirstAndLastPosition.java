package BinarySearch;

/**
 * Program to find the first and last position of a target element in a sorted array.
 * Implements:
 * 1. Optimal Binary Search Approach (O(log n))
 * 2. Brute Force Linear Search Approach (O(n))
 *
 * Problem:
 * Given an array of integers sorted in non-decreasing order, find the starting and ending position of a given target value.
 * If target is not found, return [-1, -1].
 *
 * Example:
 * Input  : nums = [5,7,7,8,8,10], target = 8
 * Output : [3,4]
 */
public class FindFirstAndLastPosition {

    public static void main(String[] args) {
        int nums[] = {5, 7, 7, 8, 8, 10};
        int target = 8;

        // ---------- Optimal Approach ----------
        int[] optimalResult = searchRangeOptimal(nums, target);
        System.out.println("First and Last Position (Optimal): [" + optimalResult[0] + ", " + optimalResult[1] + "]");

        // ---------- Brute Force Approach ----------
        int[] bruteResult = searchRangeBrute(nums, target);
        System.out.println("First and Last Position (Brute Force): [" + bruteResult[0] + ", " + bruteResult[1] + "]");
    }

    /**
     * Algorithm 1: Optimal Binary Search Approach
     * -------------------------------------------
     * Idea:
     * Use binary search twice:
     *  - Once to find the first occurrence of target.
     *  - Once to find the last occurrence of target.
     *
     * Steps:
     * 1. Perform binary search to find left boundary (first occurrence).
     * 2. Perform binary search to find right boundary (last occurrence).
     * 3. Return both indices.
     *
     * Time Complexity: O(log n)
     * Space Complexity: O(1)
     */
    public static int[] searchRangeOptimal(int[] nums, int target) {
        int first = findBound(nums, target, true);
        int last = findBound(nums, target, false);
        return new int[]{first, last};
    }

    private static int findBound(int[] nums, int target, boolean isFirst) {
        int left = 0, right = nums.length - 1, bound = -1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] == target) {
                bound = mid;
                if (isFirst) {
                    right = mid - 1; // keep searching left
                } else {
                    left = mid + 1;  // keep searching right
                }
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return bound;
    }

    /**
     * Algorithm 2: Brute Force Linear Search
     * ---------------------------------------
     * Idea:
     * Scan the array to find first and last occurrence of target.
     *
     * Steps:
     * 1. Initialize first = -1, last = -1.
     * 2. Traverse array:
     *      - If nums[i] == target:
     *          * If first == -1 → set first = i.
     *          * Always update last = i.
     * 3. Return [first, last].
     *
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     */
    public static int[] searchRangeBrute(int[] nums, int target) {
        int first = -1, last = -1;

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == target) {
                if (first == -1) first = i;
                last = i;
            }
        }

        return new int[]{first, last};
    }
}
