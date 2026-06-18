package LinkedList.MergingAndSorting;

import LinkedList.ListNode;

/**
 * Problem: Quick Sort on Linked List (GFG variant)
 * -----------------------------------------------
 * Sort a linked list using QuickSort algorithm.
 */
public class QuickSortLinkedList {

    /**
     * Algorithm 1: Optimal QuickSort (Partition + Recursion)
     * ------------------------------------------------------
     * Mnemonic: "Partition → Recurse → Stitch."
     *
     * Steps:
     * 1. Choose the last node as pivot.
     * 2. Partition the list into two sublists:
     *    - Nodes less than pivot.
     *    - Nodes greater than or equal to pivot.
     * 3. Recursively sort left and right sublists.
     * 4. Concatenate left + pivot + right.
     *
     * Time Complexity: O(n log n) average, O(n^2) worst-case (like array QuickSort).
     * Space Complexity: O(log n) recursion stack.
     */
    public ListNode quickSort(ListNode head) {
        if (head == null || head.next == null) return head;
        return quickSortRecur(head, getTail(head));
    }

    private ListNode quickSortRecur(ListNode head, ListNode end) {
        if (head == null || head == end) return head;

        ListNode newHead = null, newEnd = null;
        ListNode pivot = partition(head, end, newHead, newEnd);

        // If pivot is the smallest element
        if (newHead != pivot) {
            // Sort left side
            ListNode temp = newHead;
            while (temp.next != pivot) temp = temp.next;
            temp.next = null;

            newHead = quickSortRecur(newHead, temp);

            // Connect left sorted list with pivot
            temp = getTail(newHead);
            temp.next = pivot;
        }

        // Sort right side
        pivot.next = quickSortRecur(pivot.next, newEnd);

        return newHead;
    }

    // Partition function
    private ListNode partition(ListNode head, ListNode end, ListNode newHead, ListNode newEnd) {
        ListNode pivot = end;
        ListNode prev = null, cur = head, tail = pivot;

        while (cur != pivot) {
            if (cur.val < pivot.val) {
                if (newHead == null) newHead = cur;
                prev = cur;
                cur = cur.next;
            } else {
                if (prev != null) prev.next = cur.next;
                ListNode tmp = cur.next;
                cur.next = null;
                tail.next = cur;
                tail = cur;
                cur = tmp;
            }
        }

        if (newHead == null) newHead = pivot;
        newEnd = tail;

        return pivot;
    }

    private ListNode getTail(ListNode node) {
        while (node != null && node.next != null) node = node.next;
        return node;
    }

    /**
     * Algorithm 2: Brute Force (Array Conversion)
     * -------------------------------------------
     * Mnemonic: "Copy → Sort → Rebuild."
     *
     * Steps:
     * 1. Copy all values into an ArrayList.
     * 2. Apply QuickSort (or Collections.sort).
     * 3. Rebuild linked list from sorted values.
     *
     * Time Complexity: O(n log n) average.
     * Space Complexity: O(n) (array storage).
     */
    public ListNode quickSortBrute(ListNode head) {
        if (head == null) return null;

        java.util.ArrayList<Integer> values = new java.util.ArrayList<>();
        ListNode temp = head;

        while (temp != null) {
            values.add(temp.val);
            temp = temp.next;
        }

        // QuickSort via Collections.sort (internally uses TimSort)
        java.util.Collections.sort(values);

        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;
        for (int v : values) {
            tail.next = new ListNode(v);
            tail = tail.next;
        }

        return dummy.next;
    }
}
