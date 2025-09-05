package com.example;

/**
 * Main application to demonstrate bit manipulation techniques.
 */
public class BitwiseDemo {

    // Helper to print a number's binary representation
    public static String toBinary(int n) {
        return String.format("%8s", Integer.toBinaryString(n)).replace(' ', '0');
    }

    public static void main(String[] args) {
        System.out.println("--- Bit Manipulation Showcase ---");

        int a = 5;  // 00000101
        int b = 3;  // 00000011

        System.out.println("a = " + a + " (" + toBinary(a) + ")");
        System.out.println("b = " + b + " (" + toBinary(b) + ")");
        System.out.println("---------------------------------");

        // --- 1. Bitwise Operators ---
        System.out.println("\n1. Bitwise Operators:");
        System.out.println("a & b = " + (a & b) + " (" + toBinary(a & b) + ")  // AND");
        System.out.println("a | b = " + (a | b) + " (" + toBinary(a | b) + ")  // OR");
        System.out.println("a ^ b = " + (a ^ b) + " (" + toBinary(a ^ b) + ")  // XOR");
        System.out.println("~a    = " + (~a) + " (" + Integer.toBinaryString(~a) + ") // NOT");
        System.out.println("a << 1= " + (a << 1) + " (" + toBinary(a << 1) + ") // Left Shift");
        System.out.println("a >> 1= " + (a >> 1) + " (" + toBinary(a >> 1) + ") // Signed Right Shift");

        // --- 2. Classic Problems ---
        System.out.println("\n2. Classic Problems:");

        // Single Number
        int[] nums1 = {4, 1, 2, 1, 2};
        int single = singleNumber(nums1);
        System.out.println("The single number in {4, 1, 2, 1, 2} is: " + single);

        // Hamming Weight (Number of 1 Bits)
        int n = 11; // 1011
        int weight = hammingWeight(n);
        System.out.println("The Hamming weight of " + n + " (" + toBinary(n) + ") is: " + weight);

        // Power of Two
        int p1 = 16; // 10000
        int p2 = 15;
        System.out.println("Is " + p1 + " a power of two? " + isPowerOfTwo(p1));
        System.out.println("Is " + p2 + " a power of two? " + isPowerOfTwo(p2));
    }

    /**
     * Finds the single element in an array where every other element appears twice.
     */
    public static int singleNumber(int[] nums) {
        int result = 0;
        for (int num : nums) {
            result ^= num;
        }
        return result;
    }

    /**
     * Counts the number of '1' bits in the binary representation of an integer.
     */
    public static int hammingWeight(int n) {
        int count = 0;
        while (n != 0) {
            // This trick clears the least significant '1' bit.
            n = n & (n - 1);
            count++;
        }
        return count;
    }

    /**
     * Checks if a number is a power of two.
     */
    public static boolean isPowerOfTwo(int n) {
        if (n <= 0) return false;
        // A power of two has exactly one bit set.
        return (n & (n - 1)) == 0;
    }
}
