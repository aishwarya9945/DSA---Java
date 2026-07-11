package Strings.SlidingWindow;

import java.util.*;

public class FindAllAnagramsInString {

    /**
     * Algorithm: Optimal Sliding Window + Frequency Map
     * -------------------------------------------------
     * Mnemonic: "Expand → Match → Shrink → Record."
     *
     * Idea:
     * - Use frequency arrays (size 26 for lowercase letters).
     * - Expand window with right pointer.
     * - When window size == pattern length, compare frequency arrays.
     * - If equal → record starting index.
     * - Slide window forward by removing left char.
     *
     * Time Complexity: O(n) (since comparing arrays is O(26) = constant)
     * Space Complexity: O(1) (fixed size arrays)
     */
    public static List<Integer> findAnagramsOptimal(String s, String p) {
        List<Integer> result = new ArrayList<>();
        if (s.length() < p.length()) return result;

        int[] pCount = new int[26]; // frequency of chars in pattern
        int[] sCount = new int[26]; // frequency of chars in current window

        // build frequency for pattern
        for (char c : p.toCharArray()) {
            pCount[c - 'a']++;
        }

        int left = 0;
        for (int right = 0; right < s.length(); right++) {
            sCount[s.charAt(right) - 'a']++; // add char to window

            // shrink window if size > p.length()
            if (right - left + 1 > p.length()) {
                sCount[s.charAt(left) - 'a']--; // remove left char
                left++;
            }

            // check if window matches pattern
            if (right - left + 1 == p.length()) {
                if (Arrays.equals(sCount, pCount)) {
                    result.add(left);
                }
            }
        }

        return result;
    }

    /**
     * Algorithm: Brute Force
     * ----------------------
     * Idea:
     * - Try all substrings of length p.length().
     * - Sort substring and pattern.
     * - If equal → record index.
     *
     * Time Complexity: O(n * m log m) (n = s.length, m = p.length)
     * Space Complexity: O(m)
     */
    public static List<Integer> findAnagramsBruteForce(String s, String p) {
        List<Integer> result = new ArrayList<>();
        int n = s.length(), m = p.length();

        char[] pArr = p.toCharArray();
        Arrays.sort(pArr);
        String sortedP = new String(pArr);

        for (int i = 0; i <= n - m; i++) {
            String sub = s.substring(i, i + m);
            char[] subArr = sub.toCharArray();
            Arrays.sort(subArr);
            if (new String(subArr).equals(sortedP)) {
                result.add(i);
            }
        }

        return result;
    }

    /**
     * Main method for testing
     */
    public static void main(String[] args) {
        String s = "cbaebabacd";
        String p = "abc";

        System.out.println("Optimal Sliding Window Result: " + findAnagramsOptimal(s, p)); // Expected: [0, 6]
        System.out.println("Brute Force Result: " + findAnagramsBruteForce(s, p));
    }
}
