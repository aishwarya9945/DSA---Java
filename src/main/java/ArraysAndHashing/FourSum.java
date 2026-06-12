package ArraysAndHashing;

import java.util.*;

/**
 * Program to find all unique quadruplets in the array which sum up to the target.
 * Implements:
 * 1. Optimal Two-Pointer Approach (O(n^3))
 * 2. Brute Force Approach (O(n^4))
 *
 * Problem:
 * Given an array nums of n integers, return all unique quadruplets [nums[a], nums[b], nums[c], nums[d]]
 * such that:
 *   - 0 <= a, b, c, d < n
 *   - a, b, c, d are distinct
 *   - nums[a] + nums[b] + nums[c] + nums[d] == target
 *
 * Example:
 * Input  : nums = [1,0,-1,0,-2,2], target = 0
 * Output : [[-2,-1,1,2], [-2,0,0,2], [-1,0,0,1]]
 */
public class FourSum {

    public static void main(String[] args) {
        int nums[] = {1, 0, -1, 0, -2, 2};
        int target = 0;

        // ---------- Optimal Approach ----------
        List<List<Integer>> optimalResult = fourSumOptimal(nums, target);
        System.out.println("Four Sum (Optimal): " + optimalResult);

        // ---------- Brute Force Approach ----------
        List<List<Integer>> bruteResult = fourSumBrute(nums, target);
        System.out.println("Four Sum (Brute Force): " + bruteResult);
    }

    /**
     * Algorithm 1: Optimal Two-Pointer Approach
     * -----------------------------------------
     * Idea:
     * Sort the array, then fix two numbers and use two pointers to find the remaining two.
     *
     * Steps:
     * 1. Sort the array.
     * 2. Loop i from 0 to n-4:
     *      - Skip duplicates.
     * 3. Loop j from i+1 to n-3:
     *      - Skip duplicates.
     * 4. Use two pointers (left, right) to find pairs that sum to target - nums[i] - nums[j].
     * 5. Skip duplicates for left and right.
     *
     * Time Complexity: O(n^3)
     * Space Complexity: O(1) (excluding output list)
     * Concept: Sorting + two pointers.
     */
    public static List<List<Integer>> fourSumOptimal(int[] nums, int target) {
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();

        for (int i = 0; i < nums.length - 3; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) continue;

            for (int j = i + 1; j < nums.length - 2; j++) {
                if (j > i + 1 && nums[j] == nums[j - 1]) continue;

                int left = j + 1, right = nums.length - 1;

                while (left < right) {
                    long sum = (long) nums[i] + nums[j] + nums[left] + nums[right];

                    if (sum == target) {
                        result.add(Arrays.asList(nums[i], nums[j], nums[left], nums[right]));

                        while (left < right && nums[left] == nums[left + 1]) left++;
                        while (left < right && nums[right] == nums[right - 1]) right--;

                        left++;
                        right--;
                    } else if (sum < target) {
                        left++;
                    } else {
                        right--;
                    }
                }
            }
        }
        return result;
    }

    /**
     * Algorithm 2: Brute Force Approach
     * ---------------------------------
     * Idea:
     * Try all quadruplets using 4 nested loops.
     *
     * Steps:
     * 1. Loop i, j, k, l over all indices.
     * 2. If nums[i] + nums[j] + nums[k] + nums[l] == target → add to result.
     * 3. Use a Set to avoid duplicates.
     *
     * Time Complexity: O(n^4)
     * Space Complexity: O(n^4) (due to set storage)
     * Concept: Exhaustive search.
     */
    public static List<List<Integer>> fourSumBrute(int[] nums, int target) {
        Set<List<Integer>> set = new HashSet<>();
        int n = nums.length;

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                for (int k = j + 1; k < n; k++) {
                    for (int l = k + 1; l < n; l++) {
                        if ((long) nums[i] + nums[j] + nums[k] + nums[l] == target) {
                            List<Integer> quad = Arrays.asList(nums[i], nums[j], nums[k], nums[l]);
                            Collections.sort(quad);
                            set.add(quad);
                        }
                    }
                }
            }
        }

        return new ArrayList<>(set);
    }
}
