package com.concept.port.stringproblems;

public class CountVowelsConsonants {

    // 1. Using if-else / switch with char comparison
    public static void countUsingIfElse(String s) {
        int vowels = 0, consonants = 0;
        String str = s.toLowerCase();
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (!Character.isLetter(c)) continue;
            if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') {
                vowels++;
            } else {
                consonants++;
            }
        }
        System.out.println("If-else: Vowels=" + vowels + ", Consonants=" + consonants);
        // Time: O(n) && Space: O(1)
    }

    // 2. Using a String constant with indexOf/contains check
    public static void countUsingIndexOf(String s) {
        String vowelsStr = "aeiou";
        int vowels = 0, consonants = 0;
        String str = s.toLowerCase();
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (!Character.isLetter(c)) continue;
            if (vowelsStr.indexOf(c) != -1) {
                vowels++;
            } else {
                consonants++;
            }
        }
        System.out.println("IndexOf: Vowels=" + vowels + ", Consonants=" + consonants);
        // Time: O(n) && Space: O(1) -> indexOf on 5-char string is effectively O(1)
    }

    // 3. Using a HashSet for vowel lookup
    public static void countUsingHashSet(String s) {
        java.util.Set<Character> vowelSet = new java.util.HashSet<>(
                java.util.Arrays.asList('a', 'e', 'i', 'o', 'u'));
        int vowels = 0, consonants = 0;
        String str = s.toLowerCase();
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (!Character.isLetter(c)) continue;
            if (vowelSet.contains(c)) {
                vowels++;
            } else {
                consonants++;
            }
        }
        System.out.println("HashSet: Vowels=" + vowels + ", Consonants=" + consonants);
        // Time: O(n) && Space: O(1) -> fixed-size set
    }

    // 4. Recursive
    public static int[] countUsingRecursion(String s, int index, int vowels, int consonants) {
        if (index == s.length()) return new int[]{vowels, consonants};
        char c = Character.toLowerCase(s.charAt(index));
        if (Character.isLetter(c)) {
            if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') {
                vowels++;
            } else {
                consonants++;
            }
        }
        return countUsingRecursion(s, index + 1, vowels, consonants);
        // Time: O(d²) && Space: O(d) -> recursion stack depth
    }

    // 5. Using Java 8 Streams
    public static void countUsingStreams(String s) {
        String str = s.toLowerCase();
        long vowels = str.chars()
                .filter(c -> Character.isLetter(c) && "aeiou".indexOf(c) != -1)
                .count();
        long consonants = str.chars()
                .filter(c -> Character.isLetter(c) && "aeiou".indexOf(c) == -1)
                .count();
        System.out.println("Streams: Vowels=" + vowels + ", Consonants=" + consonants);
        // Time: O(n) && Space: O(1) -> excluding stream internal overhead
    }

    public static void main(String[] args) {
        String str = "Hello World";

        countUsingIfElse(str);
        countUsingIndexOf(str);
        countUsingHashSet(str);

        int[] result = countUsingRecursion(str, 0, 0, 0);
        System.out.println("Recursion: Vowels=" + result[0] + ", Consonants=" + result[1]);

        countUsingStreams(str);
    }

}
