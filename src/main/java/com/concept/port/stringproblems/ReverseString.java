package com.concept.port.stringproblems;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.stream.IntStream;

public class ReverseString {

    // 1. Using StringBuilder.reverse()
    // (built-in, most common in practice)
    public static String reverseStringBuilder(String s) {
        return new StringBuilder(s).reverse().toString();
    } // Time: O(n) && Space: O(n)

    // 2. Two-pointer with char array (manual, in-place)
    public static String reverseTwoPointer(String s) {
        char[] chars = s.toCharArray();
        int left = 0, right = chars.length - 1;
        while (left < right) {
            char temp = chars[left];
            chars[left] = chars[right];
            chars[right] = temp;
            left++;
            right--;
        }
        return new String(chars);
    } // Time: O(n) && Space: O(n) -> new char array

    // 3. Iterative -build new string by appending from the end
    public static String reverseIterative(String s) {
        String result = "";
        for (int i = s.length() - 1; i >= 0; i--) {
            result += s.charAt(i);
        }
        return result;
    } // Time: O(n²) && Space: O(n²)
    // -> new String object created each iteration


    // 4. Recursion
    public static String reverseRecursive(String s) {
        if (s.isEmpty()) return s;
        return reverseRecursive(s.substring(1)) + s.charAt(0);
    } // Time: O(n) && Space: O(n)
    // -> call stack+substring allocations

    // 5. Using a Stack
    public static String reverseUsingStack(String s) {
        Deque<Character> stack = new ArrayDeque<>();
        for (char c : s.toCharArray()) {
            stack.push(c);
        }
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }
        return sb.toString();
    } // Time: O(n) && Space: O(n) -> stack + result string

    // 6. Using Java 8 Streams
    public static String reverseUsingStreams(String s) {
        return IntStream.range(0, s.length())
                .mapToObj(i -> s.charAt(s.length() - 1 - i))
                .collect(StringBuilder::new,
                        StringBuilder::appendCodePoint,
                        StringBuilder::append)
                .toString();
    } // Time: O(n) && Space: O(n)
    // -> plus stream overhead


    public static void main(String[] args) {
        String str = "hello";

        System.out.println("StringBuilder: " + reverseStringBuilder(str));
        System.out.println("Two-pointer: " + reverseTwoPointer(str));
        System.out.println("Iterative (concat): " + reverseIterative(str));
        System.out.println("Recursive: " + reverseRecursive(str));
        System.out.println("Using Stack: " + reverseUsingStack(str));
        System.out.println("Using Streams: " + reverseUsingStreams(str));
    }

}
