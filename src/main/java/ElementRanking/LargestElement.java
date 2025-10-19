package ElementRanking;

// This class finds the largest element in an integer array
public class LargestElement {

    public static void main(String[] args) {
        // Initialize an array of integers
        int[] arr = { 12, 3, 4, 5, 6, 15 };

        // Assume the first element is the largest initially
        int largest = arr[0];

        // Loop through the array starting from the second element
        for (int i = 1; i < arr.length; i++) {
            // If the current element is greater than the current largest, update it
            if (arr[i] > largest) {
                largest = arr[i];
            }
        }

        // Print the largest element found in the array
        System.out.println(largest);
    }
}