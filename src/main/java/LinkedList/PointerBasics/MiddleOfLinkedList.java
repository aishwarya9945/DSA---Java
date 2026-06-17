package LinkedList.PointerBasics;

/**
 * Program to find the middle node of a singly linked list.
 * Implements:
 * 1. Optimal Fast & Slow Pointer Approach (O(n), O(1))
 * 2. Brute Force Two-Pass Approach (O(n), O(1))
 *
 * Problem:
 * Given the head of a singly linked list, return the middle node.
 * If there are two middle nodes, return the second middle node.
 *
 * Example:
 * Input  : 1 -> 2 -> 3 -> 4 -> 5
 * Output : 3
 *
 * Input  : 1 -> 2 -> 3 -> 4 -> 5 -> 6
 * Output : 4
 */
public class MiddleOfLinkedList {

    static class ListNode {
        int val;
        ListNode next;
        ListNode(int val) { this.val = val; }
    }

    public static void main(String[] args) {
        // Build sample list: 1 -> 2 -> 3 -> 4 -> 5 -> 6
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        head.next.next.next.next.next = new ListNode(6);

        // ---------- Optimal Approach ----------
        ListNode optimalResult = middleNodeOptimal(head);
        System.out.println("Middle Node (Optimal): " + optimalResult.val);

        // ---------- Brute Force Approach ----------
        ListNode bruteResult = middleNodeBrute(head);
        System.out.println("Middle Node (Brute Force): " + bruteResult.val);
    }

    /**
     * Algorithm 1: Optimal Fast & Slow Pointer Approach
     * -------------------------------------------------
     * Idea:
     * Move slow pointer one step at a time, fast pointer two steps.
     * When fast reaches end, slow will be at middle.

     * Why it’s easy to recall
     * Slow pointer → “walks.”
     * Fast pointer → “runs.”
     *
     * When the runner finishes, the walker is at the middle.

     * Time Complexity: O(n)
     * Space Complexity: O(1)
     */
    public static ListNode middleNodeOptimal(ListNode head) {
        ListNode slow = head, fast = head; // Initialize two pointers: slow moves 1 step, fast moves 2 steps
        while (fast != null && fast.next != null) { // Loop continues only if fast can safely move two steps ahead
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow; // Middle
    }

    /**
     * Algorithm 2: Brute Force Two-Pass Approach
     * ------------------------------------------
     * Idea:
     * First count total nodes. Then traverse again to the middle index.
     *
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     */
    public static ListNode middleNodeBrute(ListNode head) {
        int count = 0;
        ListNode curr = head;
        while (curr != null) {
            count++;
            curr = curr.next;
        }
        int midIndex = count / 2;
        curr = head;
        for (int i = 0; i < midIndex; i++) {
            curr = curr.next;
        }
        return curr;
    }
}
