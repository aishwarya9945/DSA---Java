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
}
