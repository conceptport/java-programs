package com.concept.port.arrayproblems;

import java.util.*;

public class FindDuplicatesInArray {

    // 1. Brute force - nested loops comparing every pair

    public static void findUsingBruteForce(int[] arr) {
        Set<Integer> duplicates = new LinkedHashSet<>();
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] == arr[j]) {
                    duplicates.add(arr[i]);
                }
            }
        }
        System.out.println("Brute force: " + duplicates);
        // Time: O(n^2) && Space: O(k)
        // -> k = number of duplicate elements found
    }

    // 2. Using HashSet - track seen elements

    public static void findUsingHashSet(int[] arr) {
        Set<Integer> seen = new HashSet<>();
        Set<Integer> duplicates = new LinkedHashSet<>();
        for (int num : arr) {
            if (!seen.add(num)) {
                duplicates.add(num);
            }
        }
        System.out.println("HashSet: " + duplicates);
        // Time: O(n) && Space: O(n)
        // -> seen set + duplicates set, up to n elements total
    }

    // 3. Using HashMap - frequency count

    public static void findUsingHashMap(int[] arr) {
        Map<Integer, Integer> freqMap = new LinkedHashMap<>();
        for (int num : arr) {
            freqMap.put(num, freqMap.getOrDefault(num, 0) + 1);
        }
        System.out.print("HashMap: ");
        for (Map.Entry<Integer, Integer> entry : freqMap.entrySet()) {
            if (entry.getValue() > 1) {
                System.out.print(entry.getKey() + "(" + entry.getValue() + ") ");
            }
        }
        System.out.println();
        // Time: O(n) && Space: O(n)
        // -> frequency map holds up to n unique keys
    }

    // 4. Sorting, then checking adjacent elements

    public static void findUsingSorting(int[] arr) {
        int[] copy = arr.clone();
        Arrays.sort(copy);
        Set<Integer> duplicates = new LinkedHashSet<>();
        for (int i = 1; i < copy.length; i++) {
            if (copy[i] == copy[i - 1]) {
                duplicates.add(copy[i]);
            }
        }
        System.out.println("Sorting: " + duplicates);
        // Time: O(n log n) && Space: O(n)
        // -> cloned array to avoid mutating original,
        // plus sort's internal space
    }

    // 5. Using array indices as hash (only works for values in range 0 to n-1, values modified temporarily)

    public static void findUsingIndexMarking(int[] arr) {
        int[] copy = arr.clone(); // work on a copy to preserve original
        Set<Integer> duplicates = new LinkedHashSet<>();
        for (int i = 0; i < copy.length; i++) {
            int index = Math.abs(copy[i]);
            if (copy[index] < 0) {
                duplicates.add(index);
            } else {
                copy[index] = -copy[index];
            }
        }
        System.out.println("Index marking: " + duplicates);
        // Time: O(n) && Space: O(1) -> excluding the copy array,
        // uses input array itself as a hash table
        // (values must be in range 0 to n-1)
    }

    public static void main(String[] args) {
        int[] arr = {4, 3, 2, 7, 8, 2, 3, 1};

        findUsingBruteForce(arr);
        findUsingHashSet(arr);
        findUsingHashMap(arr);
        findUsingSorting(arr);

        int[] arr2 = {1, 3, 4, 2, 2}; // must be within range 0 to n-1 for index marking
        findUsingIndexMarking(arr2);
    }
}
