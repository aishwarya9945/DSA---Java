package LinkedList.MergingAndSorting; /**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;              // value stored in node
 *     ListNode next;        // pointer to next node
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */

import LinkedList.ListNode;

import java.util.PriorityQueue;

public class MergeKSortedLists {

    /**
     * Algorithm 1: Min Heap Approach (Optimal)
     * ----------------------------------------
     * Mnemonic: "Heapify → Extract → Attach."
     *
     * Steps:
     * 1. Push all list heads into a min heap.
     * 2. Extract the smallest node, attach to result.
     * 3. Push its next node into heap.
     * 4. Repeat until heap is empty.
     *
     * Time Complexity: O(N log k)  (N = total nodes, k = number of lists)
     * Space Complexity: O(k)       (heap stores at most k nodes)
     */
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null; // edge case

        // Priority Queue (min heap) to hold list nodes, sorted by value
        PriorityQueue<ListNode> minHeap = new PriorityQueue<>(
                (a, b) -> a.val - b.val
        );

        // Step 1: Add head of each list to the heap
        for (ListNode node : lists) {
            if (node != null) {
                minHeap.add(node);
            }
        }

        ListNode dummy = new ListNode(0);   // dummy node to anchor merged list
        ListNode current = dummy;           // pointer to build merged list

        // Step 2: Extract min, attach, push next
        while (!minHeap.isEmpty()) {
            ListNode smallest = minHeap.poll(); // extract smallest node
            current.next = smallest;            // attach to result
            current = current.next;             // advance current pointer

            // Step 3: If there is a next node in the list, add it to heap
            if (smallest.next != null) {
                minHeap.add(smallest.next);
            }
        }

        return dummy.next; // return merged list (skip dummy)
    }

    /**
     * Algorithm 2: Brute Force Pairwise Merge
     * ---------------------------------------
     * Mnemonic: "Merge one by one."
     *
     * Steps:
     * 1. Start with an empty result list.
     * 2. Merge result with each list sequentially using two-list merge.
     * 3. Continue until all lists are merged.
     *
     * Time Complexity: O(kN)   (merge each list one by one)
     * Space Complexity: O(1)   (in-place merge)
     */
    public ListNode mergeKListsBrute(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;

        ListNode result = null; // start with empty result list

        // Merge each list into result one by one
        for (ListNode list : lists) {
            result = mergeTwoLists(result, list);
        }

        return result;
    }

    // Helper: merge two sorted lists (same as LeetCode 21)
    private ListNode mergeTwoLists(ListNode l1, ListNode l2) {
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
}
