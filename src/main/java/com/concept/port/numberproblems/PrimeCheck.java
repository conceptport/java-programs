package com.concept.port.numberproblems;

import java.util.Arrays;

public class PrimeCheck {

    // 1. Basic approach — check divisibility up to n-1
    public static boolean isPrimeBasic(int n) {
        if (n <= 1) return false;
        for (int i = 2; i < n; i++) {
            if (n % i == 0) return false;
        }
        return true;
    } //Time: O(n) && Space: O(1)

    // 2. Optimized — check divisibility up to sqrt(n)
    public static boolean isPrimeOptimized(int n) {
        if (n <= 1) return false;
        if (n <= 3) return true;
        if (n % 2 == 0 || n % 3 == 0) return false;
        for (int i = 5; (long) i * i <= n; i += 6) {
            if (n % i == 0 || n % (i + 2) == 0) return false;
        }
        return true;
    } //Time: O(√n) && Space: O(1)

    // 3. Sieve of Eratosthenes — for finding all primes up to n
    // (not single-number check)
    public static boolean[] isPrimeSieve(int n) {
        boolean[] isComposite = new boolean[n + 1];
        for (int i = 2; (long) i * i <= n; i++) {
            if (!isComposite[i]) {
                for (int j = i * i; j <= n; j += i) {
                    isComposite[j] = true;
                }
            }
        }
        return isComposite;
    } // Time: O(n log log n) && Space: O(n)

    public static void main(String[] args) {
        int num = 29;
        System.out.println(num + " is prime (basic): " + isPrimeBasic(num));
        System.out.println(num + " is prime (optimized): " + isPrimeOptimized(num));
        System.out.println(num + " is prime (sieve): " + Arrays.toString(isPrimeSieve(num)));
    }

}
