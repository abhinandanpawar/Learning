package com.javanotes.streams;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;

import java.util.Optional;
import java.util.function.Function;

import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class AdvancedStreamExample {

    public static void main(String[] args) {
        System.out.println("### Advanced Stream Examples ###");
        System.out.println("---");

        // --- Stream Creation ---
        System.out.println("--- Stream Creation ---");
        Stream<String> streamOf = Stream.of("a", "b", "c");
        System.out.println("Stream.of: " + streamOf.collect(Collectors.toList()));

        String[] array = {"a", "b", "c"};
        Stream<String> streamOfArray = Arrays.stream(array);
        System.out.println("Arrays.stream: " + streamOfArray.collect(Collectors.toList()));

        Stream<Integer> streamIterate = Stream.iterate(0, n -> n + 2).limit(5);
        System.out.println("Stream.iterate: " + streamIterate.collect(Collectors.toList()));

        Stream<Double> streamGenerate = Stream.generate(Math::random).limit(3);
        System.out.println("Stream.generate: " + streamGenerate.collect(Collectors.toList()));
        System.out.println("---");


        // --- Advanced Collectors ---
        System.out.println("--- Advanced Collectors ---");
        List<String> names = List.of("Alice", "Bob", "Charlie", "Anna", "Alex", "bob");

        // groupingBy
        Map<Integer, List<String>> namesByLength = names.stream()
                .collect(Collectors.groupingBy(String::length));
        System.out.println("Names grouped by length: " + namesByLength);

        // partitioningBy
        Map<Boolean, List<String>> namesPartitioned = names.stream()
                .collect(Collectors.partitioningBy(s -> s.length() > 4));
        System.out.println("Names partitioned by length > 4: " + namesPartitioned);

        // joining
        String joinedNames = names.stream()
                .collect(Collectors.joining(", "));
        System.out.println("Joined names: " + joinedNames);
        System.out.println("---");


        // --- Primitive Streams ---
        System.out.println("--- Primitive Streams ---");
        int[] numbers = {1, 2, 3, 4, 5};

        // IntStream from array
        IntStream intStream = Arrays.stream(numbers);
        System.out.println("Sum of numbers: " + intStream.sum());

        // IntStream.range and summaryStatistics
        IntSummaryStatistics stats = IntStream.range(1, 10)
                .summaryStatistics();
        System.out.println("Statistics for numbers 1-9: " + stats);
        System.out.println("---");


        // --- Parallel Streams ---
        System.out.println("--- Parallel Streams ---");
        long count = IntStream.range(1, 1_000_000).parallel().filter(n -> n % 2 == 0).count();
        System.out.println("Count of even numbers (1 to 1,000,000) using parallel stream: " + count);

        System.out.println("---");

        // --- Optional ---
        System.out.println("--- Optional ---");
        Optional<String> maybeName = names.stream().filter(s -> s.length() > 10).findFirst();
        System.out.println("Is name present? " + maybeName.isPresent());
        System.out.println("Name or default: " + maybeName.orElse("No name found"));
        maybeName.ifPresent(name -> System.out.println("Name found: " + name));
        System.out.println("---");


        // --- Exception Handling ---
        System.out.println("--- Exception Handling ---");
        Function<String, String> readFile = path -> {
            try {
                return new String(Files.readAllBytes(Paths.get(path)));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        };
        // Example with a file that doesn't exist to show the exception being thrown
        try {
            Stream.of("non_existent_file.txt").map(readFile).forEach(System.out::println);
        } catch (Exception e) {
            System.out.println("Caught expected exception: " + e.getMessage());
        }
        System.out.println("---");


        // --- Custom Collector ---
        System.out.println("--- Custom Collector ---");
        String customJoined = names.stream().collect(new StringCollector(" | "));
        System.out.println("Custom joined names: " + customJoined);
        System.out.println("---");

    }
}
