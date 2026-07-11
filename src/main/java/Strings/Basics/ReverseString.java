package Strings.Basics;

import java.util.Arrays;

public class ReverseString {

    /**
     * Problem Statement:
     * ------------------
     * Write a function that reverses a string. The input string is given as an array of characters s.
     * You must do this by modifying the input array in-place with O(1) extra memory.
     *
     * Example 1:
     * Input: s = ["h","e","l","l","o"]
     * Output: ["o","l","l","e","h"]
     *
     * Example 2:
     * Input: s = ["H","a","n","n","a","h"]
     * Output: ["h","a","n","n","a","H"]
     *
     * Constraints:
     * 1 <= s.length <= 10^5
     * s[i] is a printable ASCII character.
     */

    // ------------------------------------------------------------
    // Optimal Solution: Two Pointers
    // ------------------------------------------------------------
    /**
     * Algorithm Steps:
     * 1. Initialize two pointers: left = 0, right = s.length - 1.
     * 2. While left < right:
     *    - Swap s[left] and s[right].
     *    - Move left++ and right--.
     * 3. End when pointers meet.
     *
     * Mnemonic: "Two pointers → Swap → Shrink."
     *
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     */
    public void reverseStringOptimal(char[] s) {
        int left = 0, right = s.length - 1;

        while (left < right) {
            // swap characters
            char temp = s[left];
            s[left] = s[right];
            s[right] = temp;

            // move pointers inward
            left++;
            right--;
        }
    }

    // ------------------------------------------------------------
    // Brute Force Solution
    // ------------------------------------------------------------
    /**
     * Algorithm Steps:
     * 1. Create a new array result of same length.
     * 2. Copy characters from s into result in reverse order.
     * 3. Copy result back into s.
     *
     * Mnemonic: "Copy backwards → Replace."
     *
     * Time Complexity: O(n)
     * Space Complexity: O(n) (extra array used)
     */
    public void reverseStringBruteForce(char[] s) {
        char[] result = new char[s.length];

        // fill result backwards
        for (int i = 0; i < s.length; i++) {
            result[s.length - 1 - i] = s[i];
        }

        // copy back into original array
        for (int i = 0; i < s.length; i++) {
            s[i] = result[i];
        }
    }

    // ------------------------------------------------------------
    // Testing both approaches
    // ------------------------------------------------------------
    public static void main(String[] args) {
        ReverseString rs = new ReverseString();

        char[] s1 = {'h','e','l','l','o'};
        rs.reverseStringOptimal(s1);
        System.out.println("Optimal Result: " + Arrays.toString(s1)); // ["o","l","l","e","h"]

        char[] s2 = {'H','a','n','n','a','h'};
        rs.reverseStringBruteForce(s2);
        System.out.println("Brute Force Result: " + Arrays.toString(s2)); // ["h","a","n","n","a","H"]
    }
}
