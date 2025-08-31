# Chapter 2: Loops - Doing Things Over and Over

---

## The Left Page: A Picture of a Robot Stacking Blocks

**(Imagine a picture here of a friendly robot stacking 5 blocks on top of each other.)**

### The Code Spell

Java the Wizard says: "We want our robot to stack 5 blocks. We could tell it to 'stack a block' 5 times, but there's a better way! We can use a **for loop**."

```java
for (int i = 0; i < 5; i++) {
    robot.stackBlock();
}
```

---

## The Right Page: The Story of the Code

### 1. The Story

Our friendly robot needs to stack 5 blocks. Instead of writing the same command 5 times, we can use a loop to tell the robot to repeat the action.

### 2. The Code Explained

A `for` loop has three parts in the parentheses, separated by semicolons:

*   `int i = 0;`: The **initialization**. We create a counter variable `i` and start it at 0.
*   `i < 5;`: The **condition**. The loop will keep running as long as `i` is less than 5.
*   `i++`: The **increment**. After each time the loop runs, we add 1 to `i`.

So, this loop will run for `i = 0`, `i = 1`, `i = 2`, `i = 3`, and `i = 4`. That's 5 times!

### 3. Under the Hood: Inside the JVM House

Java the Wizard says: "Let's see what happens in the **Upstairs Room** (the **Method Stack**) when we run this loop."

1.  A new suitcase for our counter `i` is created in the stack frame. It starts with the value `0`.
2.  The JVM checks if `i < 5`. It is! So, it runs the code inside the loop.
3.  The JVM increments `i` to `1`.
4.  It goes back to step 2 and checks the condition again.
5.  This continues until `i` is `5`. Now, `i < 5` is false, so the loop stops.

**(Imagine a cartoon picture of the `i` suitcase in the stack, with the number inside it changing from 0 to 1, to 2, to 3, to 4, and then the loop stopping.)**

---

### Your Next Experiment!

**Predict:** What happens if you change the code to `i < 10`? How many blocks will the robot stack?

**Run:** Try it out in an online Java sandbox!

**Reflect:** Can you write a loop that counts down from 5 to 1?
