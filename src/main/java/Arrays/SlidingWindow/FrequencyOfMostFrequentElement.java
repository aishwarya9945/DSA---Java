package Arrays.SlidingWindow;

import java.util.Arrays;

/**
 * Problem: 1838. Frequency of the Most Frequent Element
 * -----------------------------------------------------
 * Given an integer array nums and an integer k,
 * you can increment any element by 1 at most k times.
 * Return the maximum frequency of any element after performing at most k increments.
 *
 * Example:
 * Input  : nums = [1,2,4], k = 5
 * Output : 3
 * Explanation: Increment 2 → 3, increment 4 → 5,6 → all become 6 → frequency = 3.
 *
 * Constraints:
 * 1 <= nums.length <= 10^5
 * 1 <= nums[i] <= 10^5
 * 1 <= k <= 10^5
 */
public class FrequencyOfMostFrequentElement {

    /**
     * Algorithm: Optimal Sliding Window + Sorting
     * --------------------------------------------
     * Mnemonic: "Sort → Expand → Shrink until valid → Record."
     *
     * Idea:
     * - Sort the array.
     * - Use sliding window to maintain a range where we can make all elements equal to nums[right].
     * - Track the total sum of the window.
     * - Condition: (windowSum + k) >= nums[right] * windowSize
     *   → means we can increment smaller elements to match nums[right].
     * - Shrink window from left if condition fails.
     * - Record maximum window size.
     *
     * Time Complexity: O(n log n) (due to sorting)
     * Space Complexity: O(1)
     */
    public static int maxFrequencyOptimal(int[] nums, int k) {
        Arrays.sort(nums); // Step 1: sort array
        long windowSum = 0; // Step 2: track sum of current window
        int left = 0;       // Step 3: window start
        int maxFreq = 0;    // Step 4: track max frequency

        for (int right = 0; right < nums.length; right++) {
            windowSum += nums[right]; // expand window by adding nums[right]

            // shrink window until condition satisfied
            while ((long) nums[right] * (right - left + 1) > windowSum + k) {
                windowSum -= nums[left]; // remove nums[left] from sum
                left++;                  // move window start forward
            }

            maxFreq = Math.max(maxFreq, right - left + 1); // record max window size
        }

        return maxFreq;
    }

    /**
     * Algorithm: Brute Force
     * ----------------------
     * Idea:
     * - For each element, try to make it the most frequent by incrementing smaller elements.
     * - Count how many can be made equal within k increments.
     *
     * Time Complexity: O(n^2)
     * Space Complexity: O(1)
     */
    public static int maxFrequencyBruteForce(int[] nums, int k) {
        Arrays.sort(nums); // sort array for easier comparison
        int n = nums.length;
        int maxFreq = 1;

        for (int i = 0; i < n; i++) {
            int freq = 1;      // count current element itself
            int used = 0;      // track increments used
            for (int j = i - 1; j >= 0; j--) {
                int diff = nums[i] - nums[j]; // difference to make nums[j] equal to nums[i]
                if (used + diff <= k) {
                    used += diff; // use increments
                    freq++;       // increase frequency
                } else {
                    break;        // stop if increments exceed k
                }
            }
            maxFreq = Math.max(maxFreq, freq); // record max frequency
        }

        return maxFreq;
    }

    /**
     * Main method for testing
     */
    public static void main(String[] args) {
        int[] nums = {1, 2, 4};
        int k = 5;

        int optimalResult = maxFrequencyOptimal(nums, k);
        int bruteResult = maxFrequencyBruteForce(nums, k);

        System.out.println("Optimal Sliding Window Result: " + optimalResult);
        System.out.println("Brute Force Result: " + bruteResult);
    }
}
