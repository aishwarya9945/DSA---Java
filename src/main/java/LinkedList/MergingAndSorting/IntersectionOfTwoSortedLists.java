package LinkedList.MergingAndSorting;

import LinkedList.Node;   // GFG-style Node with `data`

import java.util.HashSet;

/**
 * Intersection of Two Sorted Lists (GFG variant)
 * ----------------------------------------------
 * Given two sorted linked lists, return a new list containing only the common elements.
 *
 * Example:
 * head1 = 1 -> 2 -> 3 -> 4 -> 6
 * head2 = 2 -> 4 -> 6 -> 8
 * Output = 2 -> 4 -> 6
 */
public class IntersectionOfTwoSortedLists {

    /**
     * Algorithm 1: Optimal Merge Traversal
     * ------------------------------------
     * Mnemonic: "Compare → Advance → Collect."
     *
     * Steps:
     * 1. Initialize a dummy node and tail pointer for result list.
     * 2. Traverse both lists simultaneously:
     *    - If values are equal → add to result, advance both.
     *    - If head1.data < head2.data → advance head1.
     *    - Else advance head2.
     * 3. Continue until one list is exhausted.
     * 4. Return result list starting from dummy.next.
     *
     * Time Complexity: O(m+n)   (each list traversed once)
     * Space Complexity: O(min(m,n)) (new list for intersection nodes)
     */
    public static Node findIntersection(Node head1, Node head2) {
        Node dummy = new Node(0);
        Node tail = dummy;

        while (head1 != null && head2 != null) {
            if (head1.data == head2.data) {
                tail.next = new Node(head1.data);
                tail = tail.next;
                head1 = head1.next;
                head2 = head2.next;
            } else if (head1.data < head2.data) {
                head1 = head1.next;
            } else {
                head2 = head2.next;
            }
        }

        return dummy.next;
    }

    /**
     * Algorithm 2: Brute Force (HashSet)
     * ----------------------------------
     * Mnemonic: "Store → Check → Collect."
     *
     * Steps:
     * 1. Traverse head1 and store all values in a HashSet.
     * 2. Initialize dummy node and tail pointer for result list.
     * 3. Traverse head2:
     *    - If head2.data exists in set → add to result.
     *    - Advance head2.
     * 4. Return result list starting from dummy.next.
     *
     * Time Complexity: O(m+n)   (store list1, then traverse list2)
     * Space Complexity: O(m)    (HashSet stores elements of list1)
     */
    public static Node findIntersectionBrute(Node head1, Node head2) {
        HashSet<Integer> set = new HashSet<>();
        Node temp = head1;

        while (temp != null) {
            set.add(temp.data);
            temp = temp.next;
        }

        Node dummy = new Node(0);
        Node tail = dummy;

        while (head2 != null) {
            if (set.contains(head2.data)) {
                tail.next = new Node(head2.data);
                tail = tail.next;
            }
            head2 = head2.next;
        }

        return dummy.next;
    }
}
