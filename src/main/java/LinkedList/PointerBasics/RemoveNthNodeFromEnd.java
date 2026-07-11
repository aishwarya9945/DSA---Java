package LinkedList.PointerBasics;

import LinkedList.ListNode;

public class RemoveNthNodeFromEnd {

    /**
     * Optimal Approach: Two Pointers with Dummy Node
     * ----------------------------------------------
     * Mnemonic: "Gap of n → Cut after slow."
     *
     * Idea:
     * - Use a dummy node before head.
     * - Move fast pointer n+1 steps ahead.
     * - Move both pointers until fast reaches end.
     * - Slow pointer will be just before the node to delete.
     *
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     */
    public ListNode removeNthFromEndOptimal(ListNode head, int n) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        ListNode fast = dummy, slow = dummy;

        // Move fast n+1 steps ahead
        for (int i = 0; i <= n; i++) {
            fast = fast.next;
        }

        // Move both until fast reaches end
        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }

        // Delete nth node
        slow.next = slow.next.next;
        return dummy.next;
    }

    /**
     * Brute Force Approach: Two-pass
     * ------------------------------
     * Mnemonic: "Count length → Cut at (len-n)."
     *
     * Idea:
     * - First traverse list to count total length.
     * - Then traverse again to (len-n)th node and delete next.
     *
     * Time Complexity: O(2n) ≈ O(n)
     * Space Complexity: O(1)
     */
    public ListNode removeNthFromEndBrute(ListNode head, int n) {
        if (head == null) return null;

        int length = 0;
        ListNode curr = head;
        while (curr != null) {
            length++;
            curr = curr.next;
        }

        // If removing head
        if (n == length) return head.next;

        curr = head;
        for (int i = 1; i < length - n; i++) {
            curr = curr.next;
        }
        curr.next = curr.next.next;
        return head;
    }

    /**
     * Main method for testing
     */
    public static void main(String[] args) {
        // Build sample list: 1 -> 2 -> 3 -> 4 -> 5
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);

        RemoveNthNodeFromEnd solver = new RemoveNthNodeFromEnd();

        // Optimal: remove 2nd from end → 1->2->3->5
        ListNode result1 = solver.removeNthFromEndOptimal(head, 2);
        printList(result1);

        // Reset list again
        head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);

        // Brute force: remove 2nd from end → 1->2->3->5
        ListNode result2 = solver.removeNthFromEndBrute(head, 2);
        printList(result2);
    }

    // Utility method to print list
    public static void printList(ListNode head) {
        ListNode curr = head;
        while (curr != null) {
            System.out.print(curr.val + (curr.next != null ? " -> " : ""));
            curr = curr.next;
        }
        System.out.println();
    }
}
