package ArraysAndHashing;

import java.util.Arrays;

/**
 * Program to solve the Container With Most Water problem using:
 * 1. Optimal Two-Pointer Approach
 * 2. Brute Force Approach
 *
 * Problem:
 * Given n non-negative integers where each represents a vertical line on the x-axis,
 * find two lines that together with the x-axis form a container that holds the most water.
 *
 * Example:
 * Input:  height = [1,8,6,2,5,4,8,3,7]
 * Output: 49
 *
 * Concept:
 * - Each pair of lines forms a container, and the water it can hold depends on:
 *      → The shorter line (height).
 *      → The distance between the two lines (width).
 * - Brute force checks all pairs.
 * - Optimal solution uses the **two-pointer technique**:
 *      → Start with left and right pointers at the ends.
 *      → Compute area = minHeight * width.
 *      → Move the pointer at the shorter line inward to try for a taller line.
 */
public class ContainerWithMostWater {

    public static void main(String[] args) {
        int[] height = {1,8,6,2,5,4,8,3,7};  // Input array

        // ---------- Optimal Two-Pointer Approach ----------
        int optimalResult = maxAreaOptimal(height);
        System.out.println("Container With Most Water (Optimal Two-Pointer): " + optimalResult);

        // ---------- Brute Force Approach ----------
        int bruteResult = maxAreaBrute(height);
        System.out.println("Container With Most Water (Brute Force): " + bruteResult);
    }

    /**
     * Algorithm 1: Optimal Approach — Two Pointers
     * --------------------------------------------
     * Steps:
     * 1. Initialize two pointers: left = 0, right = n-1.
     * 2. While left < right:
     *      - width = right - left
     *      - minHeight = min(height[left], height[right])
     *      - currentArea = width * minHeight
     *      - Update maxArea if currentArea is larger.
     *      - Move the pointer at the shorter line inward.
     * 3. Return maxArea.
     *
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     * Concept: Two-pointer technique to maximize area.
     */
    public static int maxAreaOptimal(int[] height) {
        int left = 0, right = height.length - 1;
        int maxArea = 0;

        while (left < right) {
            int width = right - left;
            int minHeight = Math.min(height[left], height[right]);
            int currentArea = width * minHeight;

            maxArea = Math.max(maxArea, currentArea);

            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }

        return maxArea;
    }

    /**
     * Algorithm 2: Brute Force Approach
     * ---------------------------------
     * Steps:
     * 1. Loop i from 0 to n-2.
     * 2. Loop j from i+1 to n-1.
     * 3. width = j - i
     * 4. minHeight = min(height[i], height[j])
     * 5. currentArea = width * minHeight
     * 6. Update maxArea if currentArea is larger.
     * 7. Return maxArea.
     *
     * Time Complexity: O(n²)
     * Space Complexity: O(1)
     * Concept: Check all possible pairs — brute force.
     */
    public static int maxAreaBrute(int[] height) {
        int maxArea = 0;

        for (int i = 0; i < height.length; i++) {
            for (int j = i + 1; j < height.length; j++) {
                int width = j - i;
                int minHeight = Math.min(height[i], height[j]);
                int currentArea = width * minHeight;

                maxArea = Math.max(maxArea, currentArea);
            }
        }

        return maxArea;
    }
}
