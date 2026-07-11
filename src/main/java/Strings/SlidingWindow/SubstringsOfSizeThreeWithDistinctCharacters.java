package Strings.SlidingWindow;

import java.util.*;

public class SubstringsOfSizeThreeWithDistinctCharacters {

    /**
     * Algorithm: Optimal Sliding Window
     * ---------------------------------
     * Mnemonic: "Slide window of 3 → Check distinct."
     *
     * Idea:
     * - Use a fixed-size sliding window of length 3.
     * - For each window, check if all 3 characters are distinct.
     * - Count valid substrings.
     *
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     */
    public static int countGoodSubstringsOptimal(String s) {
        int n = s.length();
        int count = 0;

        for (int i = 0; i + 2 < n; i++) {
            char a = s.charAt(i);
            char b = s.charAt(i + 1);
            char c = s.charAt(i + 2);

            if (a != b && a != c && b != c) {
                count++;
            }
        }

        return count;
    }

    /**
     * Algorithm: Brute Force
     * ----------------------
     * Idea:
     * - Generate all substrings of length 3.
     * - Use a Set to check distinct characters.
     * - Count valid substrings.
     *
     * Time Complexity: O(n * 3) ~ O(n)
     * Space Complexity: O(1)
     */
    public static int countGoodSubstringsBruteForce(String s) {
        int n = s.length();
        int count = 0;

        for (int i = 0; i + 2 < n; i++) {
            String sub = s.substring(i, i + 3);
            Set<Character> set = new HashSet<>();
            for (char c : sub.toCharArray()) {
                set.add(c);
            }
            if (set.size() == 3) {
                count++;
            }
        }

        return count;
    }

    /**
     * Main method for testing
     */
    public static void main(String[] args) {
        String s = "xyzzaz";

        System.out.println("Optimal Sliding Window Result: " + countGoodSubstringsOptimal(s)); // Expected: 1 ("xyz")
        System.out.println("Brute Force Result: " + countGoodSubstringsBruteForce(s));
    }
}
