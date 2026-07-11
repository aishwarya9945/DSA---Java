package Arrays.Hashing;

import java.util.TreeSet;

/**
 * Program to solve the Contains Duplicate III problem using:
 * 1. Optimal Balanced BST (TreeSet) Approach
 * 2. Brute Force Approach
 *
 * Problem:
 * Given an integer array nums and two integers k and t,
 * return true if there are two distinct indices i and j in the array such that:
 *   - abs(i - j) <= k
 *   - abs(nums[i] - nums[j]) <= t
 *
 * Example:
 * Input:  nums = [1,2,3,1], k = 3, t = 0
 * Output: true
 *
 * Concept:
 * - This is a sliding window + range query problem.
 * - Brute force checks all pairs within distance k.
 * - Optimal solution uses a TreeSet (balanced BST):
 *      → Maintain a sliding window of size k.
 *      → For each number, check if there exists a number in the TreeSet
 *        within [num - t, num + t].
 *      → Insert current number, remove element outside window.
 */
public class ContainsDuplicateIII {

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 1};
        int k = 3, t = 0;

        // ---------- Optimal TreeSet Approach ----------
        boolean optimalResult = containsNearbyAlmostDuplicateOptimal(nums, k, t);
        System.out.println("Contains Duplicate III (Optimal TreeSet): " + optimalResult);

        // ---------- Brute Force Approach ----------
        boolean bruteResult = containsNearbyAlmostDuplicateBrute(nums, k, t);
        System.out.println("Contains Duplicate III (Brute Force): " + bruteResult);
    }

    /**
     * Algorithm 1: Optimal Approach — TreeSet
     * ---------------------------------------
     * Steps:
     * 1. Initialize a TreeSet to maintain sliding window of size k.
     * 2. For each number:
     *      - Use ceiling() to find smallest >= num.
     *      - Use floor() to find largest <= num.
     *      - If either is within t distance → return true.
     *      - Add current number to TreeSet.
     *      - If window size exceeds k → remove nums[i-k].
     * 3. Return false if no pair found.
     *
     * Time Complexity: O(n log k)
     * Space Complexity: O(k)
     * Concept: Balanced BST for range queries in sliding window.
     */
    public static boolean containsNearbyAlmostDuplicateOptimal(int[] nums, int k, int t) {
        TreeSet<Long> set = new TreeSet<>();

        for (int i = 0; i < nums.length; i++) {
            long num = nums[i];

            // Find smallest >= num
            Long ceil = set.ceiling(num);
            if (ceil != null && ceil - num <= t) return true;

            // Find largest <= num
            Long floor = set.floor(num);
            if (floor != null && num - floor <= t) return true;

            set.add(num);

            // Maintain sliding window of size k
            if (i >= k) {
                set.remove((long) nums[i - k]);
            }
        }

        return false;
    }

    /**
     * Algorithm 2: Brute Force Approach
     * ---------------------------------
     * Steps:
     * 1. Loop i from 0 to n-1.
     * 2. For each i, loop j from i+1 to min(i+k, n-1).
     * 3. If abs(nums[i] - nums[j]) <= t → return true.
     * 4. Return false if no pair found.
     *
     * Time Complexity: O(nk)
     * Space Complexity: O(1)
     * Concept: Check all pairs within distance k.
     */
    public static boolean containsNearbyAlmostDuplicateBrute(int[] nums, int k, int t) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j <= i + k && j < nums.length; j++) {
                if (Math.abs((long) nums[i] - nums[j]) <= t) {
                    return true;
                }
            }
        }
        return false;
    }
}
