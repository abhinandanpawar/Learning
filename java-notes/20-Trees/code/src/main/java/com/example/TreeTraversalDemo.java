package com.example;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

/**
 * Main application to demonstrate tree traversal algorithms.
 */
public class TreeTraversalDemo {

    public static void main(String[] args) {
        // --- Build the Example Tree ---
        //      4
        //     / \
        //    2   7
        //   / \ / \
        //  1  3 6  9
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(2);
        root.right = new TreeNode(7);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(3);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(9);

        System.out.println("--- Tree Traversal Showcase ---");

        // --- Pre-order Traversal ---
        List<Integer> preOrderRecursive = new ArrayList<>();
        preOrderRecursive(root, preOrderRecursive);
        System.out.println("Pre-order (Recursive): " + preOrderRecursive);
        System.out.println("Pre-order (Iterative): " + preOrderIterative(root));

        // --- In-order Traversal ---
        List<Integer> inOrderRecursive = new ArrayList<>();
        inOrderRecursive(root, inOrderRecursive);
        System.out.println("In-order (Recursive):  " + inOrderRecursive);
        System.out.println("In-order (Iterative):  " + inOrderIterative(root));

        // --- Post-order Traversal ---
        List<Integer> postOrderRecursive = new ArrayList<>();
        postOrderRecursive(root, postOrderRecursive);
        System.out.println("Post-order (Recursive):" + postOrderRecursive);
        // Iterative post-order is more complex, so we'll just show one implementation.

        // --- Level-order Traversal ---
        System.out.println("Level-order (Iterative):" + levelOrder(root));
    }

    // --- Recursive DFS Implementations ---

    public static void preOrderRecursive(TreeNode node, List<Integer> result) {
        if (node == null) return;
        result.add(node.val);        // Visit root
        preOrderRecursive(node.left, result);  // Visit left
        preOrderRecursive(node.right, result); // Visit right
    }

    public static void inOrderRecursive(TreeNode node, List<Integer> result) {
        if (node == null) return;
        inOrderRecursive(node.left, result);   // Visit left
        result.add(node.val);         // Visit root
        inOrderRecursive(node.right, result);  // Visit right
    }

    public static void postOrderRecursive(TreeNode node, List<Integer> result) {
        if (node == null) return;
        postOrderRecursive(node.left, result);  // Visit left
        postOrderRecursive(node.right, result); // Visit right
        result.add(node.val);         // Visit root
    }

    // --- Iterative DFS Implementations ---

    public static List<Integer> preOrderIterative(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) return result;
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            result.add(node.val);
            if (node.right != null) stack.push(node.right);
            if (node.left != null) stack.push(node.left);
        }
        return result;
    }

    public static List<Integer> inOrderIterative(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode curr = root;
        while (curr != null || !stack.isEmpty()) {
            while (curr != null) {
                stack.push(curr);
                curr = curr.left;
            }
            curr = stack.pop();
            result.add(curr.val);
            curr = curr.right;
        }
        return result;
    }

    // --- Iterative BFS Implementation ---

    public static List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            List<Integer> currentLevel = new ArrayList<>();
            for (int i = 0; i < levelSize; i++) {
                TreeNode node = queue.poll();
                currentLevel.add(node.val);
                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }
            result.add(currentLevel);
        }
        return result;
    }
}
