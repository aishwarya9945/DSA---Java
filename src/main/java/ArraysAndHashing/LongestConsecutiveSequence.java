package ArraysAndHashing;

import java.util.HashSet;

/**
 * Program to find the length of the Longest Consecutive Sequence in an unsorted array.
 * Implements:
 * 1. Optimal HashSet Approach (O(n))
 * 2. Brute Force Sorting Approach (O(n log n))
 *
 * Problem:
 * Given an unsorted array of integers, return the length of the longest consecutive elements sequence.
 *
 * Example:
 * Input  : [100, 4, 200, 1, 3, 2]
 * Output : 4
 * Explanation: The longest consecutive sequence is [1, 2, 3, 4].
 */
public class LongestConsecutiveSequence {

    public static void main(String[] args) {
        int nums[] = {100, 4, 200, 1, 3, 2};

        // ---------- Optimal Approach ----------
        int optimalLength = longestConsecutiveOptimal(nums);
        System.out.println("Longest Consecutive Sequence (Optimal): " + optimalLength);

        // ---------- Brute Force Approach ----------
        int bruteLength = longestConsecutiveBrute(nums);
        System.out.println("Longest Consecutive Sequence (Brute Force): " + bruteLength);
    }

    /**
     * Algorithm 1: Optimal HashSet Approach
     * -------------------------------------
     * Idea:
     * Use a HashSet to store all numbers. For each number, check if it's the start of a sequence
     * (i.e., num - 1 not in set). Then expand forward until the sequence ends.
     *
     * Steps:
     * 1. Insert all numbers into a HashSet.
     * 2. For each number:
     *      - If num - 1 not in set → start of a sequence.
     *      - Expand forward (num + 1, num + 2, …) while present in set.
     *      - Track max length.
     * 3. Return max length.
     *
     * Time Complexity: O(n) (each number visited at most twice)
     * Space Complexity: O(n)
     * Concept: HashSet for O(1) lookups.
     */
    public static int longestConsecutiveOptimal(int[] nums) {
        HashSet<Integer> set = new HashSet<>();
        for (int num : nums) set.add(num);

        int longest = 0;

        for (int num : set) {
            // Only start counting if it's the beginning of a sequence
            if (!set.contains(num - 1)) {
                int currentNum = num;
                int length = 1;

                while (set.contains(currentNum + 1)) {
                    currentNum++;
                    length++;
                }

                longest = Math.max(longest, length);
            }
        }

        return longest;
    }

    /**
     * Algorithm 2: Brute Force Sorting Approach
     * -----------------------------------------
     * Idea:
     * Sort the array, then scan linearly to count consecutive streaks.
     *
     * Steps:
     * 1. Sort the array.
     * 2. Traverse through sorted array:
     *      - If current == previous + 1 → increment streak.
     *      - If current == previous → skip (duplicate).
     *      - Else → reset streak.
     * 3. Track max streak length.
     *
     * Time Complexity: O(n log n) (due to sorting)
     * Space Complexity: O(1) or O(n) depending on sort implementation
     * Concept: Sorting + linear scan.
     */
    public static int longestConsecutiveBrute(int[] nums) {
        if (nums.length == 0) return 0;

        java.util.Arrays.sort(nums);

        int longest = 1;
        int currentStreak = 1;

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[i - 1]) {
                continue; // skip duplicates
            } else if (nums[i] == nums[i - 1] + 1) {
                currentStreak++;
            } else {
                longest = Math.max(longest, currentStreak);
                currentStreak = 1;
            }
        }

        return Math.max(longest, currentStreak);
    }
}
