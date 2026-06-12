package BinarySearch;

/**
 * Program to search for a target element in a Rotated Sorted Array (with duplicates allowed).
 * Implements:
 * 1. Optimal Binary Search Approach (O(log n) average, O(n) worst case due to duplicates)
 * 2. Brute Force Linear Search Approach (O(n))
 *
 * Problem:
 * Given a sorted array that has been rotated at an unknown pivot,
 * and may contain duplicates, return the index of target if found, otherwise -1.
 *
 * Example:
 * Input  : nums = [2,5,6,0,0,1,2], target = 0
 * Output : 3 (or 4, since target appears multiple times)
 */
public class SearchInRotatedSortedArrayII {

    public static void main(String[] args) {
        int nums[] = {2, 5, 6, 0, 0, 1, 2};
        int target = 0;

        // ---------- Optimal Approach ----------
        boolean foundOptimal = searchOptimal(nums, target);
        System.out.println("Target Found (Optimal): " + foundOptimal);

        // ---------- Brute Force Approach ----------
        int bruteIndex = searchBrute(nums, target);
        System.out.println("Target Index (Brute Force): " + bruteIndex);
    }

    /**
     * Algorithm 1: Optimal Binary Search Approach
     * -------------------------------------------
     * Idea:
     * In a rotated sorted array, one half is always sorted.
     * With duplicates, when nums[left] == nums[mid] == nums[right],
     * we cannot decide which half is sorted → shrink search space.
     *
     * Steps:
     * 1. Initialize left = 0, right = nums.length - 1.
     * 2. While left <= right:
     *      - Find mid = (left + right) / 2.
     *      - If nums[mid] == target → return true.
     *      - If nums[left] == nums[mid] == nums[right]:
     *            * Increment left, decrement right (skip duplicates).
     *      - Else if left half is sorted (nums[left] <= nums[mid]):
     *            * If target in [nums[left], nums[mid]] → search left half.
     *            * Else → search right half.
     *      - Else (right half is sorted):
     *            * If target in [nums[mid], nums[right]] → search right half.
     *            * Else → search left half.
     * 3. Return false if not found.
     *
     * Time Complexity: O(log n) average, O(n) worst case (due to duplicates).
     * Space Complexity: O(1)
     */
    public static boolean searchOptimal(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] == target)
                return true;

            // Handle duplicates
            if (nums[left] == nums[mid] && nums[mid] == nums[right]) {
                left++;
                right--;
                continue; // after duplicate skip → avoids falling into sorted half logic with stale indices.

            }
            // Left half is sorted
            else if (nums[left] <= nums[mid]) {
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

        return false;
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
     */
    public static int searchBrute(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == target) return i;
        }
        return -1;
    }
}
