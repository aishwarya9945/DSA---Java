package ArraysAndHashing;

/**
 * Program to find the Minimum Element in a Rotated Sorted Array.
 * Implements:
 * 1. Optimal Binary Search Approach (O(log n))
 * 2. Brute Force Linear Search Approach (O(n))
 *
 * Problem:
 * Given a sorted array that has been rotated at an unknown pivot,
 * find the smallest element in the array.
 *
 * Example:
 * Input  : [1, 2, 3, 0, 45, 12, 44]
 * Output : 0
 */
public class FindMinRotatedSortedArray {

    public static void main(String[] args) {
        int nums[] = {1, 2, 3, 0, 45, 12, 44};

        // ---------- Optimal Approach ----------
        int optimalMin = findMinOptimal(nums);
        System.out.println("Minimum Element (Optimal): " + optimalMin);

        // ---------- Brute Force Approach ----------
        int bruteMin = findMinBrute(nums);
        System.out.println("Minimum Element (Brute Force): " + bruteMin);
    }

    /**
     * Algorithm 1: Optimal Binary Search Approach
     * -------------------------------------------
     * Idea:
     * In a rotated sorted array, one half is always sorted.
     * The minimum element lies in the unsorted half.
     *
     * Steps:
     * 1. Initialize left = 0 and right = nums.length - 1.
     * 2. While left < right:
     *      - Find mid = (left + right) / 2.
     *      - Compare nums[mid] with nums[right]:
     *          * If nums[mid] < nums[right]:
     *                Minimum is in the left half (including mid), so right = mid.
     *          * Else:
     *                Minimum is in the right half, so left = mid + 1.
     * 3. When loop ends, left == right — the smallest element.
     * 4. Return nums[left].
     *
     * Time Complexity: O(log n)
     * Space Complexity: O(1)
     * Concept: Binary Search → reduce search space by half each step.
     */
    public static int findMinOptimal(int[] nums) {
        int left = 0;                       // Left pointer
        int right = nums.length - 1;        // Right pointer

        // Binary search loop
        while (left < right) {
            int mid = left + (right - left) / 2; // Mid index

            // If mid element < rightmost → Minimum lies on left side including mid
            if (nums[mid] < nums[right]) {
                right = mid;
            }
            // Else → Minimum lies on right side, excluding mid
            else {
                left = mid + 1;
            }
        }

        // At the end of loop, left == right → points to smallest element
        return nums[left];
    }

    /**
     * Algorithm 2: Brute Force Linear Search
     * ---------------------------------------
     * Idea:
     * Simply scan the entire array to find the smallest element.
     *
     * Steps:
     * 1. Initialize min = first element.
     * 2. Iterate through the array:
     *      - If any element < min, update min.
     * 3. Return min after traversal.
     *
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     * Concept: Simple comparison — no optimization.
     */
    public static int findMinBrute(int[] nums) {
        int min = nums[0]; // Initialize with first element

        // Traverse through all elements
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] < min) { // Update if smaller element found
                min = nums[i];
            }
        }

        return min;
    }
}
