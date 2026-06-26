package Greedy;

import java.util.*;

/**
 * Problem: 881. Boats to Save People
 * ----------------------------------
 * You are given an array people where people[i] is the weight of the i-th person,
 * and an infinite number of boats where each boat can carry a maximum weight limit.
 * Each boat carries at most two people at the same time, provided the sum of their
 * weights is at most limit.
 *
 * Return the minimum number of boats to carry every given person.
 *
 * Example 1:
 * Input  : people = [1,2], limit = 3
 * Output : 1
 *
 * Example 2:
 * Input  : people = [3,2,2,1], limit = 3
 * Output : 3
 *
 * Example 3:
 * Input  : people = [3,5,3,4], limit = 5
 * Output : 4
 *
 * Constraints:
 * - 1 <= people.length <= 5 * 10^4
 * - 1 <= people[i] <= limit <= 3 * 10^4
 *
 * Approaches Implemented:
 * 1. Optimal Greedy Two-Pointer Approach (O(n log n), O(1))
 * 2. Brute Force Approach (O(n^2), O(n))
 */
public class BoatsToSavePeople {

    /**
     * Algorithm 1: Optimal Greedy Two-Pointer
     * ---------------------------------------
     * Idea:
     * - Sort the array of weights.
     * - Use two pointers: lightest (left) and heaviest (right).
     * - If they can share a boat (sum <= limit), move both pointers.
     * - Otherwise, put the heavier person alone (move right).
     * - Increment boat count each time.
     *
     * Time Complexity: O(n log n) (due to sorting)
     * Space Complexity: O(1)
     * Concept: Greedy pairing of lightest + heaviest.
     */
    public int numRescueBoatsOptimal(int[] people, int limit) {
        Arrays.sort(people);
        int left = 0, right = people.length - 1;
        int boats = 0;

        while (left <= right) {
            if (people[left] + people[right] <= limit) {
                left++;
                right--;
            } else {
                right--;
            }
            boats++;
        }
        return boats;
    }

    /**
     * Algorithm 2: Brute Force Approach
     * ---------------------------------
     * Idea:
     * - Try to pair each person with another person.
     * - Mark them as used once placed in a boat.
     * - Count boats until all are assigned.
     *
     * Steps:
     * - Use a visited[] array to track who is already placed.
     * - For each person, try to find a partner <= limit - weight.
     * - If found, mark both visited.
     * - Otherwise, assign boat to single person.
     *
     * Time Complexity: O(n^2)
     * Space Complexity: O(n)
     * Concept: Naive pairing check.
     */
    public int numRescueBoatsBrute(int[] people, int limit) {
        int n = people.length;
        boolean[] visited = new boolean[n];
        Arrays.sort(people);
        int boats = 0;

        for (int i = n - 1; i >= 0; i--) {
            if (visited[i]) continue;
            visited[i] = true;
            boats++;
            for (int j = i - 1; j >= 0; j--) {
                if (!visited[j] && people[i] + people[j] <= limit) {
                    visited[j] = true;
                    break;
                }
            }
        }
        return boats;
    }
}
