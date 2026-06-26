package Trees;

import java.util.*;

/**
 * Problem: 653. Two Sum IV - Input is a BST
 * -----------------------------------------
 * Given the root of a Binary Search Tree (BST) and an integer k,
 * return true if there exist two elements in the BST such that
 * their sum is equal to k, otherwise return false.
 *
 * Example 1:
 * Input  : root = [5,3,6,2,4,null,7], k = 9
 * Output : true
 *
 * Example 2:
 * Input  : root = [5,3,6,2,4,null,7], k = 28
 * Output : false
 *
 * Constraints:
 * - Number of nodes: [1, 10^4]
 * - Node values: [-10^4, 10^4]
 * - k: [-10^5, 10^5]
 *
 * Approaches Implemented:
 * 1. HashSet + DFS (O(n), O(n))
 * 2. Inorder + Two Pointers (O(n), O(n))
 */
public class TwoSumBST {

    // ---------- Approach 1: HashSet + DFS ----------
    public boolean findTargetDFS(TreeNode root, int k) {
        HashSet<Integer> set = new HashSet<>();
        return dfs(root, k, set);
    }

    private boolean dfs(TreeNode node, int k, HashSet<Integer> set) {
        if (node == null) return false;

        if (set.contains(k - node.val)) return true;
        set.add(node.val);

        return dfs(node.left, k, set) || dfs(node.right, k, set);
    }

    // ---------- Approach 2: Inorder + Two Pointers ----------
    public boolean findTargetInorder(TreeNode root, int k) {
        List<Integer> nums = new ArrayList<>();
        inorder(root, nums);

        int left = 0, right = nums.size() - 1;
        while (left < right) {
            int sum = nums.get(left) + nums.get(right);
            if (sum == k) return true;
            if (sum < k) {
                left++;
            } else {
                right--;
            }
        }
        return false;
    }


    private void inorder(TreeNode node, List<Integer> nums) {
        if (node == null) return;
        inorder(node.left, nums);
        nums.add(node.val);
        inorder(node.right, nums);
    }
}

/**
 * Definition for a binary tree node.
 */
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
