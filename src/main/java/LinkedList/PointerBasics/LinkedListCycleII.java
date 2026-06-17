package LinkedList.PointerBasics;

/**
 * Program to find the node where the cycle begins in a singly linked list.
 * Implements:
 * 1. Optimal Fast & Slow Pointer Approach (O(n), O(1))
 * 2. Brute Force HashMap Approach (O(n), O(n))
 *
 * Problem:
 * Given the head of a linked list, return the node where the cycle begins.
 * If there is no cycle, return null.
 *
 * Example:
 * Input  : 3 -> 2 -> 0 -> -4
 *                 ^         |
 *                 |_________|
 * Output : Node with value 2
 *
 * Input  : 1 -> 2 -> null
 * Output : null
 */
public class LinkedListCycleII {

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
        head.next.next.next.next = head.next; // cycle at node 2

        // ---------- Optimal Approach ----------
        ListNode optimalResult = detectCycleOptimal(head);
        System.out.println("Cycle starts at (Optimal): " +
                (optimalResult != null ? optimalResult.val : "null"));

        // ---------- Brute Force Approach ----------
        ListNode bruteResult = detectCycleBrute(head);
        System.out.println("Cycle starts at (Brute Force): " +
                (bruteResult != null ? bruteResult.val : "null"));
    }

    /**
     * Algorithm 1: Optimal Fast & Slow Pointer Approach
     * -------------------------------------------------
     * Idea:
     * 1. Use Floyd’s Cycle Detection to check if a cycle exists.
     * 2. When slow == fast, reset one pointer to head.
     * 3. Move both one step at a time; the node where they meet is the cycle start.
     *
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     */
    public static ListNode detectCycleOptimal(ListNode head) {
        // Edge case: empty list or single node cannot form a cycle
        if (head == null || head.next == null) return null;

        ListNode slow = head, fast = head;
        boolean hasCycle = false;

        // Step 1: Detect cycle using fast/slow pointer
        while (fast != null && fast.next != null) {
            slow = slow.next;       // move slow one step
            fast = fast.next.next;  // move fast two steps
            if (slow == fast) {     // pointers meet → cycle detected
                hasCycle = true;
                break;
            }
        }

        // If no cycle, return null
        if (!hasCycle) return null;

        // Step 2: Reset one pointer to head
        slow = head;

        // Step 3: Move both one step at a time
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }

        // Step 4: Meeting point is the cycle start
        return slow;
    }


    /**
     * Algorithm 2: Brute Force HashMap Approach
     * -----------------------------------------
     * Idea:
     * Store visited nodes in a HashMap.
     * If a node is revisited, that node is the cycle start.
     *
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     */
    public static ListNode detectCycleBrute(ListNode head) {
        java.util.HashSet<ListNode> visited = new java.util.HashSet<>();
        ListNode curr = head;
        while (curr != null) {
            if (visited.contains(curr)) return curr;
            visited.add(curr);
            curr = curr.next;
        }
        return null;
    }
}
