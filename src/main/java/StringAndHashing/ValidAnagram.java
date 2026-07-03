package StringAndHashing;

import java.util.*;

/**
 * Problem: 242. Valid Anagram
 * ---------------------------
 * Given two strings s and t, return true if t is an anagram of s, and false otherwise.
 *
 * Example 1:
 * Input  : s = "anagram", t = "nagaram"
 * Output : true
 *
 * Example 2:
 * Input  : s = "rat", t = "car"
 * Output : false
 *
 * Constraints:
 * - 1 <= s.length, t.length <= 5 * 10^4
 * - s and t consist of lowercase English letters.
 *
 * Approaches Implemented:
 * 1. Optimal Frequency Count Approach (O(n), O(1))
 * 2. Brute Force Sorting Approach (O(n log n), O(1))
 */
public class ValidAnagram {

    /**
     * Algorithm 1: Optimal Frequency Count
     * ------------------------------------
     * Idea:
     * - Count frequency of each character in s.
     * - Subtract frequency using characters in t.
     * - If all counts return to zero → anagram.
     *
     * Mnemonic
     * Think of it as “Count → Cancel → Check”:
     *
     * Count → Count characters in s.
     * Hook: “Fill the frequency bucket.”
     *
     * Cancel → Subtract counts using t.
     * Hook: “Cancel out with second string.”
     *
     * Check → Verify all counts are zero.
     * Hook: “If bucket empty → anagram.”
     *
     * Time Complexity: O(n)
     * Space Complexity: O(1) (fixed array of size 26)
     * Concept: Hashing / Frequency array.
     */
    public boolean isAnagramOptimal(String s, String t) {
        // Step 1: Quick check — lengths must match
        if (s.length() != t.length()) return false;

        // Step 2: Frequency array for 26 lowercase letters
        int[] count = new int[26];

        // Step 3: Count characters in s
        for (char c : s.toCharArray()) {
            count[c - 'a']++;
        }

        // Step 4: Subtract counts using t
        for (char c : t.toCharArray()) {
            count[c - 'a']--;
        }

        // Step 5: Verify all counts are zero
        for (int val : count) {
            if (val != 0) return false;
        }

        return true; // All counts balanced → anagram
    }


    /**
     * Algorithm 2: Brute Force Sorting
     * --------------------------------
     * Idea:
     * - Sort both strings.
     * - If sorted versions are equal → anagram.
     *
     * Time Complexity: O(n log n)
     * Space Complexity: O(1) (ignoring sort overhead)
     * Concept: Sorting comparison.
     */
    public boolean isAnagramBrute(String s, String t) {
        if (s.length() != t.length()) return false;
        char[] sArr = s.toCharArray();
        char[] tArr = t.toCharArray();
        Arrays.sort(sArr);
        Arrays.sort(tArr);
        return Arrays.equals(sArr, tArr);
    }
}
