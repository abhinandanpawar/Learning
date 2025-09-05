package com.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * A generic class representing a graph using an adjacency list.
 * The adjacency list is implemented using a Map, where the key is a vertex
 * and the value is a list of its adjacent vertices.
 *
 * @param <T> The type of the vertices.
 */
public class Graph<T> {

    private final Map<T, List<T>> adjacencyList;

    public Graph() {
        this.adjacencyList = new HashMap<>();
    }

    /**
     * Adds a new vertex to the graph.
     * @param vertex The vertex to add.
     */
    public void addVertex(T vertex) {
        adjacencyList.putIfAbsent(vertex, new ArrayList<>());
    }

    /**
     * Adds an undirected edge between two vertices.
     * @param vertex1 The first vertex.
     * @param vertex2 The second vertex.
     */
    public void addEdge(T vertex1, T vertex2) {
        // Ensure both vertices exist in the graph
        addVertex(vertex1);
        addVertex(vertex2);

        // Add edge from vertex1 to vertex2
        adjacencyList.get(vertex1).add(vertex2);
        // Add edge from vertex2 to vertex1 for an undirected graph
        adjacencyList.get(vertex2).add(vertex1);
    }

    /**
     * Gets the list of adjacent vertices for a given vertex.
     * @param vertex The vertex to get the neighbors of.
     * @return A list of neighboring vertices.
     */
    public List<T> getNeighbors(T vertex) {
        return adjacencyList.getOrDefault(vertex, new LinkedList<>());
    }

    /**
     * Gets all vertices in the graph.
     * @return A set of all vertices.
     */
    public java.util.Set<T> getVertices() {
        return adjacencyList.keySet();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (T vertex : adjacencyList.keySet()) {
            sb.append(vertex.toString()).append(": ");
            sb.append(adjacencyList.get(vertex).toString()).append("\n");
        }
        return sb.toString();
    }
}
