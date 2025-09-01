# 18 - Modern Memory Management Quick View

This chapter provides a high-level view of modern memory management in the JVM.

## Key Concepts

*   **Heap Regions (Young, Old):** These still exist, but with modern garbage collectors like **G1** and **ZGC**, you rarely need to tune their sizes manually. The JVM does a good job of sizing them dynamically.

*   **Metaspace:** This replaced the old "PermGen" space. It's where the JVM stores metadata about your classes. Metaspace is allocated from native memory, so it's only limited by the amount of available system memory.
    > **Interview Tip:** Metaspace leaks are a common problem in applications that do a lot of dynamic class loading, such as applications that use hot reloading or certain types of libraries.

*   **Allocation Spikes:** A sudden increase in object allocation can cause GC pauses.
    > **Best Practice:** Use profiling tools to watch for allocation spikes. A common rule of thumb is that large objects (>32 KB) often skip the Eden space and are allocated directly in the old generation.

## Essential Tools

*   **VisualVM:** A great tool for taking and analyzing heap snapshots to find memory leaks.
*   **Java Flight Recorder (JFR):** A low-overhead profiling tool that's built into the JVM. It's safe to use in production to sample memory allocations and GC activity.

## GC Flags Worth Memorizing

These are some of the most common and useful GC flags for modern Java applications.

```bash
# Use the G1 Garbage Collector (the default in modern Java)
-XX:+UseG1GC

# Unlock experimental VM options (needed for some advanced flags)
-XX:+UnlockExperimentalVMOptions

# Save memory by de-duplicating identical strings in the heap
-XX:+UseStringDeduplication
```

---
[< Previous: 17 - Concurrency Essentials](./17-concurrency-essentials.md) | [Up: Table of Contents](./README.md) | [Next: 19 - Spring Boot Mini-Playbook >](./19-spring-boot-playbook.md)
