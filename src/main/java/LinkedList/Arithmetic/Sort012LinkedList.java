package LinkedList.Arithmetic;

import LinkedList.ListNode; // ✔ Import your existing ListNode class

/**
 * Program to Sort a Linked List containing only 0s, 1s, and 2s.
 * Implements:
 * 1. Counting Approach (O(n), overwrite values)
 * 2. Dutch Flag Three-Pointer Approach (O(n), rearrange links)
 *
 * Problem:
 * Given a linked list with nodes containing values 0, 1, or 2,
 * sort the list so that all 0s come first, then 1s, then 2s.
 *
 * Example:
 * Input  : 1 -> 2 -> 0 -> 1 -> 2 -> 0
 * Output : 0 -> 0 -> 1 -> 1 -> 2 -> 2
 *
 * Mnemonics:
 * 👉 "Count and overwrite."
 * 👉 "Split and stitch."
 */
public class Sort012LinkedList {

    // ---------- Approach 1: Counting ----------
    public static ListNode sortCounting(ListNode head) {
        if (head == null || head.next == null) return head;

        int count0 = 0, count1 = 0, count2 = 0;
        ListNode curr = head;

        // Step 1: Traverse list and count number of 0s, 1s, 2s
        while (curr != null) {
            if (curr.val == 0) count0++;
            else if (curr.val == 1) count1++;
            else count2++;
            curr = curr.next;
        }

        // Step 2: Traverse again and overwrite node values in sorted order
        curr = head;
        while (count0-- > 0) {
            curr.val = 0;
            curr = curr.next;
        }
        while (count1-- > 0) {
            curr.val = 1;
            curr = curr.next;
        }
        while (count2-- > 0) {
            curr.val = 2;
            curr = curr.next;
        }

        return head;
    }

    // ---------- Approach 2: Dutch Flag (Changing Links) ----------
    public static ListNode sortDutchFlag(ListNode head) {
        if (head == null || head.next == null) return head;

        // Step 1: Create three dummy lists for 0s, 1s, and 2s
        ListNode zeroDummy = new ListNode(0), oneDummy = new ListNode(0), twoDummy = new ListNode(0);
        ListNode zero = zeroDummy, one = oneDummy, two = twoDummy;

        // Step 2: Traverse original list and attach each node to correct dummy list
        ListNode curr = head;
        while (curr != null) {
            if (curr.val == 0) {
                zero.next = curr;
                zero = zero.next;
            } else if (curr.val == 1) {
                one.next = curr;
                one = one.next;
            } else {
                two.next = curr;
                two = two.next;
            }
            curr = curr.next;
        }

        // Step 3: Connect zero → one → two lists
        zero.next = (oneDummy.next != null) ? oneDummy.next : twoDummy.next;
        one.next = twoDummy.next;

        // Step 4: Terminate last node
        two.next = null;

        // Step 5: Return merged list head
        return zeroDummy.next;
    }
}
