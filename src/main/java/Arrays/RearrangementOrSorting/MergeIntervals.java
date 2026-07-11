package Arrays.RearrangementOrSorting;

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
     * Merge Intervals - Optimal Approach - Sorting + Merge Approach
     * ----------------------------------
     * Mnemonic: "Sort → Sweep → Merge"
     *
     * Steps:
     * 1. Sort intervals by start time.
     * 2. Initialize a 'current' interval.
     * 3. For each next interval:
     *      - If overlap (next.start <= current.end) → merge by updating current.end.
     *      - Else → add current to result and reset current = next.
     * 4. After loop, add the last current interval.
     *
     * Time Complexity: O(n log n) due to sorting
     * Space Complexity: O(n) for result list

     * Purpose
     * If the input array intervals is empty (length == 0), there are no intervals to merge.
     * Instead of continuing with sorting and accessing intervals[0]
     (which would throw an ArrayIndexOutOfBoundsException), the method immediately returns an empty 2D array.

     * 🔹 Why new int[0][]
     * new int[0][] creates a 2D int array with zero rows.

     * This matches the expected return type int[][].

     * It’s the conventional way in Java to return “no intervals” while still respecting the method signature.
     */
    public static int[][] mergeOptimal(int[][] intervals) {
        if (intervals.length == 0) return new int[0][];

        // Step 1: Sort by start time
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));

        List<int[]> result = new ArrayList<>();
        int[] current = intervals[0]; // Step 2: initialize

        // Step 3: Sweep through intervals
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] <= current[1]) {
                // Overlap → merge
                current[1] = Math.max(current[1], intervals[i][1]);
            } else {
                // No overlap → add current and reset
                result.add(current);
                current = intervals[i];
            }
        }

        // Step 4: Add the last interval
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
