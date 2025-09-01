# 20 - Architectural Patterns

This chapter provides a quick reference to common architectural patterns that every developer with ~3 years of experience should be able to recognize.

> **Interview Tip:** In a system design interview, being able to identify and discuss the trade-offs of these patterns is a huge plus.

| Pattern | When to Apply | Java Touchpoint |
| --- | --- | --- |
| **CQRS + Event Sourcing** | When you need to separate read and write workloads, or when you need a complete audit trail of all changes. | Use Spring Data projections for the read side and a message bus like **Apache Kafka** for the event log. |
| **Hexagonal / Clean Architecture**| When you want to keep your core business logic independent of external frameworks and technologies (like the web framework or the database). | The "ports" are **Java interfaces**, and the "adapters" are the concrete implementations (e.g., a Spring MVC controller or a JPA repository). |
| **Circuit Breaker** | When you have a downstream dependency that might be unstable. The circuit breaker prevents a single failing service from cascading failures throughout your system. | Use a library like **Resilience4j** or the **Spring Cloud Circuit Breaker** project. |
| **Saga** | When you need to manage a transaction that spans across multiple services in a microservices architecture. | **Orchestration:** Use a process manager like **Camunda**. **Choreography:** Use an event bus like **Apache Kafka** or **RabbitMQ**. |

---
[< Previous: 19 - Spring Boot Mini-Playbook](./19-spring-boot-playbook.md) | [Up: Table of Contents](./README.md) | [Next: 21 - Performance & Observability >](./21-performance-and-observability.md)
