# 22 - Interview & Growth Prep

This chapter provides a checklist of topics to review for interviews and for your continued growth as a Java developer.

## Common Pitfalls to Review

*   **Autoboxing in hot loops:** Creating a lot of wrapper objects in a tight loop can cause GC pressure.
*   **Deadlocks with nested locks:** Be able to explain how deadlocks can occur and how to avoid them.
*   **Misuse of `Optional`:** `Optional` is intended to be used as a return type, not as a field in a class or as a method parameter.
*   **`String` concatenation in loops:** Use `StringBuilder` instead of `+` to concatenate strings in a loop.

## Algorithm Drills

*   **Practice on arrays and strings.** These are the most common topics in coding interviews.
*   **Use Java Streams first.** Try to solve the problem with a functional approach using streams.
*   **Re-implement imperatively.** Then, re-implement the same solution with a traditional `for` loop to compare the performance and readability trade-offs.

## System Design Questions

*   **Sketch a high-level class diagram.**
*   **Sketch a sequence flow diagram.**
*   **Highlight trade-offs immediately.** When you make a design choice, immediately state the trade-offs (e.g., "I'm choosing consistency over latency here because...").

## Staying Up-to-Date

*   **Follow the Java release cadence.** A new version of Java is released every six months. Keep an eye on the new features.
*   **Read blogs and articles.** Follow prominent Java developers and publications.
*   **Contribute to open source.** This is one of the best ways to learn and grow as a developer.

---
[< Previous: 21 - Performance & Observability](./21-performance-and-observability.md) | [Up: Table of Contents](./README.md)
