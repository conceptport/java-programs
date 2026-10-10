package com.concept.port.arrayproblems;

import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;
import java.util.TreeSet;

public class FindSecondLargest {

    // 1. Single pass - track largest and second largest together
    public static void findUsingSinglePass(int[] arr) {
        int largest = Integer.MIN_VALUE,
                secondLargest = Integer.MIN_VALUE;
        for (int num : arr) {
            if (num > largest) {
                secondLargest = largest;
                largest = num;
            } else if (num > secondLargest && num != largest) {
                secondLargest = num;
            }
        }
        System.out.println("Single pass: " + secondLargest);
        // Time: O(n) && Space: O(1)-> single traversal, two variables tracked
    }

    // 2. Sorting, then picking second-from-last (handling duplicates)
    public static void findUsingSorting(int[] arr) {
        int[] copy = arr.clone();
        Arrays.sort(copy);
        int secondLargest = Integer.MIN_VALUE;
        for (int i = copy.length - 2; i >= 0; i--) {
            if (copy[i] != copy[copy.length - 1]) {
                secondLargest = copy[i];
                break;
            }
        }
        System.out.println("Sorting: " + secondLargest);
        // Time: O(n log n) && Space: O(n)->cloned array to avoid mutating original,plus sort's internal space
    }

    // 3. Using Streams (sorted descending, distinct, skip first)
    public static void findUsingStreams(int[] arr) {
        Optional<Integer> result = Arrays.stream(arr)
                .distinct()
                .boxed()
                .sorted(Collections.reverseOrder())
                .skip(1)
                .findFirst();
        System.out.println("Streams: " +
                (result.isPresent() ? result.get() : "None"));
        // Time: O(n log n) && Space: O(n)-> boxing, distinct filtering and sort all add overhead
    }

    // 4. Using TreeSet (auto-sorted, auto-deduplicated)
    public static void findUsingTreeSet(int[] arr) {
        TreeSet<Integer> set = new TreeSet<>();
        for (int num : arr) set.add(num);
        if (set.size() < 2) {
            System.out.println("TreeSet: None (not enough distinct elements)");
            return;
        }
        int largest = set.last();
        int secondLargest = set.lower(largest);
        System.out.println("TreeSet: " + secondLargest);
        // Time: O(n log n) && Space: O(n)-> red-black tree stores all distinct elements
    }

    // 5. Brute force - find largest, remove/skip it, find max again
    public static void findUsingBruteForce(int[] arr) {
        int largest = Integer.MIN_VALUE;
        for (int num : arr) {
            if (num > largest) largest = num;
        }
        int secondLargest = Integer.MIN_VALUE;
        for (int num : arr) {
            if (num > secondLargest && num != largest) secondLargest = num;
        }
        System.out.println("Brute force (two-pass): " + secondLargest);
        // Time: O(n) && Space: O(1) -> two full passes but no extra structures,functionally similar to single pass
    }

    public static void main(String[] args) {
        int[] arr = {12, 35, 1, 10, 34, 1};

        findUsingSinglePass(arr);
        findUsingSorting(arr);
        findUsingStreams(arr);
        findUsingTreeSet(arr);
        findUsingBruteForce(arr);
    }

}
