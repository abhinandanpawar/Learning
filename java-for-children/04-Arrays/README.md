# Chapter 4: Arrays - Storing Lots of Things

---

## The Left Page: A Picture of an Egg Carton

**(Imagine a picture here of an egg carton with 6 eggs in it.)**

### The Code Spell

Java the Wizard says: "What if we want to store a whole bunch of things, not just one? For that, we can use an **array**. An array is like an egg carton: a container that holds a fixed number of items of the same type."

```java
// An array to hold 6 integers
int[] myNumbers = new int[6];

// Put numbers in the array
myNumbers[0] = 10;
myNumbers[1] = 20;
// ...and so on
```

---

## The Right Page: The Story of the Code

### 1. The Story

We have a bunch of numbers that we want to keep together. We can use an array, which is like a container with a fixed number of slots. In our example, our array has 6 slots.

### 2. The Code Explained

*   `int[]`: This tells Java that we want to create an array of integers. The `[]` is what makes it an array.
*   `myNumbers`: This is the name of our array.
*   `new int[6]`: This creates a new array object that has 6 slots for integers.
*   `myNumbers[0] = 10;`: We use square brackets `[]` to access the slots in the array. The slots are numbered starting from **0**, not 1. This is a very important rule in Java!

### 3. Under the Hood: Inside the JVM House

Java the Wizard says: "Arrays are objects, so they live in the **Downstairs Toy Box** (the **Heap**)."

1.  When you create the `myNumbers` variable, a new suitcase is created in the **Upstairs Room** (the **Stack**). This suitcase holds a remote control that points to the array.
2.  The array **object** itself is created in the **Downstairs Toy Box** (the **Heap**). It's a long box with 6 compartments, and each compartment can hold an integer.

**(Imagine a cartoon picture of the JVM House. A suitcase labeled "myNumbers" is in the Upstairs Room, with an arrow pointing to an egg carton-like box in the Downstairs Toy Box. The egg carton has 6 slots.)**

---

### Your Next Experiment!

**Predict:** Can you use a `for` loop (from Chapter 2) to print out all the numbers in the `myNumbers` array?

**Run:** Try it out in an online Java sandbox!

**Reflect:** What happens if you try to access `myNumbers[6]`? (Remember, the slots are numbered 0 to 5).
