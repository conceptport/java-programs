package com.concept.port.stringproblems;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.IntStream;

public class StringCompression {

    // 1. Using StringBuilder with character counting (standard approach)
    public static String compressUsingStringBuilder(String s) {
        if (s == null || s.isEmpty()) return s;
        StringBuilder sb = new StringBuilder();
        int count = 1;
        for (int i = 1; i <= s.length(); i++) {
            if (i < s.length() && s.charAt(i) == s.charAt(i - 1)) {
                count++;
            } else {
                sb.append(s.charAt(i - 1));
                if (count > 1) sb.append(count);
                count = 1;
            }
        }
        return sb.length() < s.length() ? sb.toString() : s;
        // Time: O(n) && Space: O(n)
    }

    // 2. Using a char array (in-place style, two-pointer compression)
    public static String compressUsingCharArray(String s) {
        if (s == null || s.isEmpty()) return s;
        char[] chars = s.toCharArray();
        int readIndex = 0;
        char[] result = new char[chars.length * 2];
        int resultIndex = 0;
        while (readIndex < chars.length) {
            char currentChar = chars[readIndex];
            int count = 0;
            while (readIndex < chars.length && chars[readIndex] == currentChar) {
                readIndex++;
                count++;
            }
            result[resultIndex++] = currentChar;
            if (count > 1) {
                for (char c : String.valueOf(count).toCharArray()) {
                    result[resultIndex++] = c;
                }
            }
        }
        String compressed = new String(result, 0, resultIndex);
        return compressed.length() < s.length() ? compressed : s;
        // Time: O(n) && Space: O(n)
    }

    // 3. Using HashMap (groups all occurrences, NOT order-preserving for non-adjacent duplicates)
    public static String compressUsingHashMap(String s) {
        if (s == null || s.isEmpty()) return s;
        Map<Character, Integer> freqMap = new LinkedHashMap<>();
        for (char c : s.toCharArray()) {
            freqMap.put(c, freqMap.getOrDefault(c, 0) + 1);
        }
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<Character, Integer> entry : freqMap.entrySet()) {
            sb.append(entry.getKey());
            if (entry.getValue() > 1) sb.append(entry.getValue());
        }
        return sb.length() < s.length() ? sb.toString() : s;
        // Time: O(n) && Space: O(k)
    }

    // 4. Recursive
    public static String compressUsingRecursion(String s) {
        if (s == null || s.isEmpty()) return s;
        String result = compressHelper(s, 0, new StringBuilder());
        return result.length() < s.length() ? result : s;
        // Time: O(n) && Space: O(n)
    }

    private static String compressHelper(String s, int index, StringBuilder sb) {
        if (index >= s.length()) return sb.toString();
        char currentChar = s.charAt(index);
        int count = 0;
        int i = index;
        while (i < s.length() && s.charAt(i) == currentChar) {
            count++;
            i++;
        }
        sb.append(currentChar);
        if (count > 1) sb.append(count);
        return compressHelper(s, i, sb);
    }

    // 5. Using Java 8 Streams (grouping consecutive chars is awkward with streams, shown for completeness)
    public static String compressUsingStreams(String s) {
        if (s == null || s.isEmpty()) return s;
        StringBuilder sb = new StringBuilder();
        int i = 0;
        while (i < s.length()) {
            char currentChar = s.charAt(i);
            int finalI = i;
            int nextDiffIndex = IntStream.range(finalI + 1, s.length())
                    .filter(j -> s.charAt(j) != currentChar)
                    .findFirst()
                    .orElse(s.length());
            int count = nextDiffIndex - finalI;
            sb.append(currentChar);
            if (count > 1) sb.append(count);
            i = nextDiffIndex;
        }
        return sb.length() < s.length() ? sb.toString() : s;
        // Time: O(n^2) && Space: O(n)
    }

    public static void main(String[] args) {
        String str = "aabcccccaaa";

        System.out.println("StringBuilder: " + compressUsingStringBuilder(str));
        System.out.println("Char array: " + compressUsingCharArray(str));
        System.out.println("HashMap: " + compressUsingHashMap(str));
        System.out.println("Recursive: " + compressUsingRecursion(str));
        System.out.println("Streams: " + compressUsingStreams(str));
    }
}
