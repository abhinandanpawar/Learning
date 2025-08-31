# Java, the JVM, and System Design: A Cheatsheet

| Feature / Concept | JVM / Memory Management Insight | System Design Implication |
| --- | --- | --- |
| **Primitive Types** | Stored on the **stack**. Very fast access. | Use primitives for simple data to improve performance. |
| **Objects** | Stored on the **heap**. Managed by the **Garbage Collector**. | Understand object lifecycle to avoid memory leaks. |
| **`ArrayList`** | Backed by an array on the heap. Resizing can be expensive. | Good for read-heavy scenarios. Fast random access. |
| **`LinkedList`** | Each node is a separate object on the heap. More memory overhead. | Good for write-heavy scenarios (insertions/deletions). |
| **`HashMap`** | Uses an array of linked lists/trees. Fast lookups. | The workhorse of many systems. Use it for fast key-value access. |
| **`synchronized`** | Acquires a lock on an object. The JMM guarantees visibility. | The basic building block for thread safety. |
| **Executor Framework**| Manages a pool of threads. Decouples task submission from execution. | The modern way to handle concurrency. Improves scalability and manageability. |
| **Interfaces** | --- | Promotes **loose coupling**. Allows for flexible and maintainable systems. |
| **Checked Exceptions**| Forces you to handle recoverable errors at compile-time. | Can lead to more robust code, but can also be verbose. |
| **Garbage Collection**| Automatically reclaims memory from unused objects. | Frees you from manual memory management, but can cause pauses. |
| **JIT Compiler** | Compiles bytecode to native code at runtime. Optimizes hot spots. | A key reason for Java's excellent performance in long-running applications. |
