package com.concept.port.arrayproblems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class RotateArray {

    // 1. Using extra array (place each element at its new position)
    public static int[] rotateUsingExtraArray(int[] arr, int k) {
        int n = arr.length;
        k = k % n;
        int[] result = new int[n];
        for (int i = 0; i < n; i++) {
            result[(i + k) % n] = arr[i];
        }
        return result;
        // Time: O(n) && Space: O(n)
    }

    // 2. Reversal algorithm (in-place, reverse whole, then reverse parts)
    public static void rotateUsingReversal(int[] arr, int k) {
        int n = arr.length;
        k = k % n;
        reverse(arr, 0, n - 1);
        reverse(arr, 0, k - 1);
        reverse(arr, k, n - 1);
        // Time: O(n) && Space: O(1)
    }

    private static void reverse(int[] arr, int left, int right) {
        while (left < right) {
            int temp = arr[left];
            arr[left] = arr[right];
            arr[right] = temp;
            left++;
            right--;
        }
    }

    // 3. Cyclic replacement (in-place, move elements directly to final position)
    public static void rotateUsingCyclicReplacement(int[] arr, int k) {
        int n = arr.length;
        k = k % n;
        int count = 0;
        for (int start = 0; count < n; start++) {
            int current = start;
            int prev = arr[start];
            do {
                int next = (current + k) % n;
                int temp = arr[next];
                arr[next] = prev;
                prev = temp;
                current = next;
                count++;
            } while (current != start);
        }
        // Time: O(n) && Space: O(1)
    }

    // 4. One-by-one rotation (rotate by 1, repeat k times)
    public static void rotateOneByOne(int[] arr, int k) {
        int n = arr.length;
        k = k % n;
        for (int i = 0; i < k; i++) {
            int last = arr[n - 1];
            for (int j = n - 1; j > 0; j--) {
                arr[j] = arr[j - 1];
            }
            arr[0] = last;
        }
        // Time: O(n*k) && Space: O(1)
    }

    // 5. Using ArrayList / Collections (via boxed list, not primitive in-place)
    public static Integer[] rotateUsingCollections(Integer[] arr, int k) {
        List<Integer> list = new ArrayList<>(Arrays.asList(arr));
        Collections.rotate(list, k);
        return list.toArray(new Integer[0]);
        // Time: O(n) && Space: O(n)
    }

    public static void main(String[] args) {
        int[] arr1 = {1, 2, 3, 4, 5, 6, 7};
        int k = 3;

        System.out.println("Extra array: " + Arrays.toString(rotateUsingExtraArray(arr1, k)));

        int[] arr2 = {1, 2, 3, 4, 5, 6, 7};
        rotateUsingReversal(arr2, k);
        System.out.println("Reversal algorithm: " + Arrays.toString(arr2));

        int[] arr3 = {1, 2, 3, 4, 5, 6, 7};
        rotateUsingCyclicReplacement(arr3, k);
        System.out.println("Cyclic replacement: " + Arrays.toString(arr3));

        int[] arr4 = {1, 2, 3, 4, 5, 6, 7};
        rotateOneByOne(arr4, k);
        System.out.println("One-by-one: " + Arrays.toString(arr4));

        Integer[] arr5 = {1, 2, 3, 4, 5, 6, 7};
        System.out.println("Collections: " + Arrays.toString(rotateUsingCollections(arr5, k)));
    }
}