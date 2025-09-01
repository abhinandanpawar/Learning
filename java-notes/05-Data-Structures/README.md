# 05 - Data Structures: Organizing Your Data

As language designers, we knew we had to provide you with a powerful and flexible set of tools for organizing your data. This is where the Java Collections Framework comes in. It's a set of data structures that we designed to handle common programming tasks.

Let's look at a few of the most important ones, and the design trade-offs we made for each.

## 1. `ArrayList`: The Go-To List

An `ArrayList` is a resizable array. It's probably the most common data structure you'll use.

In our e-commerce app, we could use an `ArrayList` to store the products in a shopping cart.

```java
// Assuming Product class from previous chapter
Product laptop = new Product("Laptop", 1200.00);
Product book = new Product("Java for Kids", 25.00);

ArrayList<Product> shoppingCart = new ArrayList<>();
shoppingCart.add(laptop);
shoppingCart.add(book);

// Iterate over the shopping cart
for (Product product : shoppingCart) {
    product.display();
}

// Remove an item
shoppingCart.remove(book);

System.out.println("Cart size: " + shoppingCart.size()); // 1
```

**Memory and Performance Deep Dive:**

*   **Memory:** An `ArrayList` is backed by a plain old array on the heap.
*   **Performance:** Accessing an element by its index (`shoppingCart.get(0)`) is very fast (O(1) time complexity). However, adding or removing an element from the middle of the list can be slow (O(n)) because it requires shifting all the subsequent elements.
*   **Resizing:** When you add an element to a full `ArrayList`, we have to create a new, larger array and copy all the elements over. This can be an expensive operation.

**System Design Insight:** `ArrayList` is a great choice when you need fast random access to elements and you don't do a lot of insertions and deletions in the middle of the list.

## 2. `LinkedList`: The Flexible List

A `LinkedList` is a sequence of nodes, where each node points to the next one.

**Memory and Performance Deep Dive:**

*   **Memory:** Each node in a `LinkedList` is a separate object on the heap, which means it has more memory overhead than an `ArrayList`.
*   **Performance:** Adding or removing an element from a `LinkedList` is very fast (O(1)) if you have a reference to the node. However, accessing an element by index is slow (O(n)) because we have to traverse the list from the beginning.

**System Design Insight:** `LinkedList` is a good choice when you have a lot of insertions and deletions, especially at the beginning or end of the list.

## 3. `HashMap`: The "Key-Value" Store

A `HashMap` stores key-value pairs. It's incredibly useful for looking up values by a key.

In our e-commerce app, we could use a `HashMap` to store the quantity of each product in the shopping cart.

```java
HashMap<Product, Integer> cartQuantities = new HashMap<>();
cartQuantities.put(laptop, 1);
cartQuantities.put(book, 2);

// Get the quantity of a product
int laptopQuantity = cartQuantities.get(laptop); // 1

// Update the quantity
cartQuantities.put(laptop, laptopQuantity + 1);

// Iterate over the keys
for (Product product : cartQuantities.keySet()) {
    System.out.println(product.getName());
}

// Iterate over the values
for (Integer quantity : cartQuantities.values()) {
    System.out.println(quantity);
}
```

**Memory and Performance Deep Dive:**

*   **Memory:** A `HashMap` uses an array of linked lists (or trees, since Java 8) to store its data. It has a higher memory overhead than a simple list.
*   **Performance:** `HashMap` provides constant-time performance (O(1)) for `get` and `put` operations, assuming the hash function disperses the elements properly among the buckets. This is why it's so fast.

**System Design Insight:** `HashMap` is the perfect tool when you need to quickly look up a value by a key. It's one of the most important data structures for system design.

---

[Previous: 04 - Advanced OOP: Interfaces and Abstraction](../04-Advanced-OOP/README.md) | [Next: 06 - Exception Handling: Dealing with the Unexpected](../06-Exception-Handling/README.md)
