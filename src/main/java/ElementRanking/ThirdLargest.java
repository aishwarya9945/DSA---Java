package ElementRanking;

// Program to find the third largest element in an integer array
// using both Linear Search and Brute Force (Sorting) methods
public class ThirdLargest {

    public static void main(String[] args) {
        // Input array of integers
        int[] arr = {1, 2, 3, 5, 8, 9};

        // ---------- Linear Search Approach ----------
        // Initialize top three values to the smallest possible integer
        int first = Integer.MIN_VALUE;
        int second = Integer.MIN_VALUE;
        int third = Integer.MIN_VALUE;

        // Traverse the array to determine top three values
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > first) {
                // Shift down the previously found values
                third = second;
                second = first;
                first = arr[i];
            } else if (arr[i] > second && arr[i] != first) {
                // Update second and shift third down
                third = second;
                second = arr[i];
            } else if (arr[i] > third && arr[i] != first && arr[i] != second) {
                // Update third if distinct
                third = arr[i];
            }
        }

        // Display result for Linear Search
        if (third == Integer.MIN_VALUE) {
            System.out.println("Third Largest (Linear Search): Not found");
        } else {
            System.out.println("Third Largest (Linear Search): " + third);
        }

        // ---------- Brute Force Approach ----------
        // Clone and sort the array in ascending order
        int[] sorted = arr.clone();
        java.util.Arrays.sort(sorted);

        // Reverse the sorted array to obtain descending order
        for (int i = 0; i < sorted.length / 2; i++) {
            int temp = sorted[i];
            sorted[i] = sorted[sorted.length - 1 - i];
            sorted[sorted.length - 1 - i] = temp;
        }

        // Identify third distinct largest element
        int count = 1;
        int bruteThird = Integer.MIN_VALUE;

        // Find the third unique maximum
        for (int i = 1; i < sorted.length; i++) {
            if (sorted[i] != sorted[i - 1]) {
                count++;
                if (count == 3) {
                    bruteThird = sorted[i];
                    break;
                }
            }
        }

        // Display result for Brute Force approach
        if (bruteThird == Integer.MIN_VALUE) {
            System.out.println("Third Largest (Brute Force): Not found");
        } else {
            System.out.println("Third Largest (Brute Force): " + bruteThird);
        }
    }
}

/*
Concepts Demonstrated: Linear Search, Brute Force (Sorting)

Algorithm 1: Linear Search
Steps:
1. Initialize three variables — `first`, `second`, `third` — to Integer.MIN_VALUE.
2. Loop through each element:
   - If the element > `first`, shift down the top values and update `first`.
   - Else if element > `second` and not equal to `first`, shift down and update `second`.
   - Else if element > `third` and distinct from first and second, update `third`.
3. Print the third largest if found.

Time Complexity: O(n)
Space Complexity: O(1)

---

Algorithm 2: Brute Force (Sorting)
Steps:
1. Clone and sort the array in ascending order.
2. Reverse it to obtain descending order.
3. Traverse to locate the third distinct element.
4. Print the value when found.

Time Complexity: O(n log n) — sorting step
Space Complexity: O(n) — cloned array for sorting
*/
