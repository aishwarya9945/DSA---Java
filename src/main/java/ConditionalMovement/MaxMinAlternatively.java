package ConditionalMovement;

/**
 * This class demonstrates rearranging an array alternately with maximum and minimum elements.
 * It provides both an optimized two-pointer approach and a brute force-like approach.
 */
public class MaxMinAlternatively {

    public static void main(String[] args) {
        // Sample input: a sorted array of integers
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8};

        // ==============================
        // Optimized Two-Pointer Approach
        // ==============================
        // Clone the original array to avoid modifying it during demonstration
        int[] arrOptimized = arr.clone();

        // Two pointers - one starting at beginning (smallest elements)
        int start = 0;
        // Another at the end (largest elements)
        int end = arrOptimized.length - 1;

        // Temporary array to hold the rearranged elements
        int[] result = new int[arrOptimized.length];

        // Flag to alternate between picking max and min elements
        boolean pickMax = true;

        // Iterate over the length of the array
        for (int i = 0; i < arrOptimized.length; i++) {
            if (pickMax) {
                // Pick the current maximum element from the right end
                result[i] = arrOptimized[end--];
            } else {
                // Pick the current minimum element from the left end
                result[i] = arrOptimized[start++];
            }
            // Flip the flag to alternate for the next iteration
            pickMax = !pickMax;
        }

        // Copy the rearranged result back into the original array clone
        System.arraycopy(result, 0, arrOptimized, 0, arrOptimized.length);

        // Display the array after rearrangement
        System.out.println("Rearranged array (Optimized): " + java.util.Arrays.toString(arrOptimized));

        // ==============================
        // Brute Force-Like Approach
        // ==============================
        // Clone original array for brute force demonstration
        int[] arrBrute = arr.clone();

        // Sort the array in ascending order to prepare for min/max merging
        java.util.Arrays.sort(arrBrute);

        // Calculate middle index to split smaller and larger halves
        int mid = (arrBrute.length + 1) / 2;

        // Pointers for left half (minimums) and right half (maximums)
        int leftIndex = 0;
        int rightIndex = arrBrute.length - 1;

        // Result array for merged output
        int[] bruteResult = new int[arrBrute.length];

        // Flag to decide whether to pick max or min element next
        boolean maxTurn = true;

        // Index for filling the result array
        int idx = 0;

        /*
         * Alternately pick elements from the right half (max values)
         * and left half (min values) until one half is fully consumed
         */
        while (leftIndex < mid && rightIndex >= mid) {
            if (maxTurn) {
                // Pick max element from right half
                bruteResult[idx++] = arrBrute[rightIndex--];
            } else {
                // Pick min element from left half
                bruteResult[idx++] = arrBrute[leftIndex++];
            }
            // Flip flag to alternate picks
            maxTurn = !maxTurn;
        }

        /*
         * Append the remaining elements from left half if any
         * This can happen if array length is odd
         */
        while (leftIndex < mid) {
            bruteResult[idx++] = arrBrute[leftIndex++];
        }

        /*
         * Append the remaining elements from right half if any
         */
        while (rightIndex >= mid) {
            bruteResult[idx++] = arrBrute[rightIndex--];
        }

        // Print the brute force-like rearranged array
        System.out.println("Rearranged array (Brute Force-like): " + java.util.Arrays.toString(bruteResult));
    }
}

/*
Concepts Demonstrated:

- Optimized Two-Pointer Approach:
  - Uses two pointers at either end.
  - Picks max and min elements alternately.
  - Time Complexity: O(n)
  - Space Complexity: O(n) (due to auxiliary array)

- Brute Force-Like Approach:
  - Sorts array ascending.
  - Splits into min and max halves.
  - Alternately merges these halves.
  - More complex and less intuitive than the optimized approach.
  - Time Complexity: O(n log n)
  - Space Complexity: O(n)
*/
