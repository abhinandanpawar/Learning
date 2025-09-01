# 21 - Performance & Observability Checklist

This chapter provides a checklist of best practices for performance and observability in your Java applications.

## Logging

*   **Use a structured logging library** like Logback or Log4j2.
*   **Log Correlation IDs in every request.** Use a `MDC` (Mapped Diagnostic Context) to automatically add a correlation ID to every log message. This is crucial for tracing requests across multiple services.
*   **Don't log sensitive information.** Be careful not to log passwords, API keys, or other sensitive data.

## Metrics

*   **Track the right things.** Don't just track averages; track percentiles (e.g., 95th, 99th) for things like request latency. Averages can hide important spikes.
*   **Use Micrometer.** Micrometer is the standard instrumentation library for JVM-based applications. It integrates with Spring Boot and can send metrics to a variety of monitoring systems (Prometheus, Datadog, etc.).

## Benchmarking

*   **Use JMH (Java Microbenchmark Harness).** JMH is the standard tool for writing and running benchmarks for Java code.
*   **Beware of the JIT.** The JIT compiler can have a big impact on your benchmark results. Be sure to include a warm-up phase in your benchmarks.
*   **Don't benchmark in unit tests.** Unit tests are for correctness, not performance. The JIT warm-up and other factors can skew your results.

## Profiling

*   **Familiarize yourself with a profiler.** **Async Profiler** is a powerful and low-overhead profiler for Java.
*   **Learn to read flame graphs.** Flame graphs are a great way to visualize where your application is spending its time (both on-CPU and off-CPU).

---
[< Previous: 20 - Architectural Patterns](./20-architectural-patterns.md) | [Up: Table of Contents](./README.md) | [Next: 22 - Interview & Growth Prep >](./22-interview-and-growth-prep.md)
