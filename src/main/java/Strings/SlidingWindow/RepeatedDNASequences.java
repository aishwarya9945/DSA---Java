package Strings.SlidingWindow;

import java.util.*;

public class RepeatedDNASequences {

    /**
     * Algorithm: Optimal HashSet + Sliding Window
     * --------------------------------------------
     * Mnemonic: "Slide → Track → Detect repeats."
     *
     * Idea:
     * - DNA string consists of 'A','C','G','T'.
     * - We need to find all 10-letter-long substrings that occur more than once.
     * - Use a HashSet to track seen substrings.
     * - Use another HashSet to track repeated substrings.
     *
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     */
    public static List<String> findRepeatedDnaSequencesOptimal(String s) {
        Set<String> seen = new HashSet<>();
        Set<String> repeated = new HashSet<>();

        // slide window of length 10
        for (int i = 0; i + 10 <= s.length(); i++) {
            String sub = s.substring(i, i + 10);

            if (!seen.add(sub)) { // if already seen
                repeated.add(sub);
            }
        }

        return new ArrayList<>(repeated);
    }

    /**
     * Algorithm: Brute Force
     * ----------------------
     * Idea:
     * - Generate all substrings of length 10.
     * - Count frequency using HashMap.
     * - Collect substrings with frequency > 1.
     *
     * Time Complexity: O(n * 10) ~ O(n)
     * Space Complexity: O(n)
     */
    public static List<String> findRepeatedDnaSequencesBruteForce(String s) {
        Map<String, Integer> freqMap = new HashMap<>();
        List<String> result = new ArrayList<>();

        for (int i = 0; i + 10 <= s.length(); i++) {
            String sub = s.substring(i, i + 10);
            freqMap.put(sub, freqMap.getOrDefault(sub, 0) + 1);
        }

        for (Map.Entry<String, Integer> entry : freqMap.entrySet()) {
            if (entry.getValue() > 1) {
                result.add(entry.getKey());
            }
        }

        return result;
    }

    /**
     * Main method for testing
     */
    public static void main(String[] args) {
        String s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT";

        System.out.println("Optimal Sliding Window Result: " + findRepeatedDnaSequencesOptimal(s)); // Expected: [AAAAACCCCC, CCCCCAAAAA]
        System.out.println("Brute Force Result: " + findRepeatedDnaSequencesBruteForce(s));
    }
}
