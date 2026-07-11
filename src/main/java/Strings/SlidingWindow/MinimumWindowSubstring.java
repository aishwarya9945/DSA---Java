package Strings.SlidingWindow;

import java.util.HashMap;

public class MinimumWindowSubstring {

    /**
     * Algorithm: Optimal Sliding Window + HashMap
     * --------------------------------------------
     * Mnemonic: "Expand → Cover → Shrink → Record."
     *
     * Idea:
     * - Use two HashMaps: one for target frequencies, one for current window.
     * - Expand window with right pointer until all target chars are covered.
     * - Shrink from left to minimize window while still valid.
     * - Track smallest valid window.
     *
     * Time Complexity: O(n)
     * Space Complexity: O(target size)
     */
    public static String minWindowOptimal(String s, String t) {
        if (s.length() < t.length()) return "";

        HashMap<Character, Integer> targetMap = new HashMap<>();
        for (char c : t.toCharArray()) {
            targetMap.put(c, targetMap.getOrDefault(c, 0) + 1);
        }

        HashMap<Character, Integer> windowMap = new HashMap<>();
        int left = 0, right = 0;
        int required = targetMap.size(); // distinct chars needed
        int formed = 0;                  // distinct chars matched
        int minLen = Integer.MAX_VALUE;
        int minLeft = 0;

        while (right < s.length()) {
            char c = s.charAt(right);
            windowMap.put(c, windowMap.getOrDefault(c, 0) + 1);

            if (targetMap.containsKey(c) &&
                    windowMap.get(c).intValue() == targetMap.get(c).intValue()) {
                formed++;
            }

            // shrink window until invalid
            while (left <= right && formed == required) {
                if (right - left + 1 < minLen) {
                    minLen = right - left + 1;
                    minLeft = left;
                }

                char leftChar = s.charAt(left);
                windowMap.put(leftChar, windowMap.get(leftChar) - 1);
                if (targetMap.containsKey(leftChar) &&
                        windowMap.get(leftChar).intValue() < targetMap.get(leftChar).intValue()) {
                    formed--;
                }
                left++;
            }

            right++;
        }

        return minLen == Integer.MAX_VALUE ? "" : s.substring(minLeft, minLeft + minLen);
    }

    /**
     * Algorithm: Brute Force
     * ----------------------
     * Idea:
     * - Try all substrings.
     * - Check if substring contains all chars of t.
     * - Track smallest valid substring.
     *
     * Time Complexity: O(n^3)
     * Space Complexity: O(n)
     */
    public static String minWindowBruteForce(String s, String t) {
        int n = s.length();
        int minLen = Integer.MAX_VALUE;
        String result = "";

        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                String sub = s.substring(i, j + 1);
                if (containsAll(sub, t)) {
                    if (sub.length() < minLen) {
                        minLen = sub.length();
                        result = sub;
                    }
                }
            }
        }

        return result;
    }

    // helper method to check if substring contains all chars of t
    private static boolean containsAll(String sub, String t) {
        HashMap<Character, Integer> map = new HashMap<>();
        for (char c : sub.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        for (char c : t.toCharArray()) {
            if (!map.containsKey(c) || map.get(c) == 0) return false;
            map.put(c, map.get(c) - 1);
        }
        return true;
    }

    /**
     * Main method for testing
     */
    public static void main(String[] args) {
        String s = "ADOBECODEBANC";
        String t = "ABC";

        System.out.println("Optimal Sliding Window Result: " + minWindowOptimal(s, t)); // Expected: "BANC"
        System.out.println("Brute Force Result: " + minWindowBruteForce(s, t));
    }
}
