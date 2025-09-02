# 20 - Trees

Trees are a fundamental data structure in computer science. They are used to represent hierarchical data, such as the file system on your computer or the structure of an HTML document.

## Introduction to Trees
A tree is a collection of nodes connected by edges. Each node contains a value and a list of references to other nodes (its children). The top-most node in a tree is called the root. Nodes that have no children are called leaf nodes.

### Binary Trees
A binary tree is a tree in which each node has at most two children, which are referred to as the left child and the right child.

## Binary Tree Traversal
Traversal is the process of visiting each node in a tree exactly once. There are several ways to traverse a binary tree.

### Preorder Traversal (Root, Left, Right)
In a preorder traversal, we visit the root node first, then recursively do a preorder traversal of the left subtree, followed by a recursive preorder traversal of the right subtree.

**Example:**
```java
public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

public class Solution {
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) return result;
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.empty()) {
            TreeNode n = stack.pop();
            result.add(n.val);
            if (n.right != null) {
                stack.push(n.right);
            }
            if (n.left != null) {
                stack.push(n.left);
            }
        }
        return result;
    }
}
```

### Inorder Traversal (Left, Root, Right)
In an inorder traversal, we recursively do an inorder traversal of the left subtree, then visit the root node, and finally do a recursive inorder traversal of the right subtree. For a binary search tree, an inorder traversal visits the nodes in ascending order.

**Example:**
```java
public List<Integer> inorderTraversal(TreeNode root) {
    List<Integer> result = new ArrayList<>();
    if (root == null) return result;
    Stack<TreeNode> stack = new Stack<>();
    TreeNode p = root;
    while (!stack.empty() || p != null) {
        if (p != null) {
            stack.push(p);
            p = p.left;
        } else {
            TreeNode t = stack.pop();
            result.add(t.val);
            p = t.right;
        }
    }
    return result;
}
```

### Postorder Traversal (Left, Right, Root)
In a postorder traversal, we recursively do a postorder traversal of the left subtree and the right subtree, and then visit the root node.

**Example:**
```java
public List<Integer> postorderTraversal(TreeNode root) {
    List<Integer> res = new ArrayList<>();
    if (root == null) return res;
    Stack<TreeNode> stack = new Stack<>();
    stack.push(root);
    while (!stack.isEmpty()) {
        TreeNode temp = stack.peek();
        if (temp.left == null && temp.right == null) {
            TreeNode pop = stack.pop();
            res.add(pop.val);
        } else {
            if (temp.right != null) {
                stack.push(temp.right);
                temp.right = null;
            }
            if (temp.left != null) {
                stack.push(temp.left);
                temp.left = null;
            }
        }
    }
    return res;
}
```

### Level Order Traversal
In a level order traversal, we visit the nodes level by level, from left to right. This is typically implemented using a queue.

**Example:**
```java
public List<List<Integer>> levelOrder(TreeNode root) {
    List<List<Integer>> result = new ArrayList<>();
    if (root == null) return result;
    Queue<TreeNode> queue = new LinkedList<>();
    queue.add(root);
    while (!queue.isEmpty()) {
        int levelSize = queue.size();
        List<Integer> currentLevel = new ArrayList<>();
        for (int i = 0; i < levelSize; i++) {
            TreeNode node = queue.poll();
            currentLevel.add(node.val);
            if (node.left != null) queue.add(node.left);
            if (node.right != null) queue.add(node.right);
        }
        result.add(currentLevel);
    }
    return result;
}
```
