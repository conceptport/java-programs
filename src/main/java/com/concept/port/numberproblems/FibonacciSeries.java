package com.concept.port.numberproblems;

import java.util.HashMap;
import java.util.Map;

public class FibonacciSeries {

    // 1. Recursive (naive)
    public static int fibRecursive(int n) {
        if (n <= 1) return n;
        return fibRecursive(n - 1) + fibRecursive(n - 2);
        // Time: O(2^n) && Space: O(n)
        // -> exponential due to repeated subcalls;
        // space is recursion stack depth
    }

    // 2. Iterative (bottom-up, constant space)
    public static int fibIterative(int n) {
        if (n <= 1) return n;
        int prev2 = 0, prev1 = 1, curr = 0;
        for (int i = 2; i <= n; i++) {
            curr = prev1 + prev2;
            prev2 = prev1;
            prev1 = curr;
        }
        return curr;
        // Time: O(n) && Space: O(1)
        // -> only tracks last two values, no extra array
    }

    // 3. Memoization (top-down DP, cache results)
    public static int fibMemoization(int n, Map<Integer, Integer> memo) {
        if (n <= 1) return n;
        if (memo.containsKey(n)) return memo.get(n);
        int result = fibMemoization(n - 1, memo) + fibMemoization(n - 2, memo);
        memo.put(n, result);
        return result;
        // Time: O(n) && Space: O(n)
        // -> memo map + recursion stack, each subproblem solved once
    }

    // 4. Tabulation (bottom-up DP with array)
    public static int fibTabulation(int n) {
        if (n <= 1) return n;
        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
        // Time: O(n) && Space: O(n)
        // -> full array stored, even though only last two values are ever needed
    }

    // 5. Matrix exponentiation
    public static int fibMatrixExponentiation(int n) {
        if (n <= 1) return n;
        int[][] matrix = {{1, 1}, {1, 0}};
        matrixPower(matrix, n - 1);
        return matrix[0][0];
        // Time: O(log n) && Space: O(log n)
        // -> divide-and-conquer power calculation,
        // recursion stack for exponentiation
    }

    private static void matrixPower(int[][] matrix, int n) {
        if (n <= 1) return;
        int[][] identity = {{1, 1}, {1, 0}};
        matrixPower(matrix, n / 2);
        multiply(matrix, matrix);
        if (n % 2 != 0) multiply(matrix, identity);
    }

    private static void multiply(int[][] a, int[][] b) {
        int x = a[0][0] * b[0][0] + a[0][1] * b[1][0];
        int y = a[0][0] * b[0][1] + a[0][1] * b[1][1];
        int z = a[1][0] * b[0][0] + a[1][1] * b[1][0];
        int w = a[1][0] * b[0][1] + a[1][1] * b[1][1];
        a[0][0] = x;
        a[0][1] = y;
        a[1][0] = z;
        a[1][1] = w;
    }

    // 6. Using Binet's formula (golden ratio, mathematical closed-form)
    public static int fibBinetFormula(int n) {
        double sqrt5 = Math.sqrt(5);
        double phi = (1 + sqrt5) / 2;
        return (int) Math.round(Math.pow(phi, n) / sqrt5);
        // Time: O(log n) && Space: O(1)
        // -> Math.pow is typically log-time internally;
        // precision breaks down for large n due to floating-point rounding
    }

    public static void main(String[] args) {
        int n = 10;

        System.out.println("Recursive: " + fibRecursive(n));
        System.out.println("Iterative: " + fibIterative(n));
        System.out.println("Memoization: " + fibMemoization(n, new HashMap<>()));
        System.out.println("Tabulation: " + fibTabulation(n));
        System.out.println("Matrix exponentiation: " + fibMatrixExponentiation(n));
        System.out.println("Binet's formula: " + fibBinetFormula(n));
    }

}
