package Arrays.SlidingWindow;

/**
 * Problem: 713. Subarray Product Less Than K
 * ------------------------------------------
 * Given an array of integers nums and an integer k,
 * return the number of contiguous subarrays where the product of all elements is strictly less than k.
 *
 * Example:
 * Input  : nums = [10, 5, 2, 6], k = 100
 * Output : 8
 * Explanation: The subarrays are [10], [5], [2], [6], [10,5], [5,2], [2,6], [5,2,6].
 *
 * Constraints:
 * 1 <= nums.length <= 3 * 10^4
 * 1 <= nums[i] <= 1000
 * 0 <= k <= 10^6
 */
public class SubarrayProductLessThanK {

    /**
     * Algorithm: Optimal Sliding Window (Variable Size)
     * -------------------------------------------------
     * Mnemonic: "Expand → Shrink until product < k → Count."
     *
     * Idea:
     * - Maintain a sliding window with product of elements.
     * - Expand window by multiplying nums[right].
     * - If product >= k, shrink window from left until product < k.
     * - For each right index, all subarrays ending at right are valid: (right - left + 1).
     *
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     */
    public static int numSubarrayProductLessThanKOptimal(int[] nums, int k) {
        if (k <= 1) return 0; // edge case: if k <= 1, no product can be strictly less than k

        int count = 0;        // total number of valid subarrays
        int left = 0;         // window start pointer
        long product = 1;     // product of elements in current window

        // iterate with right pointer expanding the window
        for (int right = 0; right < nums.length; right++) {
            product *= nums[right]; // include nums[right] in product

            // shrink window until product < k
            while (product >= k) {
                product /= nums[left]; // remove nums[left] from product
                left++;                // move window start forward
            }

            // all subarrays ending at right and starting from left..right are valid
            count += right - left + 1;
        }

        return count; // return total count of valid subarrays
    }


    /**
     * Algorithm: Brute Force
     * ----------------------
     * Idea:
     * - For each starting index, expand subarray until product >= k.
     * - Count valid subarrays.
     *
     * Time Complexity: O(n^2)
     * Space Complexity: O(1)
     */
    public static int numSubarrayProductLessThanKBruteForce(int[] nums, int k) {
        int n = nums.length;
        int count = 0;

        for (int i = 0; i < n; i++) {
            long product = 1;
            for (int j = i; j < n; j++) {
                product *= nums[j];
                if (product < k) {
                    count++;
                } else {
                    break; // no need to expand further
                }
            }
        }

        return count;
    }

    /**
     * Main method for testing
     */
    public static void main(String[] args) {
        int[] nums = {10, 5, 2, 6};
        int k = 100;

        int optimalResult = numSubarrayProductLessThanKOptimal(nums, k);
        int bruteResult = numSubarrayProductLessThanKBruteForce(nums, k);

        System.out.println("Optimal Sliding Window Count: " + optimalResult);
        System.out.println("Brute Force Count: " + bruteResult);
    }
}
