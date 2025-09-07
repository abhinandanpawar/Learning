package com.javanotes.streams;

import java.util.List;
import java.util.stream.Collectors;

public class StreamExample {

    public static void main(String[] args) {
        List<String> names = List.of("Alice", "Bob", "Charlie", "Anna", "Alex");

        System.out.println("Original list: " + names);

        // Use a stream to find all names that start with "A",
        // convert them to uppercase, and collect them into a new list.
        List<String> aNamesInUpperCase = names.stream()
                .filter(name -> name.startsWith("A"))
                .map(String::toUpperCase)
                .collect(Collectors.toList());

        System.out.println("Names starting with 'A' in uppercase: " + aNamesInUpperCase);
    }
}
