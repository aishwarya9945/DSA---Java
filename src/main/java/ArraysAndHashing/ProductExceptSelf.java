package ArraysAndHashing;

import java.util.Arrays;

/**
 * Program to solve the Product of Array Except Self problem using:
 * 1. Optimal Prefix & Postfix Approach
 * 2. Brute Force Approach
 *
 * Problem:
 * Given an integer array `nums`, return an array `result` where each
 * `result[i]` equals the product of all the elements of `nums` except `nums[i]`.
 *
 * You must solve it without using division and in O(n) time.
 *
 * Example:
 * Input  : [12, 5, 9, 8, 3]
 * Output : [1080, 2592, 1440, 1620, 4320]
 */
public class ProductExceptSelf {

    public static void main(String[] args) {
        int[] nums = {12, 5, 9, 8, 3};

        // ---------- Optimal Prefix & Postfix Approach ----------
        int[] optimalResult = productExceptSelfOptimal(nums);
        System.out.println("Product Except Self (Optimal): " + Arrays.toString(optimalResult));

        // ---------- Brute Force Approach ----------
        int[] bruteResult = productExceptSelfBrute(nums);
        System.out.println("Product Except Self (Brute Force): " + Arrays.toString(bruteResult));
    }

    /**
     * Algorithm 1: Optimal Approach (Prefix & Postfix Products)
     * ---------------------------------------------------------
     * Idea:
     * For each index `i`, multiply all elements to the left (prefix)
     * and all elements to the right (postfix) — without using division.
     *
     * Steps:
     * 1. Initialize an output array `result[]` filled with 1s.
     * 2. Traverse left-to-right:
     *      - Track cumulative prefix product.
     *      - Update result[i] = prefix.
     * 3. Traverse right-to-left:
     *      - Track cumulative postfix product.
     *      - Update result[i] *= postfix.
     * 4. Return result[].
     *
     * Time Complexity: O(n)
     * Space Complexity: O(1) extra (excluding result[])
     * Concept: Prefix-Postfix accumulation avoids repeated multiplication and division.
     */
    public static int[] productExceptSelfOptimal(int[] nums) {
        int n = nums.length;
        int[] result = new int[n];         // Output array
        Arrays.fill(result, 1);           // Initialize each value as 1

        int prefix = 1;                   // Product of elements before current index
        for (int i = 0; i < n; i++) {
            result[i] = prefix;           // Store prefix product so far
            prefix *= nums[i];            // Update prefix for next iteration
        }

        int postfix = 1;                  // Product of elements after current index
        for (int i = n - 1; i >= 0; i--) {
            result[i] *= postfix;         // Multiply stored prefix with postfix product
            postfix *= nums[i];           // Update postfix for next iteration
        }

        return result;                    // Return final result array
    }

    /**
     * Algorithm 2: Brute Force Approach
     * ---------------------------------
     * Idea:
     * For each element at index i, multiply all other elements except `nums[i]`.
     * This involves nested loops — hence O(n²) complexity.
     *
     * Steps:
     * - Initialize an output array result[].
     * - For each index i:
     *      → Initialize product = 1.
     *      → Loop through array j:
     *          → If i != j, multiply product *= nums[j].
     *      → Store result[i] = product.
     * - Return result[].
     *
     * Time Complexity: O(n²)
     * Space Complexity: O(n)
     * Concept: Simple nested loop without optimization.
     */
    public static int[] productExceptSelfBrute(int[] nums) {
        int n = nums.length;
        int[] result = new int[n];        // Output array

        for (int i = 0; i < n; i++) {
            int product = 1;              // Initialize product for current element

            for (int j = 0; j < n; j++) {
                if (i != j) {             // Skip current element
                    product *= nums[j];   // Multiply all other elements
                }
            }

            result[i] = product;          // Store computed product
        }

        return result;                    // Return result array
    }
}
