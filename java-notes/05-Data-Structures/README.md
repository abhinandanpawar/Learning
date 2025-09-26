# 05 - An Overview of Core Data Structures

As language designers, we knew that providing powerful, efficient, and easy-to-use data structures was not just a feature—it was essential. The way you organize your data has a profound impact on your program's performance, scalability, and clarity.

This chapter provides a high-level overview of the most common data structures available in the **Java Collections Framework**. We will explore the design trade-offs we made for each, so you can learn to choose the right tool for the job.

```mermaid
graph TD
    subgraph "Java Collections Framework"
        direction LR

        subgraph "Interfaces (The Contracts)"
            Collection --> List
            Collection --> Set
            Collection --> Queue
            Map
        end

        subgraph "Common Classes (The Implementations)"
            ArrayList -- implements --> List
            LinkedList -- implements --> List
            LinkedList -- implements --> Queue

            HashSet -- implements --> Set
            TreeSet -- implements --> Set

            PriorityQueue -- implements --> Queue

            HashMap -- implements --> Map
            TreeMap -- implements --> Map
        end

        style `Interfaces (The Contracts)` fill:#cde4ff
        style `Common Classes (The Implementations)` fill:#e1f5fe
    end
```

**What's in this chapter:**
*   [Mental Models for Core Data Structures](#1-mental-models-for-core-data-structures)
*   [`ArrayList`: The Resizable Array](#2-arraylist-the-resizable-array)
*   [`LinkedList`: The Sequential Chain](#3-linkedlist-the-sequential-chain)
*   [`Stack`: Last-In, First-Out](#4-stack-last-in-first-out)
*   [`Queue`: First-In, First-Out](#5-queue-first-in-first-out)
*   [`HashMap`: The Key-Value Store](#6-hashmap-the-key-value-store)
*   [Your Mission: Count Word Frequencies](#7-your-mission-count-word-frequencies)
*   [Performance at a Glance: Big-O Notation](#8-performance-at-a-glance-big-o-notation)

---

## 1. Mental Models for Core Data Structures

To make these abstract structures concrete, let's use some real-world analogies.

*   **`ArrayList` is a Train:** A train has a fixed number of cars, and each car has a number (its index). Getting to car #5 is super fast—you just walk straight to it (`O(1)`). But adding a new car in the middle is a huge pain; you have to disconnect the train and shift everything down (`O(n)`).

*   **`LinkedList` is a Scavenger Hunt:** You start at the first clue, which tells you where to find the next clue, and so on. There's no way to jump to the 5th clue without following the first four (`O(n)`). But adding a new clue in the middle is easy; you just change the location on the previous clue to point to your new one, and your new one points to the one that was originally next.

*   **`HashMap` is a Dictionary:** When you want to find the definition of the word "Polymorphism," you don't read the dictionary from the beginning. You flip directly to the "P" section and find the word. It's an incredibly fast lookup (`O(1)` on average). The word is the **key**, and the definition is the **value**.

*   **`Stack` is a Stack of Plates:** You can only add a new plate to the top, and you can only take a plate from the top. This is called **Last-In, First-Out (LIFO)**.

*   **`Queue` is a Checkout Line:** The first person to get in line is the first person to be served. New people are added to the back of the line. This is called **First-In, First-Out (FIFO)**.

---

## 2. `ArrayList`: The Resizable Array

An `ArrayList` is a list backed by a plain Java array. It's the most common list implementation and should generally be your default choice.

**How it works:**
It wraps a regular array, but when the array gets full, it automatically creates a new, larger array (typically 1.5x the size) and copies the old elements over. This is called **amortized constant time** `O(1)` for additions.

```mermaid
graph TD
    subgraph "ArrayList Resizing"
        A["<b>Before Add:</b><br>[A, B, C, D]<br>(size=4, capacity=4)"] -- "add('E')" --> B["<b>After Add:</b><br>[A, B, C, D, E, , , ]<br>(size=5, capacity=8)<br><i>New array created, elements copied.</i>"]
    end
```

**When to use it:**
*   When you need fast, index-based access to elements (`get(i)`).
*   When you are mostly adding elements to the end of the list.

---

## 3. `LinkedList`: The Sequential Chain

A `LinkedList` stores elements in a chain of nodes. Each node is an object that holds an element and a reference (a pointer) to the next node in the chain.

**How it works:**
To add or remove an element, you just create a new node and update the pointers of its neighbors. This makes additions and removals in the middle of the list much faster than in an `ArrayList`.

```mermaid
graph TD
    subgraph "LinkedList Operations"
        A("Node A") --> B("Node C")
        A -- "add(B) after A" --> C("Node A") --> D("Node B") --> E("Node C")
    end
```

**When to use it:**
*   When you frequently add or remove elements from the beginning, middle, or end of the list.
*   When you don't need fast random access.

---

## 4. `Stack`: Last-In, First-Out

A `Stack` is a data structure that follows the **LIFO (Last-In, First-Out)** principle. Think of it as a stack of plates.

**How it works:**
You `push` elements onto the top of the stack and `pop` them off the top.

```mermaid
graph TD
    subgraph "Stack (LIFO)"
        direction TB
        Top --> A("Plate C") --> B("Plate B") --> C("Plate A")
        subgraph "Operations"
            direction LR
            Push["push(D)"] -- adds to top --> Top
            Pop["pop()"] -- removes from top --> Top
        end
    end
```

**When to use it:**
*   Parsing expressions (e.g., checking for balanced parentheses).
*   Managing function calls (the "call stack").
*   Implementing "undo" functionality.

```java
Stack<String> stack = new Stack<>();
stack.push("A");
stack.push("B");
stack.push("C");
System.out.println(stack.pop()); // "C"
```

---

## 5. `Queue`: First-In, First-Out

A `Queue` is a data structure that follows the **FIFO (First-In, First-Out)** principle. Think of it as a checkout line.

**How it works:**
You `add` (or `offer`) elements to the back of the queue and `remove` (or `poll`) them from the front.

```mermaid
graph TD
    subgraph "Queue (FIFO)"
        direction LR
        Front --> A("Person A") --> B("Person B") --> C("Person C") --> Back
        subgraph "Operations"
            direction TD
            Enqueue["add(D)"] -- adds to back --> Back
            Dequeue["remove()"] -- removes from front --> Front
        end
    end
```

**When to use it:**
*   Processing tasks in the order they were received.
*   Breadth-first search in graphs and trees.
*   Managing requests in a web server.

```java
Queue<String> queue = new LinkedList<>(); // LinkedList implements Queue
queue.add("A");
queue.add("B");
queue.add("C");
System.out.println(queue.remove()); // "A"
```

---

## 6. `HashMap`: The Key-Value Store

A `HashMap` is a powerful structure that stores key-value pairs. It uses the `hashCode()` of the key to calculate an index, allowing for incredibly fast lookups.

**How it works:**
A `HashMap` uses an internal array of "buckets". When you `put(key, value)`, it calculates the key's hash code to determine which bucket to place the entry in. If multiple keys hash to the same bucket (a "collision"), the entries are stored as a linked list within that bucket.

```mermaid
graph TD
    subgraph "HashMap with Collision"
        direction LR
        subgraph "Internal Array (Buckets)"
            B0["Bucket 0"]
            B1["Bucket 1"] --> E1{"key: 'name', hash: 123"}
            B2["Bucket 2"] --> E2{"key: 'city', hash: 456"}
            B3["Bucket 3"] --> E3{"key: 'job', hash: 789"}
            B1 --> E4{"key: 'user', hash: 123"}
        end
        note for E4 "<b>Hash Collision!</b><br>Both 'name' and 'user' have hash 123.<br>They are stored in a list/tree in the same bucket."
    end
```

**When to use it:**
*   Anytime you need to associate one piece of data with another.
*   When you need to look up data by a unique identifier. This is one of the most useful data structures in all of programming.

---

## 7. Your Mission: Count Word Frequencies

The best way to understand data structures is to use them. Your mission is to write a program that counts the frequency of each word in a sentence. This is a classic problem that perfectly demonstrates the power of `HashMap`.

We have created a new project for you in the `code/` directory.

**Your Mission:**

1.  **Find the Code:** Open the `code/src/main/java/com/tenx/ds/WordFrequency.java` file.
2.  **Follow the Instructions:** The file contains comments guiding you through the mission.
3.  **Run Your Code:** Navigate to the `code/` directory in your terminal and run `mvn compile exec:java`.
4.  **Verify Your Output:** You should see the map of word counts printed to the console.

<details>
<summary>Stuck? Here's the solution</summary>

```java
package com.tenx.ds;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class WordFrequency {
    public static void main(String[] args) {
        String sentence = "The quick brown fox jumps over the lazy dog";
        List<String> words = Arrays.asList(sentence.toLowerCase().split(" "));
        Map<String, Integer> wordFrequencies = new HashMap<>();

        for (String word : words) {
            wordFrequencies.put(word, wordFrequencies.getOrDefault(word, 0) + 1);
        }
        System.out.println("Word Frequencies: " + wordFrequencies);
    }
}
```
</details>

---

## 8. Performance at a Glance: Big-O Notation

Choosing the right data structure requires understanding its performance characteristics. Big-O notation gives us a standardized way to talk about this.

| Operation         | `ArrayList` | `LinkedList` | `HashMap`        | Notes                                                              |
|-------------------|-------------|--------------|------------------|--------------------------------------------------------------------|
| **`get(index)`**  | `O(1)`      | `O(n)`       | `N/A`            | `ArrayList` is the clear winner for random access.                 |
| **`add(element)`**| `O(1)`*     | `O(1)`       | `O(1)`*          | *Amortized constant time. Resizing can take O(n).                  |
| **`remove(index)`**| `O(n)`      | `O(n)`       | `N/A`            | `remove(element)` is O(n) for both lists.                          |
| **`containsKey(key)`**| `O(n)` | `O(n)`       | `O(1)`*          | This is where `HashMap` shines.   |

**Key Takeaways:**
*   **Choose the Right Tool:** The data structure you choose has a huge impact on performance.
*   **`ArrayList` for Speed:** Default to `ArrayList` when you need fast, index-based access.
*   **`LinkedList` for Ends:** Use `LinkedList` only when you do a lot of additions/removals from the *ends* of the list.
*   **`HashMap` for Lookups:** Use `HashMap` whenever you need to look up values by a unique key.
*   **Big-O Matters:** Big-O notation is the language we use to talk about performance. `O(1)` (constant time) is amazing, `O(n)` (linear time) is okay, and `O(n^2)` (quadratic time) can be very slow for large inputs.
