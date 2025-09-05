package com.example;

/**
 * A simple calculator application to demonstrate Java basics.
 * This class showcases variables, operators, and control flow.
 */
public class Calculator {

    public static void main(String[] args) {
        // 1. VARIABLES
        // We declare and initialize variables to hold our data.
        // 'double' is a primitive type for decimal numbers.
        double num1 = 100.0;
        double num2 = 25.0;
        // 'char' is a primitive type for a single character.
        char operator = '*';
        // 'result' is declared but not yet initialized.
        double result;

        System.out.println("--- Java Basics Showcase: Simple Calculator ---");
        System.out.println("Number 1: " + num1);
        System.out.println("Number 2: " + num2);
        System.out.println("Operator: " + operator);
        System.out.println("---------------------------------------------");

        // 2. CONTROL FLOW (Modern Switch Expression)
        // We use a switch expression (Java 14+) to decide which operation to perform.
        // It's concise and returns a value, which we assign to 'result'.
        result = switch (operator) {
            case '+' -> num1 + num2;
            case '-' -> num1 - num2;
            case '*' -> num1 * num2;
            case '/' -> {
                // For division, we add a check to prevent dividing by zero.
                if (num2 == 0) {
                    System.out.println("Error: Cannot divide by zero!");
                    // We yield a value for the switch expression in this block.
                    yield 0.0;
                } else {
                    yield num1 / num2;
                }
            }
            default -> {
                System.out.println("Error: Invalid operator!");
                yield 0.0;
            }
        };

        // 3. OPERATORS and OUTPUT
        // The result of the calculation is printed to the console.
        System.out.println("Result: " + num1 + " " + operator + " " + num2 + " = " + result);

        // 4. MORE CONTROL FLOW (for loop)
        System.out.println("\n--- Demonstrating a for loop ---");
        // A 'for' loop is used to repeat actions a specific number of times.
        for (int i = 1; i <= 3; i++) {
            System.out.println("This is loop iteration number " + i);
        }
    }
}
