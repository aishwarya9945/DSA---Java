package LinkedList.Arithmetic;

import LinkedList.ListNode; // ✔ Import the shared ListNode class

/**
 * Program to Add "1" to a Number represented by a Linked List.
 * Implements:
 * 1. Optimal Reverse + Carry Approach (O(n))
 * 2. Brute Force Convert-to-Integer Approach (O(n))
 *
 * Problem:
 * Given a non-empty linked list representing a non-negative integer.
 * Digits are stored in forward order (most significant digit first).
 * Add 1 to the number and return the result as a linked list.
 *
 * Example:
 * Input  : 1 -> 9 -> 9
 * Output : 2 -> 0 -> 0
 * Explanation: 199 + 1 = 200
 *
 * Mnemonic:
 * 👉 "Flip, Add, Flip."
 *
 * Algorithm Steps (Optimal Reverse + Carry):
 * 1. Reverse the linked list.
 * 2. Initialize carry = 1 (since we’re adding 1).
 * 3. Traverse nodes:
 *      - sum = node.val + carry
 *      - node.val = sum % 10
 *      - carry = sum / 10
 *      - Stop early if carry = 0
 * 4. If carry > 0 at the end, append new node.
 * 5. Reverse the list back.
 *
 * Algorithm Steps (Brute Force Conversion):
 * 1. Traverse list to form integer.
 * 2. Add 1.
 * 3. Convert result back into linked list.
 */
public class AddOneToNumber {

    // ---------- Optimal Reverse + Carry Approach ----------
    public static ListNode addOneOptimal(ListNode head) {
        head = reverse(head);              // ✔ reverse list

        ListNode curr = head;
        int carry = 1;                     // ✔ start with +1

        while (curr != null) {
            int sum = curr.val + carry;    // ✔ add carry
            curr.val = sum % 10;           // ✔ update digit
            carry = sum / 10;              // ✔ update carry

            if (carry == 0) break;         // ✔ stop early if no carry
            if (curr.next == null && carry > 0) {
                curr.next = new ListNode(carry); // ✔ append new node if needed
                carry = 0;
                break;
            }
            curr = curr.next;              // ✔ move forward
        }

        return reverse(head);              // ✔ reverse back
    }

    // ---------- Brute Force Convert-to-Integer Approach ----------
    public static ListNode addOneBrute(ListNode head) {
        int num = 0;
        while (head != null) {             // ✔ build integer
            num = num * 10 + head.val;
            head = head.next;
        }

        num += 1;                          // ✔ add 1

        ListNode dummy = new ListNode(0);  // ✔ dummy anchor
        ListNode curr = dummy;

        for (char c : String.valueOf(num).toCharArray()) {
            curr.next = new ListNode(c - '0'); // ✔ convert digit to node
            curr = curr.next;
        }

        return dummy.next;                 // ✔ return result list
    }

    // Utility: Reverse linked list
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
}
