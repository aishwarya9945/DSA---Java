package LinkedList.PointerBasics;

import java.util.HashSet;

public class LinkedListCycle {

    static class ListNode {
        int val;
        ListNode next;
        ListNode(int val) { this.val = val; }
    }

    /**
     * Optimal Approach: Fast & Slow Pointer (Floyd’s Cycle Detection)
     * ---------------------------------------------------------------
     * Mnemonic: "Runner catches walker → cycle."
     *
     * Idea:
     * - Move slow pointer one step, fast pointer two steps.
     * - If they ever meet, there is a cycle.
     * - If fast reaches null, no cycle.
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
     * Brute Force Approach: HashSet
     * -----------------------------
     * Mnemonic: "Seen before → cycle."
     *
     * Idea:
     * - Store visited nodes in a HashSet.
     * - If a node is revisited, there is a cycle.
     *
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     */
    public static boolean hasCycleBrute(ListNode head) {
        HashSet<ListNode> visited = new HashSet<>();
        ListNode curr = head;
        while (curr != null) {
            if (visited.contains(curr)) return true;
            visited.add(curr);
            curr = curr.next;
        }
        return false;
    }

    /**
     * Main method for testing
     */
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

        // Build sample list without cycle: 1 -> 2 -> null
        ListNode head2 = new ListNode(1);
        head2.next = new ListNode(2);

        System.out.println("Has Cycle (Optimal, no cycle): " + hasCycleOptimal(head2));
        System.out.println("Has Cycle (Brute Force, no cycle): " + hasCycleBrute(head2));
    }
}
