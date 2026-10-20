package com.concept.port.stringproblems;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class FirstNonRepeatingChar {

    // 1. Using LinkedHashMap (preserves insertion order, frequency count)
    public static char findUsingLinkedHashMap(String s) {
        Map<Character, Integer> freqMap = new LinkedHashMap<>();
        for (char c : s.toCharArray()) {
            freqMap.put(c, freqMap.getOrDefault(c, 0) + 1);
        }
        for (Map.Entry<Character, Integer> entry : freqMap.entrySet()) {
            if (entry.getValue() == 1) return entry.getKey();
        }
        return '\0';
        // Time: O(n) && Space: O(k)
    }

    // 2. Using a fixed-size frequency array (only for known charset, e.g. ASCII), two passes
    public static char findUsingCharArray(String s) {
        int[] freq = new int[256];
        for (char c : s.toCharArray()) {
            freq[c]++;
        }
        for (char c : s.toCharArray()) {
            if (freq[c] == 1) return c;
        }
        return '\0';
        // Time: O(n) && Space: O(1)
    }

    // 3. Using HashMap + separate order tracking via original string scan
    public static char findUsingHashMap(String s) {
        Map<Character, Integer> freqMap = new HashMap<>();
        for (char c : s.toCharArray()) {
            freqMap.put(c, freqMap.getOrDefault(c, 0) + 1);
        }
        for (char c : s.toCharArray()) {
            if (freqMap.get(c) == 1) return c;
        }
        return '\0';
        // Time: O(n) && Space: O(k)
    }

    // 4. Brute force - nested loops counting occurrences
    public static char findUsingBruteForce(String s) {
        for (int i = 0; i < s.length(); i++) {
            boolean repeated = false;
            for (int j = 0; j < s.length(); j++) {
                if (i != j && s.charAt(i) == s.charAt(j)) {
                    repeated = true;
                    break;
                }
            }
            if (!repeated) return s.charAt(i);
        }
        return '\0';
        // Time: O(n^2) && Space: O(1)
    }

    // 5. Using Java 8 Streams
    public static char findUsingStreams(String s) {
        Map<Character, Long> freqMap = s.chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.groupingBy(c -> c,
                        LinkedHashMap::new, Collectors.counting()));
        return freqMap.entrySet().stream()
                .filter(e -> e.getValue() == 1)
                .map(Map.Entry::getKey)
                .findFirst()
                .orElse('\0');
        // Time: O(n) && Space: O(k)
    }

    public static void main(String[] args) {
        String str = "swiss";

        System.out.println("LinkedHashMap: " + findUsingLinkedHashMap(str));
        System.out.println("Char array: " + findUsingCharArray(str));
        System.out.println("HashMap (two-pass): " + findUsingHashMap(str));
        System.out.println("Brute force: " + findUsingBruteForce(str));
        System.out.println("Streams: " + findUsingStreams(str));
    }
}