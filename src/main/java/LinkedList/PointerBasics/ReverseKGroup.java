package LinkedList.PointerBasics;

/**
 * Program to reverse nodes of a linked list in groups of size k.
 * Implements:
 * 1. Optimal Iterative Approach (O(n), O(1))
 * 2. Optimal Recursive Approach (O(n), O(n/k) stack frames)
 * 3. Brute Force Approach using auxiliary array (O(n), O(n))
 *
 * Problem:
 * Given the head of a linked list, reverse the nodes of the list k at a time,
 * and return the modified list. If the number of nodes is not a multiple of k,
 * leave the last nodes as is.
 *
 * Example:
 * Input  : 1 -> 2 -> 3 -> 4 -> 5, k = 2
 * Output : 2 -> 1 -> 4 -> 3 -> 5
 *
 * Input  : 1 -> 2 -> 3 -> 4 -> 5, k = 3
 * Output : 3 -> 2 -> 1 -> 4 -> 5
 */

/* How to Recall:
Category: PointerBasics → “reverse blocks” family (with Reverse Linked List, Reverse II, Reorder List).
Pattern: “Check size → reverse block → reconnect.”
Mnemonic: “Group, Flip, Stitch.”

## Memory Hooks

#Check block size first
Always verify there are at least k nodes ahead.
If not, return as‑is.
→ Hook: “Don’t start a dance if the group is too small.”

#Reverse block of k nodes
Use the prev–curr–next dance inside the block.
After reversing, reconnect with the rest.
→ Hook: “Reverse in chunks, stitch back.”

#Iterative vs Recursive
Iterative: dummy + prev pointer, loop through blocks.
Recursive: reverse first k, then call recursively on the rest.
→ Hook: “Iterative stitches, recursive chains.”*/

public class ReverseKGroup {

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

        int k = 2;

        // ---------- Optimal Iterative Approach ----------
        ListNode iterativeResult = reverseKGroupIterative(head, k);
        System.out.print("Reversed in k-Group (Iterative Optimal): ");
        printList(iterativeResult);

        // ---------- Optimal Recursive Approach ----------
        ListNode recursiveResult = reverseKGroupRecursive(iterativeResult, k);
        System.out.print("Reversed in k-Group (Recursive Optimal): ");
        printList(recursiveResult);

        // ---------- Brute Force Approach ----------
        ListNode bruteResult = reverseKGroupBrute(recursiveResult, k);
        System.out.print("Reversed in k-Group (Brute Force): ");
        printList(bruteResult);
    }

    /**
     * Algorithm 1: Optimal Iterative Approach
     * ---------------------------------------
     * Idea:
     * Use dummy + prev + curr pointers. Reverse each block of k nodes in place.
     *
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     */
    public static ListNode reverseKGroupIterative(ListNode head, int k) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prev = dummy, curr = head;

        while (true) {
            // Check k nodes ahead → Make sure there are at least k nodes ahead.
            ListNode check = curr;

            for (int i = 0; i < k; i++) {
                if (check == null) return dummy.next;
                check = check.next;
            } // If not enough, stop.

            // Flip → Reverse exactly k nodes.
            //This is the same “flip” logic as the full reverse, but limited to k iterations.
            ListNode tail = curr;

            ListNode prevNode = null;
            for (int i = 0; i < k; i++) {
                ListNode next = curr.next;
                curr.next = prevNode;
                prevNode = curr;
                curr = next;
            }

           // Reconnect → Stitch the reversed block back into the list.
            prev.next = prevNode;
            tail.next = curr;
            prev = tail;
        }
    }

    /* notes :
    You just finished reversing a block of k nodes.
prevNode is the new head of that reversed block.
tail is the original head of the block (which after reversal becomes the tail).
curr is the pointer to the next node after the block (the start of the next segment).

prev is the pointer to the node before the block (used to stitch blocks together).
# What each line does
prev.next = prevNode;
Connects the node before the block (prev) to the new head of the reversed block (prevNode).
→ Hook: “Stitch the block back in.”

tail.next = curr;
Connects the tail of the reversed block (tail) to the next segment (curr).
→ Hook: “Attach the tail to the rest.”

prev = tail;
Moves prev forward to the end of the reversed block, so it’s ready to stitch the next block.
→ Hook: “Advance the stitch pointer.”*/


    /**
     * Algorithm 2: Optimal Recursive Approach
     * ---------------------------------------
     * Idea:
     * Recursively reverse each block of k nodes, then connect with the rest.
     *
     * Time Complexity: O(n)
     * Space Complexity: O(n/k) recursion stack
     */
    public static ListNode reverseKGroupRecursive(ListNode head, int k) {
        ListNode check = head;
        for (int i = 0; i < k; i++) {
            if (check == null) return head;
            check = check.next;
        }

        ListNode prev = null, curr = head;
        for (int i = 0; i < k; i++) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }

      // Connect
        head.next = reverseKGroupRecursive(curr, k);
        return prev;
    }

    /**
     * Algorithm 3: Brute Force Approach
     * ---------------------------------
     * Idea:
     * Store nodes in an array, then rebuild the list reversing each block of k.
     *
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     */
    public static ListNode reverseKGroupBrute(ListNode head, int k) {
        java.util.ArrayList<ListNode> nodes = new java.util.ArrayList<>();
        ListNode curr = head;
        while (curr != null) {
            nodes.add(curr);
            curr = curr.next;
        }

        for (int i = 0; i + k <= nodes.size(); i += k) {
            for (int l = 0, r = k - 1; l < r; l++, r--) {
                ListNode temp = nodes.get(i + l);
                nodes.set(i + l, nodes.get(i + r));
                nodes.set(i + r, temp);
            }
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
