package Arrays.SlidingWindow;

/**
 * Problem: 643. Maximum Average Subarray I
 * ----------------------------------------
 * Given an integer array nums and an integer k,
 * find the contiguous subarray of length k that has the maximum average value.
 *
 * Example:
 * Input  : nums = [1,12,-5,-6,50,3], k = 4
 * Output : 12.75
 * Explanation: (12 - 5 - 6 + 50) / 4 = 51 / 4 = 12.75
 *
 * Constraints:
 * 1 <= k <= n <= 10^5
 * -10^4 <= nums[i] <= 10^4
 */
public class MaximumAverageSubarrayI {

    /**
     * Algorithm: Optimal Sliding Window (Fixed Size)
     * ----------------------------------------------
     * Mnemonic: "Slide → Drop left → Add right."
     *
     * Idea:
     * - Compute the sum of the first k elements.
     * - Slide the window one element at a time:
     *   remove the leftmost element and add the next right element.
     * - Track the maximum sum seen so far.
     * - Divide the maximum sum by k to get the maximum average.
     *
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     */
    public static double findMaxAverageOptimal(int[] nums, int k) {
        int n = nums.length;        // length of the array
        double sum = 0;             // variable to store current window sum

        // Step 1: Initial window sum (first k elements)
        for (int i = 0; i < k; i++) {
            sum += nums[i];         // add each of the first k elements
        }

        double maxSum = sum;        // initialize maxSum with the first window sum

        // Step 2: Slide window across the array
        for (int i = k; i < n; i++) {
            sum += nums[i];         // add the new element entering the window
            sum -= nums[i - k];     // remove the element leaving the window
            maxSum = Math.max(maxSum, sum); // update maxSum if current window sum is larger
        }

        return maxSum / k;          // return maximum average (max sum divided by k)
    }


    /**
     * Algorithm: Brute Force
     * ----------------------
     * Idea:
     * - For every possible subarray of length k,
     *   compute its sum and track the maximum.
     *
     * Time Complexity: O(n * k)
     * Space Complexity: O(1)
     */
    public static double findMaxAverageBruteForce(int[] nums, int k) {
        int n = nums.length;
        double maxSum = Double.NEGATIVE_INFINITY;

        for (int i = 0; i <= n - k; i++) {
            double sum = 0;
            for (int j = i; j < i + k; j++) {
                sum += nums[j];
            }
            maxSum = Math.max(maxSum, sum);
        }

        return maxSum / k;
    }

    /**
     * Main method for testing
     */
    public static void main(String[] args) {
        int[] nums = {1, 12, -5, -6, 50, 3};
        int k = 4;

        double optimalResult = findMaxAverageOptimal(nums, k);
        double bruteResult = findMaxAverageBruteForce(nums, k);

        System.out.println("Optimal Sliding Window Average: " + optimalResult);
        System.out.println("Brute Force Average: " + bruteResult);
    }
}
