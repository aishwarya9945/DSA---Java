package ArraysAndHashing;

/**
 * Program to calculate the total amount of rainwater trapped between bars.
 * Implements:
 * 1. Optimal Two-Pointer Approach (O(n), O(1) space)
 * 2. Prefix-Suffix Array Approach (O(n), O(n) space)
 * 3. Brute Force Approach (O(n^2))
 *
 * Problem:
 * Given n non-negative integers representing an elevation map where the width of each bar is 1,
 * compute how much water it can trap after raining.
 *
 * Example:
 * Input  : [0,1,0,2,1,0,1,3,2,1,2,1]
 * Output : 6
 */
public class TrappingRainWater {

    public static void main(String[] args) {
        int height[] = {0,1,0,2,1,0,1,3,2,1,2,1};

        // ---------- Optimal Approach ----------
        int optimalWater = trapOptimal(height);
        System.out.println("Water Trapped (Optimal): " + optimalWater);

        // ---------- Prefix-Suffix Approach ----------
        int prefixWater = trapPrefixSuffix(height);
        System.out.println("Water Trapped (Prefix-Suffix): " + prefixWater);

        // ---------- Brute Force Approach ----------
        int bruteWater = trapBrute(height);
        System.out.println("Water Trapped (Brute Force): " + bruteWater);
    }

    /**
     * Algorithm 1: Optimal Two-Pointer Approach
     * -----------------------------------------
     * Idea:
     * Water trapped depends on the shorter boundary.
     * Use two pointers (left, right) and track leftMax and rightMax.
     *
     * Steps:
     * 1. Initialize left = 0, right = n-1, leftMax = 0, rightMax = 0.
     * 2. While left < right:
     *      - If height[left] < height[right]:
     *          * If height[left] >= leftMax → update leftMax.
     *          * Else → add (leftMax - height[left]) to water.
     *          * Move left++.
     *      - Else:
     *          * If height[right] >= rightMax → update rightMax.
     *          * Else → add (rightMax - height[right]) to water.
     *          * Move right--.
     * 3. Return total water.
     *
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     */
    public static int trapOptimal(int[] height) {
        int left = 0, right = height.length - 1;
        int leftMax = 0, rightMax = 0;
        int water = 0;

        while (left < right) {
            if (height[left] < height[right]) {
                if (height[left] >= leftMax) {
                    leftMax = height[left];
                } else {
                    water += leftMax - height[left];
                }
                left++;
            } else {
                if (height[right] >= rightMax) {
                    rightMax = height[right];
                } else {
                    water += rightMax - height[right];
                }
                right--;
            }
        }
        return water;
    }

    /**
     * Algorithm 2: Prefix-Suffix Array Approach
     * -----------------------------------------
     * Idea:
     * Precompute max height to the left and right for each index.
     * Water at index = min(leftMax[i], rightMax[i]) - height[i].
     *
     * Steps:
     * 1. Build leftMax[] and rightMax[] arrays.
     * 2. Traverse array and calculate trapped water.
     *
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     */
    public static int trapPrefixSuffix(int[] height) {
        int n = height.length;
        if (n == 0) return 0;

        int[] leftMax = new int[n];
        int[] rightMax = new int[n];

        leftMax[0] = height[0];
        for (int i = 1; i < n; i++) {
            leftMax[i] = Math.max(leftMax[i - 1], height[i]);
        }

        rightMax[n - 1] = height[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            rightMax[i] = Math.max(rightMax[i + 1], height[i]);
        }

        int water = 0;
        for (int i = 0; i < n; i++) {
            water += Math.min(leftMax[i], rightMax[i]) - height[i];
        }

        return water;
    }

    /**
     * Algorithm 3: Brute Force Approach
     * ---------------------------------
     * Idea:
     * For each index, find max height to its left and right.
     * Water at index = min(leftMax, rightMax) - height[i].
     *
     * Steps:
     * 1. For each index i:
     *      - Find leftMax by scanning [0..i].
     *      - Find rightMax by scanning [i..n-1].
     *      - Add min(leftMax, rightMax) - height[i] to water.
     *
     * Time Complexity: O(n^2)
     * Space Complexity: O(1)
     */
    public static int trapBrute(int[] height) {
        int n = height.length;
        int water = 0;

        for (int i = 0; i < n; i++) {
            int leftMax = 0, rightMax = 0;

            for (int j = 0; j <= i; j++) {
                leftMax = Math.max(leftMax, height[j]);
            }
            for (int j = i; j < n; j++) {
                rightMax = Math.max(rightMax, height[j]);
            }

            water += Math.min(leftMax, rightMax) - height[i];
        }

        return water;
    }
}
