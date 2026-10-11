package com.concept.port.numberproblems;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

public class FactorialCalculation {

    // 1. Recursive
    public static long factorialRecursive(int n) {
        if (n <= 1) return 1;
        return n * factorialRecursive(n - 1);
        // Time: O(n) && Space: O(n)
        // -> recursion call stack depth of n
    }

    // 2. Iterative
    public static long factorialIterative(int n) {
        long result = 1;
        for (int i = 2; i <= n; i++) {
            result *= i;
        }
        return result;
        // Time: O(n) && Space: O(1)
        // -> single accumulator variable, no stack growth
    }

    // 3. Tail recursion (accumulator passed through calls)
    public static long factorialTailRecursive(int n, long accumulator) {
        if (n <= 1) return accumulator;
        return factorialTailRecursive(n - 1, n * accumulator);
        // Time: O(n) && Space: O(n)
        // -> Java does not optimize tail calls,
        // so stack still grows despite the pattern
    }

    // 4. Using BigInteger (handles very large n without overflow)
    public static BigInteger factorialBigInteger(int n) {
        BigInteger result = BigInteger.ONE;
        for (int i = 2; i <= n; i++) {
            result = result.multiply(BigInteger.valueOf(i));
        }
        return result;
        // Time: O(n * M(d)) && Space: O(d) -> d = digit count of result,
        // M(d) = cost of big-number multiplication, grows with result size
    }

    // 5. Using Java 8 Streams
    public static long factorialStreams(int n) {
        return java.util.stream.LongStream.rangeClosed(1, n)
                .reduce(1, (a, b) -> a * b);
        // Time: O(n) && Space: O(1)
        // -> excluding stream internal pipeline overhead
    }

    // 6. Memoization (cache computed factorials, useful for repeated queries)
    public static long factorialMemoization(int n, Map<Integer, Long> memo) {
        if (n <= 1) return 1;
        if (memo.containsKey(n)) return memo.get(n);
        long result = n * factorialMemoization(n - 1, memo);
        memo.put(n, result);
        return result;
        // Time: O(n) && Space: O(n)
        // -> memo map + recursion stack, avoids recompute on repeated calls
    }

    public static void main(String[] args) {
        int n = 10;

        System.out.println("Recursive: " + factorialRecursive(n));
        System.out.println("Iterative: " + factorialIterative(n));
        System.out.println("Tail recursive: " + factorialTailRecursive(n, 1));
        System.out.println("BigInteger: " + factorialBigInteger(20)); // large n, avoids overflow
        System.out.println("Streams: " + factorialStreams(n));
        System.out.println("Memoization: " + factorialMemoization(n, new HashMap<>()));
    }
}
