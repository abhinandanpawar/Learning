# 15 - Core Language Refresh (Java 17+)

This chapter is a quick, reference-style refresh on the modern Java features that a developer with ~3 years of experience should be comfortable with.

> **Best Practice:** Use these modern features to write more concise, readable, and maintainable code.

> **Interview Tip:** Being fluent in modern Java syntax shows that you keep your skills up-to-date.

| Feature | Why It Matters & Quick Reminders |
| --- | --- |
| **Records** | **Boilerplate-free DTOs.** Automatically generates `equals()`, `hashCode()`, `toString()`, and constructor. Use for immutable data carriers. |
| **Switch Expressions** | **Return values from a `switch`.** Avoids fall-through bugs. Use `yield` for multi-line cases. Cleaner and less error-prone than traditional `switch` statements. |
| **Text Blocks** | **Clean multi-line strings.** Use triple quotes (`"""`) to preserve formatting for JSON, SQL, etc. |
| **Sealed Classes** | **Model finite hierarchies.** A `sealed` class can only be extended by the classes in its `permits` clause. Useful for things like Abstract Syntax Trees (ASTs) or state machines. |
| **Pattern Matching (`instanceof`)** | **Fewer casts.** `if (obj instanceof String s)` checks the type and declares a new variable `s` of that type in one go. Can be used in `switch` statements with guards for more complex logic. |

---
[< Previous: 14 - New Java Features](../14-New-Java-Features/README.md) | [Up: Table of Contents](./README.md) | [Next: 16 - Collections & Streams Cheat-Sheet >](./16-collections-and-streams.md)
