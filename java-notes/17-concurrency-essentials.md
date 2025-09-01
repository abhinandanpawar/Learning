# 17 - Concurrency Essentials

This chapter provides a quick reference for choosing the right concurrency API for a given task.

> **Interview Tip:** Concurrency is a common topic in interviews for mid-level and senior roles. Be prepared to discuss the trade-offs between different concurrency primitives.

| Need | API to Reach For | Pro Tip |
| --- | --- | --- |
| **Offload blocking I/O** | `CompletableFuture.supplyAsync` | Chain with `thenApplyAsync` to keep work off the caller thread and avoid blocking your main application threads. |
| **CPU-bound parallelism** | `ForkJoinPool.commonPool()` or a custom `ExecutorService` | Keep the number of threads close to the number of CPU cores. Avoid blocking calls inside these tasks to prevent starving the pool. |
| **Periodic tasks** | `ScheduledExecutorService` | Prefer `scheduleAtFixedRate` over `Timer` for better accuracy and error handling. |
| **Rate-limit access** | `Semaphore` | Acquire permits in a `try-with-resources` block for automatic release, even if exceptions occur. |
| **Read-heavy, write-rare map** | `ConcurrentHashMap` | Excellent performance for concurrent reads. |
| **One-time data exchange** | `Exchanger` | A simple way for two threads to swap data. |
| **Wait for multiple operations** | `CountDownLatch` or `Phaser` | `CountDownLatch` is for a one-time event. `Phaser` is more flexible and can be used for multiple phases. |

---
[< Previous: 16 - Collections & Streams Cheat-Sheet](./16-collections-and-streams.md) | [Up: Table of Contents](./README.md) | [Next: 18 - Modern Memory Management >](./18-modern-memory-management.md)
