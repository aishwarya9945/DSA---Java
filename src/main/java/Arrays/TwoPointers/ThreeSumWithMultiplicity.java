package Arrays.TwoPointers;

import java.util.*;

/**
 * Problem: 923. 3Sum With Multiplicity
 * ------------------------------------
 * Given an integer array arr, and an integer target,
 * return the number of tuples (i, j, k) such that:
 *   - i < j < k
 *   - arr[i] + arr[j] + arr[k] == target
 *
 * Since the answer can be very large, return it modulo 1e9+7.
 *
 * Example 1:
 * Input  : arr = [1,1,2,2,3,3,4,4,5,5], target = 8
 * Output : 20
 *
 * Example 2:
 * Input  : arr = [2,1,3], target = 6
 * Output : 1
 *
 * Constraints:
 * - 3 <= arr.length <= 3000
 * - 0 <= arr[i] <= 100
 * - 0 <= target <= 300
 *
 * Approaches Implemented:
 * 1. Optimal Counting + Two Pointers Approach (O(n^2), O(1))
 * 2. Brute Force Approach (O(n^3), O(1))
 */
public class ThreeSumWithMultiplicity {

    private static final int MOD = 1_000_000_007;

    /**
     * 1_000_000_007 (which is 10^9 + 7)
     * is not a Java default constant — it’s a commonly used prime modulus in competitive programming and algorithm problems.
     *
     * This line defines a constant MOD that we use to take results modulo 10^9+7.
     * It’s user‑defined, not built into Java. You could also write:
     * private static final int MOD = (int)1e9 + 7;

     * Algorithm 1: Optimal Counting + Two Pointers
     * --------------------------------------------
     * Idea:
     * - Sort the array.
     * - Fix one element arr[i].
     * - Use two pointers (left, right) to find pairs such that
     *   arr[i] + arr[left] + arr[right] == target.
     * - Handle duplicates carefully:
     *   - If arr[left] != arr[right], count combinations by frequency.
     *   - If arr[left] == arr[right], use nC2 formula.
     *
     * Steps:
     * 1. Sort arr.
     * 2. For each i:
     *    - Set T = target - arr[i].
     *    - Use two pointers left=i+1, right=n-1.
     *    - While left < right:
     *      - If sum < T → left++
     *      - If sum > T → right--
     *      - If sum == T → count combinations.
     *
     * Time Complexity: O(n^2)
     * Space Complexity: O(1)
     * Concept: Sorting + Two Pointers + Combinatorics.
     */
    public int threeSumMultiOptimal(int[] arr, int target) {
        Arrays.sort(arr);
        long count = 0;

        for (int i = 0; i < arr.length; i++) {
            int T = target - arr[i];
            int left = i + 1, right = arr.length - 1;

            while (left < right) {
                int sum = arr[left] + arr[right];
                if (sum < T) {
                    left++;
                } else if (sum > T) {
                    right--;
                } else { // sum == T
                    //Case 1: arr[left] != arr[right]
                    //We need to count how many duplicates are at left and how many at right.
                    //“left group × right group”.
                    //Each duplicate on the left can pair with each duplicate on the right.
                    if (arr[left] != arr[right]) {
                        int lCount = 1, rCount = 1;
                        while (left + 1 < right && arr[left] == arr[left + 1]) {
                            lCount++;
                            left++;
                        }
                        while (right - 1 > left && arr[right] == arr[right - 1]) {
                            rCount++;
                            right--;
                        }
                        count += (long) lCount * rCount;

                        count %= MOD;// keeps the result within bounds.

                        left++;
                        right--;
                        // Case 2: arr[left] == arr[right]
                        //All numbers between left and right are the same.
                        //Number of ways = choose any 2 out of n → nC2 = n*(n-1)/2.
                    } else {
                        int n = right - left + 1;
                        count += (long) n * (n - 1) / 2;

                        count %= MOD;// keeps the result within bounds.
                        break;
                    }
                }
            }
        }
        return (int) count;
    }

    /**
     * Algorithm 2: Brute Force Approach
     * ---------------------------------
     * Idea:
     * - Try all triplets (i, j, k).
     * - Count if sum == target.
     *
     * Steps:
     * - Loop i from 0 → n-3
     * - Loop j from i+1 → n-2
     * - Loop k from j+1 → n-1
     * - If arr[i] + arr[j] + arr[k] == target → increment count
     *
     * Time Complexity: O(n^3)
     * Space Complexity: O(1)
     * Concept: Simple enumeration.
     */
    public int threeSumMultiBrute(int[] arr, int target) {
        int n = arr.length;
        long count = 0;

        for (int i = 0; i < n - 2; i++) {
            for (int j = i + 1; j < n - 1; j++) {
                for (int k = j + 1; k < n; k++) {
                    if (arr[i] + arr[j] + arr[k] == target) {
                        count++;
                        count %= MOD;
                    }
                }
            }
        }
        return (int) count;
    }
}
