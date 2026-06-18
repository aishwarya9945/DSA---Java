package LinkedList.MergingAndSorting;

import LinkedList.ListNode;

/**
 * Problem: Sort List (LeetCode 148)
 * ---------------------------------
 * Given the head of a linked list, sort it in ascending order.
 *
 * Constraint: Must run in O(n log n) time and use constant space.
 */
public class SortList {

    /**
     * Algorithm 1: Optimal Merge Sort
     * -------------------------------
     * Mnemonic: "Split → Sort → Merge."
     *
     * Steps:
     * 1. Base case: if list is empty or has one node, return head.
     * 2. Use slow/fast pointers to find the middle of the list.
     * 3. Split the list into two halves.
     * 4. Recursively sort each half.
     * 5. Merge the two sorted halves.
     *
     * Time Complexity: O(n log n)   (divide and merge steps)
     * Space Complexity: O(1)        (in-place merge, recursion stack ignored)
     */
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) return head; // base case

        // Step 2: Find middle using slow/fast pointers
        ListNode slow = head, fast = head, prev = null;
        while (fast != null && fast.next != null) {
            prev = slow;
            slow = slow.next;
            fast = fast.next.next;
        }

        // Step 3: Split into two halves
        prev.next = null;

        // Step 4: Recursively sort each half
        ListNode left = sortList(head);
        ListNode right = sortList(slow);

        // Step 5: Merge sorted halves
        return merge(left, right);
    }

    // Helper: Merge two sorted linked lists
    private ListNode merge(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;

        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                tail.next = l1;
                l1 = l1.next;
            } else {
                tail.next = l2;
                l2 = l2.next;
            }
            tail = tail.next;
        }

        tail.next = (l1 != null) ? l1 : l2;
        return dummy.next;
    }

    /**
     * Algorithm 2: Brute Force (Array Conversion)
     * -------------------------------------------
     * Mnemonic: "Copy → Sort → Rebuild."
     *
     * Steps:
     * 1. Traverse the linked list and copy all values into an ArrayList.
     * 2. Sort the ArrayList using Collections.sort().
     * 3. Rebuild a new linked list from the sorted values.
     *
     * Time Complexity: O(n log n)   (array sort)
     * Space Complexity: O(n)        (array storage)
     */
    public ListNode sortListBrute(ListNode head) {
        if (head == null) return null;

        java.util.ArrayList<Integer> values = new java.util.ArrayList<>();
        ListNode temp = head;

        // Step 1: Copy values into array
        while (temp != null) {
            values.add(temp.val);
            temp = temp.next;
        }

        // Step 2: Sort array
        java.util.Collections.sort(values);

        // Step 3: Rebuild linked list
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;
        for (int v : values) {
            tail.next = new ListNode(v);
            tail = tail.next;
        }

        return dummy.next;
    }
}
