package StringAndHashing;

import java.util.*;

/**
 * Problem: 451. Sort Characters By Frequency
 * ------------------------------------------
 * Given a string s, sort it in decreasing order based on the frequency of the characters.
 * The frequency of a character is the number of times it appears in the string.
 *
 * Example 1:
 * Input  : s = "tree"
 * Output : "eert" (or "eetr")
 *
 * Example 2:
 * Input  : s = "cccaaa"
 * Output : "cccaaa" (or "aaaccc")
 *
 * Example 3:
 * Input  : s = "Aabb"
 * Output : "bbAa" (or "bbaA")
 *
 * Constraints:
 * - 1 <= s.length <= 5 * 10^5
 * - s consists of uppercase/lowercase English letters and digits.
 *
 * Approaches Implemented:
 * 1. Optimal HashMap + MaxHeap Approach (O(n log n), O(n))
 * 2. Brute Force Sorting Approach (O(n log n), O(n))
 */
public class SortCharactersByFrequency {

    /**
     * Algorithm 1: Optimal HashMap + MaxHeap
     * --------------------------------------
     * Idea:
     * - Count frequency of each character using HashMap.
     * - Push entries into a MaxHeap sorted by frequency.
     * - Build result string by repeatedly appending characters.
     *
     * Time Complexity: O(n log n)
     * Space Complexity: O(n)
     * Concept: Hashing + Priority Queue.
     */
    public String frequencySortOptimal(String s) {
        Map<Character, Integer> freqMap = new HashMap<>();
        for (char c : s.toCharArray()) {
            freqMap.put(c, freqMap.getOrDefault(c, 0) + 1);
        }

        PriorityQueue<Map.Entry<Character, Integer>> maxHeap =
                new PriorityQueue<>((a, b) -> b.getValue() - a.getValue());

        maxHeap.addAll(freqMap.entrySet());

        StringBuilder sb = new StringBuilder();
        while (!maxHeap.isEmpty()) {
            Map.Entry<Character, Integer> entry = maxHeap.poll();
            for (int i = 0; i < entry.getValue(); i++) {
                sb.append(entry.getKey());
            }
        }
        return sb.toString();
    }

    /**
     * Algorithm 2: Brute Force Sorting
     * --------------------------------
     * Idea:
     * - Count frequency of each character.
     * - Convert to list of pairs (char, freq).
     * - Sort list by frequency descending.
     * - Build result string.
     *
     * Time Complexity: O(n log n)
     * Space Complexity: O(n)
     * Concept: Sorting by frequency.
     */
    public String frequencySortBrute(String s) {
        Map<Character, Integer> freqMap = new HashMap<>();
        for (char c : s.toCharArray()) {
            freqMap.put(c, freqMap.getOrDefault(c, 0) + 1);
        }

        List<Map.Entry<Character, Integer>> list = new ArrayList<>(freqMap.entrySet());
        list.sort((a, b) -> b.getValue() - a.getValue());

        StringBuilder sb = new StringBuilder();
        for (Map.Entry<Character, Integer> entry : list) {
            for (int i = 0; i < entry.getValue(); i++) {
                sb.append(entry.getKey());
            }
        }
        return sb.toString();
    }
}
