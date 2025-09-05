package com.example;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

/**
 * Main application to demonstrate the modern Java HttpClient.
 */
public class ApiClientDemo {

    public static void main(String[] args) {
        System.out.println("--- Java 11 HttpClient Showcase ---");

        // 1. Create an HttpClient. It's often best to create one and reuse it.
        HttpClient client = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_2)
                .followRedirects(HttpClient.Redirect.NORMAL)
                .build();

        // 2. Create an HttpRequest.
        // We will use a public API that returns information about a random cat fact.
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://catfact.ninja/fact"))
                .GET() // This is the default, but it's good to be explicit.
                .build();

        try {
            System.out.println("Sending request to: " + request.uri());

            // 3. Send the request and receive the response.
            // We ask for the body to be handled as a String.
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // 4. Process the response.
            System.out.println("Status Code: " + response.statusCode());
            System.out.println("Response Body (JSON):");
            System.out.println(response.body());

        } catch (IOException | InterruptedException e) {
            System.err.println("Error sending request: " + e.getMessage());
            // In a real application, you'd handle this more gracefully.
            Thread.currentThread().interrupt(); // Restore the interrupted status
        }
    }
}
