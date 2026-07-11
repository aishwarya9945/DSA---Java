package Strings.SlidingWindow;

import java.util.*;

public class CountSubstringsWithoutRepeatingCharacters {

    /**
     * Algorithm: Optimal Sliding Window + HashSet
     * --------------------------------------------
     * Mnemonic: "Expand → Shrink duplicates → Count substrings."
     *
     * Idea:
     * - Use a sliding window with two pointers.
     * - Expand window with right pointer.
     * - If duplicate found, shrink from left until valid.
     * - For each valid window, count substrings ending at right:
     *   → (right - left + 1) substrings.
     *
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     */
    public static int countSubstringsOptimal(String s) {
        Set<Character> set = new HashSet<>();
        int left = 0;
        int count = 0;

        for (int right = 0; right < s.length(); right++) {
            char c = s.charAt(right);

            // shrink until no duplicate
            while (set.contains(c)) {
                set.remove(s.charAt(left));
                left++;
            }

            set.add(c);

            // all substrings ending at right are valid
            count += right - left + 1;
        }

        return count;
    }

    /**
     * Algorithm: Brute Force
     * ----------------------
     * Idea:
     * - Generate all substrings.
     * - Check if substring has all distinct characters.
     * - Count valid substrings.
     *
     * Time Complexity: O(n^3) (generate + check distinct)
     * Space Complexity: O(n)
     */
    public static int countSubstringsBruteForce(String s) {
        int n = s.length();
        int count = 0;

        for (int i = 0; i < n; i++) {
            Set<Character> set = new HashSet<>();
            for (int j = i; j < n; j++) {
                char c = s.charAt(j);
                if (set.contains(c)) break; // duplicate found
                set.add(c);
                count++;
            }
        }

        return count;
    }

    /**
     * Main method for testing
     */
    public static void main(String[] args) {
        String s = "abcab";

        System.out.println("Optimal Sliding Window Result: " + countSubstringsOptimal(s)); // Expected: 9
        System.out.println("Brute Force Result: " + countSubstringsBruteForce(s));
    }
}
