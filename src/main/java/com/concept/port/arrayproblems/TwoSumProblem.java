package com.concept.port.arrayproblems;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.OptionalInt;

public class TwoSumProblem {

    // 1. Brute force - check every pair
    public static int[] twoSumBruteForce(int[] arr, int target) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] + arr[j] == target) {
                    return new int[]{i, j};
                }
            }
        }
        return new int[]{-1, -1};
        // Time: O(n^2) && Space: O(1)
        // -> nested loops, no extra data structure
    }

    // 2. Sorting + two-pointer (returns values, not original indices)
    public static int[] twoSumSortingTwoPointer(int[] arr, int target) {
        int[] copy = arr.clone();
        Arrays.sort(copy);
        int left = 0, right = copy.length - 1;
        while (left < right) {
            int sum = copy[left] + copy[right];
            if (sum == target) {
                return new int[]{copy[left], copy[right]};
            } else if (sum < target) {
                left++;
            } else {
                right--;
            }
        }
        return new int[]{-1, -1};
        // Time: O(n log n) && Space: O(n)
        // -> cloned array to preserve original,
        // sorting loses original indices
    }

    // 3. HashMap - one-pass (optimal)
    public static int[] twoSumHashMapOnePass(int[] arr, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            int complement = target - arr[i];
            if (map.containsKey(complement)) {
                return new int[]{map.get(complement), i};
            }
            map.put(arr[i], i);
        }
        return new int[]{-1, -1};
        // Time: O(n) && Space: O(n)
        // -> map holds up to n elements,
        // but only one array traversal needed
    }

    // 4. HashMap - two-pass (build map first, then search)
    public static int[] twoSumHashMapTwoPass(int[] arr, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            map.put(arr[i], i);
        }
        for (int i = 0; i < arr.length; i++) {
            int complement = target - arr[i];
            if (map.containsKey(complement) && map.get(complement) != i) {
                return new int[]{i, map.get(complement)};
            }
        }
        return new int[]{-1, -1};
        // Time: O(n) && Space: O(n)
        // -> two full passes over array, map holds all n elements
    }

    // 5. Using Streams (nested pair check, functional style)
    public static int[] twoSumStreams(int[] arr, int target) {
        for (int i = 0; i < arr.length; i++) {
            int idx = i;
            OptionalInt match = java.util.stream.IntStream.range(i + 1, arr.length)
                    .filter(j -> arr[idx] + arr[j] == target)
                    .findFirst();
            if (match.isPresent()) {
                return new int[]{i, match.getAsInt()};
            }
        }
        return new int[]{-1, -1};
        // Time: O(n^2) && Space: O(1)
        // -> same brute-force complexity, plus stream overhead per outer iteration
    }

    public static void main(String[] args) {
        int[] arr = {2, 7, 11, 15};
        int target = 9;

        System.out.println("Brute force: " + Arrays.toString(twoSumBruteForce(arr, target)));
        System.out.println("Sorting + two-pointer: " + Arrays.toString(twoSumSortingTwoPointer(arr, target)));
        System.out.println("HashMap one-pass: " + Arrays.toString(twoSumHashMapOnePass(arr, target)));
        System.out.println("HashMap two-pass: " + Arrays.toString(twoSumHashMapTwoPass(arr, target)));
        System.out.println("Streams: " + Arrays.toString(twoSumStreams(arr, target)));
    }
}
