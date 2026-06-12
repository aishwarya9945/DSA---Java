package BinarySearch;

/**
 * Program to find the Median of Two Sorted Arrays.
 * Implements:
 * 1. Optimal Binary Search Partition Approach (O(log(min(m,n))))
 * 2. Brute Force Merge Approach (O(m+n))
 *
 * Problem:
 * Given two sorted arrays nums1 and nums2 of size m and n respectively,
 * return the median of the two sorted arrays.
 *
 * Example:
 * Input  : nums1 = [1,3], nums2 = [2]
 * Output : 2.0
 *
 * Input  : nums1 = [1,2], nums2 = [3,4]
 * Output : 2.5
 */

/* Mnemonic
“Swap, Partition, Boundaries, Check, Median.”

1. Swap to ensure nums1 is smaller.
2. Partition both arrays.
3. Boundaries handle edges with ±∞.
4. Check partition validity.
5. Median from left/right values.(even , odd)
6. Adjust search if partition invalid.*/


public class MedianOfTwoSortedArrays {

    public static void main(String[] args) {
        int nums1[] = {1, 3};
        int nums2[] = {2};

        // ---------- Optimal Approach ----------
        double optimalMedian = findMedianOptimal(nums1, nums2);
        System.out.println("Median (Optimal): " + optimalMedian);

        // ---------- Brute Force Approach ----------
        double bruteMedian = findMedianBrute(nums1, nums2);
        System.out.println("Median (Brute Force): " + bruteMedian);
    }

    /**
     * Algorithm 1: Optimal Binary Search Partition Approach
     * -----------------------------------------------------
     * Idea:
     * Use binary search on the smaller array to partition both arrays
     * such that left half and right half are balanced.
     *
     * Steps:
     * 1. Ensure nums1 is the smaller array.
     * 2. Binary search on nums1:
     *      - Partition nums1 at i, nums2 at j = (m+n+1)/2 - i.
     *      - Check if leftMax1 <= rightMin2 and leftMax2 <= rightMin1.
     *      - If valid partition → median found.
     *      - Else adjust binary search boundaries.
     * 3. Median:
     *      - If total length odd → max(leftMax1, leftMax2).
     *      - If even → average of max(leftMax1, leftMax2) and min(rightMin1, rightMin2).
     *
     * Time Complexity: O(log(min(m,n)))
     * Space Complexity: O(1)
     */
    public static double findMedianOptimal(int[] nums1, int[] nums2) {
        // 1. Swap to ensure nums1 is smaller.
        if (nums1.length > nums2.length) {
            return findMedianOptimal(nums2, nums1); // ensure nums1 is smaller
        }

        int m = nums1.length, n = nums2.length;
        int left = 0, right = m;

        while (left <= right) {

            // 2. Partition both arrays.
            int partition1 = (left + right) / 2;
            int partition2 = (m + n + 1) / 2 - partition1;

            // 3. Boundaries handle edges with ±∞.
            int leftMax1 = (partition1 == 0) ? Integer.MIN_VALUE : nums1[partition1 - 1];
            int rightMin1 = (partition1 == m) ? Integer.MAX_VALUE : nums1[partition1];

            int leftMax2 = (partition2 == 0) ? Integer.MIN_VALUE : nums2[partition2 - 1];
            int rightMin2 = (partition2 == n) ? Integer.MAX_VALUE : nums2[partition2];

            // 4. Check partition validity.
            if (leftMax1 <= rightMin2 && leftMax2 <= rightMin1) {

                // 5. Median from left/right values.*/
                //Median: even → average, odd → max.
                if ((m + n) % 2 == 0) {
                    return (Math.max(leftMax1, leftMax2) + Math.min(rightMin1, rightMin2)) / 2.0;// Case 1: Even total length
                } else {
                    return Math.max(leftMax1, leftMax2);// Case 2: Odd total length //Note: even for average , odd for max
                }
            } else if (leftMax1 > rightMin2) { // 6. Adjust search if partition invalid.
                right = partition1 - 1;
            } else {
                left = partition1 + 1;
            }
        }

        throw new IllegalArgumentException("Input arrays are not sorted.");
    }

    /**
     * Algorithm 2: Brute Force Merge Approach
     * ---------------------------------------
     * Idea:
     * Merge both arrays into one sorted array, then find the median.
     *
     * Steps:
     * 1. Merge nums1 and nums2 into a single array.
     * 2. If total length odd → return middle element.
     * 3. If even → return average of two middle elements.
     *
     * Time Complexity: O(m+n)
     * Space Complexity: O(m+n)
     */
    public static double findMedianBrute(int[] nums1, int[] nums2) {
        int m = nums1.length, n = nums2.length;
        int[] merged = new int[m + n];
        int i = 0, j = 0, k = 0;

        while (i < m && j < n) {
            if (nums1[i] < nums2[j]) {
                merged[k++] = nums1[i++];
            } else {
                merged[k++] = nums2[j++];
            }
        }
        while (i < m) merged[k++] = nums1[i++];
        while (j < n) merged[k++] = nums2[j++];

        int total = m + n;
        if (total % 2 == 1) {
            return merged[total / 2];
        } else {
            return (merged[total / 2 - 1] + merged[total / 2]) / 2.0;
        }
    }
}
