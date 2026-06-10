package ElementRanking;

import java.util.HashMap;
import java.util.Map;

// Combined Program: Find Majority Element using
// 1. Boyer-Moore Voting Algorithm (Efficient)
// 2. HashMap
// 3. Brute Force
public class MajorityElementAll {

    public static void main(String[] args) {
        // Example inputs
        int[] arr1 = {3, 3, 4, 2, 3, 3, 3};
        int[] arr2 = {2, 2, 1, 1, 1, 2, 2};

        // ---------- Efficient Approach (Boyer-Moore Voting Algorithm) ----------
        int candidate = findCandidate(arr1);
        if (isMajority(arr1, candidate)) {
            System.out.println("Majority Element (Voting Algorithm, Input: [3,3,4,2,3,3,3]): " + candidate);
        } else {
            System.out.println("No Majority Element Found (Voting Algorithm)");
        }

        // ---------- HashMap Approach ----------
        int mapResult = findMajorityElementUsingMap(arr2);
        System.out.println("Majority Element (HashMap, Input: [2,2,1,1,1,2,2]): " +
                (mapResult != -1 ? mapResult : "Not found"));

        // ---------- Brute Force Approach ----------
        int bruteResult = findMajorityBruteForce(arr1);
        System.out.println("Majority Element (Brute Force, Input: [3,3,4,2,3,3,3]): " +
                (bruteResult != -1 ? bruteResult : "Not found"));
    }

    // Boyer-Moore Voting Algorithm
    private static int findCandidate(int[] arr) {
        int count = 0, candidate = -1;
        for (int num : arr) {
            if (count == 0) {
                candidate = num;
                count = 1;
            } else if (num == candidate) {
                count++;
            } else {
                count--;
            }
        }
        return candidate;
    }

    private static boolean isMajority(int[] arr, int candidate) {
        int freq = 0;
        for (int num : arr) {
            if (num == candidate) freq++;
        }
        return freq > arr.length / 2;
    }

    // HashMap Approach
    private static int findMajorityElementUsingMap(int[] nums) {
        int n = nums.length;
        Map<Integer, Integer> map = new HashMap<>();

        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() > n / 2) {
                return entry.getKey();
            }
        }
        return -1;
    }

    // Brute Force Approach
    private static int findMajorityBruteForce(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int count = 0;
            for (int j = 0; j < arr.length; j++) {
                if (arr[i] == arr[j]) count++;
            }
            if (count > arr.length / 2) return arr[i];
        }
        return -1;
    }
}

/*
==========================
Question:
Find the majority element in an array. A majority element is the element that appears more than n/2 times.

Definition:
Majority element = element that appears more than n/2 times in an array of size n.

---

Algorithm 1: Boyer-Moore Voting Algorithm (Efficient)
Steps:
1. Traverse array with candidate and counter.
2. If counter = 0, set candidate = current element.
3. If current element == candidate, increment counter; else decrement.
4. After traversal, candidate is potential majority.
5. Verify candidate frequency > n/2.
Time Complexity: O(n)
Space Complexity: O(1)

Input: [3, 3, 4, 2, 3, 3, 3]
Output: Majority Element (Voting Algorithm): 3

---

Algorithm 2: HashMap
Steps:
1. Use a HashMap to count frequency of each element.
2. Traverse the map to check if any element count > n/2.
3. Return that element, else return -1.
Time Complexity: O(n)
Space Complexity: O(n)

Input: [2, 2, 1, 1, 1, 2, 2]
Output: Majority Element (HashMap): 2

---

Algorithm 3: Brute Force
Steps:
1. For each element, count its frequency by scanning the array.
2. If frequency > n/2, return that element.
3. Else return -1.
Time Complexity: O(n^2)
Space Complexity: O(1)

Input: [3, 3, 4, 2, 3, 3, 3]
Output: Majority Element (Brute Force): 3
==========================
*/
