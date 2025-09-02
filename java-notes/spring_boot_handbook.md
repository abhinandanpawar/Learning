# Spring Boot Handbook

## Introduction

This handbook is a comprehensive guide for anyone who wants to start with Spring Boot. It covers the core concepts of the Spring Framework, explains how to build applications with Spring Boot, and provides detailed information on topics like data persistence and RESTful web services.

## Core Concepts

At the heart of Spring Boot is the Spring Framework's core module, which provides the foundation for Inversion of Control (IoC) and Dependency Injection (DI).

### Inversion of Control (IoC)

In traditional programming, the flow of control is dictated by the code itself. For example, a class is responsible for creating the objects it needs to do its work.

**Inversion of Control** is a design principle in which the control of object creation and management is transferred from your code to a container or framework. In Spring, the IoC container is responsible for instantiating, configuring, and assembling objects known as **beans**.

### Dependency Injection (DI)

**Dependency Injection** is a pattern we can use to implement IoC. Instead of a component creating its dependencies itself, the IoC container "injects" them into the component.

For example, instead of a `Car` class creating its own `Engine`, the `Engine` is provided to the `Car` by the Spring IoC container.

This leads to loosely coupled systems, which are easier to test and maintain. Dependencies are defined through constructor arguments, factory methods, or properties.

### Spring Beans

In Spring, the objects that form the backbone of your application and that are managed by the Spring IoC container are called **beans**. A bean is an object that is instantiated, assembled, and otherwise managed by a Spring IoC container.

### ApplicationContext

The `ApplicationContext` is the central interface within a Spring application that is used for providing configuration information to the application. It is the central part of the Spring IoC container. It provides a way to create and manage beans, and to access them from your application.

## Getting Started

To create a Spring Boot web application, add the `spring-boot-starter-web` dependency to your `pom.xml`:

```xml
<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
</dependencies>
```

This starter includes all the necessary dependencies for building web, including RESTful, applications using Spring MVC. It uses Tomcat as the default embedded container.

## Basic Application

A minimal Spring Boot application is a class with a `main` method that calls `SpringApplication.run()`:

```java
package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
}
```

The `@SpringBootApplication` annotation is a convenience annotation that adds all of the following:

- **`@Configuration`**: Tags the class as a source of bean definitions for the application context.
- **`@EnableAutoConfiguration`**: Tells Spring Boot to start adding beans based on classpath settings, other beans, and various property settings.
- **`@ComponentScan`**: Tells Spring to look for other components, configurations, and services in the `com/example` package, allowing it to find the controllers.

## Understanding Spring Boot Annotations

Spring Boot's power comes from its extensive use of annotations to simplify configuration and development. Here are some of the most common and important annotations:

### Core Annotations

- **`@SpringBootApplication`**: As mentioned above, this is the main annotation for any Spring Boot application. It's a combination of `@Configuration`, `@EnableAutoConfiguration`, and `@ComponentScan`.

- **`@Configuration`**: Indicates that a class declares one or more `@Bean` methods and may be processed by the Spring container to generate bean definitions and service requests for those beans at runtime.

- **`@EnableAutoConfiguration`**: Enables Spring Boot's auto-configuration mechanism, which attempts to automatically configure your Spring application based on the jar dependencies that you have added.

- **`@ComponentScan`**: Configures component scanning directives for use with `@Configuration` classes. It scans for components like `@Component`, `@Service`, `@Repository`, and `@Controller`/`@RestController`.

### Web Annotations

- **`@RestController`**: A convenience annotation that is itself annotated with `@Controller` and `@ResponseBody`. This annotation is used for creating RESTful web services.

- **`@RequestMapping`**: A general-purpose annotation for mapping web requests onto specific handler classes and/or handler methods. It can be used with HTTP methods like GET, POST, PUT, DELETE, etc.

- **`@GetMapping`**, **`@PostMapping`**, **`@PutMapping`**, **`@DeleteMapping`**: These are shortcuts for `@RequestMapping` for different HTTP methods. For example, `@GetMapping("/hello")` is equivalent to `@RequestMapping(path = "/hello", method = RequestMethod.GET)`.

- **`@PathVariable`**: Indicates that a method parameter should be bound to a URI template variable. For example, in a request to `/users/{id}`, the `{id}` part can be extracted with `@PathVariable`.

- **`@RequestParam`**: Binds a method parameter to a web request parameter.

- **`@RequestBody`**: Indicates that a method parameter should be bound to the body of the web request. Spring will deserialize the incoming HTTP request body into the parameter's type.

### Component Scan Annotations

- **`@Component`**: A generic stereotype annotation for any Spring-managed component.

- **`@Service`**: A specialization of `@Component` for service layer classes.

- **`@Repository`**: A specialization of `@Component` for persistence layer classes (e.g., DAOs). It also enables the translation of persistence-related exceptions into Spring's unified data access exception hierarchy.

### Dependency Injection Annotations

- **`@Autowired`**: Marks a constructor, field, setter method, or config method as to be autowired by Spring's dependency injection facilities.

## REST Controller

Use `@RestController` to create a RESTful web service. `@GetMapping` maps HTTP GET requests to a specific handler method.

```java
package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
        return String.format("Hello %s!", name);
    }
}
```

- `@RestController` combines `@Controller` and `@ResponseBody`, which eliminates the need to annotate every request handling method with `@ResponseBody`.
- `@GetMapping("/hello")` is a shortcut for `@RequestMapping(method = RequestMethod.GET, path = "/hello")`.
- `@RequestParam` binds the value of the query string parameter `name` into the `name` parameter of the `hello()` method.

## Resource Representation

To model a resource, you can use a simple Java class or a Java 17+ record. Spring Boot uses Jackson to automatically marshal instances into JSON.

```java
package com.example.demo;

public record Greeting(long id, String content) { }
```

When a `Greeting` object is returned from a controller method, it will be serialized to JSON, like this:

```json
{
    "id": 1,
    "content": "Hello, World!"
}
```

## Data Persistence with Spring Data JPA

Spring Data JPA makes it easy to work with relational databases. It simplifies the data access layer by providing a set of interfaces that you can extend to create repositories for your entities.

### 1. Add Dependencies

You need the `spring-boot-starter-data-jpa` dependency, and a dependency for your chosen database (e.g., H2, PostgreSQL, MySQL).

```xml
<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-jpa</artifactId>
    </dependency>
    <dependency>
        <groupId>com.h2database</groupId>
        <artifactId>h2</artifactId>
        <scope>runtime</scope>
    </dependency>
</dependencies>
```

### 2. Define a JPA Entity

A JPA entity is a simple Java class that is annotated with `@Entity`. It represents a table in your database.

```java
package com.example.demo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String firstName;
    private String lastName;

    // constructors, getters, and setters
}
```

- **`@Entity`**: Specifies that the class is an entity.
- **`@Id`**: Specifies the primary key of an entity.
- **`@GeneratedValue`**: Provides for the specification of generation strategies for the values of primary keys.

### 3. Create a Repository Interface

Create an interface that extends `CrudRepository` or `JpaRepository`. Spring Data JPA will automatically create an implementation for this interface at runtime.

```java
package com.example.demo;

import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, Long> {
    // You can define custom query methods here
}
```

By extending `CrudRepository`, you get methods for CRUD operations (Create, Read, Update, Delete) out of the box.

### 4. Configure the Database

You can configure the database connection in the `application.properties` file:

```properties
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
```

For an in-memory database like H2, Spring Boot can auto-configure the connection if you have the H2 dependency on the classpath.

## Securing Your Application with Spring Security

Spring Security is a powerful and highly customizable authentication and access-control framework. It is the de-facto standard for securing Spring-based applications.

### 1. Add Spring Security Dependency

To get started with Spring Security, you need to add the `spring-boot-starter-security` dependency to your `pom.xml`:

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-security</artifactId>
</dependency>
```

Once this dependency is on the classpath, Spring Boot will automatically secure all HTTP endpoints with basic authentication.

### 2. Configure Web Security

You can customize the security settings by creating a `SecurityFilterChain` bean in a configuration class.

```java
package com.example.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests((requests) -> requests
                .requestMatchers("/", "/home").permitAll()
                .anyRequest().authenticated()
            )
            .formLogin((form) -> form
                .loginPage("/login")
                .permitAll()
            )
            .logout((logout) -> logout.permitAll());

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user =
             User.withDefaultPasswordEncoder()
                .username("user")
                .password("password")
                .roles("USER")
                .build();

        return new InMemoryUserDetailsManager(user);
    }
}
```

- **`@EnableWebSecurity`**: Enables Spring Security's web security support.
- **`securityFilterChain` bean**: Configures which URL paths should be secured. In this example, `/` and `/home` are public, while all other paths require authentication. It also configures a custom login page.
- **`userDetailsService` bean**: Sets up an in-memory user store with a single user.

## Testing in Spring Boot

Spring Boot provides a rich set of tools for testing your application. The `spring-boot-starter-test` dependency includes libraries like JUnit 5, Mockito, and AssertJ.

### 1. Test Dependencies

The `spring-boot-starter-test` is the primary dependency for testing in Spring Boot. It is usually included by default when you create a new project with Spring Initializr.

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-test</artifactId>
    <scope>test</scope>
</dependency>
```

### 2. Unit Testing with `@SpringBootTest`

The `@SpringBootTest` annotation is used for integration testing. It loads the full application context, which can be useful for testing the interaction between different components.

```java
package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class MyControllerTest {

    @Autowired
    private HelloController controller;

    @Test
    void contextLoads() {
        assertThat(controller).isNotNull();
    }
}
```

### 3. Web Layer Testing with `@WebMvcTest`

To test the web layer without loading the full application context, you can use the `@WebMvcTest` annotation. This annotation will only scan the specified controller and the Spring MVC infrastructure.

You can use `MockMvc` to send HTTP requests to your controller and assert the response.

```java
package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(HelloController.class)
class HelloControllerWebTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void helloShouldReturnDefaultMessage() throws Exception {
        mockMvc.perform(get("/hello"))
            .andExpect(status().isOk())
            .andExpect(content().string("Hello World!"));
    }
}
```

## Monitoring with Spring Boot Actuator

Spring Boot Actuator brings production-ready features to your application. It provides a number of built-in endpoints that you can use to monitor and manage your application.

### 1. Add Actuator Dependency

To use the Actuator, you need to add the `spring-boot-starter-actuator` dependency to your `pom.xml`:

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-actuator</artifactId>
</dependency>
```

### 2. Accessing Actuator Endpoints

By default, most Actuator endpoints are exposed over HTTP at the `/actuator` path. For example, the `health` endpoint is available at `/actuator/health`.

### 3. Common Actuator Endpoints

Here are some of the most common Actuator endpoints:

- **`/actuator/health`**: Shows application health information.
- **`/actuator/info`**: Displays arbitrary application info.
- **`/actuator/beans`**: Displays a complete list of all Spring beans in your application.
- **`/actuator/metrics`**: Shows metrics information for your application.

### 4. Exposing All Endpoints

For security reasons, only the `health` endpoint is exposed over HTTP by default. To expose all endpoints, you can add the following property to your `application.properties` file:

```properties
management.endpoints.web.exposure.include=*
```

**Note:** Be careful when exposing all endpoints, as some of them might contain sensitive information.

## Java 17 Features in Spring Boot

Spring Boot 3 and later versions are built on top of Java 17, which brings several new features to the Java language. You can leverage these features to write more modern and concise Spring applications.

### Java Records

Java Records, introduced in Java 14 and finalized in Java 16, are perfect for creating immutable Data Transfer Objects (DTOs) or resource representations in Spring Boot. A record is a transparent carrier for immutable data.

Instead of writing a traditional class with private fields, a constructor, getters, `equals()`, `hashCode()`, and `toString()` methods, you can define a record in a single line:

```java
public record Greeting(long id, String content) { }
```

This one line of code generates a class with:
- A constructor for all fields (`id` and `content`).
- `public` accessor methods for all fields (e.g., `id()`, `content()`).
- Implementations of `equals()`, `hashCode()`, and `toString()`.

Spring Boot's JSON serialization/deserialization (powered by Jackson) works seamlessly with records, making them an excellent choice for request and response bodies in your REST controllers.

### Text Blocks

Text blocks, introduced in Java 13 and finalized in Java 15, make it easier to work with multiline strings in your code. This is particularly useful for writing embedded JSON, SQL queries, or any other multiline text.

For example, you can write a JSON string like this:

```java
String json = """
{
    "id": 1,
    "content": "Hello, World!"
}
""";
```

This is much more readable than concatenating strings or using escape characters.

## Running the Application

You can run your Spring Boot application from the command line using Maven or Gradle.

### Maven

```bash
./mvnw spring-boot:run
```

To build an executable JAR file:

```bash
./mvnw clean package
java -jar target/<artifact-id>-<version>.jar
```

### Gradle

```bash
./gradlew bootRun
```

To build an executable JAR file:

```bash
./gradlew build
java -jar build/libs/<artifact-id>-<version>.jar
```
