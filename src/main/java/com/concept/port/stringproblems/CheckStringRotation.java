package com.concept.port.stringproblems;

import java.util.ArrayDeque;
import java.util.Deque;

public class CheckStringRotation {

    // 1. Concatenation trick: s2 must be a substring of s1 + s1
    public static boolean isRotationConcat(String s1, String s2) {
        if (s1.length() != s2.length()) return false;
        return (s1 + s1).contains(s2);
        // Time: O(n) average && Space: O(n)
    }

    // 2. Concatenation + KMP (guaranteed linear substring search)
    public static boolean isRotationKMP(String s1, String s2) {
        if (s1.length() != s2.length()) return false;
        if (s2.isEmpty()) return true;
        String text = s1 + s1;
        int[] lps = buildLPS(s2);
        int i = 0, j = 0;
        while (i < text.length()) {
            if (text.charAt(i) == s2.charAt(j)) {
                i++;
                j++;
                if (j == s2.length()) return true;
            } else if (j > 0) {
                j = lps[j - 1];
            } else {
                i++;
            }
        }
        return false;
        // Time: O(n) && Space: O(n)
    }

    private static int[] buildLPS(String p) {
        int[] lps = new int[p.length()];
        int len = 0, i = 1;
        while (i < p.length()) {
            if (p.charAt(i) == p.charAt(len)) {
                lps[i++] = ++len;
            } else if (len > 0) {
                len = lps[len - 1];
            } else {
                lps[i++] = 0;
            }
        }
        return lps;
    }

    // 3. Circular index comparison (no extra string built)
    public static boolean isRotationCircular(String s1, String s2) {
        int n = s1.length();
        if (n != s2.length()) return false;
        if (n == 0) return true;
        for (int start = 0; start < n; start++) {
            int k = 0;
            while (k < n && s1.charAt((start + k) % n) == s2.charAt(k)) k++;
            if (k == n) return true;
        }
        return false;
        // Time: O(n^2) && Space: O(1)
    }

    // 4. Brute force - build every rotation with substring and compare
    public static boolean isRotationBruteForce(String s1, String s2) {
        int n = s1.length();
        if (n != s2.length()) return false;
        if (n == 0) return true;
        for (int i = 0; i < n; i++) {
            String rotated = s1.substring(i) + s1.substring(0, i);
            if (rotated.equals(s2)) return true;
        }
        return false;
        // Time: O(n^2) && Space: O(n)
    }

    // 5. Queue-based - rotate one character at a time and compare
    public static boolean isRotationQueue(String s1, String s2) {
        int n = s1.length();
        if (n != s2.length()) return false;
        if (n == 0) return true;
        Deque<Character> queue = new ArrayDeque<>();
        for (char c : s1.toCharArray()) queue.addLast(c);
        for (int i = 0; i < n; i++) {
            StringBuilder sb = new StringBuilder();
            for (char c : queue) sb.append(c);
            if (sb.toString().equals(s2)) return true;
            queue.addLast(queue.removeFirst());
        }
        return false;
        // Time: O(n^2) && Space: O(n)
    }

    public static void main(String[] args) {
        String s1 = "ABCD";
        String s2 = "CDAB"; // rotation of s1 by 2
        String s3 = "ACBD"; // same letters, NOT a rotation

        System.out.println("Concat: " + isRotationConcat(s1, s2));
        System.out.println("KMP: " + isRotationKMP(s1, s2));
        System.out.println("Circular: " + isRotationCircular(s1, s2));
        System.out.println("Brute force: " + isRotationBruteForce(s1, s2));
        System.out.println("Queue: " + isRotationQueue(s1, s2));

        System.out.println("Negative case (Concat): " + isRotationConcat(s1, s3)); // false
    }
}
