package com.concept.port.arrayproblems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MoveZerosToEnd {

    // 1. Two-pointer (in-place, single pass, swap-based)
    public static void moveZerosTwoPointer(int[] arr) {
        int[] copy = arr.clone();
        int insertPos = 0;
        for (int i = 0; i < copy.length; i++) {
            if (copy[i] != 0) {
                int temp = copy[insertPos];
                copy[insertPos] = copy[i];
                copy[i] = temp;
                insertPos++;
            }
        }
        System.out.println("Two-pointer: " + Arrays.toString(copy));
        // Time: O(n) && Space: O(1)
        // -> excluding the copy made to preserve original,true in-place swap logic
    }

    // 2. Two-pointer without swapping (overwrite + fill zeros at end)
    public static void moveZerosOverwrite(int[] arr) {
        int[] copy = arr.clone();
        int insertPos = 0;
        for (int num : copy) {
            if (num != 0) {
                copy[insertPos++] = num;
            }
        }
        while (insertPos < copy.length) {
            copy[insertPos++] = 0;
        }
        System.out.println("Overwrite: " + Arrays.toString(copy));
        // Time: O(n) && Space: O(1)
        // -> excluding the copy made to preserve original, two sequential passes
    }

    // 3. Using an extra array
    public static void moveZerosExtraArray(int[] arr) {
        int[] result = new int[arr.length];
        int index = 0;
        for (int num : arr) {
            if (num != 0) result[index++] = num;
        }
        // remaining positions default to 0 automatically
        System.out.println("Extra array: " + Arrays.toString(result));
        // Time: O(n) && Space: O(n)
        // -> new array allocated, not truly in-place
    }

    // 4. Using a List (remove and re-add zeros)
    public static void moveZerosUsingList(int[] arr) {
        List<Integer> list = new ArrayList<>();
        int zeroCount = 0;
        for (int num : arr) {
            if (num != 0) list.add(num);
            else zeroCount++;
        }
        for (int i = 0; i < zeroCount; i++) list.add(0);
        System.out.println("List: " + list);
        // Time: O(n) && Space: O(n)
        // -> boxed List built from primitive array
    }

    // 5. Bubble-style (shift zero to end one step at a time)
    public static void moveZerosBubbleStyle(int[] arr) {
        int[] copy = arr.clone();
        int n = copy.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (copy[j] == 0 && copy[j + 1] != 0) {
                    int temp = copy[j];
                    copy[j] = copy[j + 1];
                    copy[j + 1] = temp;
                }
            }
        }
        System.out.println("Bubble-style: " + Arrays.toString(copy));
        // Time: O(n^2) && Space: O(1)
        // -> excluding the copy made to preserve original, nested loop shifting
    }

    public static void main(String[] args) {
        int[] arr = {0, 1, 0, 3, 12, 0, 5};

        moveZerosTwoPointer(arr);
        moveZerosOverwrite(arr);
        moveZerosExtraArray(arr);
        moveZerosUsingList(arr);
        moveZerosBubbleStyle(arr);
    }

}
