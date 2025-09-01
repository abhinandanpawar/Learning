# 19 - Spring Boot Mini-Playbook

This chapter provides a quick "playbook" for getting started with Spring Boot.

## Starter Stack

To create a simple web application, you only need one dependency.

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>
```

## Mandatory Annotations

These are the core annotations you'll use in almost every Spring Boot application.

*   `@SpringBootApplication`: The main annotation that enables Spring Boot's auto-configuration.
*   `@RestController`: For creating REST APIs.
*   `@GetMapping`, `@PostMapping`, etc.: For mapping HTTP requests to your controller methods.
*   `@Service`: For your business logic layer.
*   `@Repository`: For your data access layer.
*   `@Autowired`: For dependency injection.

## Profiles

> **Best Practice:** Use profiles to manage different configurations for different environments (dev, stage, prod). You can create separate configuration files like `application-dev.yml`, `application-prod.yml`, etc.

## Actuator

The Spring Boot Actuator provides production-ready features for monitoring and managing your application.

*   Add the `spring-boot-starter-actuator` dependency.
*   Access endpoints like `/actuator/health` and `/actuator/metrics`.
*   **Important:** Remember to secure the Actuator endpoints in production!

## Testing Stack

> **Interview Tip:** Be prepared to talk about how you test your Spring Boot applications.

A typical testing stack includes:
*   **JUnit 5:** The standard for unit testing.
*   **Spring Boot Test:** Provides `@SpringBootTest` for integration testing.
*   **Mockito:** For creating mock objects.
*   **Testcontainers:** For running real dependencies (like a database) in a Docker container during your tests.

---
[< Previous: 18 - Modern Memory Management](./18-modern-memory-management.md) | [Up: Table of Contents](./README.md) | [Next: 20 - Architectural Patterns >](./20-architectural-patterns.md)
