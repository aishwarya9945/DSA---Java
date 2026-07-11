package Strings.SlidingWindow;

import java.util.*;

public class SubstringWithConcatenationOfAllWords {

    /**
     * Algorithm: Optimal Sliding Window + HashMap
     * --------------------------------------------
     * Mnemonic: "Expand in word-size chunks → Track → Shrink → Record."
     *
     * Idea:
     * - Each word has fixed length (say L).
     * - Total substring length = L * number of words.
     * - Use HashMap to store frequency of words in list.
     * - Slide window in steps of L across string.
     * - Track counts in current window.
     * - If counts match target → record starting index.
     *
     * Time Complexity: O(n * L) (n = length of s, L = word length)
     * Space Complexity: O(m * L) (m = number of words)
     */
    public static List<Integer> findSubstringOptimal(String s, String[] words) {
        List<Integer> result = new ArrayList<>();
        if (s.length() == 0 || words.length == 0) return result;

        int wordLen = words[0].length();
        int totalLen = wordLen * words.length;

        // frequency map of words
        Map<String, Integer> wordMap = new HashMap<>();
        for (String w : words) {
            wordMap.put(w, wordMap.getOrDefault(w, 0) + 1);
        }

        // try each possible offset within word length
        for (int i = 0; i < wordLen; i++) {
            int left = i;
            int count = 0;
            Map<String, Integer> windowMap = new HashMap<>();

            for (int right = i; right + wordLen <= s.length(); right += wordLen) {
                String word = s.substring(right, right + wordLen);

                if (wordMap.containsKey(word)) {
                    windowMap.put(word, windowMap.getOrDefault(word, 0) + 1);
                    count++;

                    // shrink window if word frequency exceeds target
                    while (windowMap.get(word) > wordMap.get(word)) {
                        String leftWord = s.substring(left, left + wordLen);
                        windowMap.put(leftWord, windowMap.get(leftWord) - 1);
                        left += wordLen;
                        count--;
                    }

                    // check if valid window
                    if (count == words.length) {
                        result.add(left);
                    }
                } else {
                    // reset window if word not in list
                    windowMap.clear();
                    count = 0;
                    left = right + wordLen;
                }
            }
        }

        return result;
    }

    /**
     * Algorithm: Brute Force
     * ----------------------
     * Idea:
     * - For each starting index, extract substring of length totalLen.
     * - Split into words of length L.
     * - Compare frequency with target.
     * - If equal → record index.
     *
     * Time Complexity: O(n * m * L) (n = length of s, m = number of words)
     * Space Complexity: O(m * L)
     */
    public static List<Integer> findSubstringBruteForce(String s, String[] words) {
        List<Integer> result = new ArrayList<>();
        if (s.length() == 0 || words.length == 0) return result;

        int wordLen = words[0].length();
        int totalLen = wordLen * words.length;

        Map<String, Integer> wordMap = new HashMap<>();
        for (String w : words) {
            wordMap.put(w, wordMap.getOrDefault(w, 0) + 1);
        }

        for (int i = 0; i <= s.length() - totalLen; i++) {
            String sub = s.substring(i, i + totalLen);
            Map<String, Integer> seen = new HashMap<>();

            for (int j = 0; j < totalLen; j += wordLen) {
                String word = sub.substring(j, j + wordLen);
                seen.put(word, seen.getOrDefault(word, 0) + 1);
            }

            if (seen.equals(wordMap)) {
                result.add(i);
            }
        }

        return result;
    }

    /**
     * Main method for testing
     */
    public static void main(String[] args) {
        String s = "barfoothefoobarman";
        String[] words = {"foo","bar"};

        System.out.println("Optimal Sliding Window Result: " + findSubstringOptimal(s, words)); // Expected: [0, 9]
        System.out.println("Brute Force Result: " + findSubstringBruteForce(s, words));
    }
}
