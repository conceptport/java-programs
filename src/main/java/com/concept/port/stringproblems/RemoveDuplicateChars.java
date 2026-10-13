package com.concept.port.stringproblems;

import java.util.LinkedHashSet;
import java.util.Set;

public class RemoveDuplicateChars {

    // 1. Using LinkedHashSet (preserves insertion order)
    public static String removeUsingLinkedHashSet(String s) {
        Set<Character> seen = new LinkedHashSet<>();
        for (char c : s.toCharArray()) {
            seen.add(c);
        }
        StringBuilder sb = new StringBuilder();
        for (char c : seen) sb.append(c);
        return sb.toString();
        // Time: O(n) && Space: O(k)
        // -> k = unique characters, set preserves original order
    }

    // 2. Using a fixed-size boolean array (only for known charset, e.g. ASCII)
    public static String removeUsingCharArray(String s) {
        boolean[] seen = new boolean[256];
        StringBuilder sb = new StringBuilder();
        for (char c : s.toCharArray()) {
            if (!seen[c]) {
                seen[c] = true;
                sb.append(c);
            }
        }
        return sb.toString();
        // Time: O(n) && Space: O(1)
        // -> fixed 256-size array, independent of input length
    }

    // 3. Using StringBuilder with indexOf check (no Set/array)
    public static String removeUsingStringBuilder(String s) {
        StringBuilder sb = new StringBuilder();
        for (char c : s.toCharArray()) {
            if (sb.indexOf(String.valueOf(c)) == -1) {
                sb.append(c);
            }
        }
        return sb.toString();
        // Time: O(n^2) && Space: O(n)
        // -> indexOf scans the built string on every character,
        // plus String.valueOf allocation each call
    }

    // 4. Brute force - nested loops checking prior characters
    public static String removeUsingBruteForce(String s) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            boolean duplicate = false;
            for (int j = 0; j < i; j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    duplicate = true;
                    break;
                }
            }
            if (!duplicate) sb.append(s.charAt(i));
        }
        return sb.toString();
        // Time: O(n^2) && Space: O(n)
        // -> nested comparison loops, result string grows up to n
    }

    // 5. Using Java 8 Streams
    public static String removeUsingStreams(String s) {
        return s.chars()
                .distinct()
                .mapToObj(c -> String.valueOf((char) c))
                .collect(java.util.stream.Collectors.joining());
        // Time: O(n) && Space: O(k)
        // -> k unique chars, plus stream pipeline overhead
    }

    public static void main(String[] args) {
        String str = "programming";

        System.out.println("LinkedHashSet: " + removeUsingLinkedHashSet(str));
        System.out.println("Char array: " + removeUsingCharArray(str));
        System.out.println("StringBuilder indexOf: " + removeUsingStringBuilder(str));
        System.out.println("Brute force: " + removeUsingBruteForce(str));
        System.out.println("Streams: " + removeUsingStreams(str));
    }

}
