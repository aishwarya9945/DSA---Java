package Arrays.SlidingWindow;

/**
 * Problem: 1493. Longest Subarray of 1's After Deleting One Element
 * -----------------------------------------------------------------
 * Given a binary array nums, you must delete exactly one element.
 * Return the length of the longest subarray containing only 1's after deletion.
 *
 * Example:
 * Input  : nums = [1,1,0,1]
 * Output : 3
 * Explanation: Delete the single 0 → longest subarray of 1's has length 3.
 *
 * Constraints:
 * 1 <= nums.length <= 10^5
 * nums[i] is either 0 or 1
 */
public class LongestSubarrayAfterDeletingOne {

    /**
     * Algorithm: Optimal Sliding Window (Variable Size)
     * -------------------------------------------------
     * Mnemonic: "Expand → Allow one zero → Shrink if >1 zero → Record."
     *
     * Idea:
     * - Use two pointers (left, right) to form a window.
     * - Expand window by moving right and counting zeros.
     * - If zeros exceed 1, shrink window from left until valid.
     * - Track the maximum window size (minus one deletion).
     *
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     */
    public static int longestSubarrayOptimal(int[] nums) {
        int left = 0;          // window start
        int zeros = 0;         // count of zeros in window
        int maxLen = 0;        // track max length

        for (int right = 0; right < nums.length; right++) {
            if (nums[right] == 0) zeros++; // expand window, count zero

            // shrink window if more than 1 zero
            while (zeros > 1) {
                if (nums[left] == 0) zeros--; // remove zero from window
                left++;                       // move window start forward
            }

            // record max length (delete one element → window size - 1)
            maxLen = Math.max(maxLen, right - left);
        }

        return maxLen;
    }

    /**
     * Algorithm: Brute Force
     * ----------------------
     * Idea:
     * - Try deleting each element.
     * - For each deletion, compute longest consecutive 1's.
     *
     * Time Complexity: O(n^2)
     * Space Complexity: O(1)
     */
    public static int longestSubarrayBruteForce(int[] nums) {
        int n = nums.length;
        int maxLen = 0;

        for (int i = 0; i < n; i++) {
            int count = 0; // count consecutive 1's after deleting nums[i]
            for (int j = 0; j < n; j++) {
                if (j == i) continue; // skip deleted element
                if (nums[j] == 1) {
                    count++;
                    maxLen = Math.max(maxLen, count);
                } else {
                    count = 0; // reset on zero
                }
            }
        }

        return maxLen;
    }

    /**
     * Main method for testing
     */
    public static void main(String[] args) {
        int[] nums = {1, 1, 0, 1};

        int optimalResult = longestSubarrayOptimal(nums);
        int bruteResult = longestSubarrayBruteForce(nums);

        System.out.println("Optimal Sliding Window Result: " + optimalResult);
        System.out.println("Brute Force Result: " + bruteResult);
    }
}
