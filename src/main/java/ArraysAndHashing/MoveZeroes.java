package ArraysAndHashing;

/**
 * Program to move all zeroes in an array to the end while maintaining the relative order of non-zero elements.
 * Implements:
 * 1. Optimal Two-Pointer Approach (O(n))
 * 2. Brute Force Extra Array Approach (O(n))
 *
 * Problem:
 * Given an integer array nums, move all 0's to the end of it while maintaining the relative order of the non-zero elements.
 *
 * Example:
 * Input  : [0,1,0,3,12]
 * Output : [1,3,12,0,0]
 * Explanation: Non-zero elements stay in order, zeros shifted to end.
 */
public class MoveZeroes {

    public static void main(String[] args) {
        int nums[] = {0, 1, 0, 3, 12};

        // ---------- Optimal Approach ----------
        int[] numsCopy1 = nums.clone();
        moveZeroesOptimal(numsCopy1);
        System.out.print("Move Zeroes (Optimal): ");
        printArray(numsCopy1);

        // ---------- Brute Force Approach ----------
        int[] numsCopy2 = nums.clone();
        int[] bruteResult = moveZeroesBrute(numsCopy2);
        System.out.print("Move Zeroes (Brute Force): ");
        printArray(bruteResult);
    }

    /**
     * Algorithm 1: Optimal Two-Pointer Approach
     * -----------------------------------------
     * Idea:
     * Use a pointer to track the position of the next non-zero element.
     * Swap non-zero elements forward, then fill remaining positions with zero.
     *
     * Steps:
     * 1. Initialize index = 0.
     * 2. Traverse array:
     *      - If nums[i] != 0 → place at nums[index], increment index.
     * 3. Fill remaining positions with 0.
     *
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     * Concept: In-place two-pointer shifting.
     */
    public static void moveZeroesOptimal(int[] nums) {
        int index = 0;
        for (int num : nums) {
            if (num != 0) {
                nums[index++] = num;
            }
        }
        while (index < nums.length) {
            nums[index++] = 0;
        }
    }

    /**
     * Algorithm 2: Brute Force Extra Array Approach
     * ---------------------------------------------
     * Idea:
     * Create a new array, copy non-zero elements, then append zeros.
     *
     * Steps:
     * 1. Traverse nums, collect non-zero elements.
     * 2. Fill remaining positions with 0.
     *
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     * Concept: Extra array for simplicity.
     */
    public static int[] moveZeroesBrute(int[] nums) {
        int[] result = new int[nums.length];
        int index = 0;

        for (int num : nums) {
            if (num != 0) {
                result[index++] = num;
            }
        }
        // Remaining positions are already 0 by default
        return result;
    }

    // Utility method to print array
    private static void printArray(int[] nums) {
        System.out.print("[");
        for (int i = 0; i < nums.length; i++) {
            System.out.print(nums[i]);
            if (i < nums.length - 1) System.out.print(", ");
        }
        System.out.println("]");
    }
}
