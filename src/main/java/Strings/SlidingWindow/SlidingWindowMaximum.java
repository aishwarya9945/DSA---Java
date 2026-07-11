package Strings.SlidingWindow;

import java.util.*;

public class SlidingWindowMaximum {

    /**
     * Algorithm: Optimal Sliding Window + Deque
     * ------------------------------------------
     * Mnemonic: "Maintain decreasing deque → Record max."
     *
     * Idea:
     * - Use a deque to store indices of elements in decreasing order.
     * - The front of deque always holds the max element index for current window.
     * - Remove elements from back if smaller than current element.
     * - Remove elements from front if they fall outside the window.
     * - Record max for each window.
     *
     * Time Complexity: O(n)
     * Space Complexity: O(k)
     */
    public static int[] maxSlidingWindowOptimal(int[] nums, int k) {
        if (nums.length == 0 || k == 0) return new int[0];

        int n = nums.length;
        int[] result = new int[n - k + 1];
        Deque<Integer> deque = new LinkedList<>(); // store indices

        for (int i = 0; i < n; i++) {
            // remove indices out of current window
            while (!deque.isEmpty() && deque.peekFirst() <= i - k) {
                deque.pollFirst();
            }

            // maintain decreasing order in deque
            while (!deque.isEmpty() && nums[deque.peekLast()] < nums[i]) {
                deque.pollLast();
            }

            deque.offerLast(i); // add current index

            // record max when window size reached
            if (i >= k - 1) {
                result[i - k + 1] = nums[deque.peekFirst()];
            }
        }

        return result;
    }

    /**
     * Algorithm: Brute Force
     * ----------------------
     * Idea:
     * - For each window of size k, scan all elements.
     * - Record maximum.
     *
     * Time Complexity: O(n * k)
     * Space Complexity: O(1)
     */
    public static int[] maxSlidingWindowBruteForce(int[] nums, int k) {
        if (nums.length == 0 || k == 0) return new int[0];

        int n = nums.length;
        int[] result = new int[n - k + 1];

        for (int i = 0; i <= n - k; i++) {
            int max = Integer.MIN_VALUE;
            for (int j = i; j < i + k; j++) {
                max = Math.max(max, nums[j]);
            }
            result[i] = max;
        }

        return result;
    }

    /**
     * Main method for testing
     */
    public static void main(String[] args) {
        int[] nums = {1,3,-1,-3,5,3,6,7};
        int k = 3;

        System.out.println("Optimal Deque Result: " + Arrays.toString(maxSlidingWindowOptimal(nums, k))); // Expected: [3,3,5,5,6,7]
        System.out.println("Brute Force Result: " + Arrays.toString(maxSlidingWindowBruteForce(nums, k)));
    }
}
