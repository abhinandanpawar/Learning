# 23 - Bit Manipulation

Bit manipulation is the act of algorithmically manipulating bits or other pieces of data shorter than a word. Computer programming tasks that require bit manipulation include low-level device control, error detection and correction algorithms, data compression, encryption algorithms, and optimization.

## Bitwise Operators in Java
Java provides a rich set of bitwise operators:
*   **AND (`&`):** Returns 1 if both bits are 1, otherwise 0.
*   **OR (`|`):** Returns 1 if at least one of the bits is 1, otherwise 0.
*   **XOR (`^`):** Returns 1 if the bits are different, otherwise 0.
*   **NOT (`~`):** Inverts the bits.
*   **Left Shift (`<<`):** Shifts the bits to the left, filling the right side with 0s. `x << 1` is equivalent to `x * 2`.
*   **Right Shift (`>>`):** Shifts the bits to the right. For positive numbers, the left side is filled with 0s. For negative numbers, it's implementation-dependent.
*   **Unsigned Right Shift (`>>>`):** Shifts the bits to the right, filling the left side with 0s, regardless of the sign.

## Common Bit Manipulation Problems

### Single Number
The problem is to find the single element in an array where every other element appears twice. The key to solving this is the XOR operator. `a ^ a = 0` and `a ^ 0 = a`. So, if you XOR all the numbers in the array, the duplicates will cancel each other out, leaving only the single number.

**The Code Example:**
```java
public class SingleNumber {
    public int singleNumber(int[] nums) {
        int result = 0;
        for (int num : nums) {
            result ^= num;
        }
        return result;
    }
}
```

### Number of 1 Bits (Hamming Weight)
The goal is to count the number of '1' bits in the binary representation of an integer.

**The Code Example:**
```java
public class HammingWeight {
    public int hammingWeight(int n) {
        int count = 0;
        while (n != 0) {
            n = n & (n - 1); // This clears the least significant bit
            count++;
        }
        return count;
    }
}
```
The expression `n & (n - 1)` is a clever trick to clear the lowest set bit of `n`. The loop continues until `n` becomes 0.

### Power of Two
The goal is to check if a number is a power of two. A number that is a power of two has only one bit set in its binary representation (e.g., 2 is `10`, 4 is `100`, 8 is `1000`).

**The Code Example:**
```java
public class PowerOfTwo {
    public boolean isPowerOfTwo(int n) {
        if (n <= 0) return false;
        return (n & (n - 1)) == 0;
    }
}
```
If `n` is a power of two, then `n & (n - 1)` will be 0. For example, if `n = 8` (binary `1000`), then `n - 1 = 7` (binary `0111`), and `1000 & 0111 = 0`.
