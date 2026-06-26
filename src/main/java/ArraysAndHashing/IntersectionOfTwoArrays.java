package ArraysAndHashing;

import java.util.*;

/**
 * Problem: 349. Intersection of Two Arrays
 * ----------------------------------------
 * Given two integer arrays nums1 and nums2, return an array of their intersection.
 * Each element in the result must be unique and you may return the result in any order.
 *
 * Example 1:
 * Input  : nums1 = [1,2,2,1], nums2 = [2,2]
 * Output : [2]
 *
 * Example 2:
 * Input  : nums1 = [4,9,5], nums2 = [9,4,9,8,4]
 * Output : [9,4] (order does not matter)
 *
 * Constraints:
 * - 1 <= nums1.length, nums2.length <= 1000
 * - 0 <= nums1[i], nums2[i] <= 1000
 *
 * Approaches Implemented:
 * 1. Optimal HashSet Approach (O(n+m), O(n+m))
 * 2. Brute Force Approach (O(n*m), O(min(n,m)))
 */
public class IntersectionOfTwoArrays {

    /**
     * Algorithm 1: Optimal HashSet Approach
     * -------------------------------------
     * Idea:
     * - Store nums1 elements in a HashSet.
     * - Iterate nums2, check membership in set.
     * - Add to result set to ensure uniqueness.
     *
     * Time Complexity: O(n + m)
     * Space Complexity: O(n + m)
     * Concept: Hashing for fast lookup.
     */
    public int[] intersectionOptimal(int[] nums1, int[] nums2) {
        Set<Integer> set1 = new HashSet<>();
        for (int num : nums1) set1.add(num);

        Set<Integer> resultSet = new HashSet<>();
        for (int num : nums2) {
            if (set1.contains(num)) {
                resultSet.add(num);
            }
        }

        int[] result = new int[resultSet.size()];
        int idx = 0;
        for (int num : resultSet) result[idx++] = num;
        return result;
    }

    /**
     * Algorithm 2: Brute Force Approach
     * ---------------------------------
     * Idea:
     * - For each element in nums1, scan nums2.
     * - If found, add to result list if not already present.
     *
     * Time Complexity: O(n * m)
     * Space Complexity: O(min(n, m)) (for result storage)
     * Concept: Simple nested loops.
     */
    public int[] intersectionBrute(int[] nums1, int[] nums2) {
        List<Integer> resultList = new ArrayList<>();
        for (int num1 : nums1) {
            for (int num2 : nums2) {
                if (num1 == num2 && !resultList.contains(num1)) {
                    resultList.add(num1);
                }
            }
        }
        int[] result = new int[resultList.size()];
        for (int i = 0; i < resultList.size(); i++) {
            result[i] = resultList.get(i);
        }
        return result;
    }
}
