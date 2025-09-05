package com.example;

/**
 * A classic generic class example.
 * A Box can hold an item of any type. The type parameter 'T'
 * acts as a placeholder for the actual type that will be used
 * when an instance of Box is created.
 *
 * @param <T> The type of item this box will hold.
 */
public class Box<T> {
    private T item;

    public void put(T item) {
        this.item = item;
    }

    public T get() {
        return item;
    }

    @Override
    public String toString() {
        return "Box containing: " + item;
    }
}
