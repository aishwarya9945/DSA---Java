package ArraysAndHashing;

/**
 * Program to solve Maximum Product Subarray using:
 * 1. Optimal Dynamic Programming (Two Pointer) Approach
 * 2. Brute Force Approach
 *
 * Problem:
 * Given an integer array, find the contiguous subarray
 * which has the largest product.
 *
 * Example:
 * Input:  [1, 2, -2, 9, 8, 5]
 * Output: 360
 */

public class MaxProductSubArray {

    public static void main(String[] args) {
        int nums[] = {1, 2, -2, 9, 8, 5};

        // ---------- Optimal Dynamic Programming Approach ----------
        int optimalResult = maxProductOptimal(nums);
        System.out.println("Maximum Product Subarray (Optimal): " + optimalResult);

        // ---------- Brute Force Approach ----------
        int bruteResult = maxProductBrute(nums);
        System.out.println("Maximum Product Subarray (Brute Force): " + bruteResult);
    }

    /**
     * Algorithm 1: Optimal Dynamic Programming (Kadane Variant)
     * ---------------------------------------------------------
     * Idea:
     * Track both the maximum and minimum product at each step
     * because a negative number can flip signs and turn a minimum
     * product into a maximum one.
     *
     * Steps:
     * - Initialize three variables: maxProduct, currentMax, currentMin to nums[0].
     * - Traverse the array from index 1:
     *      - If current number < 0 → swap currentMax and currentMin.
     *      - Update currentMax = max(nums[i], currentMax * nums[i]).
     *      - Update currentMin = min(nums[i], currentMin * nums[i]).
     *      - Update maxProduct = max(maxProduct, currentMax).
     * - Return maxProduct.
     *
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     * Concept: Dynamic Programming using two-pointer tracking.
     */
    public static int maxProductOptimal(int[] nums) {
        int maxProduct = nums[0];
        int currentMax = nums[0];
        int currentMin = nums[0];

        for (int i = 1; i < nums.length; i++) {
            // If element is negative, swap currentMax and currentMin
            if (nums[i] < 0) {
                int temp = currentMax;
                currentMax = currentMin;
                currentMin = temp;
            }

            // Select max/min of (current element) or (extend previous subarray)
            currentMax = Math.max(nums[i], currentMax * nums[i]);
            currentMin = Math.min(nums[i], currentMin * nums[i]);

            // Track overall maximum product found so far
            maxProduct = Math.max(maxProduct, currentMax);
        }

        return maxProduct;
    }

    /**
     * Algorithm 2: Brute Force Approach
     * ---------------------------------
     * Idea:
     * Generate all possible subarrays and calculate their product.
     * Track the global maximum among them.
     *
     * Steps:
     * - Initialize maxProduct = Integer.MIN_VALUE.
     * - For each starting index i:
     *      - Initialize product = 1.
     *      - Multiply elements sequentially from i → j.
     *      - Update maxProduct = max(maxProduct, product).
     * - Return maxProduct.
     *
     * Time Complexity: O(n²)
     * Space Complexity: O(1)
     * Concept: Straightforward nested loop to check all subarray products.
     */
    public static int maxProductBrute(int[] nums) {
        int maxProduct = Integer.MIN_VALUE;

        for (int i = 0; i < nums.length; i++) {
            int product = 1; // Reset product for each new subarray start
            for (int j = i; j < nums.length; j++) {
                product *= nums[j]; // Multiply current element
                maxProduct = Math.max(maxProduct, product);
            }
        }

        return maxProduct;
    }
}
