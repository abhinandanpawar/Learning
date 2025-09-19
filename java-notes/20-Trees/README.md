# 20 - Trees: Representing Hierarchical Data

Trees are a fundamental non-linear data structure used to represent hierarchical data. Think of a family tree, an organization chart, or the file system on your computer—these are all tree structures.

```mermaid
graph TD
    subgraph "Tree Terminology"
        Root -- Edge --> Child1
        Root -- Edge --> Child2
        Child1 -- Edge --> Leaf1
        Child1 -- Edge --> Leaf2

        subgraph Legend
            direction LR
            A["Root: The top node"]
            B["Edge: Link between nodes"]
            C["Leaf: Node with no children"]
        end
    end
    style Root fill:#cde4ff
    style Leaf1 fill:#e1f5fe
    style Leaf2 fill:#e1f5fe
```

**What's in this chapter:**
*   [Binary Trees and Traversals](#1-binary-trees-and-traversals)
*   [Binary Search Trees (BST)](#2-binary-search-trees-bst)
*   [Balanced vs. Unbalanced Trees](#3-balanced-vs-unbalanced-trees)
*   [Hands-On Lab: Building and Traversing a Tree](#4-hands-on-lab)

---

## 1. Binary Trees and Traversals

A **Binary Tree** is a tree in which each node has at most two children, referred to as the `left` child and the `right` child.

### Our Example Tree
Let's use this simple binary tree to demonstrate the traversal algorithms.

```mermaid
graph TD
    A(4) --> B(2)
    A --> C(7)
    B --> D(1)
    B --> E(3)
    C --> F(6)
    C --> G(9)
```

**Traversal** is the process of visiting every node in the tree exactly once. The order in which you visit them defines the traversal type.

### Pre-order Traversal (Root, Left, Right)
Visits the current node **before** its children. Useful for creating a copy of a tree.

```mermaid
graph TD
    subgraph "Pre-order: Visit -> Go Left -> Go Right"
        direction LR
        A(4) -- 1. Visit --> R1[Path: 4]
        R1 -- 2. Go Left --> B(2)
        B -- 3. Visit --> R2[Path: 4, 2]
        R2 -- 4. Go Left to 1, Visit, Backtrack --> R3[Path: 4, 2, 1]
        R3 -- 5. Go Right to 3, Visit, Backtrack --> R4[Path: 4, 2, 1, 3]
        R4 -- 6. Backtrack, Go Right --> C(7)
        C -- 7. Visit & Continue... --> R5[Final Path: 4,2,1,3,7,6,9]
    end
```

### In-order Traversal (Left, Root, Right)
Visits the current node **in-between** its left and right children. For a Binary Search Tree, this visits the nodes in sorted order.

```mermaid
graph TD
    subgraph "In-order: Go Left -> Visit -> Go Right"
        direction LR
        A(4) -- 1. Go Left --> B(2)
        B -- 2. Go Left --> D(1)
        D -- 3. Visit 1 --> R1[Path: 1]
        R1 -- 4. Backtrack, Visit 2 --> R2[Path: 1, 2]
        R2 -- 5. Go Right to 3, Visit --> R3[Path: 1, 2, 3]
        R3 -- 6. Backtrack, Visit 4 --> R4[Path: 1, 2, 3, 4]
        R4 -- 7. Go Right to 7 & Continue... --> R5[Final Path: 1,2,3,4,6,7,9]
    end
```

### Post-order Traversal (Left, Right, Root)
Visits the current node **after** its children. Useful for deleting nodes from a tree (you delete the children before the parent).

```mermaid
graph TD
    subgraph "Post-order: Go Left -> Go Right -> Visit"
        direction LR
        A(4) -- 1. Go Left --> B(2)
        B -- 2. Go Left to 1, Visit --> R1[Path: 1]
        R1 -- 3. Go Right to 3, Visit --> R2[Path: 1, 3]
        R2 -- 4. Backtrack, Visit 2 --> R3[Path: 1, 3, 2]
        R3 -- 5. Backtrack to 4, Go Right to 7 --> C(7)
        C -- 6. Continue... --> R4[Final Path: 1,3,2,6,9,7,4]
    end
```
---

## 2. Binary Search Trees (BST)

A **Binary Search Tree** is a special type of binary tree with a crucial property:
> For any given node, all values in its **left** subtree are **less than** the node's value, and all values in its **right** subtree are **greater than** the node's value.

This property makes searching for and inserting elements incredibly efficient, with an average time complexity of **O(log n)**.

```mermaid
graph TD
    subgraph "Searching for 6"
        A(8) -- "6 < 8<br>Go Left" --> B(3)
        B -- "6 > 3<br>Go Right" --> C(6)
        C -- "6 == 6<br>Found!" --> F{Found!}
    end
    subgraph "Inserting 13"
        D(8) -- "13 > 8<br>Go Right" --> E(10)
        E -- "13 > 10<br>Go Right" --> G(14)
        G -- "13 < 14<br>Go Left" --> H(13)
        H -- "Insert here" --> I{Inserted!}
    end
```

---

## 3. Balanced vs. Unbalanced Trees

The O(log n) performance of a BST depends on it being **balanced**. If you insert sorted elements into a BST, it becomes a completely unbalanced tree, which is just a linked list. Performance degrades to O(n).

```mermaid
graph TD
    subgraph "Balanced BST (Height: O(log n))"
        A(4) --> B(2)
        A --> C(6)
        B --> D(1)
        B --> E(3)
        C --> F(5)
        C --> G(7)
    end

    subgraph "Unbalanced BST (Height: O(n))"
        H(1) --> I(2)
        I --> J(3)
        J --> K(4)
        K --> L(5)
    end
```

**Self-Balancing Trees:** Special types of BSTs, like **AVL trees** and **Red-Black trees**, automatically perform rotations during insertion and deletion to keep the tree balanced. Java's `TreeMap` and `TreeSet` are implemented using Red-Black trees.

---

## 4. Hands-On Lab

We've created a runnable project in the `code/` directory that builds the example tree and implements all four traversal algorithms. Explore the source code to see how they are implemented.

**To run it:**
1.  Navigate to the `code/` directory.
2.  Run `mvn compile exec:java`.
