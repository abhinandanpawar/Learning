package com.example;

/**
 * A simple class to represent a node in a binary tree.
 * For simplicity, we are using public fields. In a real-world application,
 * you might prefer private fields with getters and setters.
 */
public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int val) {
        this.val = val;
        this.left = null;
        this.right = null;
    }
}
