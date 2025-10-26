package ConditionalMovement;

// Program to segregate even and odd numbers using both optimal two-pointer and brute force approaches
public class EvenODDSegregate {

    public static void main(String[] args) {
        // Input array containing both even and odd numbers
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9};

        // ---------- Optimal Two-Pointer Approach (In-place) ----------
        int[] arrTwoPointer = arr.clone(); // Clone to preserve original for fair comparison
        int left = 0;
        int right = arrTwoPointer.length - 1;

        while (left < right) {
            if (arrTwoPointer[left] % 2 == 0) {
                left++;
            } else if (arrTwoPointer[right] % 2 != 0) {
                right--;
            } else {
                int temp = arrTwoPointer[left];
                arrTwoPointer[left] = arrTwoPointer[right];
                arrTwoPointer[right] = temp;
                left++;
                right--;
            }
        }

        System.out.println("Array after segregation (Two-Pointer): " + java.util.Arrays.toString(arrTwoPointer));

        // ---------- Brute Force Approach (Using Extra Array) ----------
        int[] arrBrute = arr.clone(); // Clone original array
        int[] temp = new int[arrBrute.length];
        int index = 0;

        // First pass: add all even elements preserving no relative order guarantee
        for (int num : arrBrute) {
            if (num % 2 == 0) {
                temp[index++] = num;
            }
        }

        // Second pass: add all odd elements
        for (int num : arrBrute) {
            if (num % 2 != 0) {
                temp[index++] = num;
            }
        }

        // Copy temp array back to original for final result
        System.arraycopy(temp, 0, arrBrute, 0, arrBrute.length);

        System.out.println("Array after segregation (Brute Force): " + java.util.Arrays.toString(arrBrute));
    }
}

/*
Concepts Used: Two-Pointer (Optimal In-Place), Brute Force (Using Temporary Array)

Two-Pointer Approach:
- Uses two pointers at array ends.
- Moves pointers inward, swapping odd and even numbers as needed.
- Time Complexity: O(n)
- Space Complexity: O(1)
- Does not preserve relative order.

Brute Force Approach:
- Uses extra space with a temporary array.
- First collects evens, then odds.
- Time Complexity: O(n)
- Space Complexity: O(n)
- Preserves the sequence of evens and odds but does not guarantee their relative original order strictly.

Both approaches effectively segregate even and odd numbers, with trade-offs in space and order preservation.
*/
