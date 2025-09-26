# 07 - The Java Collections Framework: A Deeper Look

We've already touched on some data structures. Now, let's look at the design philosophy behind the entire **Java Collections Framework (JCF)**. We designed it to be a unified architecture for representing and manipulating collections, enabling them to be manipulated independently of the details of their representation.

**What's in this chapter:**
*   [The JCF Hierarchy: A Map of the Tools](#1-the-jcf-hierarchy-a-map-of-the-tools)
*   [The Core Interfaces: `List`, `Set`, `Queue`, and `Map`](#2-the-core-interfaces)
*   [How to Choose the Right Collection](#3-how-to-choose-the-right-collection)
*   [Sorting: `Comparable` vs. `Comparator`](#4-sorting-comparable-vs-comparator)
*   [Your Mission: Custom Sorting](#5-your-mission-custom-sorting)

---

## 1. The JCF Hierarchy: A Map of the Tools

The cornerstone of the JCF is a set of core interfaces. Understanding this hierarchy helps you understand the capabilities of each part of the framework.

```mermaid
graph TD
    subgraph "Java Collections Framework"
        direction TB
        subgraph "Core Interfaces"
            I(Iterable)
            C(Collection)
            L(List)
            S(Set)
            Q(Queue)
            M(Map)
            I --> C
            C --> L
            C --> S
            C --> Q
        end

        subgraph "Common Implementations"
            AL(ArrayList)
            LL(LinkedList)
            HS(HashSet)
            LHS(LinkedHashSet)
            TS(TreeSet)
            PQ(PriorityQueue)
            AD(ArrayDeque)
            HM(HashMap)
            LHM(LinkedHashMap)
            TM(TreeMap)
        end

        L -- "implemented by" --> AL
        L -- "implemented by" --> LL
        Q -- "implemented by" --> LL
        S -- "implemented by" --> HS
        S -- "implemented by" --> LHS
        S -- "implemented by" --> TS
        Q -- "implemented by" --> PQ
        Q -- "implemented by" --> AD
        M -- "implemented by" --> HM
        M -- "implemented by" --> LHM
        M -- "implemented by" --> TM

        note for M "Map does not extend Collection"

        style "Core Interfaces" fill:#cde4ff
        style "Common Implementations" fill:#e1f5fe
    end
```

---

## 2. The Core Interfaces: `List`, `Set`, `Queue`, and `Map`

### `List`: An Ordered Sequence
A `List` is an **ordered** collection that allows duplicates. Think of it as a shopping list.

```mermaid
graph TD
    subgraph "List: An ordered sequence"
        direction LR
        A["0: eggs"] --> B["1: milk"] --> C["2: eggs"]
    end
    note["Allows duplicates. Access by index."]
```
*   **Common Implementations:** `ArrayList`, `LinkedList`
*   **When to use:** When you need to access elements by their position and the order of elements matters.

### `Set`: A Collection of Uniques
A `Set` is a collection that contains **no duplicate** elements. Think of it as a party guest list.

```mermaid
graph TD
    subgraph "Set: A collection of unique items"
        A["Alice"]
        B["Bob"]
        C["Charlie"]
    end
    note["No duplicates. Order not guaranteed (usually)."]
```
*   **Common Implementations:** `HashSet`, `LinkedHashSet`, `TreeSet`
*   **When to use:** When you need to ensure that every element in the collection is unique.

### `Queue`: A First-In, First-Out Line
A `Queue` is a collection used to hold elements prior to processing, typically in a **FIFO** (first-in, first-out) manner. Think of a checkout line.

```mermaid
graph TD
    subgraph "Queue (FIFO)"
        direction LR
        Front --> A("Person A") --> B("Person B") --> C("Person C") --> Back
    end
```
*   **Common Implementations:** `LinkedList`, `PriorityQueue`, `ArrayDeque`
*   **When to use:** When you need to process elements in the order they are added.

### `Map`: A Key-Value Store
A `Map` is an object that maps **keys to values**. Think of a coat check.

```mermaid
graph TD
    subgraph "Map: Key-Value pairs"
        K1["Key: 'Ticket #12'"] --> V1["Value: 'Brown Coat'"]
        K2["Key: 'Ticket #13'"] --> V2["Value: 'Blue Jacket'"]
    end
    note["Unique keys. Fast lookups."]
```
*   **Common Implementations:** `HashMap`, `LinkedHashMap`, `TreeMap`
*   **When to use:** When you need to associate a unique key with a value for fast retrieval.

---

## 3. How to Choose the Right Collection

This is a critical skill for a Java developer. Use this decision guide to help you choose.

```mermaid
graph TD
    A{Start} --> B{Need key-value pairs?};
    B -- Yes --> C(Use a <b>Map</b>);
    C --> C1{Need keys sorted?};
    C1 -- Yes --> C2(<b>TreeMap</b>);
    C1 -- No --> C3{Need insertion order?};
    C3 -- Yes --> C4(<b>LinkedHashMap</b>);
    C3 -- No --> C5(<b>HashMap</b>);

    B -- No --> D{Need a group of elements?};
    D --> E{Need to prevent duplicates?};
    E -- Yes --> F(Use a <b>Set</b>);
    F --> F1{Need elements sorted?};
    F1 -- Yes --> F2(<b>TreeSet</b>);
    F1 -- No --> F3{Need insertion order?};
    F3 -- Yes --> F4(<b>LinkedHashSet</b>);
    F3 -- No --> F5(<b>HashSet</b>);

    E -- No --> G(Use a <b>List</b>);
    G --> G1{Need fast random access?};
    G1 -- Yes --> G2(<b>ArrayList</b>);
    G1 -- No --> G3(<b>LinkedList</b>);
```

---

## 4. Sorting: `Comparable` vs. `Comparator`

We provided two mechanisms for defining how objects are ordered.

```mermaid
graph TD
    subgraph "Sorting Mechanisms"
        subgraph "Comparable: Natural Ordering (Internal)"
            P["Person implements Comparable<Person>"]
            subgraph P
                direction TB
                id[id]
                name[name]
                compareTo["compareTo(other)<br><i>Compares by 'id'</i>"]
            end
        end

        subgraph "Comparator: Custom Ordering (External)"
            SBN["SortByName<br>implements Comparator<Person>"]
            SBA["SortByAge<br>implements Comparator<Person>"]
            P --> SBN
            P --> SBA
        end

        style P fill:#cde4ff
    end
```

*   **`Comparable`:** For defining the **natural order** of an object. A class implements `Comparable` itself.
*   **`Comparator`:** For defining **custom or external orderings**. A `Comparator` is implemented in a separate class.

**Key Takeaway:** If a class has a clear, single, obvious order, implement `Comparable`. For all other sorting needs, create `Comparator`s.

---

## 5. Your Mission: Custom Sorting

The code in the `code/` directory demonstrates many of the collections we've discussed. Your mission is to add a new custom sorting behavior.

1.  **Find the Code:** Open the `code/src/main/java/com/example/CollectionDemo.java` file.
2.  **Accept the Challenge:** The code already sorts the list of `Person` objects by their natural order (ID) and by name. Your task is to write the code to sort the list by age, from youngest to oldest, using a lambda expression.
3.  **Run and Verify:** Run the code (`mvn compile exec:java`) and check that the final output shows the list of people sorted by age.

<details>
<summary>Stuck? Here's the solution</summary>

```java
// Your Mission: Sort the 'people' list by age using a lambda.
people.sort((p1, p2) -> Integer.compare(p1.getAge(), p2.getAge()));
```
</details>

---

### Key Takeaways

*   **It's All About Interfaces:** The power of the JCF comes from its core interfaces (`List`, `Set`, `Map`, `Queue`). Always code to the interface, not the implementation (e.g., `List<String> names = new ArrayList<>();`).
*   **Use the Right Tool:** Use the flowchart in this chapter to guide your choice. `List` for ordered sequences, `Set` for uniqueness, `Map` for key-value lookups.
*   **`Comparable` vs. `Comparator`:** `Comparable` defines the single, natural order of an object. `Comparator` defines any number of custom, external orderings.
*   **Lambdas Make Sorting Easy:** Since Java 8, using lambda expressions to create `Comparator`s on the fly is the standard, most concise way to perform custom sorts.
