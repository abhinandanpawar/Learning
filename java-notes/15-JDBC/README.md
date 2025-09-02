# 15 - JDBC and Database Connectivity

Java Database Connectivity (JDBC) is a Java API that defines how a client may access a database. It provides methods for querying and updating data in a database and is oriented towards relational databases.

## What is JDBC?
JDBC is an abstraction layer that allows users to choose between databases. JDBC enables developers to write database applications in Java, without having to concern themselves with the underlying details of a particular database.

## The Role of the Driver in JDBC
The JDBC Driver is a software component that enables a Java application to interact with a database. JDBC drivers are vendor-specific implementations of the abstract classes provided by the JDBC API. Each driver must provide implementations for the following classes of the `java.sql` package: `Connection`, `Statement`, `PreparedStatement`, `CallableStatement`, `ResultSet`, and `Driver`.

## The `Class.forName()` method
This method is used to load the driver that will establish a connection to the database. For example:
```java
try {
    Class.forName("com.mysql.jdbc.Driver");
} catch (ClassNotFoundException e) {
    // Handle exception
}
```

## `PreparedStatement` vs. `Statement`
*   **`Statement`:** Used for general-purpose access to your database. It's useful when you're using static SQL statements at runtime.
*   **`PreparedStatement`:** Used when you want to execute a SQL statement repeatedly. `PreparedStatement`s are precompiled, which can lead to better performance. They are also more secure because they help prevent SQL injection attacks.

## `CallableStatement`
A `CallableStatement` is used to execute stored procedures from your Java application. Stored procedures are precompiled SQL code that is stored in the database. Using stored procedures can improve security and modularity. You can prepare a `CallableStatement` using the `prepareCall()` method of the `Connection` object.

## Connection Pooling
Interacting with a database can be costly, especially when it comes to opening and closing connections. A connection pool is a cache of database connections maintained so that the connections can be reused when future requests to the database are required. Connection pooling can significantly improve the performance of your application.

## A Practical Example
Here is a simple example of how to connect to a MySQL database and execute a query.

```java
import java.sql.*;

class MysqlCon {
    public static void main(String args[]) {
        try {
            // 1. Load the driver
            Class.forName("com.mysql.jdbc.Driver");

            // 2. Create a connection
            Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/sonoo", "root", "root");

            // 3. Create a statement
            Statement stmt = con.createStatement();

            // 4. Execute a query
            ResultSet rs = stmt.executeQuery("select * from emp");

            // 5. Process the result set
            while (rs.next()) {
                System.out.println(rs.getInt(1) + "  " + rs.getString(2) + "  " + rs.getString(3));
            }

            // 6. Close the connection
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
```

This example shows the basic steps of working with JDBC: loading the driver, establishing a connection, creating a statement, executing a query, processing the results, and closing the connection.
