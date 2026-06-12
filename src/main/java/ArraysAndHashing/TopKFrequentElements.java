package ArraysAndHashing;

import java.util.*;

/**
 * Program to find the k most frequent elements in an array.
 * Implements:
 * 1. Optimal Heap + HashMap Approach (O(n log k))
 * 2. Brute Force Sorting Approach (O(n log n))
 *
 * Problem:
 * Given an integer array nums and an integer k, return the k most frequent elements.
 *
 * Example:
 * Input  : nums = [1,1,1,2,2,3], k = 2
 * Output : [1,2]
 * Explanation: 1 appears 3 times, 2 appears 2 times → top 2 frequent.
 */
public class TopKFrequentElements {

    public static void main(String[] args) {
        int nums[] = {1, 1, 1, 2, 2, 3};
        int k = 2;

        // ---------- Optimal Approach ----------
        int[] optimalResult = topKFrequentOptimal(nums, k);
        System.out.print("Top K Frequent Elements (Optimal): ");
        printArray(optimalResult);

        // ---------- Brute Force Approach ----------
        int[] bruteResult = topKFrequentBrute(nums, k);
        System.out.print("Top K Frequent Elements (Brute Force): ");
        printArray(bruteResult);
    }

    /**
     * Algorithm 1: Optimal Heap + HashMap Approach
     * --------------------------------------------
     * Idea:
     * Use a HashMap to count frequencies, then a Min-Heap to keep top k elements.
     *
     * Steps:
     * 1. Count frequency of each element using HashMap.
     * 2. Use a Min-Heap (priority queue) of size k:
     *      - Push (element, frequency).
     *      - If heap size > k, pop smallest frequency.
     * 3. Extract elements from heap.
     *
     * Time Complexity: O(n log k)
     * Space Complexity: O(n)
     * Concept: HashMap + Min-Heap.
     */
    public static int[] topKFrequentOptimal(int[] nums, int k) {
        Map<Integer, Integer> freqMap = new HashMap<>();
        for (int num : nums) {
            freqMap.put(num, freqMap.getOrDefault(num, 0) + 1);
        }

        PriorityQueue<Map.Entry<Integer, Integer>> minHeap =
                new PriorityQueue<>(Comparator.comparingInt(Map.Entry::getValue));

        for (Map.Entry<Integer, Integer> entry : freqMap.entrySet()) {
            minHeap.offer(entry);
            if (minHeap.size() > k) {
                minHeap.poll();
            }
        }

        int[] result = new int[k];
        int index = 0;
        while (!minHeap.isEmpty()) {
            result[index++] = minHeap.poll().getKey();
        }

        return result;
    }

    /**
     * Algorithm 2: Brute Force Sorting Approach
     * -----------------------------------------
     * Idea:
     * Count frequencies, then sort by frequency descending.
     *
     * Steps:
     * 1. Count frequency using HashMap.
     * 2. Convert map entries to list.
     * 3. Sort list by frequency descending.
     * 4. Pick first k elements.
     *
     * Time Complexity: O(n log n)
     * Space Complexity: O(n)
     * Concept: Sorting by frequency.
     */
    public static int[] topKFrequentBrute(int[] nums, int k) {
        Map<Integer, Integer> freqMap = new HashMap<>();
        for (int num : nums) {
            freqMap.put(num, freqMap.getOrDefault(num, 0) + 1);
        }

        List<Map.Entry<Integer, Integer>> list = new ArrayList<>(freqMap.entrySet());
        list.sort((a, b) -> b.getValue() - a.getValue());

        int[] result = new int[k];
        for (int i = 0; i < k; i++) {
            result[i] = list.get(i).getKey();
        }

        return result;
    }

    // Utility method to print array
    private static void printArray(int[] nums) {
        System.out.print("[");
        for (int i = 0; i < nums.length; i++) {
            System.out.print(nums[i]);
            if (i < nums.length - 1) System.out.print(", ");
        }
        System.out.println("]");
    }
}
