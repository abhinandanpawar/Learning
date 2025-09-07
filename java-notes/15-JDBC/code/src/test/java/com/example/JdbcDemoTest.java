package com.example;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * A self-contained test demonstrating modern Java database connectivity.
 * This test uses the H2 in-memory database, so it requires no external setup.
 */
class JdbcDemoTest {

    private static DataSource dataSource;
    private static JdbcTemplate jdbcTemplate;

    @BeforeAll
    static void setup() throws SQLException {
        // Create a DataSource for an H2 in-memory database.
        // "DB_CLOSE_DELAY=-1" keeps the DB open as long as the JVM is running.
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1;");
        config.setUsername("sa");
        config.setPassword("");
        dataSource = new HikariDataSource(config);

        // Initialize the JdbcTemplate with the DataSource
        jdbcTemplate = new JdbcTemplate(dataSource);

        // Create a simple table and insert some data for our tests
        try (Connection conn = dataSource.getConnection(); Statement stmt = conn.createStatement()) {
            stmt.execute("CREATE TABLE users (id INT PRIMARY KEY, name VARCHAR(255))");
            stmt.execute("INSERT INTO users (id, name) VALUES (1, 'Alice')");
        }
    }

    @Test
    @DisplayName("should fetch user name using raw JDBC")
    void rawJdbcExample() throws SQLException {
        System.out.println("Running raw JDBC example...");
        String userName = null;
        String sql = "SELECT name FROM users WHERE id = ?";

        // The full, verbose, try-with-resources ceremony for raw JDBC
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, 1);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    userName = resultSet.getString("name");
                }
            }
        }

        System.out.println("   ...found user: " + userName);
        assertEquals("Alice", userName);
    }

    @Test
    @DisplayName("should fetch user name using Spring JdbcTemplate")
    void jdbcTemplateExample() {
        System.out.println("Running Spring JdbcTemplate example...");
        String sql = "SELECT name FROM users WHERE id = ?";

        // Your Mission:
        // The raw JDBC example above is verbose. Your mission is to get the
        // same result using the much simpler JdbcTemplate.
        // 1. Use the 'jdbcTemplate.queryForObject' method.
        // 2. The first argument is the SQL query.
        // 3. The second argument is the expected return type's class (String.class).
        // 4. The third argument is the value for the '?' parameter in the SQL (the ID 1).
        String userName = null; // Replace this line with your code

        System.out.println("   ...found user: " + userName);
        assertEquals("Alice", userName);
    }

    @AfterAll
    static void tearDown() {
        // In a real app, you might shut down the datasource. For an in-memory H2 test, it's not strictly necessary.
        System.out.println("Tests finished.");
    }
}
