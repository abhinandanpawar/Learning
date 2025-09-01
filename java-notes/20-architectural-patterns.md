# 20 - Architectural Patterns: A Principal's Field Guide

Software architecture is the set of high-level decisions that determine the structure of a system. As a principal engineer, you are expected to not just recognize these patterns, but to understand the deep trade-offs they represent.

---

## 1. Hexagonal / Clean Architecture: Protecting Your Core Logic

**System Design Problem:** How do you prevent your core business logic from being tightly coupled to external concerns like your web framework, database, or messaging queue? This coupling makes your code hard to test and hard to change.

**The Pattern:** The Hexagonal (or "Ports and Adapters") Architecture puts your core domain logic at the center. The core logic defines "ports" (Java interfaces) that it needs to interact with the outside world. All external technologies (web controllers, database repositories) are implemented as "adapters" that plug into these ports.

**The Principal's Take:** This is the gold standard for building maintainable, long-lived applications. Your core business logic should not know or care that you are using Spring Boot or JPA. This separation of concerns allows you to swap out technologies (e.g., move from a REST API to a gRPC API) without rewriting your core domain.

---

## 2. CQRS: Separating Reads from Writes

**System Design Problem:** In many applications, the way you write data is very different from the way you read it. For example, you might have a complex, normalized model for writing, but your users need to see a denormalized, aggregated view for reading. Trying to serve both needs with a single model can lead to a messy, inefficient system.

**The Pattern:** Command Query Responsibility Segregation (CQRS) separates the "write" model (Commands) from the "read" model (Queries).
*   **Commands:** These are intent-based objects that change the state of the system (e.g., `PlaceOrderCommand`). They are handled by a write-optimized model.
*   **Queries:** These retrieve data but do not change state. They are handled by a read-optimized model (e.g., a denormalized view in a database).

**The Principal's Take:** CQRS is a powerful pattern, but it adds complexity. Only use it when you have a clear need for separate read/write models. It is often combined with **Event Sourcing**, where all state changes are stored as an immutable sequence of events.

---

## 3. Circuit Breaker: Building Resilient Systems

**System Design Problem:** In a microservices architecture, your service depends on other services. What happens if one of those downstream services becomes slow or fails? The failures can cascade, taking down your entire system.

**The Pattern:** The Circuit Breaker pattern wraps a dangerous operation (like a network call) in a component that monitors for failures.
1.  **Closed:** The circuit is closed, and all calls pass through.
2.  **Open:** If the number of failures exceeds a threshold, the circuit "opens", and all subsequent calls fail immediately without even trying to make the network call.
3.  **Half-Open:** After a timeout, the circuit goes into a "half-open" state and allows a single test call to go through. If it succeeds, the circuit closes. If it fails, it stays open.

**The Principal's Take:** This is a non-negotiable pattern for any production microservice that has downstream dependencies. Use a library like **Resilience4j**.

---

## 4. Saga: Managing Distributed Transactions

**System Design Problem:** How do you maintain data consistency across multiple services when you can't use a traditional, two-phase commit (2PC) database transaction?

**The Pattern:** The Saga pattern models a long-running transaction as a sequence of smaller, local transactions. Each local transaction updates the database and publishes an event to trigger the next step in the saga. If a step fails, the saga executes a series of compensating transactions to undo the previous steps.

**The Principal's Take:** Sagas are complex to implement correctly. There are two main approaches:
*   **Choreography:** Each service listens for events and knows what to do. This is loosely coupled but can be hard to track.
*   **Orchestration:** A central orchestrator (e.g., a process manager like Camunda) is responsible for telling each service what to do. This is more tightly coupled but easier to manage and monitor.

Choose the approach that best fits your team's maturity and your system's complexity.

---
[< Previous: 19 - Spring Boot: A Principal's Production Playbook >](./19-spring-boot-playbook.md) | [Up: Table of Contents](./README.md) | [Next: 21 - Performance & Observability Checklist >](./21-performance-and-observability.md)
