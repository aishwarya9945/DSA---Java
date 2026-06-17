package LinkedList.PointerBasics;

/* Problem Recap
Reverse a linked list from position m to n, in one pass.

# Memory Hook (Conceptual Trick)
Think of it as:

Skip → Walk to node before m.
Detach → Mark the start of the reversal.
Flip → Reverse nodes between m and n.
Reconnect → Attach the reversed sublist back.

# Mnemonic: “Skip, Detach, Flip, Reconnect” → SDFR.

Example Walkthrough
List: 1 → 2 → 3 → 4 → 5, reverse from 2 to 4.
Skip: stop at node 1.
Detach: mark node 2 as start.
Flip: reverse 2 → 3 → 4 into 4 → 3 → 2.
Reconnect: attach 1 → 4 → 3 → 2 → 5.

This way, you can mentally anchor the process without re-deriving it each time.*/

/**
 * Program to reverse a sublist of a singly linked list between positions left and right.
 * Implements:
 * 1. Optimal Iterative Approach (O(n), O(1))
 * 2. Optimal Recursive Approach (O(n), O(n) stack space)
 * 3. Brute Force Approach using auxiliary array (O(n), O(n))
 *
 * Problem:
 * Given the head of a singly linked list and two integers left and right (1-indexed),
 * reverse the nodes from position left to right, and return the modified list.
 *
 * Example:
 * Input  : 1 -> 2 -> 3 -> 4 -> 5, left = 2, right = 4
 * Output : 1 -> 4 -> 3 -> 2 -> 5
 */
public class ReverseLinkedListII {

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

        int left = 2, right = 4;

        // ---------- Optimal Iterative Approach ----------
        ListNode iterativeResult = reverseBetweenIterative(head, left, right);
        System.out.print("Reversed Sublist (Iterative Optimal): ");
        printList(iterativeResult);

        // ---------- Optimal Recursive Approach ----------
        ListNode recursiveResult = reverseBetweenRecursive(iterativeResult, left, right);
        System.out.print("Reversed Sublist (Recursive Optimal): ");
        printList(recursiveResult);

        // ---------- Brute Force Approach ----------
        ListNode bruteResult = reverseBetweenBrute(recursiveResult, left, right);
        System.out.print("Reversed Sublist (Brute Force): ");
        printList(bruteResult);
    }

    /**
     * Algorithm 1: Optimal Iterative Approach
     * ---------------------------------------
     * Idea:
     * Use dummy node, move to left-1 position, reverse sublist in place, reconnect.
     *
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     */
    public static ListNode reverseBetweenIterative(ListNode head, int left, int right) {
        ListNode dummy = new ListNode(0); // dummy node to simplify edge cases
        dummy.next = head;
        ListNode prev = dummy;

        for (int i = 1; i < left; i++) {  // 1. Skip: move prev to node before 'left'
            prev = prev.next;
        }

        ListNode curr = prev.next; // 2. Detach: curr points to start of sublist

        for (int i = 0; i < right - left; i++) { // 3. Flip: reverse nodes between left and right
            ListNode next = curr.next;
            curr.next = next.next;
            next.next = prev.next;
            prev.next = next;
        }

        return dummy.next; // 4. Reconnect: dummy.next is the new head
    }

    /**
     * Algorithm 2: Optimal Recursive Approach
     * ---------------------------------------
     * Idea:
     * Recursively reverse the first (right-left+1) nodes starting from left.
     *
     * Time Complexity: O(n)
     * Space Complexity: O(n) recursion stack
     */
    static ListNode successor = null; // to reconnect tail

    public static ListNode reverseBetweenRecursive(ListNode head, int left, int right) {
        if (left == 1) {
            return reverseN(head, right);
        }
        head.next = reverseBetweenRecursive(head.next, left - 1, right - 1);
        return head;
    }

    private static ListNode reverseN(ListNode head, int n) {
        if (n == 1) {
            successor = head.next;
            return head;
        }
        ListNode newHead = reverseN(head.next, n - 1);
        head.next.next = head;
        head.next = successor;
        return newHead;
    }

    /**
     * Algorithm 3: Brute Force Approach
     * ---------------------------------
     * Idea:
     * Store nodes in an array, reverse the sublist between left and right, rebuild list.
     *
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     */
    public static ListNode reverseBetweenBrute(ListNode head, int left, int right) {
        java.util.ArrayList<ListNode> nodes = new java.util.ArrayList<>();
        ListNode curr = head;
        while (curr != null) {
            nodes.add(curr);
            curr = curr.next;
        }

        while (left < right) {
            ListNode temp = nodes.get(left - 1);
            nodes.set(left - 1, nodes.get(right - 1));
            nodes.set(right - 1, temp);
            left++;
            right--;
        }

        for (int i = 0; i < nodes.size() - 1; i++) {
            nodes.get(i).next = nodes.get(i + 1);
        }
        nodes.get(nodes.size() - 1).next = null;
        return nodes.isEmpty() ? null : nodes.get(0);
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
