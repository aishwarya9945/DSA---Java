package Arrays.SlidingWindow;

import java.util.HashMap;
import java.util.Map;

/**
 * Problem: 904. Fruit Into Baskets
 * --------------------------------
 * You are given an array fruits where fruits[i] is the type of fruit.
 * You start from any index and pick fruits into two baskets.
 * Each basket can only hold one type of fruit.
 * Return the maximum number of fruits you can pick.
 *
 * Example:
 * Input  : fruits = [1,2,1]
 * Output : 3
 * Explanation: You can pick all fruits since only 2 types exist.
 *
 * Constraints:
 * 1 <= fruits.length <= 10^5
 * 0 <= fruits[i] < fruits.length
 */
public class FruitIntoBaskets {

    /**
     * Algorithm: Optimal Sliding Window (Variable Size)
     * -------------------------------------------------
     * Mnemonic: "Expand → Shrink until ≤ 2 types → Record."
     *
     * Idea:
     * - Use a HashMap to track counts of fruit types in the window.
     * - Expand window by adding fruits[right].
     * - If more than 2 types, shrink from left until valid.
     * - Track the maximum window size.
     *
     * Time Complexity: O(n)
     * Space Complexity: O(1) (at most 2 types in map)
     */
    public static int totalFruitOptimal(int[] fruits) {
        Map<Integer, Integer> basket = new HashMap<>(); // map to store fruit counts
        int left = 0;                                   // window start
        int maxFruits = 0;                              // track max window size

        for (int right = 0; right < fruits.length; right++) {
            // expand window: add nums[right]
            basket.put(fruits[right], basket.getOrDefault(fruits[right], 0) + 1); // add fruit at right

            // shrink window if more than 2 types
            while (basket.size() > 2) {
                basket.put(fruits[left], basket.get(fruits[left]) - 1); // remove fruit at left
                if (basket.get(fruits[left]) == 0) basket.remove(fruits[left]); // clean up if count 0
                left++; // move window start forward
            }

            maxFruits = Math.max(maxFruits, right - left + 1); // record max window size
        }

        return maxFruits;
    }

    /**
     * Algorithm: Brute Force
     * ----------------------
     * Idea:
     * - For each starting index, expand subarray until > 2 types.
     * - Track the longest valid subarray.
     *
     * Time Complexity: O(n^2)
     * Space Complexity: O(n) (map for counts)
     */
    public static int totalFruitBruteForce(int[] fruits) {
        int n = fruits.length;
        int maxFruits = 0;

        for (int i = 0; i < n; i++) {
            Map<Integer, Integer> basket = new HashMap<>(); // new basket for each start
            for (int j = i; j < n; j++) {
                basket.put(fruits[j], basket.getOrDefault(fruits[j], 0) + 1); // add fruit

                if (basket.size() > 2) break; // stop if more than 2 types

                maxFruits = Math.max(maxFruits, j - i + 1); // record valid length
            }
        }

        return maxFruits;
    }

    /**
     * Main method for testing
     */
    public static void main(String[] args) {
        int[] fruits = {1, 2, 3, 2, 2};

        int optimalResult = totalFruitOptimal(fruits);
        int bruteResult = totalFruitBruteForce(fruits);

        System.out.println("Optimal Sliding Window Result: " + optimalResult);
        System.out.println("Brute Force Result: " + bruteResult);
    }
}
