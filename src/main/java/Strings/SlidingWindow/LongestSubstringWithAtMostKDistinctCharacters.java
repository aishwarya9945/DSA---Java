package Strings.SlidingWindow;

import java.util.HashMap;

public class LongestSubstringWithAtMostKDistinctCharacters {

    /**
     * Algorithm: Optimal Sliding Window + HashMap
     * --------------------------------------------
     * Mnemonic: "Expand → Count chars → Shrink if > K → Record."
     *
     * Idea:
     * - Use HashMap to store frequency of characters in current window.
     * - Expand window with right pointer.
     * - If distinct chars > K, shrink from left until valid.
     * - Track max length.
     *
     * Time Complexity: O(n)
     * Space Complexity: O(min(n, charset)) → O(256) for ASCII
     */
    public static int lengthOfLongestSubstringKDistinctOptimal(String s, int k) {
        if (k == 0) return 0; // edge case: no distinct chars allowed

        HashMap<Character, Integer> map = new HashMap<>(); // store char frequencies
        int left = 0;                                      // window start
        int maxLen = 0;                                    // track max length

        for (int right = 0; right < s.length(); right++) {
            char c = s.charAt(right);
            map.put(c, map.getOrDefault(c, 0) + 1); // add char to map

            // shrink window until distinct chars ≤ k
            while (map.size() > k) {
                char leftChar = s.charAt(left);
                map.put(leftChar, map.get(leftChar) - 1); // remove left char
                if (map.get(leftChar) == 0) map.remove(leftChar); // cleanup
                left++; // move window start forward
            }

            // record max length of valid substring
            maxLen = Math.max(maxLen, right - left + 1);
        }

        return maxLen;
    }

    /**
     * Algorithm: Brute Force
     * ----------------------
     * Idea:
     * - Try all substrings.
     * - Count distinct characters in each substring.
     * - If ≤ K, update max length.
     *
     * Time Complexity: O(n^3) (generate substrings + count distinct)
     * Space Complexity: O(n)
     */
    public static int lengthOfLongestSubstringKDistinctBruteForce(String s, int k) {
        int n = s.length();
        int maxLen = 0;

        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                if (countDistinct(s, i, j) <= k) {
                    maxLen = Math.max(maxLen, j - i + 1);
                }
            }
        }

        return maxLen;
    }

    // helper method to count distinct chars in substring
    private static int countDistinct(String s, int start, int end) {
        HashMap<Character, Integer> map = new HashMap<>();
        for (int i = start; i <= end; i++) {
            char c = s.charAt(i);
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        return map.size();
    }

    /**
     * Main method for testing
     */
    public static void main(String[] args) {
        String s = "eceba";
        int k = 2;

        int optimalResult = lengthOfLongestSubstringKDistinctOptimal(s, k);
        int bruteResult = lengthOfLongestSubstringKDistinctBruteForce(s, k);

        System.out.println("Optimal Sliding Window Result: " + optimalResult); // Expected: 3 ("ece")
        System.out.println("Brute Force Result: " + bruteResult);
    }
}
