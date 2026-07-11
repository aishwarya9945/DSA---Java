package Arrays.Basics;

/**
 * Problem: Reverse an Array
 * -------------------------
 * You are given an array of integers arr[].
 * Reverse the given array in place.
 *
 * Example 1:
 * Input  : arr = [1, 4, 3, 2, 6, 5]
 * Output : [5, 6, 2, 3, 4, 1]
 *
 * Example 2:
 * Input  : arr = [4, 5, 2]
 * Output : [2, 5, 4]
 *
 * Example 3:
 * Input  : arr = [1]
 * Output : [1]
 *
 * Approaches Implemented:
 * 1. Optimal Two-Pointer Approach (O(n), O(1))
 * 2. Brute Force Approach using extra array (O(n), O(n))
 */
public class ReverseArray {

    public static void main(String[] args) {
        int[] arr = {1, 4, 3, 2, 6, 5};

        // ---------- Optimal Approach ----------
        int[] arrCopy1 = arr.clone();
        reverseOptimal(arrCopy1);
        System.out.print("Reverse Array (Optimal): ");
        printArray(arrCopy1);

        // ---------- Brute Force Approach ----------
        int[] arrCopy2 = arr.clone();
        reverseBrute(arrCopy2);
        System.out.print("Reverse Array (Brute Force): ");
        printArray(arrCopy2);
    }

    /**
     * Algorithm 1: Optimal Two-Pointer Approach
     * -----------------------------------------
     * Idea:
     * Use two pointers (left and right) and swap elements until they meet.
     *
     * Steps:
     * - Initialize left = 0, right = n-1.
     * - While left < right:
     *   - Swap arr[left] and arr[right].
     *   - Move left++, right--.
     *
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     * Concept: In-place reversal using two pointers.
     */
    public static void reverseOptimal(int[] arr) {
        int left = 0, right = arr.length - 1;
        while (left < right) {
            int temp = arr[left];
            arr[left] = arr[right];
            arr[right] = temp;
            left++;
            right--;
        }
    }

    /**
     * Algorithm 2: Brute Force Approach
     * ---------------------------------
     * Idea:
     * Use an extra array to store elements in reverse order.
     *
     * Steps:
     * - Create new array of size n.
     * - Copy elements from arr[n-1] → arr[0].
     * - Copy back into original array.
     *
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     * Concept: Extra space reversal.
     */
    public static void reverseBrute(int[] arr) {
        int n = arr.length;
        int[] temp = new int[n];
        for (int i = 0; i < n; i++) {
            temp[i] = arr[n - 1 - i];
        }
        for (int i = 0; i < n; i++) {
            arr[i] = temp[i];
        }
    }

    // Utility method to print array
    private static void printArray(int[] arr) {
        System.out.print("[");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]);
            if (i < arr.length - 1) System.out.print(", ");
        }
        System.out.println("]");
    }
}
