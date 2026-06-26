package Greedy;

import java.util.*;

/**
 * Problem: 621. Task Scheduler
 * ----------------------------
 * You are given an array of CPU tasks, each labeled with a letter from A to Z, and a number n.
 * Each CPU interval can be idle or allow the completion of one task.
 * Tasks can be completed in any order, but there has to be a gap of at least n intervals
 * between two tasks with the same label.
 *
 * Return the minimum number of CPU intervals required to complete all tasks.
 *
 * Example 1:
 * Input  : tasks = ["A","A","A","B","B","B"], n = 2
 * Output : 8
 *
 * Example 2:
 * Input  : tasks = ["A","C","A","B","D","B"], n = 1
 * Output : 6
 *
 * Example 3:
 * Input  : tasks = ["A","A","A","B","B","B"], n = 3
 * Output : 10
 *
 * Constraints:
 * - 1 <= tasks.length <= 10^4
 * - tasks[i] is an uppercase English letter.
 * - 0 <= n <= 100
 *
 * Approaches Implemented:
 * 1. Optimal Greedy + Math Formula (O(n), O(1))
 * 2. Brute Force Simulation (O(n log n), O(n))
 */
public class TaskScheduler {

    /**
     * Algorithm 1: Optimal Greedy + Math Formula
     * ------------------------------------------
     * Idea:
     * - Count frequency of each task.
     * - Let maxFreq = maximum frequency.
     * - Let countMax = number of tasks with frequency = maxFreq.
     * - Formula:
     *   result = max(tasks.length, (maxFreq - 1) * (n + 1) + countMax)
     *
     * Explanation:
     * - (maxFreq - 1) blocks of size (n+1), each block contains one occurrence of the most frequent task.
     * - Add countMax for the last block.
     * - Compare with total tasks length (if tasks fill idle slots).
     *
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     * Concept: Greedy + Counting.
     */
    public int leastIntervalOptimal(char[] tasks, int n) {
        int[] freq = new int[26];
        for (char c : tasks) freq[c - 'A']++;

        int maxFreq = 0;
        for (int f : freq) maxFreq = Math.max(maxFreq, f);

        int countMax = 0;
        for (int f : freq) {
            if (f == maxFreq) countMax++;
        }

        return Math.max(tasks.length, (maxFreq - 1) * (n + 1) + countMax);
    }

    /**
     * Algorithm 2: Brute Force Simulation
     * -----------------------------------
     * Idea:
     * - Use a maxHeap to pick tasks with highest remaining count.
     * - Each cycle of (n+1) intervals: execute up to (n+1) tasks.
     * - Push back tasks with remaining counts.
     * - Continue until all tasks are done.
     *
     * Time Complexity: O(n log n)
     * Space Complexity: O(n)
     * Concept: Priority Queue simulation.
     */
    public int leastIntervalBrute(char[] tasks, int n) {
        Map<Character, Integer> freqMap = new HashMap<>();
        for (char c : tasks) freqMap.put(c, freqMap.getOrDefault(c, 0) + 1);

        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        maxHeap.addAll(freqMap.values());

        int time = 0;
        while (!maxHeap.isEmpty()) {
            List<Integer> temp = new ArrayList<>();
            int cycle = n + 1;

            for (int i = 0; i < cycle; i++) {
                if (!maxHeap.isEmpty()) {
                    temp.add(maxHeap.poll() - 1);
                }
            }

            for (int t : temp) {
                if (t > 0) maxHeap.add(t);
            }

            time += maxHeap.isEmpty() ? temp.size() : cycle;
        }
        return time;
    }
}
