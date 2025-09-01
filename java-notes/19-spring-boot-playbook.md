# 19 - Spring Boot: A Principal's Production Playbook

Spring Boot is more than a framework; it's a platform for building production-grade, standalone applications. A principal engineer should have a strong, opinionated view on how to use it effectively.

---

## 1. The "Right" Dependencies: A Starter Stack

Your `pom.xml` or `build.gradle` is a statement of your service's architecture. Keep it clean.

**System Design Context:** Every dependency you add increases the binary size, attack surface, and cognitive load of your application. Be deliberate.

### Core Stack for a RESTful Service:
```xml
<!-- For building web applications, including RESTful applications -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>

<!-- For production-ready features like health checks and metrics -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-actuator</artifactId>
</dependency>

<!-- For writing clean, modern data access layers -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>

<!-- For robust testing -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-test</artifactId>
    <scope>test</scope>
</dependency>
```

---

## 2. Structuring Your Application: A Clean Architecture

**The Principal's Take:** Don't just throw all your classes in one package. Structure your application to reflect its architecture. A simple, clean architecture separates concerns:

*   `com.example.app.web`: Controllers (`@RestController`) - The entry point to your application.
*   `com.example.app.service`: Services (`@Service`) - The business logic.
*   `com.example.app.repository`: Repositories (`@Repository`) - The data access layer.
*   `com.example.app.domain`: Your domain objects (JPA Entities, Records).

---

## 3. Configuration: Your Application's Control Panel

**Production-Oriented Advice:** Externalize your configuration. Never hard-code connection strings, API keys, or other environment-specific values.

*   **Use `application.yml` (or `.properties`)**: This is the standard for Spring Boot configuration.
*   **Use Profiles:** Define profiles (`dev`, `staging`, `prod`) in separate `application-{profile}.yml` files. Activate a profile with the `spring.profiles.active` property.
*   **Use `@ConfigurationProperties`:** For type-safe access to your configuration. Create a record or class that maps to a prefix in your YAML file. This is much cleaner than scattering `@Value` annotations throughout your code.

---

## 4. The Definitive Testing Strategy

**The Principal's Take:** Your test suite is a safety net that enables you to refactor and deploy with confidence. A principal engineer advocates for a balanced test pyramid.

*   **Unit Tests (`@Test` with Mockito):** The base of your pyramid. Test your services and other business logic components in isolation. Mock their dependencies. These are fast.
*   **Integration Tests (`@SpringBootTest`):** Test the integration between your service and a real dependency, like a database.
    *   **The Gold Standard: `Testcontainers`**. Use Testcontainers to spin up a real database in a Docker container for your integration tests. This is much more reliable than using an in-memory database like H2, which can behave differently from your production database.
*   **API / End-to-End Tests:** Use `@SpringBootTest` with `webEnvironment = WebEnvironment.RANDOM_PORT` and `TestRestTemplate` to make real HTTP calls to your running application.

---
[< Previous: 18 - Modern Memory Management: A Principal's Guide >](./18-modern-memory-management.md) | [Up: Table of Contents](./README.md) | [Next: 20 - Architectural Patterns: A Principal's Field Guide >](./20-architectural-patterns.md)
