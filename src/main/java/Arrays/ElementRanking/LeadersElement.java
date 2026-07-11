package Arrays.ElementRanking;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// Program to find all Leaders in an integer array
// Leaders are elements greater than or equal to all elements to their right
// Demonstrates both: with duplicates allowed and without duplicates
// Also shows difference between using arrays vs lists
public class LeadersElement {

    public static void main(String[] args) {
        // Input array of integers
        int[] arr = {16, 17, 4, 3, 5, 2, 2, 5};

        // ---------- Linear Traversal Approach (With Duplicates Allowed) ----------
        int maxFromRight = arr[arr.length - 1];
        System.out.print("Leaders (With Duplicates, Array Print): " + maxFromRight);

        for (int i = arr.length - 2; i >= 0; i--) {
            if (arr[i] >= maxFromRight) {
                maxFromRight = arr[i];
                System.out.print(" " + arr[i]);
            }
        }
        System.out.println();

        // ---------- Linear Traversal Approach (Without Duplicates) ----------
        maxFromRight = arr[arr.length - 1];
        System.out.print("Leaders (Without Duplicates, Array Print): " + maxFromRight);

        for (int i = arr.length - 2; i >= 0; i--) {
            if (arr[i] > maxFromRight) {  // strictly greater
                maxFromRight = arr[i];
                System.out.print(" " + arr[i]);
            }
        }
        System.out.println();

        // ---------- Using List to Collect Leaders ----------
        List<Integer> leadersList = new ArrayList<>();
        maxFromRight = arr[arr.length - 1];
        leadersList.add(maxFromRight);

        for (int i = arr.length - 2; i >= 0; i--) {
            if (arr[i] >= maxFromRight) {
                leadersList.add(arr[i]);
                maxFromRight = arr[i];
            }
        }

        // Reverse to restore original left-to-right order
        Collections.reverse(leadersList);
        System.out.println("Leaders (List Return): " + leadersList);

        // ---------- Brute Force Approach ----------
        System.out.print("Leaders (Brute Force): ");
        for (int i = 0; i < arr.length; i++) {
            boolean isLeader = true;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] < arr[j]) {
                    isLeader = false;
                    break;
                }
            }
            if (isLeader) {
                System.out.print(arr[i] + " ");
            }
        }
        System.out.println();
    }
}

/*
Definition:
A Leader in an array is an element that is greater than or equal to all elements to its right.
The rightmost element is always a leader.

---

Algorithm 1: Linear Traversal (Efficient)
Steps:
1. Start from the last element; mark it as a leader.
2. Traverse the array from right to left.
3. Keep track of the maximum element seen so far.
4. If current element >= maxFromRight → leader (duplicates allowed).
5. If current element > maxFromRight → leader (duplicates not allowed).
6. Print leaders directly (array style) or collect them in a list.

Time Complexity: O(n)
Space Complexity: O(1) for array print, O(k) for list (where k = number of leaders)

---

Algorithm 2: Brute Force
Steps:
1. For each element, compare it with all elements to its right.
2. If it is greater than or equal to all of them, mark it as a leader.
3. Print all leaders.

Time Complexity: O(n^2)
Space Complexity: O(1)

---

Difference: Array vs List
- **Array Print Approach:** Leaders are printed directly during traversal. Simple and memory-efficient, but results cannot be reused later.
- **List Approach:** Leaders are collected into a List<Integer>. Flexible — you can return them from a method, reverse order, or manipulate further. Slightly more memory usage but more versatile.
*/
