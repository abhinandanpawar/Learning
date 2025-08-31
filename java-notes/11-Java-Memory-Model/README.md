# 11 - The Java Memory Model

The Java Memory Model (JMM) defines how threads interact through memory and how changes to variables by one thread are made visible to other threads.

## 1. Stack and Heap Memory

The Java Virtual Machine (JVM) divides memory into two main parts: the stack and the heap.

### Stack Memory

*   Each thread has its own private stack.
*   The stack stores local variables and partial results, and plays a part in method invocation and return.
*   Variables on the stack exist only as long as the method that created them is running.
*   Memory on the stack is managed automatically.

### Heap Memory

*   The heap is a shared runtime data area where objects are allocated.
*   All objects, including arrays, are created on the heap.
*   The heap is shared among all threads.
*   Memory on the heap is managed by the Garbage Collector.

## 2. Garbage Collection

Garbage Collection (GC) is the process of automatically freeing up memory on the heap that is no longer being used by the application.

When an object is no longer referenced by any part of the program, it becomes eligible for garbage collection. The garbage collector runs periodically to find and reclaim the memory used by these objects.

You can suggest that the JVM run the garbage collector by calling `System.gc()`, but there is no guarantee that it will run.

## 3. Metaspace (formerly PermGen)

Metaspace is a native memory region that stores metadata about the classes and methods in the application. This includes information like the runtime constant pool, method data, and method code.

Prior to Java 8, this was known as the Permanent Generation (PermGen) and was part of the heap, which could lead to `OutOfMemoryError: PermGen space` issues. Metaspace is allocated from native memory and is only limited by the amount of available native memory.

---

[Previous: 10 - Multithreading and Concurrency](../10-Multithreading-and-Concurrency/README.md) | [Next: 12 - System Design with Java](../12-System-Design-with-Java/README.md)
