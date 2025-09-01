# 24 - Java Networking: Talking to the Internet

---

## Part 1: The Basics

Welcome to the world of networking! This is how Java applications talk to each other and to other services on the internet.

### What is Networking?
Networking is about sending and receiving data between two computers. In Java, we use the `java.net` package for this.

### Key Classes for Beginners

*   **`URL`**: Represents a Uniform Resource Locator, which is a pointer to a resource on the internet (e.g., a web page or an image).
*   **`Socket`**: Represents a single connection between two computers. Think of it as a telephone call.
*   **`ServerSocket`**: A special kind of socket that waits for clients to connect. Think of it as a telephone operator waiting for calls.

### Simple Example: Reading from a URL
This example shows how to read the content of a web page.

```java
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

public class UrlReader {
    public static void main(String[] args) throws Exception {
        URL studytonight = new URL("https://www.studytonight.com/");
        try (BufferedReader in = new BufferedReader(
                new InputStreamReader(studytonight.openStream()))) {
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                System.out.println(inputLine);
            }
        }
    }
}
```

---

## Part 2: The Principal's Deep Dive

### System Design Context: Blocking I/O vs. Non-Blocking I/O
The simple `Socket` and `ServerSocket` classes use **blocking I/O**. This means that when a thread makes a call to `read()` or `write()`, it will "block" (wait) until the data is available.

In a high-performance server that needs to handle thousands of concurrent connections, you cannot afford to have a thread for each connection, as most of them will just be sitting idle, waiting for data. This is incredibly inefficient and does not scale.

**The Principal's Take:** For any serious server-side application, you must use **non-blocking I/O (NIO)**.

### The Java NIO APIs
Java has had NIO capabilities since version 1.4, but the APIs (`Selector`, `Channel`) are notoriously difficult to use correctly.

**Production-Oriented Advice:** Instead of using the low-level Java NIO APIs directly, a principal engineer will almost always use a high-level library that provides a clean, asynchronous, event-driven API on top of NIO.

*   **The Industry Standard:** **Netty**. Netty is a high-performance, asynchronous event-driven network application framework. It is the foundation of most high-performance Java networking applications, including many popular web servers and microservice frameworks.
*   **Modern Java:** For simpler use cases, `CompletableFuture` (which we discussed in the Concurrency chapter) can be used to handle asynchronous I/O in a clean way.

### Complete, Runnable Example: A Simple Non-Blocking Echo Server with NIO
This example is more complex and shows the "callback-style" nature of NIO. It is provided to illustrate the complexity that libraries like Netty hide from you.

```java
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class NioEchoServer {
    public static void main(String[] args) throws IOException {
        Selector selector = Selector.open();
        ServerSocketChannel serverSocket = ServerSocketChannel.open();
        serverSocket.bind(new InetSocketAddress("localhost", 5454));
        serverSocket.configureBlocking(false);
        serverSocket.register(selector, SelectionKey.OP_ACCEPT);
        ByteBuffer buffer = ByteBuffer.allocate(256);

        System.out.println("Server started on port 5454");

        while (true) {
            selector.select();
            Set<SelectionKey> selectedKeys = selector.selectedKeys();
            Iterator<SelectionKey> iter = selectedKeys.iterator();
            while (iter.hasNext()) {
                SelectionKey key = iter.next();

                if (key.isAcceptable()) {
                    SocketChannel client = serverSocket.accept();
                    client.configureBlocking(false);
                    client.register(selector, SelectionKey.OP_READ);
                }

                if (key.isReadable()) {
                    SocketChannel client = (SocketChannel) key.channel();
                    client.read(buffer);
                    buffer.flip();
                    client.write(buffer);
                    buffer.clear();
                }
                iter.remove();
            }
        }
    }
}
```

---
[< Previous: 23 - A Deep Dive into the Stream API](./23-stream-api-deep-dive.md) | [Up: Table of Contents](./README.md)
