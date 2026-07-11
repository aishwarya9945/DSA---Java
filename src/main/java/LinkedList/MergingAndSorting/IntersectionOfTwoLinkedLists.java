package LinkedList.MergingAndSorting;

import LinkedList.ListNode;

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;              // value stored in node
 *     ListNode next;        // pointer to next node
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class IntersectionOfTwoLinkedLists {

    /**
     * Algorithm: Two Pointers Swap Tracks
     * -----------------------------------
     * Mnemonic: "Swap tracks → Meet at intersection."
     *
     * Idea:
     * - Use two pointers (pA, pB) starting at headA and headB.
     * - Traverse each list; when a pointer reaches null, redirect it to the other list’s head.
     * - If the lists intersect, the pointers will meet at the intersection node.
     * - If not, both will eventually become null at the same time.
     *
     * Time Complexity: O(m+n)   (m and n are lengths of the two lists)
     * Space Complexity: O(1)    (constant extra space)
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) return null; // edge case: one list empty

        ListNode pA = headA; // pointer for list A
        ListNode pB = headB; // pointer for list B

        // Traverse until both pointers meet or both become null
        while (pA != pB) {
            // If pA reaches end, redirect to headB; else move forward
            pA = (pA == null) ? headB : pA.next;

            // If pB reaches end, redirect to headA; else move forward
            pB = (pB == null) ? headA : pB.next;
        }

        // Either intersection node or null if no intersection
        return pA;
    }

    /**
     * Algorithm 1: Brute Force
     * ------------------------
     * Idea:
     * - For each node in list A, traverse list B fully.
     * - If any node matches (same reference), return intersection.
     *
     * Time Complexity: O(m * n)   (m = length of list A, n = length of list B)
     * Space Complexity: O(1)      (no extra space)
     */
    public ListNode getIntersectionNodeBruteForce(ListNode headA, ListNode headB) {
        for (ListNode a = headA; a != null; a = a.next) {
            for (ListNode b = headB; b != null; b = b.next) {
                if (a == b) return a; // intersection found
            }
        }
        return null; // no intersection
    }

    /**
     * Main method for testing
     */
    public static void main(String[] args) {
        // Create sample linked lists with intersection
        ListNode common = new ListNode(8);
        common.next = new ListNode(10);

        ListNode headA = new ListNode(3);
        headA.next = new ListNode(7);
        headA.next.next = common; // intersection at node 8

        ListNode headB = new ListNode(99);
        headB.next = new ListNode(1);
        headB.next.next = common; // intersection at node 8

        IntersectionOfTwoLinkedLists solver = new IntersectionOfTwoLinkedLists();

        // Test brute force
        ListNode result1 = solver.getIntersectionNodeBruteForce(headA, headB);
        System.out.println("Brute Force Intersection: " + (result1 != null ? result1.val : "No Intersection"));

        // Test optimal two pointers
        ListNode result2 = solver.getIntersectionNode(headA, headB);
        System.out.println("Two Pointers Intersection: " + (result2 != null ? result2.val : "No Intersection"));
    }
}

