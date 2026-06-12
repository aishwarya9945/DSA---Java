package ArraysAndHashing;

/**
 * Program to rotate an array to the right by k steps.
 * Implements:
 * 1. Optimal Reverse Approach (O(n))
 * 2. Brute Force Approach (O(n * k))
 *
 * Problem:
 * Given an integer array nums, rotate the array to the right by k steps, where k is non-negative.
 *
 * Example:
 * Input  : nums = [1,2,3,4,5,6,7], k = 3
 * Output : [5,6,7,1,2,3,4]
 * Explanation: Rotate right by 3 → [5,6,7,1,2,3,4].
 */
public class RotateArray {

    public static void main(String[] args) {
        int nums[] = {1, 2, 3, 4, 5, 6, 7};
        int k = 3;

        // ---------- Optimal Approach ----------
        int[] numsCopy1 = nums.clone();
        rotateOptimal(numsCopy1, k);
        System.out.print("Rotate Array (Optimal): ");
        printArray(numsCopy1);

        // ---------- Brute Force Approach ----------
        int[] numsCopy2 = nums.clone();
        rotateBrute(numsCopy2, k);
        System.out.print("Rotate Array (Brute Force): ");
        printArray(numsCopy2);
    }

    /**
     * Algorithm 1: Optimal Reverse Approach
     * -------------------------------------
     * Idea:
     * Reverse the array in three steps:
     * 1. Reverse the entire array.
     * 2. Reverse the first k elements.
     * 3. Reverse the remaining n - k elements.
     *
     * Steps:
     * - Normalize k = k % n.
     * - Apply reverse operations.
     *
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     * Concept: Array reversal trick.
     */
    public static void rotateOptimal(int[] nums, int k) {
        int n = nums.length;
        k = k % n;

        reverse(nums, 0, n - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, n - 1);
    }

    private static void reverse(int[] nums, int left, int right) {
        while (left < right) {
            int temp = nums[left];
            nums[left] = nums[right];
            nums[right] = temp;
            left++;
            right--;
        }
    }

    /**
     * Algorithm 2: Brute Force Approach
     * ---------------------------------
     * Idea:
     * Rotate one step at a time, repeat k times.
     *
     * Steps:
     * - For each rotation:
     *   - Store last element.
     *   - Shift all elements right by one.
     *   - Place last element at index 0.
     *
     * Time Complexity: O(n * k)
     * Space Complexity: O(1)
     * Concept: Simulation of rotation.
     */
    public static void rotateBrute(int[] nums, int k) {
        int n = nums.length;
        k = k % n;

        for (int i = 0; i < k; i++) {
            int last = nums[n - 1];
            for (int j = n - 1; j > 0; j--) {
                nums[j] = nums[j - 1];
            }
            nums[0] = last;
        }
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
