package ElementRanking;

// Program to find the largest element in an integer array
// using both Linear Search and Brute Force (Sorting) approaches
public class LargestElement {

    public static void main(String[] args) {
        // Input array of integers
        int[] arr = {12, 3, 4, 5, 6, 15};

        // ---------- Linear Search Approach ----------
        // Start by assuming the first element is the largest
        int largest = arr[0];

        // Traverse the array from the second element onwards
        for (int i = 1; i < arr.length; i++) {
            // Update if a larger element is found
            if (arr[i] > largest) {
                largest = arr[i];
            }
        }

        // Display the largest element found using linear search
        System.out.println("Largest Element (Linear Search): " + largest);

        // ---------- Brute Force Approach ----------
        // Clone the original array to retain its initial values
        int[] sorted = arr.clone();

        // Sort the cloned array in ascending order
        java.util.Arrays.sort(sorted);

        // The last element after sorting is the largest
        int bruteLargest = sorted[sorted.length - 1];

        // Display the result from the brute-force method
        System.out.println("Largest Element (Brute Force): " + bruteLargest);
    }
}

/*
Concepts Demonstrated: Linear Search and Brute Force (Sorting)

Algorithm 1: Linear Search
Steps:
1. Initialize an array of integers.
2. Assign the first element as the current largest.
3. Iterate through the array:
   - If any element is greater than the current largest, update it.
4. Once traversal is complete, print the largest element.

Time Complexity: O(n)  → Single traversal through the array
Space Complexity: O(1)  → No additional memory used

---

Algorithm 2: Brute Force (Sorting)
Steps:
1. Clone the original array to keep the source intact.
2. Sort the cloned array in ascending order.
3. The largest element will be the final element in the sorted array.
4. Print this element as the result.

Time Complexity: O(n log n)  → Due to sorting operation
Space Complexity: O(n)  → For cloned array storage
*/
        System.out.println(largest);
    }
}
>>>>>>> f9c965a4cbee20caa5db21f25d08f31caa38a421
