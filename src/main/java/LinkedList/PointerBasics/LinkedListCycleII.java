package LinkedList.PointerBasics;

import java.util.HashSet;

/**
 * Program to find the node where the cycle begins in a singly linked list.
 * Implements:
 * 1. Optimal Fast & Slow Pointer Approach (O(n), O(1))
 * 2. Brute Force HashSet Approach (O(n), O(n))
 */
public class LinkedListCycleII {

    static class ListNode {
        int val;
        ListNode next;
        ListNode(int val) { this.val = val; }
    }

    /**
     * Optimal Approach: Fast & Slow Pointer (Floyd’s Cycle Detection)
     * ---------------------------------------------------------------
     * Mnemonic: "Detect → Reset → Walk → Meet."
     *
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     */
    public static ListNode detectCycleOptimal(ListNode head) {
        if (head == null || head.next == null) return null;

        ListNode slow = head, fast = head;
        boolean hasCycle = false;

        // Step 1: Detect cycle
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                hasCycle = true;
                break;
            }
        }

        if (!hasCycle) return null;

        // Step 2: Reset one pointer to head
        slow = head;

        // Step 3: Move both one step at a time
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }

        return slow; // cycle start
    }

    /**
     * Brute Force Approach: HashSet
     * -----------------------------
     * Mnemonic: "Seen before → cycle start."
     *
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     */
    public static ListNode detectCycleBrute(ListNode head) {
        HashSet<ListNode> visited = new HashSet<>();
        ListNode curr = head;
        while (curr != null) {
            if (visited.contains(curr)) return curr;
            visited.add(curr);
            curr = curr.next;
        }
        return null;
    }

    /**
     * Main method for testing
     */
    public static void main(String[] args) {
        // Case 1: List with cycle (3 -> 2 -> 0 -> -4 -> points back to 2)
        ListNode head1 = new ListNode(3);
        head1.next = new ListNode(2);
        head1.next.next = new ListNode(0);
        head1.next.next.next = new ListNode(-4);
        head1.next.next.next.next = head1.next; // cycle at node 2

        System.out.println("Case 1: Cycle present");
        System.out.println("Optimal → Cycle starts at: " +
                (detectCycleOptimal(head1) != null ? detectCycleOptimal(head1).val : "null"));
        System.out.println("Brute   → Cycle starts at: " +
                (detectCycleBrute(head1) != null ? detectCycleBrute(head1).val : "null"));

        // Case 2: List without cycle (1 -> 2 -> null)
        ListNode head2 = new ListNode(1);
        head2.next = new ListNode(2);

        System.out.println("\nCase 2: No cycle");
        System.out.println("Optimal → Cycle starts at: " +
                (detectCycleOptimal(head2) != null ? detectCycleOptimal(head2).val : "null"));
        System.out.println("Brute   → Cycle starts at: " +
                (detectCycleBrute(head2) != null ? detectCycleBrute(head2).val : "null"));
    }
}
