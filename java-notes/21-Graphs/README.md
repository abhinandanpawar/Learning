# 21 - Graphs

A graph is a data structure that consists of a set of vertices (or nodes) and a set of edges that connect these vertices. Graphs are used to model relationships between objects and are one of the most versatile data structures in computer science.

## Introduction to Graphs
*   **Vertex:** A node in the graph.
*   **Edge:** A connection between two vertices.
*   **Directed vs. Undirected:** In a directed graph, edges have a direction (e.g., a one-way street). In an undirected graph, edges have no direction.
*   **Weighted vs. Unweighted:** In a weighted graph, each edge has a weight or cost associated with it.

## Graph Representation
There are two common ways to represent a graph:
1.  **Adjacency Matrix:** A 2D array where `adj[i][j] = 1` if there is an edge from vertex `i` to vertex `j`.
2.  **Adjacency List:** An array of lists, where `adj[i]` contains a list of all vertices that are adjacent to vertex `i`.

## Common Graph Problems

### Cloning a Graph
The goal is to create a deep copy of a graph. This is a classic graph traversal problem that can be solved with either Breadth-First Search (BFS) or Depth-First Search (DFS).

**Example (BFS):**
```java
class UndirectedGraphNode {
    int label;
    List<UndirectedGraphNode> neighbors;
    UndirectedGraphNode(int x) { label = x; neighbors = new ArrayList<UndirectedGraphNode>(); }
};

public class Solution {
    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
        if (node == null) return null;

        Map<UndirectedGraphNode, UndirectedGraphNode> map = new HashMap<>();
        Queue<UndirectedGraphNode> queue = new LinkedList<>();

        queue.add(node);
        map.put(node, new UndirectedGraphNode(node.label));

        while (!queue.isEmpty()) {
            UndirectedGraphNode u = queue.poll();

            for (UndirectedGraphNode neighbor : u.neighbors) {
                if (!map.containsKey(neighbor)) {
                    map.put(neighbor, new UndirectedGraphNode(neighbor.label));
                    queue.add(neighbor);
                }
                map.get(u).neighbors.add(map.get(neighbor));
            }
        }

        return map.get(node);
    }
}
```

### Course Schedule (Topological Sort)
This problem is equivalent to finding if a directed graph has a cycle. If it doesn't, we can find a topological sort of the graph, which is a linear ordering of its vertices such that for every directed edge from vertex `u` to vertex `v`, `u` comes before `v` in the ordering.

This can be solved using Kahn's algorithm (a BFS-based approach) or by using DFS.

**Example (BFS / Kahn's Algorithm):**
```java
public boolean canFinish(int numCourses, int[][] prerequisites) {
    int[] inDegree = new int[numCourses];
    List<List<Integer>> adj = new ArrayList<>(numCourses);
    for (int i = 0; i < numCourses; i++) {
        adj.add(new ArrayList<>());
    }

    for (int[] prerequisite : prerequisites) {
        adj.get(prerequisite[1]).add(prerequisite[0]);
        inDegree[prerequisite[0]]++;
    }

    Queue<Integer> queue = new LinkedList<>();
    for (int i = 0; i < numCourses; i++) {
        if (inDegree[i] == 0) {
            queue.add(i);
        }
    }

    int count = 0;
    while (!queue.isEmpty()) {
        int u = queue.poll();
        count++;
        for (int v : adj.get(u)) {
            if (--inDegree[v] == 0) {
                queue.add(v);
            }
        }
    }

    return count == numCourses;
}
```
This algorithm works by finding nodes with an in-degree of 0 (no prerequisites), adding them to a queue, and then "removing" them from the graph (and decrementing the in-degree of their neighbors). If we are able to remove all nodes, then there is no cycle and it's possible to finish all courses.
