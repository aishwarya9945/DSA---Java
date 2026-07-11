package Arrays.Hashing;

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
     * Mnemonic
     * Think of it as “Count → Heap → Result”:
     *
     * Count → Use a HashMap to count frequencies.
     * Hook: “Map counts numbers.”
     *
     * Heap → Use a min‑heap of size k to keep only the top k frequent.
     * Hook: “Heap throws away extras.”
     *
     * Result → Drain heap into result array.
     * Hook: “Poll heap, fill result.”
     *
     * Time Complexity: O(n log k)
     * Space Complexity: O(n)
     * Concept: HashMap + Min-Heap.
     */
    public static int[] topKFrequentOptimal(int[] nums, int k) {
        // Step 1: Count frequency of each number
        Map<Integer, Integer> freqMap = new HashMap<>();
        for (int num : nums) {
            freqMap.put(num, freqMap.getOrDefault(num, 0) + 1);
        }

        // Step 2: MinHeap (smallest frequency at top)
        // Keeps only k most frequent elements
        PriorityQueue<Map.Entry<Integer, Integer>> minHeap =
                new PriorityQueue<>(Comparator.comparingInt(Map.Entry::getValue));

        for (Map.Entry<Integer, Integer> entry : freqMap.entrySet()) {
            minHeap.offer(entry);
            if (minHeap.size() > k) {
                minHeap.poll(); // remove least frequent
            }
        }

        // Step 3: Build result array from heap
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

    /**
     * Problem: 209. Minimum Size Subarray Sum
     * ---------------------------------------
     * Given an array of positive integers nums and a positive integer target,
     * return the minimal length of a subarray whose sum is greater than or equal to target.
     * If there is no such subarray, return 0 instead.
     *
     * Example 1:
     * Input  : target = 7, nums = [2,3,1,2,4,3]
     * Output : 2
     * Explanation: The subarray [4,3] has sum 7 and length 2.
     *
     * Example 2:
     * Input  : target = 4, nums = [1,4,4]
     * Output : 1
     *
     * Example 3:
     * Input  : target = 11, nums = [1,1,1,1,1,1,1,1]
     * Output : 0
     *
     * Constraints:
     * - 1 <= target <= 10^9
     * - 1 <= nums.length <= 10^5
     * - 1 <= nums[i] <= 10^4
     *
     * Approaches Implemented:
     * 1. Optimal Sliding Window Approach (O(n), O(1))
     * 2. Brute Force Approach (O(n^2), O(1))
     */
    public static class MinimumSizeSubarraySum {

        /**
         * Algorithm 1: Optimal Sliding Window
         * -----------------------------------
         * Idea:
         * - Use two pointers (start, end) to maintain a window.
         * - Expand end to increase sum.
         * - Shrink start while sum >= target, update minLength.
         *
         * Time Complexity: O(n)
         * Space Complexity: O(1)
         * Concept: Sliding Window.
         */
        public int minSubArrayLenOptimal(int target, int[] nums) {
            int n = nums.length;
            int left = 0, sum = 0;
            int minLength = Integer.MAX_VALUE;

            for (int right = 0; right < n; right++) {
                sum += nums[right];
                while (sum >= target) {
                    minLength = Math.min(minLength, right - left + 1);
                    sum -= nums[left];
                    left++;
                }
            }
            return minLength == Integer.MAX_VALUE ? 0 : minLength;
        }

        /**
         * Algorithm 2: Brute Force Approach
         * ---------------------------------
         * Idea:
         * - Try all subarrays.
         * - For each start index, expand end index until sum >= target.
         * - Track minimal length.
         *
         * Time Complexity: O(n^2)
         * Space Complexity: O(1)
         * Concept: Simple enumeration.
         */
        public int minSubArrayLenBrute(int target, int[] nums) {
            int n = nums.length;
            int minLength = Integer.MAX_VALUE;

            for (int i = 0; i < n; i++) {
                int sum = 0;
                for (int j = i; j < n; j++) {
                    sum += nums[j];
                    if (sum >= target) {
                        minLength = Math.min(minLength, j - i + 1);
                        break; // no need to expand further
                    }
                }
            }
            return minLength == Integer.MAX_VALUE ? 0 : minLength;
        }
    }
}
