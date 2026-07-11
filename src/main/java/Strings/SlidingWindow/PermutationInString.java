package Strings.SlidingWindow;

import java.util.*;

public class PermutationInString {

    /**
     * Algorithm: Optimal Sliding Window + Frequency Arrays
     * ----------------------------------------------------
     * Mnemonic: "Expand → Match → Shrink → Check."
     *
     * Idea:
     * - Use frequency arrays (size 26 for lowercase letters).
     * - Expand window with right pointer.
     * - When window size == pattern length, compare frequency arrays.
     * - If equal → return true (permutation exists).
     * - Slide window forward by removing left char.
     *
     * Time Complexity: O(n) (since comparing arrays is O(26) = constant)
     * Space Complexity: O(1) (fixed size arrays)
     */
    public static boolean checkInclusionOptimal(String s1, String s2) {
        if (s1.length() > s2.length()) return false;

        int[] s1Count = new int[26]; // frequency of chars in s1
        int[] s2Count = new int[26]; // frequency of chars in current window

        // build frequency for s1
        for (char c : s1.toCharArray()) {
            s1Count[c - 'a']++;
        }

        int left = 0;
        for (int right = 0; right < s2.length(); right++) {
            s2Count[s2.charAt(right) - 'a']++; // add char to window

            // shrink window if size > s1.length()
            if (right - left + 1 > s1.length()) {
                s2Count[s2.charAt(left) - 'a']--; // remove left char
                left++;
            }

            // check if window matches s1
            if (Arrays.equals(s1Count, s2Count)) {
                return true;
            }
        }

        return false;
    }

    /**
     * Algorithm: Brute Force
     * ----------------------
     * Idea:
     * - Generate all permutations of s1.
     * - Check if any permutation is a substring of s2.
     *
     * Time Complexity: O(n! * m) (not practical for large strings)
     * Space Complexity: O(n)
     */
    public static boolean checkInclusionBruteForce(String s1, String s2) {
        List<String> permutations = new ArrayList<>();
        generatePermutations("", s1, permutations);

        for (String perm : permutations) {
            if (s2.contains(perm)) return true;
        }
        return false;
    }

    // helper method to generate permutations
    private static void generatePermutations(String prefix, String remaining, List<String> result) {
        if (remaining.length() == 0) {
            result.add(prefix);
            return;
        }
        for (int i = 0; i < remaining.length(); i++) {
            generatePermutations(prefix + remaining.charAt(i),
                    remaining.substring(0, i) + remaining.substring(i + 1),
                    result);
        }
    }

    /**
     * Main method for testing
     */
    public static void main(String[] args) {
        String s1 = "ab";
        String s2 = "eidbaooo";

        System.out.println("Optimal Sliding Window Result: " + checkInclusionOptimal(s1, s2)); // Expected: true
        System.out.println("Brute Force Result: " + checkInclusionBruteForce(s1, s2));
    }
}
