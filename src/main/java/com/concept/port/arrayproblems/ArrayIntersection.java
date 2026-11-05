package com.concept.port.arrayproblems;

import java.util.*;
import java.util.stream.Collectors;

public class ArrayIntersection {

    // 1. Brute force - compare every pair
    public static List<Integer> intersectBruteForce(int[] a, int[] b) {
        Set<Integer> result = new LinkedHashSet<>();
        for (int x : a) {
            for (int y : b) {
                if (x == y) {
                    result.add(x);
                    break;
                }
            }
        }
        return new ArrayList<>(result);
        // Time: O(n*m) && Space: O(k)
    }

    // 2. HashSet - store one array, probe with the other
    public static List<Integer> intersectHashSet(int[] a, int[] b) {
        Set<Integer> set = new HashSet<>();
        for (int x : a) set.add(x);
        Set<Integer> result = new LinkedHashSet<>();
        for (int y : b) {
            if (set.contains(y)) result.add(y);
        }
        return new ArrayList<>(result);
        // Time: O(n + m) && Space: O(n)
    }

    // 3. HashMap frequency count - keeps duplicates (multiset intersection)
    public static List<Integer> intersectHashMapWithDuplicates(int[] a, int[] b) {
        Map<Integer, Integer> freq = new HashMap<>();
        for (int x : a) freq.merge(x, 1, Integer::sum);
        List<Integer> result = new ArrayList<>();
        for (int y : b) {
            if (freq.getOrDefault(y, 0) > 0) {
                result.add(y);
                freq.put(y, freq.get(y) - 1);
            }
        }
        return result;
        // Time: O(n + m) && Space: O(n)
    }

    // 4. Sorting + two pointers
    public static List<Integer> intersectSortedTwoPointer(int[] a, int[] b) {
        int[] x = a.clone();
        int[] y = b.clone();
        Arrays.sort(x);
        Arrays.sort(y);
        Set<Integer> result = new LinkedHashSet<>();
        int i = 0, j = 0;
        while (i < x.length && j < y.length) {
            if (x[i] == y[j]) {
                result.add(x[i]);
                i++;
                j++;
            } else if (x[i] < y[j]) {
                i++;
            } else {
                j++;
            }
        }
        return new ArrayList<>(result);
        // Time: O(n log n + m log m) && Space: O(n + m)
    }

    // 5. Sort one array + binary search for each element of the other
    public static List<Integer> intersectBinarySearch(int[] a, int[] b) {
        int[] sortedB = b.clone();
        Arrays.sort(sortedB);
        Set<Integer> result = new LinkedHashSet<>();
        for (int x : a) {
            if (Arrays.binarySearch(sortedB, x) >= 0) result.add(x);
        }
        return new ArrayList<>(result);
        // Time: O(m log m + n log m) && Space: O(m)
    }

    // 6. Java 8 Streams
    public static List<Integer> intersectStreams(int[] a, int[] b) {
        Set<Integer> setB = Arrays.stream(b).boxed().collect(Collectors.toSet());
        return Arrays.stream(a)
                .distinct()
                .filter(setB::contains)
                .boxed()
                .collect(Collectors.toList());
        // Time: O(n + m) && Space: O(m)
    }

    // 7. Set.retainAll()
    public static List<Integer> intersectRetainAll(int[] a, int[] b) {
        Set<Integer> set1 = new LinkedHashSet<>();
        for (int x : a) set1.add(x);
        Set<Integer> set2 = new HashSet<>();
        for (int y : b) set2.add(y);
        set1.retainAll(set2);
        return new ArrayList<>(set1);
        // Time: O(n + m) && Space: O(n + m)
    }

    public static void main(String[] args) {
        int[] a = {4, 9, 5, 4};
        int[] b = {9, 4, 9, 8, 4};

        System.out.println("Brute force: " + intersectBruteForce(a, b));
        System.out.println("HashSet: " + intersectHashSet(a, b));
        System.out.println("HashMap (with duplicates): " + intersectHashMapWithDuplicates(a, b));
        System.out.println("Sorted two-pointer: " + intersectSortedTwoPointer(a, b));
        System.out.println("Binary search: " + intersectBinarySearch(a, b));
        System.out.println("Streams: " + intersectStreams(a, b));
        System.out.println("retainAll: " + intersectRetainAll(a, b));
    }
}

/*
Which is best:

HashSet: BEST. O(n + m) time, simple, and the standard interview answer (LeetCode 349); put the smaller array in the set to save memory.
HashMap with counts: the right choice when duplicates must be kept (LeetCode 350), for example [2,2] ∩ [2,2] should give [2,2], not [2].
Sort + two-pointer: best when the arrays are already sorted or memory is tight, since it needs no hash structure and gives O(1) extra space in that case. Sorting first costs O(n log n).
Sort + binary search: a middle ground when one array is much smaller than the other, but it is slower than hashing and adds no real advantage in most cases.
retainAll: a clean built-in for Set/List inputs, but it needs boxed collections, so it adds overhead when you start from primitive arrays.
Streams: concise and readable, with the same complexity as HashSet, but boxing and pipeline overhead make it less suited to performance-critical code.
Brute force (AVOID): O(n·m) nested loops are unnecessary when an O(n + m) solution exists; mention it only as the naive baseline.

Practical/interview default: HashSet approach, and clarify up front whether the result should contain duplicates (if yes, switch to the HashMap-count version).*/
