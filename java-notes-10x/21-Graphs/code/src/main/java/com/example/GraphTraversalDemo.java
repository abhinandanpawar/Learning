package com.example;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

/**
 * Main application to demonstrate graph traversals (BFS and DFS).
 */
public class GraphTraversalDemo {

    /**
     * Performs a Breadth-First Search (BFS) on a graph starting from a given vertex.
     * @param graph The graph to traverse.
     * @param start The starting vertex.
     * @param <T> The type of the vertices.
     */
    public static <T> void bfs(Graph<T> graph, T start) {
        System.out.print("BFS starting from " + start + ": ");
        Set<T> visited = new HashSet<>();
        Queue<T> queue = new LinkedList<>();

        queue.add(start);
        visited.add(start);

        while (!queue.isEmpty()) {
            T vertex = queue.poll();
            System.out.print(vertex + " ");

            for (T neighbor : graph.getNeighbors(vertex)) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.add(neighbor);
                }
            }
        }
        System.out.println();
    }

    /**
     * Performs a Depth-First Search (DFS) on a graph starting from a given vertex.
     * This is the iterative version using a Stack.
     * @param graph The graph to traverse.
     * @param start The starting vertex.
     * @param <T> The type of the vertices.
     */
    public static <T> void dfs(Graph<T> graph, T start) {
        System.out.print("DFS starting from " + start + ": ");
        Set<T> visited = new HashSet<>();
        Stack<T> stack = new Stack<>();

        stack.push(start);

        while (!stack.isEmpty()) {
            T vertex = stack.pop();

            if (!visited.contains(vertex)) {
                visited.add(vertex);
                System.out.print(vertex + " ");

                for (T neighbor : graph.getNeighbors(vertex)) {
                    if (!visited.contains(neighbor)) {
                        stack.push(neighbor);
                    }
                }
            }
        }
        System.out.println();
    }


    public static void main(String[] args) {
        // --- Build a Sample Graph (a simple social network) ---
        Graph<String> socialNetwork = new Graph<>();
        socialNetwork.addEdge("Alice", "Bob");
        socialNetwork.addEdge("Alice", "Charlie");
        socialNetwork.addEdge("Bob", "David");
        socialNetwork.addEdge("Charlie", "Eve");
        socialNetwork.addEdge("David", "Eve");
        socialNetwork.addEdge("Eve", "Frank");

        System.out.println("--- Graph Traversal Showcase ---");
        System.out.println("Graph Structure (Adjacency List):");
        System.out.println(socialNetwork);

        // --- Perform Traversals ---
        bfs(socialNetwork, "Alice");
        dfs(socialNetwork, "Alice");
    }
}
