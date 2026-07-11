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
 */
public class MiddleOfLinkedList {

    static class ListNode {
        int val;
        ListNode next;
        ListNode(int val) { this.val = val; }
    }

    /**
     * Algorithm 1: Optimal Fast & Slow Pointer Approach
     * -------------------------------------------------
     * Mnemonic: "Runner finishes → Walker at middle."
     *
     * Idea:
     * - Initialize two pointers: slow and fast at head.
     * - Move slow one step, fast two steps.
     * - When fast reaches end, slow will be at middle.
     * - If even length, slow lands on the second middle (LeetCode requirement).
     *
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     */
    public static ListNode middleNodeOptimal(ListNode head) {
        ListNode slow = head, fast = head; // slow walks, fast runs
        while (fast != null && fast.next != null) {
            slow = slow.next;       // move 1 step
            fast = fast.next.next;  // move 2 steps
        }
        return slow; // middle node
    }

    /**
     * Algorithm 2: Brute Force Two-Pass Approach
     * ------------------------------------------
     * Mnemonic: "Count → Jump to mid."
     *
     * Idea:
     * - First count total nodes.
     * - Then traverse again to middle index (count/2).
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
        int midIndex = count / 2; // second middle for even length
        curr = head;
        for (int i = 0; i < midIndex; i++) {
            curr = curr.next;
        }
        return curr;
    }

    /**
     * Utility method to print list
     */
    public static void printList(ListNode head) {
        ListNode curr = head;
        while (curr != null) {
            System.out.print(curr.val + (curr.next != null ? " -> " : ""));
            curr = curr.next;
        }
        System.out.println();
    }

    /**
     * Main method for testing
     */
    public static void main(String[] args) {
        // Case 1: Even length list → 1->2->3->4->5->6
        ListNode headEven = new ListNode(1);
        headEven.next = new ListNode(2);
        headEven.next.next = new ListNode(3);
        headEven.next.next.next = new ListNode(4);
        headEven.next.next.next.next = new ListNode(5);
        headEven.next.next.next.next.next = new ListNode(6);

        System.out.print("Even List: ");
        printList(headEven);
        ListNode optimalEven = middleNodeOptimal(headEven);
        System.out.println("Middle Node (Optimal): " + (optimalEven != null ? optimalEven.val : "null"));
        ListNode bruteEven = middleNodeBrute(headEven);
        System.out.println("Middle Node (Brute): " + (bruteEven != null ? bruteEven.val : "null"));

        // Case 2: Odd length list → 1->2->3->4->5
        ListNode headOdd = new ListNode(1);
        headOdd.next = new ListNode(2);
        headOdd.next.next = new ListNode(3);
        headOdd.next.next.next = new ListNode(4);
        headOdd.next.next.next.next = new ListNode(5);

        System.out.print("\nOdd List: ");
        printList(headOdd);
        ListNode optimalOdd = middleNodeOptimal(headOdd);
        System.out.println("Middle Node (Optimal): " + (optimalOdd != null ? optimalOdd.val : "null"));
        ListNode bruteOdd = middleNodeBrute(headOdd);
        System.out.println("Middle Node (Brute): " + (bruteOdd != null ? bruteOdd.val : "null"));
    }
}
