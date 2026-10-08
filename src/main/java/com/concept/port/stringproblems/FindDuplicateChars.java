package com.concept.port.stringproblems;

import java.util.*;
import java.util.stream.Collectors;

public class FindDuplicateChars {

    // 1. Using HashMap (character -> frequency count)
    public static void findUsingHashMap(String s) {
        Map<Character, Integer> freqMap = new LinkedHashMap<>();
        for (char c : s.toCharArray()) {
            freqMap.put(c, freqMap.getOrDefault(c, 0) + 1);
        }
        System.out.print("HashMap: ");
        for (Map.Entry<Character, Integer> entry : freqMap.entrySet()) {
            if (entry.getValue() > 1) {
                System.out.print(entry.getKey() + "(" + entry.getValue() + ") ");
            }
        }
        System.out.println();
    } //Time: O(n) && Space: O(k) -> k = unique chars

    // 2. Using HashSet (track seen chars, add to duplicates set on repeat)
    public static void findUsingHashSet(String s) {
        Set<Character> seen = new HashSet<>();
        Set<Character> duplicates = new LinkedHashSet<>();
        for (char c : s.toCharArray()) {
            if (!seen.add(c)) {
                duplicates.add(c);
            }
        }
        System.out.println("HashSet: " + duplicates);
    } //Time: O(n) && Space: O(k)

    // 3. Using an int array as frequency counter (only for known charset, e.g. ASCII/lowercase)
    public static void findUsingCharArray(String s) {
        int[] freq = new int[256]; // ASCII range
        for (char c : s.toCharArray()) {
            freq[c]++;
        }
        System.out.print("Char array: ");
        for (char c : s.toCharArray()) {
            if (freq[c] > 1) {
                System.out.print(c + "(" + freq[c] + ") ");
                freq[c] = 0; // avoid printing the same char again
            }
        }
        System.out.println();
    } //Time: O(n) && Space: O(1)
    // -> fixed 256-size array, independent of input

    // 4. Brute force - nested loops, no extra data structure
    public static void findUsingBruteForce(String s) {
        System.out.print("Brute force: ");
        Set<Character> printed = new LinkedHashSet<>();
        for (int i = 0; i < s.length(); i++) {
            for (int j = i + 1; j < s.length(); j++) {
                if (s.charAt(i) == s.charAt(j) && !printed.contains(s.charAt(i))) {
                    System.out.print(s.charAt(i) + " ");
                    printed.add(s.charAt(i));
                }
            }
        }
        System.out.println();
    } //Time: O(n²) && Space: O(1) -> (excluding output tracking)

    // 5. Using Java 8 Streams
    public static void findUsingStreams(String s) {
        Map<Character, Long> freqMap = s.chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.groupingBy(c -> c,
                        LinkedHashMap::new,
                        Collectors.counting()));
        System.out.print("Streams: ");
        freqMap.entrySet().stream()
                .filter(e -> e.getValue() > 1)
                .forEach(e -> System.out.print(e.getKey() + "(" + e.getValue() + ") "));
        System.out.println();
    } //Time: O(n) && Space: O(k) -> plus stream overhead

    public static void main(String[] args) {
        String str = "programming";

        findUsingHashMap(str);
        findUsingHashSet(str);
        findUsingCharArray(str);
        findUsingBruteForce(str);
        findUsingStreams(str);
    }

}
