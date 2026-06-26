package MergingAndSorting;

import java.util.*;

/**
 * Problem: 88. Merge Sorted Array
 * -------------------------------
 * You are given two integer arrays nums1 and nums2, sorted in non-decreasing order,
 * and two integers m and n, representing the number of elements in nums1 and nums2 respectively.
 *
 * Merge nums2 into nums1 as one sorted array. The result must be stored in nums1.
 *
 * Example 1:
 * Input  : nums1 = [1,2,3,0,0,0], m = 3, nums2 = [2,5,6], n = 3
 * Output : [1,2,2,3,5,6]
 *
 * Example 2:
 * Input  : nums1 = [1], m = 1, nums2 = [], n = 0
 * Output : [1]
 *
 * Example 3:
 * Input  : nums1 = [0], m = 0, nums2 = [1], n = 1
 * Output : [1]
 *
 * Constraints:
 * - nums1.length == m + n
 * - nums2.length == n
 * - 0 <= m, n <= 200
 * - 1 <= m + n <= 200
 * - -10^9 <= nums1[i], nums2[j] <= 10^9
 *
 * Approaches Implemented:
 * 1. Optimal Two-Pointer from End (O(m+n), O(1))
 * 2. Brute Force Copy + Sort (O((m+n) log(m+n)), O(1))
 */
public class MergeSortedArray {

    /**
     * Algorithm 1: Optimal Two-Pointer from End
     * -----------------------------------------
     * Idea:
     * - Fill nums1 from the back to avoid overwriting.
     * - Use three pointers:
     *   i = m-1 (end of nums1's valid part),
     *   j = n-1 (end of nums2),
     *   k = m+n-1 (end of nums1 total).
     * - Compare nums1[i] and nums2[j], place larger at nums1[k].
     * - Decrement pointers accordingly.
     *
     * Time Complexity: O(m+n)
     * Space Complexity: O(1)
     * Concept: Two-pointer merge in-place.
     */
    public void mergeOptimal(int[] nums1, int m, int[] nums2, int n) {
        int i = m - 1, j = n - 1, k = m + n - 1;

        while (i >= 0 && j >= 0) {
            if (nums1[i] > nums2[j]) {
                nums1[k--] = nums1[i--];
            } else {
                nums1[k--] = nums2[j--];
            }
        }

        // Copy remaining nums2 if any
        while (j >= 0) {
            nums1[k--] = nums2[j--];
        }
    }

    /**
     * Algorithm 2: Brute Force Copy + Sort
     * ------------------------------------
     * Idea:
     * - Copy nums2 into nums1 starting at index m.
     * - Sort nums1.
     *
     * Time Complexity: O((m+n) log(m+n))
     * Space Complexity: O(1)
     * Concept: Simple sort after merge.
     */
    public void mergeBrute(int[] nums1, int m, int[] nums2, int n) {
        for (int i = 0; i < n; i++) {
            nums1[m + i] = nums2[i];
        }
        Arrays.sort(nums1);
    }
}
