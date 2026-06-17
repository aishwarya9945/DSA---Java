package LinkedList.PointerBasics;

/*
Category: PointerBasics → “reverse dance” family
Pattern: “Prev–Curr–Next dance”
Mnemonic: “Flip, March, Stitch.”

# Algorithm Mental Steps:

1.Initialize prev = null, curr = head.
2.While curr != null:
3.Save next = curr.next.
4.Reverse link: curr.next = prev.
5.Advance: prev = curr, curr = next.
6.Return prev as new head.

# Why it sticks
Flip → reverse the pointer.
March → move forward.
Stitch → connect the reversed chain.

# This is the same “reverse blocks” family as Reverse II
 and Reverse k‑Group, so once you remember the dance, you can extend it to those variants.*/

/**
 * Program to reverse a singly linked list.
 * Implements:
 * 1. Optimal Iterative Approach (O(n), O(1))
 * 2. Optimal Recursive Approach (O(n), O(n) stack space)
 * 3. Brute Force Approach using auxiliary array (O(n), O(n))
 *
 * Problem:
 * Given the head of a singly linked list, reverse the list and return the new head.
 *
 * Example:
 * Input  : 1 -> 2 -> 3 -> 4 -> 5
 * Output : 5 -> 4 -> 3 -> 2 -> 1
 */
public class ReverseLinkedList {

    // Definition for singly-linked list.
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

        // ---------- Optimal Iterative Approach ----------
        ListNode iterativeResult = reverseIterative(head);
        System.out.print("Reversed (Iterative Optimal): ");
        printList(iterativeResult);

        // ---------- Optimal Recursive Approach ----------
        ListNode recursiveResult = reverseRecursive(iterativeResult); // reverse back
        System.out.print("Reversed (Recursive Optimal): ");
        printList(recursiveResult);

        // ---------- Brute Force Approach ----------
        ListNode bruteResult = reverseBrute(recursiveResult);
        System.out.print("Reversed (Brute Force): ");
        printList(bruteResult);
    }

    /**
     * Algorithm 1: Optimal Iterative Approach
     * ---------------------------------------
     * Idea:
     * Use three pointers (prev, curr, next) to reverse links one by one.
     *
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     */
    public static ListNode reverseIterative(ListNode head) {
        ListNode prev = null, curr = head;

        while (curr != null) {
            ListNode next = curr.next;   // 1. Save next
            curr.next = prev;            // 2. Flip
            prev = curr;                 // 3. March
            curr = next;                 // 4. March
        }
        return prev;                     // 5. Reconnect
    }

    /**
     * Algorithm 2: Optimal Recursive Approach
     * ---------------------------------------
     * Idea:
     * Recursively reverse the rest of the list, then fix current node’s pointer.
     *
     * Time Complexity: O(n)
     * Space Complexity: O(n) due to recursion stack
     */
    public static ListNode reverseRecursive(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode newHead = reverseRecursive(head.next);
        head.next.next = head;
        head.next = null;
        return newHead;
    }

    /**
     * Algorithm 3: Brute Force Approach
     * ---------------------------------
     * Idea:
     * Store nodes in an array, then rebuild the list in reverse order.
     *
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     */
    public static ListNode reverseBrute(ListNode head) {
        java.util.ArrayList<ListNode> nodes = new java.util.ArrayList<>();
        ListNode curr = head;
        while (curr != null) {
            nodes.add(curr);
            curr = curr.next;
        }
        for (int i = nodes.size() - 1; i > 0; i--) {
            nodes.get(i).next = nodes.get(i - 1);
        }
        if (!nodes.isEmpty()) {
            nodes.get(0).next = null;
            return nodes.get(nodes.size() - 1);
        }
        return null;
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
