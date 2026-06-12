package ArraysAndHashing;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

/**
 * Program to merge overlapping intervals.
 * Implements:
 * 1. Optimal Sorting + Merge Approach (O(n log n))
 * 2. Brute Force Approach (O(n^2))
 *
 * Problem:
 * Given an array of intervals where intervals[i] = [start, end],
 * merge all overlapping intervals and return an array of non-overlapping intervals.
 *
 * Example:
 * Input  : [[1,3],[2,6],[8,10],[15,18]]
 * Output : [[1,6],[8,10],[15,18]]
 */
public class MergeIntervals {

    public static void main(String[] args) {
        int[][] intervals = {{1,3},{2,6},{8,10},{15,18}};

        // ---------- Optimal Approach ----------
        int[][] optimalResult = mergeOptimal(intervals);
        System.out.println("Merged Intervals (Optimal): " + Arrays.deepToString(optimalResult));

        // ---------- Brute Force Approach ----------
        int[][] bruteResult = mergeBrute(intervals);
        System.out.println("Merged Intervals (Brute Force): " + Arrays.deepToString(bruteResult));
    }

    /**
     * Algorithm 1: Optimal Sorting + Merge Approach
     * ---------------------------------------------
     * Idea:
     * Sort intervals by start time. Then iterate and merge overlapping intervals.
     *
     * Steps:
     * 1. Sort intervals by start.
     * 2. Initialize result list.
     * 3. For each interval:
     *      - If result is empty or current interval does not overlap with last → add it.
     *      - Else → merge with last interval (update end).
     * 4. Return result list as array.
     *
     * Time Complexity: O(n log n) (due to sorting)
     * Space Complexity: O(n)
     */
    public static int[][] mergeOptimal(int[][] intervals) {
        if (intervals.length == 0) return new int[0][];

        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));
        List<int[]> result = new ArrayList<>();

        int[] current = intervals[0];
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] <= current[1]) {
                current[1] = Math.max(current[1], intervals[i][1]); // merge
            } else {
                result.add(current);
                current = intervals[i];
            }
        }
        result.add(current);

        return result.toArray(new int[result.size()][]);
    }

    /**
     * Algorithm 2: Brute Force Approach
     * ---------------------------------
     * Idea:
     * Compare every interval with every other interval and merge if overlapping.
     *
     * Steps:
     * 1. For each interval, check overlap with others.
     * 2. Merge overlapping intervals.
     * 3. Mark merged intervals to avoid duplication.
     *
     * Time Complexity: O(n^2)
     * Space Complexity: O(n)
     */
    public static int[][] mergeBrute(int[][] intervals) {
        boolean[] merged = new boolean[intervals.length];
        List<int[]> result = new ArrayList<>();

        for (int i = 0; i < intervals.length; i++) {
            if (merged[i]) continue;
            int start = intervals[i][0];
            int end = intervals[i][1];

            for (int j = i + 1; j < intervals.length; j++) {
                if (!merged[j] && intervals[j][0] <= end && intervals[j][1] >= start) {
                    start = Math.min(start, intervals[j][0]);
                    end = Math.max(end, intervals[j][1]);
                    merged[j] = true;
                }
            }
            result.add(new int[]{start, end});
        }

        return result.toArray(new int[result.size()][]);
    }
}
