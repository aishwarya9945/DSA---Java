package LinkedList.Arithmetic;

import LinkedList.ListNode; // ✔ Import the shared ListNode class

/**
 * Program to Multiply Two Numbers represented by Linked Lists.
 * Implements:
 * 1. Optimal Simulation Approach (O(m*n))
 * 2. Brute Force Convert-to-Integer Approach (O(m+n))
 *
 * Problem:
 * Given two linked lists representing two non-negative integers.
 * Digits are stored in forward order (most significant digit first).
 * Multiply the two numbers and return the product as a linked list.
 *
 * Example:
 * Input  : (9 -> 4) × (6 -> 2)
 * Output : 5 -> 8 -> 2 -> 8
 * Explanation: 94 × 62 = 5828
 *
 * Mnemonics:
 * 👉 "Simulate school multiplication."
 * 👉 "Reverse → Multiply → Accumulate → Carry → Build."
 */
public class MultiplyTwoNumbers {

    // ---------- Approach 1: Optimal Simulation ----------
    public static ListNode multiplyOptimal(ListNode l1, ListNode l2) {
        // Step 1: Reverse both lists to start from least significant digit
        l1 = reverse(l1);
        l2 = reverse(l2);

        int m = length(l1), n = length(l2);
        int[] res = new int[m + n]; // Step 2: Result array to accumulate products

        // Step 2: Nested loops to multiply each digit of l1 with each digit of l2
        ListNode p1 = l1;
        for (int i = 0; i < m; i++) {
            ListNode p2 = l2;
            for (int j = 0; j < n; j++) {
                res[i + j] += p1.val * p2.val; // accumulate product
                p2 = p2.next;
            }
            p1 = p1.next;
        }

        // Step 3: Handle carry propagation across the array
        for (int i = 0; i < res.length; i++) {
            int carry = res[i] / 10;
            res[i] %= 10;
            if (carry > 0) res[i + 1] += carry;
        }

        // Step 4: Build linked list from array (skip leading zeros)
        int k = res.length - 1;
        while (k > 0 && res[k] == 0) k--;

        ListNode dummy = new ListNode(0);
        ListNode curr = dummy;
        for (int i = k; i >= 0; i--) {
            curr.next = new ListNode(res[i]);
            curr = curr.next;
        }

        return dummy.next;
    }

    // ---------- Approach 2: Brute Force Convert-to-Integer ----------
    public static ListNode multiplyBrute(ListNode l1, ListNode l2) {
        long num1 = 0, num2 = 0;

        // Step 1: Traverse l1 to form integer
        while (l1 != null) {
            num1 = num1 * 10 + l1.val;
            l1 = l1.next;
        }

        // Step 2: Traverse l2 to form integer
        while (l2 != null) {
            num2 = num2 * 10 + l2.val;
            l2 = l2.next;
        }

        // Step 3: Multiply integers
        long product = num1 * num2;
        if (product == 0) return new ListNode(0);

        // Step 4: Convert product back into linked list
        ListNode dummy = new ListNode(0);
        ListNode curr = dummy;
        for (char c : String.valueOf(product).toCharArray()) {
            curr.next = new ListNode(c - '0');
            curr = curr.next;
        }

        return dummy.next;
    }

    // ---------- Utility: Reverse linked list ----------
    private static ListNode reverse(ListNode head) {
        ListNode prev = null, curr = head;
        while (curr != null) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;
    }

    // ---------- Utility: Length of linked list ----------
    private static int length(ListNode head) {
        int len = 0;
        while (head != null) {
            len++;
            head = head.next;
        }
        return len;
    }
}
