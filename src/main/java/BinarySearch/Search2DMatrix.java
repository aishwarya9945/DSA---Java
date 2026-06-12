package BinarySearch;

import java.util.Arrays;

/**
 * Program to search for a target value in a 2D matrix.
 * Implements:
 * 1. Optimal Binary Search Approach (O(log(m*n)))
 * 2. Brute Force Linear Search Approach (O(m*n))
 *
 * Problem:
 * Write an efficient algorithm that searches for a value in an m x n matrix.
 * The matrix has the following properties:
 *  - Integers in each row are sorted from left to right.
 *  - The first integer of each row is greater than the last integer of the previous row.
 *
 * Example:
 * Input  : matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 3
 * Output : true
 *
 * Input  : matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 13
 * Output : false
 */
public class Search2DMatrix {

    public static void main(String[] args) {
        int[][] matrix = {
                {1, 3, 5, 7},
                {10, 11, 16, 20},
                {23, 30, 34, 60}
        };
        int target = 3;

        // ---------- Optimal Approach ----------
        boolean optimalResult = searchMatrixOptimal(matrix, target);
        System.out.println("Target Found (Optimal): " + optimalResult);

        // ---------- Brute Force Approach ----------
        boolean bruteResult = searchMatrixBrute(matrix, target);
        System.out.println("Target Found (Brute Force): " + bruteResult);
    }

    /**
     * Algorithm 1: Optimal Binary Search Approach
     * -------------------------------------------
     * Idea:
     * Treat the 2D matrix as a flattened sorted array of length m*n.
     * Apply binary search by mapping 1D index back to 2D coordinates.
     *
     * Steps:
     * 1. Set left = 0, right = m*n - 1.
     * 2. While left <= right:
     *      - mid = (left + right) / 2
     *      - row = mid / n, col = mid % n
     *      - Compare matrix[row][col] with target.
     * 3. Return true if found, else false.
     *
     * Time Complexity: O(log(m*n))
     * Space Complexity: O(1)
     */
    public static boolean searchMatrixOptimal(int[][] matrix, int target) {
        int m = matrix.length, n = matrix[0].length;
        int left = 0, right = m * n - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            int row = mid / n;
            int col = mid % n;

            if (matrix[row][col] == target) {
                return true;
            } else if (matrix[row][col] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return false;
    }

    /**
     * Algorithm 2: Brute Force Linear Search
     * --------------------------------------
     * Idea:
     * Scan through each element of the matrix until target is found.
     *
     * Steps:
     * 1. Traverse each row and column.
     * 2. If matrix[i][j] == target → return true.
     * 3. If not found → return false.
     *
     * Time Complexity: O(m*n)
     * Space Complexity: O(1)
     */
    public static boolean searchMatrixBrute(int[][] matrix, int target) {
        for (int[] row : matrix) {
            for (int val : row) {
                if (val == target) return true;
            }
        }
        return false;
    }
}
