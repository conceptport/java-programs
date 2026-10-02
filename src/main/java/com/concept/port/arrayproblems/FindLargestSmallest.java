package com.concept.port.arrayproblems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class FindLargestSmallest {


    // 1. Linear scan - single pass tracking max and min
    public static void findUsingLinearScan(int[] arr) {
        int max = arr[0], min = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) max = arr[i];
            if (arr[i] < min) min = arr[i];
        }
        System.out.println("Linear scan: Max=" + max + ", Min=" + min);
        // Time: O(n) && Space: O(1)
        // -> single pass, no extra array or structure
    }

    // 2. Sorting the array, then picking first/last elements
    public static void findUsingSorting(int[] arr) {
        int[] copy = arr.clone();
        Arrays.sort(copy);
        int min = copy[0], max = copy[copy.length - 1];
        System.out.println("Sorting: Max=" + max + ", Min=" + min);
        // Time: O(n log n) && Space: O(n)
        //-> cloned array to avoid mutating original,sort's internal space
    }

    // 3. Using Java Streams
    public static void findUsingStreams(int[] arr) {
        int max = Arrays.stream(arr).max().getAsInt();
        int min = Arrays.stream(arr).min().getAsInt();
        System.out.println("Streams: Max=" + max + ", Min=" + min);
        // Time: O(n) && Space: O(1)
        // -> excluding stream internal overhead, two separate passes
    }


    // 4. Using Collections (requires boxed List<Integer>)
    public static void findUsingCollections(int[] arr) {
        List<Integer> list = new ArrayList<>();
        for (int num : arr) list.add(num);
        int max = Collections.max(list);
        int min = Collections.min(list);
        System.out.println("Collections: Max=" + max + ", Min=" + min);
        // Time: O(n) && Space: O(n)
        // -> boxed Integer list built from primitive array
    }

    // 5. Recursive
    public static int[] findUsingRecursion(int[] arr, int index, int max, int min) {
        if (index == arr.length) return new int[]{max, min};
        if (arr[index] > max) max = arr[index];
        if (arr[index] < min) min = arr[index];
        return findUsingRecursion(arr, index + 1, max, min);
        // Time: O(n) && Space: O(n) -> recursion call stack depth equal to array length
    }

    // 6. Divide and conquer
    public static int[] findUsingDivideAndConquer(int[] arr, int low, int high) {
        if (low == high) return new int[]{arr[low], arr[low]};
        if (high - low == 1) {
            return new int[]{Math.max(arr[low], arr[high]), Math.min(arr[low], arr[high])};
        }
        int mid = (low + high) / 2;
        int[] left = findUsingDivideAndConquer(arr, low, mid);
        int[] right = findUsingDivideAndConquer(arr, mid + 1, high);
        return new int[]{Math.max(left[0], right[0]), Math.min(left[1], right[1])};
        // Time: O(n) && Space: O(log n) -> recursion depth from halving the array each call
    }


    public static void main(String[] args) {
        int[] arr = {5, 9, 1, 3, 7, 2, 8};

        findUsingLinearScan(arr);
        findUsingSorting(arr);
        findUsingStreams(arr);
        findUsingCollections(arr);

        int[] recResult = findUsingRecursion(arr, 0, arr[0], arr[0]);
        System.out.println("Recursion: Max=" + recResult[0] + ", Min=" + recResult[1]);

        int[] dcResult = findUsingDivideAndConquer(arr, 0, arr.length - 1);
        System.out.println("Divide & Conquer: Max=" + dcResult[0] + ", Min=" + dcResult[1]);
    }

}
