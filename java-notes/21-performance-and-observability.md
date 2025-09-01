# 21 - Performance & Observability: A Principal's Checklist

A principal engineer is responsible for the non-functional requirements of a system, and performance and observability are at the top of that list. Your service is not "done" until it is observable and performant.

---

## 1. Observability: The Three Pillars

Your goal is to be able to answer any question about your system's behavior without having to ship new code. This requires three pillars:

### a. Structured Logging
*   **The Standard:** Use **Logback** or **Log4j2**. Configure them to output structured JSON. This makes logs machine-readable and searchable in tools like Splunk or Elasticsearch.
*   **Correlation IDs:** This is non-negotiable. Use a **MDC (Mapped Diagnostic Context)** to add a `traceId` to every log message for a given request. This allows you to trace a single request through multiple services.
*   **What to Log:** Log key business events, entry/exit points of major components, and errors. **Never log sensitive data.**

### b. Metrics
*   **The Standard:** Use **Micrometer** as your application metrics facade. It's the standard in the Spring ecosystem.
*   **The "Golden Signals":** For any service, you should track:
    1.  **Latency:** The time it takes to serve a request. Track the average, but more importantly, the 95th and 99th percentiles (p95, p99). Averages hide outliers.
    2.  **Traffic:** The number of requests per second (RPS).
    3.  **Errors:** The number of requests that result in an error.
    4.  **Saturation:** How "full" your service is (e.g., CPU usage, thread pool queue size).
*   **Dashboarding:** Use a tool like **Grafana** to build dashboards that visualize these key metrics.

### c. Distributed Tracing
*   **The Standard:** Use **OpenTelemetry** for instrumenting your code to generate traces. A trace follows a single request as it flows through multiple services.
*   **Tools:** Send your traces to a backend like **Jaeger** or **Datadog** for visualization and analysis.

---

## 2. Performance Engineering: A Scientific Approach

**The Principal's Take:** Performance tuning is not guesswork. It's a scientific process of forming a hypothesis, measuring, and then making a change.

### a. Benchmarking
*   **The Tool:** Use **JMH (Java Microbenchmark Harness)** for any micro-benchmarks. It's designed to handle the complexities of the JVM (like JIT warm-up) to give you accurate results.
*   **What to Benchmark:** Don't benchmark trivial code. Benchmark small, critical, hot-spot methods where performance is critical.

### b. Profiling
*   **The Tool:** Use **async-profiler**. It's the industry standard for low-overhead, accurate profiling of Java applications in production.
*   **How to Use It:**
    1.  Generate a **flame graph** to get a visual representation of where your CPU time is being spent.
    2.  Use it to profile memory allocations (`-e alloc`) to find the source of memory pressure.
*   **Continuous Profiling:** For mature systems, consider running a profiler continuously in production to catch performance regressions before they become major issues.

---
[< Previous: 20 - Architectural Patterns: A Principal's Field Guide >](./20-architectural-patterns.md) | [Up: Table of Contents](./README.md) | [Next: 22 - Interview & Growth Prep: Leveling Up >](./22-interview-and-growth-prep.md)
