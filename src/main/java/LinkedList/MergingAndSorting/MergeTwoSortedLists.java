package LinkedList.MergingAndSorting;

import LinkedList.ListNode;

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;              // value stored in node
 *     ListNode next;        // pointer to next node
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
public class MergeTwoSortedLists {

    /**
     * Algorithm 1: Optimal Iterative Approach
     * ---------------------------------------
     * Mnemonic: "Dummy → Compare → Attach → Advance."
     *
     * Steps:
     * 1. Create a dummy node to simplify edge cases.
     * 2. Use a current pointer to build the merged list.
     * 3. Compare list1 and list2 nodes:
     *    - Attach the smaller node to current.next.
     *    - Advance the pointer of the list whose node was chosen.
     * 4. Move current forward.
     * 5. When one list is exhausted, attach the remaining nodes.
     * 6. Return dummy.next (skip dummy).
     *
     * Time Complexity: O(m+n)  (m and n are lengths of list1 and list2)
     * Space Complexity: O(1)   (in-place merge, no extra structures)
     */
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode dummy = new ListNode(0);   // dummy node to anchor merged list
        ListNode current = dummy;           // pointer to build merged list

        // Traverse both lists until one becomes null
        while (list1 != null && list2 != null) {
            if (list1.val <= list2.val) {   // if list1’s value is smaller
                current.next = list1;       // attach list1 node
                list1 = list1.next;         // advance list1
            } else {
                current.next = list2;       // attach list2 node
                list2 = list2.next;         // advance list2
            }
            current = current.next;         // move current forward
        }

        // Attach whichever list still has remaining nodes
        if (list1 != null) current.next = list1;
        if (list2 != null) current.next = list2;

        return dummy.next;                  // return merged list (skip dummy)
    }

    /**
     * Algorithm 2: Recursive Approach (Brute Style)
     * ---------------------------------------------
     * Mnemonic: "Pick smaller → Recurse → Stitch."
     *
     * Steps:
     * 1. Base case: if one list is null, return the other.
     * 2. Compare heads of both lists.
     * 3. Choose the smaller node as head.
     * 4. Recursively merge the rest.
     * 5. Return the chosen head.
     *
     * Time Complexity: O(m+n)
     * Space Complexity: O(m+n) (due to recursion stack)
     */
    public ListNode mergeTwoListsRecursive(ListNode list1, ListNode list2) {
        if (list1 == null) return list2;    // if list1 empty, return list2
        if (list2 == null) return list1;    // if list2 empty, return list1

        if (list1.val <= list2.val) {       // if list1 smaller
            list1.next = mergeTwoListsRecursive(list1.next, list2); // recurse
            return list1;                   // return list1 as head
        } else {
            list2.next = mergeTwoListsRecursive(list1, list2.next); // recurse
            return list2;                   // return list2 as head
        }
    }
}
