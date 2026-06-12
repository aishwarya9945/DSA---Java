package ArraysAndHashing;

/**
 * Program to find the missing number in an array containing n distinct numbers
 * taken from the range [0, n].
 * Implements:
 * 1. Optimal XOR Approach (O(n))
 * 2. Brute Force Summation Approach (O(n))
 *
 * Problem:
 * Given an array nums containing n distinct numbers in the range [0, n],
 * return the only number in the range that is missing from the array.
 *
 * Example:
 * Input  : [3,0,1]
 * Output : 2
 * Explanation: Numbers are [0,1,2,3]. Missing = 2.
 */
public class MissingNumber {

    public static void main(String[] args) {
        int nums[] = {3, 0, 1};

        // ---------- Optimal Approach ----------
        int optimalResult = missingNumberOptimal(nums);
        System.out.println("Missing Number (Optimal): " + optimalResult);

        // ---------- Brute Force Approach ----------
        int bruteResult = missingNumberBrute(nums);
        System.out.println("Missing Number (Brute Force): " + bruteResult);
    }

    /**
     * Algorithm 1: Optimal XOR Approach
     * ---------------------------------
     * Idea:
     * XOR all indices and numbers together. The missing number will remain.
     *
     * Steps:
     * 1. Initialize result = n.
     * 2. For each index i and number nums[i]:
     *      result ^= i ^ nums[i].
     * 3. Return result.
     *
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     * Concept: XOR cancels out duplicates.
     */
    public static int missingNumberOptimal(int[] nums) {
        int n = nums.length;
        int result = n;

        for (int i = 0; i < n; i++) {
            result ^= i ^ nums[i];
        }

        return result;
    }

    /**
     * Algorithm 2: Brute Force Summation Approach
     * -------------------------------------------
     * Idea:
     * Sum of numbers from 0 to n is n*(n+1)/2. Subtract actual sum to find missing.
     *
     * Steps:
     * 1. Compute expectedSum = n*(n+1)/2.
     * 2. Compute actualSum = sum of nums.
     * 3. Return expectedSum - actualSum.
     *
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     * Concept: Arithmetic series formula.
     */
    public static int missingNumberBrute(int[] nums) {
        int n = nums.length;
        int expectedSum = n * (n + 1) / 2;

        int actualSum = 0;
        for (int num : nums) {
            actualSum += num;
        }

        return expectedSum - actualSum;
    }
}
