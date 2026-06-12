package ArraysAndHashing;

import java.util.Arrays;

/**
 * Program to set entire row and column to zero if an element in the matrix is zero.
 * Implements:
 * 1. Optimal In-place Approach using markers (O(m*n), O(1) extra space)
 * 2. Brute Force Approach using auxiliary arrays (O(m*n), O(m+n) space)
 *
 * Problem:
 * Given an m x n matrix, if an element is 0, set its entire row and column to 0.
 *
 * Example:
 * Input  : [[1,1,1],[1,0,1],[1,1,1]]
 * Output : [[1,0,1],[0,0,0],[1,0,1]]
 */
public class SetMatrixZeroes {

    public static void main(String[] args) {
        int[][] matrix = {
                {1, 1, 1},
                {1, 0, 1},
                {1, 1, 1}
        };

        // ---------- Optimal Approach ----------
        int[][] matrixOptimal = deepCopy(matrix);
        setZeroesOptimal(matrixOptimal);
        System.out.println("Matrix after setting zeroes (Optimal): " + Arrays.deepToString(matrixOptimal));

        // ---------- Brute Force Approach ----------
        int[][] matrixBrute = deepCopy(matrix);
        setZeroesBrute(matrixBrute);
        System.out.println("Matrix after setting zeroes (Brute Force): " + Arrays.deepToString(matrixBrute));
    }

    /**
     * Algorithm 1: Optimal In-place Approach
     * --------------------------------------
     * Idea:
     * Use the first row and first column as markers to indicate which rows/columns should be zeroed.
     *
     * Steps:
     * 1. Check if first row and first column need to be zeroed (store flags).
     * 2. Traverse matrix:
     *      - If matrix[i][j] == 0 → mark matrix[i][0] and matrix[0][j] = 0.
     * 3. Traverse again using markers to set zeroes.
     * 4. Finally, zero out first row/column if needed.
     *
     * Time Complexity: O(m*n)
     * Space Complexity: O(1)
     */
    public static void setZeroesOptimal(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        boolean firstRowZero = false, firstColZero = false;

        // Step 1: Check first row
        for (int j = 0; j < n; j++) {
            if (matrix[0][j] == 0) {
                firstRowZero = true;
                break;
            }
        }

        // Step 2: Check first column
        for (int i = 0; i < m; i++) {
            if (matrix[i][0] == 0) {
                firstColZero = true;
                break;
            }
        }

        // Step 3: Use first row/col as markers
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (matrix[i][j] == 0) {
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                }
            }
        }

        // Step 4: Apply markers
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0;
                }
            }
        }

        // Step 5: Zero out first row/col if needed
        if (firstRowZero) {
            for (int j = 0; j < n; j++) matrix[0][j] = 0;
        }
        if (firstColZero) {
            for (int i = 0; i < m; i++) matrix[i][0] = 0;
        }
    }

    /**
     * Algorithm 2: Brute Force Approach
     * ---------------------------------
     * Idea:
     * Use two auxiliary arrays to track which rows and columns should be zeroed.
     *
     * Steps:
     * 1. Create row[] and col[] arrays.
     * 2. Traverse matrix:
     *      - If matrix[i][j] == 0 → mark row[i] and col[j].
     * 3. Traverse again:
     *      - If row[i] == 0 or col[j] == 0 → set matrix[i][j] = 0.
     *
     * Time Complexity: O(m*n)
     * Space Complexity: O(m+n)
     */
    public static void setZeroesBrute(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        boolean[] row = new boolean[m];
        boolean[] col = new boolean[n];

        // Step 1: Mark rows and columns
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 0) {
                    row[i] = true;
                    col[j] = true;
                }
            }
        }

        // Step 2: Apply marks
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (row[i] || col[j]) {
                    matrix[i][j] = 0;
                }
            }
        }
    }

    // Utility: Deep copy matrix for demonstration
    private static int[][] deepCopy(int[][] matrix) {
        int[][] copy = new int[matrix.length][matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            copy[i] = Arrays.copyOf(matrix[i], matrix[i].length);
        }
        return copy;
    }
}
