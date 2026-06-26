package ArraysAndHashing;

import java.util.*;

/**
 * Problem: 287. Find the Duplicate Number
 * ---------------------------------------
 * Given an array nums containing n+1 integers where each integer is in the range [1, n],
 * return the duplicate number.
 *
 * You must solve the problem without modifying the array and using only constant extra space.
 *
 * Example 1:
 * Input  : nums = [1,3,4,2,2]
 * Output : 2
 *
 * Example 2:
 * Input  : nums = [3,1,3,4,2]
 * Output : 3
 *
 * Example 3:
 * Input  : nums = [3,3,3,3,3]
 * Output : 3
 *
 * Constraints:
 * - 1 <= n <= 10^5
 * - nums.length == n+1
 * - 1 <= nums[i] <= n
 * - Exactly one integer is repeated two or more times.
 *
 * Approaches Implemented:
 * 1. Optimal Floyd’s Cycle Detection (O(n), O(1))
 * 2. Brute Force HashSet Approach (O(n), O(n))
 */
public class FindTheDuplicateNumber {

    /**
     * Algorithm 1: Optimal Floyd’s Cycle Detection
     * --------------------------------------------
     * Idea:
     * - Treat array as a linked list: index → nums[index].
     * - Duplicate number creates a cycle.
     * - Use Floyd’s Tortoise and Hare to detect cycle start.
     *
     * Steps:
     * 1. Initialize slow = nums[0], fast = nums[0].
     * 2. Move slow = nums[slow], fast = nums[nums[fast]] until they meet.
     * 3. Reset slow = nums[0].
     * 4. Move both one step until they meet again → duplicate.
     *
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     * Concept: Cycle detection in linked list.
     */
    public int findDuplicateOptimal(int[] nums) {
        int slow = nums[0];
        int fast = nums[0];

        // Phase 1: Find intersection
        do {
            slow = nums[slow];
            fast = nums[nums[fast]];
        } while (slow != fast);

        // Phase 2: Find cycle start
        slow = nums[0];
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[fast];
        }
        return slow;
    }

    /**
     * Algorithm 2: Brute Force HashSet
     * --------------------------------
     * Idea:
     * - Use a HashSet to track seen numbers.
     * - First repeat encountered is the duplicate.
     *
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     * Concept: Hashing for duplicate detection.
     */
    public int findDuplicateBrute(int[] nums) {
        Set<Integer> seen = new HashSet<>();
        for (int num : nums) {
            if (seen.contains(num)) return num;
            seen.add(num);
        }
        return -1; // should never happen per constraints
    }
}
