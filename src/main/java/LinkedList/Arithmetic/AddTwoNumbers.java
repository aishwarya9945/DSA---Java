package LinkedList.Arithmetic;

import LinkedList.ListNode;  // ✔ import the shared ListNode

/**
 * Program to Add Two Numbers represented by Linked Lists.
 * Implements:
 * 1. Optimal Carry Loop Approach (O(max(m,n)))
 * 2. Brute Force Convert-to-Integer Approach (O(m+n))
 *
 * Problem:
 * Given two non-empty linked lists representing two non-negative integers.
 * Digits are stored in reverse order, and each node contains a single digit.
 * Add the two numbers and return the sum as a linked list.
 *
 * Example:
 * Input  : (2 -> 4 -> 3) + (5 -> 6 -> 4)
 * Output : 7 -> 0 -> 8
 * Explanation: 342 + 465 = 807
 *
 * Mnemonic:
 * 👉 "Dummy carries the math."
 *
 * Algorithm Steps (Optimal Carry Loop):
 * 1. Create a dummy node, set carry = 0.
 * 2. While either list has nodes or carry > 0:
 *      - Extract digit from each list (0 if null).
 *      - Compute sum = d1 + d2 + carry.
 *      - Create new node with sum % 10.
 *      - Update carry = sum / 10.
 *      - Advance both lists.
 * 3. Return dummy.next.
 */
public class AddTwoNumbers {

    // ---------- Optimal Approach ----------
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);   // ✔ dummy node anchor
        ListNode curr = dummy;              // ✔ builder pointer
        int carry = 0;                      // ✔ carry initialization

        while (l1 != null || l2 != null || carry > 0) { // ✔ loop until all digits + carry processed
            int d1 = (l1 != null) ? l1.val : 0; // ✔ digit from l1 or 0
            int d2 = (l2 != null) ? l2.val : 0; // ✔ digit from l2 or 0
            int sum = d1 + d2 + carry;          // ✔ add digits + carry

            curr.next = new ListNode(sum % 10); // ✔ create new node with digit
            carry = sum / 10;                   // ✔ update carry

            curr = curr.next;                   // ✔ move builder pointer
            if (l1 != null) l1 = l1.next;       // ✔ advance l1
            if (l2 != null) l2 = l2.next;       // ✔ advance l2
        }

        return dummy.next; // ✔ return result list
    }

    /**
     * Algorithm 2: Brute Force Convert-to-Integer Approach
     * ----------------------------------------------------
     * Idea:
     * Convert both linked lists into integers.
     * Add them, then convert back into a linked list.
     *
     * Steps:
     * 1. Traverse l1 and l2 to form integers.
     * 2. Add integers.
     * 3. Convert sum back into linked list.
     *
     * Time Complexity: O(m+n)
     * Space Complexity: O(m+n)
     * Concept: Simple conversion, not optimal for very large numbers.
     */
    public ListNode addTwoNumbersBrute(ListNode l1, ListNode l2) {
        int num1 = 0, num2 = 0, place = 1;

        while (l1 != null) {               // ✔ build integer from l1
            num1 += l1.val * place;
            place *= 10;
            l1 = l1.next;
        }

        place = 1;
        while (l2 != null) {               // ✔ build integer from l2
            num2 += l2.val * place;
            place *= 10;
            l2 = l2.next;
        }

        int sum = num1 + num2;             // ✔ add integers
        if (sum == 0) return new ListNode(0);

        ListNode dummy = new ListNode(0);  // ✔ dummy node anchor
        ListNode curr = dummy;

        while (sum > 0) {                  // ✔ convert sum back to list
            curr.next = new ListNode(sum % 10);
            curr = curr.next;
            sum /= 10;
        }

        return dummy.next;                 // ✔ return result list
    }
}
