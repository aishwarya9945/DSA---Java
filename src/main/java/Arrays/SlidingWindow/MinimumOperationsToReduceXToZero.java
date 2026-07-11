package Arrays.SlidingWindow;

/**
 * Problem: 1658. Minimum Operations to Reduce X to Zero
 * -----------------------------------------------------
 * You are given an array nums and an integer x.
 * In one operation, you can remove the leftmost or rightmost element from nums
 * and subtract its value from x.
 * Return the minimum number of operations to reduce x to exactly 0.
 * If not possible, return -1.
 *
 * Example:
 * Input  : nums = [1,1,4,2,3], x = 5
 * Output : 2
 * Explanation: Remove 2 and 3 → x becomes 0 in 2 operations.
 *
 * Constraints:
 * 1 <= nums.length <= 10^5
 * 1 <= nums[i] <= 10^4
 * 1 <= x <= 10^9
 */
public class MinimumOperationsToReduceXToZero {

    /**
     * Algorithm: Optimal Sliding Window
     * ---------------------------------
     * Mnemonic: "Find longest subarray sum = total - x → Answer = n - length."
     *
     * Idea:
     * - Instead of removing from both ends, think of keeping a middle subarray.
     * - The sum of that subarray must equal (totalSum - x).
     * - Find the longest subarray with this sum using sliding window.
     * - Answer = n - longestLength (operations = elements removed).
     *
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     */
    public static int minOperationsOptimal(int[] nums, int x) {
        int n = nums.length;
        int totalSum = 0;

        // Step 1: Calculate total sum of array
        for (int num : nums) totalSum += num;

        int target = totalSum - x; // Step 2: target subarray sum
        if (target < 0) return -1; // impossible if x > totalSum

        int left = 0;       // window start
        int currentSum = 0; // current window sum
        int maxLen = -1;    // longest subarray length

        // Step 3: Sliding window to find longest subarray with sum = target
        for (int right = 0; right < n; right++) {
            currentSum += nums[right]; // expand window

            // shrink window if sum > target
            while (currentSum > target && left <= right) {
                currentSum -= nums[left];
                left++;
            }

            // check if valid subarray found
            if (currentSum == target) {
                maxLen = Math.max(maxLen, right - left + 1);
            }
        }

        // Step 4: If no subarray found, return -1
        return (maxLen == -1) ? -1 : n - maxLen;
    }

    /**
     * Algorithm: Brute Force
     * ----------------------
     * Idea:
     * - Try removing elements from left and right in all combinations.
     * - Check if sum equals x.
     *
     * Time Complexity: O(n^2)
     * Space Complexity: O(1)
     */
    public static int minOperationsBruteForce(int[] nums, int x) {
        int n = nums.length;
        int minOps = Integer.MAX_VALUE;

        // prefix sums from left
        int[] prefix = new int[n + 1];
        for (int i = 0; i < n; i++) prefix[i + 1] = prefix[i] + nums[i];

        // suffix sums from right
        int[] suffix = new int[n + 1];
        for (int i = n - 1; i >= 0; i--) suffix[n - i] = suffix[n - i - 1] + nums[i];

        // try all combinations of left + right removals
        for (int left = 0; left <= n; left++) {
            for (int right = 0; right <= n - left; right++) {
                if (prefix[left] + suffix[right] == x) {
                    minOps = Math.min(minOps, left + right);
                }
            }
        }

        return (minOps == Integer.MAX_VALUE) ? -1 : minOps;
    }

    /**
     * Main method for testing
     */
    public static void main(String[] args) {
        int[] nums = {1, 1, 4, 2, 3};
        int x = 5;

        int optimalResult = minOperationsOptimal(nums, x);
        int bruteResult = minOperationsBruteForce(nums, x);

        System.out.println("Optimal Sliding Window Result: " + optimalResult);
        System.out.println("Brute Force Result: " + bruteResult);
    }
}
