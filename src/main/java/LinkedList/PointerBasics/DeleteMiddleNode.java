package LinkedList.PointerBasics;

/**
 * Program to delete the middle node of a singly linked list.
 * Implements:
 * 1. Optimal Fast & Slow Pointer Approach (O(n), O(1))
 * 2. Brute Force Two-Pass Approach (O(n), O(1))
 *
 * Problem:
 * Given the head of a singly linked list, delete the middle node and return the head.
 * If the list has an even number of nodes, delete the second middle node.
 *
 * Example:
 * Input  : 1 -> 2 -> 3 -> 4 -> 5
 * Output : 1 -> 2 -> 4 -> 5
 *
 * Input  : 1 -> 2 -> 3 -> 4
 * Output : 1 -> 2 -> 4
 */
public class DeleteMiddleNode {

    static class ListNode {
        int val;
        ListNode next;
        ListNode(int val) { this.val = val; }
    }

    public static void main(String[] args) {
        // Build sample list: 1 -> 2 -> 3 -> 4 -> 5
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);

        // ---------- Optimal Approach ----------
        ListNode optimalResult = deleteMiddleOptimal(head);
        System.out.print("List after deleting middle (Optimal): ");
        printList(optimalResult);

        // ---------- Brute Force Approach ----------
        // Rebuild list again for brute force demo
        head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);

        ListNode bruteResult = deleteMiddleBrute(head);
        System.out.print("List after deleting middle (Brute Force): ");
        printList(bruteResult);
    }

    /**
     * Algorithm 1: Optimal Fast & Slow Pointer Approach
     * -------------------------------------------------
     * Idea:
     * Use fast and slow pointers. When fast reaches end, slow is at middle.
     * Keep track of prev to unlink the middle node.
     *
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     */
    public static ListNode deleteMiddleOptimal(ListNode head) {
        if (head == null || head.next == null) return null;

        ListNode slow = head, fast = head, prev = null;
        while (fast != null && fast.next != null) {
            prev = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        // slow is middle, prev is node before middle
        prev.next = slow.next;
        return head;
    }

    /**
     * Algorithm 2: Brute Force Two-Pass Approach
     * ------------------------------------------
     * Idea:
     * First count total nodes. Then traverse again to middle index and delete it.
     *
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     */
    public static ListNode deleteMiddleBrute(ListNode head) {
        if (head == null || head.next == null) return null;

        int count = 0;
        ListNode curr = head;
        while (curr != null) {
            count++;
            curr = curr.next;
        }

        int midIndex = count / 2;
        curr = head;
        ListNode prev = null;
        for (int i = 0; i < midIndex; i++) {
            prev = curr;
            curr = curr.next;
        }
        prev.next = curr.next;
        return head;
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
