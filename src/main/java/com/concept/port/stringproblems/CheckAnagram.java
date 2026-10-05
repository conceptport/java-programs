package com.concept.port.stringproblems;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class CheckAnagram {

    // 1. Sorting both strings and comparing
    public static boolean isAnagramSorting(String s1, String s2) {
        if (s1.length() != s2.length()) return false;
        char[] arr1 = s1.toLowerCase().toCharArray();
        char[] arr2 = s2.toLowerCase().toCharArray();
        Arrays.sort(arr1);
        Arrays.sort(arr2);
        return Arrays.equals(arr1, arr2);
        // Time: O(n log n) && Space: O(n)-> two char arrays created and sorted
    }

    // 2. Using a fixed-size frequency array (only for lowercase a-z)
    public static boolean isAnagramCharArray(String s1, String s2) {
        if (s1.length() != s2.length()) return false;
        int[] freq = new int[26];
        String a = s1.toLowerCase(), b = s2.toLowerCase();
        for (int i = 0; i < a.length(); i++) {
            freq[a.charAt(i) - 'a']++;
            freq[b.charAt(i) - 'a']--;
        }
        for (int f : freq) {
            if (f != 0) return false;
        }
        return true;
        // Time: O(n) && Space: O(1) -> fixed 26-size array, independent of input length
    }

    // 3. Using HashMap (frequency count, works for any character set)
    public static boolean isAnagramHashMap(String s1, String s2) {
        if (s1.length() != s2.length()) return false;
        Map<Character, Integer> freqMap = new HashMap<>();
        for (char c : s1.toLowerCase().toCharArray()) {
            freqMap.put(c, freqMap.getOrDefault(c, 0) + 1);
        }
        for (char c : s2.toLowerCase().toCharArray()) {
            if (!freqMap.containsKey(c) || freqMap.get(c) == 0) return false;
            freqMap.put(c, freqMap.get(c) - 1);
        }
        return true;
        // Time: O(n) && Space: O(k) -> k = number of unique characters
    }

    // 4. Using HashSet with frequency (alternative HashMap-style two-map comparison)
    public static boolean isAnagramTwoMaps(String s1, String s2) {
        if (s1.length() != s2.length()) return false;
        Map<Character, Integer> map1 = new HashMap<>();
        Map<Character, Integer> map2 = new HashMap<>();
        for (char c : s1.toLowerCase().toCharArray()) {
            map1.put(c, map1.getOrDefault(c, 0) + 1);
        }
        for (char c : s2.toLowerCase().toCharArray()) {
            map2.put(c, map2.getOrDefault(c, 0) + 1);
        }
        return map1.equals(map2);
        // Time: O(n) && Space: O(k) -> two frequency maps, k = unique characters
    }

    // 5. Using Streams (sorted char comparison functionally)
    public static boolean isAnagramStreams(String s1, String s2) {
        if (s1.length() != s2.length()) return false;

        String sorted1 = s1.toLowerCase().chars().sorted()
                .collect(StringBuilder::new,
                        StringBuilder::appendCodePoint, StringBuilder::append).toString();

        String sorted2 = s2.toLowerCase().chars().sorted()
                .collect(StringBuilder::new,
                        StringBuilder::appendCodePoint, StringBuilder::append).toString();
        return sorted1.equals(sorted2);
        // Time: O(n log n) && Space: O(n) -> sorted stream output stored in new strings
    }

    public static void main(String[] args) {
        String s1 = "listen", s2 = "silent";

        System.out.println("Sorting: " + isAnagramSorting(s1, s2));
        System.out.println("Char array: " + isAnagramCharArray(s1, s2));
        System.out.println("HashMap: " + isAnagramHashMap(s1, s2));
        System.out.println("Two maps: " + isAnagramTwoMaps(s1, s2));
        System.out.println("Streams: " + isAnagramStreams(s1, s2));
    }
}
