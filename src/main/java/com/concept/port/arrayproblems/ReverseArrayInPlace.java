package com.concept.port.arrayproblems;

import java.util.*;

public class ReverseArrayInPlace {

    // 1. Two-pointer swap (classic in-place reversal)
    public static void reverseTwoPointer(int[] arr) {
        int left = 0, right = arr.length - 1;
        while (left < right) {
            int temp = arr[left];
            arr[left] = arr[right];
            arr[right] = temp;
            left++;
            right--;
        }
        System.out.println("Two-pointer: " + Arrays.toString(arr));
        // Time: O(n) && Space: O(1)
        // -> swaps within the same array, no extra memory
    }

    // 2. XOR swap (avoids temp variable, numeric only)
    public static void reverseUsingXOR(int[] arr) {
        int left = 0, right = arr.length - 1;
        while (left < right) {
            if (left != right) { // guard: XOR fails if same index
                arr[left] ^= arr[right];
                arr[right] ^= arr[left];
                arr[left] ^= arr[right];
            }
            left++;
            right--;
        }
        System.out.println("XOR swap: " + Arrays.toString(arr));
        // Time: O(n) && Space: O(1)
        // -> no temp variable, pure in-place bitwise swap
    }

    // 3. Recursive in-place swap
    public static void reverseUsingRecursion(int[] arr, int left, int right) {
        if (left >= right) return;
        int temp = arr[left];
        arr[left] = arr[right];
        arr[right] = temp;
        reverseUsingRecursion(arr, left + 1, right - 1);
        // Time: O(n) && Space: O(n)
        // -> recursion call stack depth of n/2
    }

    // 4. Using Collections.reverse() (requires boxed List)
    public static void reverseUsingCollections(int[] arr) {
        List<Integer> list = new ArrayList<>();
        for (int num : arr) list.add(num);
        Collections.reverse(list);
        for (int i = 0; i < arr.length; i++) arr[i] = list.get(i);
        System.out.println("Collections: " + Arrays.toString(arr));
        // Time: O(n) && Space: O(n)
        // -> boxed List built from primitive array,defeats true in-place goal
    }

    // 5. Using a Stack
    public static void reverseUsingStack(int[] arr) {
        Deque<Integer> stack = new ArrayDeque<>();
        for (int num : arr) stack.push(num);
        for (int i = 0; i < arr.length; i++) arr[i] = stack.pop();
        System.out.println("Stack: " + Arrays.toString(arr));
        // Time: O(n) && Space: O(n) -> stack holds a full copy of the array
    }

    public static void main(String[] args) {
        int[] arr1 = {1, 2, 3, 4, 5};
        reverseTwoPointer(arr1);
        reverseUsingXOR(arr1);

        reverseUsingRecursion(arr1, 0, arr1.length - 1);
        System.out.println("Recursion: " + Arrays.toString(arr1));

        reverseUsingCollections(arr1);
        reverseUsingStack(arr1);
    }

}
