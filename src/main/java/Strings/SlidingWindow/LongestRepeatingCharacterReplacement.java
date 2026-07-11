package Strings.SlidingWindow;

import java.util.*;

public class LongestRepeatingCharacterReplacement {

    /**
     * Algorithm: Optimal Sliding Window + Frequency Array
     * ---------------------------------------------------
     * Mnemonic: "Expand → Track max freq → Shrink if invalid → Record."
     *
     * Idea:
     * - Use an array to track frequency of characters in current window.
     * - Expand window with right pointer.
     * - Track the count of the most frequent character in the window.
     * - If window size - maxFreq > k → shrink from left.
     * - Record maximum valid window length.
     *
     * Time Complexity: O(n)
     * Space Complexity: O(26) → O(1)
     */
    public static int characterReplacementOptimal(String s, int k) {
        int[] freq = new int[26]; // frequency of chars in window
        int left = 0;             // window start
        int maxFreq = 0;          // track max frequency of a single char
        int maxLen = 0;           // track max length

        for (int right = 0; right < s.length(); right++) {
            char c = s.charAt(right);
            freq[c - 'A']++; // add char to window

            // update max frequency
            maxFreq = Math.max(maxFreq, freq[c - 'A']);

            // shrink window if replacements needed > k
            while ((right - left + 1) - maxFreq > k) {
                freq[s.charAt(left) - 'A']--; // remove left char
                left++;
            }

            // record max length
            maxLen = Math.max(maxLen, right - left + 1);
        }

        return maxLen;
    }

    /**
     * Algorithm: Brute Force
     * ----------------------
     * Idea:
     * - Try all substrings.
     * - For each substring, count frequencies.
     * - Check if (length - maxFreq) ≤ k.
     * - Record maximum valid length.
     *
     * Time Complexity: O(n^2 * 26)
     * Space Complexity: O(26)
     */
    public static int characterReplacementBruteForce(String s, int k) {
        int n = s.length();
        int maxLen = 0;

        for (int i = 0; i < n; i++) {
            int[] freq = new int[26];
            int maxFreq = 0;

            for (int j = i; j < n; j++) {
                char c = s.charAt(j);
                freq[c - 'A']++;
                maxFreq = Math.max(maxFreq, freq[c - 'A']);

                if ((j - i + 1) - maxFreq <= k) {
                    maxLen = Math.max(maxLen, j - i + 1);
                }
            }
        }

        return maxLen;
    }

    /**
     * Main method for testing
     */
    public static void main(String[] args) {
        String s = "AABABBA";
        int k = 1;

        System.out.println("Optimal Sliding Window Result: " + characterReplacementOptimal(s, k)); // Expected: 4
        System.out.println("Brute Force Result: " + characterReplacementBruteForce(s, k));
    }
}
