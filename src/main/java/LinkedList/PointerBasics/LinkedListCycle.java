package LinkedList.PointerBasics;

/**
 * Program to detect if a singly linked list has a cycle.
 * Implements:
 * 1. Optimal Fast & Slow Pointer Approach (O(n), O(1))
 * 2. Brute Force HashSet Approach (O(n), O(n))
 *
 * Problem:
 * Given the head of a linked list, return true if there is a cycle in the list.
 * A cycle exists if some node’s next pointer points back to a previous node.
 *
 * Example:
 * Input  : 3 -> 2 -> 0 -> -4
 *                 ^         |
 *                 |_________|
 * Output : true
 *
 * Input  : 1 -> 2 -> null
 * Output : false
 */
public class LinkedListCycle {

    static class ListNode {
        int val;
        ListNode next;
        ListNode(int val) { this.val = val; }
    }

    public static void main(String[] args) {
        // Build sample list with cycle: 3 -> 2 -> 0 -> -4 -> (points back to 2)
        ListNode head = new ListNode(3);
        head.next = new ListNode(2);
        head.next.next = new ListNode(0);
        head.next.next.next = new ListNode(-4);
        head.next.next.next.next = head.next; // cycle

        // ---------- Optimal Approach ----------
        boolean optimalResult = hasCycleOptimal(head);
        System.out.println("Has Cycle (Optimal): " + optimalResult);

        // ---------- Brute Force Approach ----------
        boolean bruteResult = hasCycleBrute(head);
        System.out.println("Has Cycle (Brute Force): " + bruteResult);
    }

    /**
     * Algorithm 1: Optimal Fast & Slow Pointer Approach
     * -------------------------------------------------
     * Idea:
     * Move slow pointer one step, fast pointer two steps.
     * If they ever meet, there is a cycle.
     *
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     */
    public static boolean hasCycleOptimal(ListNode head) {
        // Edge case: empty list or single node cannot form a cycle
        if (head == null || head.next == null) return false;

        // Initialize two pointers: slow moves 1 step, fast moves 2 steps
        ListNode slow = head, fast = head;

        // Loop continues only if fast can safely jump two steps
        while (fast != null && fast.next != null) {
            slow = slow.next;       // move slow one step
            fast = fast.next.next;  // move fast two steps

            // If slow and fast meet, a cycle exists
            if (slow == fast) return true;
        }

        // If fast reaches the end, no cycle
        return false;
    }

    /**
     * Algorithm 2: Brute Force HashSet Approach
     * -----------------------------------------
     * Idea:
     * Store visited nodes in a HashSet.
     * If a node is revisited, there is a cycle.
     *
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     */
    public static boolean hasCycleBrute(ListNode head) {
        java.util.HashSet<ListNode> visited = new java.util.HashSet<>();
        ListNode curr = head;
        while (curr != null) {
            if (visited.contains(curr)) return true;
            visited.add(curr);
            curr = curr.next;
        }
        return false;
    }
}
