# System Design Poster: A Simple Web App

## The Big Picture: How a Web App Works

When you visit a website, like our e-commerce store, there are three main parts that work together. Here's a simple diagram to show how they are connected:

```mermaid
graph TD
    A[Client (Your Computer)] -- HTTP Request --> B[Server (Java Application)]
    B -- HTTP Response --> A
    B -- SQL Query --> C[Database]
    C -- SQL Result --> B

    subgraph "Your Computer"
        A
    end

    subgraph "Our Servers"
        B
        C
    end
```

Here's a breakdown of each part:

1.  **The Client:** This is the web browser on your computer or phone. It's what you see and interact with. It shows you the website and sends your requests (like "add to cart") to the server.

2.  **The Server:** This is a powerful computer that runs the main logic of our application. It receives requests from your computer, processes them, and sends back the results. This is where most of our Java code will live.

3.  **The Database:** This is where all the data is stored. It stores all the information about our products, users, and orders.

---

## <a name="client-deep-dive"></a>Client Deep Dive

**(This section would be "folded" or hidden until the user "clicks" on the link above.)**

The client is usually written in HTML, CSS, and JavaScript. It doesn't typically run any Java code. Its job is to talk to our Java server.

## <a name="server-deep-dive"></a>Server Deep Dive

This is where Java shines! Our server will be a Java application.

*   **Java Classes:** We'll have classes like `ProductService`, `OrderService`, and `UserService` that contain the business logic.
*   **Data Structures:** We'll use `ArrayList`s to hold lists of products, and `HashMap`s to look up users by their username.
*   **Data Transfer Objects (DTOs):** When the server sends data to the client, it often uses special objects called DTOs. These are simple Java objects that just hold data.

## <a name="database-deep-dive"></a>Database Deep Dive

Our Java server will talk to the database to save and retrieve data. We'll use a Java library called **JDBC (Java Database Connectivity)** to do this.

---

This poster gives you a map of how a real-world Java application is put together. We'll be referring back to it as we learn more about Java.
