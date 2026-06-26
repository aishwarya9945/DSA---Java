package ArraysAndHashing;

import java.util.*;

/**
 * Problem: 49. Group Anagrams
 * ---------------------------
 * Given an array of strings strs, group the anagrams together.
 * You can return the answer in any order.
 *
 * Example 1:
 * Input  : strs = ["eat","tea","tan","ate","nat","bat"]
 * Output : [["bat"],["nat","tan"],["ate","eat","tea"]]
 *
 * Example 2:
 * Input  : strs = [""]
 * Output : [[""]]
 *
 * Example 3:
 * Input  : strs = ["a"]
 * Output : [["a"]]
 *
 * Constraints:
 * - 1 <= strs.length <= 10^4
 * - 0 <= strs[i].length <= 100
 * - strs[i] consists of lowercase English letters.
 *
 * Approaches Implemented:
 * 1. Optimal HashMap + Frequency Count (O(n * k), O(n * k))
 * 2. Brute Force Sorting Key Approach (O(n * k log k), O(n * k))
 */
public class GroupAnagrams {

    /**
     * Algorithm 1: Optimal HashMap + Frequency Count
     * ----------------------------------------------
     * Idea:
     * - For each string, build a frequency signature (26-length char count).
     * - Use this signature as a key in HashMap.
     * - Group strings by identical signatures.
     *
     * Time Complexity: O(n * k) (n = number of strings, k = max length of string)
     * Space Complexity: O(n * k)
     * Concept: Hashing with frequency arrays.
     */
    public List<List<String>> groupAnagramsOptimal(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();

        for (String s : strs) {
            int[] count = new int[26];
            for (char c : s.toCharArray()) {
                count[c - 'a']++;
            }
            StringBuilder keyBuilder = new StringBuilder();
            for (int val : count) {
                keyBuilder.append('#').append(val);
            }
            String key = keyBuilder.toString();

            map.computeIfAbsent(key, k -> new ArrayList<>()).add(s);
        }
        return new ArrayList<>(map.values());
    }

    /**
     * Algorithm 2: Brute Force Sorting Key
     * ------------------------------------
     * Idea:
     * - Sort characters of each string.
     * - Use sorted string as key in HashMap.
     * - Group strings by identical sorted keys.
     *
     * Time Complexity: O(n * k log k)
     * Space Complexity: O(n * k)
     * Concept: Sorting-based grouping.
     */
    public List<List<String>> groupAnagramsBrute(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();

        for (String s : strs) {
            char[] arr = s.toCharArray();
            Arrays.sort(arr);
            String key = new String(arr);

            map.computeIfAbsent(key, k -> new ArrayList<>()).add(s);
        }
        return new ArrayList<>(map.values());
    }
}
