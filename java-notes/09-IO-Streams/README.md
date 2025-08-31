# 09 - Java IO Streams

Java's I/O (Input/Output) is based on the concept of streams. A stream is a sequence of data.

## 1. What are Streams?

*   **Input Stream:** Used to read data from a source (e.g., a file).
*   **Output Stream:** Used to write data to a destination (e.g., a file).

## 2. Byte Streams vs. Character Streams

*   **Byte Streams:** Perform I/O of 8-bit bytes. They are suitable for handling binary data (e.g., images, videos). The base classes are `InputStream` and `OutputStream`.

*   **Character Streams:** Perform I/O of 16-bit Unicode characters. They are suitable for handling text data. The base classes are `Reader` and `Writer`.

## 3. Common Stream Classes

### Reading and Writing Files (Byte Streams)

```java
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

// ...

try (FileInputStream in = new FileInputStream("input.txt");
     FileOutputStream out = new FileOutputStream("output.txt")) {

    int c;
    while ((c = in.read()) != -1) {
        out.write(c);
    }
} catch (IOException e) {
    e.printStackTrace();
}
```

### Reading and Writing Files (Character Streams)

```java
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

// ...

try (FileReader in = new FileReader("input.txt");
     FileWriter out = new FileWriter("output.txt")) {

    int c;
    while ((c = in.read()) != -1) {
        out.write(c);
    }
} catch (IOException e) {
    e.printStackTrace();
}
```

## 4. Buffered Streams

Buffered streams read or write data in chunks (a buffer), which can significantly improve performance compared to reading or writing one byte/character at a time.

*   `BufferedInputStream` and `BufferedOutputStream` for byte streams.
*   `BufferedReader` and `BufferedWriter` for character streams.

```java
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

// ...

try (BufferedReader br = new BufferedReader(new FileReader("input.txt"))) {
    String line;
    while ((line = br.readLine()) != null) {
        System.out.println(line);
    }
} catch (IOException e) {
    e.printStackTrace();
}
```

## 5. The `try-with-resources` Statement

The `try-with-resources` statement, introduced in Java 7, ensures that each resource is closed at the end of the statement. This is a much cleaner way to handle resources like streams, which must be closed after use. All the examples above use `try-with-resources`.

---

[Previous: 08 - Generics](../08-Generics/README.md) | [Next: 10 - Multithreading and Concurrency](../10-Multithreading-and-Concurrency/README.md)
