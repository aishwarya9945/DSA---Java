package Arrays.SlidingWindow;

/**
 * Problem: 1004. Max Consecutive Ones III
 * ---------------------------------------
 * Given a binary array nums and an integer k,
 * return the maximum number of consecutive 1's in the array if you can flip at most k 0's.
 *
 * Example:
 * Input  : nums = [1,1,1,0,0,0,1,1,1,1,0], k = 2
 * Output : 6
 * Explanation: Flip the two 0's at indices 3 and 4 → longest sequence of 1's is length 6.
 *
 * Constraints:
 * 1 <= nums.length <= 10^5
 * 0 <= k <= nums.length
 * nums[i] is either 0 or 1
 */
public class MaxConsecutiveOnesIII {

    /**
     * Algorithm: Optimal Sliding Window (Variable Size)
     * -------------------------------------------------
     * Mnemonic: "Expand → Count zeros → Shrink if > k → Record."
     *
     * Idea:
     * - Use two pointers (left, right) to form a window.
     * - Expand window by moving right and counting zeros.
     * - If zeros exceed k, shrink window from left until valid.
     * - Track the maximum window size.
     *
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     */
    public static int longestOnesOptimal(int[] nums, int k) {
        int left = 0;          // window start
        int zeros = 0;         // count of zeros in window
        int maxLen = 0;        // track max consecutive ones

        for (int right = 0; right < nums.length; right++) {
            if (nums[right] == 0) zeros++; // expand window, count zero

            // shrink window if zeros exceed k
            while (zeros > k) {
                if (nums[left] == 0) zeros--; // remove zero from window
                left++;                       // move window start forward
            }

            maxLen = Math.max(maxLen, right - left + 1); // record max length
        }

        return maxLen;
    }

    /**
     * Algorithm: Brute Force
     * ----------------------
     * Idea:
     * - For each starting index, expand subarray until zeros > k.
     * - Track the longest valid subarray.
     *
     * Time Complexity: O(n^2)
     * Space Complexity: O(1)
     */
    public static int longestOnesBruteForce(int[] nums, int k) {
        int n = nums.length;
        int maxLen = 0;

        for (int i = 0; i < n; i++) {
            int zeros = 0; // reset zero count for each start
            for (int j = i; j < n; j++) {
                if (nums[j] == 0) zeros++; // count zero

                if (zeros > k) break; // stop if more than k zeros

                maxLen = Math.max(maxLen, j - i + 1); // record valid length
            }
        }

        return maxLen;
    }

    /**
     * Main method for testing
     */
    public static void main(String[] args) {
        int[] nums = {1,1,1,0,0,0,1,1,1,1,0};
        int k = 2;

        int optimalResult = longestOnesOptimal(nums, k);
        int bruteResult = longestOnesBruteForce(nums, k);

        System.out.println("Optimal Sliding Window Result: " + optimalResult);
        System.out.println("Brute Force Result: " + bruteResult);
    }
}
