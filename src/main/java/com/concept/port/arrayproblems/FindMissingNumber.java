package com.concept.port.arrayproblems;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class FindMissingNumber {

    // 1. Sum formula (expected sum - actual sum)
    public static int findUsingSumFormula(int[] arr, int n) {
        int expectedSum = n * (n + 1) / 2;
        int actualSum = 0;
        for (int num : arr) {
            actualSum += num;
        }
        return expectedSum - actualSum;
        // Time: O(n) && Space: O(1)
    }

    // 2. XOR approach (avoids overflow risk with large sums)
    public static int findUsingXOR(int[] arr, int n) {
        int xorAll = 0, xorArr = 0;
        for (int i = 1; i <= n; i++) {
            xorAll ^= i;
        }
        for (int num : arr) {
            xorArr ^= num;
        }
        return xorAll ^ xorArr;
        // Time: O(n) && Space: O(1)
    }

    // 3. Sorting, then checking sequential gap
    public static int findUsingSorting(int[] arr, int n) {
        int[] copy = arr.clone();
        Arrays.sort(copy);
        for (int i = 0; i < copy.length; i++) {
            if (copy[i] != i + 1) {
                return i + 1;
            }
        }
        return n;
        // Time: O(n log n) && Space: O(n)
    }

    // 4. Using HashSet (mark present numbers, check which is missing)
    public static int findUsingHashSet(int[] arr, int n) {
        Set<Integer> present = new HashSet<>();
        for (int num : arr) present.add(num);
        for (int i = 1; i <= n; i++) {
            if (!present.contains(i)) return i;
        }
        return -1;
        // Time: O(n) && Space: O(n)
    }

    // 5. Using boolean array marker
    public static int findUsingBooleanArray(int[] arr, int n) {
        boolean[] present = new boolean[n + 1];
        for (int num : arr) present[num] = true;
        for (int i = 1; i <= n; i++) {
            if (!present[i]) return i;
        }
        return -1;
        // Time: O(n) && Space: O(n)
    }

    // 6. Using Streams
    public static int findUsingStreams(int[] arr, int n) {
        int expectedSum = n * (n + 1) / 2;
        int actualSum = Arrays.stream(arr).sum();
        return expectedSum - actualSum;
        // Time: O(n) && Space: O(1)
    }

    public static void main(String[] args) {
        int n = 10;
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 9, 10}; // 8 is missing

        System.out.println("Sum formula: " + findUsingSumFormula(arr, n));
        System.out.println("XOR: " + findUsingXOR(arr, n));
        System.out.println("Sorting: " + findUsingSorting(arr, n));
        System.out.println("HashSet: " + findUsingHashSet(arr, n));
        System.out.println("Boolean array: " + findUsingBooleanArray(arr, n));
        System.out.println("Streams: " + findUsingStreams(arr, n));
    }
}