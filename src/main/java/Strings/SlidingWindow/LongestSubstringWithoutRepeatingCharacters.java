package Strings.SlidingWindow;

import java.util.HashSet;
import java.util.HashMap;

public class LongestSubstringWithoutRepeatingCharacters {

    /**
     * Algorithm: Optimal Sliding Window + HashMap
     * --------------------------------------------
     * Mnemonic: "Expand → Track chars → Shrink on repeat → Record."
     *
     * Idea:
     * - Use HashMap to store last seen index of each character.
     * - Expand window with right pointer.
     * - If character repeats, move left pointer to max(lastSeen+1, left).
     * - Track max length.
     *
     * Time Complexity: O(n)
     * Space Complexity: O(min(n, charset)) → O(256) for ASCII
     */
    public static int lengthOfLongestSubstringOptimal(String s) {
        // HashMap to store the last seen index of each character
        HashMap<Character, Integer> map = new HashMap<>();

        int left = 0;   // window start pointer
        int maxLen = 0; // variable to track the maximum substring length

        // Expand window with right pointer
        for (int right = 0; right < s.length(); right++) {
            char c = s.charAt(right); // current character at position right

            // If character was seen before, move left pointer
            // to ensure substring has no duplicates
            if (map.containsKey(c)) {
                // left jumps to max of current left or last seen index + 1
                left = Math.max(left, map.get(c) + 1);
            }

            // Update last seen index of current character
            map.put(c, right);

            // Record maximum length of valid substring so far
            maxLen = Math.max(maxLen, right - left + 1);
        }

        // Return the maximum length found
        return maxLen;
    }

    /**
     * Algorithm: Brute Force
     * ----------------------
     * Idea:
     * - Try all substrings.
     * - Check if substring has all unique characters.
     *
     * Time Complexity: O(n^3) (generate substrings + check uniqueness)
     * Space Complexity: O(n)
     */
    public static int lengthOfLongestSubstringBruteForce(String s) {
        int n = s.length();
        int maxLen = 0;

        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                if (allUnique(s, i, j)) {
                    maxLen = Math.max(maxLen, j - i + 1);
                }
            }
        }

        return maxLen;
    }

    // helper method to check uniqueness
    private static boolean allUnique(String s, int start, int end) {
        HashSet<Character> set = new HashSet<>();
        for (int i = start; i <= end; i++) {
            char c = s.charAt(i);
            if (set.contains(c)) return false;
            set.add(c);
        }
        return true;
    }

    /**
     * Main method for testing
     */
    public static void main(String[] args) {
        String s = "abcabcbb";

        int optimalResult = lengthOfLongestSubstringOptimal(s);
        int bruteResult = lengthOfLongestSubstringBruteForce(s);

        System.out.println("Optimal Sliding Window Result: " + optimalResult);
        System.out.println("Brute Force Result: " + bruteResult);
    }
}
