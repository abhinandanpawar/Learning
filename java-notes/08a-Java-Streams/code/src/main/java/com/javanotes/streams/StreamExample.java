package com.javanotes.streams;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamExample {

    public static void main(String[] args) {
        List<String> names = List.of("Alice", "Bob", "Charlie", "Anna", "Alex", "bob");

        System.out.println("Original list: " + names);
        System.out.println("---");

        // filter, map, collect
        List<String> aNamesInUpperCase = names.stream()
                .filter(name -> name.toLowerCase().startsWith("a"))
                .map(String::toUpperCase)
                .collect(Collectors.toList());
        System.out.println("Names starting with 'A' in uppercase: " + aNamesInUpperCase);
        System.out.println("---");

        // distinct, sorted
        List<String> distinctSortedNames = names.stream()
                .map(String::toLowerCase)
                .distinct()
                .sorted()
                .collect(Collectors.toList());
        System.out.println("Distinct, sorted, lowercase names: " + distinctSortedNames);
        System.out.println("---");

        // flatMap
        List<List<Integer>> listOfLists = List.of(List.of(1, 2), List.of(3, 4, 5), List.of(6));
        List<Integer> flattenedList = listOfLists.stream()
                .flatMap(List::stream)
                .collect(Collectors.toList());
        System.out.println("Flattened list: " + flattenedList);
        System.out.println("---");

        // peek, limit, skip
        System.out.println("Peek, skip, limit example:");
        List<String> peekedNames = names.stream()
                .peek(name -> System.out.println("Peeking at: " + name))
                .skip(1)
                .limit(3)
                .collect(Collectors.toList());
        System.out.println("Result of peek, skip, limit: " + peekedNames);
        System.out.println("---");

        // reduce
        Optional<String> reducedNames = names.stream()
                .reduce((s1, s2) -> s1 + ", " + s2);
        reducedNames.ifPresent(s -> System.out.println("Reduced names: " + s));
        System.out.println("---");

        // matching: anyMatch, allMatch, noneMatch
        boolean anyMatchBob = names.stream().anyMatch(name -> name.equalsIgnoreCase("bob"));
        boolean allMatchBob = names.stream().allMatch(name -> name.equalsIgnoreCase("bob"));
        boolean noneMatchEve = names.stream().noneMatch(name -> name.equalsIgnoreCase("eve"));
        System.out.println("Any name is 'bob'? " + anyMatchBob);
        System.out.println("All names are 'bob'? " + allMatchBob);
        System.out.println("No name is 'eve'? " + noneMatchEve);
        System.out.println("---");

        // findFirst, findAny
        Optional<String> first = names.stream().findFirst();
        first.ifPresent(s -> System.out.println("First name: " + s));

        Optional<String> any = names.stream().parallel().findAny();
        any.ifPresent(s -> System.out.println("Any name (from parallel stream): " + s));
    }
}
