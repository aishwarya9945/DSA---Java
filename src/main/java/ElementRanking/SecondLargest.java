package ElementRanking;

// Program to find the second largest element in an integer array
// using both Linear Search and Brute Force (Sorting) methods
public class SecondLargest {

    public static void main(String[] args) {
        // Input array containing both small and large numbers
        int[] arr = {12, 3, 6, 17, 19, 5};

        // ---------- Linear Search Approach ----------
        // Initialize largest to the first element
        int largest = arr[0];
        // Initialize second largest to the smallest possible value
        int secondLargest = Integer.MIN_VALUE;

        // Traverse the array to identify largest and second largest
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > largest) {
                secondLargest = largest;
                largest = arr[i];
            } else if (arr[i] > secondLargest && arr[i] != largest) {
                secondLargest = arr[i];
            }
        }

        // Print result for Linear Search
        if (secondLargest == Integer.MIN_VALUE) {
            System.out.println("Second Largest (Linear Search): Not found");
        } else {
            System.out.println("Second Largest (Linear Search): " + secondLargest);
        }

        // ---------- Brute Force Approach ----------
        // Clone the original array to keep it intact
        int[] sorted = arr.clone();

        // Sort the cloned array in ascending order
        java.util.Arrays.sort(sorted);

        // Reverse the sorted array to make it descending
        for (int i = 0; i < sorted.length / 2; i++) {
            int temp = sorted[i];
            sorted[i] = sorted[sorted.length - 1 - i];
            sorted[sorted.length - 1 - i] = temp;
        }

        // Identify the second largest by finding the first number smaller than the largest
        int largestDesc = sorted[0];
        int bruteSecondLargest = Integer.MIN_VALUE;

        for (int i = 1; i < sorted.length; i++) {
            if (sorted[i] < largestDesc) {
                bruteSecondLargest = sorted[i];
                break;
            }
        }

        // Print the result for Brute Force
        if (bruteSecondLargest == Integer.MIN_VALUE) {
            System.out.println("Second Largest (Brute Force): Not found");
        } else {
            System.out.println("Second Largest (Brute Force): " + bruteSecondLargest);
        }
    }
}

/*
Concepts Illustrated: Linear Search, Brute Force (Sorting)

Algorithm 1: Linear Search
Steps:
1. Initialize two variables:
   - `largest` = first element
   - `secondLargest` = Integer.MIN_VALUE
2. Iterate through each element:
   - If element > `largest`, update `secondLargest` = `largest` and then `largest` = element
   - Else if element < `largest` and > `secondLargest`, update `secondLargest`
3. Print the result after traversal.

Time Complexity: O(n)
Space Complexity: O(1)

---

Algorithm 2: Brute Force (Sorting)
Steps:
1. Clone the input array to preserve the original.
2. Sort the array in ascending order.
3. Reverse it to get descending order.
4. The second largest is the first unique element smaller than the largest.

Time Complexity: O(n log n) — due to sorting
Space Complexity: O(n) — for cloned array
*/
