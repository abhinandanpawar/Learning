package com.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * Main application to demonstrate the Java Collections Framework.
 *
 * <p>The framework is built around a set of standard interfaces. The diagram below
 * shows the core interface hierarchy:</p>
 * <img src="doc-files/collections_hierarchy.png" alt="Collections Framework Hierarchy">
 */
public class CollectionDemo {

    public static void main(String[] args) {
        System.out.println("--- Java Collections Framework Showcase ---");

        // --- List Example (ArrayList) ---
        System.out.println("\n--- 1. List Example ---");
        List<String> names = new ArrayList<>();
        names.add("Alice");
        names.add("Charlie");
        names.add("Bob");
        System.out.println("Original List: " + names);
        Collections.sort(names); // Using the Collections utility class
        System.out.println("Sorted List: " + names);


        // --- Set Example (HashSet) ---
        System.out.println("\n--- 2. Set Example ---");
        Set<String> uniqueNames = new HashSet<>();
        uniqueNames.add("Alice");
        uniqueNames.add("Bob");
        uniqueNames.add("Alice"); // This duplicate will be ignored
        System.out.println("Set of unique names: " + uniqueNames);


        // --- Map Example (HashMap) ---
        System.out.println("\n--- 3. Map Example ---");
        Map<String, Integer> cityPopulations = new HashMap<>();
        cityPopulations.put("New York", 8_400_000);
        cityPopulations.put("Los Angeles", 4_000_000);
        cityPopulations.put("Chicago", 2_700_000);
        System.out.println("Population of Chicago: " + cityPopulations.get("Chicago"));


        // --- Comparable and Comparator Example ---
        System.out.println("\n--- 4. Comparable vs. Comparator ---");
        List<Person> people = new ArrayList<>();
        people.add(new Person(102, "Charlie", 30));
        people.add(new Person(101, "Alice", 25));
        people.add(new Person(103, "Bob", 35));

        System.out.println("Original list of people: " + people);

        // Sorting by natural order (personId), defined by Comparable
        Collections.sort(people);
        System.out.println("Sorted by ID (Comparable): " + people);

        // Sorting with a custom Comparator for name (using a lambda expression)
        Comparator<Person> byName = (p1, p2) -> p1.getName().compareTo(p2.getName());
        people.sort(byName); // Lists have a built-in sort method that takes a Comparator
        System.out.println("Sorted by Name (Comparator): " + people);

        // Your Mission:
        // 1. Sort the 'people' list by age, from youngest to oldest.
        // 2. Use a lambda expression to create a Comparator inline.
        //    Hint: The sort method is people.sort(...);
        //    Hint: Integer.compare(p1.getAge(), p2.getAge()) is the logic you need.

        // --- Your code goes here ---


        // --- End of your code ---
        System.out.println("Sorted by Age (Comparator): " + people);


        // --- PriorityQueue Example ---
        System.out.println("\n--- 5. PriorityQueue Example ---");
        // A PriorityQueue orders elements based on their natural order or a provided Comparator.
        // It's a "min-heap" by default, so the smallest element has the highest priority.
        PriorityQueue<Integer> taskQueue = new PriorityQueue<>();
        taskQueue.add(5); // Medium priority
        taskQueue.add(1); // High priority
        taskQueue.add(10); // Low priority

        System.out.println("Processing tasks by priority:");
        while (!taskQueue.isEmpty()) {
            // poll() retrieves and removes the head of the queue (the highest priority element)
            System.out.println("Processing task with priority: " + taskQueue.poll());
        }
    }
}
