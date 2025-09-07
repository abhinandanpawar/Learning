package com.javanotes.streams;

import java.util.Collections;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

public class StringCollector implements Collector<String, StringBuilder, String> {

    private final String delimiter;

    public StringCollector(String delimiter) {
        this.delimiter = delimiter;
    }

    @Override
    public Supplier<StringBuilder> supplier() {
        return StringBuilder::new;
    }

    @Override
    public BiConsumer<StringBuilder, String> accumulator() {
        return (sb, s) -> {
            if (sb.length() > 0) {
                sb.append(delimiter);
            }
            sb.append(s);
        };
    }

    @Override
    public BinaryOperator<StringBuilder> combiner() {
        return (sb1, sb2) -> {
            if (sb1.length() > 0) {
                sb1.append(delimiter);
            }
            sb1.append(sb2);
            return sb1;
        };
    }

    @Override
    public Function<StringBuilder, String> finisher() {
        return StringBuilder::toString;
    }

    @Override
    public Set<Characteristics> characteristics() {
        return Collections.emptySet();
    }
}
